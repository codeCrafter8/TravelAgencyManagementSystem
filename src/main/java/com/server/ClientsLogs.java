package com.server;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.logs.LogsAdmins;
import com.server.logs.LogsServer;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * A class containing fields and methods handling GUI components related to client logs.
 */
public class ClientsLogs extends javax.swing.JFrame {
    
    /**
     * A constructor responsible for GUI initialization.
     */
    public ClientsLogs() {
        initComponents();
    }

    /**
     * Sets the client logs list.
     */
    private void setLogsList() {
        DefaultListModel<String> defaultListModel = new DefaultListModel<>();
        for (String log : LogsAdmins.logs) {
            defaultListModel.addElement(log);
        }
        clientsLogsList.setModel(defaultListModel);
        clientsLogsScrollPane.setViewportView(clientsLogsList);
    }

    /**
     * Initializes the graphical components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createServerPanel();
        setLabels();
        createOptionsPanel();
        setButtons();
        createLayout();
        setLogsList();
    }

    /**
     * Sets properties for the main window.
     */
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Logi Klientów");
        setPreferredSize(DimensionUtils.WINDOW_DIMENSION);
        getContentPane().setBackground(ColorUtils.BEIGE);
    }

    /**
     * Creates the menu panel and sets its layout.
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
     * Creates the server panel and sets its layout.
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
     * Sets properties for labels.
     */
    private void setLabels(){
        serverIconLabel.setIcon(new javax.swing.ImageIcon("img\\server.png"));
        serverIconLabel.setMaximumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setMinimumSize(DimensionUtils.ICON_LABEL_DIMENSION);
        serverIconLabel.setPreferredSize(DimensionUtils.ICON_LABEL_DIMENSION);

        serverLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        serverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverLabel.setText("Serwer");

        clientsLogsLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 22));
        clientsLogsLabel.setText("Logi Klientów");
    }

    /**
     * Creates the options panel and sets its layout.
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
     * Sets properties for buttons and defines their action listeners.
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

        clientsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        clientsLogsButton.setText("   Logi klientów");
        clientsLogsButton.setBorder(javax.swing.BorderFactory.createLineBorder(Color.white));
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
    }

    /**
     * Creates the overall layout of the main window.
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
                                                .addGap(320, 320, 320)
                                                .addComponent(clientsLogsLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(clientsLogsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(415, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(clientsLogsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(clientsLogsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    /**
     * Handles the action when the serverLogsButton is clicked.
     *
     * @param evt The event that occurred (in this case, a button click).
     */
    private void serverLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new ServerLogs().setVisible(true);
    }

    /**
     * Handles the action when the manageServerButton is clicked.
     *
     * @param evt The event that occurred (in this case, a button click).
     */
    private void manageServerButtonActionPerformed(ActionEvent evt) {
        dispose();
        new Server().setVisible(true);
    }

    /**
     * Handles the action when the adminsLogsButton is clicked.
     *
     * @param evt The event that occurred (in this case, a button click).
     */
    private void adminsLogsButtonActionPerformed(ActionEvent evt) {
        dispose();
        new AdminsLogs().setVisible(true);
    }

    /**
     * Allows the window to be run as a standalone application.
     *
     * @param args Command-line arguments (if any) passed to the application.
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
            java.util.logging.Logger.getLogger(ClientsLogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            new LogsServer("ClientsLogs", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        java.awt.EventQueue.invokeLater(() -> new ClientsLogs().setVisible(true));
    }

    /**
     * A button for navigating to the administrators' logs tab.
     */
    private final javax.swing.JButton adminsLogsButton = new JButton();

    /**
     * A button for navigating to the clients' logs tab.
     */
    private final javax.swing.JButton clientsLogsButton = new JButton();

    /**
     * A button for navigating to the server management tab.
     */
    private final javax.swing.JButton manageServerButton = new JButton();

    /**
     * A button for navigating to the server logs tab.
     */
    private final javax.swing.JButton serverLogsButton = new JButton();

    /**
     * A list of client logs.
     */
    private final javax.swing.JList<String> clientsLogsList = new JList<>();

    /**
     * A menu panel that contains various options for the application.
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
     * A panel that contains options for the application.
     */
    private final JPanel optionsPanel = new JPanel();

    /**
     * A scroll pane that wraps the logs for clients.
     */
    private final JScrollPane clientsLogsScrollPane = new JScrollPane();

    /**
     * A label that describes the displayed logs for clients.
     */
    private final JLabel clientsLogsLabel = new JLabel();
}