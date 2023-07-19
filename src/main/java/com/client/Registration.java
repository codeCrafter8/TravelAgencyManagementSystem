package com.client;

import com.server.Logs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność rejestracji
 */
public class Registration extends javax.swing.JFrame {
    /**
     * Atrybut określający, czy klient istnieje w bazie
     */
    public String clientExists;
    /**
     * Atrybut będący imieniem
     */
    public String firstName;
    /**
     * Atrybut będący nazwiskiem
     */
    public String lastName;
    /**
     * Atrybut będący numerem telefonu
     */
    public String phoneNumber;
    /**
     * Atrybut będący emailem
     */
    public String email;
    /**
     * Atrybut będący hasłem
     */
    public String password;
    /**
     * Atrybut określający, czy imię jest poprawne
     */
    private boolean firstNameCorrect;
    /**
     * Atrybut określający, czy nazwisko jest poprawne
     */
    private boolean lastNameCorrect;
    /**
     * Atrybut określający, czy numer telefonu jest poprawny
     */
    private boolean phoneNumberCorrect;
    /**
     * Atrybut określający, czy email jest poprawny
     */
    private boolean emailCorrect;
    /**
     * Atrybut określający, czy hasło jest poprawne
     */
    private boolean passwordCorrect;
    /**
     * Atrybut określający, czy potwierdzone hasło jest poprawne
     */
    private boolean confirmPasswordCorrect;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut określający, czy adminstrator jest zalogowany
     */
    private boolean adminLogged;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut będący imieniem administatora
     */
    private String adminName;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param adminLogged parametr określający, czy administrator jest zalogowany
     * @param client parametr przechowujący obiekt klasy Client
     */
    public Registration(boolean adminLogged, Client client) {
        this.client = client;
        this.adminLogged = adminLogged;
        initComponents();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if(adminLogged) {
                        data.clear();
                        data.add("logOut");
                        new Client(data);
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param adminLogged parametr określający, czy administrator jest zalogowany
     * @param client parametr przechowujący obiekt klasy Client
     * @param adminName parametr będący imieniem administratora
     */
    public Registration(boolean adminLogged, Client client, String adminName){
        this.client = client;
        this.adminLogged = adminLogged;
        this.adminName = adminName;
        initComponents();
    }
    /**
     * Pomocniczy konstruktor odpowiadający za inicjalizację GUI
     */
    public Registration(){initComponents();}
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        createRegPanel();
        setLabels();
        setTextFields();
        setButtons();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rejestracja");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setBackground(new Color(215,198,151));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    if(adminLogged) {
                        data.clear();
                        data.add("logOut");
                        new Client(data);
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new Logs("Registration", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
                }
            }
        });
    }
    private void createRegPanel(){
        regPanel.setBackground(new java.awt.Color(247, 233, 201));
        regPanel.setPreferredSize(new java.awt.Dimension(450, 620));
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
                                        .addComponent(wrongConfirmPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
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
                                                .addComponent(wrongPhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(wrongLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(wrongFirstNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(wrongEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(wrongPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                                .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, regPanelLayout.createSequentialGroup()
                                .addContainerGap(153, Short.MAX_VALUE)
                                .addComponent(createAccountLabel)
                                .addGap(152, 152, 152))
        );
        regPanelLayout.setVerticalGroup(
                regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(regPanelLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(createAccountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongFirstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongLastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(phoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongPhoneNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongConfirmPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                                .addGroup(regPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25))
        );
    }
    private void setLabels(){
        createAccountLabel.setFont(new java.awt.Font("Myanmar Text", Font.PLAIN, 24));
        createAccountLabel.setText("Utwórz konto");

        firstNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        firstNameLabel.setText("Imię");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        lastNameLabel.setText("Nazwisko");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        phoneNumberLabel.setText("Numer telefonu");

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");


        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Hasło");

        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        confirmPasswordLabel.setText("Potwierdź hasło");

        wrongConfirmPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongConfirmPasswordLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        wrongConfirmPasswordLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        wrongPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongPasswordLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        wrongPasswordLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        wrongEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongEmailLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        wrongEmailLabel.setPreferredSize(new java.awt.Dimension(400, 16));

        wrongPhoneNumberLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongPhoneNumberLabel.setMaximumSize(new java.awt.Dimension(400, 16));
        wrongPhoneNumberLabel.setPreferredSize(new java.awt.Dimension(350, 16));

        wrongLastNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongLastNameLabel.setMaximumSize(new java.awt.Dimension(350, 16));
        wrongLastNameLabel.setPreferredSize(new java.awt.Dimension(350, 16));

        wrongFirstNameLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongFirstNameLabel.setMaximumSize(new java.awt.Dimension(350, 16));
        wrongFirstNameLabel.setPreferredSize(new java.awt.Dimension(350, 16));
    }
    private void setTextFields(){
        firstNameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(cancelButton)) {
                        performFirstNameValidation();
                    }
            }
        });
        lastNameTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(cancelButton)) {
                        performLastNameValidation();
                    }
            }
        });
        phoneNumberTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performPhoneNumberValidation();
                }
            }
        });
        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performEmailValidation();
                }
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performPasswordValidation();
                }
            }
        });
        confirmPasswordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(!e.getOppositeComponent().equals(cancelButton)) {
                    performConfirmPasswordValidation();
                }
            }
        });
    }
    private void setButtons(){
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
    }
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
                                .addContainerGap(54, Short.MAX_VALUE)
                                .addComponent(regPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Cofnij"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if(adminLogged)
            new Clients(client, adminName).setVisible(true);
        else
            new StartPage().setVisible(true);
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji imienia
     */
    private void performFirstNameValidation(){
        firstName = firstNameTextField.getText();
        if(firstName.equals("")) {
            firstNameCorrect = false;
            wrongFirstNameLabel.setText("Pole jest wymagane.");
        }
        else {
            firstNameCorrect = Validation.isFirstNameValid(firstName);
            if (firstNameCorrect)
                wrongFirstNameLabel.setText("");
            else
                wrongFirstNameLabel.setText("Sprawdź czy podane imię jest poprawne.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji nazwiska
     */
    private void performLastNameValidation(){
        lastName = lastNameTextField.getText();
        if(lastName.equals("")) {
            lastNameCorrect = false;
            wrongLastNameLabel.setText("Pole jest wymagane.");
        }
        else {
            lastNameCorrect = Validation.isLastNameValid(lastName);
            if (lastNameCorrect)
                wrongLastNameLabel.setText("");
            else
                wrongLastNameLabel.setText("Sprawdź czy podane nazwisko jest poprawne.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji numeru telefonu
     */
    private void performPhoneNumberValidation(){
        phoneNumber = phoneNumberTextField.getText();
        phoneNumberCorrect = Validation.isPhoneNumberValid(phoneNumber);
        if (phoneNumberCorrect || phoneNumber.equals(""))
            wrongPhoneNumberLabel.setText("");
        else
            wrongPhoneNumberLabel.setText("Sprawdź czy podany numer telefonu jest poprawny.");
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji emailu
     */
    private void performEmailValidation(){
        email = emailTextField.getText();
        if(email.equals("")) {
            emailCorrect = false;
            wrongEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = Validation.isEmailValid(email);
            if (emailCorrect)
                wrongEmailLabel.setText("");
            else
                wrongEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji hasła
     */
    private void performPasswordValidation(){
        password = new String(passwordField.getPassword());
        if(password.equals("")) {
            passwordCorrect = false;
            wrongPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            passwordCorrect = Validation.isPasswordValid(password);
            if (passwordCorrect)
                wrongPasswordLabel.setText("");
            else
                wrongPasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji potwierdzonego hasła
     */
    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(password)){
            wrongConfirmPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            wrongConfirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Utwórz"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performFirstNameValidation();
        performLastNameValidation();
        performPhoneNumberValidation();
        performEmailValidation();
        performPasswordValidation();
        performConfirmPasswordValidation();
        if(firstNameCorrect && lastNameCorrect && phoneNumberCorrect && emailCorrect && passwordCorrect && confirmPasswordCorrect) {
            data.clear();
            data.add("addClient");
            data.add(firstName);
            data.add(lastName);
            data.add(phoneNumber);
            data.add(email);
            data.add(password);
            Client client = new Client(data);
            clientExists = client.getReturningData().get(0);
            if (clientExists.equals("Tak")) {
                wrongEmailLabel.setText("Użytkownik o tym adresie e-mail już istnieje. Podaj inny.");
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
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Registration().setVisible(true));
    }
    //GUI variables
    /**
     * Etykieta informująca, że potwierdzone hasło jest niepoprawne
     */
    private final javax.swing.JLabel wrongConfirmPasswordLabel = new JLabel();
    /**
     * Etykieta informująca, że email jest niepoprawny
     */
    private final javax.swing.JLabel wrongEmailLabel = new JLabel();
    /**
     * Etykieta informująca, że imię jest niepoprawne
     */
    private final javax.swing.JLabel wrongFirstNameLabel = new JLabel();
    /**
     * Etykieta informująca, że nazwisko jest niepoprawne
     */
    private final javax.swing.JLabel wrongLastNameLabel = new JLabel();
    /**
     * Etykieta informująca, że hasło jest niepoprawne
     */
    private final javax.swing.JLabel wrongPasswordLabel = new JLabel();
    /**
     * Etykieta informująca, że numer telefonu jest niepoprawny
     */
    private final javax.swing.JLabel wrongPhoneNumberLabel = new JLabel();
    /**
     * Przycisk umożliwiający cofnięcie do strony logowania
     */
    private final javax.swing.JButton cancelButton = new JButton();
    /**
     * Pole do wprowadzenia potwierdzonego hasła
     */
    private final javax.swing.JPasswordField confirmPasswordField = new JPasswordField();
    /**
     * Pole do wprowadzenia emailu
     */
    private final javax.swing.JTextField emailTextField = new JTextField();
    /**
     * Pole do wprowadzenia imienia
     */
    private final javax.swing.JTextField firstNameTextField = new JTextField();
    /**
     * Pole do wprowadzenia nazwiska
     */
    private final javax.swing.JTextField lastNameTextField = new JTextField();
    /**
     * Pole do wprowadzenia hasła
     */
    private final javax.swing.JPasswordField passwordField = new JPasswordField();
    /**
     * Pole do wprowadzenia numeru telefonu
     */
    private final javax.swing.JTextField phoneNumberTextField = new JTextField();
    /**
     * Przycisk umożliwiający utworzenie konta
     */
    private final javax.swing.JButton submitButton = new JButton();
    private final JPanel regPanel = new JPanel();
    private final JLabel createAccountLabel = new JLabel();
    private final JLabel firstNameLabel = new JLabel();
    private final JLabel lastNameLabel = new JLabel();
    private final JLabel phoneNumberLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel passwordLabel = new JLabel();
    private final JLabel confirmPasswordLabel = new JLabel();
}
