package com.client;

import com.client.utils.ColorUtils;
import com.client.validation.ClientValidator;
import com.server.LogsClients;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing fields and methods for handling the window with functionality for data edition by the client.
 */
class DataEdition extends javax.swing.JFrame {

    /**
     * A dimension representing the size of validation labels used in the data edition form.
     * It specifies the width and height of the validation labels used for displaying error messages.
     */
    private static final Dimension VALIDATION_LABEL_DIMENSION = new Dimension(363, 16);

    /**
     * A dimension representing the size of the data edition window.
     * It specifies the width and height of the window used for editing client data.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(449, 510);

    /**
     * An attribute determining if the client's first name is correct.
     */
    private boolean firstNameCorrect;
    
    /**
     * An attribute determining if the client's email is correct.
     */
    private boolean emailCorrect;
    
    /**
     * An attribute determining if the client's phone number is correct.
     */
    private boolean phoneNumberCorrect;
    
    /**
     * An attribute determining if the client's last name is correct.
     */
    private boolean lastNameCorrect;
    
    /**
     * An attribute storing the first name of the client whose data is being edited.
     */
    private String firstName;
    
    /**
     * An attribute storing the last name of the client whose data is being edited.
     */
    private String lastName;
    
    /**
     * An attribute storing the email of the client whose data is being edited.
     */
    private String email;
    
    /**
     * An attribute storing the phone number of the client whose data is being edited.
     */
    private String phoneNumber;
    
    /**
     * An attribute being a list that stores data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    
    /**
     * An attribute storing the ID of the client whose data is being edited.
     */
    private final String clientId;
    
    /**
     * An attribute being a list that stores client data.
     */
    private final List<String> clientData = new ArrayList<>();
    
    /**
     * An attribute being an object of the Client class.
     */
    private final Client client;
    
    /**
     * A constructor responsible for GUI initialization and relevant attributes.
     *
     * @param clientData list storing client data
     * @param client     object of the Client class
     */
    public DataEdition(List<String> clientData, Client client) {
        this.client = client;
        this.clientData.addAll(clientData);
        this.clientId = clientData.get(4);
        initComponents();
    }
    
    /**
     * Initializes graphic components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setTextFields();
        createLabels();
        createButton();
        createRegPanel();
        createLayout();
    }

    /**
     * Sets the properties of the window for the data edition form.
     * It configures the close operation, title, preferred size, and makes the window non-resizable.
     * Also, it adds a WindowListener to handle the closing event, which navigates to the MyAccount window.
     */
    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edytuj dane");
        setPreferredSize(WINDOW_DIMENSION);
        setResizable(false);
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
    }


    /**
     * Creates and configures the labels used in the data edition form.
     * This method sets the fonts and text for various labels in the form.
     */
    private void createLabels() {
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

        validFirstNameLabel.setForeground(Color.RED);
        validFirstNameLabel.setMaximumSize(VALIDATION_LABEL_DIMENSION);
        validFirstNameLabel.setMinimumSize(VALIDATION_LABEL_DIMENSION);
        validFirstNameLabel.setPreferredSize(VALIDATION_LABEL_DIMENSION);

        validLastNameLabel.setForeground(Color.RED);
        validLastNameLabel.setMaximumSize(VALIDATION_LABEL_DIMENSION);
        validLastNameLabel.setMinimumSize(VALIDATION_LABEL_DIMENSION);
        validLastNameLabel.setPreferredSize(VALIDATION_LABEL_DIMENSION);

        validEmailLabel.setForeground(Color.RED);
        validEmailLabel.setMaximumSize(VALIDATION_LABEL_DIMENSION);
        validEmailLabel.setMinimumSize(VALIDATION_LABEL_DIMENSION);
        validEmailLabel.setPreferredSize(VALIDATION_LABEL_DIMENSION);

        validPhoneNumberLabel.setForeground(Color.RED);
        validPhoneNumberLabel.setMaximumSize(VALIDATION_LABEL_DIMENSION);
        validPhoneNumberLabel.setMinimumSize(VALIDATION_LABEL_DIMENSION);
        validPhoneNumberLabel.setPreferredSize(VALIDATION_LABEL_DIMENSION);
    }

    /**
     * Sets the text fields with the existing client data.
     * This method populates the text fields with the client's data for editing.
     */
    private void setTextFields() {
        firstNameTextField.setText(clientData.get(0));
        lastNameTextField.setText(clientData.get(1));
        emailTextField.setText(clientData.get(2));
        phoneNumberField.setText(clientData.get(3));
    }

    /**
     * Creates and configures the submit button for the data edition form.
     * This method sets the background, font, text, action listener, and cursor for the submit button.
     */
    private void createButton() {
        submitButton.setBackground(ColorUtils.LIGHT_BROWN);
        submitButton.setBackground(ColorUtils.LIGHT_BROWN);
        submitButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        submitButton.setForeground(Color.white);
        submitButton.setText("Zatwierdź");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Creates and configures the main registration panel for the data edition form.
     * This method sets the size, layout, and components within the registration panel.
     */
    private void createRegPanel(){
        regPanel.setPreferredSize(new java.awt.Dimension(450, 550));
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
    }

    /**
     * Creates the layout for the data edition form.
     * This method sets the layout of the content pane, arranging the main registration panel within it.
     */
    private void createLayout() {
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
     * Performs first name validation for the client.
     */
    private void performFirstNameValidation() {
        firstName = firstNameTextField.getText();
        if (firstName.equals("")) {
            firstNameCorrect = false;
            validFirstNameLabel.setText("This field is required.");
        } else {
            firstNameCorrect = ClientValidator.isFirstNameValid(firstName);
            if (firstNameCorrect)
                validFirstNameLabel.setText("");
            else
                validFirstNameLabel.setText("Please check if the entered first name is valid.");
        }
    }

    /**
     * Performs last name validation for the client.
     */
    private void performLastNameValidation() {
        lastName = lastNameTextField.getText();
        if (lastName.equals("")) {
            lastNameCorrect = false;
            validLastNameLabel.setText("This field is required.");
        } else {
            lastNameCorrect = ClientValidator.isLastNameValid(lastName);
            if (lastNameCorrect)
                validLastNameLabel.setText("");
            else
                validLastNameLabel.setText("Please check if the entered last name is valid.");
        }
    }

    /**
     * Performs email validation for the client.
     */
    private void performEmailValidation() {
        email = emailTextField.getText();
        if (email.equals("")) {
            emailCorrect = false;
            validEmailLabel.setText("This field is required.");
        } else {
            emailCorrect = ClientValidator.isEmailValid(email);
            if (emailCorrect)
                validEmailLabel.setText("");
            else
                validEmailLabel.setText("Please check if the entered email is valid.");
        }
    }

    /**
     * Performs phone number validation for the client.
     */
    public void performPhoneNumberValidation() {
        phoneNumber = phoneNumberField.getText();
        phoneNumberCorrect = ClientValidator.isPhoneNumberValid(phoneNumber);
        if (phoneNumberCorrect || phoneNumber.equals(""))
            validPhoneNumberLabel.setText("");
        else
            validPhoneNumberLabel.setText("Please check if the entered phone number is valid.");
    }

    /**
     * Handles the "Submit" button click.
     * @param evt The event received when the button is clicked.
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performFirstNameValidation();
        performLastNameValidation();
        performEmailValidation();
        performPhoneNumberValidation();
        if (firstNameCorrect && lastNameCorrect && emailCorrect && phoneNumberCorrect) {
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
     * Allows the window to be launched.
     * @param args Arguments received when launching the application.
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

    /**
     * A label used to create a new label.
     * It is a generic label used for various purposes throughout the application.
     */
    private final JLabel createLabel = new JLabel();

    /**
     * A label that displays the text "First Name".
     * It indicates the label used to represent the first name input field.
     */
    private final JLabel firstNameLabel = new JLabel();

    /**
     * A label that displays the text "Last Name".
     * It indicates the label used to represent the last name input field.
     */
    private final JLabel lastNameLabel = new JLabel();

    /**
     * A label that displays the text "Email".
     * It indicates the label used to represent the email input field.
     */
    private final JLabel emailLabel = new JLabel();

    /**
     * A label that displays the text "Phone Number".
     * It indicates the label used to represent the phone number input field.
     */
    private final JLabel phoneNumberLabel = new JLabel();

    /**
     * A button used to submit the form or action.
     * It is used to submit the data entered in the form or to perform a specific action.
     */
    private final JButton submitButton = new JButton();

    /**
     * A text field for entering the client's email.
     */
    private final JTextField emailTextField = new JTextField();

    /**
     * A text field for entering the client's first name.
     */
    private final JTextField firstNameTextField = new JTextField();

    /**
     * A text field for entering the client's last name.
     */
    private final JTextField lastNameTextField = new JTextField();

    /**
     * A text field for entering the client's phone number.
     */
    private final JTextField phoneNumberField = new JTextField();

    /**
     * A label indicating that the client's email is invalid.
     */
    private final JLabel validEmailLabel = new JLabel();

    /**
     * A label indicating that the client's first name is invalid.
     */
    private final JLabel validFirstNameLabel = new JLabel();

    /**
     * A label indicating that the client's last name is invalid.
     */
    private final JLabel validLastNameLabel = new JLabel();

    /**
     * A label indicating that the client's phone number is invalid.
     */
    private final JLabel validPhoneNumberLabel = new JLabel();

    /**
     * A panel for the registration form.
     */
    private final JPanel regPanel = new JPanel();

}