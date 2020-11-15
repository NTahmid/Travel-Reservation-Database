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
public class CustomerTableViewAdminController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private UserQuery userq;
    
    @FXML
    private TableView<CustomerTable> table;

    
    @FXML
    private TableColumn<CustomerTable, String> c1;

    @FXML
    private TableColumn<CustomerTable, String> c2;

    @FXML
    private TableColumn<CustomerTable, String> c3;

    @FXML
    private TableColumn<CustomerTable, String> c4;

    @FXML
    private TableColumn<CustomerTable, String> c5;

    @FXML
    private TableColumn<CustomerTable, String> c6;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private Button searchButton;
    
    ObservableList<CustomerTable>data;
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        if(countryName.equals("None")||countryName==null)
        {   
            cityBox.getItems().clear();
            cityBox.setVisible(false);
            cityBox.setDisable(true);
            return;
        }
            
        cityBox.setVisible(true);
        cityBox.setDisable(false);
        cityBox.getItems().clear();
        //String countryName=countryBox.getValue();
        cityBox.getItems().addAll(cityq.getCities(countryName));
        cityBox.getItems().add("None");
    }
    
    @FXML
    private void searchHotel(ActionEvent event)
    {
        
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        
        List<List<String>> userDataList=null;
        if(countryName==null||countryName.equals("None"))
        {
            userDataList=userq.getCustomerInfo();
        }
        else
        {
            if(cityName==null||cityName.equals("None"))
                userDataList=userq.getCustomerInfo(countryName);
            else
                userDataList=userq.getCustomerInfo(countryName, cityName);
        }
        
        reinsertTableData(userDataList);
    }
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CustomerTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }
    
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
        // TODO
        countryq=new CountryQuery();
        cityq=new CityQuery();
        userq=new UserQuery();
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        countryBox.getItems().addAll(countryq.getCountryWithCities());
        countryBox.getItems().add("None");
    
        c1.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("customerUsername"));
         c3.setCellValueFactory(new PropertyValueFactory<>("address"));
          c4.setCellValueFactory(new PropertyValueFactory<>("contact"));
           c5.setCellValueFactory(new PropertyValueFactory<>("countryName"));
            c6.setCellValueFactory(new PropertyValueFactory<>("cityName"));
       data = FXCollections.observableArrayList();
        List<List<String>> userDataList = userq.getCustomerInfo();
        
            
            
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CustomerTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }    
    
}
