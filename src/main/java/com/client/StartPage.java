package com.client;

import java.awt.event.FocusEvent;
import java.awt.*;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność logowania
 */
public class StartPage extends javax.swing.JFrame {
    /**
     * Pole do wprowadzenia emailu
     */
    private javax.swing.JTextField emailTextField;
    /**
     * Pole do wprowadzenia hasła
     */
    private javax.swing.JPasswordField passwordField;
    /**
     * Przycisk umożliwiający zalogowanie się
     */
    private javax.swing.JButton registerButton;
    /**
     * Przycisk umożliwiający przejście do okna z rejestracją
     */
    private javax.swing.JButton signInButton;
    /**
     * Etykieta informująca, że email jest niepoprawny
     */
    private javax.swing.JLabel wrongEmailLabel;
    /**
     * Etykieta informująca, że hasło jest niepoprawne
     */
    private javax.swing.JLabel wrongPasswordLabel;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut określający, czy użytkownik istnieje w bazie
     */
    boolean userExists;
    /**
     * Atrybut określający, czy hasło dla danego emailu zgadza się z tym w bazie danych
     */
    boolean passwordValid;
    /**
     * Atrybut określający, czy administrator jest zalogowany
     */
    boolean adminLogged;
    /**
     * Atrybut określający, czy klient jest zalogowany
     */
    boolean clientLogged;
    /**
     * Atrybut przechowujący email
     */
    String email;
    /**
     * Atrybut przechowujący hasło
     */
    String password;
    /**
     * Atrybut przechowujący wiadomość przekazaną od klasy Client
     */
    String message;
    /**
     * Atrybut będący obiektem klasy Validation
     */
    private final Validation validation;
    /**
     * Atrybut określający, czy email jest poprawny
     */
    private boolean emailCorrect;
    /**
     * Atrybut określający, czy hasło jest poprawne
     */
    private boolean passwordCorrect;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     */
    StartPage() {
        validation = new Validation();
        initComponents();
    }
    /**
     * Metoda odpowiadający za inicjalizację odpowiednich atrybutów
     */
    void setLoginFields(boolean userExists, boolean passwordValid, boolean adminLogged, boolean clientLogged, String message){
        this.userExists = userExists;
        this.passwordValid = passwordValid;
        this.adminLogged = adminLogged;
        this.clientLogged = clientLogged;
        this.message = message;
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        JPanel loginPanel = new JPanel();
        JLabel emailLabel = new JLabel();
        emailTextField = new javax.swing.JTextField();
        JLabel passwordLabel = new JLabel();
        signInButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        wrongEmailLabel = new javax.swing.JLabel();
        wrongPasswordLabel = new javax.swing.JLabel();
        JLabel jLabel2 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logowanie");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(242, 214, 158));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        loginPanel.setBackground(new java.awt.Color(247, 233, 201));
        loginPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(229, 221, 192), null, null));
        loginPanel.setForeground(new java.awt.Color(224, 214, 176));

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");

        emailTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        emailTextField.setPreferredSize(new java.awt.Dimension(64, 28));

        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performEmailValidation();
                    }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performPasswordValidation();
                    }
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Hasło");

        signInButton.setBackground(new java.awt.Color(189, 165, 111));
        signInButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        signInButton.setText("Zaloguj się");
        signInButton.setActionCommand("Zaloguj się");
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signInButton.setMaximumSize(new java.awt.Dimension(105, 25));
        signInButton.setMinimumSize(new java.awt.Dimension(105, 25));
        signInButton.setPreferredSize(new java.awt.Dimension(105, 25));
        signInButton.addActionListener(this::signInButtonActionPerformed);
        signInButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        registerButton.setBackground(new java.awt.Color(189, 165, 111));
        registerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        registerButton.setText("Utwórz konto");
        registerButton.addActionListener(this::registerButtonActionPerformed);
        registerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        passwordField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        passwordField.setPreferredSize(new java.awt.Dimension(64, 28));

        wrongEmailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        wrongEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongEmailLabel.setMinimumSize(new java.awt.Dimension(94, 18));
        wrongEmailLabel.setPreferredSize(new java.awt.Dimension(94, 18));

        wrongPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        wrongPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongPasswordLabel.setMinimumSize(new java.awt.Dimension(94, 18));
        wrongPasswordLabel.setPreferredSize(new java.awt.Dimension(94, 18));

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(wrongEmailLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addGap(0, 365, Short.MAX_VALUE))
                            .addComponent(wrongPasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(passwordLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(registerButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(emailLabel)
                .addGap(7, 7, 7)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addGap(7, 7, 7)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(wrongPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jLabel2.setIcon(new javax.swing.ImageIcon("img\\paris.jpg"));
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().setBackground(new Color(215,198,151));
        JLabel travelLabel = new JLabel("Travel");
        travelLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        travelLabel.setBounds(182, 50, 250, 65);
        add(travelLabel);
        JLabel agencyLabel = new JLabel("Agency");
        agencyLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        agencyLabel.setBounds(247, 85, 250, 100);
        add(agencyLabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Utwórz konto"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Registration(false,client).setVisible(true);
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji emailu
     */
    private void performEmailValidation(){
        String emailFromTextField = emailTextField.getText();
        if(emailFromTextField.equals("")) {
            emailCorrect = false;
            wrongEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = validation.emailIsValid(emailFromTextField);
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
        String passwordFromPasswordField = new String(passwordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            wrongPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            passwordCorrect = validation.passwordIsValid(passwordFromPasswordField);
            if (passwordCorrect)
                wrongPasswordLabel.setText("");
            else
                wrongPasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Zaloguj się"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performEmailValidation();
        performPasswordValidation();

        if(emailCorrect && passwordCorrect) {
            email = emailTextField.getText();
            password = new String(passwordField.getPassword());
            data.add(email);
            data.add(password);
            wrongEmailLabel.setText("");
            Client client = new Client("login", data);
            this.client = client;
            userExists = client.getStartPageUserExists();
            passwordValid = client.getStartPagePasswordValid();
            message = client.getStartPageMessage();
            adminLogged = client.getStartPageAdminLogged();
            clientLogged = client.getStartPageClientLogged();
            if (!userExists) {
                wrongEmailLabel.setText("Użytkownik o tym adresie e-mail nie istnieje. Podaj inny.");
                data.clear();
            }
            else {
                if (!passwordValid){
                    wrongPasswordLabel.setText("Błędne hasło.");
                    data.clear();
                }
                else {
                    if(!message.equals("")) {
                        JOptionPane.showMessageDialog(this, message, "Informacja", JOptionPane.ERROR_MESSAGE);
                        data.clear();
                    }
                    else {
                        if (adminLogged) {
                            this.dispose();
                            new Dashboard(client,"").setVisible(true);
                        } else if (clientLogged) {
                            this.dispose();
                            new SearchEngine(client).setVisible(true);
                        }
                    }
                }
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
            java.util.logging.Logger.getLogger(StartPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new StartPage().setVisible(true));
    }
}
