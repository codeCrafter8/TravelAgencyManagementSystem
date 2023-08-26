package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.ClientValidator;
import com.server.logging.Logs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing fields and methods for handling the registration window functionality.
 */
public class Registration extends javax.swing.JFrame {

    /**
     * An attribute indicating whether the client exists in the database.
     */
    public String clientExists;

    /**
     * An attribute representing the first name.
     */
    public String firstName;

    /**
     * An attribute representing the last name.
     */
    public String lastName;

    /**
     * An attribute representing the phone number.
     */
    public String phoneNumber;

    /**
     * An attribute representing the email.
     */
    public String email;

    /**
     * An attribute representing the password.
     */
    public String password;

    /**
     * An attribute indicating whether the first name is valid.
     */
    private boolean firstNameCorrect;

    /**
     * An attribute indicating whether the last name is valid.
     */
    private boolean lastNameCorrect;

    /**
     * An attribute indicating whether the phone number is valid.
     */
    private boolean phoneNumberCorrect;

    /**
     * An attribute indicating whether the email is valid.
     */
    private boolean emailCorrect;

    /**
     * An attribute indicating whether the password is valid.
     */
    private boolean passwordCorrect;

    /**
     * An attribute indicating whether the confirmed password is valid.
     */
    private boolean confirmPasswordCorrect;

    /**
     * An attribute storing data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();

    /**
     * An attribute indicating whether an administrator is logged in.
     */
    private boolean adminLogged;

    /**
     * An attribute representing the Client object.
     */
    private Client client;

    /**
     * An attribute representing the administrator's name.
     */
    private String adminName;

    /**
     * A constructor responsible for initializing the GUI and relevant elements.
     * @param adminLogged Parameter indicating whether an administrator is logged in.
     * @param client Parameter storing the Client object.
     */
    public Registration(boolean adminLogged, Client client) {
        this.client = client;
        this.adminLogged = adminLogged;
        initComponents();
    }

    /**
     * A constructor responsible for initializing the GUI and relevant elements.
     * @param adminLogged Parameter indicating whether an administrator is logged in.
     * @param client Parameter storing the Client object.
     * @param adminName Parameter representing the administrator's name.
     */
    public Registration(boolean adminLogged, Client client, String adminName) {
        this.client = client;
        this.adminLogged = adminLogged;
        this.adminName = adminName;
        initComponents();
    }

    /**
     * An auxiliary constructor responsible for initializing the GUI.
     */
    public Registration() {
        initComponents();
    }

    /**
     * Initializes the graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createRegPanel();
        setLabels();
        setTextFields();
        setButtons();
        createLayout();
    }

    /**
     * Sets the properties for the registration window.
     * Sets the default close operation to dispose on close, sets the title to "Rejestracja",
     * sets the preferred size of the window to a predefined dimension,
     * sets the background color to a predefined color (BEIGE),
     * and adds a window listener to handle closing actions for the window,
     * such as logging out the admin if they are currently logged in.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rejestracja");
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
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

    /**
     * Creates the registration panel with various components for user registration.
     * Sets the background color to a predefined color (LIGHT_BEIGE),
     * and sets the preferred size of the panel to a predefined dimension.
     * Configures the layout and placement of various labels, text fields, and error labels.
     * Also adds action listeners and validation logic for certain text fields.
     */
    private void createRegPanel(){
        regPanel.setBackground(ColorUtils.LIGHT_BEIGE);
        regPanel.setPreferredSize(DimensionUtils.REG_PANEL_DIMENSION);
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

    /**
     * Sets the labels for the registration panel.
     * Configures the fonts and text for various labels related to user registration.
     * Also sets the foreground color and preferred size for error labels used for validation.
     */
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

        wrongConfirmPasswordLabel.setForeground(Color.RED);
        wrongConfirmPasswordLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongPasswordLabel.setForeground(Color.RED);
        wrongPasswordLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongEmailLabel.setForeground(Color.RED);
        wrongEmailLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongPhoneNumberLabel.setForeground(Color.RED);
        wrongPhoneNumberLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongLastNameLabel.setForeground(Color.RED);
        wrongLastNameLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongFirstNameLabel.setForeground(Color.RED);
        wrongFirstNameLabel.setPreferredSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Configures focus listeners for text fields in the registration panel.
     * Performs validation actions when focus is lost on specific text fields
     * to ensure that user input is valid for first name, last name, phone number, email, password, and confirm password.
     * Displays error messages in error labels accordingly.
     */
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

    /**
     * Configures buttons in the registration panel.
     * Sets background colors, fonts, text, dimensions, and action listeners for the "Utwórz" (Submit) and "Anuluj" (Cancel) buttons.
     * Additionally, sets the cursor to a hand cursor when the button is in the rollover state.
     */
    private void setButtons(){
        submitButton.setBackground(ColorUtils.DARK_BEIGE);
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setMinimumSize(DimensionUtils.SUBMIT_CANCEL_BUTTON_DIMENSION);
        submitButton.setText("Utwórz");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        cancelButton.setBackground(ColorUtils.DARK_BEIGE);
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(DimensionUtils.SUBMIT_CANCEL_BUTTON_DIMENSION);
        cancelButton.addActionListener(this::cancelButtonActionPerformed);
        cancelButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                cancelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });
    }

    /**
     * Creates the layout for the registration window.
     * Sets the layout for the content pane and places the registration panel in the center of the window.
     * Configures the horizontal and vertical alignment of components within the panel.
     */
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
     * Handles the "Cancel" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        if (adminLogged)
            new Clients(client, adminName).setVisible(true);
        else
            new StartPage().setVisible(true);
    }

    /**
     * Handles the validation of the first name.
     */
    private void performFirstNameValidation() {
        firstName = firstNameTextField.getText();
        if (firstName.equals("")) {
            firstNameCorrect = false;
            wrongFirstNameLabel.setText("Field is required.");
        } else {
            firstNameCorrect = ClientValidator.isFirstNameValid(firstName);
            if (firstNameCorrect)
                wrongFirstNameLabel.setText("");
            else
                wrongFirstNameLabel.setText("Check if the provided first name is correct.");
        }
    }

    /**
     * Handles the validation of the last name.
     */
    private void performLastNameValidation() {
        lastName = lastNameTextField.getText();
        if (lastName.equals("")) {
            lastNameCorrect = false;
            wrongLastNameLabel.setText("Field is required.");
        } else {
            lastNameCorrect = ClientValidator.isLastNameValid(lastName);
            if (lastNameCorrect)
                wrongLastNameLabel.setText("");
            else
                wrongLastNameLabel.setText("Check if the provided last name is correct.");
        }
    }

    /**
     * Handles the validation of the phone number.
     */
    private void performPhoneNumberValidation() {
        phoneNumber = phoneNumberTextField.getText();
        phoneNumberCorrect = ClientValidator.isPhoneNumberValid(phoneNumber);
        if (phoneNumberCorrect || phoneNumber.equals(""))
            wrongPhoneNumberLabel.setText("");
        else
            wrongPhoneNumberLabel.setText("Check if the provided phone number is correct.");
    }

    /**
     * Handles the validation of the email.
     */
    private void performEmailValidation() {
        email = emailTextField.getText();
        if (email.equals("")) {
            emailCorrect = false;
            wrongEmailLabel.setText("Field is required.");
        } else {
            emailCorrect = ClientValidator.isEmailValid(email);
            if (emailCorrect)
                wrongEmailLabel.setText("");
            else
                wrongEmailLabel.setText("Check if the provided email address is correct.");
        }
    }

    /**
     * Handles the validation of the password.
     */
    private void performPasswordValidation() {
        password = new String(passwordField.getPassword());
        if (password.equals("")) {
            passwordCorrect = false;
            wrongPasswordLabel.setText("Field is required.");
        } else {
            passwordCorrect = ClientValidator.isPasswordValid(password);
            if (passwordCorrect)
                wrongPasswordLabel.setText("");
            else
                wrongPasswordLabel.setText("Minimum 8 characters including one digit, one uppercase letter, and one lowercase letter.");
        }
    }

    /**
     * Handles the validation of the confirmed password.
     */
    private void performConfirmPasswordValidation() {
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if (!confirmPasswordFromPasswordField.equals(password)) {
            wrongConfirmPasswordLabel.setText("Passwords do not match.");
            confirmPasswordCorrect = false;
        } else {
            wrongConfirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }

    /**
     * Handles the "Submit" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performFirstNameValidation();
        performLastNameValidation();
        performPhoneNumberValidation();
        performEmailValidation();
        performPasswordValidation();
        performConfirmPasswordValidation();
        if (firstNameCorrect && lastNameCorrect && phoneNumberCorrect && emailCorrect && passwordCorrect && confirmPasswordCorrect) {
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
                wrongEmailLabel.setText("A user with this email address already exists. Please provide a different one.");
                data.clear();
            } else {
                dispose();
                if (adminLogged)
                    new Clients(this.client, adminName).setVisible(true);
                else
                    new StartPage().setVisible(true);
            }
        }
    }

    /**
     * Allows the window to be launched.
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
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Registration().setVisible(true));
    }

    /**
     * A label informing that the confirmed password is incorrect.
     */
    private final javax.swing.JLabel wrongConfirmPasswordLabel = new JLabel();

    /**
     * A label informing that the email is incorrect.
     */
    private final javax.swing.JLabel wrongEmailLabel = new JLabel();

    /**
     * A label informing that the first name is incorrect.
     */
    private final javax.swing.JLabel wrongFirstNameLabel = new JLabel();

    /**
     * A label informing that the last name is incorrect.
     */
    private final javax.swing.JLabel wrongLastNameLabel = new JLabel();

    /**
     * A label informing that the password is incorrect.
     */
    private final javax.swing.JLabel wrongPasswordLabel = new JLabel();

    /**
     * A label informing that the phone number is incorrect.
     */
    private final javax.swing.JLabel wrongPhoneNumberLabel = new JLabel();

    /**
     * A button allowing the cancellation and returning to the login page.
     */
    private final javax.swing.JButton cancelButton = new JButton();

    /**
     * A text field for entering the confirmed password.
     */
    private final javax.swing.JPasswordField confirmPasswordField = new JPasswordField();

    /**
     * A text field for entering the email.
     */
    private final javax.swing.JTextField emailTextField = new JTextField();

    /**
     * A text field for entering the first name.
     */
    private final javax.swing.JTextField firstNameTextField = new JTextField();

    /**
     * A text field for entering the last name.
     */
    private final javax.swing.JTextField lastNameTextField = new JTextField();

    /**
     * A text field for entering the password.
     */
    private final javax.swing.JPasswordField passwordField = new JPasswordField();

    /**
     * A text field for entering the phone number.
     */
    private final javax.swing.JTextField phoneNumberTextField = new JTextField();

    /**
     * A button allowing the account creation.
     */
    private final javax.swing.JButton submitButton = new JButton();

    /**
     * A registration panel that contains various components for user registration.
     * Components such as labels, text fields, and buttons are placed in this panel.
     */
    private final JPanel regPanel = new JPanel();

    /**
     * A label that displays the title "Utwórz konto" (Create Account) in a larger font.
     * This label is used to indicate that the user is in the process of creating a new account.
     */
    private final JLabel createAccountLabel = new JLabel();

    /**
     * A label that indicates the input field for the user's first name.
     * It is associated with the input field where the user is expected to enter their first name.
     */
    private final JLabel firstNameLabel = new JLabel();

    /**
     * A label that indicates the input field for the user's last name.
     * It is associated with the input field where the user is expected to enter their last name.
     */
    private final JLabel lastNameLabel = new JLabel();

    /**
     * A label that indicates the input field for the user's phone number.
     * It is associated with the input field where the user is expected to enter their phone number.
     */
    private final JLabel phoneNumberLabel = new JLabel();

    /**
     * A label that indicates the input field for the user's email address.
     * It is associated with the input field where the user is expected to enter their email address.
     */
    private final JLabel emailLabel = new JLabel();

    /**
     * A label that indicates the input field for the user's password.
     * It is associated with the input field where the user is expected to enter their password.
     */
    private final JLabel passwordLabel = new JLabel();

    /**
     * A label that indicates the input field for confirming the user's password.
     * It is associated with the input field where the user is expected to re-enter their password for confirmation.
     */
    private final JLabel confirmPasswordLabel = new JLabel();
}
