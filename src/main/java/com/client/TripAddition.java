package com.client;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripAddition extends javax.swing.JFrame {
    public List<String> data = new ArrayList<>();
    List<String> departureCities = new ArrayList<>();
    public String departure, arrival;
    private boolean countryCorrect;
    private boolean cityCorrect;
    private boolean departureCityCorrect;
    private boolean priceCorrect;
    private boolean hotelNameCorrect;
    private boolean peopleLimitCorrect;
    private Validation validation;
    public String country;
    public String city;
    public String departureCity;
    public String price;
    public String peopleLimit;
    public String hotelName;
    private Date arrivalDate;
    private Date departureDate;
    private javax.swing.JTextField limitTextField;
    private com.toedter.calendar.JDateChooser arrivalDateChooser;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField countryTextField;
    private com.toedter.calendar.JDateChooser departureDateChooser;
    private javax.swing.JTextField departureCityField;
    private javax.swing.JTextField nameHotelTextField;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel validArrivalLabel;
    private javax.swing.JLabel validCityLabel;
    private javax.swing.JLabel validCountryLabel;
    private javax.swing.JLabel validDepartureCityLabel;
    private javax.swing.JLabel validDepartureLabel;
    private javax.swing.JLabel validLimitLabel;
    private javax.swing.JLabel validNameHotelLabel;
    private javax.swing.JLabel validPriceLabel;
    private Client client;
    private String adminName;
    public TripAddition(Client client, String adminName) {
        this.client = client;
        this.adminName = adminName;
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        departureCities.add("Krakow");
        departureCities.add("Warszawa");
        departureCities.add("Wroclaw");
        departureCities.add("Katowice");
        departureCities.add("Gdansk");
        validCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validCountryLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validDepartureCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validPriceLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        validNameHotelLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        validDepartureLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        validArrivalLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    new Client("logOut",new ArrayList<>());
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    public TripAddition(boolean overrided){}
    private void initComponents() {
        JPanel regPanel = new JPanel();
        JLabel createLabel = new JLabel();
        JLabel countryLabel = new JLabel();
        countryTextField = new javax.swing.JTextField();
        JLabel cityLabel = new JLabel();
        cityTextField = new javax.swing.JTextField();
        JLabel departureCityLabel = new JLabel();
        departureCityField = new javax.swing.JTextField();
        JLabel priceLabel = new JLabel();
        priceField = new javax.swing.JTextField();
        JLabel limitLabel = new JLabel();
        limitTextField = new javax.swing.JTextField();
        JLabel departureLabel = new JLabel();
        JLabel arrivalLabel = new JLabel();
        JLabel hotelNameLabel = new JLabel();
        nameHotelTextField = new javax.swing.JTextField();
        JButton submitButton = new JButton();
        JButton cancelButton = new JButton();
        departureDateChooser = new com.toedter.calendar.JDateChooser();
        arrivalDateChooser = new com.toedter.calendar.JDateChooser();
        validCountryLabel = new javax.swing.JLabel();
        validCityLabel = new javax.swing.JLabel();
        validDepartureCityLabel = new javax.swing.JLabel();
        validPriceLabel = new javax.swing.JLabel();
        validLimitLabel = new javax.swing.JLabel();
        validDepartureLabel = new javax.swing.JLabel();
        JLabel validCountryLabel6 = new JLabel();
        validArrivalLabel = new javax.swing.JLabel();
        validNameHotelLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dodaj wycieczkę");
        setMaximumSize(new java.awt.Dimension(1024, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));

        regPanel.setBackground(new java.awt.Color(247, 233, 201));
        regPanel.setPreferredSize(new java.awt.Dimension(450, 620));

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

        departureDateChooser.setMinSelectableDate(new java.util.Date(-62135769509000L));

        validCountryLabel.setForeground(new java.awt.Color(255, 0, 0));
        validCountryLabel.setText(" ");

        validCityLabel.setForeground(new java.awt.Color(255, 0, 0));
        validCityLabel.setText(" ");

        validDepartureCityLabel.setForeground(new java.awt.Color(255, 0, 0));
        validDepartureCityLabel.setText(" ");

        validPriceLabel.setForeground(new java.awt.Color(255, 0, 0));
        validPriceLabel.setText(" ");

        validLimitLabel.setForeground(new java.awt.Color(255, 0, 0));
        validLimitLabel.setText(" ");

        validDepartureLabel.setForeground(new java.awt.Color(255, 0, 0));
        validDepartureLabel.setText(" ");

        validCountryLabel6.setForeground(new java.awt.Color(255, 0, 0));
        validCountryLabel6.setText(" ");

        validArrivalLabel.setForeground(new java.awt.Color(255, 0, 0));
        validArrivalLabel.setText(" ");

        validNameHotelLabel.setForeground(new java.awt.Color(255, 0, 0));
        validNameHotelLabel.setText(" ");

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
                            .addComponent(validPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(validDepartureCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameHotelTextField)
                                .addComponent(countryTextField)
                                .addComponent(cityTextField)
                                .addComponent(departureCityField, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                .addComponent(countryLabel)
                                .addComponent(cityLabel)
                                .addComponent(departureCityLabel)
                                .addComponent(validCountryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(validNameHotelLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(regPanelLayout.createSequentialGroup()
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(validDepartureLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(validLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(validArrivalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(validCountryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validCityLabel)
                .addGap(2, 2, 2)
                .addComponent(departureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departureCityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validDepartureCityLabel)
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
                    .addComponent(validPriceLabel)
                    .addComponent(validLimitLabel))
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
                    .addComponent(validDepartureLabel)
                    .addComponent(validArrivalLabel))
                .addGap(2, 2, 2)
                .addComponent(hotelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameHotelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validNameHotelLabel)
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

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performCountryValidation();
        performCityValidation();
        performDepartureCityValidation();
        performPriceValidation();
        performPeopleLimitValidation();
        performHotelNameValidtion();
        boolean departureCorrect = departureIsValid();
        boolean arrivalCorrect = arrivalIsValid();
        if(countryCorrect && cityCorrect && departureCityCorrect && priceCorrect && peopleLimitCorrect && hotelNameCorrect && departureCorrect && arrivalCorrect) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            arrival = dateFormat.format(arrivalDate);
            departure = dateFormat.format(departureDate);
            data.clear();
            data.add(country);
            data.add(city);
            data.add(departureCity);
            data.add(price);
            data.add(peopleLimit);
            data.add(hotelName);
            data.add(arrival);
            data.add(departure);
            new Client("addTrip",data);
            data.clear();
            dispose();
            new Trips(client,adminName).setVisible(true);
        }
    }

    private boolean departureIsValid() {
        departureDate = departureDateChooser.getDate();
        if(departureDate == null) {
            validDepartureLabel.setText("Pole wymagane.");
            return false;
        }
        validDepartureLabel.setText("");
        return true;
    }
    private boolean arrivalIsValid() {
        arrivalDate = arrivalDateChooser.getDate();
        if(arrivalDate == null) {
            validArrivalLabel.setText("Pole wymagane.");
            return false;
        }
        validArrivalLabel.setText("");
        return true;
    }

    private void performCityValidation() {
        city = cityTextField.getText();
        if(city.equals("")) {
            validCityLabel.setText("Pole wymagane.");
            cityCorrect = false;
        }
        else {
            cityCorrect = validation.countryOrCityIsValid(city);
            if (cityCorrect)
                validCityLabel.setText("");
            else
                validCityLabel.setText("Sprawdź czy podane miasto jest poprawne.");
        }
    }

    private void performDepartureCityValidation() {
        departureCity = departureCityField.getText();
        if(departureCity.equals("")) {
            validDepartureCityLabel.setText("Pole wymagane.");
            departureCityCorrect = false;
        }
        else {
            if (departureCities.contains(departureCity)) {
                validDepartureCityLabel.setText("");
                departureCityCorrect = true;
            } else {
                validDepartureCityLabel.setText("Nie mozna leciec z tego miasta.");
                departureCityCorrect = false;
            }
        }
    }

    private void performPriceValidation() {
        price = priceField.getText();
        if(price.equals("")) {
            validPriceLabel.setText("Pole wymagane.");
            priceCorrect = false;
        }
        else {
            priceCorrect = validation.priceIsValid(price);
            if (priceCorrect)
                validPriceLabel.setText("");
            else
                validPriceLabel.setText("Zły format.");
        }
    }

    private void performPeopleLimitValidation() {
        peopleLimit = limitTextField.getText();
        if(peopleLimit.equals("")) {
            validLimitLabel.setText("Pole wymagane.");
            peopleLimitCorrect = false;
        }
        else {
            peopleLimitCorrect = validation.peopleLimitIsValid(peopleLimit);
            if (peopleLimitCorrect)
                validLimitLabel.setText("");
            else
                validLimitLabel.setText("Zły format.");
        }
    }
    private void performHotelNameValidtion() {
        hotelName = nameHotelTextField.getText();
        if(hotelName.equals("")) {
            validNameHotelLabel.setText("Pole wymagane.");
            hotelNameCorrect = false;
        }
        else {
            hotelNameCorrect = validation.hotelNameIsValid(hotelName);
            if (hotelNameCorrect)
                validNameHotelLabel.setText("");
            else
                validNameHotelLabel.setText("Sprawdź czy podana nazwa hotelu jest poprawna.");
        }
    }

    private void performCountryValidation() {
        country = countryTextField.getText();
        if(country.equals("")) {
            validCountryLabel.setText("Pole wymagane.");
            countryCorrect = false;
        }
        else {
            countryCorrect = validation.countryOrCityIsValid(country);
            if (countryCorrect)
                validCountryLabel.setText("");
            else
                validCountryLabel.setText("Sprawdź czy podany kraj jest poprawny.");
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Trips().setVisible(true);
    }
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
}