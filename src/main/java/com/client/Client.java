package com.client;

import java.io.*;
import java.net.Socket;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/**
 * A class containing fields and methods for handling client-server communication.
 */
public class Client implements Serializable {
    
    /**
     * An instance of the StartPage class.
     */
    private final StartPage startPage = new StartPage();
    
    /**
     * An instance of the Offer class.
     */
    private final Offer offer = new Offer();
    
    /**
     * An attribute that is a list containing data passed from the client to the server.
     */
    private final List<String> data = new ArrayList<>();
    
    /**
     * Gets information if the administrator is logged in.
     * @return true if the administrator is logged in, false otherwise.
     */
    boolean getStartPageAdminLogged() {
        return startPage.adminLogged;
    }
    
    /**
     * Gets the email address from the start page.
     * @return the email address.
     */
    String getUserEmail() {
        return startPage.email;
    }
    
    /**
     * Gets the password from the start page.
     * @return the password.
     */
    String getUserPassword() {
        return startPage.password;
    }
    
    /**
     * Gets the user identifier in the trip offer.
     * @return the user identifier in the trip offer.
     */
    int getOfferClientID() {
        return offer.userID;
    }
    
    /**
     * Sets the user's email address for the start page.
     * @param userEmail the user's email address.
     */
    void setUserEmail(String userEmail) {
        startPage.email = userEmail;
    }
    
    /**
     * A list with data returned from the server.
     */
    private final List<String> returningData = new ArrayList<>();
    
    /**
     * Gets the list with data returned from the server.
     * @return the list with data returned from the server.
     */
    List<String> getReturningData() {
        return returningData;
    }
    
    /**
     * An attribute representing a socket for connecting to the server.
     */
    private Socket socket;
    /**
     * An overridden method converting data to a string.
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    /**
     * A constructor of the Client class.
     * @param data the list of data.
     */
    Client(List<String> data) {
        this.data.addAll(data);
        try {
            socket = new Socket("localhost", 1522);
            sendData();
            getData();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Sends data to the server.
     */
    private void sendData() {
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
     * Receives data from the server.
     */
    private void getData() {
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
