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
public class CountryFXMLController implements Initializable {

    @FXML
    private TextField countryNameField;

    @FXML
    private TextField countryIdField;

    @FXML
    private Button enterButton;

    @FXML
    private Button backButton;

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
        String countryname=countryNameField.getText().trim();
        String countryId=countryIdField.getText().trim();
        if(countryname.isEmpty()||countryId.isEmpty())
        {
            warningText.setVisible(true);
            return;
        }
        warningText.setVisible(false);
        CountryQuery cq=new CountryQuery();
        cq.InsertCountry(countryname, countryId);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warningText.setVisible(false);
    }    
    
}
