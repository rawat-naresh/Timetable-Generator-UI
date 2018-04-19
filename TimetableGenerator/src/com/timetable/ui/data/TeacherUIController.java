package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Teacher;
import com.timetable.core.main.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TeacherUIController implements Initializable {
	@FXML
	private VBox teacherInfo;
	@FXML
	private TableView<Teacher> teacherTable;
	@FXML
	private TableColumn<Teacher,Integer> teacherIdCol;
	@FXML
	private TableColumn<Teacher,String> teacherNameCol;
	@FXML
	private TableColumn<Teacher,Integer> teachingHoursCol;
	@FXML
	private TableColumn<Teacher,Boolean> hasConstraintCol;
	@FXML
	private TextField teacherName;
	@FXML
	private TextField teachingHour;
	@FXML
	private Button addTeacherButton;
	
	private Data data = Data.getInstance();
	
	private ObservableList<Teacher> list;

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadTeacherData();
	}
	
	
	@FXML
	public void addTeacher(ActionEvent event) {
		
		if(teacherName.getText().isEmpty() || teachingHour.getText().isEmpty()) {
    		showAlert("All Fields Are Required!!");
    	}
    	else {
    		
    		try {
    			int hour = Integer.parseInt(teachingHour.getText());
    			int count = data.getTeacherCount();
        		data.addTeacher(new Teacher(count,teacherName.getText(),hour));
        		data.setTeacherCount(++count);
        		
        		loadTeacherData();
    		}
    		catch(NumberFormatException e) {
    			showAlert("Invalid Input!!");
    		}
    		
    		
    	}
		
		
	}
	
	private void loadTeacherData() {
		
		list = FXCollections.observableArrayList(data.getTeachers());
		
		teacherIdCol.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("id"));
		teacherNameCol.setCellValueFactory(new PropertyValueFactory<Teacher,String>("name"));
		teachingHoursCol.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("targetNoHours"));
		hasConstraintCol.setCellValueFactory(new PropertyValueFactory<Teacher,Boolean>("hasConstraint"));
		
		teacherTable.setItems(list);
	}
	
	private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
	
}
