package com.server;

import org.apache.log4j.*;
import java.io.File;
import java.io.FileWriter;
/**
 * Klasa zawierająca pola i metody umożliwiające zapis zdarzeń generowanych przez aplikację do pliku
 */
public class Logs {
    /**
     * Atrybut służący do ustawienia odpowiedniego typu loga zdarzenia
     */
    private final Logger log;
    /**
     * Atrybut określający wiadomość zdarzenia
     */
    private static String message;
    /**
     * Atrybut określający nazwę klasy, z której aktualnie pobierane są zdarzenia
     */
    private static String className;
    /**
     * Konstruktor umożliwiający inicjację pól finalnych i wywołujący metodę odpowiedzialną za zapis zdarzenia do pliku
     * @param className parametr określający nazwę klasy, z której aktualnie pobierane są zdarzenia
     * @param messageType parametr określający typ zdarzenia
     * @param message parametr określający wiadomość zdarzenia
     */
    public Logs(String className, String messageType, String message) {
        Logs.message = message;
        Logs.className = className;
        this.log = LogManager.getLogger(className + ".class");
        writeToFile(messageType);
    }
    /**
     * Metoda odpowiedzialna za zapis zdarzenia do pliku
     * @param messageType parametr określający typ zdarzenia
     */
    protected void writeToFile(String messageType) {
        try {
            File folder = new File("com.server.Logs");
            if (!folder.exists())
                folder.mkdir();
            File file = new File("com.server.Logs/com.server.Logs.log");
            FileWriter fileWriter = new FileWriter(file, true);
            switch (messageType) {
                case "fatal" -> log.fatal(Logs.message);
                case "info" -> log.info(Logs.message);
                case "error" -> log.error(Logs.message);
            }
            fileWriter.write("[" + messageType.toUpperCase() + "] " + Logs.className + ": " + Logs.message + "\n");
            fileWriter.close();

        } catch (Exception e) {
            new LogsServer(className, messageType, message).setMessage(e.getMessage());
            log.fatal("[ " + new java.util.Date() + " ] " + "Error writing log to file");
        }
    }
}