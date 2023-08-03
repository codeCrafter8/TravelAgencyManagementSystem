package com.client;

import com.client.utils.ColorUtils;
import com.client.validation.CreditCardValidator;
import com.client.validation.DateValidator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * Class containing fields and methods for handling the payment window functionality.
 */
public class Payment extends javax.swing.JFrame {
    private static final Dimension WINDOW_DIMENSION = new Dimension(548, 640);
    /**
     * Attribute indicating whether a payment method has been chosen.
     */
    private boolean methodChosen = false;
    /**
     * Attribute representing the trip ID.
     */
    private int idTrip;
    /**
     * Attribute representing the selected insurance.
     */
    private String insurance;
    /**
     * Attribute representing the number of people.
     */
    private int peopleQuantity;
    /**
     * Attribute representing the client ID.
     */
    private int clientId;
    /**
     * Attribute representing the list storing data passed to the Client class.
     */
    private final java.util.List<String> data = new ArrayList<>();
    /**
     * Constructor responsible for initializing the GUI and related elements.
     * @param client Parameter holding an object of the Client class.
     * @param idTrip Parameter representing the trip ID.
     * @param insurance Parameter representing the selected insurance.
     * @param peopleQuantity Parameter representing the number of people.
     */
    public Payment(Client client, int idTrip, String insurance, int peopleQuantity) {
        this.idTrip = idTrip;
        this.insurance = insurance;
        this.peopleQuantity = peopleQuantity;
        this.clientId = client.getOfferClientID();
        initComponents();
    }
    /**
     * Helper constructor responsible for initializing the GUI.
     */
    Payment() { initComponents(); }
    /**
     * Method initializing the graphic components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButtons();
        setTextFields();
        createChoicePanel();
        createFirstNameLastNamePanel();
        createNumberPanel();
        createExpirationDatePanel();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(Color.WHITE);
        setPreferredSize(WINDOW_DIMENSION);
        setTitle("Płatność kartą kredytową");
    }
    private void setLabels(){
        errorLabel.setBackground(Color.WHITE);
        errorLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        errorLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        errorLabel.setForeground(Color.RED);
        errorLabel.setText("");

        paymentMethodLabel.setBackground(ColorUtils.BEIGE);
        paymentMethodLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        paymentMethodLabel.setText("Metoda płatności");
        paymentMethodLabel.setOpaque(true);

        ownerLabel.setBackground(ColorUtils.BEIGE);
        ownerLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        ownerLabel.setText("Właściciel");
        ownerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ownerLabel.setOpaque(true);

        firstNameLastNameLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        firstNameLastNameLabel.setText("Imie i nazwisko:");

        numberLabel.setBackground(ColorUtils.BEIGE);
        numberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        numberLabel.setText("Numer");
        numberLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        numberLabel.setOpaque(true);

        cardNumberLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cardNumberLabel.setText("Numer karty kredytowej:");

        cvvLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cvvLabel.setText("CVV:");

        expirationDateLabel.setBackground(ColorUtils.BEIGE);
        expirationDateLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        expirationDateLabel.setText("Termin ważności");
        expirationDateLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        expirationDateLabel.setOpaque(true);

        monthLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        monthLabel.setText("Miesiąc:");

        yearLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        yearLabel.setText("Rok:");
    }
    private void setButtons(){
        mastercardButton.setIcon(new javax.swing.ImageIcon("img\\mastercard.png"));
        mastercardButton.addActionListener(this::mastercardActionPerformed);
        mastercardButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        visaButton.setIcon(new javax.swing.ImageIcon("img\\visa.png"));
        visaButton.addActionListener(this::visaActionPerformed);
        visaButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        discoverButton.setIcon(new javax.swing.ImageIcon("img\\discover-logo.png"));
        discoverButton.addActionListener(this::discoverActionPerformed);
        discoverButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        mastercardButton.setFocusable(false);
        visaButton.setFocusable(false);
        discoverButton.setFocusable(false);

        payButton.setIcon(new javax.swing.ImageIcon("img\\pay.png"));
        payButton.addActionListener(this::payButtonActionPerformed);
        payButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    private void createChoicePanel(){
        choicePanel.setBackground(ColorUtils.LIGHT_BROWN);
        javax.swing.GroupLayout choicePanelLayout = new javax.swing.GroupLayout(choicePanel);
        choicePanel.setLayout(choicePanelLayout);
        choicePanelLayout.setHorizontalGroup(
                choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(choicePanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mastercardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(visaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(discoverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41))
        );
        choicePanelLayout.setVerticalGroup(
                choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(choicePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(mastercardButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(visaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(discoverButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void setTextFields(){
        firstNameLastNameTextField.setBackground(ColorUtils.MILK);
        cardNumberTextField.setBackground(ColorUtils.MILK);
        monthTextField.setBackground(ColorUtils.MILK);
    }
    private void createFirstNameLastNamePanel(){
        firstNameLastNamePanel.setBackground(ColorUtils.LIGHT_BROWN);
        javax.swing.GroupLayout firstNameLastNamePanelLayout = new javax.swing.GroupLayout(firstNameLastNamePanel);
        firstNameLastNamePanel.setLayout(firstNameLastNamePanelLayout);
        firstNameLastNamePanelLayout.setHorizontalGroup(
                firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                                                .addComponent(firstNameLastNameLabel)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(firstNameLastNameTextField))
                                .addContainerGap())
        );
        firstNameLastNamePanelLayout.setVerticalGroup(
                firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(firstNameLastNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(firstNameLastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(8, Short.MAX_VALUE))
        );
    }
    private void createNumberPanel(){
        numberPanel.setBackground(ColorUtils.LIGHT_BROWN);
        javax.swing.GroupLayout numberPanelLayout = new javax.swing.GroupLayout(numberPanel);
        numberPanel.setLayout(numberPanelLayout);
        numberPanelLayout.setHorizontalGroup(
                numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cardNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cardNumberLabel))
                                .addGap(18, 18, 18)
                                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cvvLabel)
                                        .addComponent(cvvData, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        numberPanelLayout.setVerticalGroup(
                numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(numberPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardNumberLabel)
                                        .addComponent(cvvLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cardNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cvvData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(8, Short.MAX_VALUE))
        );
    }
    private void createExpirationDatePanel(){
        expirationDatePanel.setBackground(ColorUtils.LIGHT_BROWN);
        javax.swing.GroupLayout expirationDatePanelLayout = new javax.swing.GroupLayout(expirationDatePanel);
        expirationDatePanel.setLayout(expirationDatePanelLayout);
        expirationDatePanelLayout.setHorizontalGroup(
                expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(expirationDatePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(monthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(monthLabel))
                                .addGap(47, 47, 47)
                                .addGroup(expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(yearLabel)
                                        .addComponent(yearData, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        expirationDatePanelLayout.setVerticalGroup(
                expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(expirationDatePanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(monthLabel)
                                        .addComponent(yearLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(expirationDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(monthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yearData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(8, Short.MAX_VALUE))
        );
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(errorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(expirationDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(expirationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(numberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(ownerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(paymentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(choicePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(firstNameLastNamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(221, 221, 221)
                                                .addComponent(payButton)))
                                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(paymentMethodLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(choicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ownerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(firstNameLastNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(numberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(numberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(expirationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(expirationDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(payButton)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method handling the "Pay" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void payButtonActionPerformed(ActionEvent evt) {
        if (!methodChosen) {
            errorLabel.setText("No payment method selected.");
        } else if (!CreditCardValidator.isFirstNameAndLastNameValid(firstNameLastNameTextField.getText())) {
            errorLabel.setText("Invalid first name and last name.");
        } else if (!CreditCardValidator.isCreditCardNumberValid(cardNumberTextField.getText())) {
            errorLabel.setText("Invalid credit card number.");
        } else if (!CreditCardValidator.isCvvValid(cvvData.getText())) {
            errorLabel.setText("Invalid CVV number.");
        } else if (!DateValidator.isMonthValid(monthTextField.getText())) {
            errorLabel.setText("Invalid credit card expiration month.");
        } else if (!DateValidator.isYearValid(yearData.getText())) {
            errorLabel.setText("Invalid credit card expiration year.");
        } else {
            data.clear();
            data.add("addReservation");
            data.add(Integer.toString(idTrip));
            data.add(Integer.toString(clientId));
            data.add(Integer.toString(peopleQuantity));
            data.add(insurance);
            new Client(data);
            data.clear();
            dispose();
        }
    }
    /**
     * Method handling the "Discover" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void discoverActionPerformed(ActionEvent evt) {
        methodChosen = true;
        errorLabel.setText("");
        discoverButton.setBorder(new LineBorder(Color.BLUE, 3));
        visaButton.setBorder(null);
        mastercardButton.setBorder(null);
    }
    /**
     * Method handling the "Visa" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void visaActionPerformed(ActionEvent evt) {
        methodChosen = true;
        errorLabel.setText("");
        visaButton.setBorder(new LineBorder(Color.BLUE, 3));
        mastercardButton.setBorder(null);
        discoverButton.setBorder(null);
    }
    /**
     * Method handling the "Mastercard" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void mastercardActionPerformed(ActionEvent evt) {
        methodChosen = true;
        errorLabel.setText("");
        mastercardButton.setBorder(new LineBorder(Color.BLUE, 3));
        visaButton.setBorder(null);
        discoverButton.setBorder(null);
    }
    /**
     * Method allowing the window to be launched.
     * @param args Arguments passed when running the application.
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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Payment().setVisible(true));
    }
    //GUI variables
    /**
     * Text field for entering the CVV number.
     */
    private final javax.swing.JTextField cvvData = new JTextField();
    /**
     * Button allowing the "Discover" selection.
     */
    private final javax.swing.JButton discoverButton = new JButton();
    /**
     * Text field for entering the first name and last name.
     */
    private final javax.swing.JTextField firstNameLastNameTextField = new JTextField();
    /**
     * Button allowing the "Mastercard" selection.
     */
    private final javax.swing.JButton mastercardButton = new JButton();
    /**
     * Label with payment operation error.
     */
    private final javax.swing.JLabel errorLabel = new JLabel();
    /**
     * Text field for entering the credit card expiration month.
     */
    private final javax.swing.JTextField monthTextField = new JTextField();
    /**
     * Text field for entering the credit card number.
     */
    private final javax.swing.JTextField cardNumberTextField = new JTextField();
    /**
     * Text field for entering the credit card expiration year.
     */
    private final javax.swing.JTextField yearData = new JTextField();
    /**
     * Button allowing the "Visa" selection.
     */
    private final javax.swing.JButton visaButton = new JButton();
    private final JLabel paymentMethodLabel = new JLabel();
    private final JPanel choicePanel = new JPanel();
    private final JLabel ownerLabel = new JLabel();
    private final JPanel firstNameLastNamePanel = new JPanel();
    private final JLabel firstNameLastNameLabel = new JLabel();
    private final JLabel numberLabel = new JLabel();
    private final JPanel numberPanel = new JPanel();
    private final JLabel cardNumberLabel = new JLabel();
    private final JLabel cvvLabel = new JLabel();
    private final JLabel expirationDateLabel = new JLabel();
    private final JPanel expirationDatePanel = new JPanel();
    private final JLabel monthLabel = new JLabel();
    private final JLabel yearLabel = new JLabel();
    private final JButton payButton = new JButton();
}