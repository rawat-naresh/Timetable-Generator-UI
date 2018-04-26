package com.timetable.ui.generate;

import com.timetable.core.main.Generate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ExportUIController {
	@FXML
	private Button exportButton;

	// Event Listener on Button[#exportButton].onAction
	@FXML
	public void exportTimetable(ActionEvent event) {
		new Generate().export();
	}
}
