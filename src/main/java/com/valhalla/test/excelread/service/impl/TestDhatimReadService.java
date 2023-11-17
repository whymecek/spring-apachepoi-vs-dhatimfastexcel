package com.valhalla.test.excelread.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.springframework.stereotype.Service;

import com.valhalla.test.excelread.service.ReadExcelService;

@Service
public class TestDhatimReadService implements ReadExcelService {

	@Override
	public void readExcel(String path) throws Exception {
		long start = System.currentTimeMillis();
		FileInputStream file = new FileInputStream(new File( path ));
		ReadableWorkbook wb = new ReadableWorkbook(file);

		Sheet sheet = wb.getFirstSheet();
		System.out.println("Execution Read "+(System.currentTimeMillis() - start )+" ms ");

		Map<Integer, List<String>> data = new HashMap<>();
        try (Stream<Row> rows = sheet.openStream()) {
            rows.forEach(r -> {
                data.put(r.getRowNum(), new ArrayList<>());

                for (Cell cell : r) {
                	if( cell == null ) {
                		data.get(r.getRowNum()).add( "" );
                	} else {
                        data.get(r.getRowNum()).add(cell.getRawValue());              		
                	}
                }
            });
        }
        
        file.close();
		wb.close();
		System.out.println("Count data "+data.size());
		System.out.println("Execution End  "+(System.currentTimeMillis() - start )+" ms ");
	}

}
