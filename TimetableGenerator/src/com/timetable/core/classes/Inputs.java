package com.timetable.core.classes;

import java.util.ArrayList;

import com.timetable.core.constraint.Constraint;
import com.timetable.core.constraint.SpaceConstraint;
import com.timetable.core.constraint.TimeConstraint;
import com.timetable.core.main.Data;

/**
 * @author Naresh
 *
 */
public class Inputs {
	ArrayList<String> notAvailableTimes = new ArrayList<>();
	Constraint constraint = Constraint.getInstance();
	public Inputs() {
		
	}
	
	public void makeSampleInputs() {
		//this is a temporary method for manual creation of objects.
		//there will a method like this that will, take all th inputted records, create objects, from GUI and serialize them to file.
		
		
	}
	
	public void fetchFromFile() {
		//1 fetch serialized objects from file(s)
		//2 get an instance of Data class
		//3 fetch all the serialized objects and store them in respective variables of Data class
		
		Data data = Data.getInstance(); //2
		
		SpaceConstraint spaceConstraint = SpaceConstraint.getInstance();
		spaceConstraint.setWeightage(50);
		
		TimeConstraint timeConstraint = TimeConstraint.getInstance();
		
		timeConstraint.setWeightage(50);
		
		
		
		//creating subjects
		Subject sb1 = createSubject(1, "Industrial Interaction");
		Subject sb2 = createSubject(2, "Wireless Network");
		Subject sb3 = createSubject(3, "Human Resource Management");
		Subject sb4 = createSubject(4, "Computer Administration");
		Subject sb5 = createSubject(5, "Advance Computer Architecture");
		Subject sb6 = createSubject(6, "Data Mining");
		Subject sb7 = createSubject(7, "Computer Admin Lab");
		
		//creating teachers
		Teacher t1  = createTeacher(1, "Abhishek Mittal", 6);
		Teacher t2  = createTeacher(2, "Ankit Tomar", 6);
		Teacher t3  = createTeacher(3, "KK Verma", 6);
		Teacher t4  = createTeacher(4, "Shishir Sangal", 6);
		Teacher t5  = createTeacher(5, "Chunnu Lal", 6);
		Teacher t6  = createTeacher(6, "Mayur Srivastava", 6);
		Teacher t7  = createTeacher(7, "Deepak Panauli", 6);
		
		
		

		
		//creating student
		Student s1 = createStudent("BTech", "4", "CSE", "CSE1",1001 );//section cse1
		s1.setSubGropus("T1");
		s1.setSubGropus("T2");
		
		
		//creating student
		Student s2 = createStudent("BTech", "4", "CSE", "CSE2",1002 );//section cse1
		s2.setSubGropus("T1");
		s2.setSubGropus("T2");
				
		
		//automatic allocation of student to respective teacher
		
		t1.addAllocatedStudent(s1);
		t2.addAllocatedStudent(s1);
		t3.addAllocatedStudent(s1);
		t4.addAllocatedStudent(s1);
		t5.addAllocatedStudent(s1);
		t6.addAllocatedStudent(s1);
		t7.addAllocatedStudent(s1);
		
		t1.addAllocatedStudent(s2);
		t2.addAllocatedStudent(s2);
		t3.addAllocatedStudent(s2);
		t4.addAllocatedStudent(s2);
		t5.addAllocatedStudent(s2);
		t6.addAllocatedStudent(s2);
		t7.addAllocatedStudent(s2);
		
		
		//Applying TimeConstraint to student s1,s2
		
		//creating arraylist of notavailable times
		
		String[] a  = {"14","17","18","24","27","28","34","37","38","44","47","48","54","57","58","64","67","68"};
		addTimeSlotToList(a);
		
		s1.setHasConstraint(true);
		timeConstraint.addNotAvailableTimeSlotConstraint(s1.getGroupId(), notAvailableTimes);
		
		s2.setHasConstraint(true);
		timeConstraint.addNotAvailableTimeSlotConstraint(s2.getGroupId(), notAvailableTimes);
		
		
		
		
		//creating Activities LAB
		ArrayList<Teacher> labTeachers1 = new ArrayList<>(2);
		labTeachers1.add(t5);
		labTeachers1.add(t6);
		
		/*ArrayList<Student> labStudents1 = new ArrayList<>(2);
		labStudents1.add(s1);
		labStudents1.add(s1);*/
		
		ArrayList<Subject> labSubjects1  = new ArrayList<>(2);
		labSubjects1.add(sb7);
		labSubjects1.add(sb1);
		
		
		//creating Activities LAB
		ArrayList<Teacher> labTeachers2 = new ArrayList<>(2);
		labTeachers2.add(t5);
		labTeachers2.add(t7);
		
		/*ArrayList<Student> labStudents2 = new ArrayList<>(2);
		labStudents2.add(s1);
		labStudents2.add(s1);*/
		
		ArrayList<Subject> labSubjects2  = new ArrayList<>(2);
		labSubjects2.add(sb7);
		labSubjects2.add(sb1);
		
		LabActivity lab1 = createLabActivity(labTeachers1, s1, labSubjects1, 2, 1, 800, "(L)");
		LabActivity lab2 = createLabActivity(labTeachers2, s1, labSubjects2, 2, 1, 900, "(L)");
		
		
		LabActivity lab11 = createLabActivity(labTeachers1, s2, labSubjects1, 2, 1, 800, "(L)");
		LabActivity lab22 = createLabActivity(labTeachers2, s2, labSubjects2, 2, 1, 900, "(L)");
		
		
		//creating Activities lectures s1
		LectureActivity a10 = createLectureActivity(t1, sb3, s1, 1, 5, 100, "(T)");
		LectureActivity a11 = createLectureActivity(t1, sb3, s1, 1, 5, 100, "(T)");
		LectureActivity a12 = createLectureActivity(t1, sb3, s1, 1, 5, 100, "(T)");
		LectureActivity a13 = createLectureActivity(t1, sb3, s1, 1, 5, 100, "(T)");
		LectureActivity a14 = createLectureActivity(t1, sb3, s1, 1, 5, 100, "(T)");
		
		
		LectureActivity a20 = createLectureActivity(t2, sb5, s1, 1, 5, 200, "(T)");
		LectureActivity a21 = createLectureActivity(t2, sb5, s1, 1, 5, 200, "(T)");
		LectureActivity a22 = createLectureActivity(t2, sb5, s1, 1, 5, 200, "(T)");
		LectureActivity a23 = createLectureActivity(t2, sb5, s1, 1, 5, 200, "(T)");
		LectureActivity a24 = createLectureActivity(t2, sb5, s1, 1, 5, 200, "(T)");
		
		LectureActivity a50 = createLectureActivity(t5, sb4, s1, 1, 5, 500, "(T)");
		LectureActivity a51 = createLectureActivity(t5, sb4, s1, 1, 5, 500, "(T)");
		LectureActivity a52 = createLectureActivity(t5, sb4, s1, 1, 5, 500, "(T)");
		LectureActivity a53 = createLectureActivity(t5, sb4, s1, 1, 5, 500, "(T)");
		LectureActivity a54 = createLectureActivity(t5, sb4, s1, 1, 5, 500, "(T)");
		
		LectureActivity a40 = createLectureActivity(t4, sb2, s1, 1, 5, 400, "(T)");
		LectureActivity a41 = createLectureActivity(t4, sb2, s1, 1, 5, 400, "(T)");
		LectureActivity a42 = createLectureActivity(t4, sb2, s1, 1, 5, 400, "(T)");
		LectureActivity a43 = createLectureActivity(t4, sb2, s1, 1, 5, 400, "(T)");
		LectureActivity a44 = createLectureActivity(t4, sb2, s1, 1, 5, 400, "(T)");

		
		
		LectureActivity a30 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		LectureActivity a31 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		LectureActivity a32 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		LectureActivity a33 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		LectureActivity a34 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		LectureActivity a35 = createLectureActivity(t3, sb6, s1, 1, 6, 300, "(T)");
		
		
		
	
		
		//creating Activities lectures s1
		LectureActivity b10 = createLectureActivity(t1, sb3, s2, 1, 5, 101, "(T)");
		LectureActivity b11 = createLectureActivity(t1, sb3, s2, 1, 5, 101, "(T)");
		LectureActivity b12 = createLectureActivity(t1, sb3, s2, 1, 5, 101, "(T)");
		LectureActivity b13 = createLectureActivity(t1, sb3, s2, 1, 5, 101, "(T)");
		LectureActivity b14 = createLectureActivity(t1, sb3, s2, 1, 5, 101, "(T)");
		
		
		LectureActivity b20 = createLectureActivity(t2, sb5, s2, 1, 5, 201, "(T)");
		LectureActivity b21 = createLectureActivity(t2, sb5, s2, 1, 5, 201, "(T)");
		LectureActivity b22 = createLectureActivity(t2, sb5, s2, 1, 5, 201, "(T)");
		LectureActivity b23 = createLectureActivity(t2, sb5, s2, 1, 5, 201, "(T)");
		LectureActivity b24 = createLectureActivity(t2, sb5, s2, 1, 5, 201, "(T)");
		
		
		LectureActivity b50 = createLectureActivity(t5, sb4, s2, 1, 5, 501, "(T)");
		LectureActivity b51 = createLectureActivity(t5, sb4, s2, 1, 5, 501, "(T)");
		LectureActivity b52 = createLectureActivity(t5, sb4, s2, 1, 5, 501, "(T)");
		LectureActivity b53 = createLectureActivity(t5, sb4, s2, 1, 5, 501, "(T)");
		LectureActivity b54 = createLectureActivity(t5, sb4, s2, 1, 5, 501, "(T)");
	
		
		
		LectureActivity b30 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
		LectureActivity b31 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
		LectureActivity b32 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
		LectureActivity b33 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
		LectureActivity b34 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
		LectureActivity b35 = createLectureActivity(t3, sb6, s2, 1, 6, 301, "(T)");
	
		
		LectureActivity b40 = createLectureActivity(t4, sb2, s2, 1, 5, 401, "(T)");
		LectureActivity b41 = createLectureActivity(t4, sb2, s2, 1, 5, 401, "(T)");
		LectureActivity b42 = createLectureActivity(t4, sb2, s2, 1, 5, 401, "(T)");
		LectureActivity b43 = createLectureActivity(t4, sb2, s2, 1, 5, 401, "(T)");
		LectureActivity b44 = createLectureActivity(t4, sb2, s2, 1, 5, 401, "(T)");


		
	
		
		//storing these inputs to the Data class
		
		data.addTeacher(t1);
		data.addTeacher(t2);
		data.addTeacher(t3);
		data.addTeacher(t4);
		data.addTeacher(t5);
		data.addTeacher(t6);
		data.addTeacher(t7);
		
		
		data.addSubject(sb1);
		data.addSubject(sb2);
		data.addSubject(sb3);
		data.addSubject(sb4);
		data.addSubject(sb5);
		data.addSubject(sb6);
		data.addSubject(sb7);
		
		//adding student
		data.addStudent(s1);
		data.addStudent(s2);
		
		//adding labs
		data.addLabActivity(lab1);
		data.addLabActivity(lab2);
		
		data.addLabActivity(lab11);
		data.addLabActivity(lab22);
		
		//adding lectures
		data.addLectureActivity(a10);
		data.addLectureActivity(a11);
		data.addLectureActivity(a12);
		data.addLectureActivity(a13);
		data.addLectureActivity(a14);
		
		data.addLectureActivity(a50);
		data.addLectureActivity(a51);
		data.addLectureActivity(a52);
		data.addLectureActivity(a53);
		data.addLectureActivity(a54);
		
		data.addLectureActivity(a20);
		data.addLectureActivity(a21);
		data.addLectureActivity(a22);
		data.addLectureActivity(a23);
		data.addLectureActivity(a24);

		
		data.addLectureActivity(a30);
		data.addLectureActivity(a31);
		data.addLectureActivity(a32);
		data.addLectureActivity(a33);
		data.addLectureActivity(a34);
		data.addLectureActivity(a35);

		
		data.addLectureActivity(a40);
		data.addLectureActivity(a41);
		data.addLectureActivity(a42);
		data.addLectureActivity(a43);
		data.addLectureActivity(a44);
		

		
		
		
		
		//lecture for another student
		
		
		//adding lectures
		data.addLectureActivity(b10);
		data.addLectureActivity(b11);
		data.addLectureActivity(b12);
		data.addLectureActivity(b13);
		data.addLectureActivity(b14);
		
		data.addLectureActivity(b20);
		data.addLectureActivity(b21);
		data.addLectureActivity(b22);
		data.addLectureActivity(b23);
		data.addLectureActivity(b24);
		
		
		data.addLectureActivity(b50);
		data.addLectureActivity(b51);
		data.addLectureActivity(b52);
		data.addLectureActivity(b53);
		data.addLectureActivity(b54);

		
		data.addLectureActivity(b30);
		data.addLectureActivity(b31);
		data.addLectureActivity(b32);
		data.addLectureActivity(b33);
		data.addLectureActivity(b34);
		data.addLectureActivity(b35);

		
		data.addLectureActivity(b40);
		data.addLectureActivity(b41);
		data.addLectureActivity(b42);
		data.addLectureActivity(b43);
		data.addLectureActivity(b44);
		

		
		
		
		

			
	}
	
	public void addTimeSlotToList(String[] a) {
		for(int i=0; i<a.length ;i++) {
			notAvailableTimes.add(a[i]);
		}
	}
	


    public Subject createSubject(int subjectId,String subjectName){
    	
         return new Subject(subjectId,subjectName);
    }
    
    public Student createStudent(String course,String year, String branch, String section,int groupId){
        
        return new Student(course+"_"+year+"_"+branch+"_"+section,year,branch,groupId);
    }
    

    
    public Teacher createTeacher(int teacherId,String teacherName,int totalHours){
        
        return new Teacher(teacherId,teacherName,totalHours);
    }
    
    public Room createRoom(String id, String name){
    	
        return new Room(id,name);
    }
    
	public LectureActivity createLectureActivity(Teacher teacher, Subject subject, Student student, 
            int duration,int totalDuration,int activityId,String activityTag){
		
        return new LectureActivity(teacher,subject,student,duration,totalDuration,activityId,activityTag);
    }
	
	public LabActivity createLabActivity(ArrayList<Teacher> teachers, Student student, ArrayList<Subject> subjects, 
            int duration,int totalDuration,int activityId,String activityTag){
		
        return new LabActivity(teachers,student,subjects,duration,totalDuration,activityId,activityTag);
    }
    
    

}
