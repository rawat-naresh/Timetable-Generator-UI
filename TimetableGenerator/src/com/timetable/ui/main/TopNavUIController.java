package com.timetable.ui.main;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.timetable.ui.data.DataSidebarUIController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    	loadUI(ResourceInitializer.teacherConstraintUI);
    	
    }

    @FXML
    void displayDataUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainDataUI);
    	loadUI(ResourceInitializer.basicInfoUI);
    	new DataSidebarUIController().loadUI(ResourceInitializer.basicInfoUI, null);
    }

    @FXML
    void displayFileUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainFileUI);
    	rootBorderPane.setCenter(ResourceInitializer.introUI);
    }

    @FXML
    void displayGenerateUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainGenerateUI);
    }
    
    public void loadUI(String centerLoc) {
    	try {
			Parent centerUI = FXMLLoader.load(getClass().getResource(centerLoc));;
			rootBorderPane.setCenter(centerUI);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

}
