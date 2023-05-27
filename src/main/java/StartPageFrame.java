
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;
import javax.swing.*;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author zuzan
 */
public class StartPageFrame extends javax.swing.JFrame {
    public static final String emailPattern = "^[\\w.-]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final Pattern compiledEmailPattern = Pattern.compile(emailPattern);
    public static final String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
    public static final Pattern compiledPasswordPattern = Pattern.compile(passwordPattern);
    public static boolean user_exists;
    public static boolean password_valid;
    public static boolean admin_logged = false;
    public static boolean client_logged = false;
    public static String email;
    public static String password;
    public static String message;
    /**
     * Creates new form StartPageFrame
     */
    public StartPageFrame() {
       // System.out.println(admin_logged);
        initComponents();
        
        //frame background color
        getContentPane().setBackground(new Color(215,198,151));
        
        //travel label
        JLabel travelLabel = new JLabel("Travel");
        travelLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        travelLabel.setBounds(182, 50, 250, 65);
        add(travelLabel);
       
        //agency label
        JLabel agencyLabel = new JLabel("Agency");
        agencyLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        agencyLabel.setBounds(247, 85, 250, 100);
        add(agencyLabel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        signInButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        typeEmailLabel = new javax.swing.JLabel();
        typePasswordLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logowanie");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(242, 214, 158));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        loginPanel.setBackground(new java.awt.Color(247, 233, 201));
        loginPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(229, 221, 192), null, null));
        loginPanel.setForeground(new java.awt.Color(224, 214, 176));

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        emailLabel.setText("E-mail");

        emailTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        emailTextField.setPreferredSize(new java.awt.Dimension(64, 28));

        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        emailIsValid();
                    }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        passwordIsValid();
                    }
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        passwordLabel.setText("Hasło");

        signInButton.setBackground(new java.awt.Color(189, 165, 111));
        signInButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        signInButton.setText("Zaloguj się");
        signInButton.setActionCommand("Zaloguj się");
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signInButton.setMaximumSize(new java.awt.Dimension(105, 25));
        signInButton.setMinimumSize(new java.awt.Dimension(105, 25));
        signInButton.setPreferredSize(new java.awt.Dimension(105, 25));
        signInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        //nowe
        signInButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        registerButton.setBackground(new java.awt.Color(189, 165, 111));
        registerButton.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        registerButton.setText("Utwórz konto");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        //nowe
        registerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        passwordField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        passwordField.setPreferredSize(new java.awt.Dimension(64, 28));

        typeEmailLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        typeEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeEmailLabel.setMinimumSize(new java.awt.Dimension(94, 18));
        typeEmailLabel.setPreferredSize(new java.awt.Dimension(94, 18));

        typePasswordLabel.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        typePasswordLabel.setForeground(new java.awt.Color(255, 0, 0));
        typePasswordLabel.setMinimumSize(new java.awt.Dimension(94, 18));
        typePasswordLabel.setPreferredSize(new java.awt.Dimension(94, 18));

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeEmailLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginPanelLayout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addGap(0, 365, Short.MAX_VALUE))
                            .addComponent(typePasswordLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(passwordLabel))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, loginPanelLayout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(registerButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(emailLabel)
                .addGap(7, 7, 7)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(typeEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordLabel)
                .addGap(7, 7, 7)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(typePasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/tets.jpg"))); // NOI18N
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(154, 154, 154))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        dispose();
        new RegistrationPage().setVisible(true);
    }//GEN-LAST:event_registerButtonActionPerformed

    private boolean emailIsValid(){
        if(emailTextField.getText().isEmpty()) {
            typeEmailLabel.setText("Pole jest wymagane.");
            return false;
        }
        else if(!compiledEmailPattern.matcher(emailTextField.getText()).matches()) {
            typeEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
            return false;
        }
        else {
            typeEmailLabel.setText("");
            return true;
        }
    }

    private boolean passwordIsValid(){
        if(new String(passwordField.getPassword()).equals("")) {
            typePasswordLabel.setText("Pole jest wymagane.");
            return false;
        }
        else if(!compiledPasswordPattern.matcher(new String(passwordField.getPassword())).matches()) {
            typePasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");
            return false;
        }
        else {
            typePasswordLabel.setText("");
            return true;
        }
    }

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInButtonActionPerformed
        // TODO add your handling code here:

        boolean emailCorrect = emailIsValid();
        boolean passwordCorrect = passwordIsValid();

        if(emailCorrect && passwordCorrect) {
            email = emailTextField.getText();
            password = new String(passwordField.getPassword());
            typeEmailLabel.setText("");
            Client.operate("Login");

            if (!user_exists)
                typeEmailLabel.setText("Użytkownik o tym adresie e-mail nie istnieje. Podaj inny.");
            else {
                if (!password_valid)
                    typePasswordLabel.setText("Błędne hasło.");
                else {
                    if(!message.equals(""))
                        JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.ERROR_MESSAGE);
                    if (admin_logged) {
                        dispose();
                        new Dashboard().setVisible(true);
                    }
                    if (client_logged) {
                        dispose();
                        new WYSZUKIWARKA().setVisible(true);
                    }
                }
            }
        }
    }//GEN-LAST:event_signInButtonActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartPageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartPageFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel typeEmailLabel;
    private javax.swing.JLabel typePasswordLabel;
    // End of variables declaration//GEN-END:variables
}
