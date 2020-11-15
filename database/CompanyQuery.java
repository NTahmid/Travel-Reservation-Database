/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nafis
 */
public class CompanyQuery {
    private OracleDBMS db;
    CompanyQuery()
    {
        db=new OracleDBMS();
    }
    public void InsertCompany(String companyId,String companyName,String address,String contact,String countryName,String cityName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO COMPANY(COMPANY_ID,CITY_ID,COMPANY_NAME,CONTACT_NO,ADDRESS) VALUES(?,(SELECT CITY_ID FROM CITY WHERE CITY_NAME=? AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?))),?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,companyId);
            pst.setString(2,cityName);
            pst.setString(3,countryName);
            pst.setString(4,companyName);
            pst.setString(5,contact);
            pst.setString(6,address);
            pst.executeUpdate();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Insertion Failed");
            db.closeConnection();
        }
    }
    
    public void deleteCompany(String companyName)
    {   
        Connection con=db.getConnection();
        String sql = "DELETE FROM COMPANY WHERE COMPANY_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,companyName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Deletion Failed");
            db.closeConnection();
        }
    }
    
     public void newCompanyName(String newName,String companyName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE COMPANY SET COMPANY_NAME = ? WHERE COMPANY_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newName);
            pst.setString(2, companyName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Deletion Failed");
            db.closeConnection();
        }
    }
    
    public void newCompanyContact(String newContact,String companyName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE COMPANY SET CONTACT_NO = ? WHERE COMPANY_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newContact);
            pst.setString(2, companyName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Deletion Failed");
            db.closeConnection();
        }
    }
    
    public void newCompanyAddress(String newAddress,String companyName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE COMPANY SET ADDRESS = ? WHERE COMPANY_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newAddress);
            pst.setString(2, companyName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Deletion Failed");
            db.closeConnection();
        }
    }
     
    public List<List<String>> getCompanyInfo()
    {
        String sql = "SELECT CMP.COMPANY_NAME,CMP.ADDRESS,CMP.CONTACT_NO,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM COMPANY CMP,CITY CIT,COUNTRY CNT WHERE CMP.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID";
        
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
                resultList.add(row);
            }
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public List<List<String>> getCompanyInfo(String countryName)
    {
        String sql = "SELECT CMP.COMPANY_NAME,CMP.ADDRESS,CMP.CONTACT_NO,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM COMPANY CMP,CITY CIT,COUNTRY CNT WHERE CMP.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";
        
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
                resultList.add(row);
            }
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public List<List<String>> getCompanyInfo(String countryName,String cityName)
    {
        String sql = "SELECT CMP.COMPANY_NAME,CMP.ADDRESS,CMP.CONTACT_NO,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM COMPANY CMP,CITY CIT,COUNTRY CNT WHERE CMP.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CIT.CITY_NAME=?";
        
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
                resultList.add(row);
            }
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public ArrayList<String>getCompanyNames(String countryName,String cityName)
    {
        ArrayList<String>array=new ArrayList<>();
        Connection con=db.getConnection();
        String sql="SELECT COMPANY_NAME FROM COMPANY WHERE CITY_ID=(SELECT CITY_ID FROM CITY WHERE CITY_NAME=(?) AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?)))";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,cityName);
            pst.setString(2,countryName);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                array.add(rs.getString(1));
            }
            db.closeConnection();
            return array;
        }catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Insertion Failed");
            db.closeConnection();
            return array;
        }
    
    }

}
