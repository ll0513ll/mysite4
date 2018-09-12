package com.javaex.sample;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;



public class Test {

public static void main(String args[]) {



      Connection conn;



      try {

         Class.forName("com.mysql.jdbc.Driver").newInstance();

         conn = DriverManager.getConnection(

               "jdbc:mysql://192.168.1.74:3306/tmp3?serverTimezone=UTC", "admin", "1234");

         System.out.println("Success!");

      } catch (SQLException ex) {

         System.out.println("SQLException:" + ex);

      } catch (Exception e) {

         System.out.println("Exception:" + e);

      }

   }

}

