/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.constraint;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.timetable.core.classes.Student;
import com.timetable.core.classes.Teacher;


/**
 * @author Naresh
 *
 */
public class TimeConstraint implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 16L;
	private int weightage=0;
	private static TimeConstraint instance = null;
	private HashMap<Student, ArrayList<String>> studentsNotAvailableTimeSlots ;
	
	private HashMap<Teacher, ArrayList<String>> teachersNotAvailableTimeSlots;
	//Teacher is the key,ArrayList<String> contains concatnated index of days+times
	
	private TimeConstraint() {
		studentsNotAvailableTimeSlots = new HashMap<>();
		teachersNotAvailableTimeSlots = new HashMap<>();
	}
	
	public static TimeConstraint getInstance() {
		if(instance == null)
			instance = new TimeConstraint();
		return instance;
	}
	
	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
    
    
    public void addStudentNotAvailableTimeSlots(Student student, ArrayList<String> notAvailableDays ) {
    	
    	studentsNotAvailableTimeSlots.put(student, notAvailableDays);
    }
    
    public ArrayList<String> getStudentNotAvailableTimeSlots(Student student){
    	
    	return studentsNotAvailableTimeSlots.get(student);
    }
    
    public void addTeacherNotAvailableTimeSlots(Teacher teacher, ArrayList<String> notAvailableDays ) {
    	
    	teachersNotAvailableTimeSlots.put(teacher, notAvailableDays);
    }
    
    public ArrayList<String> getTeacherNotAvailableTimeSlots(Teacher teacher){
    	
    	return teachersNotAvailableTimeSlots.get(teacher);
    }
    
    public HashMap<Student, ArrayList<String>> getStudentsNotAvailableTimeSlots(){
    	return studentsNotAvailableTimeSlots;
    }
    
    public HashMap<Teacher, ArrayList<String>> getTeachersNotAvailableTimeSlots(){
    	return teachersNotAvailableTimeSlots;
    }
    
    public static void setInstance(TimeConstraint timeConstraint) {
    	TimeConstraint.instance = timeConstraint;
    }
    
   
}
