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
public class HotelTableViewAdminController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private HotelQuery hotelq;
    
    ObservableList<HotelTableAdmin>data;
    
    @FXML
    private Button backButton;

    @FXML
    private TableView<HotelTableAdmin> table;

    @FXML
    private TableColumn<HotelTableAdmin, String> c1;

    @FXML
    private TableColumn<HotelTableAdmin, String> c2;

    @FXML
    private TableColumn<HotelTableAdmin, String> c3;

    @FXML
    private TableColumn<HotelTableAdmin, String> c4;

    @FXML
    private TableColumn<HotelTableAdmin, String> c5;

    @FXML
    private TableColumn<HotelTableAdmin, String> c6;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private TextField nameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField addressField;

    @FXML
    private Button changeNameButton;

    @FXML
    private Button changeContactButton;

    @FXML
    private Button changeAddressButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button viewRoomsButton;

    @FXML
    private TextField ratingField;

    @FXML
    private Button changeRatingButton;
    
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelTableAdmin(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        if(countryName.equals("None"))
        {   
            cityBox.getItems().clear();
            cityBox.setVisible(false);
            cityBox.setDisable(true);
            reinsertTableData(hotelq.getHotelInfo());
        }
        else
        {
            cityBox.getItems().clear();
            cityBox.setVisible(true);
            cityBox.setDisable(false);
            cityBox.getItems().addAll(cityq.getCities(countryName));
            cityBox.getItems().add("None");
            cityBox.setPromptText("Select City");
            reinsertTableData(hotelq.getHotelInfo(countryName));
        }
    }
    
    @FXML
    private void comboEvent1(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        if(cityName.equals("None"))
        {   
            reinsertTableData(hotelq.getHotelInfo(countryName));
        }
        else
        {
            reinsertTableData(hotelq.getHotelInfo(countryName,cityName));
        }
    }
    
    @FXML
    private void changeHotelName(ActionEvent action)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newName=nameField.getText().trim();
        if(selectedItem==null||newName.isEmpty())
            return;
        String hotelName=selectedItem.getHotelName();
        hotelq.newHotelName(newName, hotelName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(hotelq.getHotelInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(hotelq.getHotelInfo(countryName));
            }
            else
            {
                reinsertTableData(hotelq.getHotelInfo(countryName, cityName));
            }
        }
    }
    
    @FXML
    private void changeHotelContact(ActionEvent action)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newContact=contactField.getText().trim();
        if(selectedItem==null||newContact.isEmpty())
            return;
        String hotelName=selectedItem.getHotelName();
        hotelq.newHotelContact(newContact, hotelName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(hotelq.getHotelInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(hotelq.getHotelInfo(countryName));
            }
            else
            {
                reinsertTableData(hotelq.getHotelInfo(countryName, cityName));
            }
        }
    }
    
    @FXML
    private void changeHotelAddress(ActionEvent action)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newAddress=addressField.getText().trim();
        if(selectedItem==null||newAddress.isEmpty())
            return;
        String hotelName=selectedItem.getHotelName();
        hotelq.newHotelAddress(newAddress, hotelName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(hotelq.getHotelInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(hotelq.getHotelInfo(countryName));
            }
            else
            {
                reinsertTableData(hotelq.getHotelInfo(countryName, cityName));
            }
        }
    }
    
    
    
    
    @FXML
    private void changeHotelRating(ActionEvent action)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        String newR=ratingField.getText().trim();
        if(selectedItem==null||newR.isEmpty())
            return;
        String hotelName=selectedItem.getHotelName();
        double newRating=Double.parseDouble(newR);
        hotelq.newHotelRating(newRating, hotelName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(hotelq.getHotelInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(hotelq.getHotelInfo(countryName));
            }
            else
            {
                reinsertTableData(hotelq.getHotelInfo(countryName, cityName));
            }
        }
    }
    
    @FXML
    private void viewRooms(ActionEvent event)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        String hotelName=selectedItem.getHotelName();
        try{        
                    
                    //System.out.println(user);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("HotelRoomTableViewAdmin.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                    // System.out.println("Haha");
                    HotelRoomTableViewAdminController c=loader.getController();
                    // System.out.println("Haha");
                    c.setHotelName(hotelName);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                  //   System.out.println("Haha");
                }catch(Exception e){System.out.println(e);}
        }
    
         
    
    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("AdminHomePageFXML.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    @FXML
    private void deleteHotel(ActionEvent event)
    {
        HotelTableAdmin selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String hotelName=selectedItem.getHotelName();
            hotelq.deleteHotel(hotelName);
            
            
            String countryName=countryBox.getValue();
            if(countryName==null||countryName.equals("None"))
            {
                reinsertTableData(hotelq.getHotelInfo());
            }
            else
            {
                String cityName=cityBox.getValue();
                if(cityName==null||cityName.equals("None"))
                {
                    reinsertTableData(hotelq.getHotelInfo(countryName));
                }
                else
                {
                    reinsertTableData(hotelq.getHotelInfo(countryName, cityName));
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hotelq=new HotelQuery();
        cityq=new CityQuery();
        countryq=new CountryQuery();
        countryBox.getItems().addAll(countryq.getCountryNames());
        
        cityBox.setVisible(false);
        cityBox.setDisable(true);
    
        countryBox.getItems().add("None");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("address"));
        c3.setCellValueFactory(new PropertyValueFactory<>("contact"));
        c4.setCellValueFactory(new PropertyValueFactory<>("rating"));
        c5.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        c6.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        data = FXCollections.observableArrayList();
         List<List<String>> userDataList = hotelq.getHotelInfo();
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelTableAdmin(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    
    }    
    
}
