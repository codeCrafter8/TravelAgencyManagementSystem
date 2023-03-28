import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void operate(String operation){
        try {
            Socket socket = new Socket("localhost", 1522);
            InputStream socket_input = socket.getInputStream();
            DataInputStream socket_input_data = new DataInputStream(socket_input);
            OutputStream socket_output = socket.getOutputStream();
            DataOutputStream socket_output_data = new DataOutputStream(socket_output);
            socket_output_data.writeUTF(operation);
            switch(operation) {
                case "Login":
                    socket_output_data.writeUTF(StartPageFrame.email);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(StartPageFrame.password);
                    socket_output_data.flush();
                    StartPageFrame.user_exists = socket_input_data.readBoolean();
                    StartPageFrame.password_valid = socket_input_data.readBoolean();
                    StartPageFrame.admin_logged = socket_input_data.readBoolean();
                    break;
                case "Register":
                    socket_output_data.writeUTF(RegistrationPage.firstName);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(RegistrationPage.lastName);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(RegistrationPage.phoneNumber);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(RegistrationPage.email);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(RegistrationPage.password);
                    socket_output_data.flush();
                    RegistrationPage.user_exists = socket_input_data.readUTF();
                    break;
                case "dashboardUpdate":
                    Dashboard.adminName = socket_input_data.readUTF();
                    Dashboard.howManyClients = socket_input_data.readInt();
                    break;
            }
            socket_output_data.close();
            socket_input_data.close();
            socket.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
