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
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.activityListUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addActivityUI);
    }

    @FXML
    void showBasicUI(MouseEvent event) {
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.basicInfoUI);
    	ResourceInitializer.mainUI.setRight(null);
    }

    @FXML
    void showSpaceUI(MouseEvent event) {

    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.roomListUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addRoomUI);
    }

    @FXML
    void showStudentsUI(MouseEvent event) {
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.addCourseUI);
    	ResourceInitializer.mainUI.setRight(null);
    }

    @FXML
    void showSubjectsUI(MouseEvent event) {
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.subjectListUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addSubjectUI);
    }

    @FXML
    void showTeachersUI(MouseEvent event) {
    	
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.teacherListUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addTeacherUI);
    }

}
