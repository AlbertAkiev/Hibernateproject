package com.peaksoft.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USERNAME = "postgres";
    private static String  PASSWORD = "postgres";

    public static Connection connection(){
        Connection con= null;
        try{
            con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Successfully commected");
        }catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
}
