package com.server.database.dao;

import com.server.logs.LogsAdmins;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TripDao {
    Connection connection;
    List<String> data;
    public TripDao(Connection connection, List<String> data){
        this.connection = connection;
        this.data = data;
    }
    /**
     * Adds a new trip to the database
     */
    public void addTrip() {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO trips VALUES (trips_seq.nextval, ?, ?, ?, ?, ?, ?, ?, 'Hotel is a place where eating is an adventure and discovering new flavors. " +
                    "We offer rooms of various standards and catering options, including breakfasts, lunches, and dinners, which are prepared by our passionate and dedicated chefs. " +
                    "Our restaurant offers fusion cuisine dishes, and our drink menu is a true masterpiece of mixology.', ?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.get(1));
            preparedStatement.setString(2, data.get(2));
            preparedStatement.setString(3, data.get(3));
            preparedStatement.setInt(4, Integer.parseInt(data.get(4)));
            preparedStatement.setDate(5, Date.valueOf(data.get(7)));
            preparedStatement.setDate(6, Date.valueOf(data.get(8)));
            preparedStatement.setInt(7, Integer.parseInt(data.get(5)));
            preparedStatement.setString(8, data.get(6));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator added a trip to: " + data.get(1) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Deletes a selected trip from the database
     */
    public void deleteTrip() {
        try{
            Statement statement = connection.createStatement();
            String query = "DELETE FROM trips WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator deleted a trip with ID: " + data.get(1) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Saves new data of an edited trip to the database
     */
    public void editTrip() {
        try{
            Statement statement = connection.createStatement();
            String query = "UPDATE trips " +
                    "SET city = ?, country = ?, price_per_person = ?, people_limit = ? " +
                    "WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(2));
            preparedState.setString(2, data.get(3));
            preparedState.setInt(3, Integer.parseInt(data.get(4)));
            preparedState.setInt(4, Integer.parseInt(data.get(5)));
            preparedState.setInt(5, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator edited trip data with ID: " + data.get(1) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves trip data from the database
     */
    public List<String> findTrip(){
        List<String> list = new ArrayList<>();
        int id = 0;
        try{
            Statement statement = connection.createStatement();
            if(data.get(1).equals("add")) {
                String query = "SELECT trips_seq.NEXTVAL FROM DUAL";
                ResultSet result = statement.executeQuery(query);
                if (result.next()) {
                    id = result.getInt("nextval") - 1;
                }
            }
            String findQuery = "SELECT * FROM trips WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(findQuery);
            if(data.get(1).equals("add"))
                preparedState.setInt(1, id);
            else
                preparedState.setInt(1, Integer.parseInt(data.get(2)));
            ResultSet result2 = preparedState.executeQuery();
            if(result2.next()){
                list.add(result2.getString("country"));
                list.add(result2.getString("city"));
                list.add(result2.getString("departure_city"));
                list.add(result2.getString("price_per_person"));
                list.add(String.valueOf(result2.getInt("people_limit")));
                list.add(result2.getString("hotel_name"));
                list.add(String.valueOf(result2.getDate("departure")));
                list.add(String.valueOf(result2.getDate("arrival")));
                list.add(String.valueOf(id));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves available travel destinations from the database
     */
    public List<String> getDestination() {
        List<String> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT DISTINCT country FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list.add(result.getString("country"));
            }
            result.close();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves available departure/arrival cities from the database
     */
    public List<String> getDeparture() {
        List<String> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT DISTINCT departure_city FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list.add(result.getString("departure_city"));
            }
            result.close();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves available trips from the database
     */
    public List<String> findUnreservedTrips() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM trips WHERE reserved = 0";
            ResultSet result = statement.executeQuery(query);
            int index;
            String departure;
            String arrival;
            while (result.next()) {
                list.add(result.getString("country"));
                list.add(result.getString("city"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                list.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                list.add(arrival.substring(0, index - 1));
                list.add(Integer.toString((result.getInt("price_per_person"))));
                list.add(result.getString("departure_city"));
                list.add(Integer.toString(result.getInt("people_limit")));
                list.add(Integer.toString(result.getInt("id_trip")));
                list.add(result.getString("description"));
                list.add(result.getString("hotel_name"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
    /**
     * Retrieves all trips from the database
     */
    public List<String> findAllTrips() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM trips";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                list.add(Integer.toString(result.getInt("id_trip")));
                list.add(result.getString("country"));
                list.add(result.getString("city"));
                list.add(Integer.toString(result.getInt("price_per_person")));
                list.add(Integer.toString(result.getInt("people_limit")));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
}
