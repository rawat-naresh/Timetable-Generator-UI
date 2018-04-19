package com.timetable.ui.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.timetable.core.classes.Room;
import com.timetable.core.main.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class RoomUIController implements Initializable{

	@FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> roomIdCol;

    @FXML
    private TableColumn<Room, String> roomNameCol;

    @FXML
    private TextField roomNumber;

    @FXML
    private TextField roomName;

    @FXML
    private Button addRoomButton;
    
    Data data = Data.getInstance();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	
		
	}

    @FXML
    void addRoom(ActionEvent event) {

    	String rn = roomName.getText();
		String rnum = roomNumber.getText();
		if(rn.isEmpty() || rnum.isEmpty())
			showAlert("All fields are Required!!");
		else {
			data.addRoom(new Room(rn,rnum));
			
			loadRoomData();
			
		}
    	
    	
    }
    
    public void loadRoomData() {
		ArrayList<Room> rooms = new ArrayList<>();
		if(data.getRooms() !=null) {
			for(int r :data.getRooms().keySet())
				rooms.add(data.getRoom(r));
			
			ObservableList<Room> list = FXCollections.observableArrayList(rooms);
			
			roomIdCol.setCellValueFactory(new PropertyValueFactory<Room,String>("roomNumber"));
			roomNameCol.setCellValueFactory(new PropertyValueFactory<Room,String>("roomName"));
			
			roomTable.setItems(list);
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
