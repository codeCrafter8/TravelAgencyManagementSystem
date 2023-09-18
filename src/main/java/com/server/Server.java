package com.server;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.database.DatabaseHandler;
import com.server.logs.LogsServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing fields and methods for handling GUI construction and server technical aspects.
 */
public class Server extends javax.swing.JFrame {
    
    /**
     * An attribute representing a list of all server threads.
     */
    private final List<Thread> threads = new ArrayList<>();
    
    /**
     * An attribute specifying the number of connected users.
     */
    public int connectedUsersNumber;
   
    /**
     * An attribute indicating whether the server is running.
     */
    private boolean running = true;
   
    /**
     * An attribute representing the socket to which the client is connected.
     */
    public Socket socket;
   
    /**
     * An attribute representing the server socket.
     */
    public ServerSocket serverSocket;
   
    /**
     * A constructor responsible for initializing the server application GUI.
     */
    public Server() {
        initComponents();
    }
   
    /**
     * Saves the number of connected users to a file.
     */
    public void saveConnectedUsersNumber(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("connectedUsersNumber.dat"))) {
            writer.write(connectedUsersNumberLabel.getText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
  
    /**
     * Sets the variable that determines whether the server is running.
     * @param running specifies whether the server is running
     */
    public void setRunning(boolean running){
        this.running = running;
    }
   
    /**
     * Initializes the graphical components of the server application.
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createServerPanel();
        setLabels();
        createOptionsPanel();
        setButtons();
        createConnectedUsersPanel();
        createLayout();
    }

    /**
     * Sets the properties for the main application window.
     * Configures the close operation, title, preferred size, and background color.
     * Also handles the window closing event to clean up resources and terminate the application gracefully.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikacja Serwera");
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    new DatabaseHandler("logOutEveryone", new ArrayList<>());
                    connectedUsersNumberLabel.setText("0");
                    saveConnectedUsersNumber();
                    running = false;
                    for (Thread thread : threads) {
                        thread.interrupt();
                    }
                    dispose();
                } catch (Exception ex) {
                    new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
                    dispose();
                }
            }
        });
    }

    /**
     * Creates the menu panel for the main application window.
     * This panel contains the server information and additional options for the application.
     */
    private void createMenuPanel(){
        menuPanel.setBackground(ColorUtils.BROWN);
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
    }

    /**
     * Creates the server panel that displays server information.
     * This panel includes a server icon and a label describing the server.
     */
    private void createServerPanel(){
        serverPanel.setBackground(ColorUtils.BROWN);
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
    }

    /**
     * Sets the labels for various components in the application.
     * Configures icons, fonts, and texts for server and connected users labels.
     */
    private void setLabels(){
        serverIconLabel.setIcon(new javax.swing.ImageIcon("img\\server.png"));
        serverIconLabel.setMaximumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setMinimumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setPreferredSize(DimensionUtils.ICON_LABEL_DIMENSION);

        serverLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        serverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverLabel.setText("Serwer");

        connectedUsersNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        connectedUsersNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersNumberLabel.setPreferredSize(DimensionUtils.NUMBER_LABEL_DIMENSION);
        try (BufferedReader reader = new BufferedReader(new FileReader("connectedUsersNumber.dat"))) {
            connectedUsersNumber = Integer.parseInt(reader.readLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        connectedUsersNumberLabel.setText(String.valueOf(connectedUsersNumber));

        connectedUsersLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        connectedUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersLabel.setText("Połączonych Użytkowników");
    }

    /**
     * Creates the options panel that contains additional application options.
     * This panel includes buttons for managing the server and accessing various logs.
     */
    private void createOptionsPanel(){
        optionsPanel.setBackground(ColorUtils.LIGHT_BROWN);
        optionsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        optionsPanel.setMinimumSize(DimensionUtils.OPTIONS_PANEL_MIN_SIZE_DIMENSION);
        optionsPanel.setPreferredSize(DimensionUtils.OPTIONS_PANEL_PREF_SIZE_DIMENSION);
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
    }

    /**
     * Configures the buttons used in the application.
     * Sets fonts, texts, actions, and behavior for the buttons.
     */
    private void setButtons(){
        manageServerButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        manageServerButton.setText("   Zarządzaj pracą serwera");
        manageServerButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
        manageServerButton.setContentAreaFilled(false);
        manageServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageServerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        manageServerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                manageServerButton.setForeground(ColorUtils.LIGHT_GREY);
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
        clientsLogsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        clientsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsLogsButton.setForeground(ColorUtils.LIGHT_GREY);
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
        adminsLogsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        adminsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                adminsLogsButton.setForeground(ColorUtils.LIGHT_GREY);
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
        serverLogsButton.setPreferredSize(DimensionUtils.OPTION_BUTTON_DIMENSION);
        serverLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                serverLogsButton.setForeground(ColorUtils.LIGHT_GREY);
                serverLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                serverLogsButton.setForeground(null);
        });
        serverLogsButton.addActionListener(this::serverLogsButtonActionPerformed);

        startServerButton.setBackground(ColorUtils.DARK_BEIGE);
        startServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        startServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startServerButton.addActionListener(this::startServerButtonActionPerformed);

        restartServerButton.setBackground(ColorUtils.DARK_BEIGE);
        restartServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        restartServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restartServerButton.addActionListener(this::restartServerButtonActionPerformed);

        stopServerButton.setBackground(ColorUtils.DARK_BEIGE);
        stopServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        stopServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopServerButton.addActionListener(this::stopServerButtonActionPerformed);

        startServerButton.setText("<html><center>Uruchom<br><center>Serwer</html>");
        restartServerButton.setText("<html><center>Zrestartuj<br><center>Serwer</html>");
        stopServerButton.setText("<html><center>Zatrzymaj<br><center>Serwer</html>");
    }

    /**
     * Creates the panel that displays the number of connected users.
     * This panel includes a label showing the number of connected users and a descriptive label.
     */
    private void createConnectedUsersPanel(){
        connectedUsersPanel.setBackground(ColorUtils.NUDE);
        connectedUsersPanel.setPreferredSize(DimensionUtils.NUMBER_PANEL_DIMENSION);
        javax.swing.GroupLayout connectedUsersPanelLayout = new javax.swing.GroupLayout(connectedUsersPanel);
        connectedUsersPanel.setLayout(connectedUsersPanelLayout);
        connectedUsersPanelLayout.setHorizontalGroup(
                connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(connectedUsersNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connectedUsersLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        connectedUsersPanelLayout.setVerticalGroup(
                connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(connectedUsersPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(connectedUsersNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(connectedUsersLabel)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
    }

    /**
     * Creates the main layout for the application window.
     * Configures the layout and arrangement of various components in the window.
     */
    private void createLayout(){
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
    
    /**
     * Handles the situation when the "Server Logs" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void serverLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new ServerLogs().setVisible(true);
    }

    /**
     * Handles the situation when the "Admins Logs" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void adminsLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new AdminsLogs().setVisible(true);
    }

    /**
     * Handles the situation when the "Clients Logs" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void clientsLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new ClientsLogs().setVisible(true);
    }

    /**
     * Handles the situation when the "Restart Server" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void restartServerButtonActionPerformed(ActionEvent evt) {
        if (running) {
            setRunning(false);
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Server has been restarted.");
        } else {
            JOptionPane.showMessageDialog(null, "The server is not running.", "Information", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the situation when the "Stop Server" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void stopServerButtonActionPerformed(ActionEvent evt) {
        if (running) {
            setRunning(false);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Server has been stopped.");
        } else {
            JOptionPane.showMessageDialog(null, "The server is not running.", "Information", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Handles the situation when the "Start Server" button is pressed
     * @param evt Event buffer created during the button click
     */
    private void startServerButtonActionPerformed(ActionEvent evt) {
        if (!running) {
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Server has been started.");
        } else {
            JOptionPane.showMessageDialog(null, "The server is already running.", "Information", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Accepts new client connections to the server
     */
    private void operate() {
        try {
            serverSocket = new ServerSocket(1522);
            serverSocket.setReuseAddress(true);
            while (running) {
                socket = serverSocket.accept();
                SwingWorker<Void, Void> worker = new GUIHandler();
                worker.execute();
            }
        } catch (IOException ex) {
            System.out.println("Ex: " + ex);
            new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }

    /**
     * Launches the server application window
     * @param args Arguments passed during the application launch
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
            new LogsServer("ServerGUI", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Server has been started.");
        Server server = new Server();
        java.awt.EventQueue.invokeLater(() -> server.setVisible(true));
        server.operate();
    }

    /**
     * A class allowing threads to work independently of the server GUI
     */
    class GUIHandler extends SwingWorker<Void, Void> implements Serializable {

        /**
         * An overridden method converting data to a string type
         */
        @Override
        public String toString() {
            return super.toString();
        }

        /**
         * A class implementing the Runnable interface to create new threads on the server
         */
        private class ClientThread implements Runnable {

            /**
             * An attribute holding a list containing data passed from the server to the database
             */
            private final List<String> data = new ArrayList<>();

            /**
             * Sends data to the client
             */
            private void sendData() {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    DatabaseHandler databaseHandler = new DatabaseHandler(data.get(0), data);
                    objectOutputStream.writeObject(databaseHandler.getReturningData());
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    outputStream.write(byteArray);
                    outputStream.flush();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            /**
             * An overridden method run, handling the running thread
             */
            @Override
            public void run() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[2048];
                    Object object = new ObjectInputStream(new ByteArrayInputStream(bytes, 0, inputStream.read(bytes))).readObject();
                    data.clear();
                    data.addAll((List<String>) object);
                    sendData();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        /**
         * An overridden method doInBackground, allowing threads to work in the background without affecting the server GUI
         * @return No return value
         */
        @Override
        protected Void doInBackground() {
            Thread thread = new Thread(new ClientThread());
            threads.add(thread);
            thread.start();
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "New thread named " + thread.getName() + " has been created.");
            return null;
        }
    }

    /**
     * A button to navigate to the "Admins Logs" tab
     */
    private final javax.swing.JButton adminsLogsButton = new JButton();

    /**
     * A button to navigate to the "Clients Logs" tab
     */
    private final javax.swing.JButton clientsLogsButton = new JButton();

    /**
     * A button to navigate to the "Manage Server" tab
     */
    private final javax.swing.JButton manageServerButton = new JButton();

    /**
     * A button to restart the server
     */
    private final javax.swing.JButton restartServerButton = new JButton();

    /**
     * A button to navigate to the "Server Logs" tab
     */
    private final javax.swing.JButton serverLogsButton = new JButton();

    /**
     * A button to start the server
     */
    private final javax.swing.JButton startServerButton = new JButton();

    /**
     * A button to stop the server
     */
    private final javax.swing.JButton stopServerButton = new JButton();

    /**
     * A label displaying the number of connected users
     */
    private final javax.swing.JLabel connectedUsersNumberLabel = new JLabel();

    /**
     * A panel that contains the main menu options for the application.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * A panel that displays server information.
     */
    private final JPanel serverPanel = new JPanel();

    /**
     * A label that displays the server icon.
     */
    private final JLabel serverIconLabel = new JLabel();

    /**
     * A label that displays the server name or description.
     */
    private final JLabel serverLabel = new JLabel();

    /**
     * A panel that contains additional options for the application.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A panel that displays connected users or clients.
     */
    private final JPanel connectedUsersPanel = new JPanel();

    /**
     * A label that describes the displayed connected users.
     */
    private final JLabel connectedUsersLabel = new JLabel();

}