/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.classes;

import java.util.ArrayList;


/**
 * @author Naresh
 *
 */
public class Student {
    private String groupName;//btech_first_cse_cse1
    private int groupId;//unique id for comparision
    private String year;
    private String branch;
    private boolean hasConstraint = false;
    private ArrayList<String> subGroups = new ArrayList<>(2); 

    
    public Student(String groupName, String year, String branch, int groupId) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.year = year;
        this.branch = branch;
    }
    
    public String getYear() {
		return year;
	}
    
	public String getBranch() {
		return branch;
	}
	
	public boolean isHasConstraint() {
		return hasConstraint;
	}


	public void setHasConstraint(boolean hasConstraint) {
		this.hasConstraint = hasConstraint;
	}
	
    public int getGroupId() {
    	return groupId;
    }
    public String getGroupName() {
    	return groupName;
    }
    
    public void setSubGropus(String subGroupName ) {
    	subGroups.add(subGroupName);
    }
    public ArrayList<String> getSubGroups(){
    	return subGroups;
    }
    

        
}
