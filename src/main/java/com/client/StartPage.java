package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;

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
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(64, 28);
    private static final Dimension BUTTON_DIMENSION = new Dimension(105, 25);
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
        initComponents();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setTextFields();
        setButtons();
        createLoginPanel();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logowanie");
        setAlwaysOnTop(true);
        setBackground(ColorUtils.BEIGE);
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
    }
    private void createLoginPanel(){
        loginPanel.setBackground(ColorUtils.LIGHT_BEIGE);
        loginPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, ColorUtils.LIGHT_BEIGE, null, null));
        loginPanel.setForeground(ColorUtils.LIGHT_BEIGE);
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
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(photoLabel)
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
                                                .addComponent(photoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(28, 28, 28))))
        );
        pack();
        setLocationRelativeTo(null);
    }
    private void setLabels(){
        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");
        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Hasło");
        wrongEmailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        wrongEmailLabel.setForeground(Color.red);
        wrongEmailLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        wrongPasswordLabel.setForeground(Color.red);
        wrongPasswordLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        photoLabel.setIcon(new javax.swing.ImageIcon("img\\paris.jpg"));
        photoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        JLabel travelLabel = new JLabel("Travel");
        travelLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        travelLabel.setBounds(182, 50, 250, 65);
        add(travelLabel);
        JLabel agencyLabel = new JLabel("Agency");
        agencyLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        agencyLabel.setBounds(247, 85, 250, 100);
        add(agencyLabel);
    }
    private void setTextFields(){
        emailTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, ColorUtils.MILK, ColorUtils.GREY, ColorUtils.GREY, ColorUtils.GREY));
        emailTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performEmailValidation();
                    }
            }
        });
        passwordField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), ColorUtils.GREY, new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        passwordField.setPreferredSize(TEXT_FIELD_DIMENSION);
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performPasswordValidation();
                    }
            }
        });
    }
    private void setButtons(){
        signInButton.setBackground(ColorUtils.DARK_BEIGE);
        signInButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        signInButton.setText("Zaloguj się");
        signInButton.setActionCommand("Zaloguj się");
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signInButton.setPreferredSize(BUTTON_DIMENSION);
        signInButton.addActionListener(this::signInButtonActionPerformed);
        signInButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        registerButton.setBackground(ColorUtils.DARK_BEIGE);
        registerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        registerButton.setText("Utwórz konto");
        registerButton.addActionListener(this::registerButtonActionPerformed);
        registerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });
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
            emailCorrect = Validation.isEmailValid(emailFromTextField);
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
            passwordCorrect = Validation.isPasswordValid(passwordFromPasswordField);
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
            data.clear();
            data.add("login");
            data.add(email);
            data.add(password);
            wrongEmailLabel.setText("");
            Client client = new Client(data);
            this.client = client;
            List<String> returningData = client.getReturningData();
            userExists = Boolean.parseBoolean(returningData.get(0));
            passwordValid = Boolean.parseBoolean(returningData.get(1));
            adminLogged = Boolean.parseBoolean(returningData.get(2));
            clientLogged = Boolean.parseBoolean(returningData.get(3));
            message = returningData.get(5);
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
    //GUI variables
    private final JPanel loginPanel = new JPanel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel passwordLabel = new JLabel();
    private final JLabel photoLabel = new JLabel();
    /**
     * Pole do wprowadzenia emailu
     */
    private final javax.swing.JTextField emailTextField = new JTextField();
    /**
     * Pole do wprowadzenia hasła
     */
    private final javax.swing.JPasswordField passwordField = new JPasswordField();
    /**
     * Przycisk umożliwiający zalogowanie się
     */
    private final javax.swing.JButton registerButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do okna z rejestracją
     */
    private final javax.swing.JButton signInButton = new JButton();
    /**
     * Etykieta informująca, że email jest niepoprawny
     */
    private final javax.swing.JLabel wrongEmailLabel = new JLabel();
    /**
     * Etykieta informująca, że hasło jest niepoprawne
     */
    private final javax.swing.JLabel wrongPasswordLabel = new JLabel();
}
