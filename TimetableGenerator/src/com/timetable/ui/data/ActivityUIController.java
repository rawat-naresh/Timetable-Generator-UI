package com.timetable.ui.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.timetable.core.classes.Activity;
import com.timetable.core.classes.Student;
import com.timetable.core.classes.Subject;
import com.timetable.core.classes.Teacher;
import com.timetable.core.main.Data;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ActivityUIController implements Initializable {

	 @FXML
	private ListView<Activity> activityList;
	@FXML
	private TextField split;
	@FXML
	private TextField duration;
	@FXML
	private ChoiceBox<String> tagChoice;
	@FXML
	private ChoiceBox<Teacher> teacherChoice;
	@FXML
	private ChoiceBox<Subject> subjectChoice;
	@FXML
	private ChoiceBox<Student> studentChoice;
	
	@FXML
    private ListView<Teacher> selectedTeacherList;

    @FXML
    private ListView<Subject> selectedSubjectList;
	@FXML
	private Button addButton;
	
	private Data data = Data.getInstance();
	private ObservableList<String> tagItems = FXCollections.observableArrayList("(L)","(T)");
	private ObservableList<Teacher> teacherItems;
	private ObservableList<Subject> subjectItems;
	private ObservableList<Student> studentItems;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadInputs();
		loadActivities();
		
		teacherChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Teacher>() {

			@Override
			public void changed(ObservableValue<? extends Teacher> observable, Teacher oldValue, Teacher newValue) {
				if(!selectedTeacherList.getItems().contains(newValue) && newValue != null )
					selectedTeacherList.getItems().add(newValue);
			}
		});
		
		subjectChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subject>() {

			@Override
			public void changed(ObservableValue<? extends Subject> observable, Subject oldValue, Subject newValue) {

				if(!selectedSubjectList.getItems().contains(newValue) && newValue != null)
					selectedSubjectList.getItems().add(newValue);
			}
		});
					
		
	}
	
	private void loadInputs() {
		
		teacherItems = FXCollections.observableArrayList(data.getTeachers());
		subjectItems = FXCollections.observableArrayList(data.getSubjects());
		studentItems = FXCollections.observableArrayList(data.getStudents());
		tagChoice.setValue("(T)");
		tagChoice.setItems(tagItems);
		teacherChoice.setItems(teacherItems);
		subjectChoice.setItems(subjectItems);
		studentChoice.setItems(studentItems);
	}
	
	private void loadActivities() {
		activityList.getItems().addAll(data.getActivities());
		
		
	}

	@FXML
	public void addActivity(ActionEvent event) {
		
		
		try {
			int dur = Integer.parseInt(split.getText());
			int totDur = Integer.parseInt(duration.getText());
			int aKey = data.getActivityKey();
			Student selectedStudent = studentChoice.getSelectionModel().getSelectedItem();
			if(selectedTeacherList.getItems().isEmpty() ||selectedSubjectList.getItems().isEmpty() || selectedStudent == null) {
				showAlert("All Fields Are required For creating Activity");
				return;
			} 
				
			ArrayList<Teacher> teachers= new ArrayList<>(selectedTeacherList.getItems());
			ArrayList<Subject> subjects = new ArrayList<>(selectedSubjectList.getItems());
			
			//check whether the similar activity already exists or not?
			//if already exists show user confirmation alert saying if they want to add this activity or not?
			
			if(checkDuplicacy(teachers,subjects,selectedStudent)) { 
				boolean confirm = showConfirmation("Activity already exists! Are you sure you want to add this activity?");
				if(!confirm) {
					clearFields();
					return;
				}
					
			}
				
			//adding Activity to Data
			for(int i=1;i<=dur;i++) {
				data.addActivity(new Activity(teachers,selectedStudent,subjects,
						dur,totDur,aKey,tagChoice.getSelectionModel().getSelectedItem()));
			}
			
			data.setActivityKey(++aKey);
			clearFields();
			clearActivityList();
			loadActivities();
		}
		catch(NumberFormatException | NullPointerException e) {
			showAlert("Couldn't Create new Activity!");
		}
		
		
	}
	
	private boolean checkDuplicacy(ArrayList<Teacher> teachers, ArrayList<Subject> subjects, Student selectedStudent) {
		for(Activity activity:data.getActivities()) {
			if(activity.getTeachers().containsAll(teachers) 
					&& activity.getSubjects().containsAll(subjects) 
					&& activity.getStudent().equals(selectedStudent)
					) {
				return true;
			}
		}
		return false;
	}
	
	private void clearFields() {
		teacherChoice.getSelectionModel().clearSelection();
		//teacherChoice.setValue(null);
		subjectChoice.getSelectionModel().clearSelection();
		//subjectChoice.setValue(null);
		selectedTeacherList.getItems().clear();
		selectedSubjectList.getItems().clear();
		
	}
	
	private void clearActivityList() {
		activityList.getItems().clear();
	}
	
	private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
	private boolean showConfirmation(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Duplicate entry");
		alert.setHeaderText(null);
		alert.setContentText(message);
		Optional<ButtonType> answer = alert.showAndWait();
		if(answer.get() == ButtonType.OK)
			return true;
		else
			return false;
	}
	
	

	
}
