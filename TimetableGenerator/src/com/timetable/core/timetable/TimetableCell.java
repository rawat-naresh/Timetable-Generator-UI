package com.timetable.core.timetable;

import com.timetable.core.constraint.Constraint;

/**
 * @author Naresh
 *
 */
public class TimetableCell {
	private int value=-1;
	private boolean isActive = true;
	private boolean isEmpty = true;
	private String type = Constraint.UNOCCUPIED_TYPE;
	public TimetableCell() {
		
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
