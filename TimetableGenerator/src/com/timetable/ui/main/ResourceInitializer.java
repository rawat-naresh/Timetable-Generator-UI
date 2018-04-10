package com.timetable.ui.main;

import java.io.IOException;

import com.timetable.ui.data.Teacher;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
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
	public static TableView<Teacher> teacherListUI;
	//public static HBox subjectsUI;
	
	public void initializeAllUI() {
		try {
			mainUI = (BorderPane)FXMLLoader.load(getClass().getResource("/com/timetable/ui/main/topNavUI.fxml"));
			mainFileUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/fileSidebarUI.fxml"));
			mainDataUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/dataSidebarUI.fxml"));
			mainConstraintUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/constraint/constraintSidebarUI.fxml"));
			mainGenerateUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/generate/generateSidebarUI.fxml"));
			introUI = (VBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/file/introUI.fxml"));
			//subjectsUI = (HBox)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/subjects/subjectsUI.fxml"));
			teacherListUI = (TableView<Teacher>)FXMLLoader.load(getClass().getResource("/com/timetable/ui/data/teacherListUI.fxml"));
		
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}//TimetableGenerator/src/com/timetable/ui/generate/mainGenerateUI.fxml
	

	

}
