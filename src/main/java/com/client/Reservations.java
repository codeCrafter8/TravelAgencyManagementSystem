package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.ClientValidator;
import com.server.logging.LogsAdmins;

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
 * A class containing fields and methods to handle the window for performing operations related to reservations.
 */
public class Reservations extends javax.swing.JFrame {

    /**
     * An attribute representing a list for storing data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * An attribute representing a list for storing reservation data returned from the Client class.
     */
    List<String> resData = new ArrayList<>();

    /**
     * An element for sorting the reservations table.
     */
    private TableRowSorter<TableModel> rowSorter;

    /**
     * An attribute representing an object of the Client class.
     */
    private Client client;

    /**
     * An attribute storing the administrator's email.
     */
    private String email;

    /**
     * An attribute storing the last name of the client when searching for reservations.
     */
    private String lastNameToSearch;

    /**
     * An auxiliary constructor for initializing the GUI.
     */
    public Reservations() {
        initComponents();
    }

    /**
     * A constructor for initializing the GUI and appropriate elements.
     * @param client Parameter storing an object of the Client class.
     * @param adminName Parameter storing the administrator's name.
     */
    public Reservations(Client client, String adminName){
        this.client = client;
        this.email = client.getUserEmail();
        initComponents();
        adminNameLabel.setText(adminName);
        generateData();
        populateTable();
        searchRes();
    }

    /**
     * Initializes graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createAdminPanel();
        setLabels();
        createOptionsPanel();
        setButtons();
        createTopPanel();
        setResTable();
        createLayout();
    }

    /**
     * Set the properties for the reservation window. It configures the window's close operation,
     * title, minimum size, background color, and position relative to the screen. It also adds a
     * window listener to handle the window closing event, which logs out the current user and disposes
     * the window.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rezerwacje");
        setMinimumSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
        setLocationRelativeTo(null);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    data.add(email);
                    new Client(data);
                    data.clear();
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("Reservations", "info", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    /**
     * Creates the menu panel with admin information and options. The panel is configured to display
     * the admin name, an icon, and navigation buttons for clients, trips, and reservations.
     */
    private void createMenuPanel(){
        menuPanel.setBackground(ColorUtils.BROWN);
        menuPanel.setPreferredSize(DimensionUtils.MENU_PANEL_DIMENSION);
        javax.swing.GroupLayout menuPanel1Layout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanel1Layout);
        menuPanel1Layout.setHorizontalGroup(
                menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanel1Layout.setVerticalGroup(
                menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanel1Layout.createSequentialGroup()
                                .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(321, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the admin panel displaying the admin name and an icon.
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
     * Set the labels and icons for various components in the GUI. This method sets the admin icon,
     * admin name label, admin label, search reservation label, and wrong reservation label.
     */
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\admin.png"));

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(DimensionUtils.ADMIN_NAME_LABEL_DIMENSION);

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        searchResLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchResLabel.setText("Wyszukaj rezerwację po nazwisku");

        wrongResLabel.setForeground(Color.RED);
        wrongResLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Creates the options panel with navigation buttons for the admin to access different sections
     * such as panel, clients, trips, and reservations.
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
     * Sets the properties and event handlers for the navigation buttons and other buttons in the GUI.
     * It configures the font, text, background color, and cursor behavior for the buttons.
     */
    private void setButtons(){
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

        tripsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        tripsButton.setText("    Wycieczki");
        tripsButton.setActionCommand(" Wycieczki");
        tripsButton.setBorder(null);
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
        tripsButton.addActionListener(this::tripsButtonActionPerformed);

        reservationsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        reservationsButton.setText("   Rezerwacje");
        reservationsButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
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

        addResButton.setBackground(ColorUtils.CREAM);
        addResButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        addResButton.setText("+ Dodaj Rezerwację");
        addResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addResButton.addActionListener(this::addResButtonActionPerformed);

        deleteResButton.setBackground(ColorUtils.CREAM);
        deleteResButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        deleteResButton.setText("Usuń Rezerwację");
        deleteResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteResButton.addActionListener(this::deleteResButtonActionPerformed);

        editResButton.setBackground(ColorUtils.CREAM);
        editResButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editResButton.setText("Edytuj Rezerwację");
        editResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editResButton.setVisible(false);
    }

    /**
     * Creates the top panel, which contains the logout button for the admin to log out of the application.
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
     * Sets up the reservation table, including its model and appearance. This method configures the
     * table columns, cell editors, selection background, and header renderer.
     */
    private void setResTable(){
        resTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Imię", "Nazwisko", "Wyjazd", "Przyjazd", "Nr telefonu"
                }
        )
        {public boolean isCellEditable(int row, int column){
            return column != 0;
        }});
        resTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setSelectionBackground(ColorUtils.BEIGE);
        resTableScrollPane.setViewportView(resTable);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(ColorUtils.LIGHT_BROWN);
        for (int i = 0; i < resTable.getModel().getColumnCount(); i++) {
            resTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    /**
     * Creates the layout for the entire reservation window. It positions all the components, such as
     * the menu panel, top panel, search labels, and reservation table, within the window.
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
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(44, 44, 44)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(wrongResLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(searchResTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(322, 322, 322)
                                                                                .addComponent(addResButton))
                                                                        .addComponent(searchResLabel)
                                                                        .addComponent(resTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(360, 360, 360)
                                                                .addComponent(deleteResButton)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(editResButton)))
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(searchResLabel)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchResTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(wrongResLabel))
                                        .addComponent(addResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(deleteResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(editResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }

    /**
     * Handles the action when the "Add reservation" button is clicked.
     * @param evt The event received when the button is clicked.
     */
    private void addResButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new ReservationAddition(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Handles the action when the "Log out" button is clicked.
     * @param evt The event received when the button is clicked.
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
     * Handles the action when the "Clients" button is clicked.
     * @param evt The event received when the button is clicked.
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Clients(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Handles the action when the "Trips" button is clicked.
     * @param evt The event received when the button is clicked.
     */
    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Trips(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Handles the action when the "Panel" button is clicked.
     * @param evt The event received when the button is clicked.
     */
    private void panelButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Handles the action when the "Delete reservation" button is clicked.
     * @param evt The event received when the button is clicked.
     */
    private void deleteResButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        if(resTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "No reservation selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            data.clear();
            data.add("deleteRes");
            data.add(model.getValueAt(resTable.getSelectedRow(), 0).toString());
            new Client(data);
            data.clear();
            model.removeRow(resTable.getSelectedRow());
        }
    }

    /**
     * Fetches the appropriate data from the Client class.
     */
    private void generateData(){
        data.clear();
        data.add("resUpdate");
        Client client1 = new Client(data);
        resData.addAll(client1.getReturningData());
    }

    /**
     * Populates the table with reservations.
     */
    private void populateTable(){
        int counter = 0;
        int size = (resData.size()/6);
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{resData.get(counter), resData.get(counter+1), resData.get(counter+2), resData.get(counter+3),
                    resData.get(counter+4), resData.get(counter+5)});
            if(size > 1)
                counter+=6;
        }
    }

    /**
     * Allows searching for reservations by last name.
     */
    private void searchRes(){
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSorter = new TableRowSorter<>(resTable.getModel());
        resTable.setRowSorter(rowSorter);
        searchResTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performLastNameValidation();
                if (lastNameToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + lastNameToSearch, 2));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                performLastNameValidation();
                if (lastNameToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + lastNameToSearch));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                performLastNameValidation();
                throw new UnsupportedOperationException("Unsupported event!");
            }
        });
    }

    /**
     * Handles last name validation for reservation search.
     */
    private void performLastNameValidation() {
        lastNameToSearch = searchResTextField.getText();
        if(ClientValidator.isLastNameValid(lastNameToSearch))
            wrongResLabel.setText("");
        else
            wrongResLabel.setText("Check if the provided last name is correct.");
        if(lastNameToSearch.equals(""))
            wrongResLabel.setText("");
    }

    /**
     * Allows the window to be launched.
     * @param args Arguments received during application startup.
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
            java.util.logging.Logger.getLogger(Reservations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Reservations().setVisible(true));
    }

    /**
     * A label displaying the administrator's name.
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
     * A table displaying reservations.
     */
    private final javax.swing.JTable resTable = new JTable();

    /**
     * A button for navigating to the "Reservations" tab.
     */
    private final javax.swing.JButton reservationsButton = new JButton();

    /**
     * A field for entering the last name of the client when searching for reservations.
     */
    private final javax.swing.JTextField searchResTextField = new JTextField();

    /**
     * A button for navigating to the "Trips" tab.
     */
    private final javax.swing.JButton tripsButton = new JButton();

    /**
    * A label indicating that the last name entered for searching reservations is incorrect.
    */
    private final javax.swing.JLabel wrongResLabel = new JLabel();

    /**
     * A panel representing the menu panel used in the application.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * A panel representing the admin panel used in the application.
     */
    private final JPanel adminPanel = new JPanel();

    /**
     * A label representing the admin icon label used in the application.
     */
    private final JLabel adminIconLabel = new JLabel();

    /**
     * A label representing the admin label used in the application.
     */
    private final JLabel adminLabel = new JLabel();

    /**
     * A panel representing the options panel used in the application.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A panel representing the top panel used in the application.
     */
    private final JPanel topPanel = new JPanel();

    /**
     * A label representing the search result label used in the application.
     */
    private final JLabel searchResLabel = new JLabel();

    /**
     * A scrollPane representing the result table scroll pane used in the application.
     */
    private final JScrollPane resTableScrollPane = new JScrollPane();

    /**
     * A button representing the "Add Resource" button used in the application.
     */
    private final JButton addResButton = new JButton();

    /**
     * A button representing the "Delete Resource" button used in the application.
     */
    private final JButton deleteResButton = new JButton();

    /**
     * A button representing the "Edit Resource" button used in the application.
     */
    private final JButton editResButton = new JButton();
}