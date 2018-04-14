package com.timetable.timetable;

import java.util.HashMap;

/**
 * @author Naresh
 *
 */
public class TimetableSheet {
	
	private String sheetName;
	private HashMap<Integer,TimetableRow> rows;
	
	public TimetableSheet(String sheetName) {
		this.sheetName = sheetName;
		rows = new HashMap<>();
	}
	
	public HashMap<Integer,TimetableRow> getRows() {
		return rows;
	}
	
	public TimetableRow getRow(int rowId) {
		
		return rows.get(rowId);
	}
	
	public String getSheetName() {
		return sheetName;
	}
	public void addTimetableRow(int k,TimetableRow row) {
		this.rows.put(k, row);
	}

	/*public int getSheetId() {
		return sheetId;
	}

	public void setSheetId(int sheetId) {
		this.sheetId = sheetId;
	}*/
}
