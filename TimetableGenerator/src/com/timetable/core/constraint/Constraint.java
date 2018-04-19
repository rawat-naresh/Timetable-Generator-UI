/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.constraint;

import java.io.Serializable;
import java.util.ArrayList;

import com.timetable.core.classes.Activity;


/**
 * @author Naresh
 *
 */
public class Constraint implements Serializable {
 
	
	private static final long serialVersionUID = 1L;

	private static Constraint instance = null;
	
    public static final int TIME_CONSTRAINT = 50;
    public static final int SPACE_CONSTRAINT = 50;
    public static int totalWeight=0;
    public static final String LAB_TYPE = "(L)";
    public static final String LECTURE_TYPE = "(T)";
    public static final String BREAK_TYPE = "(B)";
    public static final int UNOCCUPIED_TYPE = 1999;
    
    private int noOfHoursPerDay;
	private int noOfDaysPerWeek;
    private String collegeName;
    
    public ArrayList<String> days = new ArrayList<>(7);
    public ArrayList<String> lectureTimes = new ArrayList<>(6);
    
    //{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"}
    //{"09:00-09:55","09:55-10:50","10:50-11:45","11:45-12:40","12:40-1:35",
    //"01:35-02:30","02:30-03:25","03:25-04:20"}
    
    
    private Constraint() {
		
	}
    
    public static Constraint getInstance() {
    	if(instance == null) {
    		instance = new Constraint();
    	}
    	
    	return instance;
    }
    
    public static void setInstance(Constraint constraint) {
    	instance = constraint;
    }
   
    
    public ArrayList<String> getDays() {
		return days;
	}
    
	public String getDay(int index) {
			
		return days.get(index);
	}

	public void setDays(ArrayList<String> days) {
		
		this.days = days;
	}

	public ArrayList<String> getLectureTimes() {
		return lectureTimes;
	}
	
	public String getLectureTime(int index) {
		return lectureTimes.get(index);
	}

	public void setLectureTimes(ArrayList<String> lectureTimes) {
		this.lectureTimes = lectureTimes;
	}

	public int getNoOfHoursPerDay() {
		return noOfHoursPerDay;
	}

	public void setNoOfHoursPerDay(int noOfHoursPerDay) {
		this.noOfHoursPerDay = noOfHoursPerDay;
	}

	public int getNoOfDaysPerWeek() {
		return noOfDaysPerWeek;
	}

	public void setNoOfDaysPerWeek(int noOfDaysPerWeek) {
		this.noOfDaysPerWeek = noOfDaysPerWeek;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
    
    public void computeActivityWeightage(ArrayList<Activity> a) {
    	
    	a.forEach((v)-> {
    		
    		v.getTeachers().forEach((t)->{
    			
    			if(t.isHasConstraint()) {
    				totalWeight+=TIME_CONSTRAINT;
    			}
    			
    		});
    		
    	
    		if(v.getStudent().isHasConstraint()) {
    				totalWeight+=TIME_CONSTRAINT;
    			}
    			
    		
    		v.getSubjects().forEach((sub)->{
    			if(sub.isHasConstraint()) {
    				totalWeight+=SPACE_CONSTRAINT;
    			}
    			
    		});
    		
    		v.setWeightage(totalWeight);
    		totalWeight = 0;
    	});
    	
    }
    
    
    
    
}
