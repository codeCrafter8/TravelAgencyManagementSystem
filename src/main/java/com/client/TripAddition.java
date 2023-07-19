package com.client;

import com.server.LogsAdmins;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność dodania wycieczki
 */
public class TripAddition extends javax.swing.JFrame {
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą możliwe miasta wylotu/przylotu
     */
    private final List<String> departureCities = new ArrayList<>();
    /**
     * Atrybut określający, czy kraj jest poprawny
     */
    private boolean countryCorrect;
    /**
     * Atrybut określający, czy miasto jest poprawne
     */
    private boolean cityCorrect;
    /**
     * Atrybut określający, czy miasto wylotu/przylotu jest poprawne
     */
    private boolean departureCityCorrect;
    /**
     * Atrybut określający, czy cena jest poprawna
     */
    private boolean priceCorrect;
    /**
     * Atrybut określający, czy nazwa hotelu jest poprawna
     */
    private boolean hotelNameCorrect;
    /**
     * Atrybut określający, czy limit osób jest poprawny
     */
    private boolean peopleLimitCorrect;
    /**
     * Atrybut przechowujący kraj
     */
    private String country;
    /**
     * Atrybut przechowujący miasto
     */
    private String city;
    /**
     * Atrybut przechowujący miasto wylotu/przylotu
     */
    private String departureCity;
    /**
     * Atrybut przechowujący cenę
     */
    private String price;
    /**
     * Atrybut przechowujący limit osób
     */
    private String peopleLimit;
    /**
     * Atrybut przechowujący nazwę hotelu
     */
    private String hotelName;
    /**
     * Atrybut przechowujący datę przyjazdu
     */
    private Date arrivalDate;
    /**
     * Atrybut przechowujący datę wyjazdu
     */
    private Date departureDate;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private final Client client;
    /**
     * Atrybut przechowujący imię administratora
     */
    private final String adminName;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Klient
     * @param adminName parametr przechowujący imię administratora
     */
    public TripAddition(Client client, String adminName) {
        this.client = client;
        this.adminName = adminName;
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        departureCities.add("Krakow");
        departureCities.add("Warszawa");
        departureCities.add("Wroclaw");
        departureCities.add("Katowice");
        departureCities.add("Gdansk");
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    new Client(data);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("TripAdditon", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        createLabels();
        createButtons();
        createRegPanel();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dodaj wycieczkę");
        setMaximumSize(new java.awt.Dimension(1024, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
    }
    private void createLabels(){
        createLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 24));
        createLabel.setText("Dodaj wycieczkę");
        countryLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        countryLabel.setText("Kraj");
        cityLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        cityLabel.setText("Miasto");
        departureCityLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        departureCityLabel.setText("Miasto wylotu/przylotu");
        priceLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        priceLabel.setText("Cena za osobę");
        limitLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        limitLabel.setText("Limit osób");
        departureLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        departureLabel.setText("Wyjazd");
        arrivalLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        arrivalLabel.setText("Przyjazd");
        hotelNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        hotelNameLabel.setText("Nazwa hotelu");
        wrongCountryLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongCountryLabel.setText(" ");
        wrongCityLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongCityLabel.setText(" ");
        wrongDepartureCityLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongDepartureCityLabel.setText(" ");
        wrongPriceLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongPriceLabel.setText(" ");
        wrongLimitLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongLimitLabel.setText(" ");
        wrongDepartureLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongDepartureLabel.setText(" ");
        validCountryLabel6.setForeground(new java.awt.Color(255, 0, 0));
        validCountryLabel6.setText(" ");
        wrongArrivalLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongArrivalLabel.setText(" ");
        wrongHotelNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongHotelNameLabel.setText(" ");
        wrongCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        wrongCountryLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        wrongDepartureCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        wrongPriceLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        wrongHotelNameLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        wrongDepartureLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        wrongArrivalLabel.setMinimumSize(new java.awt.Dimension(100, 16));
    }
    private void createButtons(){
        submitButton.setBackground(new java.awt.Color(189, 165, 111));
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setText("Dodaj wycieczkę");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(new java.awt.Color(189, 165, 111));
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new java.awt.Dimension(116, 27));
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    private void createRegPanel(){
        regPanel.setBackground(new java.awt.Color(247, 233, 201));
        regPanel.setPreferredSize(new java.awt.Dimension(450, 620));
        departureDateChooser.setMinSelectableDate(new java.util.Date(-62135769509000L));

        javax.swing.GroupLayout regPanelLayout = new javax.swing.GroupLayout(regPanel);
        regPanel.setLayout(regPanelLayout);
        regPanelLayout.setHorizontalGroup(
                regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(regPanelLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(regPanelLayout.createSequentialGroup()
                                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(hotelNameLabel)
                                                        .addComponent(wrongPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(wrongDepartureCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(nameHotelTextField)
                                                                .addComponent(countryTextField)
                                                                .addComponent(cityTextField)
                                                                .addComponent(departureCityField, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                                                .addComponent(countryLabel)
                                                                .addComponent(cityLabel)
                                                                .addComponent(departureCityLabel)
                                                                .addComponent(wrongCountryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(wrongCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(wrongHotelNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(regPanelLayout.createSequentialGroup()
                                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(wrongDepartureLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(departureLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(priceField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(departureDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                        .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 88, Short.MAX_VALUE)
                                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(limitLabel)
                                                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(arrivalDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(arrivalLabel))
                                                        .addComponent(limitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wrongLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wrongArrivalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(regPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(submitButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(createLabel)
                                .addGap(135, 135, 135))
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(regPanelLayout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(validCountryLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(292, Short.MAX_VALUE)))
        );
        regPanelLayout.setVerticalGroup(
                regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(regPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(createLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(countryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(countryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(wrongCountryLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongCityLabel)
                                .addGap(2, 2, 2)
                                .addComponent(departureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(departureCityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongDepartureCityLabel)
                                .addGap(2, 2, 2)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(limitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(limitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(wrongPriceLabel)
                                        .addComponent(wrongLimitLabel))
                                .addGap(2, 2, 2)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(departureLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(arrivalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(arrivalDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(departureDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(wrongDepartureLabel)
                                        .addComponent(wrongArrivalLabel))
                                .addGap(2, 2, 2)
                                .addComponent(hotelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameHotelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongHotelNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58))
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                                        .addContainerGap(340, Short.MAX_VALUE)
                                        .addComponent(validCountryLabel6)
                                        .addGap(245, 245, 245)))
        );
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(287, 287, 287)
                                .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(287, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(63, Short.MAX_VALUE)
                                .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Dodaj wycieczkę"
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performCountryValidation();
        performCityValidation();
        performDepartureCityValidation();
        performPriceValidation();
        performPeopleLimitValidation();
        performHotelNameValidation();
        boolean departureCorrect = departureIsValid();
        boolean arrivalCorrect = arrivalIsValid();
        if(countryCorrect && cityCorrect && departureCityCorrect && priceCorrect && peopleLimitCorrect && hotelNameCorrect && departureCorrect && arrivalCorrect) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String arrival = dateFormat.format(arrivalDate);
            String departure = dateFormat.format(departureDate);
            data.clear();
            data.add("addTrip");
            data.add(country);
            data.add(city);
            data.add(departureCity);
            data.add(price);
            data.add(peopleLimit);
            data.add(hotelName);
            data.add(arrival);
            data.add(departure);
            new Client(data);
            data.clear();
            dispose();
            new Trips(client,adminName).setVisible(true);
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji daty wyjazdu
     * @return zwraca true jeśli data wyjazdu jest poprawna, w przeciwnym wypadku zwraca false
     */
    private boolean departureIsValid() {
        departureDate = departureDateChooser.getDate();
        if(departureDate == null) {
            wrongDepartureLabel.setText("Pole wymagane.");
            return false;
        }
        wrongDepartureLabel.setText("");
        return true;
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji daty przyjazdu
     * @return zwraca true jeśli data przyjazdu jest poprawna, w przeciwnym wypadku zwraca false
     */
    private boolean arrivalIsValid() {
        arrivalDate = arrivalDateChooser.getDate();
        if(arrivalDate == null) {
            wrongArrivalLabel.setText("Pole wymagane.");
            return false;
        }
        wrongArrivalLabel.setText("");
        return true;
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji miasta
     */
    private void performCityValidation() {
        city = cityTextField.getText();
        if(city.equals("")) {
            wrongCityLabel.setText("Pole wymagane.");
            cityCorrect = false;
        }
        else {
            cityCorrect = Validation.isCountryOrCityValid(city);
            if (cityCorrect)
                wrongCityLabel.setText("");
            else
                wrongCityLabel.setText("Sprawdź czy podane miasto jest poprawne.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji miasta wylotu/przylotu
     */
    private void performDepartureCityValidation() {
        departureCity = departureCityField.getText();
        if(departureCity.equals("")) {
            wrongDepartureCityLabel.setText("Pole wymagane.");
            departureCityCorrect = false;
        }
        else {
            if (departureCities.contains(departureCity)) {
                wrongDepartureCityLabel.setText("");
                departureCityCorrect = true;
            } else {
                wrongDepartureCityLabel.setText("Nie mozna leciec z tego miasta.");
                departureCityCorrect = false;
            }
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji ceny
     */
    private void performPriceValidation() {
        price = priceField.getText();
        if(price.equals("")) {
            wrongPriceLabel.setText("Pole wymagane.");
            priceCorrect = false;
        }
        else {
            priceCorrect = Validation.isPriceValid(price);
            if (priceCorrect)
                wrongPriceLabel.setText("");
            else
                wrongPriceLabel.setText("Zły format.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji limitu osób
     */
    private void performPeopleLimitValidation() {
        peopleLimit = limitTextField.getText();
        if(peopleLimit.equals("")) {
            wrongLimitLabel.setText("Pole wymagane.");
            peopleLimitCorrect = false;
        }
        else {
            peopleLimitCorrect = Validation.isPeopleLimitValid(peopleLimit);
            if (peopleLimitCorrect)
                wrongLimitLabel.setText("");
            else
                wrongLimitLabel.setText("Zły format.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji nazwy hotelu
     */
    private void performHotelNameValidation() {
        hotelName = nameHotelTextField.getText();
        if(hotelName.equals("")) {
            wrongHotelNameLabel.setText("Pole wymagane.");
            hotelNameCorrect = false;
        }
        else {
            hotelNameCorrect = Validation.isHotelNameValid(hotelName);
            if (hotelNameCorrect)
                wrongHotelNameLabel.setText("");
            else
                wrongHotelNameLabel.setText("Sprawdź czy podana nazwa hotelu jest poprawna.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji kraju
     */
    private void performCountryValidation() {
        country = countryTextField.getText();
        if(country.equals("")) {
            wrongCountryLabel.setText("Pole wymagane.");
            countryCorrect = false;
        }
        else {
            countryCorrect = Validation.isCountryOrCityValid(country);
            if (countryCorrect)
                wrongCountryLabel.setText("");
            else
                wrongCountryLabel.setText("Sprawdź czy podany kraj jest poprawny.");
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Anuluj"
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Trips().setVisible(true);
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
            java.util.logging.Logger.getLogger(TripAddition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TripAddition(null,null).setVisible(true));
    }
    //GUI variables
    private final JPanel regPanel = new JPanel();
    private final JLabel createLabel = new JLabel();
    private final JLabel countryLabel = new JLabel();
    private final JLabel cityLabel = new JLabel();
    private final JLabel departureCityLabel = new JLabel();
    private final JLabel priceLabel = new JLabel();
    private final JLabel limitLabel = new JLabel();
    private final JLabel departureLabel = new JLabel();
    private final JLabel arrivalLabel = new JLabel();
    private final JLabel hotelNameLabel = new JLabel();
    private final JButton submitButton = new JButton();
    private final JButton cancelButton = new JButton();
    private final JLabel validCountryLabel6 = new JLabel();
    /**
     * Pole do wprowadzenia limitu osób
     */
    private final javax.swing.JTextField limitTextField = new JTextField();
    /**
     * Element do wyboru daty przyjazdu
     */
    private final com.toedter.calendar.JDateChooser arrivalDateChooser = new com.toedter.calendar.JDateChooser();
    /**
     * Pole do wprowadzenia miasta
     */
    private final javax.swing.JTextField cityTextField = new JTextField();
    /**
     * Pole do wprowadzenia kraju
     */
    private final javax.swing.JTextField countryTextField = new JTextField();
    /**
     * Element do wyboru daty wyjazdu
     */
    private final com.toedter.calendar.JDateChooser departureDateChooser = new com.toedter.calendar.JDateChooser();
    /**
     * Pole do wprowadzenia miasta wylotu/przylotu
     */
    private final javax.swing.JTextField departureCityField = new JTextField();
    /**
     * Pole do wprowadzenia nazwy hotelu
     */
    private final javax.swing.JTextField nameHotelTextField = new JTextField();
    /**
     * Pole do wprowadzenia ceny
     */
    private final javax.swing.JTextField priceField = new JTextField();
    /**
     * Etykieta informująca, że data przyjazu jest niepoprawna
     */
    private final javax.swing.JLabel wrongArrivalLabel = new JLabel();
    /**
     * Etykieta informująca, że miasto jest niepoprawne
     */
    private final javax.swing.JLabel wrongCityLabel = new JLabel();
    /**
     * Etykieta informująca, że kraj jest niepoprawny
     */
    private final javax.swing.JLabel wrongCountryLabel = new JLabel();
    /**
     * Etykieta informująca, że miasto wylotu/przylotu jest niepoprawne
     */
    private final javax.swing.JLabel wrongDepartureCityLabel = new JLabel();
    /**
     * Etykieta informująca, że data wyjazdu jest niepoprawna
     */
    private final javax.swing.JLabel wrongDepartureLabel = new JLabel();
    /**
     * Etykieta informująca, że limit osób jest niepoprawny
     */
    private final javax.swing.JLabel wrongLimitLabel = new JLabel();
    /**
     * Etykieta informująca, że nazwa hotelu jest niepoprawna
     */
    private final javax.swing.JLabel wrongHotelNameLabel = new JLabel();
    /**
     * Etykieta informująca, że cena jest niepoprawna
     */
    private final javax.swing.JLabel wrongPriceLabel = new JLabel();
}