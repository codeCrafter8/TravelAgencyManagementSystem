package com.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa przyjmująca dane od serwera i łącząca się z bazą danych
 */
public class Database {
    /**
     * Atrybut pozwalający na wykonywanie zapytań do bazy danych
     */
    private Statement statement;
    /**
     * Atrybut pozwalający na połączenie się z bazą danych
     */
    private Connection connection;
    /**
     * Atrybut określający rodzaj wykonywanej operacji
     */
    private String operation;
    /**
     * Atrybut będący listą zawierającą dane przyjmowane od serwera
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą zawierającą dane zwracane do serwera
     */
    private final List<String> returningData = new ArrayList<>();
    /**
     * Konstruktor pozwalający na tworzenie instancji klasy
     * @param operation rodzaj wykonywanej operacji
     * @param data lista zawierająca dane przyjmowane od serwera
     */
    public Database(String operation, List<String> data){
        this.operation = operation;
        this.data.addAll(data);
    }
    /**
     * Metoda zwracająca listę zawierającą dane zwracane do serwera
     * @return Zwraca listę zawierającą dane zwracane do serwera
     */
    public List<String> getReturningData(){
        return returningData;
    }
    /**
     * Metoda pozwalająca na zainicjowanie parametrów połączenia z baza danych oraz wybór odpowiedniej operacji
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
                case "editClient" -> editClient();
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
                case "dataEdition" -> editData();
                case "sendNumbers" -> sendNumbers();
                case "getNumbers" -> getNumbers();
            }
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych numery klientów do kontaktu
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
     * Metoda zapisująca do bazy danych numery klientów do kontaktu
     */
    public void sendNumbers(){
        try{
            String query = "INSERT INTO numbers VALUES(numbers_seq.nextval,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,data.get(0));
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
     * Metoda zapisująca do bazy nowych danych nowe dane edytowanego użytkownika
     */
    public void editData() {
        try{
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(0));
            preparedState.setString(2, data.get(1));
            preparedState.setString(3, data.get(2));
            preparedState.setInt(4, Integer.parseInt(data.get(3)));
            preparedState.setInt(5, Integer.parseInt(data.get(4)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("com.server.Database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane klienta o ID: " + data.get(4) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("com.server.Database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych dane użytkownika
     */
    private void findClientData(){
        try{
            String adminQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(0));
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
     * Metoda dodająca do bazy danych nową wycieczkę
     */
    public void addTrip() {
        try {
            String query = "INSERT INTO trips VALUES (trips_seq.nextval, ?, ?, ?, ?, ?, ?, ?, 'Hotel to miejsce, w ktorym jedzenie to kwestia przygody i odkrywania nowych smakow. " +
                    "W naszej ofercie znajda Panstwo pokoje o roznym standardzie oraz opcje wyzywienia, w tym sniadania, obiady i kolacje, ktore są przygotowywane przez naszych szefow kuchni z pasja i zaangazowaniem. " +
                    "Nasza restauracja oferuje dania kuchni fusion, a nasza karta drinkow to prawdziwe arcydziela mixologii.', ?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setString(2, data.get(1));
            preparedStatement.setString(3, data.get(2));
            preparedStatement.setInt(4, Integer.parseInt(data.get(3)));
            preparedStatement.setDate(5, Date.valueOf(data.get(6)));
            preparedStatement.setDate(6, Date.valueOf(data.get(7)));
            preparedStatement.setInt(7, Integer.parseInt(data.get(4)));
            preparedStatement.setString(8, data.get(5));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał wycieczkę do : " + data.get(0) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda usuwająca z bazy danych wybraną rezerwację
     */
    private void deleteRes() {
        try{
            String selectIDTripQuery = "SELECT id_trip FROM reservations " +
                    "WHERE id_reservation = ?";
            PreparedStatement idTripPreparedState = connection.prepareStatement(selectIDTripQuery);
            idTripPreparedState.setInt(1, Integer.parseInt(data.get(0)));
            ResultSet result = idTripPreparedState.executeQuery();
            int id_trip = 0;
            while(result.next()) {
                id_trip = result.getInt("id_trip");
            }
            String query = "DELETE FROM reservations WHERE id_reservation = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(0)));
            preparedState.executeUpdate();
            String tripsUpdateQuery = "UPDATE trips SET reserved = 0 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, id_trip);
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął wycieczkę o ID: " + data.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych wszystkie rezerwacje
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
     * Metoda dodająca do bazy danych nową rezerwację
     */
    public void addReservation() {
        try {
            String query = "INSERT INTO reservations (id_reservation, id_trip, ID_user, people_quantity, insurance) VALUES (reservations_seq.nextval, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(data.get(0)));
            preparedStatement.setInt(2, Integer.parseInt(data.get(1)));
            preparedStatement.setInt(3, Integer.parseInt(data.get(2)));
            preparedStatement.setString(4, data.get(3));
            preparedStatement.executeUpdate();
            preparedStatement.close();
            String tripsUpdateQuery = "UPDATE trips SET reserved = 1 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, Integer.parseInt(data.get(0)));
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał rezerwację wycieczki o ID: " + Integer.parseInt(data.get(0)) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda usuwająca z bazy danych wybraną wycieczkę
     */
    public void deleteTrip() {
        try{
            String query = "DELETE FROM trips WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął wycieczkę o ID: " + data.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda zapisująca do bazy danych nowe dane edytowanej wycieczki
     */
    private void editTrip() {
        try{
            String query = "UPDATE trips " +
                    "SET city = ?, country = ?, price_per_person = ?, people_limit = ? " +
                    "WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            preparedState.setString(2, data.get(2));
            preparedState.setInt(3, Integer.parseInt(data.get(3)));
            preparedState.setInt(4, Integer.parseInt(data.get(4)));
            preparedState.setInt(5, Integer.parseInt(data.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane wycieczki o ID: " + data.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych dostępne kierunki podróży
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
     * Metoda pobierająca z bazy danych dostępne miasta wylotu/przylotu
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
     * Metoda pobierająca z bazy danych dostępne wycieczki
     */
    public void tripsListPopulate() {
        try {
            String query = "SELECT * FROM trips WHERE reserved = 0";
            ResultSet result = statement.executeQuery(query);
            int index;
            String departure;
            String arrival;
            while(result.next()){
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
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda zapisująca do bazy danych nowe hasło edytowanego użytkownika
     */
    public void changeClientPassword() {
        try{
            String query = "UPDATE users SET password = ? WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(0));
            preparedState.setInt(2, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zmienił hasło użytkownika o ID: " + data.get(1) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda usuwająca z bazy danych wybranego klienta
     */
    private void deleteClient() {
        try{
            String query = "DELETE FROM users WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął klienta o ID: " + data.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda zapisująca do bazy danych nowe dane edytowanego klienta
     */
    public void editClient() {
        try{
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(0));
            preparedState.setString(2, data.get(1));
            preparedState.setString(3, data.get(2));
            preparedState.setString(4, data.get(3));
            preparedState.setInt(5, Integer.parseInt(data.get(4)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane klienta o ID: " + data.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych imię administratora
     */
    private void findAdminName(){
        try{
            String adminQuery = "SELECT firstName FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(0));
            ResultSet resultAdmin = adminPreparedState.executeQuery();
            if(resultAdmin.next()){
                returningData.add(resultAdmin.getString("firstName"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych informacje wyświetlane w panelu administratora
     */
    public void updateDashboard() {
        try {
            findAdminName();
            String howManyClientsQuery = "SELECT COUNT(*) as clientsCount FROM users WHERE userRank = 'client'";
            ResultSet resultHowManyClients = statement.executeQuery(howManyClientsQuery);
            if(resultHowManyClients.next()) {
                returningData.add(Integer.toString(resultHowManyClients.getInt("clientsCount")));
            }
            String howManyTripsQuery = "SELECT COUNT(*) as tripsCount FROM trips";
            ResultSet resultHowManyTrips = statement.executeQuery(howManyTripsQuery);
            if(resultHowManyTrips.next()) {
                returningData.add(Integer.toString(resultHowManyTrips.getInt("tripsCount")));
            }
            String howManyReservationsQuery = "SELECT COUNT(*) as resCount FROM reservations";
            ResultSet resultHowManyReservations = statement.executeQuery(howManyReservationsQuery);
            if(resultHowManyReservations.next()) {
                returningData.add(Integer.toString(resultHowManyReservations.getInt("resCount")));
            }
            String howMuchIncomeQuery = "SELECT people_quantity, price_per_person FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip";
            ResultSet resultHowMuchIncome = statement.executeQuery(howMuchIncomeQuery);
            int incomeQuantity = 0;
            while(resultHowMuchIncome.next()) {
                int peopleQuantity = resultHowMuchIncome.getInt("people_quantity");
                int price = resultHowMuchIncome.getInt("price_per_person");
                incomeQuantity += peopleQuantity * price;
            }
            returningData.add(Integer.toString(incomeQuantity));
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych wszystkich klientów
     */
    public void updateClients() {
        try {
            String query = "SELECT * FROM users WHERE userRank = 'client'";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                returningData.add(Integer.toString(result.getInt("ID_user")));
                returningData.add(result.getString("firstName"));
                returningData.add(result.getString("lastName"));
                returningData.add(result.getString("email"));
                returningData.add(result.getString("phoneNumber"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych wszystkie wycieczki
     */
    public void updateTrips() {
        try {
            String query = "SELECT * FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                returningData.add(Integer.toString(result.getInt("id_trip")));
                returningData.add(result.getString("country"));
                returningData.add(result.getString("city"));
                returningData.add(Integer.toString(result.getInt("price_per_person")));
                returningData.add(Integer.toString(result.getInt("people_limit")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda dodająca do bazy danych nowego klienta
     */
    public void addClient(){
        try {
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(3));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                returningData.add("Tak");
            }
            else {
                returningData.add("Nie");
                String query = "INSERT INTO users (ID_user, email, password, firstName, lastName, phoneNumber, userLogged) VALUES (users_seq.nextval, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, data.get(3));
                preparedStatement.setString(2, data.get(4));
                preparedStatement.setString(3, data.get(0));
                preparedStatement.setString(4, data.get(1));
                preparedStatement.setString(5, data.get(2));
                preparedStatement.setInt(6, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                String commit = "COMMIT";
                statement.executeUpdate(commit);
                if(data.get(5).equals("true")){
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał użytkownika o e-mailu: " + data.get(3) + ".");
                }
            }
            emailPreparedState.close();
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda wypełniająca listę zawierającą dane zwracane do serwera
     */
    public void fillLoginReturningData(String startPageMessage, boolean isExisting){
        if(!isExisting) {
            returningData.add("false");
            returningData.add("false");
        }
        returningData.add("false");
        returningData.add("false");
        returningData.add(Integer.toString(-1));
        returningData.add(startPageMessage);
    }
    /**
     * Metoda pozwalająca na zalogowanie się pojedynczego użytkownika
     */
    public void login(){
        try{
            String startPageMessage = "";
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(0));
            ResultSet result = emailPreparedState.executeQuery();
            if(result.next()){
                returningData.add("true");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, data.get(1));
                passwordPreparedState.setString(2, data.get(0));
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if(resultPassword.next()){
                    returningData.add("true");
                    if(resultPassword.getInt("userLogged") == 0) {
                        String loggingQuery = "UPDATE users SET userLogged = 1 WHERE email = ?";
                        PreparedStatement loggingPreparedState = connection.prepareStatement(loggingQuery);
                        loggingPreparedState.setString(1, data.get(0));
                        loggingPreparedState.execute();
                        String commit = "COMMIT";
                        statement.executeUpdate(commit);
                        String resultPass = resultPassword.getString("userRank");
                        if (resultPass.equals("admin")) {
                            returningData.add("true");
                            returningData.add("false");
                            returningData.add(Integer.toString(resultPassword.getInt("ID_user"))); //offerUserID
                            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator o e-mailu: " + data.get(0) + " zalogował się.");
                        } else {
                            returningData.add("false");
                            returningData.add("true");
                            returningData.add(Integer.toString(resultPassword.getInt("ID_user"))); //offerUserID
                            new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + data.get(0) + " zalogował się.");
                        }
                        returningData.add(startPageMessage);
                    }
                    else {
                        startPageMessage = "Użytkownik jest już zalogowany.";
                        fillLoginReturningData(startPageMessage, true);
                        new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto użytkownika o e-mailu: " + data.get(0)+".");
                    }
                }
                else{
                    returningData.add("false");
                    fillLoginReturningData(startPageMessage, true);
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto użytkownika o e-mailu: " + data.get(0)+".");
                }
            }
            else{
                fillLoginReturningData(startPageMessage, false);
                new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto użytkownika o e-mailu: " + data.get(0)+".");
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pobierająca z bazy danych informacje wyświetlane w oknie Moje Konto
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
            preparedState.setString(1, data.get(0));
            ResultSet result = preparedState.executeQuery();
            while(result.next()){
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
                if(result.getString("insurance") != null)
                    returningData.add(result.getString("insurance"));
                else
                    returningData.add("Brak");
                returningData.add(result.getString("departure_city"));
                returningData.add(result.getString("hotel_name"));
                returningData.add(result.getString("description"));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pozwalająca na wylogowanie się pojedynczego użytkownika
     */
    public void logOut() {
        try {
            String query = "UPDATE users SET userLogged = 0 WHERE email = ?";
            PreparedStatement loggingPreparedState = connection.prepareStatement(query);
            loggingPreparedState.setString(1, data.get(0));
            loggingPreparedState.execute();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            String query2 = "SELECT userRank FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, data.get(0));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("userRank").equals("admin"))
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator o e-mailu: " + data.get(0) + " wylogował się.");
                else
                    new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + data.get(0) + " wylogował się.");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pozwalająca na wylogowanie wszystkich użytkowników
     */
    public void logOutEveryone() {
        try {
            String query = "UPDATE users SET userLogged = 0";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsServer("database", "info", "[ " + new java.util.Date() + " ] " + "Wylogowano wszystkich z serwera.");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
}