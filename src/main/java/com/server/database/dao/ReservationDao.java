package com.server.database.dao;

import com.server.logs.LogsAdmins;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {
    Connection connection;
    List<String> data;
    public ReservationDao(Connection connection, List<String> data){
        this.connection = connection;
        this.data = data;
    }
    /**
     * Adds a new reservation to the database
     */
    public void addReservation() {
        try {
            Statement statement = connection.createStatement();
            String query = "INSERT INTO reservations (id_reservation, id_trip, ID_user, people_quantity, insurance) VALUES (reservations_seq.nextval, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(data.get(1)));
            preparedStatement.setInt(2, Integer.parseInt(data.get(2)));
            preparedStatement.setInt(3, Integer.parseInt(data.get(3)));
            preparedStatement.setString(4, data.get(4));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            String tripsUpdateQuery = "UPDATE trips SET reserved = 1 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, Integer.parseInt(data.get(1)));
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator added a reservation for trip ID: " + Integer.parseInt(data.get(1)) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Retrieves reservations data from the database
     */
    public List<String> findReservation(){
        List<String> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT reservations_seq.NEXTVAL FROM DUAL";
            ResultSet result = statement.executeQuery(query);
            int id = 0;
            if(result.next()){
                id = result.getInt("nextval") - 1;
            }
            String findQuery = "SELECT * FROM reservations WHERE id_reservation = ?";
            PreparedStatement preparedState = connection.prepareStatement(findQuery);
            preparedState.setInt(1, id);
            ResultSet result2 = preparedState.executeQuery();
            if(result2.next()){
                list.add(result2.getString("id_reservation"));
                list.add(result2.getString("id_trip"));
                list.add(result2.getString("id_user"));
                list.add(result2.getString("people_quantity"));
                list.add(result2.getString("insurance"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
    /**
     * Deletes a selected reservation from the database
     */
    public void deleteReservation() {
        try{
            Statement statement = connection.createStatement();
            String selectIDTripQuery = "SELECT id_trip FROM reservations " +
                    "WHERE id_reservation = ?";
            PreparedStatement idTripPreparedState = connection.prepareStatement(selectIDTripQuery);
            idTripPreparedState.setInt(1, Integer.parseInt(data.get(1)));
            ResultSet result = idTripPreparedState.executeQuery();
            int id_trip = 0;
            while(result.next()) {
                id_trip = result.getInt("id_trip");
            }

            String query = "DELETE FROM reservations WHERE id_reservation = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String tripsUpdateQuery = "UPDATE trips SET reserved = 0 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, id_trip);
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator deleted a reservation with ID: " + data.get(1) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves all reservations from the database
     */
    public List<String> findAllReservations() {
        String departure;
        String arrival;
        int index;
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id_reservation, firstname, lastname, departure, arrival, phonenumber, reserved FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                list.add(Integer.toString(result.getInt("id_reservation")));
                list.add(result.getString("firstname"));
                list.add(result.getString("lastname"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                list.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                list.add(arrival.substring(0, index - 1));
                list.add(result.getString("phonenumber"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
}
