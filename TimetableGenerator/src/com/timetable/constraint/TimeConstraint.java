/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.constraint;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author Naresh
 *
 */
public class TimeConstraint {
	
	private int weightage=0;
	private static TimeConstraint instance = null;
	private HashMap<Integer, ArrayList<String>> notAvailableTimeSlots ;
	
	private TimeConstraint() {
		notAvailableTimeSlots = new HashMap<>();
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
    
    
    public void addNotAvailableTimeSlotConstraint(int id, ArrayList<String> notAvailableDays ) {
    	
    	notAvailableTimeSlots.put(id, notAvailableDays);
    }
    
    public ArrayList<String> getNotAvailableTimeSlots(int id){
    	
    	return notAvailableTimeSlots.get(id);
    }
    
   
}
