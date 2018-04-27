package com.timetable.core.main;

import com.timetable.core.constraint.Constraint;
import com.timetable.core.export.Export;
import com.timetable.core.timetable.TimetableCell;
import com.timetable.core.timetable.TimetableRow;
import com.timetable.core.timetable.TimetableSheet;

/**
 * @author Naresh
 *
 */
public class Generate {

	Distribution distribution ;
	Data data;
	Constraint constraint;
	
	public Generate() {
		distribution = Distribution.getInstance();
		data = Data.getInstance();
		constraint = Constraint.getInstance();
	}
	
	public void generate() {
		
		//allocateStudents();
		
		//compute weightage
		constraint.computeActivityWeightage(data.getActivities());
		
		//sort activities accordidng to weightage
		data.sortActivities();
		
		/*data.getTeachers().forEach(teacher->{
			System.out.println(teacher.getAllocatedStudent());
		});*/
		
		//create vitrual timetable
		createVirtualTimetable();
		
		//start algo
		startAlgo();
		
		
		printVirtualTbl();
		
		
	}
	
	/*private void allocateStudents() {
		data.getActivities().forEach(activity->{
			ArrayList<Teacher> teachers = activity.getTeachers();
			teachers.forEach(teacher->{
				teacher.addAllocatedStudent(activity.getStudent());
			});
		});
	}*/
	
	
	
	private void createVirtualTimetable() {
		
		//creating virtualTimetable slots
		
		for(int s=0;s<data.getStudents().size();s++) {
			//creating sheet equals to number of student
			TimetableSheet sheet = new TimetableSheet(data.getStudents().get(s).getGroupName());
			
			for(int i=1;i<=constraint.getNoOfDaysPerWeek();i++) {
				//creating row
				TimetableRow row= new TimetableRow();
				
				for(int j=1;j<=constraint.getNoOfHoursPerDay();j++) {
					//creating cell
					TimetableCell cell= new TimetableCell();
	
					//adding each cell to the row
					row.addTimetableCell(j, cell);
				}
				
				//adding each row to the sheet
				sheet.addTimetableRow(i, row);
			}
			
			
			//adding each sheet to the distribution
			distribution.addTimetableSheet(data.getStudents().get(s).getGroupId(), sheet);
		}
		
	}
	
	private void startAlgo() {
		Algorithm a = new Algorithm();
		a.populateTimetable();
	}
	
	public void export() {
		Export export = new Export("NewOutputDemo.xls");
		export.createExcelSheets(distribution.getTimetableSheets());
		
		export.writeToExcelFile();
	}
	
	
	private void printVirtualTbl() {
		
		for(int s=0;s<data.getStudents().size();s++) {
		
		TimetableSheet sheet = distribution.getTimetableSheet(data.getStudents().get(s).getGroupId());

		for(int i=1;i<=constraint.getNoOfDaysPerWeek();i++) {
			
			TimetableRow row= sheet.getRow(i);
			String cellValue="";
			for(int j=1;j<=constraint.getNoOfHoursPerDay();j++) {
				
				TimetableCell cell= row.getCell(j);

				cellValue = cellValue+cell.getType()+"\t";
			}
			
			System.out.println(cellValue);
		}
		System.out.println("------------Another sheet----");

		}
		
		
	}

}







/*

System.out.println("--------------------STUDENT");
data.getStudents().forEach(k->{
	System.out.println(k.getGroupName());
});
System.out.println("--------------------TEACHERS");
data.getTeachers().forEach(k->{
	System.out.println(k.getName());
});

System.out.println("--------------------SUBJECTS");
data.getSubjects().forEach(k->{
	System.out.println(k.getName());
});

*/

/*data.getSortedLectureActivities().forEach((key,value)->{
System.out.println(value.getStudent().getGroupName()+": :"+value.getSubject().getName());
});*/


//Algorithm









/*for(int key: data.getSortedLectureActivities().keySet()) {
System.out.println(data.getLectureActivity(key).getSubject().getName());
}
*/
