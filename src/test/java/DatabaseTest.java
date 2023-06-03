import com.server.Database;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class DatabaseTest {
    @Test
    public void testAddClientDatabase(){
        new Database("addClient", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testLoginDatabase(){
        new Database("login", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testDashboardUpdateDatabase(){
        new Database("dashboardUpdate", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testClientsUpdateDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.updateClients();
    }
    @Test
    public void testEditClientDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.editClient();
    }
    @Test
    public void testChangeClientPasswordDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.changeClientPassword();
    }
    @Test
    public void testLogOutEveryoneDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.logOutEveryone();
    }
    @Test
    public void testLogOutDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.logOut();
    }
    @Test
    public void testTripsListPopulationDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.tripsListPopulate();
    }
    @Test
    public void testGetDestinationDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.getDestination();
    }
    @Test
    public void testTripsUpdateDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.updateTrips();
    }
    @Test
    public void testDeleteTripDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.deleteTrip();
    }
    @Test
    public void testAddTripDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.addTrip();
    }
    @Test
    public void testMyAccountUpdateDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.updateMyAccount();
    }
    @Test
    public void testDataEditionDatabase(){
        new Database("dataEdition", new ArrayList<>()).connectWithDatabase();
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.editData();
    }
    @Test
    public void testSendNumbersDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.sendNumbers();
    }
    @Test
    public void testGetNumbersDatabase(){
        Database database = new Database("", new ArrayList<>());
        database.connectWithDatabase();
        database.getNumbers();
    }
}
