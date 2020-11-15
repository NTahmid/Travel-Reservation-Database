/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectver2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Nafis
 */
public class HotelQuery {
    private OracleDBMS db;
    HotelQuery()
    {
        db=new OracleDBMS();
    }
    public void InsertHotelRoom(String roomNo,String hotelName,int roomCapacity,String roomDescription,int price,String roomName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO ROOM(HOTEL_ID,ROOM_NO,ROOM_DESCRIPTION,ROOM_CAPACITY,PRICE_PER_DAY,ROOM_NAME) VALUES((SELECT HOTEL_ID FROM HOTEL WHERE HOTEL_NAME=(?)),?,?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,hotelName);
            pst.setString(2,roomNo);
            pst.setString(3,roomDescription);
            pst.setInt(4,roomCapacity);
            pst.setInt(5,price);
            pst.setString(6,roomName);
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
    
    public void InsertHotel(String hotelId,String hotelName,String address,String contact,double rating,String countryName,String cityName)
    {
        Connection con=db.getConnection();
        String sql="INSERT INTO HOTEL(HOTEL_ID,CITY_ID,HOTEL_NAME,RATING,ADDRESS,CONTACT_NO) VALUES (?,(SELECT CITY_ID FROM CITY WHERE CITY_NAME=? AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?))),?,?,?,?)";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,hotelId);
            pst.setString(2,cityName);
            pst.setString(3,countryName);
            pst.setString(4,hotelName);
            pst.setDouble(5,rating);
            pst.setString(6,address);
            pst.setString(7,contact);
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
    public ArrayList<String>getHotelNames(String countryName,String cityName)
    {
        Connection con=db.getConnection();
        ArrayList<String>array=new ArrayList<>();
        String sql="SELECT HOTEL_NAME FROM HOTEL WHERE CITY_ID=(SELECT CITY_ID FROM CITY WHERE CITY_NAME=(?) AND COUNTRY_ID=(SELECT COUNTRY_ID FROM COUNTRY WHERE COUNTRY_NAME=(?)))";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,cityName);
            pst.setString(2,countryName);
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
    public List<List<String>> getAllHotelInfo()
    {
        String sql = "select h.hotel_name,countr.country_name,cit.city_name,h.rating,get_review(h.rating)REVIEW,h.contact_no from hotel h,country countr, city cit where h.city_id=cit.city_id and cit.country_id=countr.country_id";

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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public void deleteHotel(String hotelName)
    {   
        Connection con=db.getConnection();
        String sql = "DELETE FROM HOTEL WHERE HOTEL_NAME=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,hotelName);
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
    
    public void deleteRoom(String roomId)
    {   
        Connection con=db.getConnection();
        String sql = "DELETE FROM ROOM WHERE ROOM_ID=?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,roomId);
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
    
    public void newHotelName(String newName,String hotelName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE HOTEL SET HOTEL_NAME = ? WHERE HOTEL_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newName);
            pst.setString(2, hotelName);
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
    
    public void newRoomName(String newName,String roomId)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE ROOM SET ROOM_NAME = ? WHERE ROOM_ID= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newName);
            pst.setString(2, roomId);
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
    
    public void newRoomDescription(String newDescription,String roomId)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE ROOM SET ROOM_DESCRIPTION = ? WHERE ROOM_ID= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newDescription);
            pst.setString(2, roomId);
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
    
    public void newRoomPrice(int newPrice,String roomId)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE ROOM SET PRICE_PER_DAY = ? WHERE ROOM_ID= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,newPrice);
            pst.setString(2, roomId);
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
    
    public void incrementRoomCap(int cap,String roomId)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE ROOM SET ROOM_CAPACITY = (ROOM_CAPACITY+?) WHERE ROOM_ID= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setInt(1,cap);
            pst.setString(2, roomId);
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
    
    public void newHotelContact(String newContact,String hotelName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE HOTEL SET CONTACT_NO = ? WHERE HOTEL_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newContact);
            pst.setString(2, hotelName);
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
    
    public void newHotelAddress(String newAddress,String hotelName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE HOTEL SET ADDRESS = ? WHERE HOTEL_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setString(1,newAddress);
            pst.setString(2, hotelName);
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
    
    public void newHotelRating(double newRating,String hotelName)
    {   
        Connection con=db.getConnection();
        String sql = "UPDATE HOTEL SET RATING = ? WHERE HOTEL_NAME= ?";
        try{
            PreparedStatement pst=con.prepareStatement(sql);
            pst.setDouble(1,newRating);
            pst.setString(2, hotelName);
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
    
    
    public List<List<String>> getHotelInfo()
    {
        String sql = "SELECT H.HOTEL_NAME,H.ADDRESS,H.CONTACT_NO,H.RATING,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM HOTEL H,CITY CIT,COUNTRY CNT WHERE H.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID";
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    public List<List<String>> getHotelInfo(String countryName)
    {
        String sql = "SELECT H.HOTEL_NAME,H.ADDRESS,H.CONTACT_NO,H.RATING,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM HOTEL H,CITY CIT,COUNTRY CNT WHERE H.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=?";
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
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
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
    
    public List<List<String>> getRoomInfo(String hotelName)
    {
        String sql = "SELECT R.ROOM_ID,R.ROOM_NO,R.ROOM_NAME,R.ROOM_CAPACITY,R.ROOM_DESCRIPTION,R.PRICE_PER_DAY FROM ROOM R, HOTEL H WHERE R.HOTEL_ID=H.HOTEL_ID AND H.HOTEL_NAME= ?";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, hotelName);
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    
    public List<List<String>> getHotelInfo(String countryName,String cityName)
    {
        String sql = "SELECT H.HOTEL_NAME,H.ADDRESS,H.CONTACT_NO,H.RATING,CIT.CITY_NAME,CNT.COUNTRY_NAME FROM HOTEL H,CITY CIT,COUNTRY CNT WHERE H.CITY_ID=CIT.CITY_ID AND CIT.COUNTRY_ID=CNT.COUNTRY_ID AND CNT.COUNTRY_NAME=? AND CIT.CITY_NAME=?";
        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, countryName);
            pst.setString(2, cityName);
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
    
    public List<List<String>> getAllHotelInfo(double minRating,double maxRating)
    {
        String sql = "select h.hotel_name,countr.country_name,cit.city_name,h.rating,get_review(h.rating)REVIEW,h.contact_no from hotel h,country countr, city cit where h.city_id=cit.city_id and cit.country_id=countr.country_id and h.rating between ? and ?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1,minRating);
            pst.setDouble(2, maxRating);
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    public List<List<String>> getAllHotelInfo(double minRating,double maxRating,String countryName,String cityName)
    {
        String sql = "select h.hotel_name,countr.country_name,cit.city_name,h.rating,get_review(h.rating)REVIEW,h.contact_no from hotel h,country countr, city cit where h.city_id=cit.city_id and cit.country_id=countr.country_id and h.rating between ? and ? and countr.country_name=? and cit.city_name=?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1,minRating);
            pst.setDouble(2, maxRating);
            pst.setString(3, countryName);
            pst.setString(4, cityName);
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    public List<List<String>> getAllHotelInfo(String countryName)
    {
        String sql = "select h.hotel_name,countr.country_name,cit.city_name,h.rating,get_review(h.rating)REVIEW,h.contact_no from hotel h,country countr, city cit where h.city_id=cit.city_id and cit.country_id=countr.country_id and countr.country_name=?";

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
                row.add(rs.getString(4));
                row.add(rs.getString(5));
                row.add(rs.getString(6));
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
    
   
    
    public List<List<String>> getAllHotelInfo(double minRating,double maxRating,String countryName)
    {
        String sql = "select h.hotel_name,countr.country_name,cit.city_name,h.rating,get_review(h.rating)REVIEW,h.contact_no from hotel h,country countr, city cit where h.city_id=cit.city_id and cit.country_id=countr.country_id and h.rating between ? and ? and countr.country_name=?";

        List<List<String>> resultList = new ArrayList<>();
        try{
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setDouble(1,minRating);
            pst.setDouble(2, maxRating);
            pst.setString(3, countryName);
           
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
            pst.close();
           db.closeConnection();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return resultList;
    }
    
     public void BookRoom(String userName,String roomId,String checkIn,String checkOut)
     {
         String sql= "INSERT INTO CUSTOMER_ROOM_BOOKING(CUSTOMER_USERNAME,ROOM_ID,CHECKIN_DATE,CHECKOUT_DATE) VALUES(?,?,?,?)";
         SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );
         try{
            java.util.Date myDate1 = format.parse( checkIn );
            java.util.Date myDate2 = format.parse( checkOut );
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            java.sql.Date sqlDate1 = new java.sql.Date( myDate1.getTime() );
            java.sql.Date sqlDate2 = new java.sql.Date( myDate2.getTime() );
             pst.setString(1, userName);
            pst.setString(2,roomId);
            
            pst.setDate(3,sqlDate1);
            pst.setDate(4,sqlDate2);
            pst.executeUpdate();
            pst.close();
            db.closeConnection();
         }catch(Exception e)
        {
            System.out.println(e);
             db.closeConnection();
        }
     }
     
    public List<List<String> >getHotelRooms(String checkinDate,String checkoutDate,String hotelName)
    {
        String sql="SELECT H.HOTEL_NAME,R.ROOM_NAME,R.ROOM_DESCRIPTION,R.ROOM_CAPACITY,GET_PRICE(R.PRICE_PER_DAY,?,?)PRICE,R.ROOM_ID  FROM HOTEL H,ROOM R WHERE H.HOTEL_NAME=? AND H.HOTEL_ID=R.HOTEL_ID AND R.ROOM_ID<>ALL(SELECT ROOM_ID FROM CUSTOMER_ROOM_BOOKING WHERE NOT(?<CHECKIN_DATE OR ?>CHECKOUT_DATE))";
         List<List<String>> resultList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );  
        try{
            java.util.Date myDate1 = format.parse( checkinDate );
             java.util.Date myDate2 = format.parse( checkoutDate );
            System.out.println(myDate1.toString());
             
            Connection con = db.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            java.sql.Date sqlDate1 = new java.sql.Date( myDate1.getTime() );
            java.sql.Date sqlDate2 = new java.sql.Date( myDate2.getTime() );
            pst.setDate(1, sqlDate1);
            pst.setDate(2,sqlDate2);
            pst.setString(3,hotelName);
            pst.setDate(4,sqlDate2);
            pst.setDate(5,sqlDate1);
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

}
