package com.client;

import java.io.*;
import java.net.Socket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody obsługujące pracę klienta w komunikacji klient-serwer
 */
public class Client implements Serializable{
    /**
     * Atrybut określający rodzaj wykonywanej operacji
     */
    String operation;
    /**
     * Instancja klasy StartPage
     */
    private final StartPage startPage = new StartPage();
    /**
     * Instancja klasy Offer
     */
    private final Offer offer = new Offer();
    /**
     * Instancja klasy Registration
     */
    private final Registration registration = new Registration();
    /**
     * Instancja klasy Dashboard
     */
    private final Dashboard dashboard = new Dashboard();
    /**
     * Instancja klasy Clients
     */
    private final Clients clients = new Clients();
    /**
     * Instancja klasy SearchEngine
     */
    private final SearchEngine searchEngine = new SearchEngine();
    /**
     * Instancja klasy Trips
     */
    private final Trips trips = new Trips();
    /**
     * Instancja klasy Reservations
     */
    private final Reservations reservations = new Reservations();
    /**
     * Instancja klasy MyAccount
     */
    private final MyAccount myAccount = new MyAccount();
    /**
     * Atrybut będący listą zawierającą dane przekazywane z klienta do serwera
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Pobiera informację, czy użytkownik jest istnieje w bazie danych.
     * @return true, jeśli użytkownik istnieje, false w przeciwnym razie
     */
    boolean getStartPageUserExists(){
        return startPage.userExists;
    }
    /**
     * Pobiera informację, czy administrator jest zalogowany.
     * @return true, jeśli administrator jest zalogowany, false w przeciwnym razie
     */
    boolean getStartPageAdminLogged(){
        return startPage.adminLogged;
    }
    /**
     * Pobiera informację, czy klient jest zalogowany.
     * @return true, jeśli klient jest zalogowany, false w przeciwnym razie
     */
    boolean getStartPageClientLogged(){
        return startPage.clientLogged;
    }
    /**
     * Pobiera informację, czy podane hasło hasło dla danego emailu zgadza się z tym w bazie danych.
     * @return true, jeśli hasło się zgadza, false w przeciwnym razie
     */
    boolean getStartPagePasswordValid(){
        return startPage.passwordValid;
    }
    /**
     * Pobiera wiadomość dla strony startowej.
     * @return wiadomość dla strony startowej
     */
    String getStartPageMessage(){
        return startPage.message;
    }
    /**
     * Pobiera informację, czy istnieje już użytkownik o podanym adresie email w procesie rejestracji.
     * @return informacja o istnieniu użytkownika o podanym adresie email
     */
    String getRegistrationUserExists(){
        return registration.clientExists;
    }
    /**
     * Pobiera adres email ze strony startowej.
     * @return adres email
     */
    String getUserEmail(){ return startPage.email; }
    /**
     * Pobiera hasło ze strony startowej.
     * @return hasło
     */
    String getUserPassword(){ return startPage.password; }
    /**
     * Pobiera identyfikator użytkownika w ofercie wycieczki.
     * @return identyfikator użytkownika w ofercie wycieczki
     */
    int getOfferClientID(){ return offer.userID; }
    /**
     * Pobiera dane konta użytkownika.
     * @return lista danych konta użytkownika
     */
    List<String> getMyAccountData(){
        return myAccount.returningData;
    }
    /**
     * Ustawia adres email użytkownika dla strony startowej.
     * @param userEmail adres email użytkownika
     */
    void setUserEmail(String userEmail) {startPage.email = userEmail;}
    /**
     * Pobiera listę miejsc wylotu z wyszukiwarki.
     * @return lista miejsc wylotu
     */
    List<String> getSearchEngineDeparture(){
        return searchEngine.departure;
    }
    /**
     * Pobiera listę kierunków podróży z wyszukiwarki.
     * @return lista kierunków podróży
     */
    List<String> getSearchEngineDestination(){
        return searchEngine.destination;
    }
    /**
     * Pobiera nazwę administratora z panelu administratora.
     * @return nazwa administratora
     */
    String getDashboardAdminName() {
        return dashboard.adminName;
    }
    /**
     * Pobiera ilość klientów z panelu administratora.
     * @return ilość klientów
     */
    int getDashboardClientsQuantity() {
        return dashboard.clientsQuantity;
    }
    /**
     * Pobiera ilość wycieczek z panelu administratora.
     * @return ilość wycieczek
     */
    int getDashboardTripsQuantity() {
        return dashboard.tripsQuantity;
    }
    /**
     * Pobiera ilość rezerwacji z panelu administratora.
     * @return ilość rezerwacji
     */
    int getDashboardReservationsQuantity() {
        return dashboard.reservationsQuantity;
    }
    /**
     * Pobiera ilość wpływów z panelu administratora.
     * @return ilość wpływów
     */
    int getDashboardIncomeQuantity() {
        return dashboard.incomeQuantity;
    }
    /**
     * Pobiera listę numerów telefonów z panelu administratora.
     * @return lista numerów telefonów
     */
    List<String> getDashboardPhoneNumbers(){
        return dashboard.phoneNumbers;
    }
    /**
     * Pobiera listę danych klientów.
     * @return lista danych klientów
     */
    List<String> getClientsList(){return clients.clientsData;}
    /**
     * Pobiera listę danych wycieczek.
     * @return lista danych wycieczek
     */
    List<String> getTripsList() {
        return trips.tripsData;
    }
    /**
     * Pobiera listę danych rezerwacji.
     * @return lista danych rezerwacji
     */
    List<String> getReservationsList() {
        return reservations.resData;
    }
    /**
     * Konstruktor klasy Client.
     * @param operation operacja
     * @param data lista danych
     */
    Client(String operation, List<String> data){
        this.operation = operation;
        this.data.addAll(data);
        operate();
    }
    /**
     * Wykonuje odpowiednią operację łącząc się z serwerem.
     */
    private void operate() {
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
                    registration.clientExists = dataInputStream.readUTF();
                }
                case "myAccountUpdate" -> {
                    dataOutputStream.writeInt(data.size());
                    dataOutputStream.flush();
                    for (String s : data) {
                        dataOutputStream.writeUTF(s);
                        dataOutputStream.flush();
                    }
                    myAccount.returningDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < myAccount.returningDataListLength; i++) {
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
                    trips.tripsDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < trips.tripsDataListLength; i++) {
                        trips.tripsData.add(dataInputStream.readUTF());
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
                    clients.clientsDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < clients.clientsDataListLength; i++) {
                        clients.clientsData.add(dataInputStream.readUTF());
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
                    reservations.resDataListLength = dataInputStream.readInt();
                    for (int i = 0; i < reservations.resDataListLength; i++) {
                        reservations.resData.add(dataInputStream.readUTF());
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
