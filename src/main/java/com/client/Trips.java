package com.client;

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
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność wykonywania operacji dotyczących wycieczek
 */
public class Trips extends javax.swing.JFrame {
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane zwracane z klasy Client
     */
    List<String> tripsData = new ArrayList<>();
    /**
     * Element do sortowania tabeli z wycieczkami
     */
    private TableRowSorter<TableModel> rowSorter;
    /**
     * Atrybut przechowujący miasto przy wyszukiwaniu wycieczki
     */
    private String cityToSearch;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut przechowujący email użytkownika
     */
    private String email;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI
     */
    public Trips() {
        initComponents();
    }
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Klient
     * @param adminName parametr przechowujący imię administratora
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
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
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
    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Wycieczki");
        getContentPane().setBackground(ColorUtils.BEIGE);
        setMaximumSize(new java.awt.Dimension(1040, 770));
        setMinimumSize(new java.awt.Dimension(1040, 770));
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
    private void createMenuPanel(){
        menuPanel.setBackground(ColorUtils.BROWN);
        menuPanel.setPreferredSize(new java.awt.Dimension(180, 806));
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
    private void setLabels(){
        adminIconLabel.setIcon(new javax.swing.ImageIcon("img\\adminLOGO.png"));

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(new java.awt.Dimension(73, 25));

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        searchTripLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchTripLabel.setText("Wyszukaj wycieczkę po mieście");

        wrongTripLabel.setForeground(Color.red);
        wrongTripLabel.setPreferredSize(new java.awt.Dimension(222, 16));
    }
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
    private void createOptionsPanel(){
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
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
    private void createTopPanel(){
        topPanel.setBackground(ColorUtils.LIGHT_BROWN);
        topPanel.setPreferredSize(new java.awt.Dimension(205, 34));
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
        tripsTable.setMaximumSize(new java.awt.Dimension(375, 550));
        tripsTable.setMinimumSize(new java.awt.Dimension(375, 550));
        tripsTable.setPreferredSize(new java.awt.Dimension(375, 550));
        tripsTable.setSelectionBackground(ColorUtils.BEIGE);
        tripsTableScrollPane.setViewportView(tripsTable);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(ColorUtils.LIGHT_BROWN);
        for (int i = 0; i < tripsTable.getModel().getColumnCount(); i++) {
            tripsTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
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
        clientsButton.setPreferredSize(new java.awt.Dimension(75, 46));
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
        tripsButton.setPreferredSize(new java.awt.Dimension(75, 46));
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
        reservationsButton.setPreferredSize(new java.awt.Dimension(75, 46));
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
     * Metoda obsługująca kliknięcie przycisku "Edytuj wycieczkę"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void editTripButtonActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        if(tripsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej wycieczki.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            int id = Integer.parseInt(model.getValueAt(tripsTable.getSelectedRow(), 0).toString());
            String country = model.getValueAt(tripsTable.getSelectedRow(), 1).toString();
            String city = model.getValueAt(tripsTable.getSelectedRow(), 2).toString();
            String price = model.getValueAt(tripsTable.getSelectedRow(), 3).toString();
            String peopleLimit = model.getValueAt(tripsTable.getSelectedRow(), 4).toString();
            if (country.equals("") || city.equals("") || price.equals("") || peopleLimit.equals("")) {
                JOptionPane.showMessageDialog(this, "Wprowadzono puste dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
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
     * Metoda pobierająca odpowiednie dane z klasy Client
     */
    private void generateData(){
        data.clear();
        data.add("tripsUpdate");
        Client client1 = new Client(data);
        tripsData.addAll(client1.getReturningData());
    }
    /**
     * Metoda wypełniająca tabelę przechowującą wycieczki
     */
    private void populateTable(){
        int counter = 0;
        int size = (tripsData.size()/5);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter+1), tripsData.get(counter+2), tripsData.get(counter+3),
                    tripsData.get(counter+4)});
            if(size > 1)
                counter+=5;
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji miasta wyszukiwanej wycieczki
     */
    private void performCityValidation(){
        cityToSearch = searchClientTextField.getText();
        if(Validation.isCountryOrCityValid(cityToSearch))
            wrongTripLabel.setText("");
        else
            wrongTripLabel.setText("Sprawdź czy podane miasto jest poprawne.");
        if(cityToSearch.equals(""))
            wrongTripLabel.setText("");
    }
    /**
     * Metoda pozwalająca na wyszukiwanie wycieczki po mieście
     */
    private void searchTrips(){
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
     * Metoda obsługująca kliknięcie przycisku "Usuń wycieczkę"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void deleteTripButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        if(tripsTable.getSelectedRow() == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej wycieczki.", "Informacja", JOptionPane.ERROR_MESSAGE);
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
     * Metoda obsługująca kliknięcie przycisku "Dodaj wycieczkę"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void addTripButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new TripAddition(client,adminNameLabel.getText()).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Klienci"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Clients(client,adminNameLabel.getText()).setVisible(true);
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
     * Metoda obsługująca kliknięcie przycisku "Panel"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void panelButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard(client,adminNameLabel.getText()).setVisible(true);
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
            java.util.logging.Logger.getLogger(Trips.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Trips().setVisible(true));
    }
    //GUI variables
    private final JPanel menuPanel = new JPanel();
    private final JPanel adminPanel = new JPanel();
    private final JPanel optionsPanel = new JPanel();
    private final JLabel adminIconLabel = new JLabel();
    private final JLabel adminLabel = new JLabel();
    private final JPanel topPanel = new JPanel();
    private final JLabel searchTripLabel = new JLabel();
    private final JButton addTripButton = new JButton();
    private final JScrollPane tripsTableScrollPane = new JScrollPane();
    private final JButton deleteTripButton = new JButton();
    private final JButton editTripButton = new JButton();
    /**
     * Etykieta z imieniem administratora
     */
    private final javax.swing.JLabel adminNameLabel = new JLabel();
    /**
     * Przycisk umożliwiający przejście do zakładki Klienci
     */
    private final javax.swing.JButton clientsButton = new JButton();
    /**
     * Przycisk umożliwiający wylogowanie się
     */
    private final javax.swing.JButton logOutButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do zakładki Panel
     */
    private final javax.swing.JButton panelButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do zakładki Rezerwacje
     */
    private final javax.swing.JButton reservationsButton = new JButton();
    /**
     * Pole do wprowadzenia miasta przy wyszukiwaniu
     */
    private final javax.swing.JTextField searchClientTextField = new JTextField();
    /**
     * Przycisk umożliwiający przejście do zakładki Wycieczki
     */
    private final javax.swing.JButton tripsButton = new JButton();
    /**
     * Tabela z wycieczkami
     */
    private final javax.swing.JTable tripsTable = new JTable();
    /**
     * Etykieta informująca, że miasto przy wyszukiwaniu jest niepoprawne
     */
    private final javax.swing.JLabel wrongTripLabel = new JLabel();
}