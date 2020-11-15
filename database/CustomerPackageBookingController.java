/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.net.URL;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class CustomerPackageBookingController implements Initializable {
    
    private CountryQuery countryq;
    private CityQuery cityq;
    private PackageQuery packageq;
    ObservableList<TourPackageTable>data;
    
    
    @FXML
    private DatePicker startDatePicker;
    
     public void setUsername(String username)
    {
        userNameText.setText(username);
    }
    
     @FXML
    private void getBack(ActionEvent action)
    {
            try {
                    
            
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerAccount.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    String user=userNameText.getText();
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                     //System.out.println("Haha");
                    CustomerAccountController c=loader.getController();
                     //System.out.println("Haha");
                    c.setUsername(user);
                    //System.out.println("Haha");
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
    private void gotoView(ActionEvent action)
    {       
            TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
            if(selectedItem==null)
                return;
            try {
                    //Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    //Scene s1=new Scene(root1);
                    //stage.setScene(s1);
                    //stage.show();
            
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerPackageSites.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    String user=userNameText.getText();
                    String packageId=selectedItem.getPackageName();
                    String pId=packageq.getPackageId(packageId);
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                     //System.out.println("Haha");
                    CustomerPackageSitesController c=loader.getController();
                     //System.out.println("Haha");
                    c.setUsername(user,pId);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
            
            
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    
    @FXML
    private void bookPackage(ActionEvent action)
    {       
            TourPackageTable selectedItem=table.getSelectionModel().getSelectedItem();
            if(selectedItem==null)
                return;
            String packageNumber=selectedItem.getAvailable();
            if(packageNumber.equals("0"))
                return;
            String startDate = startDatePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            String packageName=selectedItem.getPackageName();
            String pId=packageq.getPackageId(packageName);
            String user=userNameText.getText();
            packageq.InsertBookedPackage(pId, user, startDate);
           
            packageq.incrementPackageNumber(-1, packageName);
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
                    reinsertTableData(packageq.getPackageInfo(countryName,cityName));
            }
    }
    
    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> cityBox;

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
    private Button bookButton;

    @FXML
    private Button viewButton;

    @FXML
    private Text userNameText;
    
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
