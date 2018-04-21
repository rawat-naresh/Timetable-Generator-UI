/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Naresh
 *
 */
public class Activity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	private int duration;//duration of this activity i.e normal class have duration of 1hrs.
    private int totalDuration;//no of lectures in the entire week.
    private int activityId;
    private String activityTag;//to identify whether the activity is lab or class.
    private int weightage = 0;
    private ArrayList<Teacher> teachers;
	private Student student;
	private ArrayList<Subject> subjects;

 
    public Activity(ArrayList<Teacher> teachers, Student student, ArrayList<Subject> subjects,
    		int duration,int totalDuration,int activityId,String activityTag) {
    	
		this.duration = duration;
        this.totalDuration = totalDuration;
        this.activityId = activityId;
        this.activityTag = activityTag;
        
        this.teachers = teachers;
        this.subjects = subjects;
        this.student = student;
	}
    
    
    public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public Student getStudent() {
		return student;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public int getDuration() {
		return duration;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public int getActivityId() {
		return activityId;
	}

	public String getActivityTag() {
		return activityTag;
	}

	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}


	@Override
	public String toString() {
		return "Activity [duration=" + duration + ", totalDuration=" + totalDuration + ", activityId=" + activityId
				+ ", activityTag=" + activityTag + ", teachers=" + teachers + ", student=" + student + ", subjects="
				+ subjects + "]";
	}
	
	
   
}
