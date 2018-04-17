package com.timetable.ui.data;

import com.timetable.core.classes.Subject;
import com.timetable.core.main.Data;
import com.timetable.ui.main.ResourceInitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddSubjectUIController  {
	@FXML
	private AnchorPane addNewSubject;
	
	@FXML
	private TextField subjectCode;
	
	@FXML
	private TextField subjectName;
	
	@FXML
	private Button addSubjectButton;
	
	private Data data = Data.getInstance();
	
    @FXML
    void addSubject(ActionEvent event) {
    	if(subjectName.getText().isEmpty()) {
    		showAlert();
    	}
    	else {
    		int count = data.getSubjectCount();
    		data.addSubject(new Subject(count,subjectName.getText()));
    		data.setSubjectCount(++count);
    		new DataSidebarUIController().loadUI(ResourceInitializer.subjectListUI, ResourceInitializer.addSubjectUI);
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
