package com.timetable.ui.constraint;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.timetable.core.classes.Teacher;
import com.timetable.core.constraint.Constraint;
import com.timetable.core.constraint.TimeConstraint;
import com.timetable.core.main.Data;
import com.timetable.ui.main.Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TeacherConstraintUIController implements Initializable {

	@FXML
    private ListView<String> teacherConstraintList;

    @FXML
    private ChoiceBox<Teacher> teacherChoiceBox;

    @FXML
    private ListView<Teacher> teacherList;

    @FXML
    private Button selectDayTime;

    @FXML
    private Button addButton;
    
    private Data data = Data.getInstance();
    private Constraint constraint = Constraint.getInstance();
    private TimeConstraint timeConstraint = TimeConstraint.getInstance();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		loadTeachersConstraint();
    	loadChoiceBox();
    	
    	teacherChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teacher>() {

			@Override
			public void changed(ObservableValue<? extends Teacher> observable, Teacher oldValue, Teacher newValue) {
				
				if(!teacherList.getItems().contains(newValue) && newValue!=null)
					teacherList.getItems().add(newValue);
			}
		});
		
		
	}

    @FXML
    void addConstraint(ActionEvent event) {
    	if(teacherList.getItems().isEmpty() || Main.slotsPickerUIController.selectedTimings == null ) {
    		showAlert("Fields cannot be empty");
    		return;
    	}
    		
    	
    	for(Teacher t : teacherList.getItems()) {
    		timeConstraint.addTeacherNotAvailableTimeSlots(t, Main.slotsPickerUIController.selectedTimings);
    	}
    	
    	loadTeachersConstraint();
    	teacherList.getItems().clear();
    	teacherChoiceBox.getSelectionModel().clearSelection();
    	Main.slotsPickerUIController.selectedTimings = null;
    	
    	
    }
    
    private void loadTeachersConstraint() {
    	teacherConstraintList.getItems().clear();
    	HashMap<Teacher, ArrayList<String>> teachersNotAvailableTimeSlots = timeConstraint.getTeachersNotAvailableTimeSlots();
    	for(Teacher teacher: teachersNotAvailableTimeSlots.keySet() ) {
    		String output = teacher.getName()+"[";
    		for(String s: teachersNotAvailableTimeSlots.get(teacher)) {
    			int t = Character.getNumericValue(s.charAt(0));
    			int d = Character.getNumericValue(s.charAt(1));
    			output += constraint.getDay(d)+":";
    			output += constraint.getLectureTime(t)+",";
    			
    			
    			
    		}
    		
    		output += "]";
    		teacherConstraintList.getItems().add(output);
    		
    	}
    	
    }
    
    private void loadChoiceBox() {
    	ArrayList<Teacher> teachers = data.getTeachers();
    	teacherChoiceBox.getItems().addAll(teachers);
    	
    }


	@FXML
	public void showDayTime(ActionEvent event) {
		Parent daytimePicker = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/timetable/ui/constraint/slotsPickerUI.fxml"));
		try {
			daytimePicker =  loader.load();
			Main.slotsPickerUIController = loader.getController();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if(daytimePicker != null && (constraint.getNoOfDaysPerWeek() > 0 && constraint.getNoOfHoursPerDay() > 0) ) {
			
			
			Stage newStage = new Stage(StageStyle.DECORATED);
			newStage.setTitle("Select Day And Time");
			newStage.setScene(new Scene(daytimePicker));
			newStage.show();
			
		}
		else 
			showAlert("Please Input Days and Times first!");
		
	}
	
	private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
}
