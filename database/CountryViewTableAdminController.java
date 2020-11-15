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
public class CountryViewTableAdminController implements Initializable {
    
     private CountryQuery countryq;
    
     ObservableList<CountryTable>data;
     
    @FXML
    private TableView<CountryTable> table;

    @FXML
    private TableColumn<CountryTable, String> c1;

    @FXML
    private TableColumn<CountryTable, String> c2;
    
    @FXML
    private Button deleteButton;

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
    
     private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CountryTable(row.get(0), row.get(1)));
        }
        table.setItems(data);
    }
    
    @FXML
    private void deleteCountry(ActionEvent event)
    {
        CountryTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String countryId=selectedItem.getCountryId();
            countryq.deleteCountry(countryId);
            reinsertTableData(countryq.getCountryInfo());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        countryq=new CountryQuery();
        c1.setCellValueFactory(new PropertyValueFactory<>("countryId"));
        c2.setCellValueFactory(new PropertyValueFactory<>("countryName"));
         
       data = FXCollections.observableArrayList();
        List<List<String>> userDataList = countryq.getCountryInfo();
        
            
            
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CountryTable(row.get(0), row.get(1)));
        }
        table.setItems(data);
    }    
    
}
