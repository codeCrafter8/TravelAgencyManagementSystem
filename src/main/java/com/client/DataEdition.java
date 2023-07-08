package com.client;

import com.server.LogsClients;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność edycji danych przez klienta
 */
class DataEdition extends javax.swing.JFrame {
    /**
     * Pole do wprowadzenia emailu przez klienta
     */
    private javax.swing.JTextField emailTextField;
    /**
     * Pole do wprowadzenia imienia przez klienta
     */
    private javax.swing.JTextField firstNameTextField;
    /**
     * Pole do wprowadzenia nazwiska przez klienta
     */
    private javax.swing.JTextField lastNameTextField;
    /**
     * Pole do wprowadzenia numeru telefonu przez klienta
     */
    private javax.swing.JTextField phoneNumberField;
    /**
     * Etykieta informująca, że email klienta jest niepoprawny
     */
    private javax.swing.JLabel validEmailLabel;
    /**
     * Etykieta informująca, że imię klienta jest niepoprawne
     */
    private javax.swing.JLabel validFirstNameLabel;
    /**
     * Etykieta informująca, że nazwisko klienta jest niepoprawne
     */
    private javax.swing.JLabel validLastNameLabel;
    /**
     * Etykieta informująca, że numer telefonu klienta jest niepoprawny
     */
    private javax.swing.JLabel validPhoneNumberLabel;
    /**
     * Atrybut określający, czy imię klienta jest poprawne
     */
    private boolean firstNameCorrect;
    /**
     * Atrybut określający, czy email klienta jest poprawny
     */
    private boolean emailCorrect;
    /**
     * Atrybut określający, czy numer telefonu klienta jest poprawny
     */
    private boolean phoneNumberCorrect;
    /**
     * Atrybut określający, czy nazwisko klienta jest poprawne
     */
    private boolean lastNameCorrect;
    /**
     * Atrybut przechowujący imię klienta, którego dane są edytowane
     */
    private String firstName;
    /**
     * Atrybut przechowujący nazwisko klienta, którego dane są edytowane
     */
    private String lastName;
    /**
     * Atrybut przechowujący email klienta, którego dane są edytowane
     */
    private String email;
    /**
     * Atrybut przechowujący numer telefonu klienta, którego dane są edytowane
     */
    private String phoneNumber;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut przechowujący id klienta, którego dane są edytowane
     */
    private final String clientId;
    /**
     * Atrybut będący listą przechowującą dane klienta
     */
    private final List<String> clientData = new ArrayList<>();
    /**
     * Atrybut będący obiektem klasy Client
     */
    private final Client client;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich atrybutów
     * @param clientData parametr będący listą przechowującą dane klienta
     * @param client parametr przechowujący obiekt klasy Client
     */
    public DataEdition(List<String> clientData, Client client) {
        this.client = client;
        this.clientData.addAll(clientData);
        this.clientId = clientData.get(4);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    new MyAccount(client).setVisible(true);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsClients("DataEdition", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        fillTextFields();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        javax.swing.JPanel regPanel = new javax.swing.JPanel();
        javax.swing.JLabel createLabel = new javax.swing.JLabel();
        javax.swing.JLabel firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        javax.swing.JLabel lastNameLabel = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        javax.swing.JLabel emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        javax.swing.JLabel phoneNumberLabel = new javax.swing.JLabel();
        phoneNumberField = new javax.swing.JTextField();
        javax.swing.JButton submitButton = new javax.swing.JButton();
        validFirstNameLabel = new javax.swing.JLabel();
        validLastNameLabel = new javax.swing.JLabel();
        validEmailLabel = new javax.swing.JLabel();
        validPhoneNumberLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edytuj dane");
        setPreferredSize(new java.awt.Dimension(449, 510));
        setResizable(false);

        regPanel.setPreferredSize(new java.awt.Dimension(450, 550));

        createLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 24));
        createLabel.setText("Edytuj dane");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        firstNameLabel.setText("Imię");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        lastNameLabel.setText("Nazwisko");

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        phoneNumberLabel.setText("Numer telefonu");

        submitButton.setBackground(new java.awt.Color(151, 123, 92));
        submitButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("Zatwierdź");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        validFirstNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        validFirstNameLabel.setMaximumSize(new java.awt.Dimension(363, 16));
        validFirstNameLabel.setMinimumSize(new java.awt.Dimension(363, 16));
        validFirstNameLabel.setPreferredSize(new java.awt.Dimension(363, 16));

        validLastNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        validLastNameLabel.setMaximumSize(new java.awt.Dimension(363, 16));
        validLastNameLabel.setMinimumSize(new java.awt.Dimension(363, 16));
        validLastNameLabel.setPreferredSize(new java.awt.Dimension(363, 16));

        validEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        validEmailLabel.setMaximumSize(new java.awt.Dimension(363, 16));
        validEmailLabel.setMinimumSize(new java.awt.Dimension(363, 16));
        validEmailLabel.setPreferredSize(new java.awt.Dimension(363, 16));

        validPhoneNumberLabel.setForeground(new java.awt.Color(255, 0, 0));
        validPhoneNumberLabel.setMaximumSize(new java.awt.Dimension(363, 16));
        validPhoneNumberLabel.setMinimumSize(new java.awt.Dimension(363, 16));
        validPhoneNumberLabel.setPreferredSize(new java.awt.Dimension(363, 16));

        javax.swing.GroupLayout regPanelLayout = new javax.swing.GroupLayout(regPanel);
        regPanel.setLayout(regPanelLayout);
        regPanelLayout.setHorizontalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                .addGap(0, 157, Short.MAX_VALUE)
                .addComponent(createLabel)
                .addGap(157, 157, 157))
            .addGroup(regPanelLayout.createSequentialGroup()
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(regPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(phoneNumberLabel)
                            .addComponent(firstNameLabel)
                            .addComponent(firstNameTextField)
                            .addComponent(lastNameLabel)
                            .addComponent(lastNameTextField)
                            .addComponent(emailLabel)
                            .addComponent(emailTextField)
                            .addComponent(phoneNumberField)
                            .addComponent(validFirstNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(validPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(regPanelLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(submitButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        regPanelLayout.setVerticalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(createLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validFirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validLastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(phoneNumberField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(validPhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(regPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji imienia klienta
     */
    private void performFirstNameValidation(){
        firstName = firstNameTextField.getText();
        if(firstName.equals("")) {
            firstNameCorrect = false;
            validFirstNameLabel.setText("Pole jest wymagane.");
        }
        else {
            firstNameCorrect = Validation.isFirstNameValid(firstName);
            if (firstNameCorrect)
                validFirstNameLabel.setText("");
            else
                validFirstNameLabel.setText("Sprawdź czy podane imię jest poprawne.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji nazwiska klienta
     */
    private void performLastNameValidation(){
        lastName = lastNameTextField.getText();
        if(lastName.equals("")) {
            lastNameCorrect = false;
            validLastNameLabel.setText("Pole jest wymagane.");
        }
        else {
            lastNameCorrect = Validation.isLastNameValid(lastName);
            if (lastNameCorrect)
                validLastNameLabel.setText("");
            else
                validLastNameLabel.setText("Sprawdź czy podane nazwisko jest poprawne.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji emailu klienta
     */
    private void performEmailValidation(){
        email = emailTextField.getText();
        if(email.equals("")) {
            emailCorrect = false;
            validEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = Validation.isEmailValid(email);
            if (emailCorrect)
                validEmailLabel.setText("");
            else
                validEmailLabel.setText("Sprawdź czy podany email jest poprawny.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji numeru telefonu klienta
     */
    public void performPhoneNumberValidation(){
        phoneNumber = phoneNumberField.getText();
        phoneNumberCorrect = Validation.isPhoneNumberValid(phoneNumber);
        if (phoneNumberCorrect || phoneNumber.equals(""))
            validPhoneNumberLabel.setText("");
        else
            validPhoneNumberLabel.setText("Sprawdź czy podany numer telefonu jest poprawny.");
    }
    /**
     * Metoda odpowiadająca za wypełnienie odpowiednich pól danymi klienta
     */
    private void fillTextFields(){
        firstNameTextField.setText(clientData.get(0));
        lastNameTextField.setText(clientData.get(1));
        emailTextField.setText(clientData.get(2));
        phoneNumberField.setText(clientData.get(3));
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Zatwierdź"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performFirstNameValidation();
        performLastNameValidation();
        performEmailValidation();
        performPhoneNumberValidation();
        if(firstNameCorrect && lastNameCorrect && emailCorrect && phoneNumberCorrect){
            data.clear();
            data.add("dataEdition");
            data.add(firstName);
            data.add(lastName);
            data.add(email);
            data.add(phoneNumber);
            data.add(clientId);
            data.add(String.valueOf(client.getStartPageAdminLogged()));
            new Client(data);
            data.clear();
            dispose();
            client.setUserEmail(email);
            new MyAccount(client).setVisible(true);
        }
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
            java.util.logging.Logger.getLogger(DataEdition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new DataEdition(new ArrayList<>(), null).setVisible(true));
    }
}