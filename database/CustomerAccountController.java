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
public class CustomerAccountController implements Initializable {

    @FXML
    private Text userNameText;

    @FXML
    private Button backButton;

    @FXML
    private Button hotelBookButton;

    @FXML
    private Button packageBookButton;

    @FXML
    private Button viewBookedHotelButton;

    @FXML
    private Button viewBookedPackagesButton;
    
    
    public void setUsername(String username)
    {
        userNameText.setText(username);
    }
    
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
    private void goToHotel(ActionEvent action)
    {
        try{        
                    String user=userNameText.getText();
                    //System.out.println(user);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerHotelBooking.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                    // System.out.println("Haha");
                    CustomerHotelBookingController c=loader.getController();
                    // System.out.println("Haha");
                    c.setUsername(user);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                  //   System.out.println("Haha");
                }catch(Exception e){System.out.println(e);}
    }
    
    @FXML
    private void goToPackage(ActionEvent action)
    {
        try{        
                    String user=userNameText.getText();
                    //System.out.println(user);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerPackageBooking.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                    // System.out.println("Haha");
                    CustomerPackageBookingController c=loader.getController();
                    // System.out.println("Haha");
                    c.setUsername(user);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                  //   System.out.println("Haha");
                }catch(Exception e){System.out.println(e);}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

}
    
