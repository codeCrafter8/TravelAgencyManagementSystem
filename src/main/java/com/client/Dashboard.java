package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.logs.LogsAdmins;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing fields and methods for handling an administrator panel window.
 */
class Dashboard extends javax.swing.JFrame {

    /**
     * A constant representing the light color used in the application.
     * The color is represented by RGB values (209, 197, 178).
     */
    private static final Color LIGHT = new Color(209, 197, 178);

    /**
     * A constant representing the dimension of a number panel used in the application.
     * The dimension is represented by width: 215 and height: 130.
     */
    private static final Dimension NUMBER_PANEL_DIMENSION = new Dimension(215, 130);

    /**
     * A constant representing the dimension of a caption used in the application.
     * The dimension is represented by width: 61 and height: 22.
     */
    private static final Dimension CAPTION_DIMENSION = new Dimension(61, 22);

    /**
     * A constant representing the dimension of a number label used in the application.
     * The dimension is represented by width: 48 and height: 57.
     */
    private static final Dimension NUMBER_LABEL_DIMENSION = new Dimension(48, 57);

    /**
     * An attribute holding the administrator's name.
     */
    public String adminName;

    /**
     * An attribute holding the number of clients.
     */
    public int clientsQuantity;

    /**
     * An attribute holding the number of trips.
     */
    public int tripsQuantity;

    /**
     * An attribute holding the number of reservations.
     */
    public int reservationsQuantity;

    /**
     * An attribute holding the number of income.
     */
    public int incomeQuantity;

    /**
     * An attribute representing a list holding phone numbers of clients for contact.
     */
    List<String> phoneNumbers = new ArrayList<>();

    /**
     * An attribute representing a list holding data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * An attribute representing an object of the Client class.
     */
    private Client client;

    /**
     * An attribute holding the user's email.
     */
    public String email;

    /**
     * A constructor responsible for initializing the GUI and relevant elements.
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
     * An auxiliary constructor responsible for initializing the GUI.
     */
    public Dashboard(){initComponents();}

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
        createTripsNumberPanel();
        createReservationsNumberPanel();
        createIncomeNumberPanel();
        createClientsNumberPanel();
        createPhoneNumbersList();
        createLayout();
    }

    /**
     * Sets various properties of the window, such as the default close operation, title, and background color.
     * Additionally, it adds a window listener to handle the window closing event, where it performs some cleanup actions
     * and logs any exceptions that may occur during the process.
     */
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

    /**
     * Creates the menu panel and sets its layout and background color.
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
                                .addContainerGap(318, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the admin panel and sets its layout and background color.
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

    /**
     * Sets the properties and text for various JLabel components.
     */
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\admin.png"));
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

    /**
     * Creates the options panel and sets its layout and background color.
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
     * Sets properties for various buttons, including their fonts, text, and appearance.
     * Additionally, it configures listeners for some buttons to handle rollover effects and actions.
     */
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

    /**
     * Creates the top panel and sets its layout and background color.
     * This panel is used to display the logout button.
     */
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

    /**
     * Creates the trips number panel and sets its layout and background color.
     * This panel displays the number of trips available.
     */
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

    /**
     * Creates the reservations number panel and sets its layout and background color.
     * This panel displays the number of reservations made.
     */
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

    /**
     * Creates the income number panel and sets its layout and background color.
     * This panel displays the income generated.
     */
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

    /**
     * Creates the clients number panel and sets its layout and background color.
     * This panel displays the number of clients in the system.
     */
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

    /**
     * Creates the phone numbers list and sets its background color and font.
     * This list displays the phone numbers of clients for contact purposes.
     */
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

    /**
     * Creates the overall layout of the window, including the placement of various panels and components.
     */
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
     * Handles the "Trips" button click.
     * @param evt The event received when the button is clicked.
     */
    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Trips(client, adminName).setVisible(true);
    }

    /**
     * Handles the "Reservations" button click.
     * @param evt The event received when the button is clicked.
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Reservations(client, adminName).setVisible(true);
    }
    
    /**
     * Fetches relevant data from the Client class.
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
     * Handles the "Log Out" button click.
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
     * Handles the "Clients" button click.
     * @param evt The event received when the button is clicked.
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Clients(client, adminName).setVisible(true);
    }
    
    /**
     * Allows the window to be launched.
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

    /**
     * A label displaying the administrator's name.
     */
    private final javax.swing.JLabel adminNameLabel = new JLabel();
    
    /**
     * A button for navigating to the "Clients" tab.
     */
    private final javax.swing.JButton clientsButton = new JButton();
    
    /**
     * A label displaying the number of clients.
     */
    private final javax.swing.JLabel clientsNumberLabel = new JLabel();
    
    /**
     * A list holding phone numbers of clients for contact.
     */
    private final javax.swing.JList<String> phoneNumbersList = new JList<>();
    
    /**
     * A label displaying the income number.
     */
    private final javax.swing.JLabel incomeNumberLabel = new JLabel();
    
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
     * A label displaying the number of reservations.
     */
    private final javax.swing.JLabel reservationsNumberLabel = new JLabel();
    
    /**
     * A button for navigating to the "Trips" tab.
     */
    private final javax.swing.JButton tripsButton = new JButton();
    
    /**
     * A label displaying the number of trips.
     */
    private final javax.swing.JLabel tripsNumberLabel = new JLabel();
    
    /**
     * A menu panel that contains the admin panel and options panel.
     * It is used as a container for the admin and options panels.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * An admin panel that displays the admin name and icon.
     * It is used to show information about the logged-in admin.
     */
    private final JPanel adminPanel = new JPanel();

    /**
     * A label that displays the icon of the admin.
     * It shows the icon associated with the logged-in admin.
     */
    private final JLabel adminIconLabel = new JLabel();

    /**
     * A label that displays the label "Admin".
     * It indicates that the displayed icon represents an admin.
     */
    private final JLabel adminLabel = new JLabel();

    /**
     * A panel that contains various options available to the admin.
     * It is used as a container for the buttons representing different options.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A top panel of the window that contains the logout button.
     * It is used to display the logout button for the admin to log out of the system.
     */
    private final JPanel topPanel = new JPanel();

    /**
     * A panel that displays the number of trips available.
     * It is used to show the total number of trips available in the system.
     */
    private final JPanel tripsNumberPanel = new JPanel();

    /**
     * A label that displays the caption "Wycieczek".
     * It indicates the type of data shown in the tripsNumberLabel.
     */
    private final JLabel tripsCaption = new JLabel();

    /**
     * A panel that displays the number of reservations made.
     * It is used to show the total number of reservations made in the system.
     */
    private final JPanel reservationsNumberPanel = new JPanel();

    /**
     * A label that displays the caption "Rezerwacji".
     * It indicates the type of data shown in the reservationsNumberLabel.
     */
    private final JLabel reservationsCaption = new JLabel();

    /**
     * A panel that displays the total income generated.
     * It is used to show the total income generated by the system.
     */
    private final JPanel incomeNumberPanel = new JPanel();

    /**
     * A label that displays the caption "Wpływów".
     * It indicates the type of data shown in the incomeNumberLabel.
     */
    private final JLabel incomeCaption = new JLabel();

    /**
     * The panel that displays the number of clients in the system.
     * It is used to show the total number of clients registered in the system.
     */
    private final JPanel clientsNumberPanel = new JPanel();

    /**
     * A label that displays the caption "Klientów".
     * It indicates the type of data shown in the clientsNumberLabel.
     */
    private final JLabel clientsCaption = new JLabel();

    /**
     * A label that displays the text "Numery telefonów klientów do kontaktu".
     * It indicates that the following list contains phone numbers of clients for contact purposes.
     */
    private final JLabel phoneNumbersLabel = new JLabel();

    /**
     * A scroll pane that contains the list of phone numbers of clients.
     * It is used to display the phone numbers of clients in a scrollable list.
     */
    private final JScrollPane phoneNumbersListScrollPane = new JScrollPane();
}