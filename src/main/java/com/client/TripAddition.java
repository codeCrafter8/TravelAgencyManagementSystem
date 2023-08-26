package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.TripValidator;
import com.server.logging.LogsAdmins;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A class containing fields and methods for handling a window with functionality to add a trip.
 */
public class TripAddition extends javax.swing.JFrame {

    /**
     * A constant representing the small dimension used for wrong item labels.
     * The small dimension is typically used for error message labels to display
     * short error messages with limited width and height.
     */
    public static final Dimension WRONG_ITEM_LABEL_DIMENSION_SMALL = new Dimension(100, 16);

    /**
     * An attribute representing a list holding data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * An attribute representing a list holding possible departure/arrival cities.
     */
    private final List<String> departureCities = new ArrayList<>();

    /**
     * An attribute indicating whether the country is correct.
     */
    private boolean countryCorrect;

    /**
     * An attribute indicating whether the city is correct.
     */
    private boolean cityCorrect;

    /**
     * An attribute indicating whether the departure/arrival city is correct.
     */
    private boolean departureCityCorrect;

    /**
     * An attribute indicating whether the price is correct.
     */
    private boolean priceCorrect;

    /**
     * An attribute indicating whether the hotel name is correct.
     */
    private boolean hotelNameCorrect;

    /**
     * An attribute indicating whether the people limit is correct.
     */
    private boolean peopleLimitCorrect;

    /**
     * An attribute holding the country.
     */
    private String country;

    /**
     * An attribute holding the city.
     */
    private String city;

    /**
     * An attribute holding the departure/arrival city.
     */
    private String departureCity;

    /**
     * An attribute holding the price.
     */
    private String price;

    /**
     * An attribute holding the people limit.
     */
    private String peopleLimit;

    /**
     * An attribute holding the hotel name.
     */
    private String hotelName;

    /**
     * An attribute holding the arrival date.
     */
    private Date arrivalDate;

    /**
     * An attribute holding the departure date.
     */
    private Date departureDate;

    /**
     * An attribute being an object of the Client class.
     */
    private final Client client;

    /**
     * An attribute holding the administrator's name.
     */
    private final String adminName;

    /**
     * A constructor responsible for initializing the GUI and relevant elements.
     * @param client parameter holding an object of the Client class.
     * @param adminName parameter holding the administrator's name.
     */
    public TripAddition(Client client, String adminName) {
        this.client = client;
        this.adminName = adminName;
        initComponents();
        setDepartureCities();
    }

    /**
     * Adds a predefined list of departure cities to the departureCities collection.
     * These cities are commonly used as departure points for flights or travel destinations.
     */
    private void setDepartureCities(){
        departureCities.add("Krakow");
        departureCities.add("Warsaw");
        departureCities.add("Wroclaw");
        departureCities.add("Katowice");
        departureCities.add("Gdansk");
    }

    /**
     * Initializes graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createLabels();
        createButtons();
        createRegPanel();
        createLayout();
    }

    /**
     * Sets the properties of the window for adding a trip.
     * The properties include setting the default close operation, title, background color,
     * minimum size, and adding a window listener to handle the window closing event.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dodaj wycieczkę");
        getContentPane().setBackground(ColorUtils.BEIGE);
        setMinimumSize(DimensionUtils.WINDOW_DIMENSION);
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
     * Creates labels for various input fields and error messages.
     * Sets the fonts, texts, and other properties for each label.
     */
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
        wrongCountryLabel.setForeground(Color.red);
        wrongCountryLabel.setText(" ");
        wrongCityLabel.setForeground(Color.red);
        wrongCityLabel.setText(" ");
        wrongDepartureCityLabel.setForeground(Color.red);
        wrongDepartureCityLabel.setText(" ");
        wrongPriceLabel.setForeground(Color.red);
        wrongPriceLabel.setText(" ");
        wrongLimitLabel.setForeground(Color.red);
        wrongLimitLabel.setText(" ");
        wrongDepartureLabel.setForeground(Color.red);
        wrongDepartureLabel.setText(" ");
        validCountryLabel.setForeground(Color.red);
        validCountryLabel.setText(" ");
        wrongArrivalLabel.setForeground(Color.red);
        wrongArrivalLabel.setText(" ");
        wrongHotelNameLabel.setForeground(Color.red);
        wrongHotelNameLabel.setText(" ");
        wrongCityLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongCountryLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongDepartureCityLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongDepartureLabel.setMinimumSize(WRONG_ITEM_LABEL_DIMENSION_SMALL);
        wrongArrivalLabel.setMinimumSize(WRONG_ITEM_LABEL_DIMENSION_SMALL);
        wrongPriceLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongHotelNameLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Creates buttons for submitting and canceling the trip addition process.
     * Sets the fonts, texts, action listeners, and other properties for each button.
     */
    private void createButtons(){
        submitButton.setBackground(ColorUtils.DARK_BEIGE);
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setText("Dodaj wycieczkę");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(ColorUtils.DARK_BEIGE);
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(DimensionUtils.SUBMIT_CANCEL_BUTTON_DIMENSION);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    /**
     * Creates a registration panel with various input fields for trip details.
     * Sets the background color, preferred size, fonts, and layout for the panel.
     * Configures date choosers, text fields, and error message labels within the panel.
     */
    private void createRegPanel(){
        regPanel.setBackground(ColorUtils.LIGHT_BEIGE);
        regPanel.setPreferredSize(DimensionUtils.REG_PANEL_DIMENSION);
        regPanel.setPreferredSize(DimensionUtils.REG_PANEL_DIMENSION);
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
                                        .addComponent(validCountryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(validCountryLabel)
                                        .addGap(245, 245, 245)))
        );
    }

    /**
     * Creates the overall layout for the window by arranging the registration panel.
     * Uses GroupLayout to set the layout constraints and positions for the components.
     */
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
     * Handles the "Add Trip" button click.
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
        if (countryCorrect && cityCorrect && departureCityCorrect && priceCorrect && peopleLimitCorrect && hotelNameCorrect && departureCorrect && arrivalCorrect) {
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
            new Trips(client, adminName).setVisible(true);
        }
    }

    /**
     * Method responsible for the validation of the departure date.
     * @return Returns true if the departure date is valid; otherwise, returns false.
     */
    private boolean departureIsValid() {
        departureDate = departureDateChooser.getDate();
        if (departureDate == null) {
            wrongDepartureLabel.setText("This field is required.");
            return false;
        }
        wrongDepartureLabel.setText("");
        return true;
    }

    /**
     * Method responsible for the validation of the arrival date.
     * @return Returns true if the arrival date is valid; otherwise, returns false.
     */
    private boolean arrivalIsValid() {
        arrivalDate = arrivalDateChooser.getDate();
        if (arrivalDate == null) {
            wrongArrivalLabel.setText("This field is required.");
            return false;
        }
        wrongArrivalLabel.setText("");
        return true;
    }

    /**
     * Method responsible for the validation of the city.
     */
    private void performCityValidation() {
        city = cityTextField.getText();
        if (city.equals("")) {
            wrongCityLabel.setText("This field is required.");
            cityCorrect = false;
        } else {
            cityCorrect = TripValidator.isCountryOrCityValid(city);
            if (cityCorrect)
                wrongCityLabel.setText("");
            else
                wrongCityLabel.setText("Check if the provided city is valid.");
        }
    }

    /**
     * Method responsible for the validation of the departure city.
     */
    private void performDepartureCityValidation() {
        departureCity = departureCityField.getText();
        if (departureCity.equals("")) {
            wrongDepartureCityLabel.setText("This field is required.");
            departureCityCorrect = false;
        } else {
            if (departureCities.contains(departureCity)) {
                wrongDepartureCityLabel.setText("");
                departureCityCorrect = true;
            } else {
                wrongDepartureCityLabel.setText("Cannot fly from this city.");
                departureCityCorrect = false;
            }
        }
    }

    /**
     * Method responsible for the validation of the country.
     */
    private void performCountryValidation() {
        country = countryTextField.getText();
        if (country.equals("")) {
            wrongCountryLabel.setText("This field is required.");
            countryCorrect = false;
        } else {
            countryCorrect = TripValidator.isCountryOrCityValid(country);
            if (countryCorrect)
                wrongCountryLabel.setText("");
            else
                wrongCountryLabel.setText("Check if the provided country is valid.");
        }
    }

    /**
     * Method responsible for the validation of the price.
     */
    private void performPriceValidation() {
        price = priceField.getText();
        if (price.equals("")) {
            wrongPriceLabel.setText("This field is required.");
            priceCorrect = false;
        } else {
            priceCorrect = TripValidator.isPriceValid(price);
            if (priceCorrect)
                wrongPriceLabel.setText("");
            else
                wrongPriceLabel.setText("Invalid format.");
        }
    }

    /**
     * Method responsible for the validation of the people limit.
     */
    private void performPeopleLimitValidation() {
        peopleLimit = limitTextField.getText();
        if (peopleLimit.equals("")) {
            wrongLimitLabel.setText("This field is required.");
            peopleLimitCorrect = false;
        } else {
            peopleLimitCorrect = TripValidator.isPeopleLimitValid(peopleLimit);
            if (peopleLimitCorrect)
                wrongLimitLabel.setText("");
            else
                wrongLimitLabel.setText("Invalid format.");
        }
    }

    /**
     * Method responsible for the validation of the hotel name.
     */
    private void performHotelNameValidation() {
        hotelName = nameHotelTextField.getText();
        if (hotelName.equals("")) {
            wrongHotelNameLabel.setText("This field is required.");
            hotelNameCorrect = false;
        } else {
            hotelNameCorrect = TripValidator.isHotelNameValid(hotelName);
            if (hotelNameCorrect)
                wrongHotelNameLabel.setText("");
            else
                wrongHotelNameLabel.setText("Check if the provided hotel name is valid.");
        }
    }

    /**
     * Handles the "Cancel" button click.
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Trips().setVisible(true);
    }

    /**
     * Allows to run the window.
     * @param args Arguments passed during application startup.
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
        java.awt.EventQueue.invokeLater(() -> new TripAddition(null, null).setVisible(true));
    }

    /**
     * A panel that holds the components for registering a trip.
     */
    private final JPanel regPanel = new JPanel();

    /**
     * A label displaying the title for the trip registration section.
     */
    private final JLabel createLabel = new JLabel();

    /**
     * A label displaying the text "Kraj" for the country input field.
     */
    private final JLabel countryLabel = new JLabel();

    /**
     * A label displaying the text "Miasto" for the city input field.
     */
    private final JLabel cityLabel = new JLabel();

    /**
     * A label displaying the text "Miasto wylotu/przylotu" for the departure city input field.
     */
    private final JLabel departureCityLabel = new JLabel();

    /**
     * A label displaying the text "Cena za osobę" for the price input field.
     */
    private final JLabel priceLabel = new JLabel();

    /**
     * A label displaying the text "Limit osób" for the participant limit input field.
     */
    private final JLabel limitLabel = new JLabel();

    /**
     * A label displaying the text "Wyjazd" for the departure date input field.
     */
    private final JLabel departureLabel = new JLabel();

    /**
     * A label displaying the text "Przyjazd" for the arrival date input field.
     */
    private final JLabel arrivalLabel = new JLabel();

    /**
     * A label displaying the text "Nazwa hotelu" for the hotel name input field.
     */
    private final JLabel hotelNameLabel = new JLabel();

    /**
     * A button used for submitting the trip registration form.
     */
    private final JButton submitButton = new JButton();

    /**
     * A button used for canceling the trip registration.
     */
    private final JButton cancelButton = new JButton();

    /**
     * A label used to display a valid country message.
     */
    private final JLabel validCountryLabel = new JLabel();

    /**
     * A text field for entering the people limit.
     */
    private final javax.swing.JTextField limitTextField = new JTextField();

    /**
     * An element for choosing the arrival date.
     */
    private final com.toedter.calendar.JDateChooser arrivalDateChooser = new com.toedter.calendar.JDateChooser();

    /**
     * A text field for entering the city.
     */
    private final javax.swing.JTextField cityTextField = new JTextField();

    /**
     * A text field for entering the country.
     */
    private final javax.swing.JTextField countryTextField = new JTextField();

    /**
     * An element for choosing the departure date.
     */
    private final com.toedter.calendar.JDateChooser departureDateChooser = new com.toedter.calendar.JDateChooser();

    /**
     * A text field for entering the departure city.
     */
    private final javax.swing.JTextField departureCityField = new JTextField();

    /**
     * A text field for entering the hotel name.
     */
    private final javax.swing.JTextField nameHotelTextField = new JTextField();

    /**
     * A text field for entering the price.
     */
    private final javax.swing.JTextField priceField = new JTextField();

    /**
     * A label indicating that the arrivalA ldate is incorrect.
     */
    private final javax.swing.JLabel wrongArrivalLabel = new JLabel();

    /**
     * A label indicating that the city is incorrect.
     */
    private final javax.swing.JLabel wrongCityLabel = new JLabel();

    /**
     * A label indicating that the country is incorrect.
     */
    private final javax.swing.JLabel wrongCountryLabel = new JLabel();

    /**
     * A label indicating that the departure city is incorrect.
     */
    private final javax.swing.JLabel wrongDepartureCityLabel = new JLabel();

    /**
     * A label indicating that the departure date is incorrect.
     */
    private final javax.swing.JLabel wrongDepartureLabel = new JLabel();

    /**
     * A label indicating that the people limit is incorrect.
     */
    private final javax.swing.JLabel wrongLimitLabel = new JLabel();

    /**
     * A label indicating that the hotel name is incorrect.
     */
    private final javax.swing.JLabel wrongHotelNameLabel = new JLabel();

    /**
     * A label indicating that the price is incorrect.
     */
    private final javax.swing.JLabel wrongPriceLabel = new JLabel();
}