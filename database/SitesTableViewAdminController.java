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
public class SitesTableViewAdminController implements Initializable {
    
    ObservableList<SitesTable>data;
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private SitesQuery sitesq;
    @FXML
    private TableView<SitesTable> table;

    @FXML
    private TableColumn<SitesTable, String> c1;

    @FXML
    private TableColumn<SitesTable, String> c2;

    @FXML
    private TableColumn<SitesTable, String> c3;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private Button deleteButton;
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        if(dataList==null)
            return;
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new SitesTable(row.get(0), row.get(1),row.get(2)));
        }
        table.setItems(data);
    }
    
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        if(countryName.equals("None"))
        {   
            cityBox.getItems().clear();
            cityBox.setVisible(false);
            cityBox.setDisable(true);
            reinsertTableData(sitesq.getSitesInfo());
        }
        else
        {
            cityBox.getItems().clear();
            cityBox.setVisible(true);
            cityBox.setDisable(false);
            cityBox.getItems().addAll(cityq.getCities(countryName));
            cityBox.getItems().add("None");
            cityBox.setPromptText("Select City");
            reinsertTableData(sitesq.getSitesInfo(countryName));
        }
    }
    
    @FXML
    private void comboEvent1(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        if(cityName.equals("None"))
        {   
            reinsertTableData(sitesq.getSitesInfo(countryName));
        }
        else
        {
            reinsertTableData(sitesq.getSitesInfo(countryName,cityName));
        }
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
    private void deleteSites(ActionEvent event)
    {
        SitesTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String sitesName=selectedItem.getSitesName();
            sitesq.deleteSite(sitesName);
            
            
            String countryName=countryBox.getValue();
            if(countryName==null||countryName.equals("None"))
            {
                reinsertTableData(sitesq.getSitesInfo());
            }
            else
            {
                String cityName=cityBox.getValue();
                if(cityName==null||cityName.equals("None"))
                {
                    reinsertTableData(sitesq.getSitesInfo(countryName));
                }
                else
                {
                    reinsertTableData(sitesq.getSitesInfo(countryName, cityName));
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        sitesq=new SitesQuery();
        
        countryBox.getItems().addAll(countryq.getCountryNames());
        
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        
        countryBox.getItems().add("None");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("sitesName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        c3.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        
        data = FXCollections.observableArrayList();
        List<List<String>> userDataList = sitesq.getSitesInfo();
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new SitesTable(row.get(0), row.get(1),row.get(2)));
        }
        table.setItems(data);
        
    }    
    
}
