package com.timetable.ui.main;

import java.io.IOException;

import org.controlsfx.control.GridView;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Naresh
 *
 */
public class ResourceInitializer {
	public static BorderPane mainUI;
	static VBox mainFileUI;
	static VBox mainDataUI;
	static VBox mainConstraintUI;
	static VBox mainGenerateUI;
	static VBox introUI;
	public static VBox teacherListUI;
	public static AnchorPane addTeacherUI;
	public static VBox subjectListUI;
	public static AnchorPane addSubjectUI;
	public static VBox roomListUI;
	public static AnchorPane addRoomUI;
	public static HBox addCourseUI;
	public static VBox basicInfoUI;
	public static VBox activityListUI;
	public static VBox addActivityUI;
	public static VBox listTeacherConstraintUI;
	public static VBox addTeacherConstraintUI;
	public static VBox listStudentConstraintUI;
	public static VBox addStudentConstraintUI;
	public static VBox listRoomConstraintUI;
	public static VBox addRoomConstraintUI;
	
	//public static HBox subjectsUI;
	
	//@SuppressWarnings("unchecked")
	public void initializeAllUI() {
		try {
			mainUI = (BorderPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/main/topNavUI.fxml"));
			mainFileUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/fileSidebarUI.fxml"));
			mainDataUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/dataSidebarUI.fxml"));
			mainConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/constraintSidebarUI.fxml"));
			mainGenerateUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/generate/generateSidebarUI.fxml"));
			introUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/introUI.fxml"));
			//subjectsUI = (HBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/subjects/subjectsUI.fxml"));
			teacherListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/teacherListUI.fxml"));
			addTeacherUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addTeacherUI.fxml"));
			subjectListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/subjectListUI.fxml"));
			addSubjectUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addSubjectUI.fxml"));
			roomListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/roomListUI.fxml"));
			addRoomUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addRoomUI.fxml"));
			addCourseUI = (HBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/studentListUI.fxml"));
			//basicInfoUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/basicInfoUI.fxml"));
			activityListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/activityListUI.fxml"));
			addActivityUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addActivityUI.fxml"));
			listTeacherConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listTeacherConstraintUI.fxml"));
			addTeacherConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addTeacherConstraintUI.fxml"));
			listStudentConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listStudentConstraintUI.fxml"));
			addStudentConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addStudentConstraintUI.fxml"));
			listRoomConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listRoomConstraintUI.fxml"));
			addRoomConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addRoomConstraintUI.fxml"));
			
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	

}
