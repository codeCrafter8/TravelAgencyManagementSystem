package com.client;

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
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność wykonywania operacji dotyczących klientów
 */
public class Clients extends javax.swing.JFrame {
    /**
     * Etykieta z imieniem administratora
     */
    private javax.swing.JLabel adminNameLabel;
    /**
     * Przycisk umożliwiający przejście do zakładki Klienci
     */
    private javax.swing.JButton clientsButton;
    /**
     * Przycisk umożliwiający wylogowanie się
     */
    private javax.swing.JButton logOutButton;
    /**
     * Przycisk umożliwiający przejście do zakładki Panel
     */
    private javax.swing.JButton panelButton;
    /**
     * Przycisk umożliwiający przejście do zakładki Rezerwacje
     */
    private javax.swing.JButton reservationsButton;
    /**
     * Tabela z danymi wszystkich klientów
     */
    private javax.swing.JTable clientsTable;
    /**
     * Przycisk umożliwiający przejście do zakładki Wycieczki
     */
    private javax.swing.JButton tripsButton;
    /**
     * Pole do wprowadzenia emailu klienta przy wyszukiwaniu
     */
    private javax.swing.JTextField searchClientTextField;
    /**
     * Etykieta informująca, że email klienta przy wyszukiwaniu jest niepoprawny
     */
    private javax.swing.JLabel wrongEmailLabel;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane zwracane z klasy Client
     */
    List<String> clientsData = new ArrayList<>();
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut przechowujący email użytkownika
     */
    private String email;
    /**
     * Atrybut przechowujący imię administratora
     */
    private String adminName;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI
     */
    public Clients() {
        initComponents();
    }
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Klient
     * @param adminName parametr przechowujący imię administratora
     */
    public Clients(Client client, String adminName){
        this.client = client;
        this.email = client.getUserEmail();
        this.adminName = adminName;
        initComponents();
        generateData();
        populateTable();
        searchClients();
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
        adminNameLabel.setText(adminName);
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        JPanel topPanel = new JPanel();
        logOutButton = new javax.swing.JButton();
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
        JScrollPane jScrollPane1 = new JScrollPane();
        clientsTable = new javax.swing.JTable();
        searchClientTextField = new javax.swing.JTextField();
        JLabel searchClientLabel = new JLabel();
        JButton addClientButton = new JButton();
        JButton deleteClientButton = new JButton();
        JButton editClientButton = new JButton();
        wrongEmailLabel = new javax.swing.JLabel();
        JButton editPasswordButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Klienci");

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

        menuPanel.setBackground(new java.awt.Color(118, 98, 75));

        adminPanel.setBackground(new java.awt.Color(118, 98, 75));
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));

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
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
        optionsPanel.setBackground(new Color(118,98,75));

        panelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        panelButton.setText("    Panel");
        panelButton.setBorder(null);
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
        panelButton.addActionListener(this::panelButtonActionPerformed);

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        clientsButton.setText("   Klienci");
        clientsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
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
                .addContainerGap(316, Short.MAX_VALUE))
        );

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
        clientsTable.setMaximumSize(new java.awt.Dimension(375, 550));
        clientsTable.setMinimumSize(new java.awt.Dimension(375, 550));
        clientsTable.setPreferredSize(new java.awt.Dimension(375, 550));
        clientsTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        jScrollPane1.setViewportView(clientsTable);

        searchClientLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchClientLabel.setText("Wyszukaj klienta po e-mailu");

        addClientButton.setBackground(new java.awt.Color(241, 227, 185));
        addClientButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        addClientButton.setText("+ Dodaj Klienta");
        addClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addClientButton.addActionListener(this::addClientButtonActionPerformed);

        deleteClientButton.setBackground(new java.awt.Color(241, 227, 185));
        deleteClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        deleteClientButton.setText("Usuń Klienta");
        deleteClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteClientButton.addActionListener(this::deleteClientButtonActionPerformed);

        editClientButton.setBackground(new java.awt.Color(241, 227, 185));
        editClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editClientButton.setText("Edytuj Klienta");
        editClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editClientButton.addActionListener(this::editClientButtonActionPerformed);

        wrongEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongEmailLabel.setText("");
        wrongEmailLabel.setPreferredSize(new java.awt.Dimension(222, 16));

        editPasswordButton.setBackground(new java.awt.Color(241, 227, 185));
        editPasswordButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editPasswordButton.setText("Zmień hasło klienta");
        editPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editPasswordButton.addActionListener(this::editPasswordButtonActionPerformed);

        getContentPane().setBackground(new Color(215,198,151));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(151,123,92));
        for (int i = 0; i < clientsTable.getModel().getColumnCount(); i++) {
            clientsTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

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
     * Metoda obsługująca kliknięcie przycisku "Wycieczki"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Trips(client,adminNameLabel.getText()).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Rezerwacje"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Reservations(client,adminNameLabel.getText()).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Wyloguj się"
     * @param evt Przyjęty event podczas kliknięcia przycisku
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
     * Metoda obsługująca kliknięcie przycisku "Panel"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void panelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard(client,adminName).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Zmień hasło klienta"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void editPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        int row = clientsTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            int clientIDToChangePassword = Integer.parseInt(model.getValueAt(row, 0).toString());
            new ClientPasswordChange(clientIDToChangePassword).setVisible(true);
        }
    }
    /**
     * Metoda pobierająca odpowiednie dane z klasy Client
     */
    private void generateData(){
        data.clear();
        data.add("clientsUpdate");
        Client client1 =  new Client(data);
        clientsData.addAll(client1.getReturningData());
    }
    /**
     * Metoda wypełniająca tabelę przechowującą klientów
     */
    private void populateTable(){
        int counter = 0;
        int size = (clientsData.size()/5);
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{clientsData.get(counter), clientsData.get(counter+1), clientsData.get(counter+2), clientsData.get(counter+3),
                    clientsData.get(counter+4)});
            if(size > 1)
                counter+=5;
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji emailu wyszukiwanego klienta
     */
    private void performEmailValidation(){
        String emailFromTextField = searchClientTextField.getText();
        if(Validation.emailIsValid(emailFromTextField))
            wrongEmailLabel.setText("");
        else
            wrongEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
        if(searchClientTextField.getText().isEmpty())
            wrongEmailLabel.setText("");
    }
    /**
     * Metoda pozwalająca na wyszukiwanie klienta po emailu
     */
    private void searchClients(){
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
     * Metoda obsługująca kliknięcie przycisku "Usuń klienta"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void deleteClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        if(clientsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
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
     * Metoda obsługująca kliknięcie przycisku "Dodaj klienta"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void addClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Registration(true, client, adminNameLabel.getText()).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Edytuj klienta"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void editClientButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        if(clientsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            int id = Integer.parseInt(model.getValueAt(clientsTable.getSelectedRow(), 0).toString());
            String firstName = model.getValueAt(clientsTable.getSelectedRow(), 1).toString();
            String lastName = model.getValueAt(clientsTable.getSelectedRow(), 2).toString();
            email = model.getValueAt(clientsTable.getSelectedRow(), 3).toString();
            String phoneNumber = model.getValueAt(clientsTable.getSelectedRow(), 4).toString();
            if (firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("")) {
                JOptionPane.showMessageDialog(this, "Wprowadzono puste dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Clients().setVisible(true));
    }
}