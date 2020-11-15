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
public class CompanyTableViewAdminController implements Initializable {
    
    private CompanyQuery companyq;
    private CountryQuery countryq;
    private CityQuery cityq;
    
    ObservableList<CompanyTable>data;
    
    @FXML
    private TableView<CompanyTable> table;
    
    @FXML
    private TableColumn<CompanyTable, String> c1;

    @FXML
    private TableColumn<CompanyTable, String> c2;

    @FXML
    private TableColumn<CompanyTable, String> c3;

    @FXML
    private TableColumn<CompanyTable, String> c4;

    @FXML
    private TableColumn<CompanyTable, String> c5;

    @FXML
    private Button backButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField contactField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField addressField;

    @FXML
    private Button contactButton;

    @FXML
    private Button nameButton;

    @FXML
    private Button addressButton;
    
    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;
    
    @FXML
    private void comboEvent(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        if(countryName.equals("None"))
        {   
            cityBox.getItems().clear();
            cityBox.setVisible(false);
            cityBox.setDisable(true);
            reinsertTableData(companyq.getCompanyInfo());
        }
        else
        {
            cityBox.getItems().clear();
            cityBox.setVisible(true);
            cityBox.setDisable(false);
            cityBox.getItems().addAll(cityq.getCities(countryName));
            cityBox.getItems().add("None");
            cityBox.setPromptText("Select City");
            reinsertTableData(companyq.getCompanyInfo(countryName));
        }
    }
    
    @FXML
    private void comboEvent1(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        if(cityName.equals("None"))
        {   
            reinsertTableData(companyq.getCompanyInfo(countryName));
        }
        else
        {
            reinsertTableData(companyq.getCompanyInfo(countryName,cityName));
        }
    }
    
    @FXML
    private void changeCompanyName(ActionEvent action)
    {
        CompanyTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newName=nameField.getText().trim();
        if(selectedItem==null||newName.isEmpty())
            return;
        String companyName=selectedItem.getCompanyName();
        companyq.newCompanyName(newName, companyName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(companyq.getCompanyInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(companyq.getCompanyInfo(countryName));
            }
            else
            {
                reinsertTableData(companyq.getCompanyInfo(countryName, cityName));
            }
        }
    }
    
    @FXML
    private void changeCompanyContact(ActionEvent action)
    {
        CompanyTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newContact=contactField.getText().trim();
        if(selectedItem==null||newContact.isEmpty())
            return;
        String companyName=selectedItem.getCompanyName();
        companyq.newCompanyContact(newContact, companyName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(companyq.getCompanyInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(companyq.getCompanyInfo(countryName));
            }
            else
            {
                reinsertTableData(companyq.getCompanyInfo(countryName, cityName));
            }
        }
    }
    
    @FXML
    private void changeCompanyAddress(ActionEvent action)
    {
        CompanyTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newAddress=addressField.getText().trim();
        if(selectedItem==null||newAddress.isEmpty())
            return;
        String companyName=selectedItem.getCompanyName();
        companyq.newCompanyAddress(newAddress, companyName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(companyq.getCompanyInfo());
        }
        else
        {
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
            {
                reinsertTableData(companyq.getCompanyInfo(countryName));
            }
            else
            {
                reinsertTableData(companyq.getCompanyInfo(countryName, cityName));
            }
        }
    }
    
     private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CompanyTable(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4)));
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
    private void deleteCompany(ActionEvent event)
    {
        CompanyTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String companyName=selectedItem.getCompanyName();
            companyq.deleteCompany(companyName);
            
            
            String countryName=countryBox.getValue();
            if(countryName==null||countryName.equals("None"))
            {
                reinsertTableData(companyq.getCompanyInfo());
            }
            else
            {
                String cityName=cityBox.getValue();
                if(cityName==null||cityName.equals("None"))
                {
                    reinsertTableData(companyq.getCompanyInfo(countryName));
                }
                else
                {
                    reinsertTableData(companyq.getCompanyInfo(countryName, cityName));
                }
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        companyq=new CompanyQuery();
        cityq=new CityQuery();
        countryq=new CountryQuery();
        countryBox.getItems().addAll(countryq.getCountryNames());
        
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        
        countryBox.getItems().add("None");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("address"));
        c3.setCellValueFactory(new PropertyValueFactory<>("contact"));
        c4.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        c5.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        data = FXCollections.observableArrayList();
         List<List<String>> userDataList = companyq.getCompanyInfo();
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new CompanyTable(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4)));
        }
        table.setItems(data);
    
    }    
    
}
