/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nafis
 */
public class HotelRoomTableAdmin {
    private final SimpleStringProperty roomId;
    private final SimpleStringProperty roomNo;
    private final SimpleStringProperty roomName;
    private final SimpleStringProperty roomCapacity;
    private final SimpleStringProperty roomDescription;
    private final SimpleStringProperty price;

    public HotelRoomTableAdmin(String roomId, String roomNo, String roomName, String roomCapacity, String roomDescription, String price) {
        this.roomId = new SimpleStringProperty(roomId);
        this.roomNo =new SimpleStringProperty(roomNo);
        this.roomName = new SimpleStringProperty(roomName);
        this.roomCapacity = new SimpleStringProperty(roomCapacity);
        this.roomDescription = new SimpleStringProperty(roomDescription);
        this.price = new SimpleStringProperty(price);
    }
    

    public String getRoomId() {
        return roomId.get();
    }

    public String getRoomNo() {
        return roomNo.get();
    }

    public String getRoomName() {
        return roomName.get();
    }

    public String getRoomCapacity() {
        return roomCapacity.get();
    }

    public String getRoomDescription() {
        return roomDescription.get();
    }

    public String getPrice() {
        return price.get();
    }
    
}
