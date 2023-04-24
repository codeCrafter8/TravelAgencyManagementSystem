import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class database {
    static Statement statement;
    static Connection connection;
    private static String adminName = "";
    static List<String> data = new ArrayList<>();
    public static void connect_with_database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
            switch (Server.operation) {
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
            }
        }
        catch(Exception ex){
            System.out.println("Ex: " + ex);
        }
    }

    private static void addTable() {
        try{
            String query = "";
            int index = Server.tableName.indexOf("(usunięta)");
            switch(Server.tableName.substring(0, index - 1)){
                case "users" -> {
                    query = "CREATE TABLE " + Server.tableName + " (ID_user NUMBER(6) PRIMARY KEY, email VARCHAR(70), password VARCHAR(70), " +
                            "firstName VARCHAR(70), lastName VARCHAR(70), phoneNumber VARCHAR(25), userLogged NUMBER(6))";
                }
                case "trips" -> {
                    query = "CREATE TABLE " + Server.tableName.substring(0, index - 1) + "(ID_trip NUMBER(6))";
                    System.out.println(query);
                }
                case "reservations" -> {
                    query = "CREATE TABLE " + Server.tableName.substring(0, index - 1) + "(ID_reservations NUMBER(6))";
                }
            }
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    private static void addSequence() {
        try{
            int index = Server.seqName.indexOf("(usunięta)");
            String query = "CREATE SEQUENCE " + Server.seqName.substring(0, index);
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            System.out.println("add seq");
        }catch (SQLException ex) {
            System.out.println("Ex add: " + ex);
        }
    }

    private static void deleteSequence() {
        try{
            String query = "DROP SEQUENCE " + Server.seqName;
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
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
        }
    }
    private static void deleteTable() {
        try{
            String query = "DROP table " + Server.tableName + " CASCADE CONSTRAINTS";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    private static void changeClientPassword() {
        try{
            String query = "UPDATE users SET password = ? WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, Server.passwordChanged);
            preparedState.setInt(2, Server.clientIDToChangePassword);
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    private static void deleteClient() {
        try{
            String query = "DELETE FROM users WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Server.clientIDToRemove);
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    private static void editClient() {
        try{
            String query = "UPDATE users " +
                    "SET firstName = ?, lastName = ?, email = ?, phoneNumber = ? " +
                    "WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, Server.clientEditList.get(1));
            preparedState.setString(2, Server.clientEditList.get(2));
            preparedState.setString(3, Server.clientEditList.get(3));
            preparedState.setString(4, Server.clientEditList.get(4));
            preparedState.setInt(5, Integer.parseInt(Server.clientEditList.get(0)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    private static void findAdminName(){
        try{
            String adminQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, Server.password);
            adminPreparedState.setString(2, Server.email);
            ResultSet resultAdmin = adminPreparedState.executeQuery();
            if(resultAdmin.next()){
                adminName = resultAdmin.getString("FirstName");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
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
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
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
        }
    }

    public static void send_data(){
        try {
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, Server.emailReg);
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                RegistrationPage.user_exists = "Tak";
            }
            else {
                RegistrationPage.user_exists = "Nie";
                String query = "INSERT INTO users (ID_user, email, password, firstName, lastName, phoneNumber, userLogged) VALUES (users_seq.nextval, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, Server.emailReg);
                preparedStatement.setString(2, Server.passwordReg);
                preparedStatement.setString(3, Server.firstName);
                preparedStatement.setString(4, Server.lastName);
                preparedStatement.setString(5, Server.phoneNumber);
                preparedStatement.setInt(6, 0);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                String commit = "COMMIT";
                statement.executeUpdate(commit);
                System.out.println("Zarejestrowano");
            }
            emailPreparedState.close();
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }

    public static void login(){
        try{
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, Server.email);
            ResultSet result = emailPreparedState.executeQuery();
            if(result.next()){
                StartPageFrame.user_exists = true;
                System.out.println("Jest");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, Server.password);
                passwordPreparedState.setString(2, Server.email);
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if(resultPassword.next()){
                    StartPageFrame.password_valid = true;
                    if(resultPassword.getString("userRank").equals("admin")){
                        System.out.println("Admin");
                        StartPageFrame.admin_logged = true;
                    }
                    else
                        StartPageFrame.admin_logged = false;
                    //dalsze działania
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
        }
    }
}
