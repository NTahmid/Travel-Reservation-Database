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
public class CountryTable {
    private final SimpleStringProperty countryId;
    private final SimpleStringProperty countryName;
    CountryTable(String cuId, String ciId) {
        this.countryId = new SimpleStringProperty(cuId);
        this.countryName = new SimpleStringProperty(ciId);
    }
   public String getCountryId() {
        return countryId.get();
    }
    
    public void setCountryId(String lname) {
        countryId.set(lname);
    }  
    
    public String getCountryName() {
        return countryName.get();
    }
    
    public void setCountryName(String lname) {
        countryName.set(lname);
    }  
        
}
