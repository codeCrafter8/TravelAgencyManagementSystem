package com.server.database.dao;

import com.server.database.DBContext;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that performs operations on numbers table
 */
public class NumberDao {

    /**
     * An attribute allowing connection to the database
     */
    Connection connection;

    /**
     * An attribute being a list containing data received from the server
     */
    List<String> data;

    /**
     * A constructor allowing creating an instance of the class
     * @param data List containing data received from the server
     */
    public NumberDao(List<String> data){
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.data = data;
    }
    /**
     * Retrieves contact numbers of customers from the database
     * @return List of numbers
     */
    public List<String> getNumbers() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM numbers";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                list.add(resultSet.getString("phoneNumber"));
            }
            resultSet.close();
        } catch (SQLException ex) {
            new LogsServer("NumberDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
        return list;
    }

    /**
     * Saves contact numbers of customers to the database
     */
    public void sendNumbers(){
        try{
            Statement statement = connection.createStatement();
            String query = "INSERT INTO numbers VALUES(numbers_seq.nextval,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,data.get(1));
            preparedStatement.execute();
            preparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }
        catch(SQLException ex){
            new LogsServer("NumberDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }
}
