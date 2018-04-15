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

import com.timetable.core.classes.LabActivity;
import com.timetable.core.classes.LectureActivity;
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

	public Export(String fileName)  {
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
		for(int i=0;i<Constraint.noOfHoursPerDay;i++) {
			Cell timeCell = timesRow.createCell(i+1);
			timeCell.setCellValue(Constraint.lectureTimes[i]);
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
		dayCell.setCellValue(Constraint.days[k-1]);
		dayCell.setCellStyle(style);
		int flag=0;
		
		for(int key : cells.keySet()) {
			TimetableCell cell = cells.get(key);
			int cellValue = cell.getValue();
			//System.out.println(cellValue);
			String outputString;
			Cell c = activityRow.createCell(key);
			
			c.setCellStyle(style);
			sheet.autoSizeColumn(key);
			if(cell.getType() == Constraint.BREAK_TYPE) {
				c.setCellValue("BREAK");
			}
			
			else if(data.getSortedLectureActivities().containsKey(cellValue)) {
				LectureActivity la = data.getSortedLectureActivities().get(cellValue);
				outputString = la.getSubject().getName()+"\n"+la.getTeacher().getName();
				c.setCellValue(outputString);
			}
			else if(data.getSortedLabActivities().containsKey(cellValue)) {
				flag++;
				if(flag %2 != 0) {
					LabActivity la = data.getSortedLabActivities().get(cellValue);
					//@param key always starts from 1
					
					sheet.addMergedRegion(new CellRangeAddress(k, k, key, key-1+la.getDuration()));
					String t1,t2,s1,s2,sg1,sg2;
					s1 = la.getSubjects().get(0).getName();
					s2 = la.getSubjects().get(1).getName();
					t1 = la.getTeachers().get(0).getName();
					t2 = la.getTeachers().get(1).getName();
					sg1 = la.getStudent().getSubGroups().get(0);
					sg2 = la.getStudent().getSubGroups().get(1);
					
					c.setCellValue(s1+", "+t1+", "+sg1+"\n"+s2+", "+t2+", "+sg2);
					
				}
				//control enters here when it is labActivity.
				//for lab activity we need to merge number of cells according to the given duration
			
				
				
			}
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
