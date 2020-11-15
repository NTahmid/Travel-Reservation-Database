/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.text.*;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class HotelRoomFXMLController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private HotelQuery hotelq;
    
    @FXML
    private TextField roomNameField;

    
    @FXML
    private Button backButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField roomNoField;

    @FXML
    private TextField roomCapacityField;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private ComboBox<String> hotelBox;

    @FXML
    private Text warningText;
    
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
    private void comboEvent1(ActionEvent action)
    {
        cityBox.setVisible(true);
        cityBox.setDisable(false);
        cityBox.getItems().clear();
        hotelBox.getItems().clear();
        hotelBox.setVisible(false);
        hotelBox.setDisable(true);
        String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
    }
    
    @FXML
    private void comboEvent2(ActionEvent action)
    {
        hotelBox.setVisible(true);
        hotelBox.setDisable(false);
        hotelBox.getItems().clear();
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        hotelBox.getItems().addAll(hotelq.getHotelNames(countryName, cityName));
    }
    
    @FXML
    private void enterData(ActionEvent action)
    {
        String roomName=roomNameField.getText().trim();
        String roomNo=roomNoField.getText().trim();
        String rcap=roomCapacityField.getText().trim();
        String roomDescription=descriptionField.getText().trim();
        String hotelName=hotelBox.getValue();
        String prc=priceField.getText().trim();
        if(roomNo.isEmpty()||rcap.isEmpty()||roomDescription.isEmpty()||hotelName==null||prc.isEmpty()||roomName.isEmpty())
        {
            warningText.setVisible(true);
            return;
        }
        warningText.setVisible(false);
        int price=Integer.parseInt(prc);
        int roomCapacity=Integer.parseInt(rcap);
        hotelq.InsertHotelRoom(roomNo, hotelName, roomCapacity, roomDescription, price,roomName);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        hotelq=new HotelQuery();
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        hotelBox.setVisible(false);
        hotelBox.setDisable(true);
        countryBox.getItems().addAll(countryq.getCountryNames());
        warningText.setVisible(false);
    }    
    
}
