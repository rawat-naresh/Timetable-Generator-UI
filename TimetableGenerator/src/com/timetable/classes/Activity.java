/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.classes;

/**
 * @author Naresh
 *
 */
public class Activity {
	
	private int duration;//duration of this activity i.e normal class have duration of 1hrs.
    private int totalDuration;//no of lectures in the entire week.
    private int activityId;
    private String activityTag;//to identify whether the activity is lab or class.
    private int weightage = 0;

    
    public Activity() {
    	//default constructor
    }
    
    public Activity(int duration,int totalDuration,int activityId,String activityTag) {
		this.duration = duration;
        this.totalDuration = totalDuration;
        this.activityId = activityId;
        this.activityTag = activityTag;
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
   
}
