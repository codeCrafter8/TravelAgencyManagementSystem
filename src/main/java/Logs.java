import org.apache.log4j.*;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Logs {
    protected final Logger log;
    protected static String message;
    protected static String className;
    public Logs(){
        log = null;
    }
    public Logs(String className, String messageType, String message) {
        Logs.message = message;
        Logs.className = className;
        this.log = LogManager.getLogger(className + ".class");
        writeToFile(messageType);
    }

    protected void writeToFile(String messageType) {
        try {
            File folder = new File("Logs");
            if (!folder.exists())
                folder.mkdir();

            File file = new File("Logs/Logs.log");
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
class LogsServer extends Logs{
    public static List<String> logs = new ArrayList<>();
    LogsServer(String className, String messageType, String message){
        super(className, messageType, message);
        setMessage(message);
    }
    public void setMessage(String message){
        logs.add(message);
    }
}

class LogsAdmins extends Logs{
    public static List<String> logs = new ArrayList<>();
    LogsAdmins(String className, String messageType, String message){
        super(className, messageType, message);
        setMessage(message);
    }
    public void setMessage(String message){
        logs.add(message);
    }
}

class LogsClients extends Logs{
    public static List<String> logs = new ArrayList<>();
    LogsClients(String className, String messageType, String message){
        super(className, messageType, message);
        setMessage(message);
    }
    public void setMessage(String message){
        logs.add(message);
    }
}

