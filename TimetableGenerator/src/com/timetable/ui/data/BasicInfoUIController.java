package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.constraint.Constraint;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BasicInfoUIController implements Initializable {
	@FXML
    private VBox rootContainer;

    @FXML
    private Label collegeName;

    @FXML
    private Button collegeNameModifyButton;

    @FXML
    private ListView<?> daysList;

    @FXML
    private TextField daysTextField;

    @FXML
    private Button addDayButton;

    @FXML
    private ListView<?> hoursList;

    @FXML
    private TextField hourTextField;

    @FXML
    private Button addHourButton;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		collegeName.setText(Constraint.getInstance().getCollegeName());
		
	}

    @FXML
    void addDay(ActionEvent event) {

    }

    @FXML
    void addHour(ActionEvent event) {

    }

    @FXML
    void modifyName(ActionEvent event) {

    	try {
			VBox modifyUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/modifyUI.fxml"));
			Stage newStage = new Stage(StageStyle.DECORATED);
			newStage.setTitle("Modify Name");
			newStage.setScene(new Scene(modifyUI));
			newStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    	
    }

	

}
