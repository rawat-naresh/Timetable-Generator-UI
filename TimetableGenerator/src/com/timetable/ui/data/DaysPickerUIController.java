package com.timetable.ui.data;

import java.util.ArrayList;

import com.timetable.core.constraint.Constraint;
import com.timetable.ui.main.ResourceInitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DaysPickerUIController {
	
	@FXML
    private VBox rootContainer;
	
	@FXML
	private CheckBox sun;
	@FXML
	private CheckBox mon;
	@FXML
	private CheckBox tue;
	@FXML
	private CheckBox wed;
	@FXML
	private CheckBox thu;
	@FXML
	private CheckBox fri;
	@FXML
	private CheckBox sat;
	
    @FXML
    void saveDays(ActionEvent event) {
    	ArrayList<String> days = new ArrayList<>(7);
    	
    	if(sun.isSelected())
    		days.add("Sunday");
    	if(mon.isSelected())
    		days.add("Monday");
    	if(tue.isSelected())
    		days.add("Tuesday");
    	if(wed.isSelected())
    		days.add("Wednesday");
    	if(thu.isSelected())
    		days.add("Thursday");
    	if(fri.isSelected())
    		days.add("Friday");
    	if(sat.isSelected())
    		days.add("Saturday");
    	
    	Constraint.getInstance().setDays(days);
    	
    	((Stage)rootContainer.getScene().getWindow()).close();
    	
    	//reload the UI
    	new DataSidebarUIController().loadUI(ResourceInitializer.basicInfoUI, null);
    	
    }

}
