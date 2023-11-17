package com.valhalla.test.excelread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valhalla.test.excelread.service.ReadExcelService;

@SpringBootApplication
public class ExcelreadApplication implements CommandLineRunner {

	// @Autowired
	// ReadExcelService readExcelService;
	
	@Autowired
	ReadExcelService testDhatimReadService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExcelreadApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String testeasy = "/home/brian/Downloads/Electric_Vehicle_Population_Data2.xlsx";
		String testhard = "/home/brian/Downloads/Electric_Vehicle_Population_Data.xlsx";
		String testlol  = "/home/brian/Downloads/Electric_Vehicle_Population_Data3.xlsx";
//		this.readExcelService.readExcel( testhard );
		
		this.testDhatimReadService.readExcel( testlol );
	}

}
