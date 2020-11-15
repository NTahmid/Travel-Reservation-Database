/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nafis
 */
public class UserQuery {
    OracleDBMS db;
    UserQuery()
    {
        db=new OracleDBMS();
    }
    boolean checkUserExists(String user,String password,String usertype)
    {   
        boolean result=false;
        try{
            Connection con=db.getConnection();
            String sql;
            if(usertype.equals("Admin"))
            {
                sql="SELECT ADMIN_USERNAME FROM DATABASE_ADMIN WHERE ADMIN_USERNAME=(?) AND ADMIN_PASSWORD=(?)"; 
                //System.out.println(sql);
            }
            else
            {
                sql="SELECT CUSTOMER_USERNAME FROM CUSTOMER WHERE CUSTOMER_USERNAME=(?) AND USER_PASSWORD=(?)";
                //System.out.println(sql);
            }
            PreparedStatement pst=con.prepareStatement(sql);
            
            pst.setString(1, user);
            pst.setString(2, password);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                result=true;
            }
            pst.close();
            db.closeConnection();
            return result;
            
        }catch(Exception e)
        {
            System.out.println(e);
            return result;
        }
    
    }
    
    public List<List<String> >getCustomerInfo()
    {
        String sql="SELECT C.CUSTOMER_NAME,C.CUSTOMER_USERNAME,C.ADDRESS,C.CONTACT_NO,CNT.COUNTRY_NAME,CIT.CITY_NAME FROM CUSTOMER C,COUNTRY CNT,CITY CIT WHERE C.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID";
         List<List<String>> resultList = new ArrayList<>();
        
        try{
            
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                resultList.add(row);
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return resultList;
    
    }
    
    public List<List<String> >getAdminInfo()
    {
        String sql="SELECT * FROM DATABASE_ADMIN";
         List<List<String>> resultList = new ArrayList<>();
        
        try{
            
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                resultList.add(row);
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return resultList;
    
    }
    
    public List<List<String> >getCustomerInfo(String countryName)
    {
        String sql="SELECT C.CUSTOMER_NAME,C.CUSTOMER_USERNAME,C.ADDRESS,C.CONTACT_NO,CNT.COUNTRY_NAME,CIT.CITY_NAME FROM CUSTOMER C,COUNTRY CNT,CITY CIT WHERE C.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";
         List<List<String>> resultList = new ArrayList<>();
        
        try{
            
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,countryName);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                resultList.add(row);
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return resultList;
    
    }
    
    public List<List<String> >getCustomerInfo(String countryName,String cityName)
    {
        String sql="SELECT C.CUSTOMER_NAME,C.CUSTOMER_USERNAME,C.ADDRESS,C.CONTACT_NO,CNT.COUNTRY_NAME,CIT.CITY_NAME FROM CUSTOMER C,COUNTRY CNT,CITY CIT WHERE C.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CITY_NAME=?";
         List<List<String>> resultList = new ArrayList<>();
        
        try{
            
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,countryName);
            pst.setString(2,cityName);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                row.add(rs.getString(3));
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
                resultList.add(row);
            }
        }catch(Exception e)
        {
            System.out.println(e);
        }
        
        return resultList;
    
    }
    
    
    
    
    
    public void CustomerRegistration(String username,String customerName,String address,String password,String contact,String countryName,String cityName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO CUSTOMER(CUSTOMER_USERNAME,CITY_ID,CUSTOMER_NAME,ADDRESS,CONTACT_NO,USER_PASSWORD) VALUES (?,(SELECT CITY_ID FROM CITY WHERE CITY_NAME=(?) AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?))),?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2,cityName);
            pst.setString(3,countryName);
            pst.setString(4,customerName);
            pst.setString(5,address);
            pst.setString(6,contact);
            pst.setString(7,password);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            db.closeConnection();
        }
    }
}
