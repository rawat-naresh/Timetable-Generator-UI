package com.timetable.ui.main;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * @author Naresh
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		
		
		try {
			BorderPane root = ResourceInitializer.mainUI;
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
	
	
		

}
