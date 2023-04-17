import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static boolean running = true;
    public static String email, password, firstName, lastName, phoneNumber, emailReg, passwordReg, passwordChanged;
    public static int clientIDToRemove;
    public static int clientIDToChangePassword;
    public static String tableToRemove;
    public static String operation;
    public static List<String> clientEditList = new ArrayList<>();
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
                System.out.println(client);
                socket_input = client.getInputStream();
                socket_input_data = new DataInputStream(socket_input);
                socket_output = client.getOutputStream();
                socket_output_data = new DataOutputStream(socket_output);
                operation = socket_input_data.readUTF();
                //System.out.println(operation);
                switch(operation){
                    case "Login" -> {
                        email = socket_input_data.readUTF();
                        password = socket_input_data.readUTF();
                        System.out.println("login " + email + password);
                        database.connect_with_database();
                        socket_output_data.writeBoolean(StartPageFrame.user_exists);
                        socket_output_data.flush();
                        socket_output_data.writeBoolean(StartPageFrame.password_valid);
                        socket_output_data.flush();
                        socket_output_data.writeBoolean(StartPageFrame.admin_logged);
                        socket_output_data.flush();
                    }
                    case "Register" -> {
                        firstName = socket_input_data.readUTF();
                        lastName = socket_input_data.readUTF();
                        phoneNumber = socket_input_data.readUTF();
                        emailReg = socket_input_data.readUTF();
                        passwordReg = socket_input_data.readUTF();
                        System.out.println("Reg " + firstName + lastName + phoneNumber + emailReg + passwordReg);
                        database.connect_with_database();
                        socket_output_data.writeUTF(RegistrationPage.user_exists);
                        socket_output_data.flush();
                    }
                    case "dashboardUpdate" -> {
                        database.connect_with_database();
                        socket_output_data.writeUTF(Dashboard.adminName);
                        socket_output_data.flush();
                        socket_output_data.writeInt(Dashboard.howManyClients);
                        socket_output_data.flush();
                    }
                    case "clientsUpdate" -> {
                        database.connect_with_database();
                        socket_output_data.writeUTF(Clients.adminName);
                        socket_output_data.flush();
                        socket_output_data.writeInt(Clients.listDataLength);
                        socket_output_data.flush();
                        for (String s : database.data) {
                            socket_output_data.writeUTF(s);
                            socket_output_data.flush();
                        }
                        database.data.clear();
                    }
                    case "deleteClient" -> {
                        clientIDToRemove = socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "editClient" -> {
                        for(int i = 0; i < 5; i++){
                            if(i == 0)
                                clientEditList.add(Integer.toString(socket_input_data.readInt()));
                            else
                                clientEditList.add(socket_input_data.readUTF());
                        }
                        database.connect_with_database();
                        clientEditList.clear();
                    }
                    case "changeClientPassword" -> {
                        passwordChanged = socket_input_data.readUTF();
                        clientIDToChangePassword = socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "deleteTable" -> {
                        tableToRemove = socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "checkTables" -> {
                        database.connect_with_database();
                        socket_output_data.writeUTF(DatabasePanel.usersTableName);
                        socket_output_data.flush();
                        socket_output_data.writeUTF(DatabasePanel.tripsTableName);
                        socket_output_data.flush();
                        socket_output_data.writeUTF(DatabasePanel.reservationsTableName);
                        socket_output_data.flush();
                    }
                    case "checkSequences" -> {
                        database.connect_with_database();
                        socket_output_data.writeUTF(DatabasePanel.usersSeqName);
                        socket_output_data.flush();
                        socket_output_data.writeUTF(DatabasePanel.tripsSeqName);
                        socket_output_data.flush();
                        socket_output_data.writeUTF(DatabasePanel.reservationsSeqName);
                        socket_output_data.flush();
                    }
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



