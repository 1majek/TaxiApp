/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevenmajek.CONFIG;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oficina taxi
 */
public class Conexion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       getConexion();
    }

    public static Connection getConexion() {
        Connection con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String net = "jdbc:mysql://localhost:3309/reservas";
            String userName = "root";
            String pass = "majekodunmi";     
            con = DriverManager.getConnection(net,userName,pass);
           
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return con;
    }

}
