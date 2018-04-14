package com.timetable.ui.constraint;

import com.jfoenix.controls.JFXButton;
import com.timetable.ui.main.ResourceInitializer;

import javafx.fxml.FXML;
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
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.listRoomConstraintUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addRoomConstraintUI);
    }

    @FXML
    void showStudentConstraintUI(MouseEvent event) {
    	
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.listStudentConstraintUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addStudentConstraintUI);
    	
    }

    @FXML
    void showTeacherConstraintUI(MouseEvent event) {
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.listTeacherConstraintUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addTeacherConstraintUI);
    }

}
