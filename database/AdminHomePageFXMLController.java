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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class AdminHomePageFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   @FXML
    private ComboBox<String> tableBox;

    @FXML
    private Button insertButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button selectButton;

    @FXML
    private Text warningText;
    
    @FXML
    private Text warningText1;
    
    @FXML
    private Button backButton;
    
    @FXML
    private void getBack(ActionEvent action)
    {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
     @FXML
    private void selectAction(ActionEvent event) {
        String selectedTable=tableBox.getValue();
        String window=null;
        if(selectedTable==null)
        {
            warningText1.setVisible(true);
            return;
        }
        if(selectedTable.equals("Customer"))
            window="CustomerTableViewAdmin";
        else if(selectedTable.equals("Country"))
            window="CountryViewTableAdmin";
        else if(selectedTable.equals("Database Admin"))
            window="DatabaseAdminTableViewAdmin";
        else if(selectedTable.equals("City"))
            window="CityTableViewAdmin";
        else if(selectedTable.equals("Company"))
            window="CompanyTableViewAdmin";
        else if(selectedTable.equals("Hotel"))
            window="HotelTableViewAdmin";
        else if(selectedTable.equals("Sites"))
            window="SitesTableViewAdmin";
        else if(selectedTable.equals("Tour Packages"))
            window="TourPackageTableViewAdmin";
        window+=".fxml";
        try {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root1 = FXMLLoader.load(getClass().getResource(window));
        Scene s1=new Scene(root1);
        stage.setScene(s1);
        stage.show();
        }
        catch(Exception e){ System.out.println(e);}  
    
    }
    
    @FXML
    private void InsertAction(ActionEvent event)
    {
        String selectedTable=tableBox.getValue();
        if(selectedTable==null)
        {
            warningText1.setVisible(true);
            return;
        }
        String windowString=null;
        if(selectedTable.equals("Country"))
            windowString="Country";
        else if(selectedTable.equals("City"))
            windowString="City";
        else if(selectedTable.equals("Company"))
            windowString="Company";
        else if(selectedTable.equals("Hotel"))
            windowString="Hotel";
        else if(selectedTable.equals("Hotel Rooms"))
            windowString="HotelRoom";
        else if(selectedTable.equals("Sites"))
            windowString="Sites";
        else if(selectedTable.equals("Staff"))
            windowString="Staff";
        else if(selectedTable.equals("Tour Packages"))
            windowString="TourPackage";
        else
        {
            warningText.setVisible(true);
            return;
        }
        windowString+="FXML.fxml";
        try {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root1 = FXMLLoader.load(getClass().getResource(windowString));
        Scene s1=new Scene(root1);
        stage.setScene(s1);
        stage.show();
        }
        catch(Exception e){ System.out.println(e);}  
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warningText.setVisible(false);
        warningText1.setVisible(false);
        tableBox.getItems().addAll(
                "Country",
                "City",
                "Company",
                "Customer",
                "Database Admin",
                "Hotel",
                "Hotel Rooms",
                "Sites",
                "Staff",
                "Tour Packages"
        );
    }    
    
}
