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
public class Room implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 6L;


	private String roomName;
    

	private String roomNumber;
    
    public Room(String roomNumber,String roomName){
        this.roomNumber = roomNumber;
        this.roomName = roomName;
    }

    public String getRoomName() {
		return roomName;
	}

	public String getRoomNumber() {
		return roomNumber;
	}
    
}
