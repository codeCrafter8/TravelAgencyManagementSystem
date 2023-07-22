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
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmiana hasła");
    }
    private void setLabels(){
        currentPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        currentPasswordLabel.setText("Aktualne hasło:");

        newPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        newPasswordLabel.setText("Podaj nowe hasło:");

        confirmPasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        confirmPasswordLabel.setText("Potwierdź nowe hasło:");

        wrongCurrentPasswordLabel.setForeground(Color.RED);
        wrongCurrentPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));

        wrongNewPasswordLabel.setForeground(Color.RED);
        wrongNewPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        wrongNewPasswordLabel.setText("");

        wrongConfirmPasswordLabel.setForeground(Color.RED);
        wrongConfirmPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
    }
    private void setButton(){
        changeButton.setBackground(ColorUtils.LIGHT_BROWN);
        changeButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        changeButton.setForeground(Color.WHITE);
        changeButton.setText("Zmień hasło");
        changeButton.addActionListener(this::changeButtonActionPerformed);
    }
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
            newPasswordCorrect = Validation.isPasswordValid(newPassword);
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
                data.clear();
                data.add("changeClientPassword");
                data.add(newPassword);
                data.add(clientId);
                new Client(data);
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
    //GUI variables
    /**
     * Pole do wprowadzenia potwierdzonego hasła klienta
     */
    private final javax.swing.JPasswordField confirmPasswordField = new JPasswordField();
    /**
     * Etykieta informująca, że potwierdzone hasło klienta jest niepoprawne
     */
    private final javax.swing.JLabel wrongConfirmPasswordLabel = new JLabel();
    /**
     * Pole do wprowadzenia obecnego hasła klienta
     */
    private final javax.swing.JPasswordField currentPasswordField = new JPasswordField();
    /**
     * Pole do wprowadzenia nowego hasła klienta
     */
    private final javax.swing.JPasswordField newPasswordField = new JPasswordField();
    /**
     * Etykieta informująca, że nowe hasło klienta jest niepoprawne
     */
    private final javax.swing.JLabel wrongNewPasswordLabel = new JLabel();
    /**
     * Etykieta informująca, że obecne hasło klienta jest niepoprawne
     */
    private final javax.swing.JLabel wrongCurrentPasswordLabel = new JLabel();
    /**
     * Atrybut określający, czy obecne hasło klienta jest poprawne
     */
    private final JLabel currentPasswordLabel = new JLabel();
    private final JLabel newPasswordLabel = new JLabel();
    private final JLabel confirmPasswordLabel = new JLabel();
    private final JButton changeButton = new JButton();
}