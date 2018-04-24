package com.timetable.ui.data;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.timetable.ui.main.Main;
import com.timetable.ui.main.ResourceInitializer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    	loadUI("/com/timetable/ui/data/activityUI.fxml");
    }

    @FXML
    void showBasicUI(MouseEvent event) {
    	
    	loadBasicUI("/com/timetable/ui/data/basicInfoUI.fxml");
    	
    }

    @FXML
    void showSpaceUI(MouseEvent event) {
    	loadUI(ResourceInitializer.roomUI);
    	
    }

    @FXML
    void showStudentsUI(MouseEvent event) {
    	loadUI(ResourceInitializer.studentUI);
    }

    @FXML
    void showSubjectsUI(MouseEvent event) {
    	
    	loadUI(ResourceInitializer.subjectUI);
    	
    }

    @FXML
    void showTeachersUI(MouseEvent event) {
    	
    	loadUI(ResourceInitializer.teacherUI);
    	
    }
    
    public void loadUI(String centerLoc) {
    	
    	//FXMLLoader loader = new FXMLLoader(getClass().getResource(centerLoc));
    	try {
    		
    		Parent centerUI = FXMLLoader.load(getClass().getResource(centerLoc));
			ResourceInitializer.topNavUI.setCenter(centerUI);
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    public void loadBasicUI(String centerLoc) {
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(centerLoc));
    	try {
    		
    		Parent centerUI =loader.load();
			ResourceInitializer.topNavUI.setCenter(centerUI);
			Main.basicInfoUIController = loader.getController();
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    

}
