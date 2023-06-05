import com.server.Database;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class DatabaseTest {
    @Test
    public void testConnectWithDatabase(){
        new Database("", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testAddClient(){
        new Database("addClient", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testLogin(){
        new Database("login", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testDashboardUpdate(){
        new Database("dashboardUpdate", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testClientsUpdate(){
        new Database("clientsUpdate", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testDeleteClient(){
        new Database("deleteClient", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testEditClient(){
        new Database("editClient", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testChangeClientPassword(){
        new Database("changeClientPassword", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testLogOutEveryone(){
        new Database("logOutEveryone", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testLogOut(){
        new Database("logOut", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testTripsListPopulation(){
        new Database("tripsListPopulation", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testGetDestination(){
        new Database("getDestination", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testGetDeparture(){
        new Database("getDeparture", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testTripsUpdate(){
        new Database("tripsUpdate", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testDeleteTrip(){
        new Database("deleteTrip", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testAddTrip(){
        new Database("addTrip", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testMyAccountUpdate(){
        new Database("myAccountUpdate", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testDataEdition(){
        new Database("dataEdition", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testSendNumbers(){
        new Database("sendNumbers", new ArrayList<>()).connectWithDatabase();
    }
    @Test
    public void testGetNumbers(){
        new Database("getNumbers", new ArrayList<>()).connectWithDatabase();
    }
}