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
	public static final String[] SUBGROUP_NAMES = {"T1","T2"};
	public static final int LAB_WEIGHTAGE = 100;
    public static final int TIME_CONSTRAINT = 50;
    public static final int SPACE_CONSTRAINT = 50;
    public static int totalWeight=0;
    public static final String LAB_TYPE = "(L)";
    public static final String LECTURE_TYPE = "(T)";
    public static final String BREAK_TYPE = "(B)";
    public static final String UNOCCUPIED_TYPE = "(Empty)";
    
    private int noOfHoursPerDay;
	private int noOfDaysPerWeek;
    private String collegeName;
    
    public ArrayList<String> days = new ArrayList<>(7);
    public ArrayList<String> lectureTimes = new ArrayList<>(6);
    
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
    		
    		if(v.getActivityTag().equals(Constraint.LAB_TYPE)) {
    			totalWeight+=LAB_WEIGHTAGE;
    		}
    		
    		v.setWeightage(totalWeight);
    		totalWeight = 0;
    	});
    	
    }
    
    
    
    
}
