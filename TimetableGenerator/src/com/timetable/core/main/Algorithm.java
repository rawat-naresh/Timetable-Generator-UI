package com.timetable.core.main;

import java.util.ArrayList;
import java.util.Collections;
import com.timetable.core.classes.Activity;
import com.timetable.core.classes.Student;
import com.timetable.core.classes.Teacher;
import com.timetable.core.constraint.Constraint;
import com.timetable.core.constraint.TimeConstraint;
import com.timetable.core.timetable.TimetableCell;
import com.timetable.core.timetable.TimetableRow;
import com.timetable.core.timetable.TimetableSheet;

/**
 * @author Naresh
 *
 */
public class Algorithm {
	
	//find suitable timeslot for passed activity and return its index if success
	Data data;
	Distribution distribution;
	TimeConstraint timeConstraint;
	Constraint constraint;
	public Algorithm(){
		data = Data.getInstance();
		constraint = Constraint.getInstance();
		timeConstraint  = TimeConstraint.getInstance();
		distribution = Distribution.getInstance();
	}
	
	public void populateTimetable() {
		
		data.getStudents().forEach(student->{
			setBreak(student.getGroupId());
		});
		
		
		
		data.getSortedActivities().forEach((k,activity)->{
			int studentId = activity.getStudent().getGroupId();
			TimetableSheet sheet = distribution.getTimetableSheet(studentId);
			findTimeSlot(k, activity, sheet );
			
			
			
			
		});
		

		
		
	}
	
	private int findTimeSlot(Integer activityKey, Activity activity, TimetableSheet sheet) {
		int duration = activity.getDuration();
		
		//HashMap<Integer,int[]> cellConflicts = new HashMap<>();//for adding conflicts
		
		ArrayList<Integer[]> finalRowColOfCells = new ArrayList<>();
		for (Integer r : sheet.getRows().keySet()) {
			TimetableRow row =  sheet.getRows().get(r);
			ArrayList<Integer> startIndexOfCells = new ArrayList<>();
			boolean rowHasLab = false;
			//added later
			int occurrence = 0;
			
			for(Integer c :row.getCells().keySet() ) {
				
				int consecutiveEmptycells = 0;
				int limit = c+duration-1;
				
				
				
				//checking whether the number of consecutive cells, equal to totalDuration, are empty or not.
				
				if(limit <= constraint.getNoOfHoursPerDay()) {
					
					for(int i = c; i <= limit ; i++) {
						TimetableCell currentCell = row.getCell(i);
						String currentCellType = currentCell.getType();
						if(currentCell.isEmpty()) {
							consecutiveEmptycells++;
						}
						
						else if(currentCell.isActive()) {
							//current cell contains either lab or lecture
							if(currentCellType == Constraint.LAB_TYPE) {
								rowHasLab = true;
								break;
							}
							else if(data.getSortedActivity(currentCell.getValue()).getActivityId() == activity.getActivityId()) {
								//current cell containts lecture with same activityId
								
								occurrence++;
								break;
							}
						}
						
						else {
							//current cell contains break
							break;
						}
							
						
					}	
				}
				
				
				/*if this row has labActivity, then skip to the next row*/
				if(rowHasLab || occurrence > 0) {
					
					break;
				}
				
				
				//if the number of consecutive empty cells from this cell= duration,then add the index of current cell to the array
				if(consecutiveEmptycells == duration) {
					
					startIndexOfCells.add(c);
					
				}
				
				
			}
			
			//outside of cell loop
			
			if(!rowHasLab && occurrence == 0) {
				for(Integer c:startIndexOfCells)
					finalRowColOfCells.add(new Integer[] {r,c});
				
			}
			
			
		}
		//outside row
		
		
		
		Collections.shuffle(finalRowColOfCells);
		for(Integer[] rowColArray : finalRowColOfCells) {
			int r = rowColArray[0];
			int c = rowColArray[1];
			int[] conflict = calculateConflict(r, c, activity);
			
			if(conflict[1] == 0) {
				TimetableRow row = sheet.getRow(r);
				for(int i=c;i <= c+duration-1; i++) {
					TimetableCell cell = row.getCell(i);
					cell.setValue(activityKey);
					cell.setEmpty(false);
					cell.setType(activity.getActivityTag());
				}
				
				//clear the cellConclicts list, because we found the conflictless cell for this activity
				//cellConflicts.clear();
				
				//return successs
				//move to the next activity in sortedList
				
				
				
				return 1;
				
			}
			else {
				//insert it into a conflictlist
				
				//found conflict
				//add this cell to the cellConflict list with conflict value
				//cellConflicts.put(conflict[1],conflict[0]);
			}
		}
		
		
		return 0;
	}
	
	
	
	
	
	private int[] calculateConflict(int row,int cell,Activity activity) {
		//ArrayList<Teacher> teachers = labActivity.getTeachers();
		int duration = activity.getDuration();
		int limit = cell+duration-1;
		int conflict = 0;
		int[] sheetConflict = new int[2];

		
		for(Teacher teacher : activity.getTeachers()) {
			int currentTeacherId = teacher.getId();
			for(Student student : teacher.getAllocatedStudent()) {
				int studentId = student.getGroupId();
				sheetConflict[0] = studentId;
				TimetableSheet timetableSheet = distribution.getTimetableSheet(studentId);
				
				for(int i = cell;i <= limit; i++) {
					TimetableCell c = timetableSheet.getRow(row).getCell(cell);
					
					
					if(!c.isEmpty() && c.isActive()) {
						
						int activityKey = c.getValue();
						
						
						for(Teacher t : data.getSortedActivity(activityKey).getTeachers()) {
							
							if(currentTeacherId == t.getId()) 
								
								conflict = conflict + 1;
							
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
	

	
	
	
	/*private boolean swapActivities(TimetableSheet sheet) {
		System.out.println("swap");
		//ArrayList<Integer[]> rowswithLab = new ArrayList<>();
		//ArrayList<Integer[]> rowswithEmptySlots = new ArrayList<>();

		for(int r=1; r <= constraint.getNoOfDaysPerWeek(); r++) {
			TimetableRow row = sheet.getRow(r);
			System.out.println("--");
			for(int c=1; c <= constraint.getNoOfHoursPerDay(); c++) {
				TimetableCell cell = row.getCell(c);
				//System.out.println(c);
				//System.out.println(cell.getType());
				if(cell.isEmpty()) {
					//rowswithEmptySlots.add(new Integer[] {r,c});
					System.out.println("empty");
				}
				else if(cell.getType() == Constraint.LAB_TYPE) {
					rowswithLab.add(new Integer[] {r,c});
					break;
				}
				
			}
		}
		
		
		for(Integer[] a :rowswithLab) {
			System.out.println(a[0]+" "+a[1]);
		}
		
		for(Integer[] a :rowswithEmptySlots) {
			System.out.println(a[0]+" "+a[1]);
		}
		
		
		return false;
	}

	
	*/
	
}