package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.ClientValidator;

import java.awt.event.FocusEvent;
import java.awt.*;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
/**
 * Class containing fields and methods for handling the window with login functionality.
 */
public class StartPage extends javax.swing.JFrame {

    /**
     * The preferred dimension for text fields.
     * The width is set to 64 pixels, and the height is set to 28 pixels.
     */
    private static final Dimension TEXT_FIELD_DIMENSION = new Dimension(64, 28);

    /**
     * The preferred dimension for buttons.
     * The width is set to 105 pixels, and the height is set to 25 pixels.
     */
    private static final Dimension BUTTON_DIMENSION = new Dimension(105, 25);

    /**
     * Attribute representing a list storing data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * Attribute specifying whether the user exists in the database.
     */
    boolean userExists;

    /**
     * Attribute specifying whether the password matches the one in the database for the given email.
     */
    boolean passwordValid;

    /**
     * Attribute specifying whether the administrator is logged in.
     */
    boolean adminLogged;

    /**
     * Attribute specifying whether the client is logged in.
     */
    boolean clientLogged;

    /**
     * Attribute storing the email.
     */
    String email;

    /**
     * Attribute storing the password.
     */
    String password;

    /**
     * Attribute storing the message passed from the Client class.
     */
    String message;

    /**
     * Attribute specifying whether the email is correct.
     */
    private boolean emailCorrect;

    /**
     * Attribute specifying whether the password is correct.
     */
    private boolean passwordCorrect;

    /**
     * Attribute representing an object of the Client class.
     */
    private Client client;

    /**
     * Constructor responsible for initializing the GUI and related elements.
     */
    StartPage() {
        initComponents();
    }

    /**
     * Method initializing graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setTextFields();
        setButtons();
        createLoginPanel();
        createLayout();
    }

    /**
     * Sets properties for the main window.
     * The window title is set to "Logowanie".
     * The window is always on top.
     * The background color is set to ColorUtils.BEIGE.
     * The preferred size is set to DimensionUtils.WINDOW_DIMENSION.
     * The content pane background color is set to ColorUtils.BEIGE.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logowanie");
        setAlwaysOnTop(true);
        setBackground(ColorUtils.BEIGE);
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
    }

    /**
     * Creates the loginPanel and sets its properties and styles.
     * The panel background color is set to ColorUtils.LIGHT_BEIGE.
     * The panel border is created with a bevel border.
     * The panel contains labels, text fields, and buttons related to the login functionality.
     */
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

    /**
     * Creates the layout for the main window (frame).
     * The layout contains the loginPanel and a photoLabel (e.g., displaying an image of Paris).
     */
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

    /**
     * Sets properties and styles for various JLabels in the loginPanel.
     * It sets the text, font, and preferred size for labels related to email, password, and error messages.
     * Additionally, it creates and adds JLabels for "Travel" and "Agency" to the layout.
     */
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

    /**
     * Sets properties and styles for the emailTextField and passwordField in the loginPanel.
     * The fields' borders are set using a SoftBevelBorder.
     * The fields' preferred size is set to a constant defined by TEXT_FIELD_DIMENSION.
     * Focus listeners are added to perform email and password validation when the fields lose focus.
     */
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

    /**
     * Sets properties and styles for signInButton and registerButton in the loginPanel.
     * The buttons' background colors, fonts, text, and action listeners are configured.
     * Change listeners are added to handle cursor changes on button rollover.
     */
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
     * Method handling the "Create Account" button click.
     * @param evt Event triggered when the button is clicked.
     */
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Registration(false, client).setVisible(true);
    }
    /**
     * Method responsible for email validation.
     */
    private void performEmailValidation() {
        String emailFromTextField = emailTextField.getText();
        if (emailFromTextField.equals("")) {
            emailCorrect = false;
            wrongEmailLabel.setText("This field is required.");
        } else {
            emailCorrect = ClientValidator.isEmailValid(emailFromTextField);
            if (emailCorrect)
                wrongEmailLabel.setText("");
            else
                wrongEmailLabel.setText("Check if the provided email address is valid.");
        }
    }
    /**
     * Method responsible for password validation.
     */
    private void performPasswordValidation() {
        String passwordFromPasswordField = new String(passwordField.getPassword());
        if (passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            wrongPasswordLabel.setText("This field is required.");
        } else {
            passwordCorrect = ClientValidator.isPasswordValid(passwordFromPasswordField);
            if (passwordCorrect)
                wrongPasswordLabel.setText("");
            else
                wrongPasswordLabel.setText("Minimum 8 characters, including at least one digit, uppercase, and lowercase letter.");
        }
    }
    /**
     * Method handling the "Sign In" button click.
     * @param evt Event triggered when the button is clicked.
     */
    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performEmailValidation();
        performPasswordValidation();

        if (emailCorrect && passwordCorrect) {
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
                wrongEmailLabel.setText("User with this email address does not exist. Provide another one.");
                data.clear();
            } else {
                if (!passwordValid) {
                    wrongPasswordLabel.setText("Incorrect password.");
                    data.clear();
                } else {
                    if (!message.equals("")) {
                        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.ERROR_MESSAGE);
                        data.clear();
                    } else {
                        if (adminLogged) {
                            this.dispose();
                            new Dashboard(client, "").setVisible(true);
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
     * Method allowing to run the window.
     * @param args Arguments passed during application startup.
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
    /**
     * A login panel containing login-related components.
     */
    private final JPanel loginPanel = new JPanel();

    /**
     * A label displaying the text "E-mail".
     */
    private final JLabel emailLabel = new JLabel();

    /**
     * A label displaying the text "Hasło".
     */
    private final JLabel passwordLabel = new JLabel();

    /**
     * A label displaying a photo or image.
     */
    private final JLabel photoLabel = new JLabel();
    /**
     * A text field for entering the email.
     */
    private final javax.swing.JTextField emailTextField = new JTextField();
    /**
     * A password field for entering the password.
     */
    private final javax.swing.JPasswordField passwordField = new JPasswordField();
    /**
     * A button for logging in.
     */
    private final javax.swing.JButton registerButton = new JButton();
    /**
     * A button for proceeding to the registration window.
     */
    private final javax.swing.JButton signInButton = new JButton();
    /**
     * A label indicating that the email is incorrect.
     */
    private final javax.swing.JLabel wrongEmailLabel = new JLabel();
    /**
     * A label indicating that the password is incorrect.
     */
    private final javax.swing.JLabel wrongPasswordLabel = new JLabel();
}
