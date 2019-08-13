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
      } catch (SQLException e) // Excepcion ocurrida por la conexion 
      {
         System.out.println("Excepcion conexion: " + e.getMessage());         
      } catch (ClassNotFoundException e) // Excepcion ocurrida por no encontrar el driver
      {
         System.out.println("Excepcion driver: " + e.getMessage());         
      }
   }

   /**
    * Permite retornar la instancia de la conexion
    */
   public Connection getConnection() {      
      return conn;
   }

   // Quitamos de memoria la conexion
   public void disconnect() {
      System.out.println("Closing database: [" + conn + "] OK");
      if (conn != null) {
         try {
            // System.out.println("Desconectado de " + bd + " OK");
            conn.close();
         } catch (SQLException e) {
            System.out.println(e);
         }
      }
   }
}
