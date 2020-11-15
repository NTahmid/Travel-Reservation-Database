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
public class CountryQuery {
    private OracleDBMS db;
    CountryQuery()
    {
        db=new OracleDBMS();
    }
    public void InsertCountry(String countryName,String countryId)
    {   
        Connection con=db.getConnection();
        String sql="Insert into COUNTRY(COUNTRY_ID,COUNTRY_NAME) values(?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1, countryId);
            pst.setString(2,countryName);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
        }catch(Exception e)
        {
            System.out.println(e);
            db.closeConnection();
        }
    }
    public String getCountryId(String countryName)
    {
        Connection con=db.getConnection();
        String sql="Select COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?)";
        String result=null;
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,countryName);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                result=rs.getString(1);
            }
            pst.close();
            db.closeConnection();
            return result;
            
        }catch(Exception e){
            System.out.println(e);
            db.closeConnection();
            return result;
        }
    }
    
    public void deleteCountry(String countryId)
    {   
        Connection con=db.getConnection();
        String sql="DELETE FROM COUNTRY WHERE COUNTRY_ID=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,countryId);
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
    
    public ArrayList<String>getCountryWithCities()
    {
        Connection con=db.getConnection();
        ArrayList<String>array=new ArrayList<>();
        String sql="select country_name from country where country_id=any(select country_id from city group by country_id having count(city_id)>0)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                array.add(rs.getString(1));
            }
            pst.close();
            db.closeConnection();
            return array;
            
        }catch(Exception e){
            System.out.println(e);
            db.closeConnection();
            return array;
        }
    }
    
    public List<List<String>> getCountryInfo()
    {
        String sql = "SELECT * FROM COUNTRY";

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
    
    public ArrayList<String>getCountryNames()
    {   
        Connection con=db.getConnection();
        ArrayList<String>array=new ArrayList<>();
        String sql="Select country_name from country";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
                array.add(rs.getString(1));
            }
            pst.close();
            db.closeConnection();
            return array;
            
        }catch(Exception e){
            System.out.println(e);
            db.closeConnection();
            return array;
        }
    }
}
