package com.timetable.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.timetable.classes.LabActivity;
import com.timetable.classes.LectureActivity;
import com.timetable.classes.Student;
import com.timetable.classes.Teacher;
import com.timetable.constraint.Constraint;
import com.timetable.constraint.TimeConstraint;
import com.timetable.timetable.TimetableCell;
import com.timetable.timetable.TimetableRow;
import com.timetable.timetable.TimetableSheet;

/**
 * @author Naresh
 *
 */
public class Algorithm {
	
	//find suitable timeslot for passed activity and return its index if success
	Data data;
	Distribution distribution;
	TimeConstraint timeConstraint;
	public Algorithm(){
		data = Data.getInstance();
		timeConstraint  = TimeConstraint.getInstance();
		distribution = Distribution.getInstance();
	}
	
	public void populateTimetable() {
		
		data.getStudents().forEach(student->{
			setBreak(student.getGroupId());
		});
		
		
		
		data.getSortedLabActivities().forEach((k,labActivity)->{
			int studentId = labActivity.getStudent().getGroupId();
			TimetableSheet sheet = distribution.getTimetableSheet(studentId);
			findLabTimeSlot(k, labActivity, sheet );
			
			
			
			
		});
		
		/*data.getSortedLectureActivities().forEach((activityKey,lectureActivity)->{
			int studentId = lectureActivity.getStudent().getGroupId();
			TimetableSheet sheet = distribution.getTimetableSheet(studentId);
			
			findLectureTimeSlot(0,activityKey, lectureActivity, sheet);
		
			
		});
		*/
		
		for(Integer activityKey:data.getSortedLectureActivities().keySet()) {
			LectureActivity lectureActivity = data.getLectureActivity(activityKey);
			int studentId = lectureActivity.getStudent().getGroupId();
			TimetableSheet sheet = distribution.getTimetableSheet(studentId);
			
			findLectureTimeSlot(0,activityKey, lectureActivity, sheet);
			
		}
		
		
	}
	
	private int findLabTimeSlot(Integer activityKey, LabActivity lab, TimetableSheet sheet) {
		int duration = lab.getDuration();
		ArrayList<Integer[]> finalRowColOfCells = new ArrayList<>();
		for (Integer r : sheet.getRows().keySet()) {
			TimetableRow row =  sheet.getRows().get(r);
			ArrayList<Integer> startIndexOfCells = new ArrayList<>();
			boolean rowHasLab = false;
			
			for(Integer c :row.getCells().keySet() ) {
				
				int consecutiveEmptycells = 0;
				int limit = c+duration-1;
				
				//checking whether the number of consecutive cells, equal to totalDuration, are empty or not.
				
				if(limit <= Constraint.noOfHoursPerDay ) {
					
					for(int i = c; i <= limit ; i++) {
						TimetableCell currentCell = row.getCell(i);
						int currentCellType = currentCell.getType();
						if(currentCell.isEmpty()) {
							consecutiveEmptycells++;
						}
						
						else if( currentCell.isActive() && currentCellType == Constraint.LAB_TYPE ) {
							rowHasLab = true;
							break;
	
						}
						
						else  {
							//we encounter a break cell
							//or we found non empty lecture slot
							break;
						}
							
						
					}	
				}
				
				
				/*if this row has labActivity, then skip to the next row*/
				if(rowHasLab) {
					
					break;
				}
				
				
				//if the number of consecutive empty cells from this cell= duration,then add the index of current cell to the array
				if(consecutiveEmptycells == duration) {
					
					startIndexOfCells.add(c);
					
				}
				
				
			}
			
			//outside of cell loop
			
			if(!rowHasLab) {
				for(Integer c:startIndexOfCells)
					finalRowColOfCells.add(new Integer[] {r,c});
				
			}
			
			
		}
		//outside row
		
		
		
		Collections.shuffle(finalRowColOfCells);
		for(Integer[] rowColArray : finalRowColOfCells) {
			int r = rowColArray[0];
			int c = rowColArray[1];
			int[] conflict = calculateLabConflict(r, c, lab);
			
			if(conflict[1] == 0) {
				TimetableRow row = sheet.getRow(r);
				for(int i=c;i <= c+duration-1; i++) {
					TimetableCell cell = row.getCell(i);
					cell.setValue(activityKey);
					cell.setEmpty(false);
					cell.setType(Constraint.LAB_TYPE);
				}
				
				return 1;
				
			}
			else {
				//insert it into a conflictlist
			}
		}
		
		
		return 0;
	}
	
	
	
	
	
	private int[] calculateLabConflict(int row,int cell,LabActivity labActivity) {
		//ArrayList<Teacher> teachers = labActivity.getTeachers();
		int duration = labActivity.getDuration();
		int limit = cell+duration-1;
		int conflict = 0;
		int[] sheetConflict = new int[2];

		
		for(Teacher teacher : labActivity.getTeachers()) {
			int currentTeacherId = teacher.getId();
			for(Student student : teacher.getAllocatedStudent()) {
				int studentId = student.getGroupId();
				sheetConflict[0] = studentId;
				TimetableSheet timetableSheet = distribution.getTimetableSheet(studentId);
				
				for(int i = cell;i <= limit; i++) {
					TimetableCell c = timetableSheet.getRow(row).getCell(cell);
					
					
					if(!c.isEmpty() && c.isActive()) {
						
						int activityKey = c.getValue();
						if(c.getType() == Constraint.LAB_TYPE) {
							//activityType = 1; //indicates lab
							LabActivity anotherLab = data.getLabActivity(activityKey);
							ArrayList<Teacher> anotherTeachers = anotherLab.getTeachers();
							for(Teacher anotherTeacher: anotherTeachers) {
								if(currentTeacherId == anotherTeacher.getId()) {
									conflict = conflict + 1;
									break;
								}
							}
							
						}
					
						else{
							
							LectureActivity anotherLecture = data.getLectureActivity(activityKey);
							if(currentTeacherId == anotherLecture.getTeacher().getId()) {
								conflict = conflict + 1;
								
							}
						}
						
					}
						
				}
				if(conflict > 0) {
					sheetConflict[1] = conflict;
					break;
				}
				
				
			}
			
			if(conflict > 0) {
				break;
			}
		}
		
		
		
		return sheetConflict;
	}
	
	


	

	private void setBreak(int id) {
		
		ArrayList<String> notAvailableTimes = timeConstraint.getNotAvailableTimeSlots(id);
		notAvailableTimes.forEach(times->{
			//System.out.println(times);
			
			int row = Character.getNumericValue(times.charAt(0));
			int cell = Character.getNumericValue(times.charAt(1));
			//System.out.println(row+" "+cell);
			TimetableCell c = distribution.getTimetableSheet(id).getRow(row).getCell(cell);
			c.setEmpty(false);
			c.setActive(false);
			//c.setValue(Constraint.BREAK_TYPE);
			c.setType(Constraint.BREAK_TYPE);
		});
	}
	

	
	private boolean findLectureTimeSlot(int recursion_count, Integer activityKey, LectureActivity lecture,TimetableSheet sheet) {
		//defining base condition for recursion
		/*if(recursion_count == 0) {
			return false;
		}
		else {*/
			
			//find timeslot for lecture
			int teacherId = lecture.getTeacher().getId();
			int activityId = lecture.getActivityId();
			//int duration = lecture.getDuration();
			int maxNoOfLecturesPerDay = lecture.getSubject().getMaxNumOfLecturesPerDay();
			
			ArrayList<Student> students = lecture.getTeacher().getAllocatedStudent();
			HashMap<Integer,int[]> cellConflicts = new HashMap<>();
			
			
			for (Integer r : sheet.getRows().keySet()) {
				TimetableRow row =  sheet.getRows().get(r);
				ArrayList<Integer> emptySlots = new ArrayList<>();
				int occurrence = 0;
				for(Integer c :row.getCells().keySet() ) {
					TimetableCell cell = row.getCell(c);
					
					if(cell.isEmpty() ) {
						emptySlots.add(c);
					}
	
					else if( cell.isActive() ) {
						int currentActivityId ;
						if(cell.getType() == Constraint.LECTURE_TYPE)
							currentActivityId = data.getLectureActivity(cell.getValue()).getActivityId();
						else
							currentActivityId = data.getLabActivity(cell.getValue()).getActivityId();
						
						
						if(currentActivityId == activityId) {
							occurrence = occurrence+1;
						}
						//active cell are reserved and do not participate in timetable ,break slots are not considered as active
						//cell is not empty
						//increase the no's occurrence of activity	
						
						
						
					}
					
				}
				
				//randomly shuffling all the empty slots
				Collections.shuffle(emptySlots);
				
				if(occurrence < maxNoOfLecturesPerDay && emptySlots.size() > 0) {
					for(Integer c : emptySlots) {
						
						int[] sheetConflict = calculateLectureConflict(r,c,teacherId,students );
						if(sheetConflict[1] == 0) {
							//put activity in this slot
							TimetableCell noConflictCell = row.getCell(c);
							noConflictCell.setValue(activityKey);
							noConflictCell.setEmpty(false);
							noConflictCell.setType(Constraint.LECTURE_TYPE);
							System.out.println("Activity Placed : "+data.getLectureActivity(activityKey).getStudent().getGroupName()+" : "+data.getLectureActivity(activityKey).getSubject().getName());
							
							//clear the cellConclicts list, because we found the conflictless cell for this activity
							cellConflicts.clear();
							
							//return successs
							//move to the next activity in sortedList
							return true;
							//since we have found appropriate slot,this 'return' will end the function with return value 1 i.e success.
						}
						else {
							//System.out.println("adding conflict");
							//found conflict
							//add this cell to the cellConflict list with conflict value
							cellConflicts.put(sheetConflict[0],sheetConflict);
						}
						
					}

					
				}
				
				
			}
			
			
			

			System.out.println("----FAILED---"+data.getLectureActivity(activityKey).getStudent().getGroupName()+" : "+data.getLectureActivity(activityKey).getSubject().getName());
			//swapActivities(sheet);
			
			return false;

			
			
		//}
		
	}
	
	private boolean swapActivities(TimetableSheet sheet) {
		System.out.println("swap");
		//ArrayList<Integer[]> rowswithLab = new ArrayList<>();
		//ArrayList<Integer[]> rowswithEmptySlots = new ArrayList<>();

		for(int r=1; r <= Constraint.noOfDaysPerWeek; r++) {
			TimetableRow row = sheet.getRow(r);
			System.out.println("--");
			for(int c=1; c <= Constraint.noOfHoursPerDay; c++) {
				TimetableCell cell = row.getCell(c);
				//System.out.println(c);
				//System.out.println(cell.getType());
				if(cell.isEmpty()) {
					//rowswithEmptySlots.add(new Integer[] {r,c});
					System.out.println("empty");
				}/*
				else if(cell.getType() == Constraint.LAB_TYPE) {
					rowswithLab.add(new Integer[] {r,c});
					break;
				}*/
				
			}
		}
		
		/*
		for(Integer[] a :rowswithLab) {
			System.out.println(a[0]+" "+a[1]);
		}
		
		for(Integer[] a :rowswithEmptySlots) {
			System.out.println(a[0]+" "+a[1]);
		}
		
		*/
		return false;
	}

	
	private int[] calculateLectureConflict(int r, int c, int teacherId,ArrayList<Student> students) {
		int[] sheetConflict = new int[4];
		
		for(Student student:students) {
			int studentId = student.getGroupId();
			TimetableSheet s = distribution.getTimetableSheet(studentId);
			TimetableCell cell = s.getRow(r).getCell(c);
			//we are checking for only one conflict ,i.e a teacher shouldn't teach multiple students at same day/time.
			
			
			if(!cell.isEmpty()) {
				
				ArrayList<Integer> teachersId = new ArrayList<>(2) ;
				if(cell.getType() == Constraint.LECTURE_TYPE)
					teachersId.add(data.getLectureActivity(cell.getValue()).getTeacher().getId());
				else
					data.getLabActivity(cell.getValue()).getTeachers().forEach(teacher->{
						teachersId.add(teacher.getId());
					});
				
				
				for(int id: teachersId) {
					if(teacherId == id) {
						sheetConflict[0] = studentId;
						sheetConflict[1] = sheetConflict[0]+1;
						sheetConflict[2] = r;
						sheetConflict[3] = c;
					}
				}
				
			}
	
			
		}
		
		return sheetConflict;
		

	}
	
}