package com.example.plainjava;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySQLUtils {

    static String ID = "id";
    static String USER_VORNAME = "vorname";
    static String USER_NACHNAME = "nachname";
    static String EMAIL_ADRESS = "lieblingsessen";
    static String ALTER = "age";

    static String USER_TABLE ="users";


    public static Connection getConnection() throws SQLException {
        Connection conn = null;

        try (FileInputStream f = new FileInputStream("plainjava/db.properties")) {
            // load the properties file
            Properties pros = new Properties();
            pros.load(f);

            // assign db parameters
            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static List<String> getColumns(String table){
        List<String> columnList = new ArrayList<>();
        try (Connection conn = MySQLUtils.getConnection()) {
            String sqlStatement = "SELECT * FROM " + table;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);

            while (rs.next()) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return columnList;
    }



    public static int getLastID(String table) {
        Integer lastID = null;
        try (Connection conn = MySQLUtils.getConnection()) {
            String sqlStatement = "SELECT  MAX(ID) " + table;
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlStatement);
            rs.next();
            lastID = rs.getInt("id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return lastID;
    }

    public String insertNewUser(String vorname, String nachname, String lieblingsessen, int alter) {
        List<String> columnList = new ArrayList<>();
        try (Connection conn = MySQLUtils.getConnection()) {
            String sqlStatement =  "INSERT INTO candidates(first_name,last_name,dob,phone,email) "
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sqlStatement, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet rs = statement.executeQuery(sqlStatement);

            int rowAffected = statement.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = statement.getGeneratedKeys();
                if(rs.next())
                    candidateId = rs.getInt(1);

            }

            while (rs.next()) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
//
//    public String updateUserInfo() {
//    }
//
//    private String addColumn(){
//    }
//
//    private String removeColumn(){
//    }
//
//    public String removeUser(){
//    }

}

