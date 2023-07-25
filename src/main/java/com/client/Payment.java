package com.client;

import com.client.utils.ColorUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność opłacenia rezerwacji
 */
public class Payment extends javax.swing.JFrame {
    private static final Dimension WINDOW_DIMENSION = new Dimension(548, 640);
    /**
     * Atrybut informujący, czy została wybrana metoda opłaty
     */
    private boolean methodChoosed = false;
    /**
     * Atrybut będący id wycieczki
     */
    private int idTrip;
    /**
     * Atrybut będący wybranym ubezpieczeniem
     */
    private String insurance;
    /**
     * Atrybut będący ilością osób
     */
    private int peopleQuantity;
    /**
     * Atrybut będący id klienta
     */
    private int clientId;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final java.util.List<String> data = new ArrayList<>();
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Client
     * @param idTrip parametr będący id wycieczki
     * @param insurance parametr będący wybrnaym ubezpieczeniem
     * @param peopleQuantity parametr będący ilością osób
     */
    public Payment(Client client, int idTrip, String insurance, int peopleQuantity) {
        this.idTrip = idTrip;
        this.insurance = insurance;
        this.peopleQuantity = peopleQuantity;
        this.clientId = client.getOfferClientID();
        initComponents();
    }
    /**
     * Pomocniczy konstruktor odpowiadający za inicjalizację GUI
     */
    Payment(){initComponents();}
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
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

        paymentMethodLabel.setBackground(new java.awt.Color(210, 180, 140));
        paymentMethodLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        paymentMethodLabel.setText("Metoda płatności");
        paymentMethodLabel.setOpaque(true);

        ownerLabel.setBackground(new java.awt.Color(210, 180, 140));
        ownerLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        ownerLabel.setText("Właściciel");
        ownerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ownerLabel.setOpaque(true);

        firstNameLastNameLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        firstNameLastNameLabel.setText("Imie i nazwisko:");

        numberLabel.setBackground(new java.awt.Color(210, 180, 140));
        numberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        numberLabel.setText("Numer");
        numberLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        numberLabel.setOpaque(true);

        cardNumberLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cardNumberLabel.setText("Numer karty kredytowej:");

        cvvLabel.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cvvLabel.setText("CVV:");

        expirationDateLabel.setBackground(new java.awt.Color(210, 180, 140));
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
     * Metoda obsługująca kliknięcie przycisku Zapłać
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void payButtonActionPerformed(ActionEvent evt) {
        if(!methodChoosed)
            errorLabel.setText("Nie wybrano metody płatności.");
        else if(!Validation.isFirstNameAndLastNameValid(firstNameLastNameTextField.getText())) {
            errorLabel.setText("Nieprawidłowe imię i nazwisko.");
        }
        else if(!Validation.isCreditCardNumberValid(cardNumberTextField.getText())) {
            errorLabel.setText("Nieprawidłowy numer karty kredytowej.");
        }
        else if(!Validation.isCvvValid(cvvData.getText())) {
            errorLabel.setText("Nieprawidłowy numer cvv.");
        }
        else if(!Validation.isMonthValid(monthTextField.getText())) {
            errorLabel.setText("Nieprawidłowy miesiąc ważności karty kredytowej.");
        }
        else if(!Validation.isYearValid(yearData.getText())) {
            errorLabel.setText("Nieprawidłowy rok ważności karty kredytowej.");
        }
        else {
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
     * Metoda obsługująca kliknięcie przycisku discover
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void discoverActionPerformed(ActionEvent evt) {
        methodChoosed = true;
        errorLabel.setText("");
        discoverButton.setBorder(new LineBorder(Color.BLUE, 3));
        visaButton.setBorder(null);
        mastercardButton.setBorder(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku visa
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void visaActionPerformed(ActionEvent evt) {
        methodChoosed = true;
        errorLabel.setText("");
        visaButton.setBorder(new LineBorder(Color.BLUE, 3));
        mastercardButton.setBorder(null);
        discoverButton.setBorder(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku mastercard
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void mastercardActionPerformed(ActionEvent evt) {
        methodChoosed = true;
        errorLabel.setText("");
        mastercardButton.setBorder(new LineBorder(Color.BLUE, 3));
        visaButton.setBorder(null);
        discoverButton.setBorder(null);
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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Payment().setVisible(true));
    }
    //GUI variables
    /**
     * Pole do wprowadzenia numeru cvv
     */
    private final javax.swing.JTextField cvvData = new JTextField();
    /**
     * Przycisk umożliwiający wybór discover
     */
    private final javax.swing.JButton discoverButton = new JButton();
    /**
     * Pole do wprowadzenia imienia i nazwiska
     */
    private final javax.swing.JTextField firstNameLastNameTextField = new JTextField();
    /**
     * Przycisk umożliwiający wybór mastercard
     */
    private final javax.swing.JButton mastercardButton = new JButton();
    /**
     * Etykieta z błędem operacji opłaty
     */
    private final javax.swing.JLabel errorLabel = new JLabel();
    /**
     * Pole do wprowadzenia miesiąca ważności karty kredytowej
     */
    private final javax.swing.JTextField monthTextField = new JTextField();
    /**
     * Pole do wprowadzenia numeru karty kredytowej
     */
    private final javax.swing.JTextField cardNumberTextField = new JTextField();
    /**
     * Pole do wprowadzenia roku ważności karty kredytowej
     */
    private final javax.swing.JTextField yearData = new JTextField();
    /**
     * Przycisk umożliwiający wybór visa
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