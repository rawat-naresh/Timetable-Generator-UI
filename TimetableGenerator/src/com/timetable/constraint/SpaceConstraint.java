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
public class SpaceConstraint {
	
	private int weightage=0;
	private static SpaceConstraint instance = null;
	private HashMap<Integer, ArrayList<Integer>> subjectPreferredRooms ;
	
	private SpaceConstraint() {
		subjectPreferredRooms = new HashMap<>();
	}
	
	public static SpaceConstraint getInstance() {
		if(instance == null)
			instance = new SpaceConstraint();
		return instance;
	}
	
	public int getWeightage() {
		return weightage;
	}

	public void setWeightage(int weightage) {
		this.weightage = weightage;
	}
	    
    public void addSubjectPreferredRoomsConstraint(int subjectId, ArrayList<Integer> rooms ) {
    	
    	subjectPreferredRooms.put(subjectId, rooms);
    }
    
    public ArrayList<Integer> getPreferredRooms(int subjectId){
    	
    	return subjectPreferredRooms.get(subjectId);
    }
    
    
}
