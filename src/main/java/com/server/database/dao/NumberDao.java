package com.server.database.dao;

import com.server.database.DBContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NumberDao {
    /**
     * An attribute allowing connection to the database
     */
    Connection connection;
    List<String> data;
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
