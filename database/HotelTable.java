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
public class HotelTable {
    private final SimpleStringProperty hotelName;
    private final SimpleStringProperty countryName;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty rating;
    private final SimpleStringProperty review;
    private final SimpleStringProperty contact;
    HotelTable(String hName, String countryN, String cityN,String r,String rev,String con) {
        this.hotelName = new SimpleStringProperty(hName);
        this.countryName = new SimpleStringProperty(countryN);
        this.cityName = new SimpleStringProperty(cityN);
        this.rating = new SimpleStringProperty(r);
        this.review = new SimpleStringProperty(rev);
        this.contact = new SimpleStringProperty(con);
    }

    public String getHotelName() {
        return hotelName.get();
    }
    
    public void setHotelName(String cuId) {
        hotelName.set(cuId);
    }
    
    public String getCountryName() {
        return countryName.get();
    }
    
    public void setCountryName(String ciId) {
        countryName.set(ciId);
    }
    
    public String getCityName() {
        return cityName.get();
    }
    
    public void setCityName(String fname) {
        cityName.set(fname);
    }
    
    public String getRating() {
        return rating.get();
    }
    
    public void setRating(String lname) {
        rating.set(lname);
    }
    
    public String getReview() {
        return review.get();
    }
    
    public void setReview(String addr) {
        review.set(addr);
    }
    
    public String getContact() {
        return contact.get();
    }
    
    public void setContact(String con) {
        contact.set(con);
    }
     @Override
    public String toString() {
        return "HotelTable{" + "hotelName=" + hotelName + ", countryName=" + countryName + ", cityName=" + cityName + ", rating=" + rating + ", contact=" + contact + '}';
    }
}
