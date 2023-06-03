package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność panelu administratora
 */
public class Dashboard extends javax.swing.JFrame {
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    public javax.swing.JLabel adminNameLabel;
    /**
     * Atrybut będący przyciskiem
     */
    private javax.swing.JButton clientsButton;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    public javax.swing.JLabel clientsNumberLabel;
    /**
     * Atrybut będący listą elementów tekstowych
     */
    public javax.swing.JList<String> jList1;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    public javax.swing.JLabel incomeNumberLabel;
    /**
     * Atrybut będący przyciskiem
     */
    private javax.swing.JButton logOutButton;
    /**
     * Atrybut będący przyciskiem
     */
    private javax.swing.JButton panelButton;
    /**
     * Atrybut będący przyciskiem
     */
    private javax.swing.JButton reservationsButton;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    public javax.swing.JLabel reservationsNumberLabel;
    /**
     * Atrybut będący przyciskiem
     */
    private javax.swing.JButton tripsButton;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    public javax.swing.JLabel tripsNumberLabel;
    /**
     * Atrybut przechowujący imię administratora
     */
    public String adminName;
    /**
     * Atrybut przechowujący liczbę klientów
     */
    public int clientsQuantity;
    /**
     * Atrybut przechowujący liczbę wycieczek
     */
    public int tripsQuantity;
    /**
     * Atrybut przechowujący liczbę rezerwacji
     */
    public int reservationsQuantity;
    /**
     * Atrybut przechowujący liczbę wpływów
     */
    public int incomeQuantity;
    /**
     * Atrybut będący listą przechowującą numery telefonów klientów do kontaktu
     */
    List<String> phoneNumbers = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane zwracane z klasy Client
     */
    List<String> returningData = new ArrayList<>();
    /**
     * Atrybut określający rozmiar listy przechowującej numery telefonów klientów do kontaktu
     */
    int phoneNumbersListLength;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut przechowujący email użytkownika
     */
    private String email;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich atrybutów
     * @param client parametr przechowujący obiekt klasy Klient
     * @param adminName parametr przechowujący imię administratora
     */
    public Dashboard(Client client, String adminName) {
        this.client = client;
        this.email = client.getUserEmail();
        this.adminName = adminName;
        initApp();
    }
    /**
     * Pomocniczy konstruktor
     * @param overrided określa czy wykorzystywany jest pomocniczy konstruktor
     */
    public Dashboard(boolean overrided){}
    /**
     * Metoda odpowiadająca za inicjalizację GUI oraz odpowiednich elementów
     */
    private void initApp(){
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        generateData();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.add(email);
                    new Client("logOut",data);
                    data.clear();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "Dashboard", "error");
                }
            }
        });
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        JPanel menuPanel = new JPanel();
        JPanel adminPanel = new JPanel();
        JLabel adminIconLabel = new JLabel();
        adminNameLabel = new javax.swing.JLabel();
        JLabel adminLabel = new JLabel();
        JPanel optionsPanel = new JPanel();
        panelButton = new javax.swing.JButton();
        clientsButton = new javax.swing.JButton();
        tripsButton = new javax.swing.JButton();
        reservationsButton = new javax.swing.JButton();
        JPanel topPanel = new JPanel();
        logOutButton = new javax.swing.JButton();
        JPanel tripsNumberPanel = new JPanel();
        JLabel tripsCaption = new JLabel();
        tripsNumberLabel = new javax.swing.JLabel();
        JPanel reservationsNumberPanel = new JPanel();
        JLabel reservationsCaption = new JLabel();
        reservationsNumberLabel = new JLabel();
        JPanel profitNumberPanel = new JPanel();
        incomeNumberLabel = new JLabel();
        JLabel incomeCaption = new JLabel();
        JPanel clientsNumberPanel = new JPanel();
        clientsNumberLabel = new javax.swing.JLabel();
        JLabel clientsCaption = new JLabel();
        JLabel jLabel1 = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Admina");

        menuPanel.setBackground(new java.awt.Color(118, 98, 75));

        adminPanel.setBackground(new java.awt.Color(118, 98, 75));
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));
        adminIconLabel.setText("jLabel1");
        adminIconLabel.setMaximumSize(new java.awt.Dimension(70, 70));
        adminIconLabel.setMinimumSize(new java.awt.Dimension(70, 70));
        adminIconLabel.setPreferredSize(new java.awt.Dimension(70, 70));

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(new java.awt.Dimension(73, 25));

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

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

        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
        optionsPanel.setBackground(new Color(118,98,75));

        panelButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        panelButton.setText("   Panel");
        panelButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        panelButton.setContentAreaFilled(false);
        panelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Color hoverColor = new Color(190, 190, 192);
        panelButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                panelButton.setForeground(hoverColor);
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
        clientsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        clientsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsButton.setForeground(hoverColor);
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
        tripsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        tripsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                tripsButton.setForeground(hoverColor);
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
        reservationsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        reservationsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                reservationsButton.setForeground(hoverColor);
                reservationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                reservationsButton.setForeground(null);
        });
        reservationsButton.addActionListener(this::reservationsButtonActionPerformed);

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

        topPanel.setBackground(new java.awt.Color(151, 123, 92));
        topPanel.setPreferredSize(new java.awt.Dimension(205, 34));

        logOutButton.setBackground(new java.awt.Color(242, 242, 242));
        logOutButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText("Wyloguj");
        logOutButton.setContentAreaFilled(false);
        logOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutButton.setPreferredSize(new java.awt.Dimension(74, 34));
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

        tripsNumberPanel.setBackground(new java.awt.Color(175, 157, 121));
        tripsNumberPanel.setPreferredSize(new java.awt.Dimension(215, 130));

        tripsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        tripsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tripsCaption.setText("Wycieczek");
        tripsCaption.setPreferredSize(new java.awt.Dimension(61, 22));

        tripsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        tripsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tripsNumberLabel.setPreferredSize(new java.awt.Dimension(48, 57));

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

        reservationsNumberPanel.setBackground(new java.awt.Color(175, 157, 121));
        reservationsNumberPanel.setPreferredSize(new java.awt.Dimension(215, 108));

        reservationsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        reservationsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reservationsCaption.setText("Rezerwacji");
        reservationsCaption.setMaximumSize(new java.awt.Dimension(61, 22));
        reservationsCaption.setMinimumSize(new java.awt.Dimension(61, 22));
        reservationsCaption.setPreferredSize(new java.awt.Dimension(61, 22));

        reservationsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        reservationsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reservationsNumberLabel.setPreferredSize(new java.awt.Dimension(48, 57));

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

        profitNumberPanel.setBackground(new java.awt.Color(175, 157, 121));
        profitNumberPanel.setPreferredSize(new java.awt.Dimension(280, 130));

        incomeNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        incomeNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incomeNumberLabel.setPreferredSize(new java.awt.Dimension(178, 57));

        incomeCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        incomeCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        incomeCaption.setText("Wpływów");

        javax.swing.GroupLayout profitNumberPanelLayout = new javax.swing.GroupLayout(profitNumberPanel);
        profitNumberPanel.setLayout(profitNumberPanelLayout);
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

        clientsNumberPanel.setBackground(new java.awt.Color(175, 157, 121));
        clientsNumberPanel.setPreferredSize(new java.awt.Dimension(215, 130));

        clientsNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        clientsNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientsNumberLabel.setPreferredSize(new java.awt.Dimension(48, 57));

        clientsCaption.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsCaption.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientsCaption.setText("Klientów");

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", Font.ITALIC, 18));
        jLabel1.setText("Numery telefonów klientów do kontaktu");

        jList1.setBackground(new java.awt.Color(209, 197, 178));
        jList1.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        jList1.setForeground(new java.awt.Color(0, 0, 0));
        jList1.setModel(new javax.swing.AbstractListModel<>() {
            final String[] strings = {};

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        jScrollPane1.setViewportView(jList1);

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
                                .addComponent(profitNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(278, 278, 278))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(245, 245, 245))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabel1)
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
                .addComponent(profitNumberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Wycieczki"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Trips(client, adminName).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Rezerwacje"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Reservations(client, adminName).setVisible(true);
    }
    /**
     * Metoda pobierająca odpowiednie dane z klasy Client
     */
    public void generateData(){
        data.add(email);
        Client client1 = new Client("dashboardUpdate", data);
        data.clear();

        adminName = client1.getDashboardAdminName();
        clientsQuantity = client1.getDashboardClientsQuantity();
        tripsQuantity = client1.getDashboardTripsQuantity();
        reservationsQuantity = client1.getDashboardReservationsQuantity();
        incomeQuantity = client1.getDashboardIncomeQuantity();

        adminNameLabel.setText(adminName);
        String clientsQuantityString = String.valueOf(clientsQuantity);
        clientsNumberLabel.setText(clientsQuantityString);
        String tripsQuantityString = String.valueOf(tripsQuantity);
        tripsNumberLabel.setText(tripsQuantityString);
        String reservationsQuantityString = String.valueOf(reservationsQuantity);
        reservationsNumberLabel.setText(reservationsQuantityString);
        String incomeQuantityString = incomeQuantity + " zł";
        incomeNumberLabel.setText(incomeQuantityString);

        Client client2 = new Client("getNumbers", new ArrayList<>());
        phoneNumbers.clear();
        phoneNumbers.addAll(client2.getDashboardPhoneNumbers());
        DefaultListModel<String> model = new DefaultListModel<>();
        for(String number : phoneNumbers)
            model.addElement(number);
        jList1.setModel(model);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Wyloguj się"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        data.add(email);
        new Client("logOut",data);
        data.clear();
        dispose();
        new StartPage().setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Klienci"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Clients(client, adminName).setVisible(true);
    }
    /**
     * Metoda pozwalająca na uruchomienie okna
     * @param args Argumenty przyjmowane podczas uruchamiania aplikacji
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
        java.awt.EventQueue.invokeLater(() -> new Dashboard(new Client(), "").setVisible(true));
    }
}