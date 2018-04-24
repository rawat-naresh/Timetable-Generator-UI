package com.timetable.ui.constraint;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Room;
import com.timetable.core.classes.Subject;
import com.timetable.core.constraint.SpaceConstraint;
import com.timetable.core.main.Data;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class RoomConstraintUIController implements Initializable {
	@FXML
	private ListView<String> roomConstraintList;
	@FXML
	private ChoiceBox<Subject> subjectChoiceBox;
	@FXML
	private ListView<Subject> subjectList;
	@FXML
	private ChoiceBox<Room> roomChoiceBox;
	@FXML
	private Button addButton;
	
	private Data data = Data.getInstance();
	private SpaceConstraint spaceConstraint = SpaceConstraint.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadRoomsConstraints();
		loadSubjectChoiceBox();
		loadRoomChoiceBox();
		
		subjectChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subject>() {

			@Override
			public void changed(ObservableValue<? extends Subject> observable, Subject oldValue, Subject newValue) {
				if(newValue != null && !subjectList.getItems().contains(newValue)){
					subjectList.getItems().add(newValue);
				}
				
			}
		});
		
	
		
	}

	@FXML
	public void addConstraint(ActionEvent event) {
		
	}
	
	private void loadRoomsConstraints() {
		
	}
	private void loadSubjectChoiceBox() {
		subjectChoiceBox.getItems().addAll(data.getSubjects());
	}
	
	private void loadRoomChoiceBox() {
		
		for(Integer k : data.getRooms().keySet())
			roomChoiceBox.getItems().add(data.getRoom(k));
		
	}

	
}
