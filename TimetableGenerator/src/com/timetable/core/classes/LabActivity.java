package com.timetable.core.classes;

import java.util.ArrayList;

/**
 * @author Naresh
 *
 */
public class LabActivity extends Activity{
	
	private ArrayList<Teacher> teachers;
	private Student student;
	private ArrayList<Subject> subjects;

	
	//construtor for creating activity for labs
    public LabActivity(ArrayList<Teacher> teachers, Student student, ArrayList<Subject> subjects, 
            int duration,int totalDuration,int activityId,String activityTag){
    	
    	super(duration,totalDuration,activityId,activityTag);
        this.teachers = teachers;
        this.subjects = subjects;
        this.student = student;
        
        
    }
    
    
    public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public Student getStudent() {
		return student;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}
}
