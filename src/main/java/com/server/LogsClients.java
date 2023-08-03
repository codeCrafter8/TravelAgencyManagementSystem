package com.server;

import java.util.ArrayList;
import java.util.List;
/**
 * Class containing fields and methods operating on events generated by clients' actions.
 */
public class LogsClients extends Logs {
    /**
     * Attribute representing a list that stores events generated by clients' actions.
     */
    public static List<String> logs = new ArrayList<>();

    /**
     * Constructor calling the base class constructor and the method responsible for saving the event to the list.
     *
     * @param className   parameter specifying the name of the class from which events are currently being retrieved
     * @param messageType parameter specifying the type of event
     * @param message     parameter specifying the event message
     */
    public LogsClients(String className, String messageType, String message) {
        super(className, messageType, message);
        setMessage(message);
    }

    /**
     * Method for saving the event to the list.
     *
     * @param message parameter specifying the event message
     */
    public void setMessage(String message) {
        logs.add(message);
    }
}
