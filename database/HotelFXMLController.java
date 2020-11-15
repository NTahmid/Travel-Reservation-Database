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
public class HotelFXMLController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private HotelQuery hotelq;
    @FXML
    private Button backButton;

    @FXML
    private TextField hotelIdField;

    @FXML
    private TextField hotelNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField ratingField;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private Button enterButton;

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
    private void enterData(ActionEvent action)
    {
        String hotelId=hotelIdField.getText().trim();
        String hotelName=hotelNameField.getText().trim();
        String address=addressField.getText().trim();
        String contact=contactField.getText().trim();
        String cityName=cityBox.getValue();
        String r=ratingField.getText().trim();
        String countryName=countryBox.getValue();
        if(hotelId.isEmpty()||hotelName.isEmpty()||address.isEmpty()||contact.isEmpty()||cityName==null||countryName==null||r.isEmpty())
        {
            warningText.setVisible(true);
            return;
        }
        warningText.setVisible(false);
        double rating=Double.parseDouble(r);
        hotelq.InsertHotel(hotelId, hotelName, address, contact, rating, countryName, cityName);
    }
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        cityBox.setVisible(true);
        cityBox.setDisable(false);
        cityBox.getItems().clear();
        String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        hotelq=new HotelQuery();
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        countryBox.getItems().addAll(countryq.getCountryNames());
        warningText.setVisible(false);
    }    
    
}
