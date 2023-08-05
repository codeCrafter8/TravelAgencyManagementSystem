package com.client;

import com.client.utils.ColorUtils;
import com.client.validation.ClientValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing fields and methods for handling the client password change window by the administrator.
 */
public class ClientPasswordChange extends javax.swing.JFrame {

    /**
     * A dimension representing the size of labels used in this window.
     * It specifies the width and height of the labels used for displaying text or captions.
     */
    private static final Dimension LABEL_DIMENSION = new Dimension(205, 16);

    /**
     * A dimension representing the size of this window.
     * It specifies the width and height of the window used for displaying content or user interfaces.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(330, 300);

    /**
     * An attribute holding the client ID whose password is being changed.
     */
    private final int clientId;

    /**
     * An attribute that is a list holding data passed to the Client class.
     */
    public List<String> data = new ArrayList<>();

    /**
     * An attribute holding the new password of the client whose password is being changed.
     */
    private String newPassword;

    /**
     * An attribute indicating whether the new client password is valid.
     */
    private boolean newPasswordCorrect;

    /**
     * An attribute indicating whether the confirmed client password is valid.
     */
    private boolean confirmPasswordCorrect;

    /**
     * A constructor responsible for initializing the GUI and its components.
     * @param clientId parameter holding the client ID whose password is being changed.
     */
    public ClientPasswordChange(int clientId) {
        this.clientId = clientId;
        initComponents();
    }
    /**
     * Initializes graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButton();
        createLayout();
    }

    /**
     * Sets the default close operation, title, background color,
     * and preferred window dimension.
     */
    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmień hasło klienta");
        getContentPane().setBackground(ColorUtils.LIGHT_BEIGE);
        setPreferredSize(WINDOW_DIMENSION);
    }

    /**
     * Sets the text and preferred size for various labels used in the window.
     */
    private void setLabels() {
        newPasswordLabel.setText("Nowe hasło");
        confirmNewPasswordLabel.setText("Powtórz nowe hasło");
        wrongNewPasswordLabel.setForeground(Color.RED);
        wrongNewPasswordLabel.setPreferredSize(LABEL_DIMENSION);
        wrongConfirmNewPasswordLabel.setForeground(Color.RED);
        wrongConfirmNewPasswordLabel.setPreferredSize(LABEL_DIMENSION);
    }

    /**
     * Sets the background color, text, cursor, and action listener
     * for the "Change Password" button.
     */
    private void setButton() {
        changePasswordButton.setBackground(ColorUtils.DARK_BEIGE);
        changePasswordButton.setText("Zmień hasło");
        changePasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordButton.addActionListener(this::changePasswordButtonActionPerformed);
    }

    /**
     * Sets up the GroupLayout for arranging the components in the window.
     */
    private void createLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(105, 105, 105)
                                                .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(confirmNewPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(newPasswordField, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(newPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(wrongNewPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                                                        .addComponent(wrongConfirmNewPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(newPasswordLabel)
                                .addGap(3, 3, 3)
                                .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongNewPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(confirmNewPasswordLabel)
                                .addGap(3, 3, 3)
                                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(wrongConfirmNewPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))
        );
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Validates the new client password.
     */
    private void performPasswordValidation() {
        newPassword = new String(newPasswordField.getPassword());
        if (newPassword.equals("")) {
            newPasswordCorrect = false;
            wrongNewPasswordLabel.setText("Pole jest wymagane.");
        } else {
            newPasswordCorrect = ClientValidator.isPasswordValid(newPassword);
            if (newPasswordCorrect)
                wrongNewPasswordLabel.setText("");
            else
                wrongNewPasswordLabel.setText("Hasło nie spełnia wymagań.");
        }
    }

    /**
     * Validates the confirmed client password.
     */
    private void performConfirmPasswordValidation() {
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if (!confirmPasswordFromPasswordField.equals(newPassword)) {
            wrongConfirmNewPasswordLabel.setText("Hasła nie zgadzają się.");
            confirmPasswordCorrect = false;
        } else {
            wrongConfirmNewPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }

    /**
     * Handles the click event of the "Change Password" button.
     * @param evt Event generated on button click.
     */
    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performPasswordValidation();
        performConfirmPasswordValidation();
        if (newPasswordCorrect) {
            if (confirmPasswordCorrect) {
                data.clear();
                data.add("changeClientPassword");
                data.add(newPassword);
                data.add(Integer.toString(clientId));
                new Client(data);
                data.clear();
                dispose();
            }
        }
    }

    /**
     * Allows to run the window.
     * @param args Arguments received when running the application.
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
            java.util.logging.Logger.getLogger(ClientPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ClientPasswordChange(-1).setVisible(true));
    }

    /**
     * A button used for changing the client's password.
     * This button allows the admin to submit the new password for the client.
     */
    private final javax.swing.JButton changePasswordButton = new javax.swing.JButton();

    /**
     * A label for displaying the "New Password" text.
     * This label is used to prompt the user to enter the new password for the client.
     */
    private final javax.swing.JLabel newPasswordLabel = new javax.swing.JLabel();
    /**
     * A label for confirming the new password
     */
    private final javax.swing.JLabel confirmNewPasswordLabel = new javax.swing.JLabel();
    /**
     * A field for entering the confirmed password of the client.
     */
    private final javax.swing.JPasswordField confirmPasswordField = new javax.swing.JPasswordField();
    /**
     * A field for entering the new password of the client.
     */
    private final javax.swing.JPasswordField newPasswordField = new javax.swing.JPasswordField();
    /**
     * A label indicating that the confirmed password of the client is incorrect.
     */
    private final javax.swing.JLabel wrongConfirmNewPasswordLabel = new javax.swing.JLabel();
    /**
     * A label indicating that the new password of the client is incorrect.
     */
    private final javax.swing.JLabel wrongNewPasswordLabel = new javax.swing.JLabel();
}
