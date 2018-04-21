package com.timetable.core.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Branch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	ArrayList<Section> sections;
	String branchName;
	int branchId;
	
	public Branch(String branchName,int branchId) {
		sections = new ArrayList<>();
		this.branchName = branchName;
		this.branchId = branchId;
				
	}
	
	public void addSection(Section section) {
		sections.add(section);
	}
	
	public String getBranchName() {
		return branchName;
	}
	public Section getSection(int i) {
		return sections.get(i);
	}


	public int getBranchId() {
		return branchId;
	}


	public ArrayList<Section> getSections(){
		
		return sections;
	}
	
	public String toString() {
		return branchName;
	}
}
