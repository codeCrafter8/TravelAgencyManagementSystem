package com.server.database.dao;

import com.server.database.DBContext;
import com.server.logs.LogsAdmins;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that performs operations on reservations table
 */
public class ReservationDao {

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
    public ReservationDao(List<String> data){
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            new LogsAdmins("ReservationDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał rezerwację wycieczki o ID: " + Integer.parseInt(data.get(1)) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves reservation data from the database
     * @return List of reservation data
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
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
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
            new LogsAdmins("ReservationDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął rezerwację o ID: " + data.get(1) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves all reservations from the database
     * @return List of reservations data
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
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves the reservations count
     * @return List of reservations count
     */
    public List<String> countAllReservations() {
        List<String> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String howManyReservationsQuery = "SELECT COUNT(*) as resCount FROM reservations";
            ResultSet resultHowManyReservations = statement.executeQuery(howManyReservationsQuery);
            if (resultHowManyReservations.next()) {
                list.add(Integer.toString(resultHowManyReservations.getInt("resCount")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves income data from the database
     * @return List of income data
     */
    public List<String> countIncome() {
        List<String> list = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String howMuchIncomeQuery = "SELECT people_quantity, price_per_person FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip";
            ResultSet resultHowMuchIncome = statement.executeQuery(howMuchIncomeQuery);
            int incomeQuantity = 0;
            while (resultHowMuchIncome.next()) {
                int peopleQuantity = resultHowMuchIncome.getInt("people_quantity");
                int price = resultHowMuchIncome.getInt("price_per_person");
                incomeQuantity += peopleQuantity * price;
            }
            list.add(Integer.toString(incomeQuantity));
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves all user reservations from the database
     * @return List of user reservations data
     */
    public List<String> findUserReservations() {
        List<String> list = new ArrayList<>();
        try {
            String query = "SELECT id_reservation, country, city, price_per_person, people_quantity, departure, arrival, insurance, departure_city, hotel_name, description FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user " +
                    "WHERE email = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            ResultSet result = preparedState.executeQuery();
            while (result.next()) {
                list.add(Integer.toString(result.getInt("id_reservation")));
                list.add(result.getString("country"));
                list.add(result.getString("city"));
                list.add(Integer.toString(result.getInt("price_per_person")));
                list.add(Integer.toString(result.getInt("people_quantity")));
                String departure = result.getString("departure");
                int index = departure.indexOf("00:");
                list.add(departure.substring(0, index - 1));
                String arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                list.add(arrival.substring(0, index - 1));
                if (result.getString("insurance") != null)
                    list.add(result.getString("insurance"));
                else
                    list.add("Brak");
                list.add(result.getString("departure_city"));
                list.add(result.getString("hotel_name"));
                list.add(result.getString("description"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ReservationDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
}
