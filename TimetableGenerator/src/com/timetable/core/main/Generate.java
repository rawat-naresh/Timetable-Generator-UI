package com.timetable.core.main;

import com.timetable.core.classes.Inputs;
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

	public static void main(String[] args) {
		
		new Inputs().fetchFromFile();
		Data data = Data.getInstance();
		
		Constraint constraint = new Constraint();
		constraint.computeLabWeightage(data.getLabActivities());
		constraint.computeLectureWeightage(data.getLectureActivities());
		
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
		
		//sort activities accordidng to weightage
		
		data.sortActivities();
		
		
		Distribution distribution = Distribution.getInstance();
	
		
		
		//creating virtualTimetable slots
		
		for(int s=0;s<data.getStudents().size();s++) {
			//creating sheet equals to number of student
			TimetableSheet sheet = new TimetableSheet(data.getStudents().get(s).getGroupName());
			
			for(int i=1;i<=Constraint.noOfDaysPerWeek;i++) {
				//creating row
				TimetableRow row= new TimetableRow();
				
				for(int j=1;j<=Constraint.noOfHoursPerDay;j++) {
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
		
		
		
		
		/*data.getSortedLectureActivities().forEach((key,value)->{
			System.out.println(value.getStudent().getGroupName()+": :"+value.getSubject().getName());
		});*/
		
		
		//Algorithm
		
		
		Algorithm a = new Algorithm();
		a.populateTimetable();
		
		
		
		
		/*for(int s=0;s<data.getStudents().size();s++) {
			//creating sheet equals to number of student
			TimetableSheet sheet = distribution.getTimetableSheet(data.getStudents().get(s).getGroupId());
			
			for(int i=1;i<=Constraint.noOfDaysPerWeek;i++) {
				//creating row
				TimetableRow row= sheet.getRow(i);
				String cellValue="";
				for(int j=1;j<=Constraint.noOfHoursPerDay;j++) {
					//creating cell
					TimetableCell cell= row.getCell(j);
	
					cellValue = cellValue+cell.getType()+"\\("+cell.isEmpty()+"\\)"+"\t";
				}
				
				System.out.println(cellValue);
			}
			System.out.println("------------Another sheet----");
			
		}*/
		
		/*for(int key: data.getSortedLectureActivities().keySet()) {
			System.out.println(data.getLectureActivity(key).getSubject().getName());
		}
		*/
		
		
		
		
		
		
		
		
		
		//Exporting
		/*
		Export export = new Export("NewOutputDemo.xls");
		export.createExcelSheets(distribution.getTimetableSheets());
		
		export.writeToExcelFile();*/
	
	

	}

}
