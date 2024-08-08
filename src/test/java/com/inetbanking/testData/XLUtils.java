package com.inetbanking.testData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public FileInputStream file;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public int rowTotal;
	public int colTotal;
	public XSSFRow currentRow;
	public XSSFCell currentCol;
	public String cellValue;
	public XLUtils(String path, String sheetName) throws IOException {
		 file = new FileInputStream(path);
		 workbook = new XSSFWorkbook(file);
		 sheet = workbook.getSheet(sheetName);
	}
	
	public int getRowCount() {
	rowTotal = sheet.getLastRowNum();
	return rowTotal;
	}
	
	public int getCellCount() {
		colTotal = sheet.getRow(0).getLastCellNum();
		return colTotal;
		}
	public String getCellValue(int rowIndex, int colIndex) {
		for(int r = 0 ; r<=rowTotal ; r++) {
			if(r==rowIndex) {
			currentRow = sheet.getRow(r);
			for(int c = 0 ; c<colTotal ; c++) {
				if(c==colIndex) {
				currentCol = currentRow.getCell(c);
				 cellValue = currentCol.toString();
				
			}
			}
		}
	}
		return cellValue;
		}
	
	/*public static void main(String[] args) throws IOException {
		XLUtils obj = new XLUtils(System.getProperty("user.dir")+"\\src\\test\\java\\com\\inetbanking\\testData\\LoginTestData.xlsx" , "Sheet1");
		System.out.println(obj.getCellCount());
		System.out.println(obj.getRowCount());
		obj.getCellValue(0, 0);
	}*/

}

	

