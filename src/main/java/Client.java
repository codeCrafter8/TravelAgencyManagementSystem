import java.io.*;
import java.net.Socket;

public class Client {
    StartPage startPage;
    Offer offer;
    Registration registration;
    Dashboard dashboard;
    Clients clients;
    ClientPasswordChange clientPasswordChange;
    LogsServer logsServer;
    SearchEngine searchEngine;
    Trips trips;
    Reservations reservations;
    TripAddition tripAddition;
    MyAccount myAccount;
    DataEdition dataEdition;
    String operation;
    Client(String operation){
        this.operation = operation;
        switch(operation){
            case "login" -> {
                startPage = new StartPage();
                offer = new Offer();
            }
            case "register" -> {
                registration = new Registration();
                startPage = new StartPage();
            }
            case "dashboardUpdate", "getNumbers" -> dashboard = new Dashboard();
            case "clientsUpdate", "deleteClient", "editClient" -> clients = new Clients();
            case "changeClientPassword" -> {
                clients = new Clients();
                clientPasswordChange = new ClientPasswordChange();
            }
            case "logOut" -> startPage = new StartPage();
            case "setServerLogs" -> logsServer = new LogsServer(null, null, null);
            case "tripsListPopulation", "sendNumbers", "getDestination", "getDeparture" -> searchEngine = new SearchEngine();
            case "tripsUpdate", "deleteTrip", "editTrip" -> trips = new Trips();
            case "resUpdate", "deleteRes", "editRes" -> reservations = new Reservations();
            case "addReservation" -> offer = new Offer();
            case "addTrip" -> tripAddition = new TripAddition();
            case "myAccountUpdate" -> myAccount = new MyAccount();
            case "dataEdition" -> dataEdition = new DataEdition();
        }
    }
    public void operate(){
        try {
            Socket socket = new Socket("localhost", 1522);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(operation);
            switch(operation) {
                case "login" -> {
                    dataOutputStream.writeUTF(startPage.email);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(startPage.password);
                    dataOutputStream.flush();
                    StartPage.userExists = dataInputStream.readBoolean();
                    StartPage.passwordValid = dataInputStream.readBoolean();
                    StartPage.adminLogged = dataInputStream.readBoolean();
                    StartPage.clientLogged = dataInputStream.readBoolean();
                    StartPage.message = dataInputStream.readUTF();
                    Offer.userID = dataInputStream.readInt();
                }
                case "register" -> {
                    dataOutputStream.writeUTF(Registration.firstName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Registration.lastName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Registration.phoneNumber);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Registration.email);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Registration.password);
                    dataOutputStream.flush();
                    dataOutputStream.writeBoolean(StartPage.adminLogged);
                    dataOutputStream.flush();
                    Registration.userExists = dataInputStream.readUTF();
                }
                case "dashboardUpdate"-> {
                    Dashboard.adminName = dataInputStream.readUTF();
                    Dashboard.clientsQuantity = dataInputStream.readInt();
                    Dashboard.tripsQuantity = dataInputStream.readInt();
                    Dashboard.reservationsQuantity = dataInputStream.readInt();
                    Dashboard.incomeQuantity = dataInputStream.readInt();
                }
                case "clientsUpdate" -> {
                    Clients.adminName = dataInputStream.readUTF();
                    Clients.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < Clients.listDataLength; i++) {
                        Clients.data.add(dataInputStream.readUTF());
                    }
                }
                case "deleteClient" -> {
                    dataOutputStream.writeInt(Clients.clientIDToRemove);
                    dataOutputStream.flush();
                }
                case "editClient" -> {
                    dataOutputStream.writeInt(Clients.id);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Clients.firstName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Clients.lastName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Clients.email);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Clients.phoneNumber);
                    dataOutputStream.flush();
                }
                case "changeClientPassword" -> {
                    dataOutputStream.writeUTF(ClientPasswordChange.password);
                    dataOutputStream.flush();
                    dataOutputStream.writeInt(Clients.clientIDToChangePassword);
                    dataOutputStream.flush();
                }
                case "logOut" -> {
                    dataOutputStream.writeUTF(StartPage.email);
                    dataOutputStream.flush();
                }
                case "setServerLogs" -> {
                    dataOutputStream.writeInt(LogsServer.logs.size());
                    dataOutputStream.flush();
                    for(String log : LogsServer.logs){
                        dataOutputStream.writeUTF(log);
                        dataOutputStream.flush();
                    }
                }
                case "tripsListPopulation" -> {
                    SearchEngine.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < SearchEngine.listDataLength; i++) {
                        SearchEngine.data.add(dataInputStream.readUTF());
                    }
                }
                case "sendNumbers" -> {
                    dataOutputStream.writeUTF(SearchEngine.number);
                    dataOutputStream.flush();
                }
                case "getNumbers" -> {
                    Dashboard.phoneNumbersListLength = dataInputStream.readInt();
                    for (int i = 0; i < Dashboard.phoneNumbersListLength; i++) {
                        Dashboard.phoneNumbers.add(dataInputStream.readUTF());
                    }
                }
                case "getDestination" -> {
                    SearchEngine.destinationListLength = dataInputStream.readInt();
                    for (int i = 0; i < SearchEngine.destinationListLength; i++) {
                        SearchEngine.destination.add(dataInputStream.readUTF());
                    }
                }
                case "getDeparture" -> {
                    SearchEngine.departureListLength = dataInputStream.readInt();
                    for (int i = 0; i < SearchEngine.departureListLength; i++) {
                        SearchEngine.departure.add(dataInputStream.readUTF());
                    }
                }
                case "tripsUpdate" -> {
                    Trips.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < Trips.listDataLength; i++) {
                        Trips.data.add(dataInputStream.readUTF());
                    }
                }
                case "deleteTrip" -> {
                    dataOutputStream.writeInt(Trips.tripIDToRemove);
                    dataOutputStream.flush();
                }
                case "editTrip" -> {
                    dataOutputStream.writeInt(Trips.id);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Trips.country);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Trips.city);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Trips.price);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Trips.peopleLimit);
                    dataOutputStream.flush();
                }
                case "resUpdate" -> {
                    Reservations.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < Reservations.listDataLength; i++) {
                        Reservations.data.add(dataInputStream.readUTF());
                    }
                }
                case "deleteRes" -> {
                    dataOutputStream.writeInt(Reservations.resIDToRemove);
                    dataOutputStream.flush();
                }
                case "editRes" -> {
                    dataOutputStream.writeInt(Reservations.id);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Reservations.firstName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Reservations.lastName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Reservations.departure);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Reservations.arrival);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Reservations.phoneNumber);
                    dataOutputStream.flush();
                }
                case "addReservation" -> {
                    dataOutputStream.writeInt(Offer.tripID);
                    dataOutputStream.flush();
                    dataOutputStream.writeInt(Offer.userID);
                    dataOutputStream.flush();
                    dataOutputStream.writeInt(Offer.peopleQuantity);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(Offer.insurance);
                    dataOutputStream.flush();
                }
                case "addTrip" -> {
                    dataOutputStream.writeUTF(TripAddition.country);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.city);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.departureCity);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.price);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.peopleLimit);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.hotelName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.departure);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(TripAddition.arrival);
                    dataOutputStream.flush();
                }
                case "myAccountUpdate" -> {
                    MyAccount.clientDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < MyAccount.clientDataListLength; i++) {
                        MyAccount.clientData.add(dataInputStream.readUTF());
                    }
                    MyAccount.resDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < MyAccount.resDataListLength; i++) {
                        MyAccount.resData.add(dataInputStream.readUTF());
                    }
                }
                case "dataEdition" -> {
                    dataOutputStream.writeUTF(DataEdition.firstName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(DataEdition.lastName);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(DataEdition.email);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(DataEdition.phoneNumber);
                    dataOutputStream.flush();
                    dataOutputStream.writeUTF(DataEdition.id);
                    dataOutputStream.flush();
                }
            }
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
