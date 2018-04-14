package com.timetable.timetable;

import com.timetable.constraint.Constraint;

/**
 * @author Naresh
 *
 */
public class TimetableCell {
	private int value=-1;
	private boolean isActive = true;
	private boolean isEmpty = true;
	private int type = Constraint.UNOCCUPIED_TYPE;
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
	
}
