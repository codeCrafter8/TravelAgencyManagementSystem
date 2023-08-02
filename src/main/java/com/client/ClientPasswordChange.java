package com.client;

import com.client.utils.ColorUtils;
import com.client.validation.ClientValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zmiany hasła klienta przez administratora
 */
public class ClientPasswordChange extends javax.swing.JFrame {
    private static final Dimension LABEL_DIMENSION = new java.awt.Dimension(205, 16);
    private static final Dimension WINDOW_DIMENSION = new java.awt.Dimension(330, 300);
    /**
     * Atrybut przechowujący id klienta, którego hasło jest zmieniane
     */
    private final int clientId;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    public List<String> data = new ArrayList<>();
    /**
     * Atrybut przechowujący nowe hasło klienta, którego hasło jest zmieniane
     */
    private String newPassword;
    /**
     * Atrybut określający, czy nowe hasło klienta jest poprawne
     */
    private boolean newPasswordCorrect;
    /**
     * Atrybut określający, czy potwierdzone hasło klienta jest poprawne
     */
    private boolean confirmPasswordCorrect;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param clientId parametr przechowujący id klienta, którego hasło jest zmieniane
     */
    public ClientPasswordChange(int clientId) {
        this.clientId = clientId;
        initComponents();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButton();
        createLayout();
    }

    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmień hasło klienta");
        getContentPane().setBackground(ColorUtils.LIGHT_BEIGE);
        setPreferredSize(WINDOW_DIMENSION);
    }

    private void setLabels() {
        newPasswordLabel.setText("Nowe hasło");
        confirmNewPasswordLabel.setText("Powtórz nowe hasło");
        wrongNewPasswordLabel.setForeground(Color.RED);
        wrongNewPasswordLabel.setPreferredSize(LABEL_DIMENSION);
        wrongConfirmNewPasswordLabel.setForeground(Color.RED);
        wrongConfirmNewPasswordLabel.setPreferredSize(LABEL_DIMENSION);
    }

    private void setButton() {
        changePasswordButton.setBackground(ColorUtils.DARK_BEIGE);
        changePasswordButton.setText("Zmień hasło");
        changePasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordButton.addActionListener(this::changePasswordButtonActionPerformed);
    }

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
     * Metoda odpowiadająca za przeprowadzenie walidacji nowego hasła klienta
     */
    private void performPasswordValidation(){
        newPassword = new String(newPasswordField.getPassword());
        if(newPassword.equals("")) {
            newPasswordCorrect = false;
            wrongNewPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            newPasswordCorrect = ClientValidator.isPasswordValid(newPassword);
            if (newPasswordCorrect)
                wrongNewPasswordLabel.setText("");
            else
                wrongNewPasswordLabel.setText("Hasło nie spełnia wymagań.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji potwierdzonego hasła klienta
     */
    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(newPassword)){
            wrongConfirmNewPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            wrongConfirmNewPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Zmień hasło"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performPasswordValidation();
        performConfirmPasswordValidation();
        if(newPasswordCorrect){
            if(confirmPasswordCorrect){
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
            java.util.logging.Logger.getLogger(ClientPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ClientPasswordChange(-1).setVisible(true));
    }
    //GUI variables
    private final javax.swing.JButton changePasswordButton = new javax.swing.JButton();
    private final javax.swing.JLabel newPasswordLabel = new javax.swing.JLabel();
    private final javax.swing.JLabel confirmNewPasswordLabel = new javax.swing.JLabel();
    /**
     * Pole do wprowadzenia potwierdzonego hasła klienta
     */
    private final javax.swing.JPasswordField confirmPasswordField = new javax.swing.JPasswordField();
    /**
     * Pole do wprowadzenia nowego hasła klienta
     */
    private final javax.swing.JPasswordField newPasswordField = new javax.swing.JPasswordField();
    /**
     * Etykieta informująca, że potwierdzone hasło klienta jest niepoprawne
     */
    private final javax.swing.JLabel wrongConfirmNewPasswordLabel = new javax.swing.JLabel();
    /**
     * Etykieta informująca, że nowe hasło klienta jest niepoprawne
     */
    private final javax.swing.JLabel wrongNewPasswordLabel = new javax.swing.JLabel();
}