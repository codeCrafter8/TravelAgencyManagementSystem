import com.server.*;
import com.client.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void testGenerateData() {
        Dashboard dashboard = new Dashboard(new Client(), "");
        dashboard.generateData();
        assertNull(dashboard.adminName);
        assertEquals(0, dashboard.clientsQuantity);
        assertEquals(0, dashboard.tripsQuantity);
        /*assertEquals(expectedReservationsQuantity, dashboard.reservationsQuantity);
        assertEquals(expectedIncomeQuantity, dashboard.incomeQuantity);
        assertEquals(expectedAdminNameLabel, dashboard.adminNameLabel.getText());
        assertEquals(expectedClientsQuantityString, dashboard.clientsNumberLabel.getText());
        assertEquals(expectedTripsQuantityString, dashboard.tripsNumberLabel.getText());
        assertEquals(expectedReservationsQuantityString, dashboard.reservationsNumberLabel.getText());
        assertEquals(expectedIncomeQuantityString, dashboard.incomeNumberLabel.getText());
        DefaultListModel<String> expectedListModel = new DefaultListModel<>();
        for (String number : expectedPhoneNumbers) {
            expectedListModel.addElement(number);
        }
        DefaultListModel<String> actualListModel = (DefaultListModel<String>) dashboard.jList1.getModel();
        //assertEquals(expectedListModel, actualListModel);*/
    }
}
