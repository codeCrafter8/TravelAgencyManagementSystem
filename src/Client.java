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
                case "Login" -> {
                    socket_output_data.writeUTF(StartPageFrame.email);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(StartPageFrame.password);
                    socket_output_data.flush();
                    StartPageFrame.user_exists = socket_input_data.readBoolean();
                    StartPageFrame.password_valid = socket_input_data.readBoolean();
                    StartPageFrame.admin_logged = socket_input_data.readBoolean();
                }
                case "Register" -> {
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
                }
                case "dashboardUpdate"-> {
                    Dashboard.adminName = socket_input_data.readUTF();
                    Dashboard.howManyClients = socket_input_data.readInt();
                }
                case "clientsUpdate" -> {
                    Clients.adminName = socket_input_data.readUTF();
                    Clients.listDataLength = socket_input_data.readInt();
                    for (int i = 0; i < Clients.listDataLength; i++) {
                        Clients.data.add(socket_input_data.readUTF());
                    }
                }
                case "deleteClient" -> {
                    socket_output_data.writeInt(Clients.clientIDToRemove);
                    socket_output_data.flush();
                }
                case "editClient" -> {
                    socket_output_data.writeInt(Clients.id);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(Clients.firstName);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(Clients.lastName);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(Clients.email);
                    socket_output_data.flush();
                    socket_output_data.writeUTF(Clients.phoneNumber);
                    socket_output_data.flush();
                }
                case "changeClientPassword" -> {
                    socket_output_data.writeUTF(ChangeClientPassword.password);
                    socket_output_data.flush();
                    socket_output_data.writeInt(Clients.clientIDToChangePassword);
                    socket_output_data.flush();
                }
                case "deleteTable" -> {
                    socket_output_data.writeUTF(DatabasePanel.tableName);
                    socket_output_data.flush();
                }
                case "checkTables" -> {
                    DatabasePanel.usersTableName = socket_input_data.readUTF();
                    DatabasePanel.tripsTableName = socket_input_data.readUTF();
                    DatabasePanel.reservationsTableName = socket_input_data.readUTF();
                }
                case "checkSequences" -> {
                    DatabasePanel.usersSeqName = socket_input_data.readUTF();
                    DatabasePanel.tripsSeqName = socket_input_data.readUTF();
                    DatabasePanel.reservationsSeqName = socket_input_data.readUTF();
                }
                case "deleteSequence" -> {
                    socket_output_data.writeUTF(DatabasePanel.seqName);
                    socket_output_data.flush();
                }
                case "addSequence" -> {
                    System.out.println("klient wchodzi");
                    socket_output_data.writeUTF(DatabasePanel.seqName);
                    socket_output_data.flush();
                }
                case "addTable" -> {
                    socket_output_data.writeUTF(DatabasePanel.tableName);
                    socket_output_data.flush();
                }
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
