package com.server;

import com.server.database.DatabaseHandler;
import com.server.logs.LogsServer;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A class allowing threads to work independently of the server GUI
 */
class BackgroundHandler extends SwingWorker<Void, Void> implements Serializable {

    /**
     * An attribute representing a list of all server threads.
     */
    private final List<Thread> threads;

    /**
     * An attribute representing the socket to which the client is connected.
     */
    public Socket socket;

    public BackgroundHandler(Socket socket, List<Thread> threads) {
        this.socket = socket;
        this.threads = threads;
    }

    /**
     * An overridden method converting data to a string type
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * A class implementing the Runnable interface to create new threads on the server
     */
    private class ClientThread implements Runnable {

        /**
         * An attribute holding a list containing data passed from the server to the database
         */
        private final List<String> data = new ArrayList<>();

        /**
         * Sends data to the client
         */
        private void sendData() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                DatabaseHandler databaseHandler = new DatabaseHandler(data.get(0), data);
                objectOutputStream.writeObject(databaseHandler.getReturningData());
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                outputStream.write(byteArray);
                outputStream.flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * An overridden method run, handling the running thread
         */
        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[2048];
                Object object = new ObjectInputStream(new ByteArrayInputStream(bytes, 0, inputStream.read(bytes))).readObject();
                data.clear();
                data.addAll((List<String>) object);
                sendData();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * An overridden method doInBackground, allowing threads to work in the background without affecting the server GUI
     * @return No return value
     */
    @Override
    protected Void doInBackground() {
        Thread thread = new Thread(new ClientThread());
        threads.add(thread);
        thread.start();
        new LogsServer("Background", "info", "[ " + new java.util.Date() + " ] " + "Nowy wątek o nazwie " + thread.getName() + " został utworzony.");
        return null;
    }
}
