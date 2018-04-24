package com.timetable.ui.main;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * @author Naresh
 *
 */
public class ResourceInitializer {
	public static BorderPane topNavUI;
	static VBox mainFileUI;
	static VBox mainDataUI;
	static VBox mainConstraintUI;
	static VBox mainGenerateUI;
	static VBox introUI ;
	public static final String teacherUI = "/com/timetable/ui/data/teacherUI.fxml";
	public static final String subjectUI = "/com/timetable/ui/data/subjectUI.fxml";
	public static final String roomUI = "/com/timetable/ui/data/RoomUI.fxml";
	public static final String studentUI = "/com/timetable/ui/data/studentUI.fxml";
	public static final String basicInfoUI = "/com/timetable/ui/data/basicInfoUI.fxml";
	public static final String teacherConstraintUI = "/com/timetable/ui/constraint/teacherConstraintUI.fxml";
	public static final String studentConstraintUI = "/com/timetable/ui/constraint/studentConstraintUI.fxml";
	public static VBox addStudentConstraintUI;
	public static VBox listRoomConstraintUI;
	public static final String roomConstraintUI = "/com/timetable/ui/constraint/roomConstraintUI.fxml";
	
	
	public void initializeAllUI() {
		try {
			topNavUI = (BorderPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/main/topNavUI.fxml"));
			mainFileUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/fileSidebarUI.fxml"));
			mainDataUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/dataSidebarUI.fxml"));
			mainConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/constraintSidebarUI.fxml"));
			mainGenerateUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/generate/generateSidebarUI.fxml"));
			introUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/introUI.fxml"));
			//teacherListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/teacherListUI.fxml"));
			//addTeacherUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addTeacherUI.fxml"));
			//subjectListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/subjectListUI.fxml"));
			//addSubjectUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addSubjectUI.fxml"));
			//roomListUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/roomListUI.fxml"));
			//addRoomUI = (AnchorPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addRoomUI.fxml"));
			//addCourseUI = (HBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/studentListUI.fxml"));
			//activityListUI = (HBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/activityListUI.fxml"));
			//addActivityUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/addActivityUI.fxml"));
			//listTeacherConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listTeacherConstraintUI.fxml"));
			//addTeacherConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addTeacherConstraintUI.fxml"));
			//studentConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listStudentConstraintUI.fxml"));
			//addStudentConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addStudentConstraintUI.fxml"));
			//listRoomConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/listRoomConstraintUI.fxml"));
			//addRoomConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/addRoomConstraintUI.fxml"));
			
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	

}
