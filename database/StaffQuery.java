/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Nafis
 */
public class StaffQuery {
    OracleDBMS db;
    StaffQuery()
    {
        db=new OracleDBMS();
    }
    public void InsertStaff(String staffId,String staffName,String address,String contact,String workplaceName,boolean work)
    {
        Connection con=db.getConnection();
        String sql=null;
        if(work==true)
            sql="INSERT INTO STAFF(STAFF_ID,HOTEL_ID,STAFF_NAME,CONTACT_NO,ADDRESS) VALUES(?,(SELECT HOTEL_ID FROM HOTEL WHERE HOTEL_NAME=(?)),?,?,?)";
        else
            sql="INSERT INTO STAFF(STAFF_ID,TOUR_PACKAGE_ID,STAFF_NAME,CONTACT_NO,ADDRESS) VALUES(?,(SELECT TOUR_PACKAGE_ID FROM TOUR_PACKAGE WHERE PACKAGE_NAME=(?)),?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,staffId);
            pst.setString(2,workplaceName);
            pst.setString(3,staffName);
            pst.setString(4,contact);
            pst.setString(5,address);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Insertion Failed");
            db.closeConnection();
        }
    }
}
