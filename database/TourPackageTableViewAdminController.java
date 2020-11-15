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
public class TourPackageTableViewAdminController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private PackageQuery packageq;
    ObservableList<TourPackageTable>data;
    
    @FXML
    private Button backButton;
    
    @FXML
    private TableView<TourPackageTable> table;
    
    @FXML
    private TableColumn<TourPackageTable, String> c1;

    @FXML
    private TableColumn<TourPackageTable, String> c2;

    @FXML
    private TableColumn<TourPackageTable, String> c3;

    @FXML
    private TableColumn<TourPackageTable, String> c4;

    @FXML
    private TableColumn<TourPackageTable, String> c5;

    @FXML
    private TableColumn<TourPackageTable, String> c6;

    @FXML
    private TableColumn<TourPackageTable, String> c7;
    
    @FXML
    private Button deleteButton;

    @FXML
    private Button nameButton;

    @FXML
    private Button amountButton;

    @FXML
    private Button lengthButton;

    @FXML
    private Button priceButton;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

    @FXML
    private Button addSiteButton;

    @FXML
    private Button removeSiteButton;
    
     @FXML
    private TextField nameField;

    @FXML
    private TextField numberField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField priceField;

    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        if(dataList==null)
            return;
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new TourPackageTable(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5),row.get(6)));
        }
        table.setItems(data);
    }
    
    @FXML
    private void deletePackage(ActionEvent event)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            String packageName=selectedItem.getPackageName();
            packageq.deletePackage(packageName);
            
            
            String countryName=countryBox.getValue();
            if(countryName==null||countryName.equals("None"))
            {
                reinsertTableData(packageq.getPackageInfo());
            }
            else
            {
                String cityName=cityBox.getValue();
                if(cityName==null||cityName.equals("None"))
                {
                    reinsertTableData(packageq.getPackageInfo(countryName));
                }
                else
                {
                    reinsertTableData(packageq.getPackageInfo(countryName, cityName));
                }
            }
        }
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
            reinsertTableData(packageq.getPackageInfo());
        }
        else
        {
            cityBox.getItems().clear();
            cityBox.setVisible(true);
            cityBox.setDisable(false);
            cityBox.getItems().addAll(cityq.getCities(countryName));
            cityBox.getItems().add("None");
            cityBox.setPromptText("Select City");
            reinsertTableData(packageq.getPackageInfo(countryName));
        }
    }
    
    @FXML
    private void viewSites(ActionEvent event)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        String packageName=selectedItem.getPackageName();
        String countryName=selectedItem.getCountryName();
        String cityName=selectedItem.getCityName();
        String cityId=cityq.getCityId(countryName, cityName);
        String packageId=packageq.getPackageId(packageName);
        System.out.println(cityId);
        System.out.println(packageId);
        try{        
                    
                    //System.out.println(user);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("PackageSitesAdmin.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                    // System.out.println("Haha");
                    PackageSitesAdminController c=loader.getController();
                    // System.out.println("Haha");
                    c.setStrings(cityId,packageId);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                  //   System.out.println("Haha");
                }catch(Exception e){System.out.println(e);}
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
    private void comboEvent1(ActionEvent action)
    {
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        if(cityName.equals("None"))
        {   
            reinsertTableData(packageq.getPackageInfo(countryName));
        }
        else
        {
            reinsertTableData(packageq.getPackageInfo(countryName,cityName));
        }
    }
    
    @FXML
    private void incrementPackageDays(ActionEvent action)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newDays=lengthField.getText().trim();
        if(selectedItem==null||newDays.isEmpty())
            return;
        int cap=Integer.parseInt(newDays);
        String packageName=selectedItem.getPackageName();
        packageq.incrementPackageDays(cap, packageName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(packageq.getPackageInfo());
        }
        else
        {   
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
                reinsertTableData(packageq.getPackageInfo(countryName));
            else
                reinsertTableData(packageq.getPackageInfo(countryName, cityName));
        }
    }
    @FXML
    private void incrementPackageNumber(ActionEvent action)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newNum=numberField.getText().trim();
        if(selectedItem==null||newNum.isEmpty())
            return;
        int cap=Integer.parseInt(newNum);
        String packageName=selectedItem.getPackageName();
        packageq.incrementPackageNumber(cap, packageName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(packageq.getPackageInfo());
        }
        else
        {   
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
                reinsertTableData(packageq.getPackageInfo(countryName));
            else
                reinsertTableData(packageq.getPackageInfo(countryName, cityName));
        }
    }
    @FXML
    private void changePackagePrice(ActionEvent action)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newNum=priceField.getText().trim();
        if(selectedItem==null||newNum.isEmpty())
            return;
        int cap=Integer.parseInt(newNum);
        String packageName=selectedItem.getPackageName();
        packageq.changePackagePrice(cap, packageName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(packageq.getPackageInfo());
        }
        else
        {   
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
                reinsertTableData(packageq.getPackageInfo(countryName));
            else
                reinsertTableData(packageq.getPackageInfo(countryName, cityName));
        }
    }
    @FXML
    private void changePackageName(ActionEvent action)
    {
        TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
        String newName=nameField.getText().trim();
        if(selectedItem==null||newName.isEmpty())
            return;
        System.out.println(newName);
        String packageName=selectedItem.getPackageName();
        packageq.changePackageName(newName, packageName);
        String countryName=countryBox.getValue();
        if(countryName==null||countryName.equals("None"))
        {
            reinsertTableData(packageq.getPackageInfo());
        }
        else
        {   
            String cityName=cityBox.getValue();
            if(cityName==null||cityName.equals("None"))
                reinsertTableData(packageq.getPackageInfo(countryName));
            else
                reinsertTableData(packageq.getPackageInfo(countryName, cityName));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        countryq=new CountryQuery();
        cityq=new CityQuery();
        packageq= new PackageQuery();
        
        countryBox.getItems().addAll(countryq.getCountryNames());
        
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        
        countryBox.getItems().add("None");
        
        c1.setCellValueFactory(new PropertyValueFactory<>("packageName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("price"));
        c3.setCellValueFactory(new PropertyValueFactory<>("available"));
        c4.setCellValueFactory(new PropertyValueFactory<>("days"));
        c5.setCellValueFactory(new PropertyValueFactory<>("companyName"));
         c6.setCellValueFactory(new PropertyValueFactory<>("cityName"));
          c7.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        data = FXCollections.observableArrayList();
        List<List<String>> userDataList = packageq.getPackageInfo();
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new TourPackageTable(row.get(0), row.get(1),row.get(2),row.get(3),row.get(4),row.get(5),row.get(6)));
        }
        table.setItems(data);
    }    
    
}
