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
import javafx.scene.text.*;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class RegisterCustomerFXMLController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private UserQuery userq;
    @FXML
    private Text warningText;

    @FXML
    private Button backButton;
    
     @FXML
    private TextField nameField;

    @FXML
    private TextField userField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField;

    @FXML
    private PasswordField passField1;

    @FXML
    private ComboBox<String> countryField;

    @FXML
    private ComboBox<String> cityField;

    @FXML
    private Button registerButton;
    
    @FXML
    private void handleRegisterAction(ActionEvent event)
    {
        String username=userField.getText();
        String customerName=nameField.getText().trim();
        String address=addressField.getText().trim();
        String contact=contactField.getText().trim();
        String password=passField1.getText();
        String countryName=countryField.getValue();
        String cityName=cityField.getValue();
        
        
        if(username.isEmpty()||customerName.isEmpty()||address.isEmpty()||contact.isEmpty()||password.isEmpty()||countryName==null||cityName==null)
        {
            warningText.setVisible(true);
            return;
        }
        
        System.out.println(username+" "+customerName+" "+address+" "+contact+" "+password+" "+countryName+" "+cityName);
        warningText.setVisible(false);
        userq.CustomerRegistration(username, customerName, address, password, contact, countryName, cityName);
    }
    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
     @FXML
    private void comboEvent(ActionEvent action)
    {
        cityField.setVisible(true);
        cityField.setDisable(false);
        cityField.getItems().clear();
        String countryName=countryField.getValue();
        cityField.getItems().addAll(cityq.getCities(countryName));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODOinitialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        userq=new UserQuery();
        warningText.setVisible(false);
        cityField.setVisible(false);
        cityField.setDisable(true);
        countryField.getItems().addAll(countryq.getCountryWithCities());
    }    
    
}
