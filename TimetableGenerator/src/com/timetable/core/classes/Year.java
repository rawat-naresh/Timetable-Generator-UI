package com.timetable.core.classes;

import java.util.ArrayList;

public class Year {

	ArrayList<Branch> branches;
	String yearName;
	int yearId;
	
	public Year(String yearName, int yearId) {
		
		branches = new ArrayList<>();
		this.yearName = yearName;
		this.yearId = yearId;
	}

	public ArrayList<Branch> getBranches() {
		return branches;
	}
	
	public Branch getBranch(int i) {
		return branches.get(i);
	}
	public void addBranch(Branch branch) {
		branches.add(branch);
	}

	public String getYearName() {
		return yearName;
	}

	public int getYearId() {
		return yearId;
	}
	
	
}
