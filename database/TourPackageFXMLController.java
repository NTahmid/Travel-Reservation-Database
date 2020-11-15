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
public class TourPackageFXMLController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private CompanyQuery companyq;
    private PackageQuery packageq;
     @FXML
    private Button backButton;
     
    @FXML
    private TextField packageNameField;

    @FXML
    private TextField packageIdField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField packageNumberField;

    @FXML
    private Button enterButton;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private ComboBox<String> companyBox;

    @FXML
    private Text warningText;
    
    @FXML
    private TextField daysField;
    
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
        companyBox.getItems().clear();
        companyBox.setVisible(false);
        companyBox.setDisable(true);
        String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
    }
    
    @FXML
    private void comboEvent2(ActionEvent action)
    {
        companyBox.setVisible(true);
        companyBox.setDisable(false);
        companyBox.getItems().clear();
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        companyBox.getItems().addAll(companyq.getCompanyNames(countryName, cityName));
    }
    
    @FXML
    private void enterData(ActionEvent action)
    {
        String packageId=packageIdField.getText().trim();
        String packageName=packageNameField.getText().trim();
        String prc=priceField.getText().trim();
        String num=packageNumberField.getText().trim();
        String companyName=companyBox.getValue();
        String dayNum=daysField.getText().trim();
        if(packageId.isEmpty()||packageName.isEmpty()||prc.isEmpty()||companyName==null||num.isEmpty()||dayNum.isEmpty())
        {
            warningText.setVisible(true);
            return;
        }
        warningText.setVisible(false);
        int price=Integer.parseInt(prc);
        int packageNumber=Integer.parseInt(num);
        int days=Integer.parseInt(dayNum);
        packageq.InsertPackage(packageId, packageName, companyName, price, packageNumber, days);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        companyq=new CompanyQuery();
        packageq=new PackageQuery();
        countryBox.getItems().addAll(countryq.getCountryNames());
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        companyBox.setVisible(false);
        companyBox.setDisable(true);
        warningText.setVisible(false);
    }    
    
}
