package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.logs.LogsAdmins;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A class containing fields and methods for handling the window that allows adding reservations.
 */
public class ReservationAddition extends javax.swing.JFrame {

    /**
     * An attribute representing a list that stores data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * An attribute representing a list that stores trip data returned from the Client class.
     */
    private final List<String> tripsData = new ArrayList<>();

    /**
     * An attribute representing a list that stores client data returned from the Client class.
     */
    private final List<String> clientsData = new ArrayList<>();

    /**
     * An attribute representing an object of the Client class.
     */
    private final Client client;

    /**
     * An attribute representing the name of the administrator.
     */
    private final String adminName;

    /**
     * A constructor responsible for initializing the GUI and relevant elements.
     * @param client Parameter storing an object of the Client class.
     * @param adminName Parameter storing the administrator's name.
     */
    public ReservationAddition(Client client, String adminName) {
        this.client = client;
        this.adminName = adminName;
        initComponents();
        generateData();
        populateClientsTable();
        populateTripsTable();
    }

    /**
     * Initializes the graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setTripsTable();
        setClientsTable();
        setButtons();
        setPeopleQuantityComponents();
        setInsuranceComponents();
        createLayout();
    }

    /**
     * Sets the window properties for the reservation addition window.
     * This method configures the window closing operation, title, preferred size, and background color.
     * It also adds a window listener to perform necessary actions when the window is closed.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        setTitle("Dodawanie rezerwacji");
        getContentPane().setBackground(ColorUtils.BEIGE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("ReservationAddition", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    /**
     * Sets up the trips table with the necessary configurations and a default table model.
     * This method configures the table's column names, cell editability, and appearance.
     * The table is placed inside a scroll pane for proper display if the content exceeds the visible area.
     */
    private void setTripsTable(){
        tripsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Kraj/Miasto", "Cena/Osoba", "Limit osób", "Termin"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tripsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setSelectionBackground(ColorUtils.BEIGE);
        tripsTableScrollPane.setViewportView(tripsTable);
    }

    /**
     * Sets up the clients table with the necessary configurations and a default table model.
     * This method configures the table's column names, cell editability, and appearance.
     * The table is placed inside a scroll pane for proper display if the content exceeds the visible area.
     */
    private void setClientsTable(){
        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Imię", "Nazwisko", "E-mail", "Nr telefonu"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setSelectionBackground(ColorUtils.BEIGE);
        clientsTableScrollPane.setViewportView(clientsTable);
    }

    /**
     * Sets up the buttons in the reservation addition window.
     * This method configures the appearance and functionality of the submit and cancel buttons.
     */
    private void setButtons(){
        submitButton.setBackground(ColorUtils.DARK_BEIGE);
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setText("Dodaj rezerwację");
        submitButton.addActionListener(evt -> submitButtonActionPerformed());
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(ColorUtils.DARK_BEIGE);
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(DimensionUtils.SUBMIT_CANCEL_BUTTON_DIMENSION);
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed());
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    /**
     * Sets up the components related to the quantity of people for the reservation.
     * This method configures a spinner for selecting the number of people and an associated label.
     */
    private void setPeopleQuantityComponents(){
        peopleQuantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        peopleQuantityLabel.setText("Liczba osób:");
    }

    /**
     * Sets up the components related to the insurance selection for the reservation.
     * This method configures a combo box for selecting the type of insurance and an associated label.
     */
    private void setInsuranceComponents(){
        insuranceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brak", "Standard", "Komfort", "Premium" }));
        insuranceLabel.setText("Ubezpieczenie:");
    }

    /**
     * Creates the layout for the reservation addition window.
     * This method configures the layout of various components, including tables, buttons, and other UI elements.
     */
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 234, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                                        .addComponent(clientsTableScrollPane))
                                .addGap(150, 150, 150))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(333, 333, 333)
                                                .addComponent(peopleQuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(peopleQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(insuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(insuranceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(374, 374, 374)
                                                .addComponent(submitButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(clientsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(peopleQuantityLabel)
                                        .addComponent(peopleQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(insuranceLabel)
                                        .addComponent(insuranceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Fetches relevant data from the Client class.
     */
    private void generateData() {
        data.clear();
        data.add("clientsUpdate");
        Client client1 = new Client(data);
        data.clear();
        data.add("tripsListPopulation");
        clientsData.addAll(client1.getReturningData());
        Client client2 = new Client(data);
        tripsData.addAll(client2.getReturningData());
    }
    
    /**
     * Populates the clients table.
     */
    private void populateClientsTable() {
        int counter = 0;
        int size = (clientsData.size() / 5);
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        for (int i = 0; i < size; i++) {
            model.addRow(new Object[]{clientsData.get(counter), clientsData.get(counter + 1), clientsData.get(counter + 2), clientsData.get(counter + 3),
                    clientsData.get(counter + 4)});
            if (size > 1)
                counter += 5;
        }
    }
    
    /**
     * Populates the trips table.
     */
    private void populateTripsTable() {
        int counter = 0;
        int size = (tripsData.size() / 10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        for (int i = 0; i < size; i++) {
            model.addRow(new Object[]{tripsData.get(counter + 7), tripsData.get(counter) + '/' + tripsData.get(counter + 1), tripsData.get(counter + 4) + " zł", tripsData.get(counter + 6),
                    (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)),});
            if (size > 1)
                counter += 10;
        }
    }
    
    /**
     * Handles the "Add Reservation" button click.
     */
    private void submitButtonActionPerformed() {
        DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
        int clientsRow = clientsTable.getSelectedRow();
        DefaultTableModel tripsModel = (DefaultTableModel) tripsTable.getModel();
        int tripsRow = tripsTable.getSelectedRow();
        if (Integer.parseInt(tripsModel.getValueAt(tripsRow, 3).toString()) < Integer.parseInt(peopleQuantitySpinner.getValue().toString())) {
            JOptionPane.showMessageDialog(null, "Too many people.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            data.clear();
            data.add("addReservation");
            data.add(tripsModel.getValueAt(tripsRow, 0).toString());
            data.add(clientsModel.getValueAt(clientsRow, 0).toString());
            data.add(peopleQuantitySpinner.getValue().toString());
            String item = (String) insuranceComboBox.getSelectedItem();
            if (Objects.equals(item, "None"))
                data.add("");
            else
                data.add(item);
            new Client(data);
            data.clear();
            dispose();
            new Reservations(client, adminName).setVisible(true);
        }
    }
    
    /**
     * Handles the "Cancel Reservation" button click.
     */
    private void cancelButtonActionPerformed() {
        dispose();
        new Reservations(client, adminName).setVisible(true);
    }
    
    /**
     * Allows for the window to be launched.
     * @param args Arguments passed when starting the application
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationAddition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ReservationAddition(null,null).setVisible(true));
    }

    /**
     * A table with clients
     */
    private final javax.swing.JTable clientsTable = new JTable();
    
    /**
     * A dropdown menu with insurance options
     */
    private final javax.swing.JComboBox<String> insuranceComboBox = new JComboBox<>();
    
    /**
     * A spinner allowing for the selection of the number of people
     */
    private final javax.swing.JSpinner peopleQuantitySpinner = new JSpinner();
   
    /**
     * A table with trips
     */
    private final javax.swing.JTable tripsTable = new JTable();
   
    /**
     * A scrollPane used to display the trips table. The table shows trip information such as
     * id, country/city, price per person, maximum number of people, and the date.
     */
    private final JScrollPane tripsTableScrollPane = new JScrollPane();

    /**
     * A scrollPane used to display the clients table. The table shows client information such as
     * id, first name, last name, email, and phone number.
     */
    private final JScrollPane clientsTableScrollPane = new JScrollPane();

    /**
     * A button for submitting the reservation addition. When clicked, it triggers the action
     * for adding the reservation with the selected trip and client data.
     */
    private final JButton submitButton = new JButton();

    /**
     * A button for canceling the reservation addition. When clicked, it cancels the addition
     * process and closes the window.
     */
    private final JButton cancelButton = new JButton();

    /**
     * A label displaying the label for selecting the number of people for the reservation.
     */
    private final JLabel peopleQuantityLabel = new JLabel();

    /**
     * A label displaying the label for selecting the type of insurance for the reservation.
     */
    private final JLabel insuranceLabel = new JLabel();
}