/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;

import java.io.Serializable;
import java.util.HashSet;


/**
 * @author Naresh
 *
 */
public class Teacher implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	private String name;
    private int id;
    private int targetNoHours;
    private boolean hasConstraint;
    // total hours in a week.
    private HashSet<Student> allocatedStudent;
    


    public Teacher(int id,String name,int targetNoHours) {
    	this.allocatedStudent = new HashSet<>();
        this.id = id;
        this.name = name;
        this.targetNoHours = targetNoHours;
    }
    

	public HashSet<Student> getAllocatedStudent() {
		return allocatedStudent;
	}
	
	public void addAllocatedStudent(Student student) {
		allocatedStudent.add(student);
	}


	public boolean isHasConstraint() {
		return hasConstraint;
	}


	public void setHasConstraint(boolean hasConstraint) {
		this.hasConstraint = hasConstraint;
	}


	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getTargetNoHours() {
		return targetNoHours;
	}


	@Override
	public String toString() {
		return name;
	}
	
	
    
    

 
    
    
}
