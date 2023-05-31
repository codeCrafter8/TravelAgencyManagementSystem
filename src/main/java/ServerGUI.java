import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServerGUI extends javax.swing.JFrame {
    static List<Thread> threads = new ArrayList<>();
    static InputStream inputStream;
    static DataInputStream dataInputStream;
    static OutputStream outputStream;
    static DataOutputStream dataOutputStream;
    static Socket client;
    private static boolean running = true;
    public static String email, password, passwordChanged;
    public static List<String> registrationList = new ArrayList<>();
    public static int userIdToRes, tripIdToRes, peopleQuantity;
    public static String insurance;
    public static int clientIDToRemove;
    public static int tripIDToRemove;
    public static int resIDToRemove;
    public static int clientIDToChangePassword;
    public static String operation;
    public static List<String> clientEditList = new ArrayList<>();
    public static List<String> tripEditList = new ArrayList<>();
    public static List<String> resEditList = new ArrayList<>();
    private static ServerSocket socket;
    public static int usersCounter;
    public static List<String> phoneNumbers = new ArrayList<>();
    public static int phoneNumbersListLength = 0;
    public static List<String> destination = new ArrayList<>();
    public static List<String> departure = new ArrayList<>();
    public static List<String> addTripData = new ArrayList<>();
    public List<String> dataFromDataEdition = new ArrayList<>();
    static String adminName = "";
    static List<String> data = new ArrayList<>();
    static List<String> tripsData = new ArrayList<>();
    static List<String> tripsAdminData = new ArrayList<>();
    static List<String> resAdminData = new ArrayList<>();
    static List<String> clientData = new ArrayList<>();
    static List<String> resData = new ArrayList<>();
    public String clientID;
    private javax.swing.JButton adminsLogsButton;
    private javax.swing.JButton clientsLogsButton;
    private javax.swing.JLabel connectedUsersNumber;
    private javax.swing.JButton manageServerButton;
    private javax.swing.JButton restartServerButton;
    private javax.swing.JButton serverLogsButton;
    private javax.swing.JButton startServerButton;
    private javax.swing.JButton stopServerButton;

    public ServerGUI() {
        initComponents();
        getContentPane().setBackground(new Color(215, 198, 151));
        startServerButton.setText("<html><center>Uruchom<br><center>Serwer</html>");
        restartServerButton.setText("<html><center>Zrestartuj<br><center>Serwer</html>");
        stopServerButton.setText("<html><center>Zatrzymaj<br><center>Serwer</html>");
        connectedUsersNumber.setText(String.valueOf(usersCounter));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    running = false;
                    Client.operate("logOutEveryone");
                    for (Thread thread : threads) {
                        //new Logs("[ " + new java.util.Date() + " ] " + "Thread called " + thread.getName() + " was deleted ", "EkranSerwer", "info");
                        thread.interrupt();
                    }
                    //new Logs("[ " + new java.util.Date() + " ] " + "Server shutdown", "EkranSerwer", "info");
                    dispose();
                } catch (Exception ex) {
                    new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
                    dispose();
                }
            }
        });

    }
    public static void setRunning(boolean running){
        ServerGUI.running = running;
    }

    private void initComponents() {
        JPanel menuPanel = new JPanel();
        JPanel serverPanel = new JPanel();
        JLabel serverIconLabel = new JLabel();
        JLabel serverLabel = new JLabel();
        JPanel optionsPanel = new JPanel();
        manageServerButton = new javax.swing.JButton();
        clientsLogsButton = new javax.swing.JButton();
        adminsLogsButton = new javax.swing.JButton();
        serverLogsButton = new javax.swing.JButton();
        JPanel connectedUsersPanel = new JPanel();
        connectedUsersNumber = new javax.swing.JLabel();
        JLabel connectedUsersLabel = new JLabel();
        startServerButton = new javax.swing.JButton();
        restartServerButton = new javax.swing.JButton();
        stopServerButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikacja Serwera");
        setPreferredSize(new java.awt.Dimension(1024, 768));

        menuPanel.setBackground(new java.awt.Color(118, 98, 75));

        serverPanel.setBackground(new java.awt.Color(118, 98, 75));

        serverIconLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/img/serverICON.png"))));
        serverIconLabel.setMaximumSize(new java.awt.Dimension(70, 70));
        serverIconLabel.setMinimumSize(new java.awt.Dimension(70, 70));
        serverIconLabel.setPreferredSize(new java.awt.Dimension(70, 70));

        serverLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        serverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverLabel.setText("Serwer");

        javax.swing.GroupLayout serverPanelLayout = new javax.swing.GroupLayout(serverPanel);
        serverPanel.setLayout(serverPanelLayout);
        serverPanelLayout.setHorizontalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serverPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(serverIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        serverPanelLayout.setVerticalGroup(
            serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(serverPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(serverIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serverLabel)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        optionsPanel.setBackground(new java.awt.Color(151, 123, 92));
        optionsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));

        manageServerButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        manageServerButton.setText("   Zarządzaj pracą serwera");
        manageServerButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        manageServerButton.setContentAreaFilled(false);
        manageServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageServerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Color hoverColor = new Color(190, 190, 192);
        manageServerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                manageServerButton.setForeground(hoverColor);
                manageServerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                manageServerButton.setForeground(null);
        });

        clientsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsLogsButton.setText("Logi klientów");
        clientsLogsButton.setActionCommand("Logi klientów");
        clientsLogsButton.setContentAreaFilled(false);
        clientsLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientsLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        clientsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsLogsButton.setForeground(hoverColor);
                clientsLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                clientsLogsButton.setForeground(null);
        });
        clientsLogsButton.addActionListener(this::clientsLogsButtonActionPerformed);

        adminsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        adminsLogsButton.setText("Logi administratorów");
        adminsLogsButton.setContentAreaFilled(false);
        adminsLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminsLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adminsLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        adminsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                adminsLogsButton.setForeground(hoverColor);
                adminsLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                adminsLogsButton.setForeground(null);
        });
        adminsLogsButton.addActionListener(this::adminsLogsButtonActionPerformed);

        serverLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        serverLogsButton.setText("Logi serwera");
        serverLogsButton.setContentAreaFilled(false);
        serverLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        serverLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        serverLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        serverLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                serverLogsButton.setForeground(hoverColor);
                serverLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                serverLogsButton.setForeground(null);
        });
        serverLogsButton.addActionListener(this::serverLogsButtonActionPerformed);

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manageServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
            .addComponent(clientsLogsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(adminsLogsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(serverLogsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addComponent(manageServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clientsLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(adminsLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(serverLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(serverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(serverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        connectedUsersPanel.setBackground(new java.awt.Color(175, 157, 121));
        connectedUsersPanel.setPreferredSize(new java.awt.Dimension(280, 130));

        connectedUsersNumber.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        connectedUsersNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersNumber.setPreferredSize(new java.awt.Dimension(178, 57));

        connectedUsersLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        connectedUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersLabel.setText("Połączonych Użytkowników");

        javax.swing.GroupLayout connectedUsersPanelLayout = new javax.swing.GroupLayout(connectedUsersPanel);
        connectedUsersPanel.setLayout(connectedUsersPanelLayout);
        connectedUsersPanelLayout.setHorizontalGroup(
            connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(connectedUsersNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(connectedUsersLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        connectedUsersPanelLayout.setVerticalGroup(
            connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectedUsersPanelLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(connectedUsersNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(connectedUsersLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        startServerButton.setBackground(new java.awt.Color(189, 165, 111));
        startServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        startServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startServerButton.addActionListener(this::startServerButtonActionPerformed);

        restartServerButton.setBackground(new java.awt.Color(189, 165, 111));
        restartServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        restartServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restartServerButton.addActionListener(this::restartServerButtonActionPerformed);

        stopServerButton.setBackground(new java.awt.Color(189, 165, 111));
        stopServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        stopServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopServerButton.addActionListener(this::stopServerButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(connectedUsersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(restartServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                        .addComponent(stopServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stopServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(connectedUsersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(restartServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(324, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void serverLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new ServerLogs().setVisible(true);
    }

    private void adminsLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new AdminsLogs().setVisible(true);
    }

    private void clientsLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new ClientsLogs().setVisible(true);
    }

    private void restartServerButtonActionPerformed(ActionEvent evt) {
        if(running){
            setRunning(false);
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został zrestartowany.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer nie jest uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }

    private void stopServerButtonActionPerformed(ActionEvent evt) {
        if(running) {
            setRunning(false);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został zatrzymany.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer nie jest uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }

    private void startServerButtonActionPerformed(ActionEvent evt) {
        if(!running) {
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został uruchomiony.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer jest już uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }

    private static void operate(){
        try {
            ServerGUI.socket = new ServerSocket(1522);
            ServerGUI.socket.setReuseAddress(true);
            while (ServerGUI.running) {
                ServerGUI.client = ServerGUI.socket.accept();
                SwingWorker<Void, Void> worker = new BackgroundHandler();
                worker.execute();
            }
        }
        catch(IOException ex){
            System.out.println("Ex: " + ex);
            new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
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
            new LogsServer("ServerGUI", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został uruchomiony.");
        java.awt.EventQueue.invokeLater(() -> new ServerGUI().setVisible(true));
        operate();
    }
}