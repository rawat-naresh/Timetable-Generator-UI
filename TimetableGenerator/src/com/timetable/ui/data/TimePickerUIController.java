package com.timetable.ui.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.timetable.core.constraint.Constraint;
import com.timetable.ui.main.ResourceInitializer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class TimePickerUIController implements Initializable {
	@FXML
	private TextField numberofLec;
	
	@FXML
	private TextField first;
	
	@FXML
	private TextField second;
	
	@FXML
	private TextField third;
	
	@FXML
    private TextField fourth;
	
	@FXML
    private TextField fifth;

    @FXML
    private TextField sixth;

    @FXML
    private TextField seventh;

    @FXML
    private TextField eighth;

    @FXML
    private TextField ninth;

    @FXML
    private TextField tenth;

    @FXML
    private TextField eleventh;

    @FXML
    private TextField twelveth;

    @FXML
    private TextField thirteenth;

    @FXML
    private TextField fourteenth;

    @FXML
    private TextField fifteenth;
    
    @FXML
    private Button saveButton;


	
	private ArrayList<TextField> fields;
	
	private Constraint constraint = Constraint.getInstance();
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fields = new ArrayList<>();
		fields.add(first);
		fields.add(second);
		fields.add(third);
		fields.add(fourth);
		fields.add(fifth);
		fields.add(sixth);
		fields.add(seventh);
		fields.add(eighth);
		fields.add(ninth);
		fields.add(tenth);
		fields.add(eleventh);
		fields.add(twelveth);
		fields.add(thirteenth);
		fields.add(fourteenth);
		fields.add(fifteenth);
		
		
		Integer noofHours = constraint.getNoOfHoursPerDay();
		
		if(noofHours > 0) {
			saveButton.setDisable(false);
			numberofLec.setText(noofHours.toString());
			for(int i=0;i<noofHours;i++) {
				
				TextField t = fields.get(i);
				t.setDisable(false);
				t.setText(constraint.getLectureTime(i));
			}
		}
		
		

		
	}
	

    @FXML
    void modifyGridPane(KeyEvent event) {
    	try {
			int num = Integer.parseInt(numberofLec.getText());
			if(num<=15) {
				saveButton.setDisable(false);
				for(int i=0;i<num;i++) {
					fields.get(i).setDisable(false);
				}
				
				for(int i=num;i<15;i++) {
					fields.get(i).setDisable(true);
				}
				
			}
			else {
				saveButton.setDisable(true);
				/*for(int i = 0;i<15;i++) {
					fields.get(i).setDisable(true);
				}*/
			}
			
			
			
		}
		catch(NumberFormatException e){
			saveButton.setDisable(true);
			System.out.println("Error");
		}
    }
    
    
    @FXML
    void saveTimings(ActionEvent event) {
    	int lectureCount = Integer.parseInt(numberofLec.getText());
    	ArrayList<String> timings = new ArrayList<>();
    	int count = 0;
    	for(int i=0; i<lectureCount; i++) {
    		
    		String t = fields.get(i).getText();
    		if(t.isEmpty()) {
    			showAlert();
    			break;
    		}
    		else {
    			count++;
    			timings.add(t);
    		}
    		
    	}
    	
    	if(count == lectureCount) {
    		constraint.setNoOfHoursPerDay(lectureCount);
    		constraint.setLectureTimes(timings);
    		((Stage)saveButton.getScene().getWindow()).close();
    		
    		//reload the UI
        	new DataSidebarUIController().loadUI(ResourceInitializer.basicInfoUI, null);
    		
    	}
    	
    }
    
    private void showAlert() {
    	Alert alert = new Alert(Alert.AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText("Some of the slots might be Empty");
    	alert.showAndWait();
    }
    
    
}
