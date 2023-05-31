import java.sql.*;

public class Database {
    static Statement statement;
    static Connection connection;
    private static ServerGUI serverGUII;
    Database(){
        //serverGUI = new ServerGUI();
    }
    public static void connect_with_database(ServerGUI serverGUI) {
        serverGUII = serverGUI;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
            switch (ServerGUI.operation) {
                case "register" -> sendData();
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
                case "editRes" -> editRes();
                case "addTrip" -> addTrip();
                case "myAccountUpdate" -> updateMyAccount();
                case "dataEdition" -> editData();
            }
        }catch(Exception ex){
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void editData() {
        try{
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, serverGUII.dataFromDataEdition.get(0));
            preparedState.setString(2, serverGUII.dataFromDataEdition.get(1));
            preparedState.setString(3, serverGUII.dataFromDataEdition.get(2));
            preparedState.setInt(4, Integer.parseInt(serverGUII.dataFromDataEdition.get(3)));
            preparedState.setInt(5, Integer.parseInt(serverGUII.dataFromDataEdition.get(4)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("Database", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane klienta o ID: " + serverGUII.clientID + ".");
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("Database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
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
                ServerGUI.resData.add(Integer.toString(result.getInt("id_reservation")));
                ServerGUI.resData.add(result.getString("country"));
                ServerGUI.resData.add(result.getString("city"));
                ServerGUI.resData.add(Integer.toString(result.getInt("price_per_person")));
                ServerGUI.resData.add(Integer.toString(result.getInt("people_quantity")));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                ServerGUI.resData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                ServerGUI.resData.add(arrival.substring(0, index - 1));
                if(result.getString("insurance") != null)
                    ServerGUI.resData.add(result.getString("insurance"));
                else
                    ServerGUI.resData.add("Brak");
                ServerGUI.resData.add(result.getString("departure_city"));
                ServerGUI.resData.add(result.getString("hotel_name"));
                ServerGUI.resData.add(result.getString("description"));
            }
            MyAccount.resDataListLength = ServerGUI.resData.size();
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
                ServerGUI.clientData.add(result.getString("firstname"));
                ServerGUI.clientData.add(result.getString("lastname"));
                ServerGUI.clientData.add(result.getString("email"));
                ServerGUI.clientData.add(result.getString("phonenumber"));
                ServerGUI.clientData.add(String.valueOf(result.getInt("ID_user")));
            }
            MyAccount.clientDataListLength = ServerGUI.clientData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    public static void addTrip() {
        try {
            String query = "INSERT INTO trips VALUES (trips_seq.nextval, ?, ?, ?, ?, ?, ?, ?, 'Hotel to miejsce, w ktorym jedzenie to kwestia przygody i odkrywania nowych smakow. " +
                    "W naszej ofercie znajda Panstwo pokoje o roznym standardzie oraz opcje wyzywienia, w tym sniadania, obiady i kolacje, ktore są przygotowywane przez naszych szefow kuchni z pasja i zaangazowaniem. " +
                    "Nasza restauracja oferuje dania kuchni fusion, a nasza karta drinkow to prawdziwe arcydziela mixologii.', ?, 0)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ServerGUI.addTripData.get(0));
            preparedStatement.setString(2, ServerGUI.addTripData.get(1));
            preparedStatement.setString(3, ServerGUI.addTripData.get(2));
            preparedStatement.setInt(4, Integer.parseInt(ServerGUI.addTripData.get(3)));
            preparedStatement.setDate(5, Date.valueOf(ServerGUI.addTripData.get(6)));
            preparedStatement.setDate(6, Date.valueOf(ServerGUI.addTripData.get(7)));
            preparedStatement.setInt(7, Integer.parseInt(ServerGUI.addTripData.get(4)));
            preparedStatement.setString(8, ServerGUI.addTripData.get(5));
            preparedStatement.executeUpdate();
            //preparedStatement.close();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał wycieczkę do : " + ServerGUI.addTripData.get(1) + ".");
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
            String query = "SELECT id_reservation, firstname, lastname, departure, arrival, phonenumber, reserved FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip " +
                    "JOIN users ON users.ID_user = reservations.ID_user";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                ServerGUI.resAdminData.add(Integer.toString(result.getInt("id_reservation")));
                ServerGUI.resAdminData.add(result.getString("firstname"));
                ServerGUI.resAdminData.add(result.getString("lastname"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                ServerGUI.resAdminData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                ServerGUI.resAdminData.add(arrival.substring(0, index - 1));
                ServerGUI.resAdminData.add(result.getString("phonenumber"));
            }
            Reservations.listDataLength = ServerGUI.resAdminData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    public static void addReservation() {
        try {
            String query = "INSERT INTO reservations (id_reservation, id_trip, ID_user, people_quantity, insurance) VALUES (reservations_seq.nextval, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ServerGUI.tripIdToRes);
            preparedStatement.setInt(2, ServerGUI.userIdToRes);
            preparedStatement.setInt(3, ServerGUI.peopleQuantity);
            preparedStatement.setString(4, ServerGUI.insurance);
            preparedStatement.executeUpdate();
            preparedStatement.close();
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
            SearchEngine.destinationListLength = ServerGUI.destination.size();
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
            SearchEngine.departureListLength = ServerGUI.departure.size();
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
                ServerGUI.tripsData.add(result.getString("country"));
                ServerGUI.tripsData.add(result.getString("city"));
                departure = result.getString("departure");
                index = departure.indexOf("00:");
                ServerGUI.tripsData.add(departure.substring(0, index - 1));
                arrival = result.getString("arrival");
                index = arrival.indexOf("00:");
                ServerGUI.tripsData.add(arrival.substring(0, index - 1));
                ServerGUI.tripsData.add(Integer.toString((result.getInt("price_per_person"))));
                ServerGUI.tripsData.add(result.getString("departure_city"));
                ServerGUI.tripsData.add(Integer.toString(result.getInt("people_limit")));
                ServerGUI.tripsData.add(Integer.toString(result.getInt("id_trip")));
                ServerGUI.tripsData.add(result.getString("description"));
                ServerGUI.tripsData.add(result.getString("hotel_name"));
            }
            SearchEngine.listDataLength = ServerGUI.tripsData.size();
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
                    new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + ServerGUI.email + " wylogował się.");
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
                ServerGUI.adminName = resultAdmin.getString("FirstName");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    private static void updateDashboard() {
        try {
            findAdminName();
            Dashboard.adminName = ServerGUI.adminName;
            String howManyClientsQuery = "SELECT COUNT(*) as clientsCount FROM users WHERE userRank = 'client'";
            ResultSet resultHowManyClients = statement.executeQuery(howManyClientsQuery);
            if(resultHowManyClients.next()) {
                Dashboard.clientsQuantity = resultHowManyClients.getInt("clientsCount");
            }
            String howManyTripsQuery = "SELECT COUNT(*) as tripsCount FROM trips";
            ResultSet resultHowManyTrips = statement.executeQuery(howManyTripsQuery);
            if(resultHowManyTrips.next()) {
                Dashboard.tripsQuantity = resultHowManyTrips.getInt("tripsCount");
            }
            String howManyReservationsQuery = "SELECT COUNT(*) as resCount FROM reservations";
            ResultSet resultHowManyReservations = statement.executeQuery(howManyReservationsQuery);
            if(resultHowManyReservations.next()) {
                Dashboard.reservationsQuantity = resultHowManyReservations.getInt("resCount");
            }
            String howMuchIncomeQuery = "SELECT people_quantity, price_per_person FROM reservations " +
                    "JOIN trips ON trips.id_trip = reservations.id_trip";
            ResultSet resultHowMuchIncome = statement.executeQuery(howMuchIncomeQuery);
            while(resultHowMuchIncome.next()) {
                int peopleQuantity = resultHowMuchIncome.getInt("people_quantity");
                int price = resultHowMuchIncome.getInt("price_per_person");
                Dashboard.incomeQuantity += peopleQuantity * price;
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    private static void updateClients() {
        findAdminName();
        Clients.adminName = ServerGUI.adminName;
        try {
            String query = "SELECT * FROM users WHERE userRank = 'client'";
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                ServerGUI.data.add(Integer.toString(result.getInt("ID_user")));
                ServerGUI.data.add(result.getString("firstName"));
                ServerGUI.data.add(result.getString("lastName"));
                ServerGUI.data.add(result.getString("email"));
                ServerGUI.data.add(result.getString("phoneNumber"));
            }
            Clients.listDataLength = ServerGUI.data.size();
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
                ServerGUI.tripsAdminData.add(Integer.toString(result.getInt("id_trip")));
                ServerGUI.tripsAdminData.add(result.getString("country"));
                ServerGUI.tripsAdminData.add(result.getString("city"));
                ServerGUI.tripsAdminData.add(Integer.toString(result.getInt("price_per_person")));
                ServerGUI.tripsAdminData.add(Integer.toString(result.getInt("people_limit")));
            }
            Trips.listDataLength = ServerGUI.tripsAdminData.size();
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    public static void sendData(){
        try {
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, ServerGUI.registrationList.get(3));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                Registration.userExists = "Tak";
            }
            else {
                Registration.userExists = "Nie";
                String query = "INSERT INTO users (ID_user, email, password, firstName, lastName, phoneNumber, userLogged) VALUES (users_seq.nextval, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ServerGUI.registrationList.get(3));
                preparedStatement.setString(2, ServerGUI.registrationList.get(4));
                preparedStatement.setString(3, ServerGUI.registrationList.get(0));
                preparedStatement.setString(4, ServerGUI.registrationList.get(1));
                preparedStatement.setString(5, ServerGUI.registrationList.get(2));
                preparedStatement.setInt(6, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                String commit = "COMMIT";
                statement.executeUpdate(commit);
                if(StartPage.adminLogged){
                    new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał użytkownika o e-mailu: " + ServerGUI.registrationList.get(3) + ".");
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
            StartPage.message = "";
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, ServerGUI.email);
            ResultSet result = emailPreparedState.executeQuery();
            if(result.next()){
                StartPage.userExists = true;
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, ServerGUI.password);
                passwordPreparedState.setString(2, ServerGUI.email);
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if(resultPassword.next()){
                    if(resultPassword.getInt("userLogged") == 0) {
                        StartPage.passwordValid = true;
                        String loggingQuery = "UPDATE users SET userLogged = 1 WHERE email = ?";
                        PreparedStatement loggingPreparedState = connection.prepareStatement(loggingQuery);
                        loggingPreparedState.setString(1, ServerGUI.email);
                        String commit = "COMMIT";
                        statement.executeUpdate(commit);
                        ServerGUI.usersCounter++;
                        if (resultPassword.getString("userRank").equals("admin")) {
                            StartPage.adminLogged = true;
                            StartPage.clientLogged = false;
                            new LogsAdmins("database", "info", "[ " + new java.util.Date() + " ] " + "Administrator o e-mailu: " + ServerGUI.email + " zalogował się.");
                        } else {
                            StartPage.adminLogged = false;
                            StartPage.clientLogged = true;
                            Offer.userID = resultPassword.getInt("ID_user");
                            new LogsClients("database", "info", "[ " + new java.util.Date() + " ] " + "Klient o e-mailu: " + ServerGUI.email + " zalogował się.");
                        }
                    }
                    else
                        StartPage.message = "Klient jest już zalogowany.";
                }
                else{
                    StartPage.passwordValid = false;
                }
            }
            else{
                StartPage.userExists = false;
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("database", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
}