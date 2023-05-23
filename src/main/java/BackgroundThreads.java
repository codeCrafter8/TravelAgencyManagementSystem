import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BackgroundThreads extends SwingWorker<Void, Void> {
    private static class ClientThread implements Runnable{
        @Override
        public void run(){
            try {
                ServerGUI.socket_input = ServerGUI.client.getInputStream();
                ServerGUI.socket_input_data = new DataInputStream(ServerGUI.socket_input);
                ServerGUI.socket_output = ServerGUI.client.getOutputStream();
                ServerGUI.socket_output_data = new DataOutputStream(ServerGUI.socket_output);
                ServerGUI.operation = ServerGUI.socket_input_data.readUTF();
                switch(ServerGUI.operation) {
                    case "Login" -> {
                        ServerGUI.email = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.password = ServerGUI.socket_input_data.readUTF();
                        System.out.println("login " + ServerGUI.email + ServerGUI.password);
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeBoolean(StartPageFrame.user_exists);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeBoolean(StartPageFrame.password_valid);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeBoolean(StartPageFrame.admin_logged);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeBoolean(StartPageFrame.client_logged);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeUTF(StartPageFrame.message);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeInt(oferty.userID);
                        ServerGUI.socket_output_data.flush();
                    }
                    case "Register" -> {
                        ServerGUI.firstName = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.lastName = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.phoneNumber = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.emailReg = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.passwordReg = ServerGUI.socket_input_data.readUTF();
                        System.out.println("Reg " + ServerGUI.firstName + ServerGUI.lastName + ServerGUI.phoneNumber + ServerGUI.emailReg + ServerGUI.passwordReg);
                        StartPageFrame.admin_logged = ServerGUI.socket_input_data.readBoolean();
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeUTF(RegistrationPage.user_exists);
                        ServerGUI.socket_output_data.flush();
                    }
                    case "dashboardUpdate" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeUTF(Dashboard.adminName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeInt(Dashboard.howManyClients);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeInt(Dashboard.howManyTrips);
                        ServerGUI.socket_output_data.flush();
                    }
                    case "clientsUpdate" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeUTF(Clients.adminName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeInt(Clients.listDataLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.data) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.data.clear();
                    }
                    case "deleteClient" -> {
                        ServerGUI.clientIDToRemove = ServerGUI.socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "editClient" -> {
                        for (int i = 0; i < 5; i++) {
                            if (i == 0)
                                ServerGUI.clientEditList.add(Integer.toString(ServerGUI.socket_input_data.readInt()));
                            else
                                ServerGUI.clientEditList.add(ServerGUI.socket_input_data.readUTF());
                        }
                        database.connect_with_database();
                        ServerGUI.clientEditList.clear();
                    }
                    case "changeClientPassword" -> {
                        ServerGUI.passwordChanged = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.clientIDToChangePassword = ServerGUI.socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "deleteTable" -> {
                        ServerGUI.tableName = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "checkTables" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.usersTableName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.tripsTableName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.reservationsTableName);
                        ServerGUI.socket_output_data.flush();
                    }
                    case "checkSequences" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.usersSeqName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.tripsSeqName);
                        ServerGUI.socket_output_data.flush();
                        ServerGUI.socket_output_data.writeUTF(DatabasePanel.reservationsSeqName);
                        ServerGUI.socket_output_data.flush();
                    }
                    case "deleteSequence" -> {
                        ServerGUI.seqName = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "addSequence" -> {
                        System.out.println("serwer wchodzi");
                        ServerGUI.seqName = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "addTable" -> {
                        ServerGUI.tableName = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "logOut" -> {
                        ServerGUI.email = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "setServerLogs" -> {
                        int size = ServerGUI.socket_input_data.readInt();
                        for(int i = 0; i < size; i++){
                            LogsServer.logs.add(ServerGUI.socket_input_data.readUTF());
                        }
                    }
                    case "tripsListPopulation" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(WYSZUKIWARKA.listDataLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.tripsData) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.tripsData.clear();
                    }
                    case "getNumbers" -> {
                        ServerGUI.socket_output_data.writeInt(ServerGUI.phoneNumbersListLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : ServerGUI.phoneNumbers) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                            System.out.println(s);
                        }
                    }
                    case "sendNumbers" -> {
                        ServerGUI.phoneNumbers.add(ServerGUI.socket_input_data.readUTF());
                        ServerGUI.phoneNumbersListLength++;
                    }
                    case "getDestination" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(WYSZUKIWARKA.destinationListLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : ServerGUI.destination) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        ServerGUI.destination.clear();
                    }
                    case "getDeparture" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(WYSZUKIWARKA.departureListLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : ServerGUI.departure) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        ServerGUI.departure.clear();
                    }
                    case "tripsUpdate" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(Wycieczki.listDataLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.tripsAdminData) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.tripsAdminData.clear();
                    }
                    case "deleteTrip" -> {
                        ServerGUI.tripIDToRemove = ServerGUI.socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "editTrip" -> {
                        for (int i = 0; i < 5; i++) {
                            if (i == 0)
                                ServerGUI.tripEditList.add(Integer.toString(ServerGUI.socket_input_data.readInt()));
                            else
                                ServerGUI.tripEditList.add(ServerGUI.socket_input_data.readUTF());
                        }
                        database.connect_with_database();
                        ServerGUI.tripEditList.clear();
                    }
                    case "resUpdate" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(Rezerwacje.listDataLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.resAdminData) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.resAdminData.clear();
                    }
                    case "deleteRes" -> {
                        ServerGUI.resIDToRemove = ServerGUI.socket_input_data.readInt();
                        database.connect_with_database();
                    }
                    case "editRes" -> {
                        for (int i = 0; i < 6; i++) {
                            if (i == 0)
                                ServerGUI.resEditList.add(Integer.toString(ServerGUI.socket_input_data.readInt()));
                            else
                                ServerGUI.resEditList.add(ServerGUI.socket_input_data.readUTF());
                        }
                        database.connect_with_database();
                        ServerGUI.resEditList.clear();
                    }
                    case "addReservation" -> {
                        ServerGUI.tripIdToRes = ServerGUI.socket_input_data.readInt();
                        ServerGUI.userIdToRes = ServerGUI.socket_input_data.readInt();
                        ServerGUI.peopleQuantity = ServerGUI.socket_input_data.readInt();
                        ServerGUI.insurance = ServerGUI.socket_input_data.readUTF();
                        //database.connect_with_database();
                        database.addReservation();
                        database.resAdminData.clear();
                    }
                    case "addTrip" -> {
                        ServerGUI.countryAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.cityAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.departureCityAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.priceAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.peopleLimitAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.hotelNameAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.departureAddTrip = ServerGUI.socket_input_data.readUTF();
                        ServerGUI.arrivalAddTrip = ServerGUI.socket_input_data.readUTF();
                        database.connect_with_database();
                    }
                    case "myAccountUpdate" -> {
                        database.connect_with_database();
                        ServerGUI.socket_output_data.writeInt(MyAccount.clientDataListLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.clientData) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.clientData.clear();
                        ServerGUI.socket_output_data.writeInt(MyAccount.resDataListLength);
                        ServerGUI.socket_output_data.flush();
                        for (String s : database.resData) {
                            ServerGUI.socket_output_data.writeUTF(s);
                            ServerGUI.socket_output_data.flush();
                        }
                        database.resData.clear();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    protected Void doInBackground() {
        Thread thread = new Thread(new ClientThread());
        ServerGUI.threads.add(thread);
        thread.start();
        //new Logs("[ " + new java.util.Date() + " ]" + " New thread called " + thread.getName() + " was created ", "EkranSerwer", "info");
        return null;
    }
}