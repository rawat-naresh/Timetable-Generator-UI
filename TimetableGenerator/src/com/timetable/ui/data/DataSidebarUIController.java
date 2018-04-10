package com.timetable.ui.data;
import com.jfoenix.controls.JFXButton;
import com.timetable.ui.main.ResourceInitializer;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;



/**
 * @author Naresh
 *
 */
public class DataSidebarUIController {
	
	

    @FXML
    private JFXButton basicButton;

    @FXML
    private JFXButton subjectsButton;

    @FXML
    private JFXButton teachersButton;

    @FXML
    private JFXButton studentsButton;

    @FXML
    private JFXButton activityButton;

    @FXML
    private JFXButton spaceButton;

    @FXML
    void showActivityUI(MouseEvent event) {

    }

    @FXML
    void showBasicUI(MouseEvent event) {

    }

    @FXML
    void showSpaceUI(MouseEvent event) {

    }

    @FXML
    void showStudentsUI(MouseEvent event) {

    }

    @FXML
    void showSubjectsUI(MouseEvent event) {
    	//rootBorderLayout.setCenter(ResourceInitializer.subjectsUI);
    }

    @FXML
    void showTeachersUI(MouseEvent event) {
    	
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.teacherListUI);
    }

}
