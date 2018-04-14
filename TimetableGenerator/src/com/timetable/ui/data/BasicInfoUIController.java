package com.timetable.ui.data;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BasicInfoUIController {
	@FXML
	private VBox rootVBox;
	@FXML
	private Label collegeName;
	@FXML
	private Button collegeNameModifyButton;

	// Event Listener on Button[#collegeNameModifyButton].onMouseClicked
	@FXML
	public void modifyName(MouseEvent event) {
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
