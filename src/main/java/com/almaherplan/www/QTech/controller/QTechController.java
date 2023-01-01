
package com.almaherplan.www.QTech.controller;

import com.almaherplan.www.QTech.model.RequestPrint;
import com.almaherplan.www.QTech.model.RequestQTech;
import com.almaherplan.www.QTech.model.ResponseQTech;
import com.almaherplan.www.QTech.service.QTechService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@CrossOrigin("*")
@RestController
public class QTechController {

    @Autowired
    QTechService qTechService;


    @GetMapping()
    public String getTest(){
        return "Hi Muhannad";
    }


    @PostMapping("/getTechPlan")
    public ResponseQTech getQTechPlan(@RequestBody RequestQTech requestQTech){
            return qTechService.responseQTech(requestQTech);
    }

    @PostMapping ("/printData/{printType}")
    public String setQTechService(@RequestBody List<RequestPrint> list, @PathVariable String printType) throws JRException, IOException {

        System.out.printf("Data Pass .... "+printType);

        return qTechService.printPlan(list,printType);
    }





}

