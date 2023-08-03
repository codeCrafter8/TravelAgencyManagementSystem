package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.client.validation.ClientValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * Class containing fields and methods for handling the password change window for the client.
 */
public class PasswordChange extends javax.swing.JFrame {
    /**
     * Attribute determining whether the current client password is correct.
     */
    private boolean currentPasswordCorrect;
    /**
     * Attribute determining whether the new client password is correct.
     */
    private boolean newPasswordCorrect;
    /**
     * Attribute determining whether the confirmed client password is correct.
     */
    private boolean confirmPasswordCorrect;
    /**
     * Attribute representing the new client password.
     */
    private String newPassword;
    /**
     * Attribute representing a list storing data passed to the Client class.
     */
    public List<String> data = new ArrayList<>();
    /**
     * Attribute representing the client ID.
     */
    private final String clientId;
    /**
     * Attribute representing an instance of the Client class.
     */
    private final Client client;
    /**
     * Constructor responsible for initializing the GUI and relevant attributes.
     * @param clientId parameter storing the ID of the client whose password is being changed.
     * @param client parameter storing an instance of the Client class.
     */
    public PasswordChange(String clientId, Client client) {
        this.client = client;
        this.clientId = clientId;
        initComponents();
    }
    /**
     * Method initializing graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButton();
        createLayout();
    }

    /**
     * Sets the window properties for the password change window.
     * The window will be disposed (closed) when the user clicks the close button.
     * The window title will be set to "Zmiana hasła".
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmiana hasła");
    }

    /**
     * Sets the labels and their fonts for the password change window.
     * Configures the labels for "Aktualne hasło", "Podaj nowe hasło", and "Potwierdź nowe hasło".
     * Also sets up labels to display error messages when the entered passwords are incorrect.
     */
    private void setLabels(){
        currentPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        currentPasswordLabel.setText("Aktualne hasło:");

        newPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        newPasswordLabel.setText("Podaj nowe hasło:");

        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        confirmPasswordLabel.setText("Potwierdź nowe hasło:");

        wrongCurrentPasswordLabel.setForeground(Color.RED);
        wrongCurrentPasswordLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);

        wrongNewPasswordLabel.setForeground(Color.RED);
        wrongNewPasswordLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
        wrongNewPasswordLabel.setText("");

        wrongConfirmPasswordLabel.setForeground(Color.RED);
        wrongConfirmPasswordLabel.setMinimumSize(DimensionUtils.WRONG_ITEM_LABEL_DIMENSION);
    }

    /**
     * Sets the properties of the "Zmień hasło" button in the password change window.
     * Configures the button's appearance, font, text, and action listener to handle password change.
     */
    private void setButton(){
        changeButton.setBackground(ColorUtils.LIGHT_BROWN);
        changeButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        changeButton.setForeground(Color.WHITE);
        changeButton.setText("Zmień hasło");
        changeButton.addActionListener(this::changeButtonActionPerformed);
    }

    /**
     * Creates the layout for the password change window.
     * Places labels and input fields for current password, new password, and confirm password.
     * Also includes error message labels and the "Zmień hasło" button.
     */
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(11, 11, 11)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(confirmPasswordLabel)
                                                        .addComponent(newPasswordLabel)
                                                        .addComponent(currentPasswordLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(wrongCurrentPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wrongNewPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(wrongConfirmPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(currentPasswordField)
                                                        .addComponent(newPasswordField)
                                                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(changeButton)))
                                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(currentPasswordLabel)
                                        .addComponent(currentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(wrongCurrentPasswordLabel)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(newPasswordLabel)
                                        .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(wrongNewPasswordLabel)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(confirmPasswordLabel)
                                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(2, 2, 2)
                                .addComponent(wrongConfirmPasswordLabel)
                                .addGap(18, 18, 18)
                                .addComponent(changeButton)
                                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method responsible for validating the current password entered by the client.
     */
    private void performCurrentPasswordValidation() {
        String passwordFromPasswordField = new String(currentPasswordField.getPassword());
        if (passwordFromPasswordField.equals("")) {
            currentPasswordCorrect = false;
            wrongCurrentPasswordLabel.setText("This field is required.");
        } else if (!(client.getUserPassword()).equals(passwordFromPasswordField)) {
            wrongCurrentPasswordLabel.setText("Incorrect password.");
            currentPasswordCorrect = false;
        } else {
            wrongCurrentPasswordLabel.setText("");
            currentPasswordCorrect = true;
        }
    }
    /**
     * Method responsible for validating the new password entered by the client.
     */
    private void performNewPasswordValidation() {
        newPassword = new String(newPasswordField.getPassword());
        if (newPassword.equals("")) {
            newPasswordCorrect = false;
            wrongNewPasswordLabel.setText("This field is required.");
        } else {
            newPasswordCorrect = ClientValidator.isPasswordValid(newPassword);
            if (newPasswordCorrect)
                wrongNewPasswordLabel.setText("");
            else
                wrongNewPasswordLabel.setText("The password does not meet the requirements.");
        }
    }
    /**
     * Method responsible for validating the confirmed password entered by the client.
     */
    private void performConfirmPasswordValidation() {
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if (!confirmPasswordFromPasswordField.equals(newPassword)) {
            wrongConfirmPasswordLabel.setText("Passwords do not match.");
            confirmPasswordCorrect = false;
        } else {
            wrongConfirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }
    /**
     * Method handling the "Change Password" button click event.
     * @param evt The event received when the button is clicked.
     */
    private void changeButtonActionPerformed(ActionEvent evt) {
        performCurrentPasswordValidation();
        performNewPasswordValidation();
        performConfirmPasswordValidation();
        if (currentPasswordCorrect) {
            if (newPasswordCorrect && confirmPasswordCorrect) {
                data.clear();
                data.add("changeClientPassword");
                data.add(newPassword);
                data.add(clientId);
                new Client(data);
                data.clear();
                dispose();
                JOptionPane.showMessageDialog(null, "Password successfully changed.", "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    /**
     * Method allowing the window to be launched.
     * @param args The arguments passed when the application is launched.
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
            java.util.logging.Logger.getLogger(PasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new PasswordChange(null, null).setVisible(true));
    }

    /**
     * Field for entering the confirmed client password.
     */
    private final javax.swing.JPasswordField confirmPasswordField = new JPasswordField();
    /**
     * Label indicating that the confirmed client password is incorrect.
     */
    private final javax.swing.JLabel wrongConfirmPasswordLabel = new JLabel();
    /**
     * Field for entering the current client password.
     */
    private final javax.swing.JPasswordField currentPasswordField = new JPasswordField();
    /**
     * Field for entering the new client password.
     */
    private final javax.swing.JPasswordField newPasswordField = new JPasswordField();
    /**
     * Label indicating that the new client password is incorrect.
     */
    private final javax.swing.JLabel wrongNewPasswordLabel = new JLabel();
    /**
     * Label indicating that the current client password is incorrect.
     */
    private final javax.swing.JLabel wrongCurrentPasswordLabel = new JLabel();
    /**
     * Attribute determining whether the current client password is correct.
     */
    private final JLabel currentPasswordLabel = new JLabel();
    /**
     * A label for displaying the "New Password" text in the change password panel.
     */
    private final JLabel newPasswordLabel = new JLabel();

    /**
     * A label for displaying the "Confirm Password" text in the change password panel.
     */
    private final JLabel confirmPasswordLabel = new JLabel();

    /**
     * A button for initiating the password change process in the change password panel.
     */
    private final JButton changeButton = new JButton();
}