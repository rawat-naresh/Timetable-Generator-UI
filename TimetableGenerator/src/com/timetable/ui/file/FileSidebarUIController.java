package com.timetable.ui.file;
import java.io.File;
import com.jfoenix.controls.JFXButton;
import com.timetable.core.model.Model;
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
    private JFXButton saveButton;

    @FXML
    void openFile(MouseEvent event) {
    	final FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);   
    	File file = fileChooser.showOpenDialog(null);
    	if(file != null) {
    		
    		new Model().readFromFile(file);
    		
    	}
    }

    @FXML
    void saveFile(MouseEvent event) {

    	final FileChooser fileChooser = new FileChooser();
    	configureFileChooser(fileChooser);   
    	File file = fileChooser.showSaveDialog(null);
    	if(file != null) {
    		new Model().saveToFile(file);
    	}
    	
    }
    
    
    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Save File");
        fileChooser.setInitialDirectory(
            new File(System.getProperty("user.dir")+"\\data")
        );
        
        fileChooser.getExtensionFilters().addAll(
                
                new FileChooser.ExtensionFilter("ser", "*.ser")
            );

}
}
