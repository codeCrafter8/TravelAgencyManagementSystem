package com.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that receives data from the server and connects to the database
 */
public class Database {
    /**
     * Attribute allowing executing queries to the database
     */
    private Statement statement;
    /**
     * Attribute allowing connection to the database
     */
    private Connection connection;
    /**
     * Attribute specifying the type of operation being performed
     */
    private final String operation;
    /**
     * Attribute being a list containing data received from the server
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Attribute being a list containing data returned to the server
     */
    private final List<String> returningData = new ArrayList<>();
    /**
     * Constructor allowing creating an instance of the class
     * @param operation The type of operation being performed
     * @param data List containing data received from the server
     */
    public Database(String operation, List<String> data){
        this.operation = operation;
        this.data.addAll(data);
        connectWithDatabase();
    }
    /**
     * Method returning a list containing data returned to the server
     * @return Returns a list containing data returned to the server
     */
    public List<String> getReturningData(){
        return returningData;
    }
    /**
     * Method allowing initializing connection parameters with the database and choosing the appropriate operation
     */
    public void connectWithDatabase() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
            switch (operation) {
                case "addClient" -> addClient();
                case "login" -> login();
                case "dashboardUpdate" -> updateDashboard();
                case "clientsUpdate" -> updateClients();
                case "deleteClient" -> deleteClient();
                case "editClient", "dataEdition" -> editData();
                case "changeClientPassword" -> changeClientPassword();
                case "logOutEveryone" -> logOutEveryone();
                case "logOut" -> logOut();
                case "tripsListPopulation" -> tripsListPopulate();
                case "getDestination" -> getDestination();
                case "getDeparture" -> getDeparture();
                case "tripsUpdate" -> updateTrips();
                case "deleteTrip" -> deleteTrip();
                case "editTrip" -> editTrip();
                case "addReservation" -> addReservation();
                case "resUpdate" -> updateRes();
                case "deleteRes" -> deleteRes();
                case "addTrip" -> addTrip();
                case "myAccountUpdate" -> updateMyAccount();
                case "sendNumbers" -> sendNumbers();
                case "getNumbers" -> getNumbers();
            }
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Method retrieving user data from the database
     */
    public void findClientData(){
        try{
            String adminQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(1));
            ResultSet result = adminPreparedState.executeQuery();
            if(result.next()){
                returningData.add(result.getString("firstname"));
                returningData.add(result.getString("lastname"));
                returningData.add(result.getString("email"));
                returningData.add(result.getString("phonenumber"));
                returningData.add(String.valueOf(result.getInt("ID_user")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Method retrieving from the database information whether the user is logged in
     * @return true if the user is logged in, false if not
     */
    public boolean getUserLogged(){
        int userLogged = 0;
        try {
            String query = "SELECT userLogged FROM users WHERE email = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            ResultSet result = preparedState.executeQuery();
            if (result.next()) {
                userLogged = result.getInt("userLogged");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return userLogged != 0;
    }
    /**
     * Method retrieving trip data from the database
     */
    public void findTrip(){
        int id = 0;
        try{
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
                returningData.add(result2.getString("country"));
                returningData.add(result2.getString("city"));
                returningData.add(result2.getString("departure_city"));
                returningData.add(result2.getString("price_per_person"));
                returningData.add(String.valueOf(result2.getInt("people_limit")));
                returningData.add(result2.getString("hotel_name"));
                returningData.add(String.valueOf(result2.getDate("departure")));
                returningData.add(String.valueOf(result2.getDate("arrival")));
                returningData.add(String.valueOf(id));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Method retrieving reservations data from the database
     */
    public void findReservation(){
        try{
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
                returningData.add(result2.getString("id_reservation"));
                returningData.add(result2.getString("id_trip"));
                returningData.add(result2.getString("id_user"));
                returningData.add(result2.getString("people_quantity"));
                returningData.add(result2.getString("insurance"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving contact numbers of customers from the database
     */
    public void getNumbers() {
        try {
            String query = "SELECT * FROM numbers";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                returningData.add(resultSet.getString("phoneNumber"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method saving contact numbers of customers to the database
     */
    public void sendNumbers(){
        try{
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

    /**
     * Method adding a new trip to the database
     */
    public void addTrip() {
        try {
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
     * Method deleting a selected reservation from the database
     */
    private void deleteRes() {
        try{
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
     * Method retrieving all reservations from the database
     */
    private void updateRes() {
        String departure;
        String arrival;
        int index;
        try {
            String query = "SELECT id_reservation, firstname, lastname, departure, arrival, phonenumber, reserved FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                returningData.add(Integer.toString(result.getInt("id_reservation")));
                returningData.add(result.getString("firstname"));
                returningData.add(result.getString("lastname"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                returningData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                returningData.add(arrival.substring(0, index - 1));
                returningData.add(result.getString("phonenumber"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method adding a new reservation to the database
     */
    public void addReservation() {
        try {
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
     * Method deleting a selected trip from the database
     */
    public void deleteTrip() {
        try{
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
     * Method saving new data of an edited trip to the database
     */
    private void editTrip() {
        try{
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
     * Method retrieving available travel destinations from the database
     */
    public void getDestination() {
        try{
            String query = "SELECT DISTINCT country FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                returningData.add(result.getString("country"));
            }
            result.close();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving available departure/arrival cities from the database
     */
    private void getDeparture() {
        try{
            String query = "SELECT DISTINCT departure_city FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                returningData.add(result.getString("departure_city"));
            }
            result.close();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Method retrieving available trips from the database
     */
    public void tripsListPopulate() {
        try {
            String query = "SELECT * FROM trips WHERE reserved = 0";
            ResultSet result = statement.executeQuery(query);
            int index;
            String departure;
            String arrival;
            while (result.next()) {
                returningData.add(result.getString("country"));
                returningData.add(result.getString("city"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                returningData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                returningData.add(arrival.substring(0, index - 1));
                returningData.add(Integer.toString((result.getInt("price_per_person"))));
                returningData.add(result.getString("departure_city"));
                returningData.add(Integer.toString(result.getInt("people_limit")));
                returningData.add(Integer.toString(result.getInt("id_trip")));
                returningData.add(result.getString("description"));
                returningData.add(result.getString("hotel_name"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method saving the new password of an edited user to the database
     */
    public void changeClientPassword() {
        try {
            String query = "UPDATE users SET password = ? WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            preparedState.setInt(2, Integer.parseInt(data.get(2)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator changed the password of user with ID: " + data.get(2) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method deleting a selected client from the database
     */
    private void deleteClient() {
        try {
            String query = "DELETE FROM users WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator deleted a client with ID: " + data.get(1) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method saving new data of an edited user to the database
     */
    public void editData() {
        try {
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            preparedState.setString(2, data.get(2));
            preparedState.setString(3, data.get(3));
            preparedState.setInt(4, Integer.parseInt(data.get(4)));
            preparedState.setInt(5, Integer.parseInt(data.get(5)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            if (Boolean.parseBoolean(data.get(6)))
                new LogsAdmins("com.server.Database", "info", "[ " + new java.util.Date() + " ] " + "Administrator edited user data with ID: " + data.get(5) + ".");
            else
                new LogsClients("com.server.Database", "info", "[ " + new java.util.Date() + " ] " + "Client with ID: " + data.get(5) + " edited their data.");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("com.server.Database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving the administrator's first name from the database
     */
    private void findAdminName() {
        try {
            String adminQuery = "SELECT firstName FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(1));
            ResultSet resultAdmin = adminPreparedState.executeQuery();
            if (resultAdmin.next()) {
                returningData.add(resultAdmin.getString("firstName"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving information displayed in the administrator's dashboard from the database
     */
    public void updateDashboard() {
        try {
            findAdminName();
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
     * Method retrieving all clients from the database
     */
    public void updateClients() {
        try {
            String query = "SELECT * FROM users WHERE userRank = 'client'";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                returningData.add(Integer.toString(result.getInt("ID_user")));
                returningData.add(result.getString("firstName"));
                returningData.add(result.getString("lastName"));
                returningData.add(result.getString("email"));
                returningData.add(result.getString("phoneNumber"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving all trips from the database
     */
    public void updateTrips() {
        try {
            String query = "SELECT * FROM trips";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                returningData.add(Integer.toString(result.getInt("id_trip")));
                returningData.add(result.getString("country"));
                returningData.add(result.getString("city"));
                returningData.add(Integer.toString(result.getInt("price_per_person")));
                returningData.add(Integer.toString(result.getInt("people_limit")));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method adding a new client to the database
     */
    public void addClient() {
        try {
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(4));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                returningData.add("Tak");
            } else {
                returningData.add("Nie");
                String query = "INSERT INTO users (ID_user, email, password, firstName, lastName, phoneNumber, userLogged) VALUES (users_seq.nextval, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, data.get(4));
                preparedStatement.setString(2, data.get(5));
                preparedStatement.setString(3, data.get(1));
                preparedStatement.setString(4, data.get(2));
                preparedStatement.setString(5, data.get(3));
                preparedStatement.setInt(6, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                String commit = "COMMIT";
                statement.executeUpdate(commit);
                if (data.get(6).equals("true")) {
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator added a user with email: " + data.get(4) + ".");
                }
            }
            emailPreparedState.close();
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator added a client with email: " + data.get(4) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method filling the list containing data returned to the server
     * @param startPageMessage message sent to the StartPage class
     * @param isExisting information whether the user already exists in the database
     */
    public void fillLoginReturningData(String startPageMessage, boolean isExisting) {
        if (!isExisting) {
            returningData.add("false");
            returningData.add("false");
        }
        returningData.add("false");
        returningData.add("false");
        returningData.add(Integer.toString(-1));
        returningData.add(startPageMessage);
    }

    /**
     * Method allowing a single user to log in
     */
    public void login() {
        try {
            String startPageMessage = "";
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(1));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                returningData.add("true");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, data.get(2));
                passwordPreparedState.setString(2, data.get(1));
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if (resultPassword.next()) {
                    returningData.add("true");
                    if (resultPassword.getInt("userLogged") == 0) {
                        String loggingQuery = "UPDATE users SET userLogged = 1 WHERE email = ?";
                        PreparedStatement loggingPreparedState = connection.prepareStatement(loggingQuery);
                        loggingPreparedState.setString(1, data.get(1));
                        loggingPreparedState.execute();
                        String commit = "COMMIT";
                        statement.executeUpdate(commit);
                        String resultPass = resultPassword.getString("userRank");
                        if (resultPass.equals("admin")) {
                            returningData.add("true");
                            returningData.add("false");
                            returningData.add(Integer.toString(resultPassword.getInt("ID_user")));
                            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator with email: " + data.get(1) + " logged in.");
                        } else {
                            returningData.add("false");
                            returningData.add("true");
                            returningData.add(Integer.toString(resultPassword.getInt("ID_user")));
                            new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Client with email: " + data.get(1) + " logged in.");
                        }
                        returningData.add(startPageMessage);
                    } else {
                        startPageMessage = "The user is already logged in.";
                        fillLoginReturningData(startPageMessage, true);
                        new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Failed attempt to log in to the account with email: " + data.get(1) + ".");
                    }
                } else {
                    returningData.add("false");
                    fillLoginReturningData(startPageMessage, true);
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Failed attempt to log in to the account with email: " + data.get(1) + ".");
                }
            } else {
                fillLoginReturningData(startPageMessage, false);
                new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Failed attempt to log in to the account with email: " + data.get(1) + ".");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method retrieving information displayed in the "My Account" window from the database
     */
    public void updateMyAccount() {
        findClientData();
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

    /**
     * Method allowing a single user to log out
     */
    public void logOut() {
        try {
            String query = "UPDATE users SET userLogged = 0 WHERE email = ?";
            PreparedStatement loggingPreparedState = connection.prepareStatement(query);
            loggingPreparedState.setString(1, data.get(1));
            loggingPreparedState.execute();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            String query2 = "SELECT userRank FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, data.get(1));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("userRank").equals("admin"))
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator with email: " + data.get(1) + " logged out.");
                else
                    new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Client with email: " + data.get(1) + " logged out.");
            }

        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Method allowing all users to log out
     */
    public void logOutEveryone() {
        try {
            String query = "UPDATE users SET userLogged = 0";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsServer("database", "info", "[ " + new java.util.Date() + " ] " + "Logged out everyone from the server.");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
}