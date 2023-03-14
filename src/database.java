import java.sql.*;
import java.util.GregorianCalendar;

public class database {
    static Statement statement;
    static Connection connection;
    public static void connect_with_database() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "admin");
            statement = connection.createStatement();
            if(Server.operation.equals("Register")) {
                send_data();
            }
            if(Server.operation.equals("Login")) {
                check_if_user_exists();
            }
        }
        catch(Exception ex){
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

    public static void check_if_user_exists(){
        try{
            String emailQuery = "SELECT * FROM users WHERE email = ?";
            PreparedStatement emailPreparedState = connection.prepareStatement(emailQuery);
            emailPreparedState.setString(1, Server.email);
            ResultSet result = emailPreparedState.executeQuery();
            if(result.next()){
                StartPageFrame.user_exists = "Tak";
                //System.out.println("Jest");
                String passwordQuery = "SELECT * FROM users WHERE password = ? AND email = ?";
                PreparedStatement passwordPreparedState = connection.prepareStatement(passwordQuery);
                passwordPreparedState.setString(1, Server.password);
                passwordPreparedState.setString(2, Server.email);
                ResultSet resultPassword = passwordPreparedState.executeQuery();
                if(resultPassword.next()){
                    StartPageFrame.password_valid = "Tak";
                    //dalsze działania
                }
                else{
                    StartPageFrame.password_valid = "Nie";
                }
            }
            else{
                StartPageFrame.user_exists = "Nie";
                StartPageFrame.password_valid = "Null";
                System.out.println("nie ma");
            }
        }catch (SQLException ex) {
            System.out.println("Ex: " + ex);
        }
    }
}
