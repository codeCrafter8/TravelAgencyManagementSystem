package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class Registration extends javax.swing.JFrame {
    public String userExists;
    public String firstName;
    public String lastName;
    public String phoneNumber;
    public String email;
    public String password;
    private boolean firstNameCorrect;
    private boolean lastNameCorrect;
    private boolean phoneNumberCorrect;
    private boolean emailCorrect;
    private boolean passwordCorrect;
    private boolean confirmPasswordCorrect;
    private Validation validation;
    private String passwordFromPasswordField;
    private javax.swing.JLabel typeConfirmPasswordLabel;
    private javax.swing.JLabel typeEmailLabel;
    private javax.swing.JLabel typeFirstnameLabel;
    private javax.swing.JLabel typeLastNameLabel;
    private javax.swing.JLabel typePasswordLabel;
    private javax.swing.JLabel typePhoneNumberLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JButton submitButton;
    private final List<String> data = new ArrayList<>();
    private boolean adminLogged;
    private Client client;
    private String adminName;
    public Registration(boolean adminLogged, Client client) {
        this.client = client;
        this.adminLogged = adminLogged;
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if(adminLogged)
                       new Client("logOut",new ArrayList<>());
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    public Registration(boolean adminLogged, Client client, String adminName){
        this.client = client;
        this.adminLogged = adminLogged;
        this.adminName = adminName;
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if(adminLogged)
                        new Client("logOut",new ArrayList<>());
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    public Registration(){

    }
    private void initComponents() {
        JPanel regPanel = new JPanel();
        JLabel createLabel = new JLabel();
        JLabel firstNameLabel = new JLabel();
        firstNameTextField = new javax.swing.JTextField();
        JLabel lastNameLabel = new JLabel();
        lastNameTextField = new javax.swing.JTextField();
        JLabel phoneNumberLabel = new JLabel();
        phoneNumberTextField = new javax.swing.JTextField();
        JLabel emailLabel = new JLabel();
        emailTextField = new javax.swing.JTextField();
        JLabel passwordLabel = new JLabel();
        passwordField = new javax.swing.JPasswordField();
        JLabel confirmPasswordLabel = new JLabel();
        confirmPasswordField = new javax.swing.JPasswordField();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        typeConfirmPasswordLabel = new javax.swing.JLabel();
        typePasswordLabel = new javax.swing.JLabel();
        typeEmailLabel = new javax.swing.JLabel();
        typePhoneNumberLabel = new javax.swing.JLabel();
        typeLastNameLabel = new javax.swing.JLabel();
        typeFirstnameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rejestracja");
        setPreferredSize(new java.awt.Dimension(1024, 768));

        regPanel.setBackground(new java.awt.Color(247, 233, 201));
        regPanel.setPreferredSize(new java.awt.Dimension(450, 620));

        createLabel.setFont(new java.awt.Font("Myanmar Text", Font.PLAIN, 24));
        createLabel.setText("Utwórz konto");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        firstNameLabel.setText("Imię");
        firstNameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(cancelButton)) {
                        performFirstNameValidation();
                    }
            }
        });

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        lastNameLabel.setText("Nazwisko");
        lastNameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(cancelButton)) {
                        performLastNameValidation();
                    }
            }
        });

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        phoneNumberLabel.setText("Numer telefonu");
        phoneNumberTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performPhoneNumberValidation();
                }
            }
        });

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");
        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performEmailValidation();
                }
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Hasło");
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performPasswordValidation();
                }
            }
        });

        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        confirmPasswordLabel.setText("Potwierdź hasło");
        confirmPasswordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performConfirmPasswordValidation();
                }
            }
        });

        submitButton.setBackground(new java.awt.Color(189, 165, 111));
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setMinimumSize(new java.awt.Dimension(116, 27));
        submitButton.setText("Utwórz");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        cancelButton.setBackground(new java.awt.Color(189, 165, 111));
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new java.awt.Dimension(116, 27));
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        cancelButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        typeConfirmPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeConfirmPasswordLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        typeConfirmPasswordLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        typePasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        typePasswordLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        typePasswordLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        typeEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeEmailLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        typeEmailLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        typePhoneNumberLabel.setForeground(new java.awt.Color(255, 0, 0));
        typePhoneNumberLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        typePhoneNumberLabel.setPreferredSize(new java.awt.Dimension(350, 16));

        typeLastNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeLastNameLabel.setMaximumSize(new java.awt.Dimension(350, 16));
        typeLastNameLabel.setPreferredSize(new java.awt.Dimension(350, 16));

        typeFirstnameLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeFirstnameLabel.setMaximumSize(new java.awt.Dimension(350, 16));
        typeFirstnameLabel.setPreferredSize(new java.awt.Dimension(350, 16));

        javax.swing.GroupLayout regPanelLayout = new javax.swing.GroupLayout(regPanel);
        regPanel.setLayout(regPanelLayout);
        regPanelLayout.setHorizontalGroup(
            regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(regPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(submitButton)
                .addGap(18, 18, 18)
                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeConfirmPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emailTextField)
                    .addComponent(confirmPasswordField)
                    .addComponent(confirmPasswordLabel)
                    .addComponent(passwordLabel)
                    .addComponent(emailLabel)
                    .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(firstNameLabel)
                        .addComponent(firstNameTextField)
                        .addComponent(lastNameLabel)
                        .addComponent(lastNameTextField)
                        .addComponent(phoneNumberLabel)
                        .addComponent(phoneNumberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                        .addComponent(typePhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(typeLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(typeFirstnameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(typeEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(typePasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(createLabel)
                .addGap(152, 152, 152))
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
                .addComponent(typeFirstnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typeLastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typePhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typeEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(typeConfirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
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
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if(adminLogged)
            new Clients(client, adminName).setVisible(true);
        else
            new StartPage().setVisible(true);
    }
    private void performFirstNameValidation(){
        String firstNameFromTextField = firstNameTextField.getText();
        if(firstNameFromTextField.equals("")) {
            firstNameCorrect = false;
            typeFirstnameLabel.setText("Pole jest wymagane.");
        }
        else {
            firstNameCorrect = validation.firstNameIsValid(firstNameFromTextField);
            if (firstNameCorrect)
                typeFirstnameLabel.setText("");
            else
                typeFirstnameLabel.setText("Sprawdź czy podane imię jest poprawne.");
        }
    }

    private void performLastNameValidation(){
        String lastNameFromTextField = lastNameTextField.getText();
        if(lastNameFromTextField.equals("")) {
            lastNameCorrect = false;
            typeLastNameLabel.setText("Pole jest wymagane.");
        }
        else {
            lastNameCorrect = validation.lastNameIsValid(lastNameFromTextField);
            if (lastNameCorrect)
                typeLastNameLabel.setText("");
            else
                typeLastNameLabel.setText("Sprawdź czy podane nazwisko jest poprawne.");
        }
    }

    private void performPhoneNumberValidation(){
        String phoneNumberFromTextField = phoneNumberTextField.getText();
        phoneNumberCorrect = validation.phoneNumberIsValid(phoneNumberFromTextField);
        if (phoneNumberCorrect || phoneNumberFromTextField.equals(""))
            typePhoneNumberLabel.setText("");
        else
            typePhoneNumberLabel.setText("Sprawdź czy podany numer telefonu jest poprawny.");
    }

    private void performEmailValidation(){
        String emailFromTextField = emailTextField.getText();
        if(emailTextField.getText().isEmpty()) {
            emailCorrect = false;
            typeEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = validation.emailIsValid(emailFromTextField);
            if (emailCorrect)
                typeEmailLabel.setText("");
            else
                typeEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
        }
    }

    private void performPasswordValidation(){
        passwordFromPasswordField = new String(passwordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            typePasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            passwordCorrect = validation.passwordIsValid(passwordFromPasswordField);
            if (passwordCorrect)
                typePasswordLabel.setText("");
            else
                typePasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");
        }
    }

    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(passwordFromPasswordField)){
            typeConfirmPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            typeConfirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performFirstNameValidation();
        performLastNameValidation();
        performPhoneNumberValidation();
        performEmailValidation();
        performPasswordValidation();
        performConfirmPasswordValidation();
        if(firstNameCorrect && lastNameCorrect && phoneNumberCorrect && emailCorrect && passwordCorrect && confirmPasswordCorrect) {
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            phoneNumber = phoneNumberTextField.getText();
            email = emailTextField.getText();
            data.add(firstName);
            data.add(lastName);
            data.add(phoneNumber);
            data.add(email);
            password = new String(passwordField.getPassword());
            data.add(password);
            Client client = new Client("addClient",data);
            userExists = client.getRegistrationUserExists();
            if (userExists.equals("Tak")) {
                typeEmailLabel.setText("Użytkownik o tym adresie e-mail już istnieje. Podaj inny.");
                data.clear();
            }
            else{
                dispose();
                if(adminLogged)
                    new Clients(this.client, adminName).setVisible(true);
                else
                    new StartPage().setVisible(true);
            }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Registration(false, null).setVisible(true));
    }
}
