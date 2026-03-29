package com.meet.crawler;

import java.sql.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class database {



    public void forimage(String fileName,String link,String url) 
    {
        String dburl = "jdbc:mysql://localhost:3306/meet";
        String user = "root";
        String password = "6174";
        try{
            Connection con = DriverManager.getConnection(dburl, user, password);
             //System.out.println("data base is connected");
                 String sql = "INSERT INTO IMAGE(image_name,url,link) VALUES (?,?,?)";
                 PreparedStatement ps = con.prepareStatement(sql);
                 ps.setString(1,fileName);
                   ps.setString(2,url);
                     ps.setString(3,link);
                     ps.executeUpdate();
        }catch(Exception e)
        {
            System.out.println("Error in DB "+ e);
        }
    }

}