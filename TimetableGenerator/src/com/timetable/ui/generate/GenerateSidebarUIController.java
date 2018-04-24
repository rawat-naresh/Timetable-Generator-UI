package com.timetable.ui.generate;

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
public class GenerateSidebarUIController {

    @FXML
    private BorderPane rootBorderLayout;

    @FXML
    private JFXButton generateButton;

    @FXML
    private JFXButton exportButton;

    @FXML
    void showExportUI(MouseEvent event) {
    	loadUI("/com/timetable/ui/generate/exportUI.fxml");
    }

    @FXML
    void showGenerateUI(MouseEvent event) {
    	loadUI("/com/timetable/ui/generate/generateUI.fxml");
    }
    
    public void loadUI(String centerLoc) {
    	try {
			Parent centerUI = FXMLLoader.load(getClass().getResource(centerLoc));;
			ResourceInitializer.topNavUI.setCenter(centerUI);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}

}
