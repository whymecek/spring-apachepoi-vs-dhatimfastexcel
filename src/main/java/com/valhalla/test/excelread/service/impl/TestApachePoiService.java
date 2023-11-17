package com.valhalla.test.excelread.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.valhalla.test.excelread.service.ReadExcelService;

@Service
public class TestApachePoiService implements ReadExcelService {

	@Override
	public void readExcel(String path) throws Exception {
		long start = System.currentTimeMillis();
		FileInputStream file = new FileInputStream(new File( path ));
		Workbook workbook = new XSSFWorkbook(file);
		
		Sheet sheet = workbook.getSheetAt(0);
		System.out.println("Execution Read "+(System.currentTimeMillis() - start )+" ms ");

		Map<Integer, List<String>> data = new HashMap<>();
		int i = 0;
		for (Row row : sheet) {
		    data.put(i, new ArrayList<String>());
		    for (Cell cell : row) {
		    	if( cell.getCellType().equals(CellType.STRING) ) {
			    	data.get(i).add( cell.getStringCellValue() );
		    	} else if( cell.getCellType().equals(CellType.NUMERIC) ) {
		    		data.get(i).add( String.valueOf( cell.getNumericCellValue() ) );
		    	} else {
			    	data.get(i).add( cell.getStringCellValue() );
		    	}
		    }
		    i++;
		}
		
		file.close();
		workbook.close();
		System.out.println("Count data "+data.size());
		System.out.println("Execution End  "+(System.currentTimeMillis() - start )+" ms ");

	}

}
