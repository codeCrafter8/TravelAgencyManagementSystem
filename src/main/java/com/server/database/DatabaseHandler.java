package com.server.database;

import com.server.database.dao.*;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that receives data from the server and connects to the database
 */
public class DatabaseHandler {

    /**
     * An attribute specifying the type of operation being performed
     */
    private final String operation;

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
        userDao = new UserDao(data);
        tripDao = new TripDao(data);
        reservationDao = new ReservationDao(data);
        numberDao = new NumberDao(data);
        performOperation();
    }

    /**
     * Returns a list containing data returned to the server
     * @return Returns a list containing data returned to the server
     */
    public List<String> getReturningData(){
        return returningData;
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
        returningData.addAll(userDao.findAdminName());
        returningData.addAll(userDao.countAllClients());
        returningData.addAll(tripDao.countAllTrips());
        returningData.addAll(reservationDao.countAllReservations());
        returningData.addAll(reservationDao.countIncome());
    }

    /**
     * Retrieves information displayed in the "My Account" window from the database
     */
    public void updateMyAccount() {
        returningData.addAll(userDao.findUser());
        returningData.addAll(reservationDao.findUserReservations());
    }
}