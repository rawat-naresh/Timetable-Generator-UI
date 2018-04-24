package com.timetable.ui.constraint;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.timetable.ui.main.ResourceInitializer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * @author Naresh
 *
 */
public class ConstraintSidebarUIController {

    @FXML
    private BorderPane rootBorderLayout;

    @FXML
    private JFXButton teacherConstraintButton;

    @FXML
    private JFXButton studentConstraintButton;

    @FXML
    private JFXButton roomConstraintButton;

    @FXML
    void showRoomConstraintUI(MouseEvent event) {
    	loadUI(ResourceInitializer.roomConstraintUI);
    	
    }

    @FXML
    void showStudentConstraintUI(MouseEvent event) {
    	loadUI(ResourceInitializer.studentConstraintUI);
    	
    }

    @FXML
    void showTeacherConstraintUI(MouseEvent event) {
    	loadUI(ResourceInitializer.teacherConstraintUI);
    }
    
    
    public void loadUI(String centerLoc) {
    	try {
			Parent centerUI = FXMLLoader.load(getClass().getResource(centerLoc));
			ResourceInitializer.topNavUI.setCenter(centerUI);
	    	
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
