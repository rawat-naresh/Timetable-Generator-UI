/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;

import java.util.ArrayList;


/**
 * @author Naresh
 *
 */
public class Student {
    private String groupName;//btech_first_cse_cse1
    private int groupId;//unique id for comparision
    private Year year;
    private Branch branch;
    private boolean hasConstraint = false;
    private ArrayList<String> subGroups = new ArrayList<>(2); 

    
    public Student(String groupName, Year year, Branch branch, int groupId) {
        this.groupName = groupName;
        this.groupId = groupId;
        this.year = year;
        this.branch = branch;
    }
    
    public String getGroupName() {
    	return groupName;
    }
    
    public Year getYear() {
		return year;
	}
    
	public Branch getBranch() {
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
    
    
    public void addSubGropus(String subGroupName ) {
    	subGroups.add(subGroupName);
    }
    public ArrayList<String> getSubGroups(){
    	return subGroups;
    }
    
    public String toString() {
    	
    	return groupName;
    }
    

        
}
