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
public class CityTable {
    
    private final SimpleStringProperty cityName;
    private final SimpleStringProperty cityId;
    private final SimpleStringProperty countryName;
    CityTable(String cuId, String ciId,String s) {
        this.cityName = new SimpleStringProperty(cuId);
        this.countryName = new SimpleStringProperty(ciId);
        this.cityId = new SimpleStringProperty(s);
    }

    public String getCityName() {
        return cityName.get();
    }

    public String getCityId() {
        return cityId.get();
    }

    public String getCountryName() {
        return countryName.get();
    }


}
