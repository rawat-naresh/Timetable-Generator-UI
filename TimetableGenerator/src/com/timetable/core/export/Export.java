package com.timetable.core.export;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.timetable.core.classes.Activity;
import com.timetable.core.classes.Subject;
import com.timetable.core.classes.Teacher;
import com.timetable.core.constraint.Constraint;
import com.timetable.core.main.Data;
import com.timetable.core.timetable.TimetableCell;
import com.timetable.core.timetable.TimetableRow;
import com.timetable.core.timetable.TimetableSheet;


/**
 * @author Naresh
 *
 */
public class Export {
	
	private Workbook workbook;
	private Data data;
	private FileOutputStream output;
	private CellStyle style;
	private Font font;
	private Constraint constraint;

	public Export(String fileName)  {
		constraint = Constraint.getInstance();
		data = Data.getInstance();
		createExcelFile(fileName);
	}
	
	private void createExcelFile(String fileName)  {
		
		workbook = new HSSFWorkbook();
		style = workbook.createCellStyle();
		font = workbook.createFont();
		//font.setBold(true);
		font.setFontName("Calibri");
		font.setFontHeightInPoints((short)9);
		//style.setShrinkToFit(true);
		style.setWrapText(true);
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setFont(font);
		//row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));

	    //adjust column width to fit the content
	    
		try {
			output = new FileOutputStream(fileName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}	
	
	public void createExcelSheets(HashMap<Integer,TimetableSheet> timetableSheets) {
		timetableSheets.forEach((K,timetableSheet)->{
			
			Sheet sheet = workbook.createSheet(timetableSheet.getSheetName());
			//sheet.autoSizeColumn(k);
			createTimesRow(sheet);
			createActivityRows(sheet, timetableSheet.getRows());
			
		});
		
	}
	
	private void createTimesRow(Sheet sheet) {
		Row timesRow = sheet.createRow(0);
		timesRow.setHeightInPoints(30);
		
		Cell c = timesRow.createCell(0);
		c.setCellStyle(style);
		c.setCellValue("DAYS/TIMES");
		for(int i=0;i<constraint.getNoOfHoursPerDay();i++) {
			Cell timeCell = timesRow.createCell(i+1);
			timeCell.setCellValue(constraint.getLectureTime(i));//modified here later
			timeCell.setCellStyle(style);
		}
	}
	
	private void createActivityRows(Sheet sheet, HashMap<Integer,TimetableRow> rows) {
		//@param key should start from 1
		rows.forEach((key,row)->{
			Row activityRow = sheet.createRow(key);
			activityRow.setHeightInPoints(50);
			createActivityCells(sheet, activityRow, row.getCells(), key);
		});
	}
	
	private void createActivityCells(Sheet sheet, Row activityRow,HashMap<Integer,TimetableCell> cells,int k) {
		Cell dayCell = activityRow.createCell(0);
		sheet.autoSizeColumn(0);
		dayCell.setCellValue(constraint.getDay(k-1));//modified here later
		dayCell.setCellStyle(style);
		
		for(int key : cells.keySet()) {
			TimetableCell cell = cells.get(key);
			int cellValue = cell.getValue();
			String cellType = cell.getType();
			Cell c = activityRow.createCell(key);
			
			c.setCellStyle(style);
			sheet.autoSizeColumn(key);
			
				
			if(cellType == Constraint.LECTURE_TYPE || cellType == Constraint.LAB_TYPE) {
				Activity activity = data.getSortedActivities().get(cellValue);
				sheet.addMergedRegion(new CellRangeAddress(k, k, key, key-1+activity.getDuration()));
				String teachers="";
				String subjects = "";
				String subgroup = "";
				for(Teacher t: activity.getTeachers())
					teachers+= "  "+t.getName();
				
				for(Subject s: activity.getSubjects())
					subjects+= "  "+s.getName();
				
				for(String sub:activity.getStudent().getSubGroups())
					subgroup+="  "+sub;
				
				key = key + activity.getDuration();
				
			
				c.setCellValue(teachers+"\n"+subjects+"\n"+subgroup);
			}
				
			
			
			else if(cellType == Constraint.BREAK_TYPE) 
				c.setCellValue("BREAK");
			
			
		}
		
		
	}
	

	
	public void writeToExcelFile() {
		try {
			workbook.write(output);
			output.close();
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}	

}
