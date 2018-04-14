package com.timetable.main;

import java.util.HashMap;

import com.timetable.timetable.TimetableSheet;

/**
 * @author Naresh
 *
 */
public class Distribution {
	private static Distribution distribution = null;

	private HashMap<Integer,TimetableSheet> timetableSheets;
	
	private Distribution() {
		timetableSheets = new HashMap<>();
	}
	
	public static Distribution getInstance() {
		
		if(distribution == null)
			distribution = new Distribution();
		
		return distribution;
	}

	public HashMap<Integer, TimetableSheet> getTimetableSheets() {
		return timetableSheets;
	}

	public void addTimetableSheet(int k,TimetableSheet timetableSheets) {
		
		this.timetableSheets.put(k, timetableSheets);
	}
	
	public TimetableSheet getTimetableSheet(int k) {
		
		return timetableSheets.get(k);
	}
	
	
	
}
