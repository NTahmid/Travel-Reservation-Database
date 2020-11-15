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
public class StaffFXMLController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private HotelQuery hotelq;
    private CompanyQuery companyq;
    private PackageQuery packageq;
    private StaffQuery staffq;
    private String workplaceChoice;
    
    @FXML
    private Text warningText;
    
    @FXML
    private TextField staffIdField;

    @FXML
    private TextField staffNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField contactField;

    @FXML
    private ComboBox<String> workplaceBox;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private ComboBox<String> hotelBox;

    @FXML
    private ComboBox<String> packageBox;

    @FXML
    private Button enterButton;

    @FXML
    private Button backButton;
    
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
        String staffId=staffIdField.getText().trim();
        String staffName=staffNameField.getText().trim();
        String address=addressField.getText().trim();
        String contact=contactField.getText().trim();
        workplaceChoice=workplaceBox.getValue();
        String hotelName=null;
        String packageName=null;
        if(staffId.isEmpty()||staffName.isEmpty()||address.isEmpty()||contact.isEmpty()||workplaceChoice==null)
        {
            warningText.setVisible(true);
            return;
        }
        else if(workplaceChoice!=null)
        {
            if(workplaceChoice.equals("Hotels"))
                hotelName=hotelBox.getValue();
            else
                packageName=packageBox.getValue();
            if(workplaceChoice.equals("Hotels")&&hotelName==null)
            {
                warningText.setVisible(true);
                return;
            }
            else if(workplaceChoice.equals("Packages")&&packageName==null)
            {
                warningText.setVisible(true);
                return;
            }
            else
            {
                warningText.setVisible(false);
                if(workplaceChoice.equals("Hotels"))
                {   
                    hotelName=hotelBox.getValue();
                    staffq.InsertStaff(staffId, staffName, address, contact, hotelName, true);
                }
                else
                {
                    packageName=packageBox.getValue();
                    staffq.InsertStaff(staffId, staffName, address, contact, packageName, false);
                }
            }
        }
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
        packageBox.getItems().clear();
        packageBox.setVisible(false);
        packageBox.setDisable(true);
        workplaceBox.setVisible(false);
        workplaceBox.setDisable(true);
        
        String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
    }
    
    @FXML
    private void comboEvent2(ActionEvent action)
    {
        workplaceBox.setVisible(true);
        workplaceBox.setDisable(false);
        
        hotelBox.getItems().clear();
        hotelBox.setVisible(false);
        hotelBox.setDisable(true);
        
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        //hotelBox.getItems().addAll(hotelq.getHotelNames(countryName, cityName));
    }
    
    @FXML
    private void comboEvent3(ActionEvent action)
    {
        workplaceChoice=workplaceBox.getValue();
        
        hotelBox.getItems().clear();
        hotelBox.setVisible(true);
        hotelBox.setDisable(false);
        
        packageBox.getItems().clear();
        packageBox.setVisible(false);
        packageBox.setDisable(true);
        
        if(workplaceChoice.equals("Hotels"))
        {
            hotelBox.setPromptText("Select Hotel");
            String countryName=countryBox.getValue();
            String cityName=cityBox.getValue();
            hotelBox.getItems().addAll(hotelq.getHotelNames(countryName, cityName));
        }
        else if(workplaceChoice.equals("Packages"))
        {
            hotelBox.setPromptText("Select Company");
            String countryName=countryBox.getValue();
            String cityName=cityBox.getValue();
            hotelBox.getItems().addAll(companyq.getCompanyNames(countryName, cityName));
        }
            
    }
    
     @FXML
    private void comboEvent4(ActionEvent action)
    {   
        if(workplaceChoice.equals("Hotels"))
            return;
        String companyName=hotelBox.getValue();
        
        packageBox.getItems().clear();
        packageBox.setVisible(true);
        packageBox.setDisable(false);
        
        packageBox.getItems().addAll(packageq.getPackageNames(companyName));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        hotelq= new HotelQuery();
        companyq=new CompanyQuery();
        staffq=new StaffQuery();
        countryBox.getItems().addAll(countryq.getCountryNames());
        
        workplaceBox.getItems().addAll(
        "Hotels",
        "Packages");
        
        warningText.setVisible(false);
        
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        packageBox.setDisable(true);
        packageBox.setVisible(false);
        hotelBox.setDisable(true);
        hotelBox.setVisible(false);
        workplaceBox.setVisible(false);
        workplaceBox.setDisable(true);
    }    
    
}
