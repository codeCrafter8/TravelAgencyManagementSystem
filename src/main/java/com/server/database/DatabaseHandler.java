package com.server.database;

import com.server.database.dao.*;
import com.server.logs.LogsAdmins;
import com.server.logs.LogsClients;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that receives data from the server and connects to the database
 */
public class DatabaseHandler {
    /**
     * An attribute allowing executing queries to the database
     */
    protected Statement statement;

    /**
     * An attribute allowing connection to the database
     */
    protected Connection connection;

    /**
     * An attribute specifying the type of operation being performed
     */
    private String operation;

    /**
     * An attribute being a list containing data received from the server
     */
    protected final List<String> data = new ArrayList<>();

    /**
     * An attribute being a list containing data returned to the server
     */
    protected List<String> returningData = new ArrayList<>();
    private final UserDao userDao;
    private final TripDao tripDao;
    private final ReservationDao reservationDao;
    private final NumberDao numberDao;

    /**
     * A constructor allowing creating an instance of the class
     * @param operation The type of operation being performed
     * @param data List containing data received from the server
     */
    public DatabaseHandler(String operation, List<String> data) {
        this.operation = operation;
        this.data.addAll(data);
        establishConnection();
        userDao = new UserDao(connection, data);
        tripDao = new TripDao(connection, data);
        reservationDao = new ReservationDao(connection, data);
        numberDao = new NumberDao(connection, data);
        performOperation();
    }

    /**
     * Returns a list containing data returned to the server
     * @return Returns a list containing data returned to the server
     */
    public List<String> getReturningData(){
        return returningData;
    }
    public void establishConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Allows initializing connection parameters with the database and choosing the appropriate operation
     */
    public void performOperation() {
        try {
            switch (operation) {
                case "addClient" -> returningData = userDao.addUser();
                case "login" -> returningData = userDao.login();
                case "dashboardUpdate" -> updateDashboard();
                case "clientsUpdate" -> returningData = userDao.findAllClients();
                case "deleteClient" -> userDao.deleteUser();
                case "editClient", "dataEdition" -> userDao.editUser();
                case "changeClientPassword" -> userDao.changeUserPassword();
                case "logOutEveryone" -> userDao.logAllUsersOut();
                case "logOut" -> userDao.logUserOut();
                case "tripsListPopulation" -> returningData = tripDao.findUnreservedTrips();
                case "getDestination" -> returningData = tripDao.getDestination();
                case "getDeparture" -> returningData = tripDao.getDeparture();
                case "tripsUpdate" -> returningData = tripDao.findAllTrips();
                case "deleteTrip" -> tripDao.deleteTrip();
                case "editTrip" -> tripDao.editTrip();
                case "addReservation" -> reservationDao.addReservation();
                case "resUpdate" -> returningData = reservationDao.findAllReservations();
                case "deleteRes" -> reservationDao.deleteReservation();
                case "addTrip" -> tripDao.addTrip();
                case "myAccountUpdate" -> updateMyAccount();
                case "sendNumbers" -> numberDao.sendNumbers();
                case "getNumbers" -> returningData = numberDao.getNumbers();
                case "findClientData" -> returningData = userDao.findUser();
                case "findTrip" -> returningData = tripDao.findTrip();
                case "findReservation" -> returningData = reservationDao.findReservation();
                case "getUserLogged" -> returningData = userDao.getUserLogged();
            }
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves information displayed in the administrator's dashboard from the database
     */
    public void updateDashboard() {
        try {
            returningData.addAll(userDao.findAdminName());
            String howManyClientsQuery = "SELECT COUNT(*) as clientsCount FROM users WHERE userRank = 'client'";
            ResultSet resultHowManyClients = statement.executeQuery(howManyClientsQuery);
            if (resultHowManyClients.next()) {
                returningData.add(Integer.toString(resultHowManyClients.getInt("clientsCount")));
            }
            String howManyTripsQuery = "SELECT COUNT(*) as tripsCount FROM trips";
            ResultSet resultHowManyTrips = statement.executeQuery(howManyTripsQuery);
            if (resultHowManyTrips.next()) {
                returningData.add(Integer.toString(resultHowManyTrips.getInt("tripsCount")));
            }
            String howManyReservationsQuery = "SELECT COUNT(*) as resCount FROM reservations";
            ResultSet resultHowManyReservations = statement.executeQuery(howManyReservationsQuery);
            if (resultHowManyReservations.next()) {
                returningData.add(Integer.toString(resultHowManyReservations.getInt("resCount")));
            }
            String howMuchIncomeQuery = "SELECT people_quantity, price_per_person FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip";
            ResultSet resultHowMuchIncome = statement.executeQuery(howMuchIncomeQuery);
            int incomeQuantity = 0;
            while (resultHowMuchIncome.next()) {
                int peopleQuantity = resultHowMuchIncome.getInt("people_quantity");
                int price = resultHowMuchIncome.getInt("price_per_person");
                incomeQuantity += peopleQuantity * price;
            }
            returningData.add(Integer.toString(incomeQuantity));
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves information displayed in the "My Account" window from the database
     */
    public void updateMyAccount() {
        returningData.addAll(userDao.findUser());
        String departure;
        String arrival;
        int index;
        try {
            String query = "SELECT id_reservation, country, city, price_per_person, people_quantity, departure, arrival, insurance, departure_city, hotel_name, description FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user " +
                    "WHERE email = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            ResultSet result = preparedState.executeQuery();
            while (result.next()) {
                returningData.add(Integer.toString(result.getInt("id_reservation")));
                returningData.add(result.getString("country"));
                returningData.add(result.getString("city"));
                returningData.add(Integer.toString(result.getInt("price_per_person")));
                returningData.add(Integer.toString(result.getInt("people_quantity")));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                returningData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                returningData.add(arrival.substring(0, index - 1));
                if (result.getString("insurance") != null)
                    returningData.add(result.getString("insurance"));
                else
                    returningData.add("Brak");
                returningData.add(result.getString("departure_city"));
                returningData.add(result.getString("hotel_name"));
                returningData.add(result.getString("description"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
}