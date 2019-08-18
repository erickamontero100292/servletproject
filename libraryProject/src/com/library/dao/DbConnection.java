package com.library.dao;

import java.sql.*;

public class DbConnection {


   static String BD = "library";
   static String LOGIN = "root";
   static String PASSWORD = "123456";
   static String URL = "jdbc:mysql://localhost/" + BD;
   Connection conn = null;

   public DbConnection() {
      try {
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
         if (conn != null) {
            System.out.println("Connecting database [" + conn + "] OK");
         }
      } catch (SQLException e)
      {
         System.out.println("Excepcion conexion: " + e.getMessage());         
      } catch (ClassNotFoundException e)
      {
         System.out.println("Excepcion driver: " + e.getMessage());         
      }
   }


   public Connection getConnection() {      
      return conn;
   }

   public void disconnect() {
      System.out.println("Closing database: [" + conn + "] OK");
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException e) {
            System.out.println(e);
         }
      }
   }
}
