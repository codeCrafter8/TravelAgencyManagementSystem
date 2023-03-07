import javax.crypto.spec.PSource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    private static boolean running = true;
    public static String email, password, firstName, lastName, phoneNumber, emailReg, passwordReg;
    public static void operate(){
        ServerSocket socket;
        Socket client;
        try {
            socket = new ServerSocket(1522);
            socket.setReuseAddress(true);
            InputStream socket_input;
            DataInputStream socket_input_data;
            OutputStream socket_output;
            DataOutputStream socket_output_data;
            while (running) {
                client = socket.accept();
                socket_input = client.getInputStream();
                socket_input_data = new DataInputStream(socket_input);
                socket_output = client.getOutputStream();
                socket_output_data = new DataOutputStream(socket_output);
                String operation = socket_input_data.readUTF();
                switch(operation){
                    case "Login":
                        email = socket_input_data.readUTF();
                        password = socket_input_data.readUTF();
                        System.out.println("login " + email + password);
                    break;
                    case "Register":
                        firstName = socket_input_data.readUTF();
                        lastName = socket_input_data.readUTF();
                        phoneNumber = socket_input_data.readUTF();
                        emailReg = socket_input_data.readUTF();
                        passwordReg = socket_input_data.readUTF();
                        System.out.println("Reg " + firstName + lastName + phoneNumber + emailReg + passwordReg);
                    break;
                }
            }
        }
        catch(IOException ex){
            System.out.println("Ex: " + ex);
        }
    }
    public static void main(String[] args){
        operate();
    }
}


