package com.timetable.ui.main;
	

import com.timetable.ui.constraint.SlotsPickerUIController;
import com.timetable.ui.data.BasicInfoUIController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * @author Naresh
 *
 */
public class Main extends Application {
	public static BasicInfoUIController basicInfoUIController;
	public static SlotsPickerUIController slotsPickerUIController = null;
	
	Stage stage;
	@Override
	public void start(Stage primaryStage) {
		
		stage = primaryStage;
		
		try {
			BorderPane root = ResourceInitializer.topNavUI;
			root.setLeft(ResourceInitializer.mainFileUI);
			root.setCenter(ResourceInitializer.introUI);
			primaryStage.setMaximized(true);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Timetable Generator");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new ResourceInitializer().initializeAllUI();
		launch(args);
	}
	
	public Stage getMainStage() {
		return stage;
	}
	
	public static BasicInfoUIController getBasicInfoUIController() {
		return basicInfoUIController;
	}
	
	
		

}
