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
public class CityTableViewAdminController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    @FXML
    private TableView<CityTable> table;

    @FXML
    private TableColumn<CityTable, String> c1;

    @FXML
    private TableColumn<CityTable, String> c2;

    @FXML
    private TableColumn<CityTable, String> c3;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private Button deleteButton;

    @FXML
    private Button changeButton;

    @FXML
    private TextField nameField;
    
    ObservableList<CityTable>data;
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        if(countryName.equals("None"))
            reinsertTableData(cityq.getCityInfo());
        else
            reinsertTableData(cityq.getCityInfo(countryName));
    }
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CityTable(row.get(0), row.get(1),row.get(2)));
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
    
    
    @FXML
    private void deleteCity(ActionEvent event)
    {
        CityTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String cityId=selectedItem.getCityId();
            cityq.deleteCity(cityId);
            String countryName=countryBox.getValue();
            if(countryName==null||countryName.equals("None"))
                reinsertTableData(cityq.getCityInfo());
            else
                reinsertTableData(cityq.getCityInfo(countryName));
        }
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        countryBox.getItems().addAll(countryq.getCountryNames());
        countryBox.getItems().add("None");
        c1.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        c3.setCellValueFactory(new PropertyValueFactory<>("cityId"));
        data = FXCollections.observableArrayList();
        List<List<String>> userDataList = cityq.getCityInfo();
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CityTable(row.get(0), row.get(1),row.get(2)));
        }
        table.setItems(data);
    }    
    
}
