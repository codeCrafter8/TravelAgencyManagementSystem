package com.client;

import com.server.LogsClients;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność strony głównej klienta wraz z wyszukiwarką
 */
public class SearchEngine extends javax.swing.JFrame {
    /**
     * Atrybut będący licznikiem wykorzystywanym przy pokazie slajdow
     */
    private int counter = 1;
    /**
     * Atrybut będący ilością danych w liście z dostępnymi wycieczkami
     */
    private final int attributesQuantity = 10;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą z danymi wycieczek
     */
    List<String> tripsData = new ArrayList<>();
    /**
     * Atrybut będący listą z numerami telefonów klientów do kontaktu
     */
    List<String> phoneNumberData = new ArrayList<>();
    /**
     * Atrybut będący listą z kierunkami podróży
     */
    List<String> destination = new ArrayList<>();
    /**
     * Atrybut będący listą z miejscami wylotu
     */
    List<String> departure = new ArrayList<>();
    /**
     * Atrybut będący numerem zaznaczonego wiersza w tabeli z wycieczkami
     */
    private int selectedRow;
    /**
     * Atrybut określający ilość dorosłych
     */
    private int adultsQuantity = 1;
    /**
     * Atrybut określający ilość dzieci
     */
    private int childrenQuantity = 0;
    /**
     * Mapa przechowująca numer wiersza jako klucz i id wycieczki jako wartość
     */
    private final Map<Integer, Integer> idRows = new TreeMap<>();
    /**
     * Atrybut określający id zaznaczonej wycieczki
     */
    private int idSelectedTrip;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut określający email klienta
     */
    private String email;
    /**
     * Pomocniczy konstruktor odpowiadający za inicjalizację GUI
     */
    SearchEngine() {
        initComponents();
    }
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Klient
     */
    SearchEngine(Client client){
        this.client = client;
        email = client.getUserEmail();
        initComponents();
        showPhotos();
        generateData();
        populateTable();
    }
    /**
     * Metoda pobierająca kierunki podróży z klasy Client a następnie dodająca je do GUI
     */
    private void getDestination() {
        data.clear();
        data.add("getDestination");
        destination.clear();
        Client client1 = new Client(data);
        destination.addAll(client1.getReturningData());
        destinationChoiceComboBox.removeAllItems();
        for(String s : destination)
            destinationChoiceComboBox.addItem(s);
    }
    /**
     * Metoda pobierająca miejsca wylotu z klasy Client a następnie dodająca je do GUI
     */
    private void getDeparture() {
        data.clear();
        data.add("getDeparture");
        departure.clear();
        Client client1 = new Client(data);
        departure.addAll(client1.getReturningData());
        departureCityChoice.removeAllItems();
        for(String s : departure)
            departureCityChoice.addItem(s);
    }

    private void generateData() {
        data.clear();
        data.add("tripsListPopulation");
        Client client2 = new Client(data);
        tripsData.addAll(client2.getReturningData());
        getDestination();
        getDeparture();
    }
    /**
     * Metoda wypełniająca tabelę z dostępnymi wycieczkami
     */
    private void populateTable(){
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter+1), (tripsData.get(counter+2) + " - " + tripsData.get(counter+3)),
                    tripsData.get(counter+4) + " zł"});
            idRows.put(i, Integer.parseInt(tripsData.get(counter+7)));
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda wyświetlająca pokaz slajdów
     */
    private void showPhotos() {
        imagesLabel.setIcon(new javax.swing.ImageIcon("img\\photo1.jpg"));
        Timer time = new Timer(3000, e -> {
            imagesLabel.setIcon(new ImageIcon("img\\photo" + counter + ".jpg"));
            if (counter == 5) counter = 0;
            counter++;
        });
        time.start();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents(){
        setWindowProperties();
        setButtons();
        setLabels();
        setComboBoxes();
        setTextFields();
        setTable();
        setManaging();
        setSpinners();
        createCountriesPanel();
        createSearchPanelIntroduction();
        createSearchPanel();
        createMainPanel();
        createMainWindow();
        createFooter();
        createLayout();
    }
    /**
     * Metoda obsługująca menu podręczne
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void managingActionPerformed(ActionEvent evt) {
        switch(String.valueOf(managingComboBox.getSelectedItem())){
            case "Wyloguj" -> {
                Object[] options = {"Tak", "Nie"};
                if(JOptionPane.showOptionDialog(null,"Czy na pewno chcesz się wylogować?","Potwierdzenie",
                        JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, null)== JOptionPane.YES_OPTION){
                    dispose();
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                    new StartPage().setVisible(true);
                }
            }
            case "Moje Konto" -> {
                dispose();
                new MyAccount(client).setVisible(true);
            }
        }
    }
    private void setButtons(){
        tripsButton.setBackground(new java.awt.Color(151, 123, 92));
        tripsButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        tripsButton.setForeground(new java.awt.Color(255, 255, 255));
        tripsButton.setText("Wczasy");
        tripsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsButton.setFocusPainted(false);
        tripsButton.setFocusable(false);
        tripsButton.addActionListener(this::tripsButtonActionPerformed);

        summer2023Button.setBackground(new java.awt.Color(151, 123, 92));
        summer2023Button.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        summer2023Button.setForeground(new java.awt.Color(255, 255, 255));
        summer2023Button.setText("Lato 2023");
        summer2023Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        summer2023Button.setFocusable(false);
        summer2023Button.addActionListener(this::summer2023ButtonActionPerformed);

        lastMinuteButton.setBackground(new java.awt.Color(151, 123, 92));
        lastMinuteButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        lastMinuteButton.setForeground(new java.awt.Color(255, 255, 255));
        lastMinuteButton.setText("Last Minute");
        lastMinuteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastMinuteButton.setFocusable(false);
        lastMinuteButton.addActionListener(this::lastMinuteButtonActionPerformed);

        exoticButton.setBackground(new java.awt.Color(151, 123, 92));
        exoticButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        exoticButton.setForeground(new java.awt.Color(255, 255, 255));
        exoticButton.setText("Egzotyka");
        exoticButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exoticButton.setFocusable(false);
        exoticButton.addActionListener(this::exoticButtonActionPerformed);

        greeceButton.setBackground(new java.awt.Color(151, 123, 92));
        greeceButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        greeceButton.setForeground(new java.awt.Color(255, 255, 255));
        greeceButton.setText("Grecja");
        greeceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        greeceButton.setFocusable(false);
        greeceButton.addActionListener(this::greeceButtonActionPerformed);

        spainButton.setBackground(new java.awt.Color(151, 123, 92));
        spainButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        spainButton.setForeground(new java.awt.Color(255, 255, 255));
        spainButton.setText("Hiszpania");
        spainButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spainButton.setFocusable(false);
        spainButton.addActionListener(this::spainButtonActionPerformed);

        turkeyButton.setBackground(new java.awt.Color(151, 123, 92));
        turkeyButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        turkeyButton.setForeground(new java.awt.Color(255, 255, 255));
        turkeyButton.setText("Turcja");
        turkeyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        turkeyButton.setFocusable(false);
        turkeyButton.addActionListener(this::turkeyButtonActionPerformed);

        egyptButton.setBackground(new java.awt.Color(151, 123, 92));
        egyptButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        egyptButton.setForeground(new java.awt.Color(255, 255, 255));
        egyptButton.setText("Egipt");
        egyptButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        egyptButton.setFocusable(false);
        egyptButton.addActionListener(this::egyptButtonActionPerformed);

        italyButton.setBackground(new java.awt.Color(151, 123, 92));
        italyButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        italyButton.setForeground(new java.awt.Color(255, 255, 255));
        italyButton.setText("Włochy");
        italyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        italyButton.setFocusable(false);
        italyButton.addActionListener(this::italyButtonActionPerformed);

        bulgariaButton.setBackground(new java.awt.Color(151, 123, 92));
        bulgariaButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        bulgariaButton.setForeground(new java.awt.Color(255, 255, 255));
        bulgariaButton.setText("Bułgaria");
        bulgariaButton.setFocusable(false);
        bulgariaButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bulgariaButton.addActionListener(this::bulgariaButtonActionPerformed);

        searchButton.setIcon(new javax.swing.ImageIcon("img\\search-icon-png-0.png"));
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(this::searchButtonActionPerformed);

        sendButton.setForeground(new java.awt.Color(151, 123, 92));
        sendButton.setText("Wyślij");
        sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendButton.setFocusable(false);
        sendButton.addActionListener(this::sendButtonActionPerformed);
    }
    private void setLabels(){
        agencyNameLabel.setBackground(new java.awt.Color(151, 123, 92));
        agencyNameLabel.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 24));
        agencyNameLabel.setForeground(new java.awt.Color(151, 123, 92));
        agencyNameLabel.setText("Travel Agency");

        tripDirectionLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        tripDirectionLabel.setForeground(new java.awt.Color(255, 255, 255));
        tripDirectionLabel.setText("Kierunek podróży");

        departureCityLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        departureCityLabel.setForeground(new java.awt.Color(255, 255, 255));
        departureCityLabel.setText("Miejsce wylotu");

        departureArrivalLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        departureArrivalLabel.setForeground(new java.awt.Color(255, 255, 255));
        departureArrivalLabel.setText("Wyjazd/Przyjazd");

        peopleQuantityLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        peopleQuantityLabel.setForeground(new java.awt.Color(255, 255, 255));
        peopleQuantityLabel.setText("Ilość dorosłych/dzieci");
    }
    private void createCountriesPanel(){
        javax.swing.GroupLayout countriesPanelLayout = new javax.swing.GroupLayout(countriesPanel);
        countriesPanel.setLayout(countriesPanelLayout);
        countriesPanelLayout.setHorizontalGroup(
                countriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(countriesPanelLayout.createSequentialGroup()
                                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(summer2023Button, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lastMinuteButton)
                                .addGap(0, 0, 0)
                                .addComponent(exoticButton)
                                .addGap(0, 0, 0)
                                .addComponent(greeceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(spainButton)
                                .addGap(0, 0, 0)
                                .addComponent(turkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(egyptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(italyButton)
                                .addGap(0, 0, 0)
                                .addComponent(bulgariaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                .addGap(0, 0, 0))
        );
        countriesPanelLayout.setVerticalGroup(
                countriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(countriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(summer2023Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lastMinuteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(exoticButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(greeceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(spainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(turkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(egyptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(italyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bulgariaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));
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
                    new LogsClients("SearchEngine", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
    private void createMainWindow(){
        mainScroll.setBorder(null);
        mainScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScroll.setMaximumSize(new java.awt.Dimension(1022, 400));
        mainScroll.setMinimumSize(new java.awt.Dimension(1022, 400));
        mainScroll.setPreferredSize(new java.awt.Dimension(1022, 729));

        mainWindow.setMaximumSize(new java.awt.Dimension(1022, 729));
        mainWindow.setMinimumSize(new java.awt.Dimension(1022, 729));
        mainWindow.setPreferredSize(new java.awt.Dimension(1022, 720));
        javax.swing.GroupLayout mainWindowLayout = new javax.swing.GroupLayout(mainWindow);
        mainWindow.setLayout(mainWindowLayout);
        mainWindowLayout.setHorizontalGroup(
                mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainWindowLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(45, Short.MAX_VALUE))
        );
        mainWindowLayout.setVerticalGroup(
                mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        mainScroll.setViewportView(mainWindow);
    }
    private void createSearchPanelIntroduction(){
        searchPanelIntroduction.setBackground(new java.awt.Color(151, 123, 92));
        javax.swing.GroupLayout searchPanelIntroductionLayout = new javax.swing.GroupLayout(searchPanelIntroduction);
        searchPanelIntroduction.setLayout(searchPanelIntroductionLayout);
        searchPanelIntroductionLayout.setHorizontalGroup(
                searchPanelIntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelIntroductionLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(searchPanelIntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(destinationChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(departureCityChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(searchPanelIntroductionLayout.createSequentialGroup()
                                                .addComponent(departureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(arrivalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(searchPanelIntroductionLayout.createSequentialGroup()
                                                .addComponent(adultsQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(childrenQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        searchPanelIntroductionLayout.setVerticalGroup(
                searchPanelIntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelIntroductionLayout.createSequentialGroup()
                                .addComponent(destinationChoiceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(departureCityChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(searchPanelIntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(arrivalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(departureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(searchPanelIntroductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(adultsQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(childrenQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }
    private void createMainPanel(){
        mainPanel.setPreferredSize(new java.awt.Dimension(955, 2000));
        javax.swing.GroupLayout glowneLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(glowneLayout);
        glowneLayout.setHorizontalGroup(
                glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(countriesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(glowneLayout.createSequentialGroup()
                                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(imagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glowneLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(agencyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(255, 255, 255)
                                .addComponent(managingComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tableScroll)
        );
        glowneLayout.setVerticalGroup(
                glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(glowneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(agencyNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(managingComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(countriesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(imagesLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tableScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
        );
    }
    private void createSearchPanel(){
        searchPanel.setBackground(new java.awt.Color(151, 123, 92));
        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
                searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tripDirectionLabel)
                                        .addComponent(departureArrivalLabel)
                                        .addComponent(departureCityLabel)
                                        .addComponent(peopleQuantityLabel))
                                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(searchPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(searchPanelIntroduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(15, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(searchButton)
                                                .addGap(29, 29, 29))))
        );
        searchPanelLayout.setVerticalGroup(
                searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(searchPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(searchPanelLayout.createSequentialGroup()
                                                .addComponent(searchPanelIntroduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchButton))
                                        .addGroup(searchPanelLayout.createSequentialGroup()
                                                .addComponent(tripDirectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(departureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(12, 12, 12)
                                                .addComponent(departureArrivalLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(peopleQuantityLabel)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void setComboBoxes(){
        destinationChoiceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dokąd?"}));
        destinationChoiceComboBox.setFocusable(false);
        departureCityChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Skąd?"}));
        departureCityChoice.setFocusable(false);
    }
    private void setTextFields(){
        departureTextField.setForeground(new java.awt.Color(153, 153, 153));
        departureTextField.setText("04/07/2023");
        departureTextField.setToolTipText("");
        departureTextField.setAutoscrolls(false);
        departureTextField.setSelectionColor(new java.awt.Color(255, 255, 255));
        arrivalTextField.setForeground(new java.awt.Color(153, 153, 153));
        arrivalTextField.setText("11/07/2023");
        arrivalTextField.setAutoscrolls(false);
        leaveNumberTextField.setForeground(new java.awt.Color(153, 153, 153));
        leaveNumberTextField.setText("Zostaw nr tel. - oddzwonimy do ciebie");
        leaveNumberTextField.setMinimumSize(new java.awt.Dimension(64, 27));
        leaveNumberTextField.setPreferredSize(new java.awt.Dimension(64, 22));
        leaveNumberTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (leaveNumberTextField.getText().equals("Zostaw nr tel. - oddzwonimy do ciebie")) {
                    leaveNumberTextField.setText("");
                    leaveNumberTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (leaveNumberTextField.getText().isEmpty()) {
                    leaveNumberTextField.setForeground(Color.GRAY);
                    leaveNumberTextField.setText("Zostaw nr tel. - oddzwonimy do ciebie");
                }
            }
        });
        departureTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (departureTextField.getText().equals("04/07/2023")) {
                    departureTextField.setText("");
                    departureTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (departureTextField.getText().isEmpty()) {
                    departureTextField.setForeground(Color.GRAY);
                    departureTextField.setText("04/07/2023");
                }
            }
        });
        arrivalTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (arrivalTextField.getText().equals("11/07/2023")) {
                    arrivalTextField.setText("");
                    arrivalTextField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (arrivalTextField.getText().isEmpty()) {
                    arrivalTextField.setForeground(Color.GRAY);
                    arrivalTextField.setText("11/07/2023");
                }
            }
        });
    }
    private void setSpinners(){
        adultsQuantitySpinner.setFocusable(false);
        childrenQuantitySpinner.setFocusable(false);
    }
    private void setTable(){
        tableScroll.setBorder(null);
        tableScroll.setToolTipText("");
        tableScroll.setColumnHeaderView(null);

        tripsTable.setAutoCreateRowSorter(true);
        tripsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Kraj", "Miasto", "Termin", "Cena/Osoba"
                }
        ));
        tripsTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tripsTable.setFocusable(false);
        tripsTable.setGridColor(new java.awt.Color(255, 255, 255));
        tripsTable.setPreferredSize(new java.awt.Dimension(300, 355));
        tripsTable.setSelectionBackground(new java.awt.Color(151, 123, 92));
        tripsTable.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tripsTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tripsTable.setShowGrid(true);
        tripsTable.getTableHeader().setResizingAllowed(false);
        tableScroll.setViewportView(tripsTable);
        tripsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedRow = tripsTable.rowAtPoint(evt.getPoint());
                if (selectedRow >= 0) {
                    idSelectedTrip = idRows.get(selectedRow);
                    dispose();
                    new Offer(client, tripsData,attributesQuantity, adultsQuantity, childrenQuantity, idSelectedTrip, selectedRow).setVisible(true);
                }
            }
        });
    }
    private void setManaging(){
        managingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Strona Główna", "Moje Konto", "Wyloguj" }));
        managingComboBox.setBorder(null);
        managingComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managingComboBox.setFocusable(false);
        managingComboBox.setLightWeightPopupEnabled(false);
        managingComboBox.addActionListener(this::managingActionPerformed);
    }
    private void createFooter(){
        footer.setBackground(new java.awt.Color(151, 123, 92));
        headphonesIconLabel.setIcon(new javax.swing.ImageIcon("img\\slucahwki.png"));

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(footerLayout.createSequentialGroup()
                                .addGap(349, 349, 349)
                                .addComponent(headphonesIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(leaveNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sendButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
                footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(leaveNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(footerLayout.createSequentialGroup()
                                                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(sendButton)
                                                        .addComponent(headphonesIconLabel))
                                                .addGap(0, 2, Short.MAX_VALUE)))
                                .addGap(8, 8, 8))
        );
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(mainScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Lato 2023"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void summer2023ButtonActionPerformed(ActionEvent evt) {
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start = "2023-07-01";
        String end = "2023-08-31";
        Date date1 = null;
        Date date2 = null;
        Date startDate = null;
        Date endDate = null;
        int rowCounter = 0;
        try{
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0; i<size; i++){
            try {
                date1 = formatter.parse(String.valueOf(tripsData.get(counter+2)));
                date2 = formatter.parse(String.valueOf(tripsData.get(counter+3)));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            if(date1.compareTo(startDate) >= 0) {
                assert date2 != null;
                if (date2.compareTo(endDate) <= 0) {
                    model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)), tripsData.get(counter + 4) + " zł"});
                    idRows.put(rowCounter, Integer.parseInt(tripsData.get(counter + 7)));
                    rowCounter++;
                }
            }
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Wyślij"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String number = leaveNumberTextField.getText();
        if(Validation.isPhoneNumberValid(number)){
            leaveNumberTextField.setText("");
            phoneNumberData.clear();
            phoneNumberData.add("sendNumbers");
            phoneNumberData.add(number);
            new Client(phoneNumberData);
            phoneNumberData.clear();
        }
        else {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisany numer telefonu.", "Informacja", JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Bułgaria"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void bulgariaButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Bulgaria");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Włochy"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void italyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Wlochy");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Egipt"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void egyptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Egipt");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Turcja"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void turkeyButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Turcja");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Hiszpania"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void spainButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Hiszpania");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Grecja"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void greeceButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Grecja");
    }
    /**
     * Metoda filtrująca tabelę z dostępnymi wycieczkami pod kątem wybranego kraju
     * @param country wybrany kraj
     */
    private void filterTable(String country){
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            if(tripsData.get(counter).equals(country)) {
                model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)),
                        tripsData.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(tripsData.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda filtrująca tabelę z dostępnymi wycieczkami pod kątem wybranej opcji
     * @param country1 pierwszy kraj
     * @param country2 drugi kraj
     */
    private void filterTable(String country1, String country2){
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            if(tripsData.get(counter).equals(country1) || tripsData.get(counter).equals(country2)) {
                model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)),
                        tripsData.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(tripsData.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Egzotyka"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void exoticButtonActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Kuba", "Emiraty Arabskie");
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Last Minute"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void lastMinuteButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date now = new Date();
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            try {
                date1 = formatter.parse(String.valueOf(tripsData.get(counter+2)));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            if(getDifferenceDays(now, date1) < 5) {
                model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)),
                        tripsData.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(tripsData.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda odpowiadająca za obliczenie różnicy dni pomiędzy datami
     * @param d1 pierwsza data
     * @param d2 druga data
     * @return liczba dni różnicy
     */
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Wczasy"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void tripsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        populateTable();
    }
    /**
     * Metoda obsługująca kliknięcie przycisku lupy
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean isDateValid = performDateValidation();
        if(isDateValid) {
            adultsQuantity = (int) adultsQuantitySpinner.getValue();
            childrenQuantity = (int) childrenQuantitySpinner.getValue();
            String country = (String) destinationChoiceComboBox.getSelectedItem();
            String departure_city = (String) departureCityChoice.getSelectedItem();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatterSearch = new SimpleDateFormat("dd/MM/yyyy");
            Date departure = null;
            Date arrival = null;
            Date date1 = null;
            Date date2 = null;

            int counter = 0;
            int size = (tripsData.size()/10);
            DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
            model.setRowCount(0);
            idRows.clear();
            int rowCounter = 0;
            for (int i = 0; i < size; i++) {
                try {
                    date1 = formatter.parse(String.valueOf(tripsData.get(counter + 2)));
                    date2 = formatter.parse(String.valueOf(tripsData.get(counter + 3)));
                    departure = formatterSearch.parse(departureTextField.getText());
                    arrival = formatterSearch.parse(this.arrivalTextField.getText());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (tripsData.get(counter).equals(country) && tripsData.get(counter + 5).equals(departure_city)) {
                    assert departure != null;
                    if (departure.compareTo(date1) == 0) {
                        assert arrival != null;
                        if (arrival.compareTo(date2) == 0 && (childrenQuantity + adultsQuantity) <= Integer.parseInt(tripsData.get(counter + 6))) {
                            model.addRow(new Object[]{tripsData.get(counter), tripsData.get(counter + 1), (tripsData.get(counter + 2) + " - " + tripsData.get(counter + 3)),
                                    tripsData.get(counter + 4) + " zł"});
                            idRows.put(rowCounter, Integer.parseInt(tripsData.get(counter + 7)));
                            rowCounter++;
                        }
                    }
                }
                if (size > 1)
                    counter += 10;
            }
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji daty wyjazdu i przyjazdu
     */
    private boolean performDateValidation() {
        if(!Validation.isDateValid(departureTextField.getText()) || !Validation.isDateValid(arrivalTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisana data.", "Informacja", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
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
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new SearchEngine().setVisible(true));
    }
    private final JScrollPane mainScroll = new JScrollPane();
    private final JPanel mainWindow = new JPanel();
    private final JPanel mainPanel = new JPanel();
    private final JPanel countriesPanel = new JPanel();
    private final JButton tripsButton = new JButton();
    private final JButton summer2023Button = new JButton();
    private final JButton lastMinuteButton = new JButton();
    private final JButton exoticButton = new JButton();
    private final JButton greeceButton = new JButton();
    private final JButton spainButton = new JButton();
    private final JButton turkeyButton = new JButton();
    private final JButton egyptButton = new JButton();
    private final JButton italyButton = new JButton();
    private final JButton bulgariaButton = new JButton();
    private final JLabel agencyNameLabel = new JLabel();
    private final JPanel searchPanel = new JPanel();
    private final JLabel tripDirectionLabel = new JLabel();
    private final JLabel departureCityLabel = new JLabel();
    private final JLabel departureArrivalLabel = new JLabel();
    private final JLabel peopleQuantityLabel = new JLabel();
    private final JPanel searchPanelIntroduction = new JPanel();
    private final JButton searchButton = new JButton();
    private final JScrollPane tableScroll = new JScrollPane();
    private final JPanel footer = new JPanel();
    private final JButton sendButton = new JButton();
    private final JLabel headphonesIconLabel = new JLabel();
    /**
     * Menu podręczne z opcjami kierunku podróży
     */
    private final javax.swing.JComboBox<String> destinationChoiceComboBox = new JComboBox<>();
    /**
     * Element umożliwiający wybór ilości dorosłych
     */
    private final javax.swing.JSpinner adultsQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
    /**
     * Element umożliwiający wybór ilości dzieci
     */
    private final javax.swing.JSpinner childrenQuantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));
    /**
     * Etykieta ze zdjęciem
     */
    private final javax.swing.JLabel imagesLabel = new JLabel();
    /**
     * Pole do wprowadzenia daty zakończenia wycieczki
     */
    private final javax.swing.JTextField arrivalTextField = new JTextField();
    /**
     * Menu podręczne z opcjami miejsca wylotu
     */
    private final javax.swing.JComboBox<String> departureCityChoice = new JComboBox<>();
    /**
     * Tabela z dostępnymi wycieczkami
     */
    private final javax.swing.JTable tripsTable= new JTable();
    /**
     * Pole do wprowadzenia daty rozpoczęcia wycieczki
     */
    private final javax.swing.JTextField departureTextField = new JTextField();
    /**
     * Menu podręczne z opcjami Strona Główna, Moje Konto, Wyloguj się
     */
    private final javax.swing.JComboBox<String> managingComboBox = new JComboBox<>();
    /**
     * Pole do wprowadzenia numeru telefonu
     */
    private final javax.swing.JTextField leaveNumberTextField = new JTextField();
}