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
public class SitesTable {
    private final SimpleStringProperty sitesName;
    private final SimpleStringProperty countryName;
    private final SimpleStringProperty cityName;

    public SitesTable(String sitesName, String countryName, String cityName) {
        this.sitesName = new SimpleStringProperty(sitesName);
        this.countryName = new SimpleStringProperty(countryName);
        this.cityName =new  SimpleStringProperty(cityName);
    }

    public String getSitesName() {
        return sitesName.get();
    }

    public String getCountryName() {
        return countryName.get();
    }

    public String getCityName() {
        return cityName.get();
    }
    

}
