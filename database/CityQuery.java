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
public class CityQuery {
    private OracleDBMS db;
    private CountryQuery cq;
    CityQuery()
    {
        db=new OracleDBMS();
        cq=new CountryQuery();
    }
    
    public void InsertCity(String cityName,String cityId,String countryName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO CITY(CITY_ID,COUNTRY_ID,CITY_NAME) VALUES (?,(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?)),?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, cityId);
            pst.setString(2,countryName);
            pst.setString(3,cityName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            db.closeConnection();
        }
    }
    
    public void deleteCity(String cityId)
    {   
        Connection con=db.getConnection();
        String sql="DELETE FROM CITY WHERE CITY_ID=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,cityId);
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
    
    public String getCityId(String countryName,String cityName)
    {
        String sql="SELECT CIT.CITY_ID FROM CITY CIT,COUNTRY CNT WHERE CIT.CITY_NAME=? AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";
        String result=null;
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1, cityName);
            pst.setString(2, countryName);
            ResultSet rs = pst.executeQuery();
            while(rs.next())
                result=rs.getString(1);
        }catch(Exception e)
        {   
            System.out.println(e);
        }
        return result;
    }
            
     public List<List<String>> getCityInfo(String countryName)
    {
        String sql = "SELECT CIT.CITY_NAME,CNT.COUNTRY_NAME,CIT.CITY_ID FROM CITY CIT, COUNTRY CNT WHERE CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, countryName);
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public List<List<String>> getCityInfo()
    {
        String sql = "SELECT CIT.CITY_NAME,CNT.COUNTRY_NAME,CIT.CITY_ID FROM CITY CIT, COUNTRY CNT WHERE CIT.COUNTRY_ID=CNT.COUNTRY_ID";

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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public ArrayList<String>getCities(String countryname)
    {   
         ArrayList<String>array=new ArrayList<>();
         Connection con=db.getConnection();
         //String countryId=cq.getCountryId(countryname);
         String sql="Select CITY_NAME FROM CITY WHERE COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?))";
         try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,countryname);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                array.add(rs.getString(1));
            }
            pst.close();
            db.closeConnection();
            return array;
         }catch(Exception e)
         {
             System.out.println(e);
             db.closeConnection();
             return array;
         }
    }
}
