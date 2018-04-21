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
    	loadUI(ResourceInitializer.roomConstraintUI,null);
    	//ResourceInitializer.mainUI.setCenter(ResourceInitializer.listRoomConstraintUI);
    	//ResourceInitializer.mainUI.setRight(ResourceInitializer.addRoomConstraintUI);
    }

    @FXML
    void showStudentConstraintUI(MouseEvent event) {
    	loadUI(ResourceInitializer.studentConstraintUI,null);
    	//ResourceInitializer.mainUI.setCenter(ResourceInitializer.listStudentConstraintUI);
    	//ResourceInitializer.mainUI.setRight(ResourceInitializer.addStudentConstraintUI);
    	
    }

    @FXML
    void showTeacherConstraintUI(MouseEvent event) {
    	loadUI(ResourceInitializer.teacherConstraintUI,null);
    	//ResourceInitializer.mainUI.setRight(ResourceInitializer.addTeacherConstraintUI);
    }
    
    
    public void loadUI(String centerLoc,String rightLoc) {
    	try {
			Parent centerUI = null;
			Parent rightUI = null;
			
			if(centerLoc != null)
				centerUI = FXMLLoader.load(getClass().getResource(centerLoc));
			
			if(rightLoc != null)
				rightUI = FXMLLoader.load(getClass().getResource(rightLoc));
			
			ResourceInitializer.topNavUI.setCenter(centerUI);
	    	ResourceInitializer.topNavUI.setRight(rightUI);
	    	
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
