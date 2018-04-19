/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timetable.core.classes;


/**
 * @author Naresh
 *
 */
public class Room {
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
