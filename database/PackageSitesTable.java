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
public class PackageSitesTable {
    private final SimpleStringProperty sitesId;
    private final SimpleStringProperty sitesName;

    public PackageSitesTable(String sitesId, String sitesName) {
        this.sitesId = new SimpleStringProperty(sitesId);
        this.sitesName = new SimpleStringProperty(sitesName);
    }

    public String getSitesId() {
        return sitesId.get();
    }

    public String getSitesName() {
        return sitesName.get();
    }
    
    
}
