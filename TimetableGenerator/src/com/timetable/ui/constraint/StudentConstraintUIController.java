package com.timetable.ui.constraint;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.timetable.core.classes.Student;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StudentConstraintUIController implements Initializable {
	@FXML
	private ListView<String> studentConstraintList;
	@FXML
	private VBox addConstraintRoot;
	@FXML
	private ChoiceBox<Student> studentChoiceBox;
	@FXML
	private ListView<Student> studentList;
	@FXML
	private Button selectDayTime;
	@FXML
	private Button addButton;
	private TimeConstraint timeConstraint = TimeConstraint.getInstance();
	private Constraint constraint = Constraint.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadStudentsConstraints();
    	loadChoiceBox();
    	
    	studentChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {

			@Override
			public void changed(ObservableValue<? extends Student> observable, Student oldValue, Student newValue) {
				
				if(!studentList.getItems().contains(newValue) && newValue!=null)
					studentList.getItems().add(newValue);
			}
		});
    	
	}

	

	@FXML
	public void addConstraint(ActionEvent event) {
		if(studentList.getItems().isEmpty() || Main.slotsPickerUIController.selectedTimings == null ) {
    		showAlert("Fields cannot be empty");
    		return;
    	}
		for(Student s : studentList.getItems()) {
    		timeConstraint.addStudentNotAvailableTimeSlots(s, Main.slotsPickerUIController.selectedTimings);
    	}
    	
    	loadStudentsConstraints();
    	studentList.getItems().clear();
    	studentChoiceBox.getSelectionModel().clearSelection();
    	Main.slotsPickerUIController.selectedTimings = null;
		
	}
	
	
	private void loadStudentsConstraints() {
    	studentConstraintList.getItems().clear();
    	HashMap<Student, ArrayList<String>> studentsNotAvailableTimeSlots = timeConstraint.getStudentsNotAvailableTimeSlots();
    	for(Student student: studentsNotAvailableTimeSlots.keySet() ) {
    		String output = student.getGroupName()+"[";
    		
    		for(String s: studentsNotAvailableTimeSlots.get(student)) {
    			int t = Character.getNumericValue(s.charAt(0));
    			int d = Character.getNumericValue(s.charAt(1));
    			output += constraint.getDay(d)+":";
    			output += constraint.getLectureTime(t)+",";
    			
    		}
    		
    		output += "]";
    		studentConstraintList.getItems().add(output);
    		
    	}
    	
    }
    
    private void loadChoiceBox() {
    	ArrayList<Student> students = Data.getInstance().getStudents();
    	studentChoiceBox.getItems().addAll(students);
    	
    }


	@FXML
	public void showDayTime(ActionEvent event) {
		Parent daytimePicker = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/timetable/ui/constraint/slotsPickerUI.fxml"));
		try {
			//daytimePicker = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/daysPickerUI.fxml"));
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
