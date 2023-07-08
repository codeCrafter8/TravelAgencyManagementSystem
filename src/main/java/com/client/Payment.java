package com.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność opłacenia rezerwacji
 */
public class Payment extends javax.swing.JFrame {
    /**
     * Pole do wprowadzenia numeru cvv
     */
    private javax.swing.JTextField cvvData;
    /**
     * Przycisk umożliwiający wybór discover
     */
    private javax.swing.JButton discover;
    /**
     * Pole do wprowadzenia imienia i nazwiska
     */
    private javax.swing.JTextField firstNameLastNameData;
    /**
     * Przycisk umożliwiający wybór mastercard
     */
    private javax.swing.JButton mastercard;
    /**
     * Etykieta z błędem operacji opłaty
     */
    private javax.swing.JLabel mistakeSpace;
    /**
     * Pole do wprowadzenia miesiąca ważności karty kredytowej
     */
    private javax.swing.JTextField monthData;
    /**
     * Pole do wprowadzenia numeru karty kredytowej
     */
    private javax.swing.JTextField cardNumberData;
    /**
     * Pole do wprowadzenia roku ważności karty kredytowej
     */
    private javax.swing.JTextField yearData;
    /**
     * Przycisk umożliwiający wybór visa
     */
    private javax.swing.JButton visa;
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
        mistakeSpace = new javax.swing.JLabel();
        JLabel paymentMethod = new JLabel();
        JPanel choicePanel = new JPanel();
        mastercard = new javax.swing.JButton();
        visa = new javax.swing.JButton();
        discover = new javax.swing.JButton();
        JLabel owner = new JLabel();
        JPanel firstNameLastNamePanel = new JPanel();
        JLabel firstNameLastName = new JLabel();
        firstNameLastNameData = new javax.swing.JTextField();
        JLabel number = new JLabel();
        JPanel numberPanel = new JPanel();
        JLabel cardNumber = new JLabel();
        cardNumberData = new javax.swing.JTextField();
        JLabel cvv = new JLabel();
        cvvData = new javax.swing.JTextField();
        JLabel validityDate = new JLabel();
        JPanel validityDatePanel = new JPanel();
        JLabel month = new JLabel();
        monthData = new javax.swing.JTextField();
        JLabel year = new JLabel();
        yearData = new javax.swing.JTextField();
        JButton payButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(450, 124));
        setPreferredSize(new java.awt.Dimension(548, 640));

        mistakeSpace.setBackground(new java.awt.Color(255, 255, 255));
        mistakeSpace.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        mistakeSpace.setText("Miejsce na informacje o błędzie");
        mistakeSpace.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        paymentMethod.setBackground(new java.awt.Color(210, 180, 140));
        paymentMethod.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        paymentMethod.setText("Metoda płatności");
        paymentMethod.setOpaque(true);

        choicePanel.setBackground(new java.awt.Color(151, 123, 92));

        mastercard.setIcon(new javax.swing.ImageIcon("img\\mastercard.png"));
        mastercard.addActionListener(this::mastercardActionPerformed);
        mastercard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        visa.setIcon(new javax.swing.ImageIcon("img\\visa.png"));
        visa.addActionListener(this::visaActionPerformed);
        visa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        discover.setIcon(new javax.swing.ImageIcon("img\\discover-logo.png"));
        discover.addActionListener(this::discoverActionPerformed);
        discover.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        setTitle("Płatność kartą kredytową");
        mastercard.setFocusable(false);
        visa.setFocusable(false);
        discover.setFocusable(false);
        mistakeSpace.setForeground(Color.RED);
        mistakeSpace.setText("");

        javax.swing.GroupLayout choicePanelLayout = new javax.swing.GroupLayout(choicePanel);
        choicePanel.setLayout(choicePanelLayout);
        choicePanelLayout.setHorizontalGroup(
            choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choicePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mastercard, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(visa, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(discover, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        choicePanelLayout.setVerticalGroup(
            choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(choicePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(choicePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(mastercard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(visa, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(discover, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        owner.setBackground(new java.awt.Color(210, 180, 140));
        owner.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        owner.setText("Właściciel");
        owner.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        owner.setOpaque(true);

        firstNameLastNamePanel.setBackground(new java.awt.Color(151, 123, 92));

        firstNameLastName.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        firstNameLastName.setText("Imie i nazwisko:");

        firstNameLastNameData.setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout firstNameLastNamePanelLayout = new javax.swing.GroupLayout(firstNameLastNamePanel);
        firstNameLastNamePanel.setLayout(firstNameLastNamePanelLayout);
        firstNameLastNamePanelLayout.setHorizontalGroup(
            firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                        .addComponent(firstNameLastName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(firstNameLastNameData))
                .addContainerGap())
        );
        firstNameLastNamePanelLayout.setVerticalGroup(
            firstNameLastNamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstNameLastNamePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(firstNameLastName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(firstNameLastNameData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        number.setBackground(new java.awt.Color(210, 180, 140));
        number.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        number.setText("Numer");
        number.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        number.setOpaque(true);

        numberPanel.setBackground(new java.awt.Color(151, 123, 92));

        cardNumber.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cardNumber.setText("Numer karty kredytowej:");

        cardNumberData.setBackground(new java.awt.Color(242, 242, 242));

        cvv.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        cvv.setText("CVV:");

        javax.swing.GroupLayout numberPanelLayout = new javax.swing.GroupLayout(numberPanel);
        numberPanel.setLayout(numberPanelLayout);
        numberPanelLayout.setHorizontalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cardNumberData, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardNumber))
                .addGap(18, 18, 18)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cvv)
                    .addComponent(cvvData, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        numberPanelLayout.setVerticalGroup(
            numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumber)
                    .addComponent(cvv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(numberPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cvvData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        validityDate.setBackground(new java.awt.Color(210, 180, 140));
        validityDate.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        validityDate.setText("Termin ważności");
        validityDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        validityDate.setOpaque(true);

        validityDatePanel.setBackground(new java.awt.Color(151, 123, 92));

        month.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        month.setText("Miesiąc:");

        monthData.setBackground(new java.awt.Color(242, 242, 242));

        year.setFont(new java.awt.Font("sansserif", Font.BOLD, 12));
        year.setText("Rok:");

        javax.swing.GroupLayout validityDatePanelLayout = new javax.swing.GroupLayout(validityDatePanel);
        validityDatePanel.setLayout(validityDatePanelLayout);
        validityDatePanelLayout.setHorizontalGroup(
            validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(validityDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(monthData, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(month))
                .addGap(47, 47, 47)
                .addGroup(validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(year)
                    .addComponent(yearData, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        validityDatePanelLayout.setVerticalGroup(
            validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(validityDatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(month)
                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(validityDatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        payButton.setIcon(new javax.swing.ImageIcon("img\\pay.png"));
        payButton.addActionListener(this::payButtonActionPerformed);
        payButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mistakeSpace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(validityDatePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(mistakeSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(paymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(choicePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(owner, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(firstNameLastNamePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(number, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(numberPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(validityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validityDatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(payButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        mistakeSpace.getAccessibleContext().setAccessibleName("Miejs");

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku Zapłać
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void payButtonActionPerformed(ActionEvent evt) {
        if(!methodChoosed)
            mistakeSpace.setText("Nie wybrano metody płatności.");
        else if(!Validation.isFirstNameAndLastNameValid(firstNameLastNameData.getText())) {
            mistakeSpace.setText("Nieprawidłowe imię i nazwisko.");
        }
        else if(!Validation.isCreditCardNumberValid(cardNumberData.getText())) {
            mistakeSpace.setText("Nieprawidłowy numer karty kredytowej.");
        }
        else if(!Validation.isCvvValid(cvvData.getText())) {
            mistakeSpace.setText("Nieprawidłowy numer cvv.");
        }
        else if(!Validation.isMonthValid(monthData.getText())) {
            mistakeSpace.setText("Nieprawidłowy miesiąc ważności karty kredytowej.");
        }
        else if(!Validation.isYearValid(yearData.getText())) {
            mistakeSpace.setText("Nieprawidłowy rok ważności karty kredytowej.");
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
        mistakeSpace.setText("");
        discover.setBorder(new LineBorder(Color.BLUE, 3));
        visa.setBorder(null);
        mastercard.setBorder(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku visa
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void visaActionPerformed(ActionEvent evt) {
        methodChoosed = true;
        mistakeSpace.setText("");
        visa.setBorder(new LineBorder(Color.BLUE, 3));
        mastercard.setBorder(null);
        discover.setBorder(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku mastercard
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void mastercardActionPerformed(ActionEvent evt) {
        methodChoosed = true;
        mistakeSpace.setText("");
        mastercard.setBorder(new LineBorder(Color.BLUE, 3));
        visa.setBorder(null);
        discover.setBorder(null);
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
}