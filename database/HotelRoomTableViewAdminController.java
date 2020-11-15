/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class HotelRoomTableViewAdminController implements Initializable {
    
    private String hotelName;
    private HotelQuery hotelq;
    ObservableList<HotelRoomTableAdmin>data;
    
    @FXML
    private Button backButton;

    @FXML
    private TableView<HotelRoomTableAdmin> table;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c1;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c2;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c3;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c4;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c5;

    @FXML
    private TableColumn<HotelRoomTableAdmin, String> c6;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField capacityField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button nameButton;

    @FXML
    private Button capacityButton;

    @FXML
    private Button descriptionButton;

    @FXML
    private TextField priceField;

    @FXML
    private Button priceButton;
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelRoomTableAdmin(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }
    
    
    public void setHotelName(String hName)
    {
        hotelName=hName;
    }
    
    
    @FXML
    private void changeRoomName(ActionEvent action)
    {
        HotelRoomTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newName=nameField.getText().trim();
        if(selectedItem==null||newName.isEmpty())
            return;
        String roomId=selectedItem.getRoomId();
        hotelq.newRoomName(newName, roomId);
        reinsertTableData(hotelq.getRoomInfo(hotelName));
        
    }
    
    @FXML
    private void incrementRoomCapacity(ActionEvent action)
    {
        HotelRoomTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newCapacity=capacityField.getText().trim();
        if(selectedItem==null||newCapacity.isEmpty())
            return;
        int cap=Integer.parseInt(newCapacity);
        String roomId=selectedItem.getRoomId();
        hotelq.incrementRoomCap(cap, roomId);
        reinsertTableData(hotelq.getRoomInfo(hotelName));
    }
    
    @FXML
    private void changeRoomDescription(ActionEvent action)
    {
        HotelRoomTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newDescription=descriptionField.getText().trim();
        if(selectedItem==null||newDescription.isEmpty())
            return;
        String roomId=selectedItem.getRoomId();
        hotelq.newRoomDescription(newDescription, roomId);
        reinsertTableData(hotelq.getRoomInfo(hotelName));
        
    }
    
    
    
    
    @FXML
    private void changeRoomPrice(ActionEvent action)
    {
        HotelRoomTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newPrice=priceField.getText().trim();
        if(selectedItem==null||newPrice.isEmpty())
            return;
        int price=Integer.parseInt(newPrice);
        String roomId=selectedItem.getRoomId();
        hotelq.newRoomPrice(price, roomId);
        reinsertTableData(hotelq.getRoomInfo(hotelName));
    }
    
    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("HotelTableViewAdmin.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    @FXML
    private void deleteRoom(ActionEvent event)
    {
        HotelRoomTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        String roomId=selectedItem.getRoomId();
        hotelq.deleteRoom(roomId);
        reinsertTableData(hotelq.getRoomInfo(hotelName));
    }
    
    @FXML
    public void load(ActionEvent event)
    {
        reinsertTableData(hotelq.getRoomInfo(hotelName));
        System.out.println(hotelName);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hotelq=new HotelQuery();
        c1.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        c2.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        c3.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        c4.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
        c5.setCellValueFactory(new PropertyValueFactory<>("roomDescription"));
        c6.setCellValueFactory(new PropertyValueFactory<>("price"));
        data = FXCollections.observableArrayList();
         List<List<String>> userDataList = hotelq.getRoomInfo(hotelName);
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelRoomTableAdmin(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }    
    
}
