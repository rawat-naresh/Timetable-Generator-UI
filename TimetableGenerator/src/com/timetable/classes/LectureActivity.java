package com.timetable.classes;

/**
 * @author Naresh
 *
 */
public class LectureActivity extends Activity{

	private Teacher teacher;
    private Subject subject;
    private Student student;
    
	//constructor for creating lectures
    public LectureActivity(Teacher teacher, Subject subject, Student student, 
            int duration,int totalDuration,int activityId, String activityTag){
    	
    	super(duration,totalDuration,activityId,activityTag);
    	this.teacher = teacher;
        this.subject = subject;
        this.student = student;
        
    }
    
	public Teacher getTeacher() {
		return teacher;
	}

	public Subject getSubject() {
		return subject;
	}

	public Student getStudent() {
		return student;
	}

}
