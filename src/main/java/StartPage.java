import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;
import javax.swing.*;

public class StartPage extends javax.swing.JFrame {
    public static boolean userExists;
    public static boolean passwordValid;
    public static boolean adminLogged = false;
    public static boolean clientLogged = false;
    public static String email;
    public static String password;
    public static String message;
    private final Validation validation;
    private boolean emailCorrect;
    private boolean passwordCorrect;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton registerButton;
    private javax.swing.JButton signInButton;
    private javax.swing.JLabel typeEmailLabel;
    private javax.swing.JLabel typePasswordLabel;
    public StartPage() {
        validation = new Validation();
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        JLabel travelLabel = new JLabel("Travel");
        travelLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        travelLabel.setBounds(182, 50, 250, 65);
        add(travelLabel);
        JLabel agencyLabel = new JLabel("Agency");
        agencyLabel.setFont(new Font("Monotype Corsiva", Font.ITALIC, 65));
        agencyLabel.setBounds(247, 85, 250, 100);
        add(agencyLabel);
    }
    private void initComponents() {
        JPanel loginPanel = new JPanel();
        JLabel emailLabel = new JLabel();
        emailTextField = new javax.swing.JTextField();
        JLabel passwordLabel = new JLabel();
        signInButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        typeEmailLabel = new javax.swing.JLabel();
        typePasswordLabel = new javax.swing.JLabel();
        JLabel jLabel2 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logowanie");
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(242, 214, 158));
        setPreferredSize(new java.awt.Dimension(1024, 768));

        loginPanel.setBackground(new java.awt.Color(247, 233, 201));
        loginPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(229, 221, 192), null, null));
        loginPanel.setForeground(new java.awt.Color(224, 214, 176));

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail");

        emailTextField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        emailTextField.setPreferredSize(new java.awt.Dimension(64, 28));

        emailTextField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performEmailValidation();
                    }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {}
            public void focusLost(FocusEvent e) {
                if(e.getOppositeComponent() != null)
                    if(!e.getOppositeComponent().equals(registerButton)) {
                        performPasswordValidation();
                    }
            }
        });

        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        passwordLabel.setText("Hasło");

        signInButton.setBackground(new java.awt.Color(189, 165, 111));
        signInButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        signInButton.setText("Zaloguj się");
        signInButton.setActionCommand("Zaloguj się");
        signInButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        signInButton.setMaximumSize(new java.awt.Dimension(105, 25));
        signInButton.setMinimumSize(new java.awt.Dimension(105, 25));
        signInButton.setPreferredSize(new java.awt.Dimension(105, 25));
        signInButton.addActionListener(this::signInButtonActionPerformed);
        signInButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        registerButton.setBackground(new java.awt.Color(189, 165, 111));
        registerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        registerButton.setText("Utwórz konto");
        registerButton.addActionListener(this::registerButtonActionPerformed);
        registerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover())
                registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        });

        passwordField.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(204, 204, 204), new java.awt.Color(153, 153, 153), new java.awt.Color(102, 102, 102), new java.awt.Color(102, 102, 102)));
        passwordField.setPreferredSize(new java.awt.Dimension(64, 28));

        typeEmailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        typeEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        typeEmailLabel.setMinimumSize(new java.awt.Dimension(94, 18));
        typeEmailLabel.setPreferredSize(new java.awt.Dimension(94, 18));

        typePasswordLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
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

        jLabel2.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("img/tets.jpg"))));
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
    }

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new Registration().setVisible(true);
    }

    private void performEmailValidation(){
        String emailFromTextField = emailTextField.getText();
        if(emailFromTextField.equals("")) {
            emailCorrect = false;
            typeEmailLabel.setText("Pole jest wymagane.");
        }
        else {
            emailCorrect = validation.emailIsValid(emailFromTextField);
            if (emailCorrect)
                typeEmailLabel.setText("");
            else
                typeEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
        }
    }

    private void performPasswordValidation(){
        String passwordFromPasswordField = new String(passwordField.getPassword());
        if(passwordFromPasswordField.equals("")) {
            passwordCorrect = false;
            typePasswordLabel.setText("Pole jest wymagane.");
        }
        else {
            passwordCorrect = validation.passwordIsValid(passwordFromPasswordField);
            if (passwordCorrect)
                typePasswordLabel.setText("");
            else
                typePasswordLabel.setText("Minimum 8 znaków w tym jedna cyfra, wielka litera i mała litera.");
        }
    }

    private void signInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        performEmailValidation();
        performPasswordValidation();

        if(emailCorrect && passwordCorrect) {
            email = emailTextField.getText();
            password = new String(passwordField.getPassword());
            typeEmailLabel.setText("");
            Client.operate("login");

            if (!userExists)
                typeEmailLabel.setText("Użytkownik o tym adresie e-mail nie istnieje. Podaj inny.");
            else {
                if (!passwordValid)
                    typePasswordLabel.setText("Błędne hasło.");
                else {
                    if(!message.equals(""))
                        JOptionPane.showMessageDialog(null, message, "Informacja", JOptionPane.ERROR_MESSAGE);
                    if (adminLogged) {
                        dispose();
                        new Dashboard().setVisible(true);
                    }
                    if (clientLogged) {
                        dispose();
                        new SearchEngine().setVisible(true);
                    }
                }
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
            java.util.logging.Logger.getLogger(StartPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new StartPage().setVisible(true));
    }
}
