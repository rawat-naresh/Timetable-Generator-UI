package com.timetable.core.timetable;

import java.util.HashMap;

/**
 * @author Naresh
 *
 */
public class TimetableRow {
	private HashMap<Integer,TimetableCell> cells; 
	
	public TimetableRow() {
		cells = new HashMap<>();
	}
	
	public TimetableCell getCell(int id) {
		return cells.get(id);	
	}
	
	public HashMap<Integer,TimetableCell> getCells(){
		return cells;
	}
	public void addTimetableCell(int k,TimetableCell cell) {
		cells.put(k,cell);
	}
	
}
