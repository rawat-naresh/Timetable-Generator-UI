package com.timetable.ui.main;
import com.jfoenix.controls.JFXButton;
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
    }

    @FXML
    void displayDataUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainDataUI);
    	//rootBorderPane.set
    }

    @FXML
    void displayFileUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainFileUI);
    }

    @FXML
    void displayGenerateUI(MouseEvent event) {
    	rootBorderPane.setLeft(ResourceInitializer.mainGenerateUI);
    }
    


}
