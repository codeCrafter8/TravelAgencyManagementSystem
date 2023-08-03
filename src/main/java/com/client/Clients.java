package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.ClientValidator;
import com.server.LogsAdmins;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * Class containing fields and methods for handling a window with client-related operations.
 */
public class Clients extends javax.swing.JFrame {
    /**
     * Attribute representing a list holding data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Attribute representing a list holding data returned from the Client class.
     */
    List<String> clientsData = new ArrayList<>();
    /**
     * Attribute representing an object of the Client class.
     */
    private Client client;
    /**
     * Attribute holding the user's email.
     */
    private String email;
    /**
     * Attribute holding the administrator's name.
     */
    private String adminName;
    /**
     * Constructor responsible for initializing the GUI.
     */
    public Clients() {
        initComponents();
    }
    /**
     * Constructor responsible for initializing the GUI and relevant elements.
     * @param client The parameter holding an object of the Client class.
     * @param adminName The parameter holding the administrator's name.
     */
    public Clients(Client client, String adminName){
        this.client = client;
        this.email = client.getUserEmail();
        this.adminName = adminName;
        initComponents();
        generateData();
        populateTable();
        searchClients();
    }
    /**
     * Method to initialize graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createTopPanel();
        setButtons();
        setLabels();
        createMenuPanel();
        createAdminPanel();
        createOptionsPanel();
        createTable();
        createLayout();
    }

    /**
     * Sets the properties of the window, such as default close operation, title, background color, and window size.
     * Also adds a window listener to handle the window closing event, which logs out the client and disposes the window.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Klienci");
        getContentPane().setBackground(ColorUtils.BEIGE);
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
                    new LogsAdmins("Clients", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    /**
     * Creates the top panel, sets its background color, and initializes the buttons and labels on the panel.
     */
    private void createTopPanel(){
        topPanel.setBackground(ColorUtils.LIGHT_BROWN);
        topPanel.setPreferredSize(DimensionUtils.TOP_PANEL_DIMENSION);
        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(737, Short.MAX_VALUE)
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
     * Sets properties for the buttons such as background color, text, and action listeners.
     */
    private void setButtons(){
        logOutButton.setBackground(ColorUtils.MILK);
        logOutButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        logOutButton.setForeground(Color.WHITE);
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

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        clientsButton.setText("   Klienci");
        clientsButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
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

        tripsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        tripsButton.setText("Wycieczki");
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

        addClientButton.setBackground(ColorUtils.CREAM);
        addClientButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        addClientButton.setText("+ Dodaj Klienta");
        addClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addClientButton.addActionListener(this::addClientButtonActionPerformed);

        deleteClientButton.setBackground(ColorUtils.CREAM);
        deleteClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        deleteClientButton.setText("Usuń Klienta");
        deleteClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteClientButton.addActionListener(this::deleteClientButtonActionPerformed);

        editClientButton.setBackground(ColorUtils.CREAM);
        editClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editClientButton.setText("Edytuj Klienta");
        editClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editClientButton.addActionListener(this::editClientButtonActionPerformed);

        editPasswordButton.setBackground(ColorUtils.CREAM);
        editPasswordButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editPasswordButton.setText("Zmień hasło klienta");
        editPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editPasswordButton.addActionListener(this::editPasswordButtonActionPerformed);
    }

    /**
     * Creates the menu panel, sets its background color, and adds components such as adminPanel and optionsPanel to it.
     */
    private void createMenuPanel(){
        menuPanel.setBackground(ColorUtils.BROWN);
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(316, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the admin panel, sets its background color, and initializes the labels and icon on the panel.
     */
    private void createAdminPanel(){
        adminPanel.setBackground(ColorUtils.BROWN);
        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
                adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(adminNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(adminIconLabel)
                                .addContainerGap(55, Short.MAX_VALUE))
                        .addComponent(adminLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminPanelLayout.setVerticalGroup(
                adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(adminIconLabel)
                                .addGap(3, 3, 3)
                                .addComponent(adminNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(adminLabel)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
    }

    /**
     * Sets properties for the labels such as icon, font, and text.
     */
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));
        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(DimensionUtils.ADMIN_NAME_LABEL_DIMENSION);
        adminNameLabel.setText(adminName);
        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");
        searchClientLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchClientLabel.setText("Wyszukaj klienta po e-mailu");
        wrongEmailLabel.setForeground(Color.RED);
        wrongEmailLabel.setText("");
        wrongEmailLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Creates the options panel, sets its minimum and preferred sizes, and initializes the buttons on the panel.
     */
    private void createOptionsPanel(){
        optionsPanel.setMinimumSize(DimensionUtils.OPTIONS_PANEL_MIN_SIZE_DIMENSION);
        optionsPanel.setPreferredSize(DimensionUtils.OPTIONS_PANEL_PREF_SIZE_DIMENSION);
        optionsPanel.setBackground(ColorUtils.BROWN);
        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
                optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tripsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(reservationsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
                optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(optionsPanelLayout.createSequentialGroup()
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
     * Creates the clients table, sets its model, appearance, and header renderer.
     */
    private void createTable(){
        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] {
                        "Id", "Imię", "Nazwisko", "E-mail", "Nr telefonu"
                }
        )
        {public boolean isCellEditable(int row, int column){
            return column != 0;
        }});
        clientsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        clientsTable.setSelectionBackground(ColorUtils.BEIGE);
        jScrollPane1.setViewportView(clientsTable);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(ColorUtils.LIGHT_BROWN);
        for (int i = 0; i < clientsTable.getModel().getColumnCount(); i++) {
            clientsTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    /**
     * Creates the layout for the main window, arranging and positioning various components within it.
     */
    private void createLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(43, 43, 43))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(searchClientLabel)
                                                                        .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(addClientButton)
                                                                .addGap(68, 68, 68))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(295, 295, 295)
                                                                .addComponent(deleteClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(35, 35, 35)
                                                                .addComponent(editClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(352, 352, 352)
                                                                .addComponent(editPasswordButton)))
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(searchClientLabel)
                                .addGap(0, 0, 0)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(addClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(editClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method handling the "Trips" button click.
     * @param evt The event received when the button is clicked.
     */
    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Trips(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the "Reservations" button click.
     * @param evt The event received when the button is clicked.
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Reservations(client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the "Log Out" button click.
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
     * Method handling the "Dashboard" button click.
     * @param evt The event received when the button is clicked.
     */
    private void panelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard(client, adminName).setVisible(true);
    }

    /**
     * Method handling the "Edit Client Password" button click.
     * @param evt The event received when the button is clicked.
     */
    private void editPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        int row = clientsTable.getSelectedRow();
        if (row == -1)
            JOptionPane.showMessageDialog(null, "No client selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            int clientIDToChangePassword = Integer.parseInt(model.getValueAt(row, 0).toString());
            new ClientPasswordChange(clientIDToChangePassword).setVisible(true);
        }
    }

    /**
     * Method to retrieve relevant data from the Client class.
     */
    private void generateData() {
        data.clear();
        data.add("clientsUpdate");
        Client client1 = new Client(data);
        clientsData.addAll(client1.getReturningData());
    }

    /**
     * Method populating the table holding clients' data.
     */
    private void populateTable() {
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
     * Method handling email validation for searching clients.
     */
    private void performEmailValidation() {
        String emailFromTextField = searchClientTextField.getText();
        if (ClientValidator.isEmailValid(emailFromTextField))
            wrongEmailLabel.setText("");
        else
            wrongEmailLabel.setText("Please check if the provided email address is correct.");
        if (searchClientTextField.getText().isEmpty())
            wrongEmailLabel.setText("");
    }

    /**
     * Method enabling client search by email.
     */
    private void searchClients() {
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(clientsTable.getModel());
        clientsTable.setRowSorter(rowSorter);
        searchClientTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performEmailValidation();
                String text = searchClientTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + text, 3));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performEmailValidation();
                String text = searchClientTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performEmailValidation();
                throw new UnsupportedOperationException("Unsupported event!");
            }
        });
    }

    /**
     * Method handling the "Delete Client" button click.
     * @param evt The event received when the button is clicked.
     */
    private void deleteClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        if (clientsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "No client selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            data.clear();
            data.add("deleteClient");
            data.add(model.getValueAt(clientsTable.getSelectedRow(), 0).toString());
            new Client(data);
            data.clear();
            model.removeRow(clientsTable.getSelectedRow());
        }
    }

    /**
     * Method handling the "Add Client" button click.
     * @param evt The event received when the button is clicked.
     */
    private void addClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Registration(true, client, adminNameLabel.getText()).setVisible(true);
    }

    /**
     * Method handling the "Edit Client" button click.
     * @param evt The event received when the button is clicked.
     */
    private void editClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        if (clientsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "No client selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            int id = Integer.parseInt(model.getValueAt(clientsTable.getSelectedRow(), 0).toString());
            String firstName = model.getValueAt(clientsTable.getSelectedRow(), 1).toString();
            String lastName = model.getValueAt(clientsTable.getSelectedRow(), 2).toString();
            email = model.getValueAt(clientsTable.getSelectedRow(), 3).toString();
            String phoneNumber = model.getValueAt(clientsTable.getSelectedRow(), 4).toString();
            if (firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                data.clear();
                data.add("editClient");
                data.add(firstName);
                data.add(lastName);
                data.add(email);
                data.add(phoneNumber);
                data.add(Integer.toString(id));
                data.add(String.valueOf(client.getStartPageAdminLogged()));
                new Client(data);
                data.clear();
            }
        }
    }

    /**
     * Method allowing the window to be launched.
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
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Clients().setVisible(true));
    }

    /**
     * Label displaying the administrator's name.
     */
    private final javax.swing.JLabel adminNameLabel = new JLabel();
    /**
     * Button for navigating to the "Clients" tab.
     */
    private final javax.swing.JButton clientsButton = new JButton();
    /**
     * Button for logging out.
     */
    private final javax.swing.JButton logOutButton = new JButton();
    /**
     * Button for navigating to the "Dashboard" tab.
     */
    private final javax.swing.JButton panelButton = new JButton();
    /**
     * Button for navigating to the "Reservations" tab.
     */
    private final javax.swing.JButton reservationsButton = new JButton();
    /**
     * Table holding data for all clients.
     */
    private final javax.swing.JTable clientsTable = new JTable();
    /**
     * Button for navigating to the "Trips" tab.
     */
    private final javax.swing.JButton tripsButton = new JButton();
    /**
     * Text field for entering a client's email for searching.
     */
    private final javax.swing.JTextField searchClientTextField = new JTextField();
    /**
     * A label to display an error message if the provided email is invalid.
     */
    private final javax.swing.JLabel wrongEmailLabel = new JLabel();

    /**
     * A panel representing the top portion of the window, containing the administrator information.
     */
    private final JPanel topPanel = new JPanel();

    /**
     * A panel representing the menu portion of the window, containing navigation options.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * A panel representing the administrator information and icon in the menu panel.
     */
    private final JPanel adminPanel = new JPanel();

    /**
     * A label displaying the administrator icon.
     */
    private final JLabel adminIconLabel = new JLabel();

    /**
     * A label displaying the role of the administrator.
     */
    private final JLabel adminLabel = new JLabel();

    /**
     * A panel representing the options available in the menu panel.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A scroll pane to display a table of clients.
     */
    private final JScrollPane jScrollPane1 = new JScrollPane();

    /**
     * A label displaying text indicating a search operation for clients.
     */
    private final JLabel searchClientLabel = new JLabel();

    /**
     * A button to add a new client to the system.
     */
    private final JButton addClientButton = new JButton();

    /**
     * A button to delete an existing client from the system.
     */
    private final JButton deleteClientButton = new JButton();

    /**
     * A button to edit the information of an existing client.
     */
    private final JButton editClientButton = new JButton();

    /**
     * A button to edit the password of an existing client.
     */
    private final JButton editPasswordButton = new JButton();
}