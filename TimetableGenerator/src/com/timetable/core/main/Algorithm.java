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
			setBreak(student);
		});
		
		
		data.getSortedActivities().forEach((key,activity)->{
			int studentId = activity.getStudent().getGroupId();
			TimetableSheet sheet = distribution.getTimetableSheet(studentId);
			findTimeSlot(key, activity, sheet );
			
		});
		
	}
	
	private int findTimeSlot(Integer activityKey, Activity activity, TimetableSheet sheet) {
		int duration = activity.getDuration();
		//System.out.println(duration);
		
		
		//HashMap<Integer,int[]> cellConflicts = new HashMap<>();//for adding conflicts
		
		ArrayList<Integer[]> finalRowColOfCells = new ArrayList<>();
		for (Integer r : sheet.getRows().keySet()) {
			TimetableRow row =  sheet.getRows().get(r);
			ArrayList<Integer> startIndexOfCells = new ArrayList<>();
			boolean rowHasLab = false;
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
		
		
		if(finalRowColOfCells.isEmpty()) {
			//in-sheet conflict
			//this conflict is normally caused by the the lab placement.
			
			ArrayList<TimetableRow> labRows = findRowsWithLab(sheet);
			ArrayList<TimetableRow>emptyCellRows = findEmptyCells(sheet);
			
			int occurrence = 0;
			@SuppressWarnings("unused")
			TimetableRow noOccurrenceRow;
			int removedActivityKey = 0 ; 
			//finding an activity in "labrows" with which we can swap this activity without raising any in-sheet conflicts
			for(TimetableRow labRow :labRows) {
				for(Integer labCellIndex : labRow.getCells().keySet()) {
					TimetableCell cellFromLabRow = labRow.getCell(labCellIndex);
					if(cellFromLabRow.getType().equals(Constraint.LECTURE_TYPE)) {
						int activityId = data.getSortedActivity(cellFromLabRow.getValue()).getActivityId();
						
						for(TimetableRow emptyCellRow : emptyCellRows) {
							occurrence = 0;//reinitializing occurrence when moving to the next row.
							for(Integer cellIndex : emptyCellRow.getCells().keySet()) {
								TimetableCell cell = emptyCellRow.getCell(cellIndex);
									if(cell.getType().equals(Constraint.LECTURE_TYPE)) {
										if(activityId == data.getSortedActivity(cell.getValue()).getActivityId()) {
											occurrence++;
										}
									}
							}
							
							if(occurrence == 0) {
								noOccurrenceRow = emptyCellRow;
								break;
							}
						}
						
						
						if(occurrence == 0) {
							removedActivityKey = cellFromLabRow.getValue();
							cellFromLabRow.setValue(-1);
							cellFromLabRow.setEmpty(true);
							cellFromLabRow.setType(Constraint.UNOCCUPIED_TYPE);
							break;
						}
						
					}
					
					
						
				}
				
				if(occurrence == 0)
					break;
					
				
			}
			
			//System.out.println(rows+" "+cells);
			//System.out.println("Internal Conflict!!! Couldn't place :" +activity);
			//System.out.println(removedActivityKey);
			
			
			//recursive call with unplaced activity first
			//after that making another recursive call with removed activity
			findTimeSlot(activityKey, activity, sheet);
			findTimeSlot(removedActivityKey, data.getSortedActivity(removedActivityKey), sheet);
		}
		
		Collections.shuffle(finalRowColOfCells);
		
		for(Integer[] rowColArray : finalRowColOfCells) {
			int r = rowColArray[0];
			int c = rowColArray[1];
			int[] conflict = calculateConflict(r, c, activity);
			
			if(conflict[1] == 0 ) {
				TimetableRow row = sheet.getRow(r);
				
				//placement of activity into cell
				
				for(int i = c;i <= c+duration-1; i++) {
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
				System.out.println("External Conflict:Couldn't place"+activity);
				//found conflict
				//add this cell to the cellConflict list with conflict value
				//cellConflicts.put(conflict[1],conflict[0]);
			}
		}
		
		
		return 0;
	}
	
	
	
	private ArrayList<TimetableRow> findRowsWithLab(TimetableSheet sheet) {
		
		ArrayList<TimetableRow> rows = new ArrayList<>();
		
		for (Integer r : sheet.getRows().keySet()) {
			TimetableRow row =  sheet.getRows().get(r);
			
			for(Integer c :row.getCells().keySet() ) {
				if(row.getCell(c).getType().equals(Constraint.LAB_TYPE)) { 
					rows.add(row);
					break;
				}
				
			}
			
		}
		
		return rows;
		
	}
	
	private ArrayList<TimetableRow> findEmptyCells(TimetableSheet sheet){
		ArrayList<TimetableRow> rows = new ArrayList<>();
		
		for (Integer r : sheet.getRows().keySet()) {
			TimetableRow row =  sheet.getRows().get(r);
			
			for(Integer c :row.getCells().keySet() ) {
				
				if(row.getCell(c).getType().equals(Constraint.UNOCCUPIED_TYPE)) {
					rows.add(row);
				}
					
			}
			
		}
		
		
		return rows;
		
	}
	
	private int[] calculateConflict(int row,int cell,Activity activity) {

		int duration = activity.getDuration();
		int limit = cell+duration-1;
		int conflict = 0;
		int[] sheetConflict = new int[2];

		
		for(Teacher teacher : activity.getTeachers()) {
			int currentTeacherId = teacher.getId();
			//there are no students allocated to the teacher so,below loop won't work for now
			for(Student student : teacher.getAllocatedStudent()) {
				int studentId = student.getGroupId();
				sheetConflict[0] = studentId;
				TimetableSheet timetableSheet = distribution.getTimetableSheet(studentId);
				
				for(int i = cell;i <= limit; i++) {
					TimetableCell c = timetableSheet.getRow(row).getCell(i);
					
					
					if(!c.isEmpty() && c.isActive()) {
						
						int activityKey = c.getValue();
						
						
						for(Teacher t : data.getSortedActivity(activityKey).getTeachers()) {
							
							if(currentTeacherId == t.getId()) 
								
								conflict = conflict + 1;
							
						}
						
						
					}
						
				}
				
				/*if(conflict > 0) {
					sheetConflict[1] = conflict;
					break;
				}*/
				
				
			}
			
			/*if(conflict > 0) {
				break;
			}*/
		}
		
		
		
		return sheetConflict;
	}
	
	


	

	private void setBreak(Student student) {
		ArrayList<String> notAvailableTimes = timeConstraint.getStudentNotAvailableTimeSlots(student);
		notAvailableTimes.forEach(times->{
			
			int row = Character.getNumericValue(times.charAt(0));
			int cell = Character.getNumericValue(times.charAt(1));
			TimetableCell c = distribution.getTimetableSheet(student.getGroupId()).getRow(row).getCell(cell);
			c.setEmpty(false);
			c.setActive(false);
			c.setType(Constraint.BREAK_TYPE);
		});
	}
	

	
}