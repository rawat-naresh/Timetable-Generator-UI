package com.timetable.ui.main;

import java.io.IOException;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	

	

}
