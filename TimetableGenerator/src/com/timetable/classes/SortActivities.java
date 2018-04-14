package com.timetable.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Naresh
 *
 */
public class SortActivities {
	private static int activityKey = 0;

	public SortActivities() {
		
	}
	

	public static HashMap<Integer,LectureActivity> sortLectureActivitiesWithWeightage(ArrayList<LectureActivity> activities){
		
		HashMap<Integer, LectureActivity> sortedActivities = new HashMap<>();
		activities.sort(new Comparator<LectureActivity>() {

			@Override
			public int compare(LectureActivity a1, LectureActivity a2) {
				if(a1.getWeightage() < a2.getWeightage())
					return 1;
				else if(a1.getWeightage() > a2.getWeightage())
					return -1;
				return 0;
			}
			
		});
		
		activities.forEach(k->{
			sortedActivities.put(activityKey++, k);
		});
		
		
		
		return sortedActivities;
	}
	
	
	public static HashMap<Integer,LabActivity> sortLabActivitiesWithWeightage(ArrayList<LabActivity> activities){
		HashMap<Integer, LabActivity> sortedActivities = new HashMap<>();
		
		activities.sort(new Comparator<LabActivity>() {

			@Override
			public int compare(LabActivity a1, LabActivity a2) {
				if(a1.getWeightage() < a2.getWeightage())
					return 1;
				else if(a1.getWeightage() > a2.getWeightage())
					return -1;
				return 0;
			}
			
		});
		
		activities.forEach(k->{
			sortedActivities.put(activityKey++, k);
		});
		
		return sortedActivities;
	}
	
}
