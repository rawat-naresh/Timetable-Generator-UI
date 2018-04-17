package com.timetable.core.classes;

import java.util.ArrayList;

public class Branch {

	ArrayList<String> sections;
	String branchName;
	int branchId;
	
	public Branch(String branchName,int branchId) {
		sections = new ArrayList<>();
		this.branchName = branchName;
		this.branchId = branchId;
				
	}
	
	public void addSection(String sectionName) {
		sections.add(sectionName);
	}
	
	public String getBranchName() {
		return branchName;
	}
	public String getSection(int i) {
		return sections.get(i);
	}


	public int getBranchId() {
		return branchId;
	}


	public ArrayList<String> getSections(){
		
		return sections;
	}
}
