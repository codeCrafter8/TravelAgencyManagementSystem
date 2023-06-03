package com.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DataEdition extends javax.swing.JFrame {
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JTextField phoneNumberField;
    private javax.swing.JLabel validEmailLabel;
    private javax.swing.JLabel validFirstNameLabel;
    private javax.swing.JLabel validLastNameLabel;
    private javax.swing.JLabel validPhoneNumberLabel;
    private final Validation validation;
    private boolean firstNameCorrect;
    private boolean emailCorrect;
    private boolean phoneNumberCorrect;
    private boolean lastNameCorrect;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<String> data = new ArrayList<>();
    private final String clientID;
    private final List<String> clientData = new ArrayList<>();
    private final Client client;
    public DataEdition(List<String> clientData, Client client) {
        this.client = client;
        this.clientData.addAll(clientData);
        this.clientID = clientData.get(4);
        validation = new Validation();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    new MyAccount(client).setVisible(true);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        fillTextFields();
    }
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

        createLabel.setFont(new java.awt.Font("Arial", Font.PLAIN, 24)); // NOI18N
        createLabel.setText("Edytuj dane");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        firstNameLabel.setText("Imię");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        lastNameLabel.setText("Nazwisko");

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        emailLabel.setText("E-mail");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        phoneNumberLabel.setText("Numer telefonu");

        submitButton.setBackground(new java.awt.Color(151, 123, 92));
        submitButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14)); // NOI18N
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
    private void performFirstNameValidation(){
        firstName = firstNameTextField.getText();
        if(firstName.equals("")) {
            firstNameCorrect = false;
            validFirstNameLabel.setText("Pole jest wymagane.");
        }
        else {
            firstNameCorrect = validation.firstNameIsValid(firstName);
            if (firstNameCorrect)
                validFirstNameLabel.setText("");
            else
                validFirstNameLabel.setText("Sprawdź czy podane imię jest poprawne.");
        }
    }

    private void performLastNameValidation(){
        lastName = lastNameTextField.getText();
        if(lastName.equals("")) {
            lastNameCorrect = false;
            validLastNameLabel.setText("Pole jest wymagane.");
        }
        else {
            lastNameCorrect = validation.lastNameIsValid(lastName);
            if (lastNameCorrect)
                validLastNameLabel.setText("");
            else
                validLastNameLabel.setText("Sprawdź czy podane nazwisko jest poprawne.");
        }
    }
    private void performEmailValidation(){
        email = emailTextField.getText();
        if(email.equals("")) {
            emailCorrect = false;
            validEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = validation.emailIsValid(email);
            if (emailCorrect)
                validEmailLabel.setText("");
            else
                validEmailLabel.setText("Sprawdź czy podany email jest poprawny.");
        }
    }
    private void performPhoneNumberValidation(){
        phoneNumber = phoneNumberField.getText();
        phoneNumberCorrect = validation.phoneNumberIsValid(phoneNumber);
        if (phoneNumberCorrect || phoneNumber.equals(""))
            validPhoneNumberLabel.setText("");
        else
            validPhoneNumberLabel.setText("Sprawdź czy podany numer telefonu jest poprawny.");
    }
    private void fillTextFields(){
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        phoneNumberField.setText("");
        firstNameTextField.setText(clientData.get(0));
        lastNameTextField.setText(clientData.get(1));
        emailTextField.setText(clientData.get(2));
        phoneNumberField.setText(clientData.get(3));
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        performFirstNameValidation();
        performLastNameValidation();
        performEmailValidation();
        performPhoneNumberValidation();
        if(firstNameCorrect && lastNameCorrect && emailCorrect && phoneNumberCorrect){
            data.add(firstName);
            data.add(lastName);
            data.add(email);
            data.add(phoneNumber);
            data.add(clientID);
            new Client("dataEdition",data);
            data.clear();
            dispose();
            client.setUserEmail(email);
            new MyAccount(client).setVisible(true);
        }
    }

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