import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BackgroundHandler extends SwingWorker<Void, Void> {
    private static class ClientThread implements Runnable{
        private final ServerGUI serverGUI;
        ClientThread(){
            serverGUI = new ServerGUI();
        }
        @Override
        public void run(){
            try {
                ServerGUI.inputStream = ServerGUI.client.getInputStream();
                ServerGUI.dataInputStream = new DataInputStream(ServerGUI.inputStream);
                ServerGUI.outputStream = ServerGUI.client.getOutputStream();
                ServerGUI.dataOutputStream = new DataOutputStream(ServerGUI.outputStream);
                ServerGUI.operation = ServerGUI.dataInputStream.readUTF();
                switch(ServerGUI.operation) {
                    case "login" -> {
                        ServerGUI.email = ServerGUI.dataInputStream.readUTF();
                        ServerGUI.password = ServerGUI.dataInputStream.readUTF();
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeBoolean(StartPage.userExists);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeBoolean(StartPage.passwordValid);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeBoolean(StartPage.adminLogged);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeBoolean(StartPage.clientLogged);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeUTF(StartPage.message);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Offer.userID);
                        ServerGUI.dataOutputStream.flush();
                    }
                    case "register" -> {
                        for (int i = 0; i < 5; i++) {
                            ServerGUI.registrationList.add(ServerGUI.dataInputStream.readUTF());
                        }
                        StartPage.adminLogged = ServerGUI.dataInputStream.readBoolean();
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeUTF(Registration.userExists);
                        ServerGUI.dataOutputStream.flush();
                    }
                    case "dashboardUpdate" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeUTF(Dashboard.adminName);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Dashboard.clientsQuantity);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Dashboard.tripsQuantity);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Dashboard.reservationsQuantity);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Dashboard.incomeQuantity);
                        ServerGUI.dataOutputStream.flush();
                    }
                    case "clientsUpdate" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeUTF(Clients.adminName);
                        ServerGUI.dataOutputStream.flush();
                        ServerGUI.dataOutputStream.writeInt(Clients.listDataLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.data) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.data.clear();
                    }
                    case "deleteClient" -> {
                        ServerGUI.clientIDToRemove = ServerGUI.dataInputStream.readInt();
                        Database.connect_with_database(serverGUI);
                    }
                    case "editClient" -> {
                        for (int i = 0; i < 5; i++) {
                            if (i == 0)
                                ServerGUI.clientEditList.add(Integer.toString(ServerGUI.dataInputStream.readInt()));
                            else
                                ServerGUI.clientEditList.add(ServerGUI.dataInputStream.readUTF());
                        }
                        Database.connect_with_database(serverGUI);
                        ServerGUI.clientEditList.clear();
                    }
                    case "changeClientPassword" -> {
                        ServerGUI.passwordChanged = ServerGUI.dataInputStream.readUTF();
                        ServerGUI.clientIDToChangePassword = ServerGUI.dataInputStream.readInt();
                        Database.connect_with_database(serverGUI);
                    }
                    case "logOut" -> {
                        ServerGUI.email = ServerGUI.dataInputStream.readUTF();
                        Database.connect_with_database(serverGUI);
                    }
                    case "setServerLogs" -> {
                        int size = ServerGUI.dataInputStream.readInt();
                        for(int i = 0; i < size; i++){
                            LogsServer.logs.add(ServerGUI.dataInputStream.readUTF());
                        }
                    }
                    case "tripsListPopulation" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(SearchEngine.listDataLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.tripsData) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.tripsData.clear();
                    }
                    case "getNumbers" -> {
                        ServerGUI.dataOutputStream.writeInt(ServerGUI.phoneNumbersListLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.phoneNumbers) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                    }
                    case "sendNumbers" -> {
                        ServerGUI.phoneNumbers.add(ServerGUI.dataInputStream.readUTF());
                        ServerGUI.phoneNumbersListLength++;
                    }
                    case "getDestination" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(SearchEngine.destinationListLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.destination) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.destination.clear();
                    }
                    case "getDeparture" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(SearchEngine.departureListLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.departure) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.departure.clear();
                    }
                    case "tripsUpdate" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(Trips.listDataLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.tripsAdminData) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.tripsAdminData.clear();
                    }
                    case "deleteTrip" -> {
                        ServerGUI.tripIDToRemove = ServerGUI.dataInputStream.readInt();
                        Database.connect_with_database(serverGUI);
                    }
                    case "editTrip" -> {
                        for (int i = 0; i < 5; i++) {
                            if (i == 0)
                                ServerGUI.tripEditList.add(Integer.toString(ServerGUI.dataInputStream.readInt()));
                            else
                                ServerGUI.tripEditList.add(ServerGUI.dataInputStream.readUTF());
                        }
                        Database.connect_with_database(serverGUI);
                        ServerGUI.tripEditList.clear();
                    }
                    case "resUpdate" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(Reservations.listDataLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.resAdminData) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.resAdminData.clear();
                    }
                    case "deleteRes" -> {
                        ServerGUI.resIDToRemove = ServerGUI.dataInputStream.readInt();
                        Database.connect_with_database(serverGUI);
                    }
                    case "editRes" -> {
                        for (int i = 0; i < 6; i++) {
                            if (i == 0)
                                ServerGUI.resEditList.add(Integer.toString(ServerGUI.dataInputStream.readInt()));
                            else
                                ServerGUI.resEditList.add(ServerGUI.dataInputStream.readUTF());
                        }
                        Database.connect_with_database(serverGUI);
                        ServerGUI.resEditList.clear();
                    }
                    case "addReservation" -> {
                        ServerGUI.tripIdToRes = ServerGUI.dataInputStream.readInt();
                        ServerGUI.userIdToRes = ServerGUI.dataInputStream.readInt();
                        ServerGUI.peopleQuantity = ServerGUI.dataInputStream.readInt();
                        ServerGUI.insurance = ServerGUI.dataInputStream.readUTF();
                        Database.addReservation();
                        ServerGUI.resAdminData.clear();
                    }
                    case "addTrip" -> {
                        for (int i = 0; i < 8; i++) {
                            ServerGUI.addTripData.add(ServerGUI.dataInputStream.readUTF());
                        }
                        Database.addTrip();
                    }
                    case "myAccountUpdate" -> {
                        Database.connect_with_database(serverGUI);
                        ServerGUI.dataOutputStream.writeInt(MyAccount.clientDataListLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.clientData) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.clientData.clear();

                        ServerGUI.dataOutputStream.writeInt(MyAccount.resDataListLength);
                        ServerGUI.dataOutputStream.flush();
                        for (String s : ServerGUI.resData) {
                            ServerGUI.dataOutputStream.writeUTF(s);
                            ServerGUI.dataOutputStream.flush();
                        }
                        ServerGUI.resData.clear();
                    }
                    case "dataEdition" -> {
                        for (int i = 0; i < 5; i++) {
                            serverGUI.dataFromDataEdition.add(serverGUI.dataInputStream.readUTF());
                        }
                        Database.connect_with_database(serverGUI);
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