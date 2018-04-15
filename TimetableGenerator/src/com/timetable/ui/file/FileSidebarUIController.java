package com.timetable.ui.file;

import java.io.File;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * @author Naresh
 *
 */
public class FileSidebarUIController {

	@FXML
    private VBox rootContainer;

    @FXML
    private JFXButton openButton;

    @FXML
    private JFXButton newButton;

    @FXML
    private JFXButton saveButton;

    @FXML
    void openFile(MouseEvent event) {
    	final FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);   
    	File file = fileChooser.showOpenDialog(null);
    	if(file != null) {
    		System.out.println(file.getAbsolutePath());
    	}
    }

    @FXML
    void saveFile(MouseEvent event) {

    }
    
    
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Select File");
        fileChooser.setInitialDirectory(
            new File("C:\\Users\\Ravishu\\git\\Timetable-Generator-UI\\TimetableGenerator\\data")
        );
        
        fileChooser.getExtensionFilters().addAll(
                
                new FileChooser.ExtensionFilter("ser", "*.ser")
            );

}
}
