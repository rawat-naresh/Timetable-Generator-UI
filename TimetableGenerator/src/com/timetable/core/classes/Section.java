package com.timetable.core.classes;

import java.io.Serializable;

public class Section implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 11L;
	String sectionName;
	boolean hasSubGroup = false;
	public Section(String sectionName) {
		this.sectionName = sectionName;
	}
	
	
	public boolean isHasSubGroup() {
		return hasSubGroup;
	}


	public void setHasSubGroup(boolean hasSubGroup) {
		this.hasSubGroup = hasSubGroup;
	}


	public String getSectionName() {
		return sectionName;
	}


	@Override
	public String toString() {
		return sectionName;
	}
}
