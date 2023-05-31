import javax.swing.*;
import java.awt.Color;

public class ClientPasswordChange extends javax.swing.JFrame {
    public static String password = "";
    private boolean passwordCorrect;
    private boolean confirmPasswordCorrect;
    private final Validation validation;
    private String passwordFromPasswordField;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JPasswordField newPasswordField;
    private javax.swing.JLabel wrongConfirmNewPasswordLabel;
    private javax.swing.JLabel wrongNewPasswordLabel;

    public ClientPasswordChange() {
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(247,233,201));
    }

    private void initComponents() {

        javax.swing.JLabel newPasswordLabel = new javax.swing.JLabel();
        javax.swing.JLabel confirmNewPasswordLabel = new javax.swing.JLabel();
        javax.swing.JButton changePasswordButton = new javax.swing.JButton();
        wrongNewPasswordLabel = new javax.swing.JLabel();
        wrongConfirmNewPasswordLabel = new javax.swing.JLabel();
        newPasswordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zmień hasło Klienta");
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
    private void performPasswordValidation(){
        passwordFromPasswordField = new String(newPasswordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            wrongNewPasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            passwordCorrect = validation.passwordIsValid(passwordFromPasswordField);
            if (passwordCorrect)
                wrongNewPasswordLabel.setText("");
            else
                wrongNewPasswordLabel.setText("Hasło nie spełnia wymagań.");
        }
    }

    private void performConfirmPasswordValidation(){
        String confirmPasswordFromPasswordField = new String(confirmPasswordField.getPassword());
        if(!confirmPasswordFromPasswordField.equals(passwordFromPasswordField)){
            wrongConfirmNewPasswordLabel.setText("Hasła się nie zgadzają.");
            confirmPasswordCorrect = false;
        }
        else {
            wrongConfirmNewPasswordLabel.setText("");
            confirmPasswordCorrect = true;
        }
    }
    private void changePasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        performPasswordValidation();
        performConfirmPasswordValidation();
        if(passwordCorrect){
            if(confirmPasswordCorrect){
                password = new String(newPasswordField.getPassword());
                new Client("changeClientPassword");
                dispose();
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
            java.util.logging.Logger.getLogger(ClientPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ClientPasswordChange().setVisible(true));
    }
}
