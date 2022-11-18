package com.almaherplan.www.QTech;


import com.almaherplan.www.QTech.repository.SorahRepository;
import com.almaherplan.www.QTech.service.QTechService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class QTechApplication {

	public static void main(String[] args) {
		SpringApplication.run(QTechApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(SorahRepository sorahRepository, QTechService qTechService){

		return args -> {

			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("***************************************************");

			//List<Sorah> sorahOrderByRid = sorahRepository.findAllByOrderByRid();
			//List<Sorah> sorahOrderByLid = sorahRepository.findAllByOrderByLid();
			//List<Drs> sorahList = qTechService.getPlan(new BigDecimal(15),30,new BigDecimal(0.3),sorahOrderByLid,5,1);

			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("***************************************************");
			//sorahList.forEach(System.out::println);
			System.out.println("###################################################");
			//qTechService.getSmallMemorized(sorahList,3).forEach(System.out::println);
			System.out.println("###################################################");

			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("***************************************************");

			//getSmallMemorized(sorahList,3).forEach( s -> System.out.println(" - "+s));


			//qTechService.getPlan(new BigDecimal(15.00),30,new BigDecimal(0.30),sorahList).forEach(System.out::println);

			System.out.println("***************************************************");
			System.out.println("***************************************************");
			System.out.println("***************************************************");
		};

	}






}
