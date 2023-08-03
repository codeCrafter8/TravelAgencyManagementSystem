package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Class containing fields and methods for handling a window with functionality to display reservation details.
 */
public class TripDescription extends javax.swing.JFrame {
    /**
     * A constant representing the color blue with RGB values (102, 102, 255).
     */
    private static final Color BLUE = new Color(102, 102, 255);

    /**
     * A constant representing the dimension of the question mark button, with width 22 and height 24.
     */
    public static final Dimension QUESTION_MARK_BUTTON_DIMENSION = new Dimension(22, 24);

    /**
     * Attribute holding the trip insurance.
     */
    private String insurance;

    /**
     * Attribute being a list containing trip data.
     */
    private List<String> resData = new ArrayList<>();

    /**
     * Attribute holding the selected row number in the reservation table in the MyAccount class.
     */
    private int selectedRow;

    /**
     * Helper constructor responsible for initializing the GUI.
     */
    public TripDescription() {
        initComponents();
    }

    /**
     * Constructor responsible for initializing the GUI and relevant elements.
     *
     * @param resData     parameter being a list holding trip data.
     * @param selectedRow parameter holding the selected row number in the reservation table in the MyAccount class.
     */
    public TripDescription(List<String> resData, int selectedRow) {
        this.resData = resData;
        this.selectedRow = selectedRow;
        initComponents();
        fillLabels();
    }

    /**
     * Method filling the corresponding labels with trip data.
     */
    private void fillLabels() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        int counter = 0;
        int size = (resData.size()) / 11;
        for (int i = 0; i < size; i++) {
            if (i == selectedRow) {
                try {
                    date1 = formatter.parse(String.valueOf(resData.get(counter + 5)));
                    date2 = formatter.parse(String.valueOf(resData.get(counter + 6)));
                    daysBetween = (int) SearchEngine.getDifferenceDays(date1, date2);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                typeCountryLabel.setText(resData.get(counter + 1));
                typeCityLabel.setText(resData.get(counter + 2));
                typeDepartureCityLabel.setText(resData.get(counter + 8));
                typeDateLabel.setText("from " + resData.get(counter + 5) + " to " + resData.get(counter + 6));
                typeDaysLabel.setText(String.valueOf(daysBetween));
                typePeopleLabel.setText(resData.get(counter + 4));
                typePriceLabel.setText(resData.get(counter + 3) + " zł");
                insurance = resData.get(counter + 7);
                typeInsuranceLabel.setText(insurance);
                typeHotelNameLabel.setText(resData.get(counter + 9));
                typeHotelDescriptionLabel.setText("<html>" + resData.get(counter + 10) + "<html>");
            }
            if (size > 1)
                counter += 11;
        }
    }

    /**
     * Method initializing graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButton();
        createLayout();
    }

    /**
     * Sets the text for various labels in the user interface.
     */
    private void setLabels(){
        countryLabel.setText("Kraj:");
        cityLabel.setText("Miasto:");
        departureCityLabel.setText("Miejsce wylotu/przylotu:");
        dateLabel.setText("Data wycieczki:");
        daysLabel.setText("Liczba dni:");
        peopleLabel.setText("Liczba osób:");
        priceLabel.setText("Cena za osobę:");
        insuranceLabel.setText("Ubezpieczenie:");
        hotelNameLabel.setText("Nazwa hotelu:");
        hotelDescriptionLabel.setText("Opis hotelu:");
        typeHotelDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
    }

    /**
     * Sets the properties of the window.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Szczegóły wycieczki");
    }

    /**
     * Sets the properties of the question mark button and adds an ActionListener to it.
     */
    private void setButton(){
        questionMarkButton.setBackground(BLUE);
        questionMarkButton.setIcon(new javax.swing.ImageIcon("img\\questionMark.png"));
        questionMarkButton.setBorderPainted(false);
        questionMarkButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        questionMarkButton.setMargin(new java.awt.Insets(0, 14, 0, 14));
        questionMarkButton.setPreferredSize(QUESTION_MARK_BUTTON_DIMENSION);
        questionMarkButton.addActionListener(this::jButtonActionPerformed);
    }

    /**
     * Creates the layout of the user interface.
     */
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(hotelDescriptionLabel)
                                        .addComponent(priceLabel)
                                        .addComponent(peopleLabel)
                                        .addComponent(daysLabel)
                                        .addComponent(dateLabel)
                                        .addComponent(countryLabel)
                                        .addComponent(cityLabel)
                                        .addComponent(departureCityLabel)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(questionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(insuranceLabel))
                                        .addComponent(hotelNameLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(typeCountryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeDepartureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeDaysLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typePeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeInsuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeHotelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeHotelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(countryLabel)
                                        .addComponent(typeCountryLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cityLabel)
                                        .addComponent(typeCityLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(departureCityLabel)
                                        .addComponent(typeDepartureCityLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateLabel)
                                        .addComponent(typeDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(daysLabel)
                                        .addComponent(typeDaysLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(typePriceLabel)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(peopleLabel)
                                                        .addComponent(typePeopleLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(priceLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(questionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(typeInsuranceLabel)
                                                        .addComponent(insuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeHotelNameLabel)
                                                        .addComponent(hotelNameLabel))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(typeHotelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(hotelDescriptionLabel))
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method handling the click event of the question mark button.
     * @param evt The event triggered by clicking the button.
     */
    private void jButtonActionPerformed(ActionEvent evt) {
        if (insurance.equals("Standard"))
            JOptionPane.showMessageDialog(null, """
                Standard Insurance (price: 150 zł).

                Covers insurance for:
                medical expenses and transport up to 20,000 EUR,
                consequences of accidents,
                """, "Information", JOptionPane.INFORMATION_MESSAGE);
        if (insurance.equals("Komfort"))
            JOptionPane.showMessageDialog(null, """
                Komfort Insurance (price: 300 zł).

                Covers:
                medical expenses and transport up to 50,000 EUR, including costs related to COVID-19,
                additional benefit for quarantine in the amount of 1,000 EUR,
                consequences of accidents,
                trip interruption costs (up to 5,000 PLN),
                baggage insurance up to 2,000 PLN,
                delayed baggage delivery up to 1,000 PLN,
                rescue and search costs (up to 5,000 EUR).""", "Information", JOptionPane.INFORMATION_MESSAGE);
        if (insurance.equals("Premium"))
            JOptionPane.showMessageDialog(null, """
                Premium Insurance (price: 600 zł).

                Covers:
                medical expenses and transport up to 100,000 EUR, including costs related to COVID-19 and chronic diseases,
                additional benefit for quarantine in the amount of 1,000 EUR,
                consequences of accidents,
                trip interruption costs (up to 5,000 PLN),
                baggage insurance up to 3,000 PLN,
                delayed baggage delivery up to 1,000 PLN,
                rescue and search costs (up to 5,000 EUR).
                """, "Information", JOptionPane.INFORMATION_MESSAGE);
        if (insurance.equals("Brak"))
            JOptionPane.showMessageDialog(null, "No insurance has been purchased.", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method allowing the window to be launched.
     * @param args Arguments passed when launching the application.
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
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TripDescription().setVisible(true));
    }

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
     * A label displaying the text "Data" for the date input field.
     */
    private final JLabel dateLabel = new JLabel();

    /**
     * A label displaying the text "Długość wycieczki (w dniach)" for the trip duration input field.
     */
    private final JLabel daysLabel = new JLabel();

    /**
     * A label displaying the text "Liczba uczestników" for the number of participants input field.
     */
    private final JLabel peopleLabel = new JLabel();

    /**
     * A label displaying the text "Cena za osobę" for the price input field.
     */
    private final JLabel priceLabel = new JLabel();

    /**
     * A label displaying the text "Ubezpieczenie" for the insurance input field.
     */
    private final JLabel insuranceLabel = new JLabel();

    /**
     * A label displaying the text "Nazwa hotelu" for the hotel name input field.
     */
    private final JLabel hotelNameLabel = new JLabel();

    /**
     * A label displaying the text "Opis hotelu" for the hotel description input field.
     */
    private final JLabel hotelDescriptionLabel = new JLabel();

    /**
     * A button used for displaying additional information or help related to the input fields.
     */
    private final JButton questionMarkButton = new JButton();

    /**
     * A label for the trip city.
     */
    private final javax.swing.JLabel typeCityLabel = new JLabel();
    /**
     * A label for the trip country.
     */
    private final javax.swing.JLabel typeCountryLabel = new JLabel();
    /**
     * A label for the trip date.
     */
    private final javax.swing.JLabel typeDateLabel = new JLabel();
    /**
     * A label for the trip duration in days.
     */
    private final javax.swing.JLabel typeDaysLabel = new JLabel();
    /**
     * A label for the trip departure/arrival city.
     */
    private final javax.swing.JLabel typeDepartureCityLabel = new JLabel();
    /**
     * A label for the trip hotel description.
     */
    private final javax.swing.JLabel typeHotelDescriptionLabel = new JLabel();
    /**
     * A label for the trip hotel name.
     */
    private final javax.swing.JLabel typeHotelNameLabel = new JLabel();
    /**
     * A label for the trip insurance.
     */
    private final javax.swing.JLabel typeInsuranceLabel = new JLabel();
    /**
     * A label for the trip number of people.
     */
    private final javax.swing.JLabel typePeopleLabel = new JLabel();
    /**
     * A label for the trip price.
     */
    private final javax.swing.JLabel typePriceLabel = new JLabel();
}