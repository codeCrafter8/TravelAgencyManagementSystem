import com.server.Database;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Klasa zawierająca pola i metody testujące klasę Database
 */
public class DatabaseTest {
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
        new Database("addClient", data);
        data.clear();
        data.add("");
        data.add("jacek@wp.pl");
        Database database = new Database("", data);
        database.connectWithDatabase();
        database.findClientData();
        returningData = database.getReturningData();
        assertEquals(returningData.get(0), "Jacek");
        assertEquals(returningData.get(1), "Jas");
        assertEquals(returningData.get(2), "jacek@wp.pl");
        assertEquals(returningData.get(3), "555666777");
        data.clear();
        data.add("");
        data.add(returningData.get(4));
        new Database("deleteClient", data);
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
        new Database("dataEdition", data);
        data.clear();
        data.add("");
        data.add("jakub@wp.pl");
        Database database = new Database("", data);
        database.connectWithDatabase();
        database.findClientData();
        returningData = database.getReturningData();
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
        new Database("dataEdition", data);
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
        new Database("addTrip", data);
        data.clear();
        data.add("");
        data.add("add");
        Database database = new Database("", data);
        database.connectWithDatabase();
        database.findTrip();
        returningData = database.getReturningData();
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
        new Database("deleteTrip", data);
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
        new Database("editTrip", data);
        data.clear();
        data.add("");
        data.add("edit");
        data.add("19");
        Database database = new Database("", data);
        database.connectWithDatabase();
        database.findTrip();
        returningData = database.getReturningData();
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
        new Database("editTrip", data);
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
        new Database("addReservation", data);
        data.clear();
        data.add("");
        Database database = new Database("", data);
        database.connectWithDatabase();
        database.findReservation();
        returningData = database.getReturningData();
        assertEquals(returningData.get(1), "19");
        assertEquals(returningData.get(2), "1");
        assertEquals(returningData.get(3), "2");
        assertEquals(returningData.get(4), "Standard");
        data.clear();
        data.add("");
        data.add(returningData.get(0));
        new Database("deleteRes", data);
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
        new Database("login", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        Database database = new Database("", data);
        database.connectWithDatabase();
        boolean userLogged = database.getUserLogged();
        assertTrue(userLogged);
        new Database("logOut", data);
    }
    /**
     * Testuje wylogowywanie się
     */
    @Test
    public void testLogOut(){
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        new Database("logOut", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        Database database = new Database("", data);
        database.connectWithDatabase();
        boolean userLogged = database.getUserLogged();
        assertFalse(userLogged);
    }
    /**
     * Testuje wylogowywanie wszystkich
     */
    @Test
    public void testLogOutEveryone(){
        data.clear();
        data.add("");
        new Database("logOutEveryone", data);
        data.clear();
        data.add("");
        data.add("jan@wp.pl");
        Database database = new Database("", data);
        database.connectWithDatabase();
        boolean userLogged = database.getUserLogged();
        assertFalse(userLogged);
        data.clear();
        data.add("");
        data.add("julia@interia.pl");
        database = new Database("", data);
        database.connectWithDatabase();
        userLogged = database.getUserLogged();
        assertFalse(userLogged);
    }
}