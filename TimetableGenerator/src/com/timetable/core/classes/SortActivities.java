package com.timetable.core.classes;

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
	

	public static HashMap<Integer,Activity> sortActivitiesWithWeightage(ArrayList<Activity> activities){
		
		HashMap<Integer, Activity> sortedActivities = new HashMap<>();
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
		
		activities.forEach(k->{
			sortedActivities.put(activityKey++, k);
		});
		
		
		
		return sortedActivities;
	}
	
	
	
	
}
