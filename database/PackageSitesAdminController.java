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
public class PackageSitesAdminController implements Initializable {
    
    private boolean init;
    private String cityId,packageId;
    private SitesQuery sitesq;
    ObservableList<PackageSitesTable>data;
    public void setStrings(String cId,String pId)
    {
        cityId=cId;
        packageId=pId;
    }
    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent root1 = FXMLLoader.load(getClass().getResource("TourPackageTableViewAdmin.fxml"));
            Scene s1=new Scene(root1);
            stage.setScene(s1);
            stage.show();
            }
            catch(Exception e){ System.out.println(e);}
    }
    
    //PackageSitesTable, String
    
    //CountryTable, String
    @FXML
    private TableView<PackageSitesTable> table;

    
    
    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<String> operationBox;
    
    @FXML
    private void Operate(ActionEvent event)
    {
        String operation=operationBox.getValue();
        PackageSitesTable selectedItem=table.getSelectionModel().getSelectedItem();
        System.out.println(cityId+" "+packageId);
        if(operation==null||selectedItem==null)
            return;
        String siteId=selectedItem.getSitesId();
        if(operation.equals("Add"))
        {
            sitesq.InsertPackageSites(siteId, packageId);
            reinsertTableData(sitesq.getAddPackageSitesInfo(cityId, packageId));
        }
        else
        {
            sitesq.deletePackageSite(siteId, packageId);
            reinsertTableData(sitesq.getRemovePackageSitesInfo(cityId, packageId));
        }
    }
    
    
    
    
    @FXML
    private void ComboEvent(ActionEvent event)
    {
        String operation=operationBox.getValue();
        System.out.println(cityId+" "+packageId);
        if(operation.equals("Add"))
        {
            reinsertTableData(sitesq.getAddPackageSitesInfo(cityId, packageId));
            
        }
        else if(operation.equals("Remove"))
        {
            reinsertTableData(sitesq.getRemovePackageSitesInfo(cityId, packageId));
        }
        addButton.setText(operation);
    }
    
    private void reinsertTableData(List<List<String> >dataList)
    {   
        System.out.println("Haha");
       
        
             table.getItems().clear();
             data.clear();
        
        if(dataList==null)
            return;
        System.out.println("HahaHO");
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new PackageSitesTable(row.get(0), row.get(1)));
        }
        table.setItems(data);
    }
    
    
    private void initializeColumns()
    {
        TableColumn<PackageSitesTable, String> c1 = new TableColumn<>("Site Id");
        c1.setMinWidth(100);
        c1.setCellValueFactory(new PropertyValueFactory<>("sitesId"));
        //firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
//        firstNameCol.setOnEditCommit(
//                (TableColumn.CellEditEvent<User, String> t) ->
//                t.getTableView().getItems().get(t.getTablePosition().getRow()).setUserName(t.getNewValue())
//        );

        TableColumn<PackageSitesTable, String> c2 = new TableColumn<>("Site Name");
        c2.setMinWidth(100);
        c2.setCellValueFactory(new PropertyValueFactory<>("sitesName"));
        //lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*lastNameCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastName(t.getNewValue())
         );*/

       
        
        table.getColumns().addAll(c1,c2);
    }
    
    
    
    @FXML
    private void Load(ActionEvent event)
    {   
        if(init==false)
            return;
        System.out.println("Hoho");
        List<List<String>> userDataList = sitesq.getSites(cityId);
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new PackageSitesTable(row.get(0), row.get(1)));
        }
        table.setItems(data);
        init=false;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        sitesq=new SitesQuery();
        operationBox.getItems().addAll("Add",
                "Remove");
        
        initializeColumns();
        data = FXCollections.observableArrayList();
        
        //System.out.println(cityId);
        
        init=true;
        
        
        
    }    
    
}
