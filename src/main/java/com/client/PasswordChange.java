package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zmiany hasła przez klienta
 */
public class PasswordChange extends javax.swing.JFrame {
    /**
     * Pole do wprowadzenia potwierdzonego hasła klienta
     */
    private javax.swing.JPasswordField confirmPasswordField;
    /**
     * Etykieta informująca, że potwierdzone hasło klienta jest niepoprawne
     */
    private javax.swing.JLabel wrongConfirmPasswordLabel;
    /**
     * Pole do wprowadzenia obecnego hasła klienta
     */
    private javax.swing.JPasswordField currentPasswordField;
    /**
     * Pole do wprowadzenia nowego hasła klienta
     */
    private javax.swing.JPasswordField newPasswordField;
    /**
     * Etykieta informująca, że nowe hasło klienta jest niepoprawne
     */
    private javax.swing.JLabel wrongNewPasswordLabel;
    /**
     * Etykieta informująca, że obecne hasło klienta jest niepoprawne
     */
    private javax.swing.JLabel wrongCurrentPasswordLabel;
    /**
     * Atrybut określający, czy obecne hasło klienta jest poprawne
     */
    private boolean currentPasswordCorrect;
    /**
     * Atrybut określający, czy nowe hasło klienta jest poprawne
     */
    private boolean newPasswordCorrect;
    /**
     * Atrybut określający, czy potwierdzone hasło klienta jest poprawne
     */
    private boolean confirmPasswordCorrect;
    /**
     * Atrybut będący obiektem klasy Validation
     */
    private final Validation validation;
    /**
     * Atrybut będący nowym hasłem klienta
     */
    private String newPassword;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    public List<String> data = new ArrayList<>();
    /**
     * Atrybut będący id klienta
     */
    private final String clientId;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private final Client client;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich atrybutów
     * @param clientId parametr przechowujący id klienta, którego hasło jest zmieniane
     * @param client parametr przechowujący obiekt klasy Client
     */
    public PasswordChange(String clientId, Client client) {
        this.client = client;
        this.clientId = clientId;
        validation = new Validation();
        initComponents();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        JLabel currentPassword = new JLabel();
        JLabel newPassword = new JLabel();
        JLabel confirmPassword = new JLabel();
        JButton changeButton = new JButton();
        wrongCurrentPasswordLabel = new javax.swing.JLabel();
        wrongNewPasswordLabel = new javax.swing.JLabel();
        wrongConfirmPasswordLabel = new javax.swing.JLabel();
        currentPasswordField = new javax.swing.JPasswordField();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmiana hasła");

        currentPassword.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        currentPassword.setText("Aktualne hasło:");

        newPassword.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        newPassword.setText("Podaj nowe hasło:");

        confirmPassword.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        confirmPassword.setText("Potwierdź nowe hasło:");

        changeButton.setBackground(new java.awt.Color(151, 123, 92));
        changeButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        changeButton.setForeground(new java.awt.Color(255, 255, 255));
        changeButton.setText("Zmień hasło");
        changeButton.addActionListener(this::changeButtonActionPerformed);

        wrongCurrentPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongCurrentPasswordLabel.setText("cos");

        wrongNewPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongNewPasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");

        wrongConfirmPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongConfirmPasswordLabel.setText("cos");

        wrongCurrentPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        wrongNewPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        wrongConfirmPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        wrongCurrentPasswordLabel.setText("");
        wrongNewPasswordLabel.setText("");
        wrongConfirmPasswordLabel.setText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(confirmPassword)
                            .addComponent(newPassword)
                            .addComponent(currentPassword))
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
                    .addComponent(currentPassword)
                    .addComponent(currentPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(wrongCurrentPasswordLabel)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPassword)
                    .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(wrongNewPasswordLabel)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPassword)
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
     * Metoda odpowiadająca za przeprowadzenie walidacji obecnego hasła klienta
     */
    private void performCurrentPasswordValidation(){
        String passwordFromPasswordField = new String(currentPasswordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            currentPasswordCorrect = false;
            wrongCurrentPasswordLabel.setText("Pole jest wymagane.");
        }
        else if(!(client.getUserPassword()).equals(passwordFromPasswordField)){
            wrongCurrentPasswordLabel.setText("Błędne hasło.");
            currentPasswordCorrect = false;
        }
        else{
            wrongCurrentPasswordLabel.setText("");
            currentPasswordCorrect = true;
        }
    }
    /**
     * Metoda odpowiadająca za przeprowadzenie walidacji nowego hasła klienta
     */
    private void performNewPasswordValidation(){
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
     * Metoda odpowiadająca za przeprowadzenie walidacji potwierdzonego hasła klienta
     */
    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(newPassword)){
            wrongConfirmPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            wrongConfirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Zmień hasło"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void changeButtonActionPerformed(ActionEvent evt) {
        performCurrentPasswordValidation();
        performNewPasswordValidation();
        performConfirmPasswordValidation();
        if(currentPasswordCorrect){
            if(newPasswordCorrect && confirmPasswordCorrect){
                data.add(newPassword);
                data.add(clientId);
                new Client("changeClientPassword",data);
                data.clear();
                dispose();
                JOptionPane.showMessageDialog(null, "Pomyślnie zmieniono hasło.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(PasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new PasswordChange(null,null).setVisible(true));
    }
}