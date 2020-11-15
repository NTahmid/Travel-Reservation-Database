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
public class HotelTableAdmin {
    private final SimpleStringProperty hotelName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty rating;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;

    public HotelTableAdmin(String hotelName, String address, String contact, String rating, String cityName, String countryName) {
        this.hotelName = new SimpleStringProperty(hotelName);
        this.address = new SimpleStringProperty(address);
        this.contact = new SimpleStringProperty(contact);
        this.rating = new SimpleStringProperty(rating);
        this.cityName = new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
    }

    public String getHotelName() {
        return hotelName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getContact() {
        return contact.get();
    }

    public String getRating() {
        return rating.get();
    }

    public String getCityName() {
        return cityName.get();
    }

    public String getCountryName() {
        return countryName.get();
    }
    
    
    
}
