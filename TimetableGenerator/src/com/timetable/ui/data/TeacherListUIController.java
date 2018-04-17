package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Teacher;
import com.timetable.core.main.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * @author Naresh
 *
 */
public class TeacherListUIController implements Initializable {
	
	 @FXML
	 private VBox teacherInfo;

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
    
    private Data data =  Data.getInstance();
	
	private ObservableList<Teacher> list;
    
    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		list = FXCollections.observableArrayList(data.getTeachers());
		
		teacherId.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("id"));
		teacherName.setCellValueFactory(new PropertyValueFactory<Teacher,String>("name"));
		teachingHours.setCellValueFactory(new PropertyValueFactory<Teacher,Integer>("targetNoHours"));
		hasConstraint.setCellValueFactory(new PropertyValueFactory<Teacher,Boolean>("hasConstraint"));
		
		teacherTable.setItems(list);
	}

}
