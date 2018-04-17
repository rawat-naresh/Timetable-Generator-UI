package com.timetable.ui.data;

import com.timetable.core.classes.Teacher;
import com.timetable.core.main.Data;
import com.timetable.ui.main.ResourceInitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddTeacherUIController {

    @FXML
    private TextField teacherName;

    @FXML
    private TextField teachingHour;

    @FXML
    private Button addTeacherButton;
    
    private Data data = Data.getInstance();
    
    @FXML
    void addTeacher(ActionEvent event) {

    	if(teacherName.getText().isEmpty() || teachingHour.getText().isEmpty()) {
    		showAlert("All Fields Are Required!!");
    	}
    	else {
    		
    		try {
    			int hour = Integer.parseInt(teachingHour.getText());
    			int count = data.getTeacherCount();
        		data.addTeacher(new Teacher(count,teacherName.getText(),hour));
        		data.setSubjectCount(++count);
        		
        		new DataSidebarUIController().loadUI(ResourceInitializer.teacherListUI, ResourceInitializer.addTeacherUI);
    		}
    		catch(NumberFormatException e) {
    			showAlert("Invalid Input!!");
    		}
    		
    		
    	}
    }
    
    private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }

}
