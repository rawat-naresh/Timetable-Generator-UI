/*
2 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;

import java.io.Serializable;

/**
 * @author Naresh
 *
 */
public class Subject implements Serializable {
    
	private static final long serialVersionUID = 3L;
	private String name;
    private int subjectId;
    private boolean hasConstraint = false;

    //2 lectures per day for a single subject constraints
    private int maxNumOfLecturesPerDay=1;
    
    public Subject(int subjectId, String name){
        this.subjectId = subjectId;
        this.name = name;
    }

    
    public boolean isHasConstraint() {
		return hasConstraint;
	}


	public void setHasConstraint(boolean hasConstraint) {
		this.hasConstraint = hasConstraint;
	}
	
	public int getMaxNumOfLecturesPerDay() {
		return maxNumOfLecturesPerDay;
	}

	public void setMaxNumOfLecturesPerDay(int maxNoOfLecturesPerDay) {
		this.maxNumOfLecturesPerDay = maxNoOfLecturesPerDay;
	}

	public String getName() {
		return name;
	}

	public int getSubjectId() {
		return subjectId;
	}


	@Override
	public String toString() {
		return name;
	}
    
    
    
    
}
