/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import javafx.beans.property.SimpleStringProperty;


public class CompanyTable {
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;
    public CompanyTable(String companyName, String address, String contact, String cityName, String countryName) {
        this.companyName = new SimpleStringProperty(companyName);
        this.address = new SimpleStringProperty(address);
        this.contact = new SimpleStringProperty(contact);
        this.cityName =new SimpleStringProperty(cityName);
        this.countryName = new SimpleStringProperty(countryName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getContact() {
        return contact.get();
    }

    public String getCityName() {
        return cityName.get();
    }

    public String getCountryName() {
        return countryName.get();
    }
    
}
