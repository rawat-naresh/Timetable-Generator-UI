package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Subject;
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

public class SubjectUIController implements Initializable {
	@FXML
	private TableView<Subject> subjectTable;
	@FXML
	private TableColumn<Subject,Integer> subjectIdCol;
	@FXML
	private TableColumn<Subject,String> subjectCodeCol;
	@FXML
	private TableColumn<Subject,String> subjectNameCol;
	@FXML
	private TableColumn<Subject,Boolean> hasConstraintCol;
	@FXML
	private TextField subjectCode;
	@FXML
	private TextField subjectName;
	@FXML
	private Button addSubjectButton;
	
	private Data data = Data.getInstance();
	
	private ObservableList<Subject> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadSubjectData();
	}

	@FXML
	public void addSubject(ActionEvent event) {
		
		if(subjectName.getText().isEmpty()) {
    		showAlert();
    	}
    	else {
    		int count = data.getSubjectCount();
    		data.addSubject(new Subject(count,subjectName.getText()));
    		data.setSubjectCount(++count);
    		
    		loadSubjectData();
    	}
		
	}
	
	private void loadSubjectData() {
		
		list = FXCollections.observableArrayList(data.getSubjects());
		subjectIdCol.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("subjectId"));
		subjectNameCol.setCellValueFactory(new PropertyValueFactory<Subject,String>("name"));
		hasConstraintCol.setCellValueFactory(new PropertyValueFactory<Subject,Boolean>("hasConstraint"));
		subjectTable.setItems(list);
		
	}
	
	
	
	private void showAlert() {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText("All fields Are Required");
    	alert.showAndWait();
    }

	
}
