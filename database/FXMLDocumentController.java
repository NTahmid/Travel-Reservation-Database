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


/**
 *
 * @author Nafis
 */
public class FXMLDocumentController implements Initializable {
    
   @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;
    
    @FXML
    private void handleRegisterAction(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("RegisterCustomerFXML.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    @FXML
    private void handleSignAction(ActionEvent event) {
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
