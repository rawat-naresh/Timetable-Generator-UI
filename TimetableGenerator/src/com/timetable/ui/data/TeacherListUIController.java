package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Naresh
 *
 */
public class TeacherListUIController implements Initializable {

    @FXML    
    private TableView<Teacher> teacherTable;
    
    @FXML
    private TableColumn<Teacher,Integer>  teacherId;
    
    @FXML
    private TableColumn<Teacher,String>  teacherName;
    
    @FXML
    private TableColumn<Teacher,Integer>  teachingHours;
    
    @FXML
    private TableColumn<Teacher,Boolean>  hasConstraint;
    
    
    
    private ObservableList<Teacher> list = FXCollections.observableArrayList(
    		new Teacher(1,"Naresh Rawat",6,false),
    		new Teacher(2,"Nicolas Rawat",6,false),
    		new Teacher(3,"Monkey D.Luffy",6,false),
    		new Teacher(4,"Nick Jonas",6,false),
    		new Teacher(5,"Perfect Strangers",6,false),
    		new Teacher(6,"Naresh Rawat",6,false),
    		new Teacher(7,"Naresh Rawat",6,false)
    		);
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		teacherId.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("teacherId"));
		teacherName.setCellValueFactory(new PropertyValueFactory<Teacher,String>("teacherName"));
		teachingHours.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("teachingHours"));
		hasConstraint.setCellValueFactory(new PropertyValueFactory<Teacher,Boolean>("hasConstraint"));
		
		teacherTable.setItems(list);
	}

}
