/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import javafx.beans.property.SimpleStringProperty;


public class HotelRoomTable {
    private final SimpleStringProperty hotelName;
    private final SimpleStringProperty roomName;
    private final SimpleStringProperty roomCapacity;
    private final SimpleStringProperty roomDescription;
    private final SimpleStringProperty price;
    private final SimpleStringProperty roomId;
    HotelRoomTable(String hName, String countryN, String cityN,String r,String rev,String s) {
        this.hotelName = new SimpleStringProperty(hName);
        this.roomName = new SimpleStringProperty(countryN);
        this.roomCapacity = new SimpleStringProperty(cityN);
        this.roomDescription = new SimpleStringProperty(r);
        this.price = new SimpleStringProperty(rev);
        this.roomId= new SimpleStringProperty(s); 
    }

    public String getRoomId() {
        return roomId.get();
    }
    

    public String getHotelName() {
        return hotelName.get();
    }
    
    public void setHotelName(String s) {
        hotelName.set(s);
    }
    
    public String getRoomName() {
        return roomName.get();
    }
    
    public void setRoomName(String s) {
        roomName.set(s);
    }
    
    public String getRoomCapacity() {
        return roomCapacity.get();
    }
    
    public void setRoomCapacity(String s) {
        roomCapacity.set(s);
    }
    
    public String getRoomDescription() {
        return roomDescription.get();
    }
    
    public void setRoomDescription(String s) {
        roomDescription.set(s);
    }
    
    public String getPrice() {
        return price.get();
    }
    
     public void setPrice(String s) {
        price.set(s);
    }

}
