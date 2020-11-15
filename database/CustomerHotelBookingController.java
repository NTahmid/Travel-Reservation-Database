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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nafis
 */
public class CustomerHotelBookingController implements Initializable {
    
    private HotelTable h;
    private CountryQuery countryq;
    private HotelQuery hotelq;
    private CityQuery cityq;
    
    private String hotel;
    @FXML
    private Text userNameText;
    
    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private Button roomViewButton;

    @FXML
    private TextField minRatingField;

    @FXML
    private TextField maxRatingField;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> cityBox;
    
    ObservableList<HotelTable>data;
    
    @FXML
    private TableView<HotelTable> table;
    
    @FXML
    private Button backButton;

    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
                    //Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    //Scene s1=new Scene(root1);
                    //stage.setScene(s1);
                    //stage.show();
            
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
    
    
    @FXML
    private void selectHotel(ActionEvent event)
    {
        HotelTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        else
        {
            hotel=selectedItem.getHotelName();
            String user=userNameText.getText();
            try{        
                    
                    //System.out.println(user);
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerHotelRoom.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                     //System.out.println("Haha");
                    CustomerHotelRoomController c=loader.getController();
                     //System.out.println("Haha");
                    c.setUsername(user);
                    c.setHotelName(hotel);
                    //System.out.println("Haha");
                    Scene s1=new Scene(root1);
                    stage.setScene(s1);
                    stage.show();
                     //System.out.println("Haha");
                }catch(Exception e){System.out.println(e);}
        }
    }
    
    private void reinsertTableData(List<List<String> >dataList)
    {
        table.getItems().clear();
        data.clear();
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }
    
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
        String minR=minRatingField.getText().trim();
        String maxR=maxRatingField.getText().trim();
        String countryName=countryBox.getValue();
        String cityName=cityBox.getValue();
        double minRating,maxRating;
        if(minR.isEmpty())
            minRating=1;
        if(maxR.isEmpty())
            maxRating=5;
        try{
            minRating=Double.parseDouble(minR);
        }catch(Exception e)
        {
            minRating=1;
        }
        try{
            maxRating=Double.parseDouble(maxR);
        }catch(Exception e)
        {
            maxRating=5;
        }
        List<List<String>> userDataList=null;
        if(countryName==null||countryName.equals("None"))
        {
            userDataList=hotelq.getAllHotelInfo(minRating, maxRating);
        }
        else
        {
            if(cityName==null||cityName.equals("None"))
                userDataList=hotelq.getAllHotelInfo(minRating, maxRating, countryName);
            else
                userDataList=hotelq.getAllHotelInfo(minRating, maxRating, countryName, cityName);
        }
        
        reinsertTableData(userDataList);
    }
    
    private void initializeColumns()
    {
        TableColumn<HotelTable, String> c1 = new TableColumn<>("Hotel Name");
        c1.setMinWidth(100);
        c1.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        //firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
//        firstNameCol.setOnEditCommit(
//                (TableColumn.CellEditEvent<User, String> t) ->
//                t.getTableView().getItems().get(t.getTablePosition().getRow()).setUserName(t.getNewValue())
//        );

        TableColumn<HotelTable, String> c2 = new TableColumn<>("Country");
        c2.setMinWidth(100);
        c2.setCellValueFactory(new PropertyValueFactory<>("countryName"));
        //lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*lastNameCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastName(t.getNewValue())
         );*/

        TableColumn<HotelTable, String> c3 = new TableColumn<>("City");
        c3.setMinWidth(200);
        c3.setCellValueFactory(new PropertyValueFactory<>("cityName"));
        //emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*emailCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue())
         );*/
         TableColumn<HotelTable, String> c4 = new TableColumn<>("Rating");
        c4.setMinWidth(200);
        c4.setCellValueFactory(new PropertyValueFactory<>("rating"));
        
        TableColumn<HotelTable, String> c5 = new TableColumn<>("Review");
        c5.setMinWidth(200);
        c5.setCellValueFactory(new PropertyValueFactory<>("review"));
        
        TableColumn<HotelTable, String> c6 = new TableColumn<>("Contact No");
        c6.setMinWidth(200);
        c6.setCellValueFactory(new PropertyValueFactory<>("contact"));
        
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6);
    }
    
    
    
    public void setUsername(String username)
    {
        userNameText.setText(username);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        countryq=new CountryQuery();
        cityq=new CityQuery();
        cityBox.setVisible(false);
        cityBox.setDisable(true);
        hotelq=new HotelQuery();
        countryBox.getItems().addAll(countryq.getCountryWithCities());
        countryBox.getItems().add("None");
        
        initializeColumns();
        data = FXCollections.observableArrayList();
            List<List<String>> userDataList = hotelq.getAllHotelInfo();
        
            
            
        for (List<String> row : userDataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        
        
        
        /*
        c1.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        c2.setCellValueFactory(new PropertyValueFactory<>("countryName"));
         c3.setCellValueFactory(new PropertyValueFactory<>("cityName"));
          c4.setCellValueFactory(new PropertyValueFactory<>("rating"));
           c5.setCellValueFactory(new PropertyValueFactory<>("contact"));
            //c6.setCellValueFactory(new PropertyValueFactory<>("contact"));
        */ 
            
        table.setItems(data);
        
    }    
    
}
