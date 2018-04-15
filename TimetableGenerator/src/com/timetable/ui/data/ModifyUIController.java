package com.timetable.ui.data;

import com.timetable.core.constraint.Constraint;
import com.timetable.ui.main.ResourceInitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModifyUIController {

    @FXML
    private VBox rootContainer;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveButton;

    @FXML
    void save(ActionEvent event) {
    	String name = nameTextField.getText();
    	Constraint.getInstance().setCollegeName(name);
    	//closing window
    	((Stage)nameTextField.getScene().getWindow()).close();
    	
    	//reloading UI
    	
    	new DataSidebarUIController().loadUI(ResourceInitializer.basicInfoUI, null);
    	
    }

}
