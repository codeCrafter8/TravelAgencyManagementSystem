package com.server;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody operujące na zdarzeniach pochodzących od działań klientów
 */
public class LogsClients extends Logs{
    /**
     * Atrybut bedący listą przechowującą zdarzenia pochodzące od działań klientów
     */
    public static List<String> logs = new ArrayList<>();
    /**
     * Konstruktor wywołujący konstuktor klasy bazowej oraz metodę odpowiedzialną za zapis zdarzenia do listy
     * @param className parametr określający nazwę klasy, z której aktualnie pobierane są zdarzenia
     * @param messageType parametr określający typ zdarzenia
     * @param message parametr określający wiadomość zdarzenia
     */
    public LogsClients(String className, String messageType, String message){
        super(className, messageType, message);
        setMessage(message);
    }
    /**
     * Metoda zapisująca zdarzenie do listy
     * @param message parametr określający wiadomość zdarzenia
     */
    public void setMessage(String message){
        logs.add(message);
    }
}
