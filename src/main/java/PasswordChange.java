import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PasswordChange extends javax.swing.JFrame {
    private boolean passwordCorrect;
    private boolean newPasswordCorrect;
    private boolean confirmPasswordCorrect;
    private final Validation validation;
    private String newPasswordFromPasswordField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JLabel confirmPasswordLabel;
    private javax.swing.JPasswordField currentPasswordField;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JLabel newPasswordLabel;
    private javax.swing.JLabel wrongPasswordLabel;
    public PasswordChange() {
        validation = new Validation();
        initComponents();
        wrongPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        newPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        confirmPasswordLabel.setMinimumSize(new java.awt.Dimension(38, 16));
        wrongPasswordLabel.setText("");
        newPasswordLabel.setText("");
        confirmPasswordLabel.setText("");
    }

    private void initComponents() {
        JLabel currentPassword = new JLabel();
        JLabel newPassword = new JLabel();
        JLabel confirmPassword = new JLabel();
        JButton changeButton = new JButton();
        wrongPasswordLabel = new javax.swing.JLabel();
        newPasswordLabel = new javax.swing.JLabel();
        confirmPasswordLabel = new javax.swing.JLabel();
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

        wrongPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongPasswordLabel.setText("cos");

        newPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        newPasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");

        confirmPasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        confirmPasswordLabel.setText("cos");

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
                            .addComponent(wrongPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(confirmPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(wrongPasswordLabel)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newPassword)
                    .addComponent(newPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(newPasswordLabel)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmPassword)
                    .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(confirmPasswordLabel)
                .addGap(18, 18, 18)
                .addComponent(changeButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    private void performPasswordValidation(){
        String passwordFromPasswordField = new String(currentPasswordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            wrongPasswordLabel.setText("Pole jest wymagane.");
        }
        else if(!StartPage.password.equals(passwordFromPasswordField)){
            wrongPasswordLabel.setText("Błędne hasło.");
            passwordCorrect = false;
        }
        else{
            wrongPasswordLabel.setText("");
            passwordCorrect = true;
        }
    }
    private void performNewPasswordValidation(){
        newPasswordFromPasswordField = new String(newPasswordField.getPassword());
        if(newPasswordFromPasswordField.equals("")) {
            newPasswordCorrect = false;
            newPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            newPasswordCorrect = validation.passwordIsValid(newPasswordFromPasswordField);
            if (newPasswordCorrect)
                newPasswordLabel.setText("");
            else
                newPasswordLabel.setText("Hasło nie spełnia wymagań.");
        }
    }
    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(newPasswordFromPasswordField)){
            confirmPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            confirmPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }

    private void changeButtonActionPerformed(ActionEvent evt) {
        performPasswordValidation();
        performNewPasswordValidation();
        performConfirmPasswordValidation();
        if(passwordCorrect){
            if(newPasswordCorrect && confirmPasswordCorrect){
                ClientPasswordChange.password = new String(newPasswordField.getPassword());
                Clients.clientIDToChangePassword = Integer.parseInt(MyAccount.clientData.get(4));
                Client.operate("changeClientPassword");
                dispose();
                JOptionPane.showMessageDialog(null, "Pomyślnie zmieniono hasło.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

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

        java.awt.EventQueue.invokeLater(() -> new PasswordChange().setVisible(true));
    }
}
