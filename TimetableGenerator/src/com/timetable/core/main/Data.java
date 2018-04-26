
package com.timetable.core.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.timetable.core.classes.Activity;
import com.timetable.core.classes.Branch;
import com.timetable.core.classes.Course;
import com.timetable.core.classes.Room;
import com.timetable.core.classes.Section;
import com.timetable.core.classes.Student;
import com.timetable.core.classes.Subject;
import com.timetable.core.classes.Teacher;
import com.timetable.core.classes.Year;


/**
 * @author Naresh
 *
 */
public class Data implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int subjectKey = 0;
	private int teacherKey = 0;
	private int roomKey = 0;
	private int coursekey = 100;
	private int branchKey = 300;
	private int yearKey = 500;
	private int studentKey = 5000;
	private int activityKey = 10000;
	private int activityHashKey = 100000;
	
	private static  Data instance = null;
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private ArrayList<Subject> subjects;
    private HashMap<Integer,Room> rooms;
    private ArrayList<Activity> activities;
    private HashMap<Integer,Activity> sortedActivities;
    private ArrayList<Course> courses;

        
    private Data(){
        initialize();
    }
    
    private void initialize(){
    	
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        subjects = new ArrayList<>();
        activities = new ArrayList<>();
        courses = new ArrayList<>();
        rooms = new HashMap<>();
        
        
    }
    
    public static Data getInstance() {
    	if(instance == null) {
    		instance = new Data();
    	}
    	return instance;
    }
    
    public static void setInstance(Data instance) {

		Data.instance = instance;
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
		return subjectKey;
	}

	public void setSubjectCount(int subjectCount) {
		this.subjectKey = subjectCount;
	}
	
	public int getTeacherCount() {
		return teacherKey;
	}

	public void setTeacherCount(int teacherCount) {
		this.teacherKey = teacherCount;
	}
    
    
    public void addActivity(Activity activity){
        activities.add(activity);
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
	
	public Room getRoom(int k) {
		return rooms.get(k);
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	

	public HashMap<Integer,Activity> getSortedActivities(){
		return sortedActivities;
	}
	
	
	
	public Activity getSortedActivity(int key) {
		
		return sortedActivities.get(key);
		
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
		
		activities.sort(new Comparator<Activity>() {

			@Override
			public int compare(Activity a1, Activity a2) {
				if(a1.getWeightage() < a2.getWeightage())
					return 1;
				else if(a1.getWeightage() > a2.getWeightage())
					return -1;
				return 0;
			}
			
		});
		
		makeSortedHash();
		
	}
	
	private void makeSortedHash() {
		sortedActivities = new LinkedHashMap<>();
		activities.forEach(activity->{
		sortedActivities.put(activityHashKey++, activity);
	});
		
	}
	
	
	public int generateStudents() {
		ArrayList<Student> tmpStudents = new ArrayList<Student>();
		
				int totalStudents=0;
				for(int i=0; i< getCourses().size(); i++) {
					
					Course c = getCourse(i);
					String cn = c.getCourseName();
					
					for(int j=0; j< c.getYears().size(); j++) {
						
						Year y = c.getYear(j);
						String yn = y.getYearName();
						
						for(int k = 0; k< y.getBranches().size(); k++) {
							
							Branch b = y.getBranch(k);
							String bn = b.getBranchName();
							
							for(int l = 0; l< b.getSections().size();l++) {
								//Section s = b.getSection(l);
								String grpName = cn+"_"+yn+"_"+bn+"_"+ b.getSection(l);
								//Student stud = new Student(grpName,y,b,studentKey++);
								tmpStudents.add(new Student(grpName,y,b,studentKey++));
								/*if(s.isHasSubGroup())
									stud.setSubGroups(Constraint.SUBGROUP_NAMES);*/
							}
						}
						
					}
					
					
				}
				
		ArrayList<Student> stud = getStudents();
			
		for(Student tmp:tmpStudents) {
			boolean occur = false;
			for(Student student: stud) {
				if(tmp.getGroupName().equals(student.getGroupName())) {
					occur = true;
					break;
				}
			
			}
		
			if(!occur) {
				addStudent(tmp);
				totalStudents++;
				
			}
		
		}
		
		return totalStudents;
	}

	public int getActivityKey() {
		return activityKey;
	}

	public void setActivityKey(int activityKey) {
		this.activityKey = activityKey;
	}

    
}
