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
public class CustomerTable {
    
    
    private final SimpleStringProperty customerName;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty customerUsername;
    private final SimpleStringProperty countryName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty contact;

    CustomerTable(String cuId, String ciId, String addr,String con,String lname,String fname) {
        this.customerName = new SimpleStringProperty(cuId);
        this.customerUsername = new SimpleStringProperty(ciId);
        this.address = new SimpleStringProperty(addr);
        this.contact = new SimpleStringProperty(con);
        this.countryName = new SimpleStringProperty(lname);
        this.cityName = new SimpleStringProperty(fname);
        
        
       

    }
    
//    User(List<String> row) {
//        this.userName = new SimpleStringProperty(row.get(0));
//        this.password = new SimpleStringProperty(row.get(1));
//        this.fullName = new SimpleStringProperty(row.get(2));
//    }

    public String getCustomerName() {
        return customerName.get();
    }
    
    public void setCustomerName(String cuId) {
        customerName.set(cuId);
    }
    
    public String getCityName() {
        return cityName.get();
    }
    
    public void setCityName(String ciId) {
        cityName.set(ciId);
    }
    
    public String getCustomerUsername() {
        return customerUsername.get();
    }
    
    public void setCustomerUsername(String fname) {
        customerUsername.set(fname);
    }
    
    public String getCountryName() {
        return countryName.get();
    }
    
    public void setCountryName(String lname) {
        countryName.set(lname);
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public void setAddress(String addr) {
        address.set(addr);
    }
    
    public String getContact() {
        return contact.get();
    }
    
    public void setContact(String con) {
        contact.set(con);
    }
}
