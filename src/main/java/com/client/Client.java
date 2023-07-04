package com.client;

import java.io.*;
import java.net.Socket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * Klasa zawierająca pola i metody obsługujące pracę klienta w komunikacji klient-serwer
 */
public class Client implements Serializable{
    /**
     * Instancja klasy StartPage
     */
    private final StartPage startPage = new StartPage();
    /**
     * Instancja klasy Offer
     */
    private final Offer offer = new Offer();
    /**
     * Atrybut będący listą zawierającą dane przekazywane z klienta do serwera
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Pobiera informację, czy administrator jest zalogowany.
     * @return true, jeśli administrator jest zalogowany, false w przeciwnym razie
     */
    boolean getStartPageAdminLogged(){
        return startPage.adminLogged;
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
     * Ustawia adres email użytkownika dla strony startowej.
     * @param userEmail adres email użytkownika
     */
    void setUserEmail(String userEmail) {startPage.email = userEmail;}
    /**
     * Lista z danymi zwróconymi od serwera
     */
    private final List<String> returningData = new ArrayList<>();
    /**
     * Pobiera listę z danymi zwróconymi od serwera
     * @return lista z danymi zwróconymi od serwera
     */
    List<String> getReturningData() {
        return returningData;
    }
    /**
     * Atrybut będący gniazdem pozwalającym na łączenie się z serwerem
     */
    private Socket socket;
    /**
     * Przesłonięcie metody konwertującej dane na typ łańcucha znaków
     */
    @Override
    public String toString() {
        return super.toString();
    }
    /**
     * Konstruktor klasy Client
     * @param data lista danych
     */
    Client(List<String> data){
        this.data.addAll(data);
        try {
            socket = new Socket("localhost", 1522);
            sendData();
            getData();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metoda wysyłająca dane do serwera
     */
    private void sendData(){
        try {
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            dataOutputStream.write(byteArray);
            dataOutputStream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metoda przyjmująca dane od serwera
     */
    private void getData(){
        try {
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            byte[] bytes = new byte[16384];
            int byteArraySize = dataInputStream.read(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bytes, 0, byteArraySize));
            Object object = objectInputStream.readObject();
            if (object instanceof List<?>)
                returningData.addAll((Collection<? extends String>) object);
            if (data.get(0).equals("login")) {
                startPage.email = data.get(1);
                startPage.password = data.get(2);
                startPage.adminLogged = Boolean.parseBoolean(returningData.get(2));
                offer.userID = Integer.parseInt(returningData.get(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}