import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.util.List;
import java.util.Objects;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Clients extends javax.swing.JFrame {
    public static String adminName = "";
    public static List<String> data = new ArrayList<>();
    public static int listDataLength = 0;
    private static TableRowSorter<TableModel> rowSorter;
    public static int clientIDToRemove = 0;
    public static int clientIDToChangePassword = 0;
    public static int id = 0;
    public static String firstName = "";
    public static String lastName = "";
    public static String email = "";
    public static String phoneNumber = "";
    private final Validation validation;
    private javax.swing.JLabel adminNameLabel;
    private javax.swing.JButton clientsButton;
    private javax.swing.JTable clientsTable;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton panelButton;
    private javax.swing.JButton reservationsButton;
    private javax.swing.JTextField searchClientTextField;
    private javax.swing.JButton tripsButton;
    private javax.swing.JLabel wrongEmailLabel;
    public Clients() {
        validation = new Validation();
        initComponents();
        generateData();
        populateTable();
        searchClients();
        getContentPane().setBackground(new Color(215,198,151));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(151,123,92));
        for (int i = 0; i < clientsTable.getModel().getColumnCount(); i++) {
                clientsTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    Client.operate("logOut");
                    data.clear();
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("Clients", "info", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    private void initComponents() {
        JPanel topPanel = new JPanel();
        logOutButton = new javax.swing.JButton();
        JPanel menuPanel = new JPanel();
        JPanel adminPanel = new JPanel();
        JLabel adminIconLabel = new JLabel();
        adminNameLabel = new javax.swing.JLabel();
        JLabel adminLabel = new JLabel();
        JPanel optionsPanel = new JPanel();
        panelButton = new javax.swing.JButton();
        clientsButton = new javax.swing.JButton();
        tripsButton = new javax.swing.JButton();
        reservationsButton = new javax.swing.JButton();
        JScrollPane jScrollPane1 = new JScrollPane();
        clientsTable = new javax.swing.JTable();
        searchClientTextField = new javax.swing.JTextField();
        JLabel searchClientLabel = new JLabel();
        JButton addClientButton = new JButton();
        JButton deleteClientButton = new JButton();
        JButton editClientButton = new JButton();
        wrongEmailLabel = new javax.swing.JLabel();
        JButton editPasswordButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Klienci");

        topPanel.setBackground(new java.awt.Color(151, 123, 92));
        topPanel.setPreferredSize(new java.awt.Dimension(205, 34));

        logOutButton.setBackground(new java.awt.Color(242, 242, 242));
        logOutButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText("Wyloguj");
        logOutButton.setContentAreaFilled(false);
        logOutButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutButton.setPreferredSize(new java.awt.Dimension(74, 34));
        logOutButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                logOutButton.setForeground(Color.lightGray);
                logOutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                logOutButton.setForeground(Color.white);
        });
        logOutButton.addActionListener(this::logOutButtonActionPerformed);

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addContainerGap(737, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        menuPanel.setBackground(new java.awt.Color(118, 98, 75));

        adminPanel.setBackground(new java.awt.Color(118, 98, 75));

        adminIconLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/img/adminLOGO.png")))); // NOI18N

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18)); // NOI18N
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(new java.awt.Dimension(73, 25));

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14)); // NOI18N
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        javax.swing.GroupLayout adminPanelLayout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanelLayout);
        adminPanelLayout.setHorizontalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(adminIconLabel)
                .addContainerGap(55, Short.MAX_VALUE))
            .addComponent(adminLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        adminPanelLayout.setVerticalGroup(
            adminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(adminIconLabel)
                .addGap(3, 3, 3)
                .addComponent(adminNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(adminLabel)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
        optionsPanel.setBackground(new Color(118,98,75));

        panelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        panelButton.setText("    Panel");
        panelButton.setBorder(null);
        panelButton.setContentAreaFilled(false);
        panelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Color hoverColor = new Color(190, 190, 192);
        panelButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                panelButton.setForeground(hoverColor);
                panelButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                panelButton.setForeground(null);
        });
        panelButton.addActionListener(this::panelButtonActionPerformed);

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16)); // NOI18N
        clientsButton.setText("   Klienci");
        clientsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        clientsButton.setContentAreaFilled(false);
        clientsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        clientsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsButton.setForeground(hoverColor);
                clientsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                clientsButton.setForeground(null);
        });

        tripsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        tripsButton.setText("Wycieczki");
        tripsButton.setContentAreaFilled(false);
        tripsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tripsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        tripsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                tripsButton.setForeground(hoverColor);
                tripsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                tripsButton.setForeground(null);
        });
        tripsButton.addActionListener(this::tripsButtonActionPerformed);

        reservationsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16)); // NOI18N
        reservationsButton.setText("Rezerwacje");
        reservationsButton.setContentAreaFilled(false);
        reservationsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reservationsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        reservationsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                reservationsButton.setForeground(hoverColor);
                reservationsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                reservationsButton.setForeground(null);
        });
        reservationsButton.addActionListener(this::reservationsButtonActionPerformed);

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tripsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reservationsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clientsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reservationsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(316, Short.MAX_VALUE))
        );

        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Id", "Imię", "Nazwisko", "E-mail", "Nr telefonu"
            }
        )
        {public boolean isCellEditable(int row, int column){
            return column != 0;
        }});
        clientsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsTable.setMaximumSize(new java.awt.Dimension(375, 550));
        clientsTable.setMinimumSize(new java.awt.Dimension(375, 550));
        clientsTable.setPreferredSize(new java.awt.Dimension(375, 550));
        clientsTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        jScrollPane1.setViewportView(clientsTable);

        searchClientLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13)); // NOI18N
        searchClientLabel.setText("Wyszukaj klienta po e-mailu");

        addClientButton.setBackground(new java.awt.Color(241, 227, 185));
        addClientButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13)); // NOI18N
        addClientButton.setText("+ Dodaj Klienta");
        addClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addClientButton.addActionListener(this::addClientButtonActionPerformed);

        deleteClientButton.setBackground(new java.awt.Color(241, 227, 185));
        deleteClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13)); // NOI18N
        deleteClientButton.setText("Usuń Klienta");
        deleteClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteClientButton.addActionListener(this::deleteClientButtonActionPerformed);

        editClientButton.setBackground(new java.awt.Color(241, 227, 185));
        editClientButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13)); // NOI18N
        editClientButton.setText("Edytuj Klienta");
        editClientButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editClientButton.addActionListener(this::editClientButtonActionPerformed);

        wrongEmailLabel.setForeground(new java.awt.Color(255, 0, 0));
        wrongEmailLabel.setText("");
        wrongEmailLabel.setPreferredSize(new java.awt.Dimension(222, 16));

        editPasswordButton.setBackground(new java.awt.Color(241, 227, 185));
        editPasswordButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13)); // NOI18N
        editPasswordButton.setText("Zmień hasło Klienta");
        editPasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editPasswordButton.addActionListener(this::editPasswordButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 844, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchClientLabel)
                                    .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addClientButton)
                                .addGap(68, 68, 68))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(295, 295, 295)
                                .addComponent(deleteClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(editClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(352, 352, 352)
                                .addComponent(editPasswordButton)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(searchClientLabel)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchClientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(wrongEmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteClientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Trips().setVisible(true);
    }

    private void reservationsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Reservations().setVisible(true);
    }
    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        dispose();
        data.clear();
        StartPage.adminLogged = false;
        Client.operate("logOut");
        new StartPage().setVisible(true);
    }
    private void panelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        dispose();
        data.clear();
        new Dashboard().setVisible(true);
    }
    private void editPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        int row = clientsTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            new ClientPasswordChange().setVisible(true);
            clientIDToChangePassword = Integer.parseInt(model.getValueAt(row, 0).toString());
        }
    }
    private void generateData(){
        Client.operate("clientsUpdate");
        adminNameLabel.setText(adminName);
    }
    private void populateTable(){
        int counter = 0;
        int size = (listDataLength/5);
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{data.get(counter), data.get(counter+1), data.get(counter+2), data.get(counter+3),
                    data.get(counter+4)});
            if(size > 1)
                counter+=5;
        }
    }
    private void performEmailValidation(){
        String emailFromTextField = searchClientTextField.getText();
        if(validation.emailIsValid(emailFromTextField))
            wrongEmailLabel.setText("");
        else
            wrongEmailLabel.setText("Sprawdź czy podany adres e-mail jest poprawny.");
        if(searchClientTextField.getText().isEmpty())
            wrongEmailLabel.setText("");
    }
    private void searchClients(){
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSorter = new TableRowSorter<>(clientsTable.getModel());
        clientsTable.setRowSorter(rowSorter);
        searchClientTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performEmailValidation();
                String text = searchClientTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + text, 3));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                performEmailValidation();
                String text = searchClientTextField.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + text));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                performEmailValidation();
                throw new UnsupportedOperationException("Unsupported event!");
            }
        });
    }
    private void deleteClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        int row = clientsTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            clientIDToRemove = Integer.parseInt(model.getValueAt(row, 0).toString());
            model.removeRow(row);
            Client.operate("deleteClient");
        }
    }
    private void addClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        dispose();
        new Registration().setVisible(true);
    }
    private void editClientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        int row = clientsTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            id = Integer.parseInt(model.getValueAt(row, 0).toString());
            firstName = model.getValueAt(row, 1).toString();
            lastName = model.getValueAt(row, 2).toString();
            email = model.getValueAt(row, 3).toString();
            phoneNumber = model.getValueAt(row, 4).toString();
            if (firstName.equals("") || lastName.equals("") || email.equals("") || phoneNumber.equals("")) {
                JOptionPane.showMessageDialog(this, "Wprowadzono puste dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                new Client("editClient");
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
            java.util.logging.Logger.getLogger(Clients.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Clients().setVisible(true));
    }
}
