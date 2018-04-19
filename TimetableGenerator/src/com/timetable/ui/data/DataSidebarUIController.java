package com.timetable.ui.data;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
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
    	loadUI("/com/timetable/ui/data/activityUI.fxml", null);
    	//ResourceInitializer.mainUI.setCenter(ResourceInitializer.activityListUI);
    	//ResourceInitializer.mainUI.setRight(ResourceInitializer.addActivityUI);
    }

    @FXML
    void showBasicUI(MouseEvent event) {
    	
    	loadUI("/com/timetable/ui/data/basicInfoUI.fxml",null);
    	
    }

    @FXML
    void showSpaceUI(MouseEvent event) {
    	loadUI(ResourceInitializer.roomUI,null);
    	
    }

    @FXML
    void showStudentsUI(MouseEvent event) {
    	loadUI(ResourceInitializer.studentUI, null);
    }

    @FXML
    void showSubjectsUI(MouseEvent event) {
    	
    	loadUI(ResourceInitializer.subjectUI,null);
    	
    }

    @FXML
    void showTeachersUI(MouseEvent event) {
    	
    	loadUI(ResourceInitializer.teacherUI,null);
    	
    }
    
    public void loadUI(String centerLoc,String rightLoc) {
    	try {
			Parent centerUI = null;
			Parent rightUI = null;
			
			if(centerLoc != null)
				centerUI = FXMLLoader.load(getClass().getResource(centerLoc));
			
			if(rightLoc != null)
				rightUI = FXMLLoader.load(getClass().getResource(rightLoc));
			
			ResourceInitializer.mainUI.setCenter(centerUI);
	    	ResourceInitializer.mainUI.setRight(rightUI);
	    	
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
