/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//package oferty;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Dodaj_wycieczke extends javax.swing.JFrame {
    private static final String countryPattern = "^[A-Za-z\\s]+$";
    private static final Pattern compiledCountryPattern = Pattern.compile(countryPattern);
    List<String> departure_cities = new ArrayList<String>();
    private static final String peopleLimitPattern = "^[1-6]$";
    private static final Pattern compiledPeopleLimitPattern = Pattern.compile(peopleLimitPattern);
    private static final String pricePattern = "^[1-9]\\d*(\\.\\d{1,2})?$";
    private static final Pattern compiledPricePattern = Pattern.compile(pricePattern);
    private static final String hotelNamePattern = "^[a-zA-Z0-9\\s]{1,50}$";
    private static final Pattern compiledHotelNamePattern = Pattern.compile(hotelNamePattern);
    public static String country, city, departureCity, price, peopleLimit, hotelName;
    public static String departure, arrival;
    /**
     * Creates new form Dodaj_wycieczke
     */
    public Dodaj_wycieczke() {
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        departure_cities.add("Krakow");
        departure_cities.add("Warszawa");
        departure_cities.add("Wroclaw");
        departure_cities.add("Katowice");
        departure_cities.add("Gdansk");
        validCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validCountryLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validDepartureCityLabel.setMinimumSize(new java.awt.Dimension(350, 16));
        validPriceLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        validNameHotelLabel.setMinimumSize(new java.awt.Dimension(200, 16));
        validDepartureLabel.setMinimumSize(new java.awt.Dimension(100, 16));
        validArrivalLabel.setMinimumSize(new java.awt.Dimension(100, 16));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regPanel = new javax.swing.JPanel();
        createLabel = new javax.swing.JLabel();
        countryLabel = new javax.swing.JLabel();
        countryTextField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        departure_cityLabel = new javax.swing.JLabel();
        departure_cityField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        priceField = new javax.swing.JTextField();
        LimitLabel = new javax.swing.JLabel();
        LimitTextField = new javax.swing.JTextField();
        departureLabel = new javax.swing.JLabel();
        arrivalLabel = new javax.swing.JLabel();
        LimitLabel4 = new javax.swing.JLabel();
        nameHotelTextField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        departureDateChooser = new com.toedter.calendar.JDateChooser();
        arrivalDateChooser = new com.toedter.calendar.JDateChooser();
        validCountryLabel = new javax.swing.JLabel();
        validCityLabel = new javax.swing.JLabel();
        validDepartureCityLabel = new javax.swing.JLabel();
        validPriceLabel = new javax.swing.JLabel();
        validLimitLabel = new javax.swing.JLabel();
        validDepartureLabel = new javax.swing.JLabel();
        validCountryLabel6 = new javax.swing.JLabel();
        validArrivalLabel = new javax.swing.JLabel();
        validNameHotelLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dodaj wycieczkę");
        setMaximumSize(new java.awt.Dimension(1024, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));

        regPanel.setBackground(new java.awt.Color(247, 233, 201));
        regPanel.setPreferredSize(new java.awt.Dimension(450, 620));

        createLabel.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        createLabel.setText("Dodaj wycieczkę");

        countryLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        countryLabel.setText("Kraj");

        cityLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cityLabel.setText("Miasto");

        departure_cityLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        departure_cityLabel.setText("Miasto wylotu/przylotu");

        departure_cityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departure_cityFieldActionPerformed(evt);
            }
        });

        priceLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        priceLabel.setText("Cena za osobę");

        priceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFieldActionPerformed(evt);
            }
        });

        LimitLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        LimitLabel.setText("Limit osób");

        LimitTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LimitTextFieldActionPerformed(evt);
            }
        });

        departureLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        departureLabel.setText("Wyjazd");

        arrivalLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        arrivalLabel.setText("Przyjazd");

        LimitLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        LimitLabel4.setText("Nazwa hotelu");

        nameHotelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameHotelTextFieldActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(189, 165, 111));
        submitButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        submitButton.setText("Dodaj wycieczkę");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        //nowe
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(new java.awt.Color(189, 165, 111));
        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new java.awt.Dimension(116, 27));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        //nowe
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
                            .addComponent(LimitLabel4)
                            .addComponent(validPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(validDepartureCityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nameHotelTextField)
                                .addComponent(countryTextField)
                                .addComponent(cityTextField)
                                .addComponent(departure_cityField, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                                .addComponent(countryLabel)
                                .addComponent(cityLabel)
                                .addComponent(departure_cityLabel)
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
                            .addComponent(LimitLabel)
                            .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(arrivalDateChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(arrivalLabel))
                            .addComponent(LimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(departure_cityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departure_cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validDepartureCityLabel)
                .addGap(2, 2, 2)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(LimitLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void departure_cityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departure_cityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_departure_cityFieldActionPerformed

    private void priceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceFieldActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
       //nowe
        boolean countryCorrect = countryIsValid();
        boolean cityCorrect = cityIsValid();
        boolean departureCityCorrect = departureCityIsValid();
        boolean priceCorrect = priceIsValid();
        boolean peopleLimitCorrect = peopleLimitIsValid();
        boolean hotelNameCorrect = hotelNameIsValid();
        boolean departureCorrect = departureIsValid();
        boolean arrivalCorrect = arrivalIsValid();
        if(countryCorrect && cityCorrect && departureCityCorrect && priceCorrect && peopleLimitCorrect && hotelNameCorrect && departureCorrect && arrivalCorrect) {
            country = countryTextField.getText();
            city = cityTextField.getText();
            departureCity = departure_cityField.getText();
            price = priceField.getText();
            peopleLimit = LimitTextField.getText();
            hotelName = nameHotelTextField.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date arrivalDate = arrivalDateChooser.getDate();
            arrival = dateFormat.format(arrivalDate);
            Date departureDate = departureDateChooser.getDate();
            departure = dateFormat.format(departureDate);
            Client.operate("addTrip");
            dispose();
            new Wycieczki().setVisible(true);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private boolean departureIsValid() {
        if(departureDateChooser.getDate() == null) {
            validDepartureLabel.setText("Pole wymagane.");
            return false;
        }
        validDepartureLabel.setText("");
        return true;
    }
    private boolean arrivalIsValid() {
        if(arrivalDateChooser.getDate() == null) {
            validArrivalLabel.setText("Pole wymagane.");
            return false;
        }
        validArrivalLabel.setText("");
        return true;
    }

    private boolean cityIsValid() {
        if(cityTextField.getText().isEmpty()) {
            validCityLabel.setText("Pole wymagane.");
            return false;
        }
        if(compiledCountryPattern.matcher(cityTextField.getText()).matches()) {
            validCityLabel.setText("");
            return true;
        }
        else {
            validCityLabel.setText("Sprawdź czy podane miasto jest poprawne.");
            return false;
        }
    }

    private boolean departureCityIsValid() {
        if(departure_cityField.getText().isEmpty()) {
            validDepartureCityLabel.setText("Pole wymagane.");
            return false;
        }
        if(departure_cities.contains(departure_cityField.getText())) {
            validDepartureCityLabel.setText("");
            return true;
        }
        else {
            validDepartureCityLabel.setText("Nie mozna leciec z tego miasta.");
            return false;
        }
    }

    private boolean priceIsValid() {
        if(priceField.getText().isEmpty()) {
            validPriceLabel.setText("Pole wymagane.");
            return false;
        }
        if(compiledPricePattern.matcher(priceField.getText()).matches()) {
            validPriceLabel.setText("");
            return true;
        }
        else {
            validPriceLabel.setText("Zły format.");
            return false;
        }
    }

    private boolean peopleLimitIsValid() {
        if(LimitTextField.getText().isEmpty()) {
            validLimitLabel.setText("Pole wymagane.");
            return false;
        }
        if(compiledPeopleLimitPattern.matcher(LimitTextField.getText()).matches()) {
            validLimitLabel.setText("");
            return true;
        }
        else {
            validLimitLabel.setText("Zły format.");
            return false;
        }
    }
    private boolean hotelNameIsValid() {
        if(nameHotelTextField.getText().isEmpty()) {
            validNameHotelLabel.setText("Pole wymagane.");
            return false;
        }
        if(compiledHotelNamePattern.matcher(nameHotelTextField.getText()).matches()) {
            validNameHotelLabel.setText("");
            return true;
        }
        else {
            validNameHotelLabel.setText("Sprawdź czy podana nazwa hotelu jest poprawna.");
            return false;
        }
    }

    private boolean countryIsValid() {
        if(countryTextField.getText().isEmpty()) {
            validCountryLabel.setText("Pole wymagane.");
            return false;
        }
        if(compiledCountryPattern.matcher(countryTextField.getText()).matches()) {
            validCountryLabel.setText("");
            return true;
        }
        else {
            validCountryLabel.setText("Sprawdź czy podany kraj jest poprawny.");
            return false;
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
        new Wycieczki().setVisible(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void LimitTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LimitTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LimitTextFieldActionPerformed

    private void nameHotelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameHotelTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameHotelTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dodaj_wycieczke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dodaj_wycieczke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dodaj_wycieczke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dodaj_wycieczke.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dodaj_wycieczke().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LimitLabel;
    private javax.swing.JLabel LimitLabel4;
    private javax.swing.JTextField LimitTextField;
    private com.toedter.calendar.JDateChooser arrivalDateChooser;
    private javax.swing.JLabel arrivalLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JTextField countryTextField;
    private javax.swing.JLabel createLabel;
    private com.toedter.calendar.JDateChooser departureDateChooser;
    private javax.swing.JLabel departureLabel;
    private javax.swing.JTextField departure_cityField;
    private javax.swing.JLabel departure_cityLabel;
    private javax.swing.JTextField nameHotelTextField;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JPanel regPanel;
    private javax.swing.JButton submitButton;
    private javax.swing.JLabel validArrivalLabel;
    private javax.swing.JLabel validCityLabel;
    private javax.swing.JLabel validCountryLabel;
    private javax.swing.JLabel validCountryLabel6;
    private javax.swing.JLabel validDepartureCityLabel;
    private javax.swing.JLabel validDepartureLabel;
    private javax.swing.JLabel validLimitLabel;
    private javax.swing.JLabel validNameHotelLabel;
    private javax.swing.JLabel validPriceLabel;
    // End of variables declaration//GEN-END:variables
}
