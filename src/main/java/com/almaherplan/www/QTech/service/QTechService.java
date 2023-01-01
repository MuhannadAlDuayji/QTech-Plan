package com.almaherplan.www.QTech.service;

import com.almaherplan.www.QTech.model.*;
import com.almaherplan.www.QTech.repository.SorahRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class QTechService {

    @Autowired
    private SorahRepository sorahRepository;

    @Value("${DEST.PATH}")
    private String outputPath;

    @Value("${FILE}")
    public  String DEST;


    public ResponseQTech responseQTech(RequestQTech requestQTech){

        ResponseQTech responseQTech = new ResponseQTech();
        List<Drs> memorized = null;
        List<Drs> smallMemorized = null;
        List<Drs> biggestMemorized = null;

        boolean rid = requestQTech.isWayForMemorized();
        List<Sorah> sorahList;

        // calculate Page * 15 + lines
        BigDecimal originalLength = requestQTech.getNumberOfPageDrs()
                .multiply(BigDecimal.valueOf(15));
        originalLength = originalLength.add(requestQTech.getNumberOfLineDrs());

        BigDecimal numberOfPageBiggestMemorized = requestQTech.getNumberOfPageBiggestMemorized()
                .multiply(BigDecimal.valueOf(15));

        System.out.println("Number of lines : "+originalLength);

        // where Drs start
        Sorah toGetStart = sorahRepository.findSorahBySorahAndAndAyah(requestQTech.getNumberOfSorah(),requestQTech.getNumberOfAyah());

        System.out.println("Request :: "+requestQTech);

        if(rid){
            sorahList = sorahRepository.findSorahByRidGreaterThanEqualOrderByRid(toGetStart.getRid());
            memorized = getPlan(originalLength,requestQTech.getNumberOfDayForPlan(),requestQTech.getPercentage()
                    ,sorahList,requestQTech.getNumberOfDayPeerWeek(),requestQTech.getNumberOfDaysSmallMemorized());

            if(requestQTech.getNumberOfRememorizedDrs() != 0)
                smallMemorized = getSmallMemorized(memorized, requestQTech.getNumberOfRememorizedDrs(),requestQTech.getNumberOfDayForPlan());

            // here take where biggest memorized start
            toGetStart = sorahRepository.findSorahBySorahAndAndAyah(requestQTech.getNumberOfSorahBiggestMemorize(),requestQTech.getNumberOfAyahBiggestMemorize());
            // here to take biggest memorized for rid
            sorahList = sorahRepository.findSorahByRidGreaterThanEqualOrderByRid(toGetStart.getRid());


            if(numberOfPageBiggestMemorized.compareTo(BigDecimal.ZERO) != 0)
                biggestMemorized = getBiggestMemorizedRid(memorized , sorahList, numberOfPageBiggestMemorized);

        }else {

            sorahList = sorahRepository.findSorahByLidGreaterThanEqualOrderByLid(toGetStart.getLid());
            memorized = getPlan(originalLength,requestQTech.getNumberOfDayForPlan(),requestQTech.getPercentage()
                    ,sorahList,requestQTech.getNumberOfDayPeerWeek(),requestQTech.getNumberOfDaysSmallMemorized());

            System.out.println("Memorized before ::"+memorized.size());

            if(requestQTech.getNumberOfRememorizedDrs() != 0)
                smallMemorized = getSmallMemorized(memorized, requestQTech.getNumberOfRememorizedDrs(),requestQTech.getNumberOfDayForPlan());

            // here take where biggest memorized start
            toGetStart = sorahRepository.findSorahBySorahAndAndAyah(requestQTech.getNumberOfSorahBiggestMemorize(),requestQTech.getNumberOfAyahBiggestMemorize());
            // here to take biggest memorized for rid
            sorahList = sorahRepository.findSorahByLidGreaterThanEqualOrderByLid(toGetStart.getLid());

            if(numberOfPageBiggestMemorized.compareTo(BigDecimal.ZERO) != 0)
                biggestMemorized = getBiggestMemorizedLid(memorized , sorahList, numberOfPageBiggestMemorized);

        }

        List<LocalDate> gregorianDateList = getGregorianDateList(memorized.size(), requestQTech.getNumberOfDayPeerWeek()
                ,requestQTech.getLocalDate());



        List<String> hijrahDateList = getHijrahDate(gregorianDateList);

        responseQTech.setGregorianDateList(gregorianDateList);
        responseQTech.setHijrahDateList(hijrahDateList);
        responseQTech.setMemorized(memorized);
        responseQTech.setSmallMemorized(smallMemorized);
        responseQTech.setBiggestMemorized(biggestMemorized);

        return responseQTech;
    }

    public List<Drs> getPlan(BigDecimal originalLength, int dayPlan, BigDecimal percentage, List<Sorah> sorahList
            , int numberOfDayPeerWeek, int numberOfDayForSmallMemorized){

        if(originalLength.compareTo(BigDecimal.ZERO)==0)
            return null;


        List<Drs> drsList = new ArrayList<>();
        BigDecimal currentLength = new BigDecimal(0);

        Sorah start = sorahList.get(0);
        Sorah end = null;

        int j = 0;
        int k = 1;
        int daysForOnlyMemorized = (numberOfDayPeerWeek - numberOfDayForSmallMemorized) == numberOfDayPeerWeek ? 0:(numberOfDayPeerWeek - numberOfDayForSmallMemorized);



        for (int i = 0 ; j < dayPlan && i+2 <= sorahList.size() ; i++ ){

            if(start.getAyah() == 0) {
                start = sorahList.get(1+i);
                continue;
            }

            // this for add null for days which is only small memorized
            if( k >  daysForOnlyMemorized && daysForOnlyMemorized != 0){

                if(k == numberOfDayPeerWeek){

                    // rest k to 1, like a new week will start
                    k = 1;

                } else {
                    k++;
                }

                j++;
                drsList.add(null);
                continue;
            }

            // here to not take length for beroas or bsmlah length
            if (sorahList.get(i).getAyah() != 0)
                currentLength = currentLength.add(sorahList.get(i).getLength());

            /* here to check if it rich to limit that pointed
                then will go to SuggestionStop with correct stop
                then will rest the currentLength = 0  + move start sorah to next .
            * */
            if (currentLength.compareTo(originalLength) >= 0){
                SuggestionStop correctStop = findCorrectStop(originalLength, currentLength
                        , percentage,i,sorahList,start);
                // for next start i = stop point (correctStop.getIndexStop()) + 1
                i = correctStop.getIndexStop()+1;
                // to set Drs
                end = correctStop.getCurrentStop();
                drsList.add(new Drs(start, end));
                // here we added null in case sorah list size before rich to length target.
                if (i < sorahList.size())
                    start = sorahList.get(i);
                else
                    break;

                currentLength = new BigDecimal(0);
                j++;
                k++;
            }
        }
        // here we will cover if list end before we sit the end
//        if(end == null){
//            drsList.add(new Drs(start,sorahList.get(sorahList.size()-1)));
//        }

        if(j != dayPlan){
            drsList.add(new Drs(start,sorahList.get(sorahList.size()-1)));
        }

        return drsList;
    }

    public List<Drs> getSmallMemorized(List<Drs> memorized, int numberOfRememorizedDrs, int dayPlan ){

        if(numberOfRememorizedDrs == 0)
            return null;


        List<Drs> smallMemorized = new ArrayList<>();


        smallMemorized.add(null);

        int startIndex = 0;
        int endIndex = 0;
        int open = 0;


        while ( open != numberOfRememorizedDrs && endIndex < memorized.size() ) {


            if (memorized.get(endIndex) == null) {
                endIndex++;
                // add perv small memorized
                smallMemorized.add(smallMemorized.get(smallMemorized.size()-1));
                continue;
            }

            smallMemorized.add(new Drs(memorized.get(startIndex).getFrom(),memorized.get(endIndex).getTo()));
            endIndex++;
            open++;
        }

        startIndex++;

        for (int k = open; k < memorized.size()-1 &&  endIndex < memorized.size()-1 && startIndex < memorized.size()-1  ;) {

            // here to skip null
            if (memorized.get(startIndex) == null) {
                startIndex++;
                continue;
            }
            // here to skip null
            if (memorized.get(endIndex) == null){
                endIndex++;
                k++;
                smallMemorized.add(smallMemorized.get(smallMemorized.size()-1));
                continue;
            }

            smallMemorized.add(new Drs(memorized.get(startIndex).getFrom(),memorized.get(endIndex).getTo()));
            startIndex++;
            endIndex++;
            k++;

        }

        return smallMemorized;
    }

    public List<Drs> getBiggestMemorizedRid(List<Drs> memorized,List<Sorah> sorahList , BigDecimal target ){

        List<Drs> biggestMemorized = new ArrayList<>();

        List<Drs> sorahsRid = getPlan(target, memorized.size(), new BigDecimal(0.3),sorahList,0,0);

        int moduloSize = 0;
        int looperIndex = 0;


        for (int i = 0; i < sorahsRid.size(); i++){


            if(memorized.get(i) != null && sorahsRid.get(moduloSize).getTo().getRid() < memorized.get(i).getFrom().getRid()){
                moduloSize++;
            }

            if (moduloSize == 0){
                biggestMemorized.add(null);
                continue;
            }

            if(looperIndex == moduloSize)
                looperIndex = 0;

            biggestMemorized.add(sorahsRid.get(looperIndex));
            looperIndex++;


        }

        return biggestMemorized;
    }

    public List<Drs> getBiggestMemorizedLid(List<Drs> memorized,List<Sorah> sorahList , BigDecimal target){

        List<Drs> biggestMemorized = new ArrayList<>();

        List<Drs> sorahsLid = getPlan(target, memorized.size(), new BigDecimal(0.3),sorahList,0,0);

        int moduloSize = 0;
        int looperIndex = 0;

        for (int i = 0; i < sorahsLid.size(); i++){

            if(memorized.get(i) != null && sorahsLid.get(moduloSize).getTo().getLid() < memorized.get(i).getFrom().getLid()){
                moduloSize++;
            }

            if (moduloSize == 0){
                biggestMemorized.add(null);
                continue;
            }

            if(looperIndex == moduloSize)
                looperIndex = 0;

            biggestMemorized.add(sorahsLid.get(looperIndex));
            looperIndex++;


        }
        return biggestMemorized;
    }

    private SuggestionStop findCorrectStop(BigDecimal originalLength, BigDecimal currentLength
            , BigDecimal percentage, int currentIndex,List<Sorah> sorahList,Sorah start){

        List<SuggestionStop> suggestionStopList = new ArrayList<>();

        SuggestionStop suggestionStopUp = stopUp(originalLength,currentLength,percentage,currentIndex,sorahList);
        //System.out.println("suggestionStopUp : "+suggestionStopUp);
        SuggestionStop suggestionStopCurrent = stopCurrent(originalLength,currentLength,percentage,currentIndex,sorahList);
        //System.out.println("suggestionStopCurrent : "+suggestionStopCurrent);
        SuggestionStop suggestionStopDown = stopDown(originalLength,currentLength,percentage,currentIndex,sorahList);
        //System.out.println("suggestionStopDown : "+suggestionStopDown);

        suggestionStopList.add(suggestionStopUp);
        suggestionStopList.add(suggestionStopCurrent);

        // special case to avoid wrong suggestionStopDown when start ayah < than suggestionStopDown ayah
        if(suggestionStopDown.getCurrentStop().getAyah() != null ) {
            if(start.getAyah() < suggestionStopDown.getCurrentStop().getAyah())
                suggestionStopList.add(suggestionStopDown);
        }

        return chooseCorrect(suggestionStopList);
    }

    /*
    * هذي الفنكشن تستقبل التالي الطول الاساسي originalLength و الطول الحالي currentLength
    * ونسبة التغيير percentage و الاندكس الاساسي originalLength و الاراي للسور sorahList
    تقوم هذي الفنكشن بارجاع القيمة المقترحة للوقف بالزيادة
    *
     */
    private SuggestionStop stopUp(BigDecimal originalLength,
                                 BigDecimal currentLength, BigDecimal percentage,
                                 int originalIndex, List<Sorah> sorahList){

        int index = originalIndex;

        if(index+1 >= sorahList.size())
            return new SuggestionStop();

        SuggestionStop suggestionStop = new SuggestionStop();
        // calculate the percentage rate here *0% => 0.*0 + 1.0 will be 1.*0x originalLength, so we will have the targetUp now
        BigDecimal targetUp = percentage.add(new BigDecimal(1)).multiply(originalLength).setScale(2, RoundingMode.FLOOR);
        BigDecimal currentLengthProcess = new BigDecimal(currentLength.toString());

        // index+1 to see the nextSorah is it safe?
        Sorah nextSorah = sorahList.get(1+index);

        // check the next is it safe or not ?
        while (isNextSafe(nextSorah,currentLengthProcess,targetUp) && index+1 < sorahList.size()){
            // if its yes safe that mean the next it's OK, so we can take it.
            index++;
            currentLengthProcess = currentLengthProcess.add(nextSorah.getLength());
            if(index+1 < sorahList.size())
                nextSorah = sorahList.get(index+1);
            else
                break;
        }

        // here calculate the different between the originalLength and currentLength after process
        BigDecimal subtract = originalLength.abs().subtract(currentLengthProcess.abs());

        // here to mark if its next Ayah = 0 that's mean special stop or Size of.
        if(nextSorah.getAyah() == 0 || index+1 == sorahList.size() || ((nextSorah.getSorah() == 2 && nextSorah.getAyah() == 1)) )
            suggestionStop.setAyahValueZero(true);

        suggestionStop.setCurrentStop(sorahList.get(index));
        suggestionStop.setIndexStop(index);
        suggestionStop.setClosetNumberToTarget(subtract.abs());

        return suggestionStop;
    }

    /* الفنكشن تستقبل الاية التالية nextSorah  و مجموع الايات الفعلي currentLengthProcess والجزء الاعلى المحدد target up
     تقوم الفنكشن بتأكد من حالة الاية التالية هل هي نقطة آمنه للوقوف ام لا
    * */
    private boolean isNextSafe(Sorah nextSorah, BigDecimal currentLengthProcess, BigDecimal targetUp){

        BigDecimal testValue = currentLengthProcess;
        testValue = testValue.add(nextSorah.getLength());

        if(nextSorah.getSorah() == 2 && nextSorah.getAyah() == 1)
            return false;

        return nextSorah.getAyah() != 0 && testValue.compareTo(targetUp) < 0;
    }

    /*
    * هذي الفنكشن تستقبل التالي الطول الاساسي originalLength و الطول الحالي currentLength
    * ونسبة التغيير percentage و الاندكس الاساسي originalLength و الاراي للسور sorahList

    تقوم هذي الفنكشن بارجاع القيمة المقترحة للوقف الحالي
    *
     */
    private SuggestionStop stopCurrent(BigDecimal originalLength,
                                      BigDecimal currentLength, BigDecimal percentage,
                                      int originalIndex, List<Sorah> sorahList){

        BigDecimal abs = currentLength.subtract(originalLength).abs();

        // it will check if originalIndex arrive to last size for sorah, and it will remark as Ayah zero true
        if(originalIndex+1 >= sorahList.size()) {
            return new SuggestionStop(sorahList.get(originalIndex), originalIndex, true, abs);
        }
        SuggestionStop suggestionStop = new SuggestionStop(sorahList.get(originalIndex),originalIndex);
        suggestionStop.setClosetNumberToTarget(abs);

        return suggestionStop;
    }


    /*
    * هذي الفنكشن تستقبل التالي الطول الاساسي originalLength و الطول الحالي currentLength
    * ونسبة التغيير percentage و الاندكس الاساسي originalIndex و الاراي للسور sorahList

    تقوم هذي الفنكشن بارجاع القيمة المقترحة للوقف بالنقصان
    *
     */
    private SuggestionStop stopDown(BigDecimal originalLength,
                                   BigDecimal currentLength, BigDecimal percentage,
                                   int originalIndex, List<Sorah> sorahList){

        int index = originalIndex;

        if(index-2 <= 0)
            return new SuggestionStop();

        SuggestionStop suggestionStop = new SuggestionStop();
        // calculate the percentage rate here *0% => | 1.0 - 0.*0 | will be 0.*0x originalLength, so we will have the targetDown now
        BigDecimal targetDown = percentage.subtract(new BigDecimal(1)).multiply(originalLength).abs().setScale(2, RoundingMode.HALF_EVEN);
        //
        BigDecimal currentLengthProcess = new BigDecimal(currentLength.toString());

        Sorah prevSorah = sorahList.get(index-1);

        while (isPreviousSafe(prevSorah,currentLengthProcess,targetDown) && index-2 > 0){
            index--;
            currentLengthProcess = currentLengthProcess.subtract(prevSorah.getLength());
            prevSorah = sorahList.get(index-1);
        }

        // here calculate the different between the originalLength and currentLength after process
        BigDecimal abs = originalLength.abs().subtract(currentLengthProcess.abs()).abs();

        // here to mark if its previous Ayah = 0 that's mean special stop + it should the index > 0.
        // because Ayah 0 include البرواز so we should take the case and subtract from 3
        if(prevSorah.getAyah() == 0 && index-3 > 0 ){
            index = index-3;
            suggestionStop.setAyahValueZero(true);
        }

        if (prevSorah.getSorah() == 1 && prevSorah.getAyah() == 6){
            suggestionStop.setAyahValueZero(true);
        }


        suggestionStop.setCurrentStop(sorahList.get(index));
        suggestionStop.setIndexStop(index);
        suggestionStop.setClosetNumberToTarget(abs);

        return suggestionStop;
    }

    /* الفنكشن تستقبل الاية السابقة previousSorah و مجموع الايات الفعلي currentLengthProcess والجزء الاقل المحدد target down
     تقوم الفنكشن بتأكد من حالة الاية السابقة هل هي نقطة آمنه للوقوف ام لا
     في حال كانت الاية السابقة لا تساوي 0 و مجموع الايات لايزال اكبر من targetDown
    * */
    private boolean isPreviousSafe(Sorah previousSorah, BigDecimal currentLengthProcess, BigDecimal targetDown){

        BigDecimal testValue = currentLengthProcess;
        testValue = testValue.subtract(previousSorah.getLength());

        if (previousSorah.getSorah() == 1 && previousSorah.getAyah() == 6)
            return false;

        return previousSorah.getAyah() != 0 && testValue.compareTo(targetDown) >= 0;
    }
    private SuggestionStop chooseCorrect(List<SuggestionStop> suggestionStopList){

        List<SuggestionStop> suggestionStopListHasAyahZero = new ArrayList<>();

        for (SuggestionStop s: suggestionStopList) {

            if (s.isAyahValueZero())
                suggestionStopListHasAyahZero.add(s);
        }

        if(suggestionStopListHasAyahZero.size()>0){
            suggestionStopListHasAyahZero.sort(Comparator.comparing(SuggestionStop::getClosetNumberToTarget));
            return suggestionStopListHasAyahZero.get(0);
        }

        suggestionStopList.sort(Comparator.comparing(SuggestionStop::getClosetNumberToTarget));
        return suggestionStopList.get(0);
    }

    // I honestly didn't know how to work but its work.
    public  List<LocalDate> getGregorianDateList(int numberOfDaysForPlan, int numberOfDaysPeerWeek, LocalDate gregorianDate){

        int remainForPlus =  7 - numberOfDaysPeerWeek;

        List<LocalDate> gregorianDateList = new ArrayList<>();

        int k = 0 ;

        int day = 0;

        for (int i = 0; i < numberOfDaysForPlan; i++) {

            k++;

            if (k % (numberOfDaysPeerWeek+1) == 0){
                k = 1;
                day += remainForPlus;
            }

            gregorianDateList.add(gregorianDate.plusDays(day++));
        }

        return gregorianDateList;
    }
    public List<String> getHijrahDate(List<LocalDate> gregorianDateList){

        List<String> hijrahDateList = new ArrayList<>();

        for (LocalDate date : gregorianDateList) {
            hijrahDateList.add(
              HijrahChronology.INSTANCE.date(date).format(
                      DateTimeFormatter.ofPattern("yyyy-MM-dd")
              )
            );
        }
        return hijrahDateList;
    }

    public String printPlan(List<RequestPrint> printList,String printType) throws JRException {

        JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(printList);


        Map<String,Object> parameters = new HashMap<String,Object>();

        parameters.put("CollectionBeanParam",jrBeanCollectionDataSource);


        JasperReport report = JasperCompileManager.compileReport(System.getProperty("user.dir")+DEST);

        JasperPrint print = JasperFillManager.fillReport(report,parameters,jrBeanCollectionDataSource);

        Random random = new Random();
        String fileName = printType+random.nextInt(100000);
        switch (printType) {

            case "pdf":
                return printPDF(print,fileName);
            case "docx":
                return printDocx(print,fileName);
            case "xlsx":
                return printXlsx(print,fileName);
            default:
                throw new RuntimeException("Unknown argument pass");
        }

    }
    private String printPDF(JasperPrint print, String randomName) throws JRException {


        System.out.println(System.getProperty("user.dir")+outputPath+"/"+randomName+".pdf");

        JasperExportManager.exportReportToPdfFile(print,outputPath+"/"+randomName+".pdf");

        return  changeToBase64(outputPath+"/"+randomName+".pdf");
    }

    private String printDocx(JasperPrint print, String randomName) throws JRException {

        JRDocxExporter docxExporter = new JRDocxExporter();
        File export = new File(outputPath+"/"+randomName+".docx");

        docxExporter.setExporterInput(new SimpleExporterInput(print));
        docxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(export));
        docxExporter.exportReport();

        return  changeToBase64(outputPath+"/"+randomName+".docx");
    }

    private String printXlsx(JasperPrint print, String randomName) throws JRException {

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(true);
        configuration.setDetectCellType(true);
        configuration.setCollapseRowSpan(false);
        configuration.setWhitePageBackground(false);

        JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        File exportXlsx = new File(outputPath+"/"+randomName+".xlsx");

        xlsxExporter.setConfiguration(configuration);

        xlsxExporter.setExporterInput(new SimpleExporterInput(print));
        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exportXlsx));
        xlsxExporter.exportReport();

        return  changeToBase64(outputPath+"/"+randomName+".xlsx");
    }

    private String changeToBase64(String filePath){

        String base64File = "";

        File file = new File(filePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a file from file system
            byte fileData[] = new byte[(int) file.length()];
            imageInFile.read(fileData);
            base64File = Base64.getEncoder().encodeToString(fileData);
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the file " + ioe);
        }

//        System.out.println("File name : "+file.getName());
        System.out.println("File deleted ?? "+file.delete());

        return base64File;
    }



}
