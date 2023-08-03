package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.LogsAdmins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * Class containing fields and methods for handling an administrator panel window.
 */
class Dashboard extends javax.swing.JFrame {
    private static final Color LIGHT = new Color(209, 197, 178);
    private static final Dimension NUMBER_PANEL_DIMENSION = new Dimension(215, 130);
    private static final Dimension CAPTION_DIMENSION = new Dimension(61, 22);
    private static final Dimension NUMBER_LABEL_DIMENSION = new Dimension(48, 57);
    /**
     * Attribute holding the administrator's name.
     */
    public String adminName;
    /**
     * Attribute holding the number of clients.
     */
    public int clientsQuantity;
    /**
     * Attribute holding the number of trips.
     */
    public int tripsQuantity;
    /**
     * Attribute holding the number of reservations.
     */
    public int reservationsQuantity;
    /**
     * Attribute holding the number of income.
     */
    public int incomeQuantity;
    /**
     * Attribute representing a list holding phone numbers of clients for contact.
     */
    List<String> phoneNumbers = new ArrayList<>();
    /**
     * Attribute representing a list holding data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Attribute representing an object of the Client class.
     */
    private Client client;
    /**
     * Attribute holding the user's email.
     */
    public String email;
    /**
     * Constructor responsible for initializing the GUI and relevant elements.
     * @param client The parameter holding an object of the Client class.
     * @param adminName The parameter holding the administrator's name.
     */
    public Dashboard(Client client, String adminName) {
        this.client = client;
        this.email = client.getUserEmail();
        this.adminName = adminName;
        initComponents();
        generateData();
    }
    /**
     * Auxiliary constructor responsible for initializing the GUI.
     */
    public Dashboard(){initComponents();}
    /**
     * Method to initialize graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createAdminPanel();
        setLabels();
        createOptionsPanel();
        setButtons();
        createTopPanel();
        createTripsNumberPanel();
        createReservationsNumberPanel();
        createIncomeNumberPanel();
        createClientsNumberPanel();
        createPhoneNumbersList();
        createLayout();
    }

    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Admina");
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
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("Dashboard", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
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
                                .addContainerGap(318, Short.MAX_VALUE))
        );
    }
    private void createAdminPanel(){
        adminPanel.setBackground(ColorUtils.BROWN);
        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
                adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(adminNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(adminIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(55, Short.MAX_VALUE))
                        .addComponent(adminLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminPanelLayout.setVerticalGroup(
                adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(adminPanelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(adminIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(adminNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(adminLabel)
                                .addContainerGap(30, Short.MAX_VALUE))
        );
    }
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));
        adminIconLabel.setText("jLabel1");
        adminIconLabel.setMaximumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        adminIconLabel.setMinimumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        adminIconLabel.setPreferredSize(DimensionUtils.ICON_LABEL_DIMENSION);

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(DimensionUtils.ADMIN_NAME_LABEL_DIMENSION);

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        tripsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        tripsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tripsCaption.setText("Wycieczek");
        tripsCaption.setPreferredSize(CAPTION_DIMENSION);

        tripsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        tripsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tripsNumberLabel.setPreferredSize(NUMBER_LABEL_DIMENSION);

        reservationsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        reservationsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reservationsCaption.setText("Rezerwacji");
        reservationsCaption.setPreferredSize(CAPTION_DIMENSION);

        reservationsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        reservationsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reservationsNumberLabel.setPreferredSize(NUMBER_LABEL_DIMENSION);

        incomeNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        incomeNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incomeNumberLabel.setPreferredSize(DimensionUtils.NUMBER_LABEL_DIMENSION);

        incomeCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        incomeCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incomeCaption.setText("Wpływów");

        clientsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        clientsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientsNumberLabel.setPreferredSize(NUMBER_LABEL_DIMENSION);

        clientsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientsCaption.setText("Klientów");

        phoneNumbersLabel.setFont(new java.awt.Font("Segoe UI", Font.ITALIC, 18));
        phoneNumbersLabel.setText("Numery telefonów klientów do kontaktu");
    }
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
    private void setButtons(){
        panelButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        panelButton.setText("   Panel");
        panelButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
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

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsButton.setText("Klienci");
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
    }
    private void createTopPanel(){
        topPanel.setBackground(ColorUtils.LIGHT_BROWN);
        topPanel.setPreferredSize(DimensionUtils.TOP_PANEL_DIMENSION);
        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
                topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private void createTripsNumberPanel(){
        tripsNumberPanel.setBackground(ColorUtils.NUDE);
        tripsNumberPanel.setPreferredSize(NUMBER_PANEL_DIMENSION);
        javax.swing.GroupLayout tripsNumberPanelLayout = new javax.swing.GroupLayout(tripsNumberPanel);
        tripsNumberPanel.setLayout(tripsNumberPanelLayout);
        tripsNumberPanelLayout.setHorizontalGroup(
                tripsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tripsCaption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(tripsNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        tripsNumberPanelLayout.setVerticalGroup(
                tripsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tripsNumberPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tripsNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tripsCaption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
    }
    private void createReservationsNumberPanel(){
        reservationsNumberPanel.setBackground(ColorUtils.NUDE);
        reservationsNumberPanel.setPreferredSize(new java.awt.Dimension(215, 108));
        javax.swing.GroupLayout reservationsNumberPanelLayout = new javax.swing.GroupLayout(reservationsNumberPanel);
        reservationsNumberPanel.setLayout(reservationsNumberPanelLayout);
        reservationsNumberPanelLayout.setHorizontalGroup(
                reservationsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(reservationsCaption, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                        .addComponent(reservationsNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        reservationsNumberPanelLayout.setVerticalGroup(
                reservationsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(reservationsNumberPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reservationsNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reservationsCaption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
    }
    private void createIncomeNumberPanel(){
        incomeNumberPanel.setBackground(ColorUtils.NUDE);
        incomeNumberPanel.setPreferredSize(DimensionUtils.NUMBER_PANEL_DIMENSION);
        javax.swing.GroupLayout profitNumberPanelLayout = new javax.swing.GroupLayout(incomeNumberPanel);
        incomeNumberPanel.setLayout(profitNumberPanelLayout);
        profitNumberPanelLayout.setHorizontalGroup(
                profitNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(incomeNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                        .addComponent(incomeCaption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        profitNumberPanelLayout.setVerticalGroup(
                profitNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(profitNumberPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(incomeNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(incomeCaption)
                                .addContainerGap(17, Short.MAX_VALUE))
        );
    }
    private void createClientsNumberPanel(){
        clientsNumberPanel.setBackground(ColorUtils.NUDE);
        clientsNumberPanel.setPreferredSize(NUMBER_PANEL_DIMENSION);
        javax.swing.GroupLayout clientsNumberPanelLayout = new javax.swing.GroupLayout(clientsNumberPanel);
        clientsNumberPanel.setLayout(clientsNumberPanelLayout);
        clientsNumberPanelLayout.setHorizontalGroup(
                clientsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(clientsNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientsCaption, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
        );
        clientsNumberPanelLayout.setVerticalGroup(
                clientsNumberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(clientsNumberPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(clientsNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientsCaption)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void createPhoneNumbersList(){
        phoneNumbersList.setBackground(LIGHT);
        phoneNumbersList.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        phoneNumbersList.setForeground(Color.BLACK);
        phoneNumbersList.setModel(new javax.swing.AbstractListModel<>() {
            final String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        phoneNumbersListScrollPane.setViewportView(phoneNumbersList);
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(60, 60, 60)
                                                .addComponent(clientsNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                                                .addComponent(tripsNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(38, 38, 38)
                                                .addComponent(reservationsNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(60, 60, 60))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(incomeNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(278, 278, 278))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(phoneNumbersListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(245, 245, 245))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(263, 263, 263)
                                                .addComponent(phoneNumbersLabel)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(reservationsNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(tripsNumberPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                                .addComponent(clientsNumberPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)))
                                .addGap(35, 35, 35)
                                .addComponent(incomeNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61)
                                .addComponent(phoneNumbersLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(phoneNumbersListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        new Trips(client, adminName).setVisible(true);
    }
    /**
     * Method handling the "Reservations" button click.
     * @param evt The event received when the button is clicked.
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Reservations(client, adminName).setVisible(true);
    }
    /**
     * Method fetching relevant data from the Client class.
     */
    public void generateData() {
        data.clear();
        data.add("dashboardUpdate");
        data.add(email);
        Client client1 = new Client(data);
        data.clear();

        List<String> returningData = client1.getReturningData();
        adminName = returningData.get(0);
        clientsQuantity = Integer.parseInt(returningData.get(1));
        tripsQuantity = Integer.parseInt(returningData.get(2));
        reservationsQuantity = Integer.parseInt(returningData.get(3));
        incomeQuantity = Integer.parseInt(returningData.get(4));

        adminNameLabel.setText(adminName);
        String clientsQuantityString = String.valueOf(clientsQuantity);
        clientsNumberLabel.setText(clientsQuantityString);
        String tripsQuantityString = String.valueOf(tripsQuantity);
        tripsNumberLabel.setText(tripsQuantityString);
        String reservationsQuantityString = String.valueOf(reservationsQuantity);
        reservationsNumberLabel.setText(reservationsQuantityString);
        String incomeQuantityString = incomeQuantity + " zł";
        incomeNumberLabel.setText(incomeQuantityString);

        data.clear();
        data.add("getNumbers");
        Client client2 = new Client(data);
        phoneNumbers.clear();
        phoneNumbers.addAll(client2.getReturningData());
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String number : phoneNumbers)
            model.addElement(number);
        phoneNumbersList.setModel(model);
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
     * Method handling the "Clients" button click.
     * @param evt The event received when the button is clicked.
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Clients(client, adminName).setVisible(true);
    }
    /**
     * Method allowing the window to be launched.
     * @param args Arguments received when launching the application.
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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }
    // GUI variables
    /**
     * Label displaying the administrator's name.
     */
    private final javax.swing.JLabel adminNameLabel = new JLabel();
    /**
     * Button for navigating to the "Clients" tab.
     */
    private final javax.swing.JButton clientsButton = new JButton();
    /**
     * Label displaying the number of clients.
     */
    private final javax.swing.JLabel clientsNumberLabel = new JLabel();
    /**
     * List holding phone numbers of clients for contact.
     */
    private final javax.swing.JList<String> phoneNumbersList = new JList<>();
    /**
     * Label displaying the income number.
     */
    private final javax.swing.JLabel incomeNumberLabel = new JLabel();
    /**
     * Button for logging out.
     */
    private final javax.swing.JButton logOutButton = new JButton();
    /**
     * Button for navigating to the "Panel" tab.
     */
    private final javax.swing.JButton panelButton = new JButton();
    /**
     * Button for navigating to the "Reservations" tab.
     */
    private final javax.swing.JButton reservationsButton = new JButton();
    /**
     * Label displaying the number of reservations.
     */
    private final javax.swing.JLabel reservationsNumberLabel = new JLabel();
    /**
     * Button for navigating to the "Trips" tab.
     */
    private final javax.swing.JButton tripsButton = new JButton();
    /**
     * Label displaying the number of trips.
     */
    private final javax.swing.JLabel tripsNumberLabel = new JLabel();
    private final JPanel menuPanel = new JPanel();
    private final JPanel adminPanel = new JPanel();
    private final JLabel adminIconLabel = new JLabel();
    private final JLabel adminLabel = new JLabel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel topPanel = new JPanel();
    private final JPanel tripsNumberPanel = new JPanel();
    private final JLabel tripsCaption = new JLabel();
    private final JPanel reservationsNumberPanel = new JPanel();
    private final JLabel reservationsCaption = new JLabel();
    private final JPanel incomeNumberPanel = new JPanel();
    private final JLabel incomeCaption = new JLabel();
    private final JPanel clientsNumberPanel = new JPanel();
    private final JLabel clientsCaption = new JLabel();
    private final JLabel phoneNumbersLabel = new JLabel();
    private final JScrollPane phoneNumbersListScrollPane = new JScrollPane();
}