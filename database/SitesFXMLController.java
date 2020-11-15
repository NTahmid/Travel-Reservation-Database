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
public class SitesFXMLController implements Initializable {
    private CountryQuery countryq;
    private CityQuery cityq;
    private SitesQuery sitesq;
    @FXML
    private Button backButton;

    @FXML
    private TextField siteNameField;

    @FXML
    private TextField siteIdField;

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
    private void comboEvent(ActionEvent action)
    {
        cityBox.setVisible(true);
        cityBox.setDisable(false);
        cityBox.getItems().clear();
        String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
    }
    
    @FXML
    private void enterData(ActionEvent action)
    {
        String siteName=siteNameField.getText().trim();
        String siteId=siteIdField.getText().trim();
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        if(siteName.isEmpty()||siteId.isEmpty()||countryName==null||cityName==null)
        {
            warningText.setVisible(true);
            return;
        }
        warningText.setVisible(false);
        sitesq.InsertSites(siteId, siteName, countryName, cityName);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        sitesq=new SitesQuery();
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        countryBox.getItems().addAll(countryq.getCountryNames());
        warningText.setVisible(false);
    }    
    
}
