/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nafis
 */
public class TourPackageTable {
    private final SimpleStringProperty packageName;
    private final SimpleStringProperty price;
    private final SimpleStringProperty available;
    private final SimpleStringProperty days;
    private final SimpleStringProperty companyName;
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty countryName;
    

    public TourPackageTable(String packageName, String price, String available, String days, String companyName, String cityName, String countryName) {
        this.packageName = new SimpleStringProperty(packageName);
        this.price =  new SimpleStringProperty(price);
        this.available =  new SimpleStringProperty(available);
        this.days =  new SimpleStringProperty(days);
        this.companyName =  new SimpleStringProperty(companyName);
        this.countryName =  new SimpleStringProperty(countryName);
        this.cityName =  new SimpleStringProperty(cityName);
    }

    public String getPackageName() {
        return packageName.get();
    }

    public String getPrice() {
        return price.get();
    }

    public String getAvailable() {
        return available.get();
    }

    public String getDays() {
        return days.get();
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public String getCountryName() {
        return countryName.get();
    }

    public String getCityName() {
        return cityName.get();
    }
    
    
}
