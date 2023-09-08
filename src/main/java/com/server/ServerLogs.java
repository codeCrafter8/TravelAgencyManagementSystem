package com.server;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.logs.LogsServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * A class containing fields and methods for handling the GUI components related to server logs
 */
public class ServerLogs extends javax.swing.JFrame {

    /**
     * A constructor responsible for initializing the GUI
     */
    public ServerLogs() {
        initComponents();
    }

    /**
     * Sets the serverLogsList with log entries from the LogsServer.
     */
    private void setLogsList() {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (String log : LogsServer.logs) {
            defaultListModel.addElement(log);
        }
        serverLogsList.setModel(defaultListModel);
    }

    /**
     * Initializes the graphical components used in the window
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createServerPanel();
        createOptionsPanel();
        setLabels();
        setButtons();
        setLogsList();
        createLayout();
    }

    /**
     * Sets the properties of this application window.
     * Configures the default close operation, title, preferred size, and background color.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logi Serwera");
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
    }

    /**
     * Creates and configures the menu panel for this application window.
     * The menu panel contains the server information and additional options.
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
                                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * Creates and configures the server panel in this application window.
     * The server panel displays server information, including a server icon and a label.
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
     * Creates and configures the options panel in this application window.
     * The options panel contains various buttons for managing the server and accessing logs.
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
                                .addComponent(serverLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    /**
     * Sets up the labels, including the server icon label,
     * server label, and server logs label.
     */
    private void setLabels(){
        serverIconLabel.setIcon(new javax.swing.ImageIcon("img\\server.png"));
        serverIconLabel.setMaximumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setMinimumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setPreferredSize(DimensionUtils.ICON_LABEL_DIMENSION);

        serverLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        serverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverLabel.setText("Serwer");

        serverLogsLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 22));
        serverLogsLabel.setText("Logi Serwera");
    }

    /**
     * Sets up the buttons, including the manage server button,
     * clients logs button, admins logs button, and server logs button.
     */
    private void setButtons(){
        manageServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        manageServerButton.setText("    Zarządzaj pracą serwera");
        manageServerButton.setBorder(null);
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
        manageServerButton.addActionListener(this::manageServerButtonActionPerformed);

        clientsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsLogsButton.setText("    Logi klientów");
        clientsLogsButton.setBorder(null);
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
        adminsLogsButton.setText("    Logi administratorów");
        adminsLogsButton.setBorder(null);
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

        serverLogsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        serverLogsButton.setText("   Logi serwera");
        serverLogsButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
        serverLogsButton.setContentAreaFilled(false);
        serverLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        serverLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        serverLogsButton.setMaximumSize(new java.awt.Dimension(166, 24));
        serverLogsButton.setMinimumSize(new java.awt.Dimension(166, 25));
        serverLogsButton.setPreferredSize(new java.awt.Dimension(166, 25));
        serverLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                serverLogsButton.setForeground(ColorUtils.LIGHT_GREY);
                serverLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                serverLogsButton.setForeground(null);
        });
    }

    /**
     * Creates the layout for this application window.
     * It uses GroupLayout for arranging the components, including the menu panel
     * and the server logs list.
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
                                                .addGap(328, 328, 328)
                                                .addComponent(serverLogsLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(67, 67, 67)
                                                .addComponent(serverLogsList, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 88, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(serverLogsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(serverLogsList, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(85, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Handles the click event of the manageServerButton
     * @param evt The event generated when the button is clicked
     */
    private void manageServerButtonActionPerformed(ActionEvent evt) {
        dispose();
        new ServerGUI().setVisible(true);
    }

    /**
     * Handles the click event of the adminsLogsButton
     * @param evt The event generated when the button is clicked
     */
    private void adminsLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new AdminsLogs().setVisible(true);
    }

    /**
     * Handles the click event of the clientsLogsButton
     * @param evt The event generated when the button is clicked
     */
    private void clientsLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new ClientsLogs().setVisible(true);
    }

    /**
     * Allows to run the window
     * @param args Arguments passed during application launch
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
            java.util.logging.Logger.getLogger(ServerLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            new LogsServer("ServerLogs", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> new ServerLogs().setVisible(true));
    }

    /**
     * A button for navigating to the administrators' logs tab
     */
    private final javax.swing.JButton adminsLogsButton = new JButton();

    /**
     * A button for navigating to the clients' logs tab
     */
    private final javax.swing.JButton clientsLogsButton = new JButton();

    /**
     * A button for navigating to the server management tab
     */
    private final javax.swing.JButton manageServerButton = new JButton();

    /**
     * A button for navigating to the server logs tab
     */
    private final javax.swing.JButton serverLogsButton = new JButton();

    /**
     * A list containing server logs
     */
    private final javax.swing.JList<String> serverLogsList = new JList<>();
    /**
     * A menu panel in the main application window.
     * This panel contains the server information and additional options for the application.
     */
    private final JPanel menuPanel = new JPanel();

    /**
     * A server panel displaying server information.
     * This panel includes a server icon and a label describing the server.
     */
    private final JPanel serverPanel = new JPanel();

    /**
     * A label displaying the server icon in the server panel.
     */
    private final JLabel serverIconLabel = new JLabel();

    /**
     * A label displaying the server text in the server panel.
     */
    private final JLabel serverLabel = new JLabel();

    /**
     * An options panel containing additional application options.
     * This panel includes buttons for managing the server and accessing various logs.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A label for the server logs section of the application.
     */
    private final JLabel serverLogsLabel = new JLabel();
}