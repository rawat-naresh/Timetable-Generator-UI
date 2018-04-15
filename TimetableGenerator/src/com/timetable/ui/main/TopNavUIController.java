package com.timetable.ui.main;
import com.jfoenix.controls.JFXButton;
import com.timetable.ui.data.DataSidebarUIController;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * @author Naresh
 *
 */
public class TopNavUIController {

    @FXML
    private JFXButton fileButton;

    @FXML
    private JFXButton dataButton;

    @FXML
    private JFXButton constraintButton;

    @FXML
    private JFXButton generateButton;
    
    @FXML
    private BorderPane rootBorderPane;

    @FXML
    void displayConstraintUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainConstraintUI);
    	ResourceInitializer.mainUI.setCenter(ResourceInitializer.listTeacherConstraintUI);
    	ResourceInitializer.mainUI.setRight(ResourceInitializer.addTeacherConstraintUI);
    }

    @FXML
    void displayDataUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainDataUI);
    	new DataSidebarUIController().loadUI(ResourceInitializer.basicInfoUI, null);
    }

    @FXML
    void displayFileUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainFileUI);
    	rootBorderPane.setCenter(ResourceInitializer.introUI);
    	rootBorderPane.setRight(null);
    }

    @FXML
    void displayGenerateUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainGenerateUI);
    }
    


}
