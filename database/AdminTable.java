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
public class AdminTable {
    
    private final SimpleStringProperty adminUsername;
    private final SimpleStringProperty adminName;
    private final SimpleStringProperty password;
    
    
    AdminTable(String cuId, String ciId, String addr) {
        this.adminUsername = new SimpleStringProperty(cuId);
        this.adminName = new SimpleStringProperty(ciId);
        this.password = new SimpleStringProperty(addr);
    }
    
    public String getAdminUsername() {
        return adminUsername.get();
    }
    
    public void setAdminUsername(String cuId) {
        adminUsername.set(cuId);
    }
    
    public String  getAdminName() {
        return adminName.get();
    }
    
    public void setAdminName(String ciId) {
        adminName.set(ciId);
    }
    
    public String getPassword() {
        return password.get();
    }
    
    public void setPassword(String fname) {
        password.set(fname);
    }
}
