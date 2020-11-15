/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.*;
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
public class LoginPageFXMLController implements Initializable {

   @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> typeCombo;

    @FXML
    private Button loginButton;

    @FXML
    private Button backButton;
    
    @FXML
    private Text warningText;
    
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
    private void handleLoginAction(ActionEvent action)
    {
        String user=userField.getText();
        String password=passwordField.getText();
        String usertype=typeCombo.getValue();
        if(user.isEmpty()||password.isEmpty()||usertype==null)
        {
            warningText.setVisible(true);
            return;
        }
        UserQuery uq=new UserQuery();
        if(uq.checkUserExists(user,password,usertype)==true)
        {
            if(usertype.equals("Admin"))
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
            else if(usertype.equals("Customer"))
            {
                try{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                    CustomerAccountController c=loader.getController();
                    c.setUsername(user);
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                
                }catch(Exception e){System.out.println(e);}
            }
        
        }
        else
            System.out.println("No such user exists");
                    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typeCombo.getItems().addAll(
                "Admin",
                "Customer");
    }    
    
}
