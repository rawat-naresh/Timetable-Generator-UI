package com.timetable.ui.data;

import java.net.URL;
import java.util.ResourceBundle;

import com.timetable.core.classes.Branch;
import com.timetable.core.classes.Course;
import com.timetable.core.classes.Section;
import com.timetable.core.classes.Year;
import com.timetable.core.main.Data;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class StudentUIController implements Initializable {
	@FXML
	private ListView<Course> coursesList;
	@FXML
	private TextField courseName;
	@FXML
	private Button addCourseBtn;
	@FXML
	private ListView<Year> yearList;
	@FXML
	private TextField yearName;
	@FXML
	private Button addYearBtn;
	@FXML
	private ListView<Branch> branchList;
	@FXML
	private TextField branchName;
	@FXML
	private Button branchAddBtn;
	@FXML
	private ListView<Section> sectionList;
	@FXML
	private TextField sectionName;
	@FXML
	private Button addSectionBtn;
	
	@FXML
    private Button generateButton;
	
	@FXML
    private CheckBox subGroupCheckBox;
	
	private int selectedCourse = -1;
	
	
	private Data data = Data.getInstance();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loadCourses();
		
	}
	
	private void loadCourses() {
		//clearing previously populated items
		coursesList.getItems().clear();
		
		//re-populating items
		//for(Course course:data.getCourses()) {
			
			coursesList.getItems().addAll(data.getCourses());
		//}
		
		
	}
	
	
	private void loadYears() {
		//clearing previously populated items
		yearList.getItems().clear();
		
		//selectedCourse = coursesList.getSelectionModel().getSelectedIndex();
		if(selectedCourse < 0)
			return;
		//re-populating items
			
			yearList.getItems().addAll(data.getCourse(selectedCourse).getYears());
		
		yearList.getSelectionModel().select(0);
		
	}
	
	private void loadBranches() {
		//clearing previously populated items
		branchList.getItems().clear();
		int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		if(selectedYear < 0)
			return;
		//re-populating items
			
			branchList.getItems().addAll(data.getCourse(selectedCourse).getYear(selectedYear).getBranches());
		
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
			
			sectionList.getItems().addAll(data.getCourse(selectedCourse).getYear(selectedYear).getBranch(selectedBranch).getSections());
		
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
		
		if(sectionName.getText().isEmpty())
			showAlert("Empty Section Name");
		else {
			b.addSection(new Section(sectionName.getText()));
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
    
    @FXML
    void generateStudent(ActionEvent event) {
    	int res = data.generateStudents();
    	showAlert(res+" New Classes Generated");
    	
    }
    
    @FXML
    void subGroupCheckEvent(ActionEvent event) {
    	int selectedYear = yearList.getSelectionModel().getSelectedIndex();
		int selectedBranch = branchList.getSelectionModel().getSelectedIndex();
    	int selectedSection = sectionList.getSelectionModel().getSelectedIndex();
    	if(selectedSection < 0) {
    		showAlert("Please select a section to divide it into sub groups!!");
    		subGroupCheckBox.setSelected(false);
			return;
    	}
    	
    	Section s = data.getCourse(selectedCourse).getYear(selectedYear).getBranch(selectedBranch).getSection(selectedSection);
    	if(subGroupCheckBox.isSelected()) {
    		s.setHasSubGroup(true);
    	}
    	else {
    		s.setHasSubGroup(false);
    	}
		
		
    }
	
	
	
	private void showAlert(String message) {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
}
