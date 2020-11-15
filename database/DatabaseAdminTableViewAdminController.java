/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class DatabaseAdminTableViewAdminController implements Initializable {
    
    ObservableList<AdminTable>data;
    private UserQuery userq;
        @FXML
    private Button backButton;

    @FXML
    private TableView<AdminTable> table;

    @FXML
    private TableColumn<AdminTable, String> c1;

    @FXML
    private TableColumn<AdminTable, String> c2;

    @FXML
    private TableColumn<AdminTable, String> c3;
    
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userq=new UserQuery();
        c1.setCellValueFactory(new PropertyValueFactory<>("adminUsername"));
        c2.setCellValueFactory(new PropertyValueFactory<>("adminName"));
        c3.setCellValueFactory(new PropertyValueFactory<>("password"));
         
       data = FXCollections.observableArrayList();
        List<List<String>> userDataList = userq.getAdminInfo();
        
            
            
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new AdminTable(row.get(0), row.get(1),row.get(2)));
        }
        table.setItems(data);
    }    
}    
    
