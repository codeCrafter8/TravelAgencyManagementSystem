package com.client;

import java.io.*;
import java.net.Socket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable{
    String operation;
    private final StartPage startPage = new StartPage();
    private final Offer offer = new Offer(true);
    private final Registration registration = new Registration();
    private final Dashboard dashboard = new Dashboard(true);
    private final Clients clients = new Clients(true);
    private final SearchEngine searchEngine = new SearchEngine(true);
    private final Trips trips = new Trips(true);
    private final Reservations reservations = new Reservations(true);
    private final MyAccount myAccount = new MyAccount(true);
    private final List<String> data = new ArrayList<>();
    public Client() {}
    public boolean getStartPageUserExists(){
        return startPage.userExists;
    }
    public boolean getStartPageAdminLogged(){
        return startPage.adminLogged;
    }
    public boolean getStartPageClientLogged(){
        return startPage.clientLogged;
    }
    public boolean getStartPagePasswordValid(){
        return startPage.passwordValid;
    }
    public String getStartPageMessage(){
        return startPage.message;
    }
    public String getRegistrationUserExists(){
        return registration.userExists;
    }
    public String getUserEmail(){ return startPage.email; }
    public String getUserPassword(){ return startPage.password; }
    public int getUserOfferID(){ return offer.userID; }
    public List<String> getMyAccountData(){
        return myAccount.returningData;
    }
    public void setUserEmail(String userEmail) {startPage.email = userEmail;}
    public List<String> getSearchEngineDeparture(){
        return searchEngine.departure;
    }
    public List<String> getSearchEngineDestination(){
        return searchEngine.destination;
    }
    public String getDashboardAdminName() {
        return dashboard.adminName;
    }

    public int getDashboardClientsQuantity() {
        return dashboard.clientsQuantity;
    }

    public int getDashboardTripsQuantity() {
        return dashboard.tripsQuantity;
    }

    public int getDashboardReservationsQuantity() {
        return dashboard.reservationsQuantity;
    }

    public int getDashboardIncomeQuantity() {
        return dashboard.incomeQuantity;
    }
    public List<String> getDashboardPhoneNumbers(){
        return dashboard.phoneNumbers;
    }
    public List<String> getClientsList(){return clients.returningData;}
    public List<String> getTripsList() {
        return trips.data;
    }
    public List<String> getReservationsList() {
        return reservations.data;
    }
    Client(String operation, List<String> data){
        this.operation = operation;
        this.data.addAll(data);
        operate();
    }
    public void operate() {
        try {
            Socket socket = new Socket("localhost", 1522);
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF(operation);
            switch (operation) {
                case "login" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                    startPage.email = data.get(0);
                    startPage.password = data.get(1);
                    startPage.userExists = dataInputStream.readBoolean();
                    startPage.passwordValid = dataInputStream.readBoolean();
                    startPage.adminLogged = dataInputStream.readBoolean();
                    startPage.clientLogged = dataInputStream.readBoolean();
                    startPage.message = dataInputStream.readUTF();
                    startPage.setLoginFields(startPage.userExists, startPage.passwordValid, startPage.adminLogged, startPage.clientLogged, startPage.message);
                    offer.userID = dataInputStream.readInt();
                    data.clear();
                }
                case "addClient" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                    dataOutputStream.writeBoolean(getStartPageAdminLogged());
                    registration.userExists = dataInputStream.readUTF();
                }
                case "myAccountUpdate" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                    myAccount.dataListLength = dataInputStream.readInt();
                    for (int i = 0; i < myAccount.dataListLength; i++) {
                        myAccount.returningData.add(dataInputStream.readUTF());
                    }
                }
                case "deleteRes", "changeClientPassword", "dataEdition", "sendNumbers", "addReservation", "deleteClient", "editClient", "addTrip", "deleteTrip", "editTrip" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                }
                case "tripsListPopulation", "tripsUpdate" -> {
                    trips.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < trips.listDataLength; i++) {
                        trips.data.add(dataInputStream.readUTF());
                    }
                }
                case "getDestination" -> {
                    searchEngine.destinationListLength = dataInputStream.readInt();
                    for (int i = 0; i < searchEngine.destinationListLength; i++) {
                        searchEngine.destination.add(dataInputStream.readUTF());
                    }
                }
                case "getDeparture" -> {
                    searchEngine.departureListLength = dataInputStream.readInt();
                    for (int i = 0; i < searchEngine.departureListLength; i++) {
                        searchEngine.departure.add(dataInputStream.readUTF());
                    }
                }
                case "dashboardUpdate" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                    int listSize = dataInputStream.readInt();
                    for (int i = 0; i < listSize; i++) {
                        dashboard.returningData.add(dataInputStream.readUTF());
                    }
                    dashboard.adminName = dashboard.returningData.get(0);
                    dashboard.clientsQuantity = Integer.parseInt(dashboard.returningData.get(1));
                    dashboard.tripsQuantity = Integer.parseInt(dashboard.returningData.get(2));
                    dashboard.reservationsQuantity = Integer.parseInt(dashboard.returningData.get(3));
                    dashboard.incomeQuantity = Integer.parseInt(dashboard.returningData.get(4));
                    dashboard.returningData.clear();
                }
                case "clientsUpdate" -> {
                    clients.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < clients.listDataLength; i++) {
                        clients.returningData.add(dataInputStream.readUTF());
                    }
                }
                case "logOut" -> {
                    dataOutputStream.writeUTF(data.get(0));
                    dataOutputStream.flush();
                    data.clear();
                }
                case "getNumbers" -> {
                    dashboard.phoneNumbersListLength = dataInputStream.readInt();
                    for (int i = 0; i < dashboard.phoneNumbersListLength; i++) {
                        dashboard.phoneNumbers.add(dataInputStream.readUTF());
                    }
                }
                case "resUpdate" -> {
                    reservations.listDataLength = dataInputStream.readInt();
                    for (int i = 0; i < reservations.listDataLength; i++) {
                        reservations.data.add(dataInputStream.readUTF());
                    }
                }
            }
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage() + " wyjatek");
        }
    }
}
