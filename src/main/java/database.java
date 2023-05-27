import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class database {
    static Statement statement;
    static Connection connection;
    private static String adminName = "";
    static List<String> data = new ArrayList<>();
    static List<String> tripsData = new ArrayList<>();
    static List<String> tripsAdminData = new ArrayList<>();
    static List<String> resAdminData = new ArrayList<>();
    static List<String> clientData = new ArrayList<>();
    static List<String> resData = new ArrayList<>();
    public static void connect_with_database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
            System.out.println("hello d");
            System.out.println("OPERATION:" + ServerGUI.operation);
            switch (ServerGUI.operation) {
                case "Register" -> send_data();
                case "Login" -> login();
                case "dashboardUpdate" -> updateDashboard();
                case "clientsUpdate" -> updateClients();
                case "deleteClient" -> deleteClient();
                case "editClient" -> editClient();
                case "changeClientPassword" -> changeClientPassword();
                case "deleteTable" -> deleteTable();
                case "checkTables" -> checkTables();
                case "checkSequences" -> checkSequences();
                case "deleteSequence" -> deleteSequence();
                case "addSequence" -> addSequence();
                case "addTable" -> addTable();
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
                case "editRes" -> editRes();
                case "addTrip" -> addTrip();
                case "myAccountUpdate" -> updateMyAccount();
            }
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void updateMyAccount() {
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
            preparedState.setString(1, ServerGUI.email);
            ResultSet result = preparedState.executeQuery();
            while(result.next()){
                resData.add(Integer.toString(result.getInt("id_reservation")));
                resData.add(result.getString("country"));
                resData.add(result.getString("city"));
                resData.add(Integer.toString(result.getInt("price_per_person")));
                resData.add(Integer.toString(result.getInt("people_quantity")));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                resData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                resData.add(arrival.substring(0, index - 1));
                if(result.getString("insurance") != null)
                    resData.add(result.getString("insurance"));
                else
                    resData.add("Brak");
                resData.add(result.getString("departure_city"));
                resData.add(result.getString("hotel_name"));
                resData.add(result.getString("description"));
            }
            MyAccount.resDataListLength = resData.size();
            for (String s : database.resData) {
                System.out.println(s);
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void findClientData(){
        try{
            String adminQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, ServerGUI.password);
            adminPreparedState.setString(2, ServerGUI.email);
            ResultSet result = adminPreparedState.executeQuery();
            if(result.next()){
                clientData.add(result.getString("firstname"));
                clientData.add(result.getString("lastname"));
                clientData.add(result.getString("email"));
                clientData.add(result.getString("phonenumber"));
                clientData.add(String.valueOf(result.getInt("ID_user")));
            }
            MyAccount.clientDataListLength = clientData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void addTrip() {
        try {
            String query = "INSERT INTO trips VALUES (trips_seq.nextval, ?, ?, ?, ?, ?, ?, ?, 'Hotel to miejsce, w ktorym jedzenie to kwestia przygody i odkrywania nowych smakow. " +
                    "W naszej ofercie znajda Panstwo pokoje o roznym standardzie oraz opcje wyzywienia, w tym sniadania, obiady i kolacje, ktore są przygotowywane przez naszych szefow kuchni z pasja i zaangazowaniem. " +
                    "Nasza restauracja oferuje dania kuchni fusion, a nasza karta drinkow to prawdziwe arcydziela mixologii.', ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ServerGUI.countryAddTrip);
            preparedStatement.setString(2, ServerGUI.cityAddTrip);
            preparedStatement.setString(3, ServerGUI.departureCityAddTrip);
            preparedStatement.setInt(4, Integer.parseInt(ServerGUI.priceAddTrip));
            preparedStatement.setDate(5, Date.valueOf(ServerGUI.departureAddTrip));
            preparedStatement.setDate(6, Date.valueOf(ServerGUI.arrivalAddTrip));
            preparedStatement.setInt(7, Integer.parseInt(ServerGUI.peopleLimitAddTrip));
            preparedStatement.setString(8, ServerGUI.hotelNameAddTrip);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał wycieczkę do : " + ServerGUI.cityAddTrip + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void editRes() {
        try{
            String query = "UPDATE reservations " +
                    "SET firstname = ?, lastname = ?, departure = ?, arrival = ?, phonenumber = ? FROM reservations " +
                    "WHERE id_reservation = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, ServerGUI.resEditList.get(1));
            preparedState.setString(2, ServerGUI.resEditList.get(2));
            preparedState.setDate(3, Date.valueOf(ServerGUI.resEditList.get(3)));
            preparedState.setDate(4, Date.valueOf(ServerGUI.resEditList.get(4)));
            preparedState.setString(5, ServerGUI.resEditList.get(5));
            preparedState.setInt(6, Integer.parseInt(ServerGUI.resEditList.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane rezerwacji o ID: " + ServerGUI.resEditList.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void deleteRes() {
        try{
            String selectIDTripQuery = "SELECT id_trip FROM reservations " +
                    "WHERE id_reservation = ?";
            PreparedStatement idTripPreparedState = connection.prepareStatement(selectIDTripQuery);
            idTripPreparedState.setInt(1, ServerGUI.resIDToRemove);
            ResultSet result = idTripPreparedState.executeQuery();
            int id_trip = 0;
            while(result.next()) {
                id_trip = result.getInt("id_trip");
                System.out.println(id_trip);
            }
            String query = "DELETE FROM reservations WHERE id_reservation = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, ServerGUI.resIDToRemove);
            preparedState.executeUpdate();
            String tripsUpdateQuery = "UPDATE trips SET reserved = 0 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, id_trip);
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął wycieczkę o ID: " + ServerGUI.tripIDToRemove + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void updateRes() {
        String departure;
        String arrival;
        int index;
        try {
            System.out.println("database przechodzi");
            String query = "SELECT id_reservation, firstname, lastname, departure, arrival, phonenumber, reserved FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                resAdminData.add(Integer.toString(result.getInt("id_reservation")));
                resAdminData.add(result.getString("firstname"));
                resAdminData.add(result.getString("lastname"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                resAdminData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                resAdminData.add(arrival.substring(0, index - 1));
                resAdminData.add(result.getString("phonenumber"));
            }
            Rezerwacje.listDataLength = resAdminData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    public static void addReservation() {
        System.out.println("przed try");
        try {
            System.out.println("hej");
            String query = "INSERT INTO reservations (id_reservation, id_trip, ID_user, people_quantity, insurance) VALUES (reservations_seq.nextval, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ServerGUI.tripIdToRes);
            preparedStatement.setInt(2, ServerGUI.userIdToRes);
            preparedStatement.setInt(3, ServerGUI.peopleQuantity);
            preparedStatement.setString(4, ServerGUI.insurance);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("dodano rezerwacje");
            String tripsUpdateQuery = "UPDATE trips SET reserved = 1 WHERE id_trip = ?";
            PreparedStatement updateTripsPreparedStatement = connection.prepareStatement(tripsUpdateQuery);
            updateTripsPreparedStatement.setInt(1, ServerGUI.tripIdToRes);
            updateTripsPreparedStatement.executeUpdate();
            updateTripsPreparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał rezerwację wycieczki o ID: " + ServerGUI.tripIdToRes + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void deleteTrip() {
        try{
            String query = "DELETE FROM trips WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, ServerGUI.tripIDToRemove);
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął wycieczkę o ID: " + ServerGUI.tripIDToRemove + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void editTrip() {
        try{
            String query = "UPDATE trips " +
                    "SET city = ?, country = ?, price_per_person = ?, people_limit = ? " +
                    "WHERE id_trip = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, ServerGUI.tripEditList.get(1));
            preparedState.setString(2, ServerGUI.tripEditList.get(2));
            preparedState.setInt(3, Integer.parseInt(ServerGUI.tripEditList.get(3)));
            preparedState.setInt(4, Integer.parseInt(ServerGUI.tripEditList.get(4)));
            preparedState.setInt(5, Integer.parseInt(ServerGUI.tripEditList.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane wycieczki o ID: " + ServerGUI.tripEditList.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void getDestination() {
        try{
            String query = "SELECT DISTINCT country FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                ServerGUI.destination.add(result.getString("country"));
            }
            WYSZUKIWARKA.destinationListLength = ServerGUI.destination.size();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void getDeparture() {
        try{
            String query = "SELECT DISTINCT departure_city FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                ServerGUI.departure.add(result.getString("departure_city"));
            }
            WYSZUKIWARKA.departureListLength = ServerGUI.departure.size();
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void tripsListPopulate() {
        try {
            String query = "SELECT * FROM trips WHERE reserved = 0";
            ResultSet result = statement.executeQuery(query);
            int index;
            String departure;
            String arrival;
            while(result.next()){
                // (id_trip NUMBER(3),country VARCHAR2(50),city VARCHAR2(50),departure_city VARCHAR2(50),price_per_person NUMBER(6),
                // departure DATE,arrival DATE,people_limit NUMBER(2),description VARCHAR2(500))
                tripsData.add(result.getString("country"));
                tripsData.add(result.getString("city"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                tripsData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                tripsData.add(arrival.substring(0, index - 1));
                tripsData.add(Integer.toString((result.getInt("price_per_person"))));
                tripsData.add(result.getString("departure_city"));
                tripsData.add(Integer.toString(result.getInt("people_limit")));
                tripsData.add(Integer.toString(result.getInt("id_trip")));
                tripsData.add(result.getString("description"));
                tripsData.add(result.getString("hotel_name"));
            }
            WYSZUKIWARKA.listDataLength = tripsData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void logOut() {
        try {
            String query = "UPDATE users SET userLogged = 0 WHERE email = ?";
            PreparedStatement loggingPreparedState = connection.prepareStatement(query);
            loggingPreparedState.setString(1, ServerGUI.email);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            ServerGUI.usersCounter--;
            String query2 = "SELECT userRank FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, ServerGUI.email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString("userRank").equals("admin"))
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator o e-mailu: " + ServerGUI.email + " wylogował się.");
                else
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + ServerGUI.email + " wylogował się.");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void logOutEveryone() {
        try {
            String query = "UPDATE users SET userLogged = 0";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            ServerGUI.usersCounter = 0;
            new LogsServer("database", "info", "[ " + new java.util.Date() + " ] " + "Wylogowano wszystkich z serwera.");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void addTable() {
        try{
            String query = "";
            int index = ServerGUI.tableName.indexOf("(usunięta)");
            switch(ServerGUI.tableName.substring(0, index - 1)){
                case "users" -> {
                    query = "CREATE TABLE " + ServerGUI.tableName + " (ID_user NUMBER(6) PRIMARY KEY, email VARCHAR(70), password VARCHAR(70), " +
                            "firstName VARCHAR(70), lastName VARCHAR(70), phoneNumber VARCHAR(25), userLogged NUMBER(6))";
                }
                case "trips" -> {
                    query = "CREATE TABLE " + ServerGUI.tableName.substring(0, index - 1) + "(id_trip NUMBER(3), country VARCHAR2(50), " +
                            "city VARCHAR2(50), departure_city VARCHAR2(50), price_per_person NUMBER(6), departure DATE, arrival DATE, " +
                            "people_limit NUMBER(2), description VARCHAR2(800), hotel_name VARCHAR2(50), reserved NUMBER(1), " +
                            "CONSTRAINT id_trip_pk PRIMARY KEY (id_trip))";
                }
                case "reservations" -> {
                    query = "CREATE TABLE " + ServerGUI.tableName.substring(0, index - 1) + "(id_reservation NUMBER(3), id_trip NUMBER, " +
                            "ID_user NUMBER, people_quantity NUMBER(3), insurance VARCHAR2(50), CONSTRAINT fk_id_trip FOREIGN KEY (id_trip) REFERENCES trips(id_trip), " +
                            "CONSTRAINT fk_id_user FOREIGN KEY (ID_user) REFERENCES users(ID_user))";
                }
            }
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał tabelę " + ServerGUI.tableName + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void addSequence() {
        try{
            int index = ServerGUI.seqName.indexOf("(usunięta)");
            String query = "CREATE SEQUENCE " + ServerGUI.seqName.substring(0, index);
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał sekwencję " + ServerGUI.seqName + ".");
        }catch (SQLException ex) {
            System.out.println("Ex add: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void deleteSequence() {
        try{
            String query = "DROP SEQUENCE " + ServerGUI.seqName;
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął sekwencję " + ServerGUI.seqName + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void checkTables() {
        checkTable("users");
        checkTable("trips");
        checkTable("reservations");
    }
    private static void checkTable(String tableName){
        try{
            String query = "SELECT * FROM user_tables WHERE table_name = '" + tableName.toUpperCase() + "'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            switch(tableName) {
                case "users" -> {
                    if (resultSet.next())
                        DatabasePanel.usersTableName = tableName;
                    else
                        DatabasePanel.usersTableName = tableName + " (usunięta)";
                }
                case "trips" -> {
                    if (resultSet.next())
                        DatabasePanel.tripsTableName = tableName;
                    else
                        DatabasePanel.tripsTableName = tableName + " (usunięta)";
                }
                case "reservations" -> {
                    if (resultSet.next())
                        DatabasePanel.reservationsTableName = tableName;
                    else
                        DatabasePanel.reservationsTableName = tableName + " (usunięta)";
                }
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void checkSequences() {
        checkSequence("users_seq");
        checkSequence("trips_seq");
        checkSequence("reservations_seq");
    }
    private static void checkSequence(String seqName){
        try{
            String query = "SELECT * FROM user_sequences WHERE sequence_name = '" + seqName.toUpperCase() + "'";
            System.out.println(query);
            ResultSet resultSet = statement.executeQuery(query);
            switch(seqName){
                case "users_seq" -> {
                    if(resultSet.next())
                        DatabasePanel.usersSeqName = seqName;
                    else
                        DatabasePanel.usersSeqName = seqName + " (usunięta)";
                }
                case "trips_seq" -> {
                    if(resultSet.next())
                        DatabasePanel.tripsSeqName = seqName;
                    else
                        DatabasePanel.tripsSeqName = seqName + " (usunięta)";
                }
                case "reservations_seq" -> {
                    if(resultSet.next())
                        DatabasePanel.reservationsSeqName = seqName;
                    else
                        DatabasePanel.reservationsSeqName = seqName + " (usunięta)";
                }
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void deleteTable() {
        try{
            String query = "DROP table " + ServerGUI.tableName + " CASCADE CONSTRAINTS";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął tabelę " + ServerGUI.tableName + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void changeClientPassword() {
        try{
            String query = "UPDATE users SET password = ? WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, ServerGUI.passwordChanged);
            preparedState.setInt(2, ServerGUI.clientIDToChangePassword);
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zmienił hasło użytkownika o ID: " + ServerGUI.clientIDToChangePassword + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void deleteClient() {
        try{
            String query = "DELETE FROM users WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, ServerGUI.clientIDToRemove);
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął klienta o ID: " + ServerGUI.clientIDToRemove + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void editClient() {
        try{
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, ServerGUI.clientEditList.get(1));
            preparedState.setString(2, ServerGUI.clientEditList.get(2));
            preparedState.setString(3, ServerGUI.clientEditList.get(3));
            preparedState.setString(4, ServerGUI.clientEditList.get(4));
            preparedState.setInt(5, Integer.parseInt(ServerGUI.clientEditList.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane klienta o ID: " + ServerGUI.clientEditList.get(0) + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void findAdminName(){
        try{
            String adminQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, ServerGUI.password);
            adminPreparedState.setString(2, ServerGUI.email);
            ResultSet resultAdmin = adminPreparedState.executeQuery();
            if(resultAdmin.next()){
                adminName = resultAdmin.getString("FirstName");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void updateDashboard() {
        try {
            findAdminName();
            Dashboard.adminName = adminName;
            String howManyClientsQuery = "SELECT COUNT(*) as clientsCount FROM users WHERE userRank = 'client'";
            ResultSet resultHowManyClients = statement.executeQuery(howManyClientsQuery);
            if(resultHowManyClients.next()) {
                Dashboard.howManyClients = resultHowManyClients.getInt("clientsCount");
            }
            String howManyTripsQuery = "SELECT COUNT(*) as tripsCount FROM trips";
            ResultSet resultHowManyTrips = statement.executeQuery(howManyTripsQuery);
            if(resultHowManyTrips.next()) {
                Dashboard.howManyTrips = resultHowManyTrips.getInt("tripsCount");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void updateClients() {
        findAdminName();
        Clients.adminName = adminName;
        try {
            String query = "SELECT * FROM users WHERE userRank = 'client'";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
               // (ID_user, email, password, firstName, lastName, phoneNumber, userLogged)
                data.add(Integer.toString(result.getInt("ID_user")));
                data.add(result.getString("firstName"));
                data.add(result.getString("lastName"));
                data.add(result.getString("email"));
                data.add(result.getString("phoneNumber"));
            }
            Clients.listDataLength = data.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void updateTrips() {
        try {
            String query = "SELECT * FROM trips";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                // id_trip,country,city,departure_city,price_per_person,departure,arrival,people_limit,description,hotel_name
                tripsAdminData.add(Integer.toString(result.getInt("id_trip")));
                tripsAdminData.add(result.getString("country"));
                tripsAdminData.add(result.getString("city"));
                tripsAdminData.add(Integer.toString(result.getInt("price_per_person")));
                tripsAdminData.add(Integer.toString(result.getInt("people_limit")));
            }
            Wycieczki.listDataLength = tripsAdminData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    public static void send_data(){
        try {
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, ServerGUI.emailReg);
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                RegistrationPage.user_exists = "Tak";
            }
            else {
                RegistrationPage.user_exists = "Nie";
                String query = "INSERT INTO users (ID_user, email, password, firstName, lastName, phoneNumber, userLogged) VALUES (users_seq.nextval, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ServerGUI.emailReg);
                preparedStatement.setString(2, ServerGUI.passwordReg);
                preparedStatement.setString(3, ServerGUI.firstName);
                preparedStatement.setString(4, ServerGUI.lastName);
                preparedStatement.setString(5, ServerGUI.phoneNumber);
                preparedStatement.setInt(6, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                String commit = "COMMIT";
                statement.executeUpdate(commit);
                System.out.println("Zarejestrowano");
                if(StartPageFrame.admin_logged){
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał użytkownika o e-mailu: " + ServerGUI.emailReg + ".");
                }
            }
            emailPreparedState.close();
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    public static void login(){
        try{
            StartPageFrame.message = "";
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, ServerGUI.email);
            ResultSet result = emailPreparedState.executeQuery();
            if(result.next()){
                StartPageFrame.user_exists = true;
                //System.out.println("Jest");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, ServerGUI.password);
                passwordPreparedState.setString(2, ServerGUI.email);
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if(resultPassword.next()){
                    if(resultPassword.getInt("userLogged") == 0) {
                        StartPageFrame.password_valid = true;
                        String loggingQuery = "UPDATE users SET userLogged = 1 WHERE email = ?";
                        PreparedStatement loggingPreparedState = connection.prepareStatement(loggingQuery);
                        loggingPreparedState.setString(1, ServerGUI.email);
                        String commit = "COMMIT";
                        statement.executeUpdate(commit);
                        ServerGUI.usersCounter++;
                        if (resultPassword.getString("userRank").equals("admin")) {
                            StartPageFrame.admin_logged = true;
                            StartPageFrame.client_logged = false;
                            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator o e-mailu: " + ServerGUI.email + " zalogował się.");
                        } else {
                            StartPageFrame.admin_logged = false;
                            StartPageFrame.client_logged = true;
                            oferty.userID = resultPassword.getInt("ID_user");
                            new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + ServerGUI.email + " zalogował się.");
                        }
                    }
                    else
                        StartPageFrame.message = "Klient jest już zalogowany.";
                }
                else{
                    StartPageFrame.password_valid = false;
                }
            }
            else{
                StartPageFrame.user_exists = false;
                //StartPageFrame.password_valid = false;
                System.out.println("nie ma");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
}