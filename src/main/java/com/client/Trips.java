package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.TripValidator;
import com.server.LogsAdmins;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 * Class containing fields and methods for handling a window with functionality related to trips.
 */
public class Trips extends javax.swing.JFrame {
    /**
     * Attribute representing a list storing data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Attribute representing a list storing data returned from the Client class.
     */
    List<String> tripsData = new ArrayList<>();
    /**
     * Element for sorting the trips table.
     */
    private TableRowSorter<TableModel> rowSorter;
    /**
     * Attribute holding the city during the search for a trip.
     */
    private String cityToSearch;
    /**
     * Attribute representing an object of the Client class.
     */
    private Client client;
    /**
     * Attribute holding the user's email.
     */
    private String email;
    /**
     * Constructor responsible for initializing the GUI.
     */
    public Trips() {
        initComponents();
    }
    /**
     * Constructor responsible for initializing the GUI and relevant elements.
     * @param client parameter storing an object of the Client class.
     * @param adminName parameter holding the administrator's name.
     */
    public Trips(Client client, String adminName){
        this.client = client;
        this.email = client.getUserEmail();
        initComponents();
        adminNameLabel.setText(adminName);
        generateData();
        populateTable();
        searchTrips();
    }
    /**
     * Method for initializing graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        createAdminPanel();
        createOptionsPanel();
        createMenuPanel();
        createTopPanel();
        createTable();
        setButtons();
        createLayout();
    }

    /**
     * Sets the properties of the window.
     * The window will be disposed and the "logOut" action will be sent to the server
     * when the window is closed.
     */
    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wycieczki");
        getContentPane().setBackground(ColorUtils.BEIGE);
        setMinimumSize(DimensionUtils.WINDOW_DIMENSION);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    new Client(data);
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("Trips", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    /**
     * Creates the menu panel with admin-related options.
     */
    private void createMenuPanel(){
        menuPanel.setBackground(ColorUtils.BROWN);
        menuPanel.setPreferredSize(DimensionUtils.MENU_PANEL_DIMENSION);
        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanelLayout.createSequentialGroup()
                                .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(284, Short.MAX_VALUE))
        );
    }

    /**
     * Sets the text and properties for various labels in the user interface.
     */
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(DimensionUtils.ADMIN_NAME_LABEL_DIMENSION);

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        searchTripLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchTripLabel.setText("Wyszukaj wycieczkę po mieście");

        wrongTripLabel.setForeground(Color.red);
        wrongTripLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Creates the admin panel with admin information and icon.
     */
    private void createAdminPanel(){
        adminPanel.setBackground(ColorUtils.BROWN);
        javax.swing.GroupLayout adminPanel1Layout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanel1Layout);
        adminPanel1Layout.setHorizontalGroup(
                adminPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(adminNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(adminLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adminIconLabel)
                                .addGap(54, 54, 54))
        );
        adminPanel1Layout.setVerticalGroup(
                adminPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adminPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(adminIconLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(adminNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(adminLabel)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the options panel with buttons for different sections.
     */
    private void createOptionsPanel(){
        optionsPanel.setMinimumSize(DimensionUtils.OPTIONS_PANEL_MIN_SIZE_DIMENSION);
        optionsPanel.setPreferredSize(DimensionUtils.OPTIONS_PANEL_PREF_SIZE_DIMENSION);
        optionsPanel.setBackground(ColorUtils.BROWN);
        javax.swing.GroupLayout optionsPanel1Layout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanel1Layout);
        optionsPanel1Layout.setHorizontalGroup(
                optionsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addComponent(tripsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reservationsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanel1Layout.setVerticalGroup(
                optionsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(optionsPanel1Layout.createSequentialGroup()
                                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(clientsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(reservationsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the top panel with a log-out button.
     */
    private void createTopPanel(){
        topPanel.setBackground(ColorUtils.LIGHT_BROWN);
        topPanel.setPreferredSize(DimensionUtils.TOP_PANEL_DIMENSION);
        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(753, Short.MAX_VALUE)
                                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        topPanelLayout.setVerticalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the table to display trips information.
     */
    private void createTable(){
        tripsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Kraj", "Miasto", "Cena/Osoba", "Limit osób"
                }
        )
        {public boolean isCellEditable(int row, int column){
            return column != 0;
        }});
        tripsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        tripsTable.setSelectionBackground(ColorUtils.BEIGE);
        tripsTableScrollPane.setViewportView(tripsTable);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(ColorUtils.LIGHT_BROWN);
        for (int i = 0; i < tripsTable.getModel().getColumnCount(); i++) {
            tripsTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    /**
     * Sets the properties and action listeners for various buttons.
     */
    private void setButtons(){
        addTripButton.setBackground(ColorUtils.CREAM);
        addTripButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        addTripButton.setText("+ Dodaj Wycieczkę");
        addTripButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addTripButton.addActionListener(this::addTripButtonActionPerformed);

        deleteTripButton.setBackground(ColorUtils.CREAM);
        deleteTripButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        deleteTripButton.setText("Usuń Wycieczkę");
        deleteTripButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteTripButton.addActionListener(this::deleteTripButtonActionPerformed);

        editTripButton.setBackground(ColorUtils.CREAM);
        editTripButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editTripButton.setText("Edytuj Wycieczkę");
        editTripButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editTripButton.addActionListener(this::editTripButtonActionPerformed);

        logOutButton.setBackground(ColorUtils.MILK);
        logOutButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        logOutButton.setForeground(Color.white);
        logOutButton.setText("Wyloguj");
        logOutButton.setContentAreaFilled(false);
        logOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutButton.setPreferredSize(DimensionUtils.LOG_OUT_BUTTON_DIMENSION);
        logOutButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                logOutButton.setForeground(Color.lightGray);
                logOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                logOutButton.setForeground(Color.white);
        });
        logOutButton.addActionListener(this::logOutButtonActionPerformed);

        panelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        panelButton.setText("    Panel");
        panelButton.setBorder(null);
        panelButton.setContentAreaFilled(false);
        panelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        panelButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                panelButton.setForeground(ColorUtils.LIGHT_GREY);
                panelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                panelButton.setForeground(null);
        });
        panelButton.addActionListener(this::panelButtonActionPerformed);

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsButton.setText("    Klienci");
        clientsButton.setBorder(null);
        clientsButton.setContentAreaFilled(false);
        clientsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        clientsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsButton.setForeground(ColorUtils.LIGHT_GREY);
                clientsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                clientsButton.setForeground(null);
        });
        clientsButton.addActionListener(this::clientsButtonActionPerformed);

        tripsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        tripsButton.setText("   Wycieczki");
        tripsButton.setActionCommand(" Wycieczki");
        tripsButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
        tripsButton.setContentAreaFilled(false);
        tripsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tripsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        tripsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                tripsButton.setForeground(ColorUtils.LIGHT_GREY);
                tripsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                tripsButton.setForeground(null);
        });

        reservationsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        reservationsButton.setText("Rezerwacje");
        reservationsButton.setContentAreaFilled(false);
        reservationsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reservationsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        reservationsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                reservationsButton.setForeground(ColorUtils.LIGHT_GREY);
                reservationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                reservationsButton.setForeground(null);
        });
        reservationsButton.addActionListener(this::reservationsButtonActionPerformed);
    }

    /**
     * Creates the overall layout of the user interface.
     */
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(searchTripLabel)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(wrongTripLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(320, 320, 320)
                                                                                .addComponent(addTripButton)
                                                                                .addGap(21, 21, 21))
                                                                        .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(282, 282, 282)
                                                .addComponent(deleteTripButton)
                                                .addGap(35, 35, 35)
                                                .addComponent(editTripButton)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchTripLabel)
                                                .addGap(0, 0, 0)
                                                .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(wrongTripLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(addTripButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteTripButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editTripButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method handling the click event of the "Edit Trip" button.
     * @param evt The event triggered by clicking the button.
     */
    private void editTripButtonActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        if (tripsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "No trip selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            int id = Integer.parseInt(model.getValueAt(tripsTable.getSelectedRow(), 0).toString());
            String country = model.getValueAt(tripsTable.getSelectedRow(), 1).toString();
            String city = model.getValueAt(tripsTable.getSelectedRow(), 2).toString();
            String price = model.getValueAt(tripsTable.getSelectedRow(), 3).toString();
            String peopleLimit = model.getValueAt(tripsTable.getSelectedRow(), 4).toString();
            if (country.equals("") || city.equals("") || price.equals("") || peopleLimit.equals("")) {
                JOptionPane.showMessageDialog(this, "Empty fields detected!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                data.clear();
                data.add("editTrip");
                data.add(Integer.toString(id));
                data.add(city);
                data.add(country);
                data.add(price);
                data.add(peopleLimit);
                new Client(data);
                data.clear();
            }
        }
    }

    /**
     * Method handling the click event of the "Log Out" button.
     * @param evt The event triggered by clicking the button.
     */
    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        data.clear();
        data.add("logOut");
        data.add(email);
        new Client(data);
        data.clear();
        dispose();
        new StartPage().setVisible(true);
    }

    /**
     * Method for retrieving appropriate data from the Client class.
     */
    private void generateData() {
        data.clear();
        data.add("tripsUpdate");
        Client client1 = new Client(data);
        tripsData.addAll(client1.getReturningData());
    }

    /**
     * Method for populating the table holding the trips.
     */
    private void populateTable() {
        int counter = 0;
        int size = (tripsData.size() / 5);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        for (int i = 0; i < size; i++) {
            model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), tripsData.get(counter + 2), tripsData.get(counter + 3),
                    tripsData.get(counter + 4)});
            if (size > 1)
                counter += 5;
        }
    }

    /**
     * Method responsible for validating the city of the searched trip.
     */
    private void performCityValidation() {
        cityToSearch = searchClientTextField.getText();
        if (TripValidator.isCountryOrCityValid(cityToSearch))
            wrongTripLabel.setText("");
        else
            wrongTripLabel.setText("Please check if the provided city is correct.");
        if (cityToSearch.equals(""))
            wrongTripLabel.setText("");
    }

    /**
     * Method allowing searching for trips by city.
     */
    private void searchTrips() {
        tripsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSorter = new TableRowSorter<>(tripsTable.getModel());
        tripsTable.setRowSorter(rowSorter);
        searchClientTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performCityValidation();
                if (cityToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + cityToSearch, 2));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performCityValidation();
                if (cityToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + cityToSearch));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performCityValidation();
                throw new UnsupportedOperationException("Unsupported event!");
            }
        });
    }

    /**
     * Method handling the click event of the "Delete Trip" button.
     * @param evt The event triggered by clicking the button.
     */
    private void deleteTripButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        if (tripsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "No trip selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            data.clear();
            data.add("deleteTrip");
            data.add(model.getValueAt(tripsTable.getSelectedRow(), 0).toString());
            new Client(data);
            data.clear();
            model.removeRow(tripsTable.getSelectedRow());
        }
    }

    /**
     * Method handling the click event of the "Add Trip" button.
     * @param evt The event triggered by clicking the button.
     */
    private void addTripButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new TripAddition(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the click event of the "Clients" button.
     * @param evt The event triggered by clicking the button.
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Clients(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the click event of the "Reservations" button.
     * @param evt The event triggered by clicking the button.
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Reservations(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the click event of the "Panel" button.
     * @param evt The event triggered by clicking the button.
     */
    private void panelButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method allowing the window to be launched.
     * @param args Arguments passed when launching the application.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Trips().setVisible(true));
    }
    /**
     * A main panel that contains the menu, admin panel, and options panel.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * A panel that displays the admin icon and name.
     */
    private final JPanel adminPanel = new JPanel();

    /**
     * A panel that contains various options for the admin.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A label that displays the admin icon.
     */
    private final JLabel adminIconLabel = new JLabel();

    /**
     * A label that displays the "Admin" text.
     */
    private final JLabel adminLabel = new JLabel();

    /**
     * A top panel that contains the log-out button.
     */
    private final JPanel topPanel = new JPanel();

    /**
     * A label that displays the "Wyszukaj wycieczkę po mieście" text.
     */
    private final JLabel searchTripLabel = new JLabel();

    /**
     * A button to add a new trip.
     */
    private final JButton addTripButton = new JButton();

    /**
     * A scroll pane that contains the table to display trips information.
     */
    private final JScrollPane tripsTableScrollPane = new JScrollPane();

    /**
     * A button to delete a trip.
     */
    private final JButton deleteTripButton = new JButton();

    /**
     * A button to edit a trip.
     */
    private final JButton editTripButton = new JButton();

    /**
     * A lA abel for the administrator's name.
     */
    private final javax.swing.JLabel adminNameLabel = new JLabel();
    /**
     * A button for navigating to the "Clients" tab.
     */
    private final javax.swing.JButton clientsButton = new JButton();
    /**
     * A button for logging out.
     */
    private final javax.swing.JButton logOutButton = new JButton();
    /**
     * A button for navigating to the "Panel" tab.
     */
    private final javax.swing.JButton panelButton = new JButton();
    /**
     * A button for navigating to the "Reservations" tab.
     */
    private final javax.swing.JButton reservationsButton = new JButton();
    /**
     * A field for entering the city during the search.
     */
    private final javax.swing.JTextField searchClientTextField = new JTextField();
    /**
     * A button for navigating to the "Trips" tab.
     */
    private final javax.swing.JButton tripsButton = new JButton();
    /**
     * A table holding the trips.
     */
    private final javax.swing.JTable tripsTable = new JTable();
    /**
     * A label informing that the city during the search is incorrect.
     */
    private final javax.swing.JLabel wrongTripLabel = new JLabel();
}