package database;

import com.server.database.DatabaseHandler;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa zawierająca pola i metody testujące klasę Database
 */
public class DatabaseHandlerTest {
    /**
     * Lista z danymi przekazywanymi do bazy danych
     */
    List<String> data = new ArrayList<>();
    /**
     * Lista z danymi zwracanymi z bazy danych
     */
    List<String> returningData = new ArrayList<>();
    /**
     * Testuje dodawanie klienta
     */
    @Test
    public void testAddClient(){
        data.clear();
        data.add("");
        data.add("Jacek");
        data.add("Jas");
        data.add("555666777");
        data.add("jacek@wp.pl");
        data.add("Jacek123");
        data.add("false");
        new DatabaseHandler("addClient", data);
        data.clear();
        data.add("");
        data.add("jacek@wp.pl");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        databaseHandler.findClientData();
        returningData = databaseHandler.getReturningData();
        assertEquals(returningData.get(0), "Jacek");
        assertEquals(returningData.get(1), "Jas");
        assertEquals(returningData.get(2), "jacek@wp.pl");
        assertEquals(returningData.get(3), "555666777");
        data.clear();
        data.add("");
        data.add(returningData.get(4));
        new DatabaseHandler("deleteClient", data);
    }
    /**
     * Testuje edytowanie danych klienta
     */
    @Test
    public void testEditData(){
        data.clear();
        data.add("");
        data.add("Jakub");
        data.add("Hajduk");
        data.add("jakub@wp.pl");
        data.add("555666777");
        data.add("74");
        data.add("true");
        new DatabaseHandler("dataEdition", data);
        data.clear();
        data.add("");
        data.add("jakub@wp.pl");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        databaseHandler.findClientData();
        returningData = databaseHandler.getReturningData();
        assertEquals(returningData.get(0), "Jakub");
        assertEquals(returningData.get(1), "Hajduk");
        assertEquals(returningData.get(2), "jakub@wp.pl");
        assertEquals(returningData.get(3), "555666777");
        data.clear();
        data.add("");
        data.add("Jan");
        data.add("Molar");
        data.add("jan@wp.pl");
        data.add("123555678");
        data.add("74");
        data.add("true");
        new DatabaseHandler("dataEdition", data);
    }
    /**
     * Testuje dodawanie wycieczki
     */
    @Test
    public void testAddTrip(){
        data.clear();
        data.add("");
        data.add("Gruzja");
        data.add("Tbilisi");
        data.add("Warszawa");
        data.add("1200");
        data.add("4");
        data.add("Gruzja Hotel");
        data.add("2023-09-10");
        data.add("2023-09-17");
        new DatabaseHandler("addTrip", data);
        data.clear();
        data.add("");
        data.add("add");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        databaseHandler.findTrip();
        returningData = databaseHandler.getReturningData();
        assertEquals(returningData.get(0), "Gruzja");
        assertEquals(returningData.get(1), "Tbilisi");
        assertEquals(returningData.get(2), "Warszawa");
        assertEquals(returningData.get(3), "1200");
        assertEquals(returningData.get(4), "4");
        assertEquals(returningData.get(5), "Gruzja Hotel");
        assertEquals(returningData.get(6), "2023-09-10");
        assertEquals(returningData.get(7), "2023-09-17");
        data.clear();
        data.add("");
        data.add(returningData.get(8));
        new DatabaseHandler("deleteTrip", data);
    }
    /**
     * Testuje edytowanie wycieczki
     */
    @Test
    public void testEditTrip(){
        data.clear();
        data.add("");
        data.add("19");
        data.add("Amsterdam");
        data.add("Holandia");
        data.add("1300");
        data.add("3");
        new DatabaseHandler("editTrip", data);
        data.clear();
        data.add("");
        data.add("edit");
        data.add("19");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        databaseHandler.findTrip();
        returningData = databaseHandler.getReturningData();
        assertEquals(returningData.get(0), "Holandia");
        assertEquals(returningData.get(1), "Amsterdam");
        assertEquals(returningData.get(3), "1300");
        assertEquals(returningData.get(4), "3");
        data.clear();
        data.add("");
        data.add("19");
        data.add("Emiraty Arabskie");
        data.add("Dubaj");
        data.add("3600");
        data.add("4");
        new DatabaseHandler("editTrip", data);
    }
    /**
     * Testuje dodawanie rezerwacji
     */
    @Test
    public void testAddReservation(){
        data.clear();
        data.add("");
        data.add("19");
        data.add("1");
        data.add("2");
        data.add("Standard");
        new DatabaseHandler("addReservation", data);
        data.clear();
        data.add("");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        databaseHandler.findReservation();
        returningData = databaseHandler.getReturningData();
        assertEquals(returningData.get(1), "19");
        assertEquals(returningData.get(2), "1");
        assertEquals(returningData.get(3), "2");
        assertEquals(returningData.get(4), "Standard");
        data.clear();
        data.add("");
        data.add(returningData.get(0));
        new DatabaseHandler("deleteRes", data);
    }
    /**
     * Testuje logowanie się
     */
    @Test
    public void testLogin(){
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        data.add("Haslo123");
        new DatabaseHandler("login", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        boolean userLogged = databaseHandler.getUserLogged();
        assertTrue(userLogged);
        new DatabaseHandler("logOut", data);
    }
    /**
     * Testuje wylogowywanie się
     */
    @Test
    public void testLogOut(){
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        new DatabaseHandler("logOut", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        boolean userLogged = databaseHandler.getUserLogged();
        assertFalse(userLogged);
    }
    /**
     * Testuje wylogowywanie wszystkich
     */
    @Test
    public void testLogOutEveryone(){
        data.clear();
        data.add("");
        new DatabaseHandler("logOutEveryone", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        DatabaseHandler databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        boolean userLogged = databaseHandler.getUserLogged();
        assertFalse(userLogged);
        data.clear();
        data.add("");
        data.add("julia@interia.pl");
        databaseHandler = new DatabaseHandler("", data);
        databaseHandler.connectWithDatabase();
        userLogged = databaseHandler.getUserLogged();
        assertFalse(userLogged);
    }
}