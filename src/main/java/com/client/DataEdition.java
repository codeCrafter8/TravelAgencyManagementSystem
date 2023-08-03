package com.client;

import com.client.utils.ColorUtils;
import com.client.validation.ClientValidator;
import com.server.LogsClients;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class containing fields and methods for handling the window with functionality for data edition by the client.
 */
class DataEdition extends javax.swing.JFrame {
    private static final Dimension VALIDATION_LABEL_DIMENSION = new Dimension(363, 16);
    private static final Dimension WINDOW_DIMENSION = new Dimension(449, 510);
    /**
     * Attribute determining if the client's first name is correct.
     */
    private boolean firstNameCorrect;
    /**
     * Attribute determining if the client's email is correct.
     */
    private boolean emailCorrect;
    /**
     * Attribute determining if the client's phone number is correct.
     */
    private boolean phoneNumberCorrect;
    /**
     * Attribute determining if the client's last name is correct.
     */
    private boolean lastNameCorrect;
    /**
     * Attribute storing the first name of the client whose data is being edited.
     */
    private String firstName;
    /**
     * Attribute storing the last name of the client whose data is being edited.
     */
    private String lastName;
    /**
     * Attribute storing the email of the client whose data is being edited.
     */
    private String email;
    /**
     * Attribute storing the phone number of the client whose data is being edited.
     */
    private String phoneNumber;
    /**
     * Attribute being a list that stores data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Attribute storing the ID of the client whose data is being edited.
     */
    private final String clientId;
    /**
     * Attribute being a list that stores client data.
     */
    private final List<String> clientData = new ArrayList<>();
    /**
     * Attribute being an object of the Client class.
     */
    private final Client client;
    /**
     * Constructor responsible for GUI initialization and relevant attributes.
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
     * Method for initializing graphic components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setTextFields();
        createLabels();
        createButton();
        createRegPanel();
        createLayout();
    }
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

    private void setTextFields() {
        firstNameTextField.setText(clientData.get(0));
        lastNameTextField.setText(clientData.get(1));
        emailTextField.setText(clientData.get(2));
        phoneNumberField.setText(clientData.get(3));
    }

    private void createButton() {
        submitButton.setBackground(ColorUtils.LIGHT_BROWN);
        submitButton.setBackground(ColorUtils.LIGHT_BROWN);
        submitButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        submitButton.setForeground(Color.white);
        submitButton.setText("Zatwierdź");
        submitButton.addActionListener(this::submitButtonActionPerformed);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
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
     * Method responsible for performing first name validation for the client.
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
     * Method responsible for performing last name validation for the client.
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
     * Method responsible for performing email validation for the client.
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
     * Method responsible for performing phone number validation for the client.
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
     * Method handling the "Submit" button click.
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
     * Method allowing the window to be launched.
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
    // GUI variables
    private final JLabel createLabel = new JLabel();
    private final JLabel firstNameLabel = new JLabel();
    private final JLabel lastNameLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel phoneNumberLabel = new JLabel();
    private final JButton submitButton = new JButton();
    /**
     * Text field for entering the client's email.
     */
    private final JTextField emailTextField = new JTextField();
    /**
     * Text field for entering the client's first name.
     */
    private final JTextField firstNameTextField = new JTextField();
    /**
     * Text field for entering the client's last name.
     */
    private final JTextField lastNameTextField = new JTextField();
    /**
     * Text field for entering the client's phone number.
     */
    private final JTextField phoneNumberField = new JTextField();
    /**
     * Label indicating that the client's email is invalid.
     */
    private final JLabel validEmailLabel = new JLabel();
    /**
     * Label indicating that the client's first name is invalid.
     */
    private final JLabel validFirstNameLabel = new JLabel();
    /**
     * Label indicating that the client's last name is invalid.
     */
    private final JLabel validLastNameLabel = new JLabel();
    /**
     * Label indicating that the client's phone number is invalid.
     */
    private final JLabel validPhoneNumberLabel = new JLabel();
    /**
     * Panel for the registration form.
     */
    private final JPanel regPanel = new JPanel();

}