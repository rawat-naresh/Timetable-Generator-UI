package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Branch;
import com.timetable.core.classes.Course;
import com.timetable.core.classes.Year;
import com.timetable.core.main.Data;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StudentListUIController implements Initializable {
	@FXML
	private ListView<String> coursesList;
	@FXML
	private TextField courseName;
	@FXML
	private Button addCourseBtn;
	@FXML
	private ListView<String> yearList;
	@FXML
	private TextField yearName;
	@FXML
	private Button addYearBtn;
	@FXML
	private ListView<String> branchList;
	@FXML
	private TextField branchName;
	@FXML
	private Button branchAddBtn;
	@FXML
	private ListView<String> sectionList;
	@FXML
	private TextField sectionName;
	@FXML
	private Button addSectionBtn;
	
	private int selectedCourse = -1;
	//private int selectedYear = -1;
	//private int selectedBranch = -1;
	
	private Data data = Data.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loadCourses();
		
	}
	
	private void loadCourses() {
		//clearing previously populated items
		coursesList.getItems().clear();
		
		//re-populating items
		for(Course course:data.getCourses()) {
			
			coursesList.getItems().addAll(course.getCourseName());
		}
		
		
	}
	
	
	private void loadYears() {
		//clearing previously populated items
		yearList.getItems().clear();
		
		//selectedCourse = coursesList.getSelectionModel().getSelectedIndex();
		if(selectedCourse < 0)
			return;
		//re-populating items
		for(Year year : data.getCourse(selectedCourse).getYears()) {
			
			yearList.getItems().addAll(year.getYearName());
		}
		
		yearList.getSelectionModel().select(0);
		
	}
	
	private void loadBranches() {
		//clearing previously populated items
		branchList.getItems().clear();
		int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		if(selectedYear < 0)
			return;
		//re-populating items
		for(Branch branch : data.getCourse(selectedCourse).getYear(selectedYear).getBranches()) {
			
			branchList.getItems().addAll(branch.getBranchName());
		}
		
		branchList.getSelectionModel().select(0);
		
	}
	
	
	private void loadSections() {
		
		//clearing previously populated items
		sectionList.getItems().clear();
		int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		int selectedBranch = branchList.getSelectionModel().getSelectedIndex();
		
		if(selectedBranch < 0)
			return;
		//re-populating items
		for(String section : data.getCourse(selectedCourse).getYear(selectedYear).getBranch(selectedBranch).getSections()) {
			
			sectionList.getItems().addAll(section);
		}
		
		sectionList.getSelectionModel().select(0);
		
	}

	@FXML
	public void addCourse(ActionEvent event) {
		
		if(courseName.getText().isEmpty())
			showAlert("Empty Course Name");
		else {
			int ck = data.getCoursekey();
			data.addCourse(new Course(courseName.getText(),ck));
			data.setCoursekey(++ck);
			loadCourses();
			
		}
	}

	
	@FXML
	public void addYear(ActionEvent event) {
		
		selectedCourse = coursesList.getSelectionModel().getSelectedIndex();
		if(selectedCourse < 0) {
			showAlert("Course must be selected to add a Year!!");
			return;
		}
		
		Course c = data.getCourse(selectedCourse);
		
		if(yearName.getText().isEmpty())
			showAlert("Empty Year Name");
		else {
			int yk = data.getYearKey();
			c.addYear(new Year(yearName.getText(),yk));
			data.setCoursekey(++yk);
			loadYears();
		}
		
	}
	
	
	@FXML
	public void addBranch(ActionEvent event) {
		
		int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		if(selectedYear < 0) {
			showAlert("Year must be selected to add a Branch!!");
			return;
		}
		
		Year y = data.getCourse(selectedCourse).getYear(selectedYear);
		
		if(branchName.getText().isEmpty())
			showAlert("Empty Branch Name");
		else {
			int bk = data.getBranchKey();
			y.addBranch(new Branch(branchName.getText(),bk));
			data.setCoursekey(++bk);
			loadBranches();
		}
		
	}
	// Event Listener on Button[#addSectionBtn].onAction
	@FXML
	public void addSection(ActionEvent event) {
		
		int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		int selectedBranch = branchList.getSelectionModel().getSelectedIndex();
		if(selectedBranch < 0) {
			showAlert("Branch must be selected to add a Section!!");
			return;
		}
		
		Branch b = data.getCourse(selectedCourse).getYear(selectedYear).getBranch(selectedBranch);
		
		if(branchName.getText().isEmpty())
			showAlert("Empty Branch Name");
		else {
			b.addSection(sectionName.getText());
			loadSections();
		}
		
	}
	
	@FXML
    void branchListMouseEvent(MouseEvent event) {
		loadSections();
    }

    @FXML
    void courseListMouseEvent(MouseEvent event) {
    	selectedCourse = coursesList.getSelectionModel().getSelectedIndex();
    	loadYears();
    	loadBranches();
    	loadSections();
    	
    }



    @FXML
    void yearListMouseEvent(MouseEvent event) {
    	loadBranches();
    	loadSections();
    }
	
	
	
	private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
}
