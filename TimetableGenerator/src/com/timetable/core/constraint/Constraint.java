/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.constraint;

import java.util.ArrayList;

import com.timetable.core.classes.LabActivity;
import com.timetable.core.classes.LectureActivity;


/**
 * @author Naresh
 *
 */
public class Constraint {
 
    public static int noOfHoursPerDay = 8;
    public static int noOfDaysPerWeek =6;
    private static final int TIME_CONSTRAINT = 50;
    private static final int SPACE_CONSTRAINT = 50;
    private static int totalWeight=0;
    public static final int LAB_TYPE = 1996;
    public static final int LECTURE_TYPE = 1997;
    public static final int BREAK_TYPE = 1989;
    public static final int UNOCCUPIED_TYPE = 1999;
    
    public static String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    public static  String[] lectureTimes = {"09:00-09:55","09:55-10:50","10:50-11:45","11:45-12:40","12:40-1:35",
                                            "01:35-02:30","02:30-03:25","03:25-04:20"};
   
    
    public void computeLabWeightage(ArrayList<LabActivity> a) {
    	
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
    
    public void computeLectureWeightage(ArrayList<LectureActivity> a) {
    	
    	a.forEach((v)->{
    		if(v.getTeacher().isHasConstraint())
    			totalWeight+=TIME_CONSTRAINT;
    		
    		
    		if(v.getStudent().isHasConstraint())
    			totalWeight+=TIME_CONSTRAINT;
    		
    		if(v.getSubject().isHasConstraint())
    			totalWeight+=SPACE_CONSTRAINT;
    		
    		v.setWeightage(totalWeight);
    		totalWeight = 0;
    	});
    }
    
    
}
