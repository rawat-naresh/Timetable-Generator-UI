package com.timetable.core.classes;

import java.util.ArrayList;

public class Course {

	ArrayList<Year> years;
	String courseName;
	int courseId;
	boolean hasSubGroup = false;
	
	public Course(String courseName, int courseId){
		years = new ArrayList<>();
		this.courseName = courseName;
		this.courseId = courseId;
	}

	public ArrayList<Year> getYears() {
		return years;
	}
	
	public void addYear(Year year) {
		years.add(year);
	}
	public Year getYear(int i) {
		return years.get(i);
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCourseId() {
		return courseId;
	}
	
	public boolean isHasSubGroup() {
		return hasSubGroup;
	}
	public void setHasSubGroup(boolean b) {
		this.hasSubGroup = b;
	}

	@Override
	public String toString() {
		return courseName;
	}
	
	
}
