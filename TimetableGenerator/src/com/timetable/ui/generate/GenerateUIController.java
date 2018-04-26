package com.timetable.ui.generate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import com.timetable.core.main.Generate;

import javafx.event.ActionEvent;

public class GenerateUIController {
	@FXML
	private Button generateButton;

	// Event Listener on Button[#generateButton].onAction
	@FXML
	public void generateTimetable(ActionEvent event) {
		new Generate().generate();
	}
	
}
