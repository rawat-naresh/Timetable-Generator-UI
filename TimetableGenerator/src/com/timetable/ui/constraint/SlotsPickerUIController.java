package com.timetable.ui.constraint;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.timetable.core.constraint.Constraint;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SlotsPickerUIController implements Initializable {
	@FXML
	private GridPane gridPane;
	@FXML
	private Button saveButton;
	private Constraint constraint = Constraint.getInstance();
	public ArrayList<String> selectedTimings;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		initializeDayTime();
		
	}

	// Event Listener on Button[#saveButton].onAction
	@FXML
	public void save(ActionEvent event) {
		selectedTimings = new ArrayList<>();
		ObservableList<Node> nodes = gridPane.getChildren();
		int rowCount = gridPane.getRowCount();
		int colCount = gridPane.getColumnCount();
		

		for(int row = 1; row < rowCount; row++) {
			for(int col = 1; col < colCount; col++) {
				
				CheckBox cb = (CheckBox)nodes.get(row*colCount+col+1);
				if(cb.isSelected())
					selectedTimings.add(col+""+row);
				
			}
		}
		
		((Stage)gridPane.getScene().getWindow()).close();
	}
	
	
	private void initializeDayTime() {
		
		Label l = new Label();
		l.setText("Day/Time");
		setStylings(l);;
		gridPane.add(l, 0, 0);
		
		//adding days labels
		for(int col = 1; col <= constraint.getNoOfDaysPerWeek();col++) {
			Label label = new Label();
			setStylings(label);
			label.setText(constraint.getDay(col-1));
			gridPane.add(label, col, 0);
			
		}
		
		
		//adding chechboxes 
		for(int row = 1; row <= constraint.getNoOfHoursPerDay();row++) {
			addHoursLabel(row);
			for(int col = 1; col <= constraint.getNoOfDaysPerWeek();col++) {
				
					CheckBox cb = new CheckBox();
					cb.setAlignment(Pos.CENTER);
					cb.setTextAlignment(TextAlignment.CENTER);
					cb.setPadding(new Insets(5,0,5,0));
					cb.setPrefWidth(Control.USE_COMPUTED_SIZE);
					cb.setMaxWidth(Double.MAX_VALUE);
					cb.setStyle("-fx-font-size:18;");
					gridPane.add(cb,col,row);
				
			}
		}
		
	}
	
	private void addHoursLabel(int row) {
		
		Label label = new Label();
		setStylings(label);
		label.setText(constraint.getLectureTime(row-1));
		gridPane.add(label, 0, row);
		
	}
	
	private void setStylings(Label label) {
		label.setAlignment(Pos.CENTER);
		label.setTextAlignment(TextAlignment.CENTER);
		label.setPrefWidth(100);
		label.setMaxWidth(Double.MAX_VALUE);
		label.setStyle("-fx-font-size:15;");
		label.setPadding(new Insets(5));
	}
	
	
}
