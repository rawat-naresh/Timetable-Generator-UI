/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;

import java.io.Serializable;



/**
 * @author Naresh
 *
 */
public class Student implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private String groupName;//btech_first_cse_cse1
    private int groupId;//unique id for comparision
    private Year year;
    private Branch branch;
    private boolean hasConstraint = false;
    private String[] subGroups;

    
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
    
    
    public void setSubGroups(String[] subGroups ) {
    	this.subGroups = subGroups;
    }
    public String[] getSubGroups(){
    	return subGroups;
    }
    
    public String toString() {
    	
    	return groupName;
    }
    

        
}
