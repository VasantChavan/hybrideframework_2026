package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public static FileInputStream fins;
	public static XSSFWorkbook workbook;

	public ExcelDataProvider(String filename) {
		String filePath = System.getProperty("user.dir") + "\\testdata\\";
		try {
			File fs = new File(filePath + filename + ".xlsx");
			fins = new FileInputStream(fs);
			workbook = new XSSFWorkbook(fins);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static int rowCount(String sheetname) {
		return workbook.getSheet(sheetname).getLastRowNum();
	}

	public static int colCount(String sheetname, int row) {
		return workbook.getSheet(sheetname).getRow(row).getLastCellNum();
	}

	public static String getCellData(String sheetname, int row, int column) {
		return workbook.getSheet(sheetname).getRow(row).getCell(column).toString();
	}

	public static String getStringCellData(String sheetname, int row, int column) {
		return workbook.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}

	public static double getNumericCellData(String sheetname, int row, int column) {
		return workbook.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
	}

	public static String[][] getTestData(String sheetname) {
		int row = rowCount(sheetname);
		int col = colCount(sheetname, 0);

		String[][] data = new String[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				data[r][c] = workbook.getSheet(sheetname).getRow(r + 1).getCell(c).toString();
			}
		}

		return data;
	}
	
	public static void setTestResut(String sheetname,String filename, int row, int cellIndex,String value)
	{
		String filePath = System.getProperty("user.dir") + "\\testdata\\";
		File fs = new File(filePath + filename + ".xlsx");
		try {
			workbook.getSheet(sheetname).getRow(row).createCell(cellIndex).setCellValue(value);
			FileOutputStream fout= new FileOutputStream(fs);
			workbook.write(fout);
			workbook.close();
			fout.close();
			
			System.out.println("Write operation is done!!!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

//	public static void main(String[] args) {
//		
//		new ExcelDataProvider("orangehrm");
//		System.out.println(rowCount("login"));
//		System.out.println(colCount("login",0));
//		
//		String r0c0=getCellData("login", 0, 0);
//		String r0c1=getCellData("login", 0, 1);
//		
//		System.out.println(r0c0+" "+r0c1);
//		
//		
//		String r1c0=getCellData("login", 1, 0);
//		String r1c1=getCellData("login", 1, 1);
//		
//		System.out.println(r1c0+" "+r1c1);
//		
//		
//	}

}
