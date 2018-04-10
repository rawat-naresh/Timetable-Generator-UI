package com.timetable.ui.data;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Naresh
 *
 */
public class Teacher {
	private  SimpleIntegerProperty teacherId;
	private SimpleStringProperty teacherName;
	private SimpleIntegerProperty teachingHours;
	private SimpleBooleanProperty hasConstraint;
	
	
	public Teacher(Integer teacherId, String teacherName,
			Integer teachingHours, Boolean hasConstraint) {
		super();
		this.teacherId = new SimpleIntegerProperty(teacherId);
		this.teacherName = new SimpleStringProperty(teacherName);
		this.teachingHours = new SimpleIntegerProperty(teachingHours);
		this.hasConstraint = new SimpleBooleanProperty(hasConstraint);
	}
	public Integer getTeacherId() {
		return teacherId.get();
	}

	public String getTeacherName() {
		return teacherName.get();
	}

	public Integer getTeachingHours() {
		return teachingHours.get();
	}
	
	public boolean isHasConstraint() {
		return hasConstraint.get();
	}

}
