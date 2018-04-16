package com.timetable.ui.data;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddSubjectUIController {
	@FXML
	private AnchorPane addNewSubject;
	
	@FXML
	private TextField subjectCode;
	
	@FXML
	private TextField subjectName;
	
	@FXML
	private Button addSubjectButton;
	
    @FXML
    void addSubject(ActionEvent event) {
    	if(subjectName.getText().isEmpty()) {
    		showAlert();
    	}
    	else {
    		
    	}
    }
    
    private void showAlert() {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText("All fields Are Required");
    	alert.showAndWait();
    }
}
