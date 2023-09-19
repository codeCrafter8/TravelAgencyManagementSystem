package com.server.database.dao;

import com.server.database.DBContext;
import com.server.logs.LogsAdmins;
import com.server.logs.LogsClients;
import com.server.logs.LogsServer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that performs operations on users table
 */
public class UserDao {

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
    public UserDao(List<String> data) {
        try {
            connection = new DBContext().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.data = data;
    }

    /**
     * Retrieves user data from the database
     * @return List of user data
     */
    public List<String> findUser() {
        List<String> list = new ArrayList<>();
        try{
            String adminQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(1));
            ResultSet result = adminPreparedState.executeQuery();
            if(result.next()){
                list.add(result.getString("firstname"));
                list.add(result.getString("lastname"));
                list.add(result.getString("email"));
                list.add(result.getString("phonenumber"));
                list.add(String.valueOf(result.getInt("ID_user")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Allows a single user to log in
     * @return List of login data
     */
    public List<String> login() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String startPageMessage = "";
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(1));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                list.add("true");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, data.get(2));
                passwordPreparedState.setString(2, data.get(1));
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if (resultPassword.next()) {
                    list.add("true");
                    if (resultPassword.getInt("userLogged") == 0) {
                        String loggingQuery = "UPDATE users SET userLogged = 1 WHERE email = ?";
                        PreparedStatement loggingPreparedState = connection.prepareStatement(loggingQuery);
                        loggingPreparedState.setString(1, data.get(1));
                        loggingPreparedState.execute();
                        String commit = "COMMIT";
                        statement.executeUpdate(commit);
                        String resultPass = resultPassword.getString("userRank");
                        if (resultPass.equals("admin")) {
                            list.add("true");
                            list.add("false");
                            list.add(Integer.toString(resultPassword.getInt("ID_user")));
                            new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator o emailu: " + data.get(1) + " zalogował się.");
                        } else {
                            list.add("false");
                            list.add("true");
                            list.add(Integer.toString(resultPassword.getInt("ID_user")));
                            new LogsClients("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Klient o emailu: " + data.get(1) + " zalogował się.");
                        }
                        list.add(startPageMessage);
                    } else {
                        startPageMessage = "Użytkownik jest już zalogowany.";
                        list.addAll(fillLoginReturningData(startPageMessage, true));
                        new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto o emailu: " + data.get(1) + ".");
                    }
                } else {
                    list.add("false");
                    list.addAll(fillLoginReturningData(startPageMessage, true));
                    new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto o emailu: " + data.get(1) + ".");
                }
            } else {
                list.addAll(fillLoginReturningData(startPageMessage, true));
                new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Nieudana próba logowania na konto o emailu: " + data.get(1) + ".");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Fills the list containing data returned to the server
     * @param startPageMessage message sent to the StartPage class
     * @param isExisting information whether the user already exists in the database
     * @return List of login data
     */
    public List<String> fillLoginReturningData(String startPageMessage, boolean isExisting) {
        List<String> data = new ArrayList<>();
        if (!isExisting) {
            data.add("false");
            data.add("false");
        }
        data.add("false");
        data.add("false");
        data.add(Integer.toString(-1));
        data.add(startPageMessage);
        return data;
    }

    /**
     * Saves the new password of an edited user to the database
     */
    public void changeUserPassword() {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE users SET password = ? WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            preparedState.setInt(2, Integer.parseInt(data.get(2)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator zmienił haslo klienta o ID: " + data.get(2) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Deletes a selected user from the database
     */
    public void deleteUser() {
        try {
            Statement statement = connection.createStatement();
            String query = "DELETE FROM users WHERE id_user = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setInt(1, Integer.parseInt(data.get(1)));
            preparedState.executeUpdate();
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator usunął klienta o ID: " + data.get(1) + ".");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Saves new data of an edited user to the database
     */
    public void editUser() {
        try {
            Statement statement = connection.createStatement();
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
                new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator zedytował dane klienta o ID: " + data.get(5) + ".");
            else
                new LogsClients("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Klient o ID: " + data.get(5) + " zedytował swoje dane.");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves the administrator's first name from the database
     * @return List of admin name data
     */
    public List<String> findAdminName() {
        List<String> list = new ArrayList<>();
        try {
            String adminQuery = "SELECT firstName FROM users WHERE email = ?";
            PreparedStatement adminPreparedState = connection.prepareStatement(adminQuery);
            adminPreparedState.setString(1, data.get(1));
            ResultSet resultAdmin = adminPreparedState.executeQuery();
            if (resultAdmin.next()) {
                list.add(resultAdmin.getString("firstName"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves all clients from the database
     * @return List of clients data
     */
    public List<String> findAllClients() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM users WHERE userRank = 'client'";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                list.add(Integer.toString(result.getInt("ID_user")));
                list.add(result.getString("firstName"));
                list.add(result.getString("lastName"));
                list.add(result.getString("email"));
                list.add(result.getString("phoneNumber"));
            }
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Adds a new client to the database
     * @return List of information if user already exists
     */
    public List<String> addUser() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, data.get(4));
            ResultSet result = emailPreparedState.executeQuery();
            if (result.next()) {
                list.add("Użytkownik już istnieje");
            } else {
                list.add("Użytkownik nie istnieje");
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
                    new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator dodał klienta o emailu: " + data.get(4) + ".");
                }
                new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Klient o emailu: " + data.get(4) + " zarejestrował się.");
            }
            emailPreparedState.close();
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Allows a single user to log out
     */
    public void logUserOut() {
        try {
            Statement statement = connection.createStatement();
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
                    new LogsAdmins("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Administrator o emailu: " + data.get(1) + " wylogował się.");
                else
                    new LogsClients("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Klient o emailu: " + data.get(1) + " wylogował się.");
            }

        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Allows all users to log out
     */
    public void logAllUsersOut() {
        try {
            Statement statement = connection.createStatement();
            String query = "UPDATE users SET userLogged = 0";
            statement.executeQuery(query);
            String commit = "COMMIT";
            statement.executeUpdate(commit);
            new LogsServer("UserDao", "info", "[ " + new java.util.Date() + " ] " + "Wylogowano wszystkich z serwera.");
        } catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Retrieves from the database information whether the user is logged in
     * @return List of information if user is logged
     */
    public List<String> getUserLogged() {
        List<String> list = new ArrayList<>();
        try {
            String query = "SELECT userLogged FROM users WHERE email = ?";
            PreparedStatement preparedState = connection.prepareStatement(query);
            preparedState.setString(1, data.get(1));
            ResultSet result = preparedState.executeQuery();
            if (result.next()) {
                list.add(String.valueOf(result.getInt("userLogged")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }

    /**
     * Retrieves the clients count
     * @return List of clients count
     */
    public List<String> countAllClients() {
        List<String> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String howManyClientsQuery = "SELECT COUNT(*) as clientsCount FROM users WHERE userRank = 'client'";
            ResultSet resultHowManyClients = statement.executeQuery(howManyClientsQuery);
            if (resultHowManyClients.next()) {
                list.add(Integer.toString(resultHowManyClients.getInt("clientsCount")));
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("UserDao", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        return list;
    }
}
