package com.ruyo.fg.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class Conexion {

    private final String USER = "root";
    private final String PASSWORD = "";
    private final String URL = "jdbc:mysql://127.0.0.1/fg";
    private final String CLASS = "com.mysql.jdbc.Driver";

    @Bean
    public Connection getConexion() {
        try{
            Class.forName(CLASS);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }



}
