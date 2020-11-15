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
public class SitesQuery {
    OracleDBMS db;
    SitesQuery()
    {
        db=new OracleDBMS();
    }
    
    public void InsertPackageSites(String sitesId,String packageId)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO TOUR_PACKAGE_SITES(TOUR_PACKAGE_ID,SITES_ID) VALUES(?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,packageId);
            pst.setString(2,sitesId);
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
    
    public void InsertSites(String siteId,String siteName,String countryName,String cityName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO SITES(SITES_ID,CITY_ID,SITES_NAME) VALUES(?,(SELECT CITY_ID FROM CITY WHERE CITY_NAME=(?) AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?))),?)";
        
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,siteId);
            pst.setString(2,cityName);
            pst.setString(3,countryName);
            pst.setString(4,siteName);
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
    
    public List<List<String>> getSitesInfo()
    {
        String sql = "SELECT S.SITES_NAME,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM SITES S, CITY CIT,COUNTRY CNT WHERE S.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID";

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
    
    public List<List<String>> getSitesInfo(String countryName)
    {
        String sql = "SELECT S.SITES_NAME,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM SITES S, CITY CIT,COUNTRY CNT WHERE S.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";

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
    
    public List<List<String>> getSitesInfo(String countryName,String cityName)
    {
        String sql = "SELECT S.SITES_NAME,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM SITES S, CITY CIT,COUNTRY CNT WHERE S.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CIT.CITY_NAME=?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, countryName);
            pst.setString(2,cityName);
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
    
    public List<List<String>> getSites(String cityId)
    {
        String sql = "SELECT S.SITES_ID,S.SITES_NAME FROM SITES S WHERE S.CITY_ID=?";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cityId);
            
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
    
     public List<List<String>> getPackageSites(String packageId)
    {
        String sql = "SELECT S.SITES_ID,S.SITES_NAME FROM SITES S, TOUR_PACKAGE_SITES TPS WHERE TPS.SITES_ID=S.SITES_ID AND TPS.TOUR_PACKAGE_ID=?";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, packageId);
            
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
    
     public List<List<String>> getRemovePackageSitesInfo(String cityId,String packageId)
    {
        String sql = "SELECT SITES_ID,SITES_NAME FROM SITES WHERE CITY_ID=? AND SITES_ID=ANY(SELECT SITES_ID FROM TOUR_PACKAGE_SITES WHERE TOUR_PACKAGE_ID=?)";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cityId);
            pst.setString(2,packageId);
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
    
    public List<List<String>> getAddPackageSitesInfo(String cityId,String packageId)
    {
        String sql = "SELECT SITES_ID,SITES_NAME FROM SITES WHERE CITY_ID=? AND SITES_ID<>ALL(SELECT SITES_ID FROM TOUR_PACKAGE_SITES WHERE TOUR_PACKAGE_ID=?)";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cityId);
            pst.setString(2,packageId);
            
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            
            while (rs.next())
            {
                List<String> row = new ArrayList<>();
                String ss=rs.getString(1);
                String sg=rs.getString(2);
                row.add(ss);
                row.add(sg);
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
     
    public void deleteSite(String siteName)
    {   
        Connection con=db.getConnection();
        String sql="DELETE FROM SITES WHERE SITES_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,siteName);
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
    
    public void deletePackageSite(String siteId,String packageId)
    {   
        Connection con=db.getConnection();
        String sql="DELETE FROM TOUR_PACKAGE_SITES WHERE SITES_ID=? AND TOUR_PACKAGE_ID=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,siteId);
            pst.setString(2,packageId);
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
    
}

