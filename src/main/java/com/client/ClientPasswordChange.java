package com.client;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zmiany hasła użytkownika przez administratora
 */
public class ClientPasswordChange extends javax.swing.JFrame {
    /**
     * Atrybut będący komponentem tekstowym wyspecjalizowanym do wprowadzania hasła
     */
    private javax.swing.JPasswordField confirmPasswordField;
    /**
     * Atrybut będący komponentem tekstowym wyspecjalizowanym do wprowadzania hasła
     */
    private javax.swing.JPasswordField newPasswordField;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    private javax.swing.JLabel wrongConfirmNewPasswordLabel;
    /**
     * Atrybut będący komponentem do umieszczania tekstu w kontenerze
     */
    private javax.swing.JLabel wrongNewPasswordLabel;
    /**
     * Atrybut przechowujący id użytkownika, którego hasło jest zmieniane
     */
    private final int idClient;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    public List<String> data = new ArrayList<>();
    /**
     * Atrybut przechowujący nowe hasło użytkownika, którego hasło jest zmieniane
     */
    private String newPassword;
    /**
     * Atrybut określający, czy nowe hasło użytkownika jest poprawne
     */
    private boolean newPasswordCorrect;
    /**
     * Atrybut określający, czy potwierdzone hasło użytkownika jest poprawne
     */
    private boolean confirmPasswordCorrect;
    /**
     * Atrybut będący obiektem klasy Validation
     */
    private final Validation validation;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich atrybutów
     * @param idClient parametr przechowujący id użytkownika, którego hasło jest zmieniane
     */
    public ClientPasswordChange(int idClient) {
        this.idClient = idClient;
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(247,233,201));
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        javax.swing.JLabel newPasswordLabel = new javax.swing.JLabel();
        javax.swing.JLabel confirmNewPasswordLabel = new javax.swing.JLabel();
        javax.swing.JButton changePasswordButton = new javax.swing.JButton();
        wrongNewPasswordLabel = new javax.swing.JLabel();
        wrongConfirmNewPasswordLabel = new javax.swing.JLabel();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmień hasło klienta");
        setBackground(new java.awt.Color(247, 233, 201));
        setPreferredSize(new java.awt.Dimension(330, 300));

        newPasswordLabel.setText("Nowe hasło");

        confirmNewPasswordLabel.setText("Powtórz nowe hasło");

        changePasswordButton.setBackground(new java.awt.Color(189, 165, 111));
        changePasswordButton.setText("Zmień hasło");
        changePasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordButton.addActionListener(this::changePasswordButtonActionPerformed);

        wrongNewPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongNewPasswordLabel.setPreferredSize(new java.awt.Dimension(205, 16));

        wrongConfirmNewPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongConfirmNewPasswordLabel.setPreferredSize(new java.awt.Dimension(205, 16));

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
     * Metoda odpowiadająca za przeprowadzenie walidacji nowego hasła użytkownika
     */
    private void performPasswordValidation(){
        newPassword = new String(newPasswordField.getPassword());
        if(newPassword.equals("")) {
            newPasswordCorrect = false;
            wrongNewPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            newPasswordCorrect = validation.passwordIsValid(newPassword);
            if (newPasswordCorrect)
                wrongNewPasswordLabel.setText("");
            else
                wrongNewPasswordLabel.setText("Hasło nie spełnia wymagań.");
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji potwierdzonego hasła użytkownika
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
     * Metoda obsługująca kliknięcie przycisku "Zmień hasło klienta"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        performPasswordValidation();
        performConfirmPasswordValidation();
        if(newPasswordCorrect){
            if(confirmPasswordCorrect){
                data.add(newPassword);
                data.add(Integer.toString(idClient));
                new Client("changeClientPassword",data);
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
}