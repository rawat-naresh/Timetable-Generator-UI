package com.timetable.core.classes;

/**
 * @author Naresh
 *
 */
public class TimeSlot {
	private int timeSlotId;
	private String day;
	private String time;
	
	public TimeSlot() {
		
	}
	
	public TimeSlot(String day,String time) {
		this.day =day;
		this.time = time;
	}

	public int getTimeSlotId() {
		return timeSlotId;
	}

	

	public String getDay() {
		return day;
	}

	

	public String getTime() {
		return time;
	}


	
	
}
