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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nafis
 */
public class PackageQuery {
    private OracleDBMS db;
    PackageQuery()
    {
        db=new OracleDBMS();
    }
    public void InsertPackage(String packageId,String packageName,String companyName,int price,int packageNumber,int days)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO TOUR_PACKAGE(TOUR_PACKAGE_ID,COMPANY_ID,PACKAGE_NAME,PRICE,AVAILABLE_NUMBER,LENGTH_OF_DAYS) VALUES(?,(SELECT COMPANY_ID FROM COMPANY WHERE COMPANY_NAME=(?)),?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,packageId);
            pst.setString(2,companyName);
            pst.setString(3,packageName);
            pst.setInt(4,price);
            pst.setInt(5,packageNumber);
            pst.setInt(6, days);
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
    
    public void InsertBookedPackage(String packageId,String userName,String startDate)
    {   
        Connection con=db.getConnection();
        String sql= "INSERT INTO TOUR_PACKAGE_BOOKING(CUSTOMER_USERNAME,TOUR_PACKAGE_ID,STARTING_DATE) VALUES(?,?,?)";
        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
        try{
            java.util.Date myDate1 = format.parse( startDate );
            PreparedStatement pst=con.prepareStatement(sql);
            java.sql.Date sqlDate1 = new java.sql.Date( myDate1.getTime() );
            pst.setString(1, userName);
            pst.setString(2,packageId);
            
            pst.setDate(3,sqlDate1);
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
      
    
    
    public ArrayList<String>getPackageNames(String companyName)
    {
        ArrayList<String>array=new ArrayList<>();
        Connection con=db.getConnection();
        String sql="SELECT PACKAGE_NAME FROM TOUR_PACKAGE WHERE COMPANY_ID=(SELECT COMPANY_ID FROM COMPANY WHERE COMPANY_NAME=(?))";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,companyName);
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
            System.out.println("Insertion Failed");
            db.closeConnection();
            return array;
        }
    }
    
    public String getPackageId(String packageName)
    {
        String sql="SELECT TOUR_PACKAGE_ID FROM TOUR_PACKAGE WHERE PACKAGE_NAME=?";
        String result=null;
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
             pst.setString(1, packageName);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next())
                result=rs.getString(1);
        }catch(Exception e)
        {   
            System.out.println(e);
        }
        return result;
    }
    
    public List<List<String>> getPackageInfo()
    {
        String sql = "SELECT P.PACKAGE_NAME,P.PRICE,P.AVAILABLE_NUMBER,P.LENGTH_OF_DAYS,C.COMPANY_NAME,CI.CITY_NAME,CNT.COUNTRY_NAME FROM TOUR_PACKAGE P,COMPANY C,CITY CI,COUNTRY CNT WHERE P.COMPANY_ID=C.COMPANY_ID AND C.CITY_ID=CI.CITY_ID AND CI.COUNTRY_ID=CNT.COUNTRY_ID";

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
                row.add(rs.getString(7));
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
    public List<List<String>> getPackageInfo(String countryName)
    {
        String sql = "SELECT P.PACKAGE_NAME,P.PRICE,P.AVAILABLE_NUMBER,P.LENGTH_OF_DAYS,C.COMPANY_NAME,CI.CITY_NAME,CNT.COUNTRY_NAME FROM TOUR_PACKAGE P,COMPANY C,CITY CI,COUNTRY CNT WHERE P.COMPANY_ID=C.COMPANY_ID AND C.CITY_ID=CI.CITY_ID AND CI.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";

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
                row.add(rs.getString(7));
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
    public List<List<String>> getPackageInfo(String countryName,String cityName)
    {
        String sql = "SELECT P.PACKAGE_NAME,P.PRICE,P.AVAILABLE_NUMBER,P.LENGTH_OF_DAYS,C.COMPANY_NAME,CI.CITY_NAME,CNT.COUNTRY_NAME FROM TOUR_PACKAGE P,COMPANY C,CITY CI,COUNTRY CNT WHERE P.COMPANY_ID=C.COMPANY_ID AND C.CITY_ID=CI.CITY_ID AND CI.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CI.CITY_NAME=?";

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
                row.add(rs.getString(7));
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
    public List<List<String>> getPackageInfo(String countryName,String cityName,String companyName)
    {
        String sql = "SELECT P.PACKAGE_NAME,P.PRICE,P.AVAILABLE_NUMBER,P.LENGTH_OF_DAYS,C.COMPANY_NAME,CI.CITY_NAME,CNT.COUNTRY_NAME FROM TOUR_PACKAGE P,COMPANY C,CITY CI,COUNTRY CNT WHERE P.COMPANY_ID=C.COMPANY_ID AND C.CITY_ID=CI.CITY_ID AND CI.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CI.CITY_NAME=? AND C.COMPANY_NAME=?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,countryName);
            pst.setString(2,cityName);
            pst.setString(3,companyName);
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
                row.add(rs.getString(7));
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
    
    public void deletePackage(String packageName)
    {   
        Connection con=db.getConnection();
        String sql="DELETE FROM TOUR_PACKAGE WHERE PACKAGE_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,packageName);
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
    
    public void changePackageName(String newName,String packageName)
    {   
        Connection con=db.getConnection();
        String sql="UPDATE TOUR_PACKAGE SET PACKAGE_NAME = ? WHERE PACKAGE_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newName);
            pst.setString(2,packageName);
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
    
     public void changePackagePrice(int price,String packageName)
    {   
        Connection con=db.getConnection();
        String sql="UPDATE TOUR_PACKAGE SET PRICE = ? WHERE PACKAGE_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,price);
            pst.setString(2,packageName);
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
     
     public void incrementPackageDays(int days,String packageName)
    {   
        Connection con=db.getConnection();
        String sql="UPDATE TOUR_PACKAGE SET LENGTH_OF_DAYS = (LENGTH_OF_DAYS+?) WHERE PACKAGE_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,days);
            pst.setString(2,packageName);
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
     
     public void incrementPackageNumber(int num,String packageName)
    {   
        Connection con=db.getConnection();
        String sql="UPDATE TOUR_PACKAGE SET AVAILABLE_NUMBER =(AVAILABLE_NUMBER+?) WHERE PACKAGE_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,num);
            pst.setString(2,packageName);
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
