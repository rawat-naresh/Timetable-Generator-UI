package com.timetable.ui.constraint;

import javafx.scene.layout.GridPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddTeacherConstraintUIController {

	@FXML
    private VBox addConstraintRoot;

    @FXML
    private Button selectDayTime;


	// Event Listener on Button[#selectDayTime].onMouseClicked
	@FXML
	public void showDayTime(MouseEvent event) {
		try {
			GridPane daytimePicker = (GridPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/daytimePickerUI.fxml"));
			Stage newStage = new Stage(StageStyle.DECORATED);
			newStage.setTitle("Select Day And Time");
			newStage.setScene(new Scene(daytimePicker));
			newStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
