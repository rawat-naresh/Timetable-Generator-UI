
package com.timetable.core.main;

import java.util.ArrayList;
import java.util.HashMap;

import com.timetable.core.classes.Course;
import com.timetable.core.classes.LabActivity;
import com.timetable.core.classes.LectureActivity;
import com.timetable.core.classes.Room;
import com.timetable.core.classes.SortActivities;
import com.timetable.core.classes.Student;
import com.timetable.core.classes.Subject;
import com.timetable.core.classes.Teacher;


/**
 * @author Naresh
 *
 */
public class Data {
	private int subjectCount = 0;
	private int teacherCount = 0;
	private int roomKey = 0;
	private int coursekey = 100;
	private int branchKey = 300;
	private int yearKey = 500;
	
	private static  Data instance = null;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private HashMap<Integer,Room> rooms;
    private ArrayList<LectureActivity> lectureActivities;
    private ArrayList<LabActivity> labActivities;
    private HashMap<Integer,LectureActivity> sortedLectureActivities;
    private HashMap<Integer,LabActivity> sortedLabActivities;
    private ArrayList<Course> courses;

        
    private Data(){
        initialize();
    }
    
    private void initialize(){
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        subjects = new ArrayList<>();
        lectureActivities = new ArrayList<>();
        labActivities = new ArrayList<>();
        courses = new ArrayList<>();
        
    }
    
    public static Data getInstance() {
    	if(instance == null) {
    		instance = new Data();
    	}
    	return instance;
    }
    
    
    
    public int getCoursekey() {
		return coursekey;
	}

	public void setCoursekey(int coursekey) {
		this.coursekey = coursekey;
	}

	public int getBranchKey() {
		return branchKey;
	}

	public void setBranchKey(int branchKey) {
		this.branchKey = branchKey;
	}

	public int getYearKey() {
		return yearKey;
	}

	public void setYearKey(int yearKey) {
		this.yearKey = yearKey;
	}

	public int getSubjectCount() {
		return subjectCount;
	}

	public void setSubjectCount(int subjectCount) {
		this.subjectCount = subjectCount;
	}
	
	public int getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(int teacherCount) {
		this.teacherCount = teacherCount;
	}
    
    
    public void addLectureActivity(LectureActivity activity){
        lectureActivities.add(activity);
    }
    public void addLabActivity(LabActivity activity){
        labActivities.add(activity);
    }
    
    public void addSubject(Subject subject){
        subjects.add(subject);
    }
    
    public void addStudent(Student student){
        
        students.add(student);
    }
    
    public void addTeacher(Teacher teacher){
        
        teachers.add(teacher);
    }
    
    public void addRoom(Room room){
        rooms.put(roomKey++, room);
    }


	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public ArrayList<Subject> getSubjects() {
		return subjects;
	}

	public HashMap<Integer, Room> getRooms() {
		return rooms;
	}

	public ArrayList<LabActivity> getLabActivities() {
		return labActivities;
	}
	public ArrayList<LectureActivity> getLectureActivities() {
		return lectureActivities;
	}
	

	public HashMap<Integer,LabActivity> getSortedLabActivities(){
		return sortedLabActivities;
	}
	
	public HashMap<Integer,LectureActivity> getSortedLectureActivities(){
		return sortedLectureActivities;
	}
	
	public LabActivity getLabActivity(int key) {
		
		return getSortedLabActivities().get(key);
		
	}
	public LectureActivity getLectureActivity(int key) {
		
		return getSortedLectureActivities().get(key);
		
	}
	
	public ArrayList<Course> getCourses(){
		
		return courses;
	}
	
	public void addCourse(Course course) {
		
		courses.add(course);
	}
	
	public Course getCourse(int i) {
		
		return courses.get(i);
	}
	
	public  void sortActivities() {
		
		sortedLectureActivities = SortActivities.sortLectureActivitiesWithWeightage(getLectureActivities());
		sortedLabActivities = SortActivities.sortLabActivitiesWithWeightage(getLabActivities());
		
	}
    
    
    
}
