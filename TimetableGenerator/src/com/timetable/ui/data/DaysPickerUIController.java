package com.timetable.ui.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.timetable.core.constraint.Constraint;
import com.timetable.ui.main.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DaysPickerUIController implements Initializable {
	
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
	
	private Constraint constraint = Constraint.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(String day: constraint.getDays()) {
			switch(day) {
			case "Sunday" :
				sun.setSelected(true);
				break;
			case "Monday" :
				mon.setSelected(true);
				break;
			case "Tuesday" :
				tue.setSelected(true);
				break;
			case "Wednesday" :
				wed.setSelected(true);
			case "Thursday":
				thu.setSelected(true);
				break;
			case "Friday":
				fri.setSelected(true);
				break;
			case "Saturday":
				sat.setSelected(true);
				break;
				
			}
		}
	}
	
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
    	
    	Constraint.getInstance().setNoOfDaysPerWeek(days.size());
    	Constraint.getInstance().setDays(days);
    	
    	((Stage)rootContainer.getScene().getWindow()).close();
    	
    	//reload the UI
    	Main.basicInfoUIController.daysList.getItems().clear();
    	Main.basicInfoUIController.loadDays();
    	
    }



}
