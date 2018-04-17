package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Subject;
import com.timetable.core.main.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class SubjectListUIController implements Initializable {
	@FXML
	private VBox rootContainer;
	@FXML
	private TableView<Subject> subjectTable;
	@FXML
	private TableColumn<Subject,Integer> subjectId;
	@FXML
	private TableColumn<Subject, String> subjectCode;
	@FXML
	private TableColumn<Subject,String> subjectName;
	@FXML
	private TableColumn<Subject, Boolean> hasConstraint;
	
	private Data data =  Data.getInstance();
	
	private ObservableList<Subject> list;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		list = FXCollections.observableArrayList(data.getSubjects());
		subjectId.setCellValueFactory(new PropertyValueFactory<Subject,Integer>("subjectId"));
		subjectName.setCellValueFactory(new PropertyValueFactory<Subject,String>("name"));
		hasConstraint.setCellValueFactory(new PropertyValueFactory<Subject,Boolean>("hasConstraint"));
		subjectTable.setItems(list);
	}

}
