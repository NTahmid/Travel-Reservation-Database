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
public class CustomerHotelRoomController implements Initializable {
    
    private boolean init;
    
    private String hotelName;
    private HotelQuery hotelq;
    @FXML
    private Button backButton;
    
    @FXML
    private DatePicker checkInPicker;

    @FXML
    private DatePicker checkOutPicker;

    @FXML
    private TableView<HotelRoomTable> table;

    @FXML
    private Button bookButton;

    @FXML
    private Text userNameText;
    
    private ObservableList<HotelRoomTable>data;
    
    @FXML
    private void getBack(ActionEvent action)
    {
            try {
                    
            
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("CustomerHotelBooking.fxml"));
                    
                    //Loader.setLoaction(getClass().getResource("CustomerAccount.fxml"));
                    //Loader.load();
                    //System.out.println("Haha");
                    String user=userNameText.getText();
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    //Parent root1 = FXMLLoader.load(getClass().getResource("CustomerAccount.fxml"));
                    Parent root1 = (Parent)loader.load();
                     //System.out.println("Haha");
                    CustomerHotelBookingController c=loader.getController();
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
        for (List<String> row : dataList)
        {
            //data.add(new HotelTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
            
            data.add(new HotelRoomTable(row.get(0), row.get(1), row.get(2),row.get(3),row.get(4),row.get(5)));
        }
        table.setItems(data);
    }
    
    private void initializeColumns()
    {
        TableColumn<HotelRoomTable, String> c1 = new TableColumn<>("Hotel Name");
        c1.setMinWidth(100);
        c1.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
        //firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
//        firstNameCol.setOnEditCommit(
//                (TableColumn.CellEditEvent<User, String> t) ->
//                t.getTableView().getItems().get(t.getTablePosition().getRow()).setUserName(t.getNewValue())
//        );

        TableColumn<HotelRoomTable, String> c2 = new TableColumn<>("Room Name");
        c2.setMinWidth(100);
        c2.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        //lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*lastNameCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setLastName(t.getNewValue())
         );*/

        TableColumn<HotelRoomTable, String> c3 = new TableColumn<>("Capacity");
        c3.setMinWidth(200);
        c3.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
        //emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        /*emailCol.setOnEditCommit(
         (TableColumn.CellEditEvent<Person, String> t) ->
         t.getTableView().getItems().get(t.getTablePosition().getRow()).setEmail(t.getNewValue())
         );*/
         TableColumn<HotelRoomTable, String> c4 = new TableColumn<>("Description");
        c4.setMinWidth(200);
        c4.setCellValueFactory(new PropertyValueFactory<>("roomDescription"));
        
        TableColumn<HotelRoomTable, String> c5 = new TableColumn<>("Price");
        c5.setMinWidth(200);
        c5.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        TableColumn<HotelRoomTable, String> c6 = new TableColumn<>("Room Id");
        c6.setMinWidth(200);
        c6.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        
        
        table.getColumns().addAll(c1,c2,c3,c4,c5,c6);
    }
    
    @FXML
    private void searchAction(ActionEvent event)
    {
        String checkindate = checkInPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String checkoutdate= checkOutPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        System.out.println(checkindate);
        System.out.println(checkoutdate);
        if(init==true)
        {
            initializeColumns();
            init=false;
        }
        
        reinsertTableData(hotelq.getHotelRooms(checkindate, checkoutdate, hotelName));
    }
    
    
    @FXML
    private void bookRoom(ActionEvent event)
    {
        HotelRoomTable selectedItem=table.getSelectionModel().getSelectedItem();
        if(selectedItem==null)
            return;
        String user=userNameText.getText();
        String roomId=selectedItem.getRoomId();
        String checkindate = checkInPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String checkoutdate= checkOutPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        
        hotelq.BookRoom(user, roomId, checkindate, checkoutdate);
        
        reinsertTableData(hotelq.getHotelRooms(checkindate, checkoutdate, hotelName));
    }
    
     public void setUsername(String username)
    {
        userNameText.setText(username);
    }
    
     public void setHotelName(String hotel)
     {
         hotelName=hotel;
         System.out.println(hotelName);
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init=true;
        data = FXCollections.observableArrayList();
        hotelq=new HotelQuery();
    }    
    
}
