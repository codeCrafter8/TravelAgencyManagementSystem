import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Reservations extends javax.swing.JFrame {
    public static List<String> data = new ArrayList<>();
    public static int listDataLength = 0;
    private static TableRowSorter<TableModel> rowSorter;
    public static int resIDToRemove = 0;
    public static int id = 0;
    public static String firstName = "";
    public static String lastName = "";
    public static String departure = "";
    public static String arrival = "";
    public static String phoneNumber = "";
    private String lastNameToSearch;
    private final Validation validation;
    private javax.swing.JLabel adminNameLabel;
    private javax.swing.JButton clientsButton;
    private javax.swing.JButton editResButton;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton panelButton;
    private javax.swing.JTable resTable;
    private javax.swing.JButton reservationsButton1;
    private javax.swing.JTextField searchResTextField;
    private javax.swing.JButton tripsButton;
    private javax.swing.JLabel wrongResLabel;
    public Reservations() {
        validation = new Validation();
        initComponents();
        generateData();
        populateTable();
        searchRes();
        getContentPane().setBackground(new Color(215,198,151));
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(151,123,92));
        for (int i = 0; i < resTable.getModel().getColumnCount(); i++) {
                resTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
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
                    //new Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
        wrongResLabel.setMinimumSize(new java.awt.Dimension(300, 16));
        editResButton.setVisible(false);
    }
    private void initComponents() {
        JPanel menuPanel = new JPanel();
        JPanel adminPanel = new JPanel();
        JLabel adminIconLabel = new JLabel();
        adminNameLabel = new javax.swing.JLabel();
        JLabel adminLabel = new JLabel();
        JPanel optionsPanel = new JPanel();
        panelButton = new javax.swing.JButton();
        clientsButton = new javax.swing.JButton();
        tripsButton = new javax.swing.JButton();
        reservationsButton1 = new javax.swing.JButton();
        JPanel topPanel = new JPanel();
        logOutButton = new javax.swing.JButton();
        JLabel searchResLabel = new JLabel();
        searchResTextField = new javax.swing.JTextField();
        wrongResLabel = new javax.swing.JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        resTable = new javax.swing.JTable();
        JButton addResButton = new JButton();
        JButton deleteResButton = new JButton();
        editResButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Rezerwacje");
        setMaximumSize(new java.awt.Dimension(1040, 770));
        setMinimumSize(new java.awt.Dimension(1040, 770));

        menuPanel.setBackground(new java.awt.Color(118, 98, 75));
        menuPanel.setPreferredSize(new java.awt.Dimension(180, 806));

        adminPanel.setBackground(new java.awt.Color(118, 98, 75));

        adminIconLabel.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/img/adminLOGO.png"))));

        adminNameLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        adminNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        adminNameLabel.setPreferredSize(new java.awt.Dimension(73, 25));

        adminLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        adminLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminLabel.setText("Admin");

        javax.swing.GroupLayout adminPanel1Layout = new javax.swing.GroupLayout(adminPanel);
        adminPanel.setLayout(adminPanel1Layout);
        adminPanel1Layout.setHorizontalGroup(
            adminPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminNameLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(adminLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adminPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(adminIconLabel)
                .addGap(54, 54, 54))
        );
        adminPanel1Layout.setVerticalGroup(
            adminPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(adminIconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(adminNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(adminLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
        optionsPanel.setBackground(new Color(118,98,75));

        panelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
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

        clientsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsButton.setText("    Klienci");
        clientsButton.setBorder(null);
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
        clientsButton.addActionListener(this::clientsButtonActionPerformed);

        tripsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        tripsButton.setText("    Wycieczki");
        tripsButton.setActionCommand(" Wycieczki");
        tripsButton.setBorder(null);
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

        reservationsButton1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        reservationsButton1.setText("   Rezerwacje");
        reservationsButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        reservationsButton1.setContentAreaFilled(false);
        reservationsButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationsButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reservationsButton1.setPreferredSize(new java.awt.Dimension(75, 46));
        reservationsButton1.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                reservationsButton1.setForeground(hoverColor);
                reservationsButton1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                reservationsButton1.setForeground(null);
        });

        javax.swing.GroupLayout optionsPanel1Layout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanel1Layout);
        optionsPanel1Layout.setHorizontalGroup(
            optionsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clientsButton, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
            .addComponent(tripsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reservationsButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanel1Layout.setVerticalGroup(
            optionsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanel1Layout.createSequentialGroup()
                .addComponent(panelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clientsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(reservationsButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menuPanel1Layout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanel1Layout);
        menuPanel1Layout.setHorizontalGroup(
            menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuPanel1Layout.setVerticalGroup(
            menuPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanel1Layout.createSequentialGroup()
                .addComponent(adminPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(321, Short.MAX_VALUE))
        );

        topPanel.setBackground(new java.awt.Color(151, 123, 92));
        topPanel.setPreferredSize(new java.awt.Dimension(205, 34));

        logOutButton.setBackground(new java.awt.Color(242, 242, 242));
        logOutButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
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
                .addContainerGap(753, Short.MAX_VALUE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        searchResLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        searchResLabel.setText("Wyszukaj rezerwację po nazwisku");

        wrongResLabel.setForeground(new java.awt.Color(255, 0, 0));

        resTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Imię", "Nazwisko", "Wyjazd", "Przyjazd", "Nr telefonu"
            }
        )
        {public boolean isCellEditable(int row, int column){
            return column != 0;
        }});
        resTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resTable.setMaximumSize(new java.awt.Dimension(375, 550));
        resTable.setMinimumSize(new java.awt.Dimension(375, 550));
        resTable.setPreferredSize(new java.awt.Dimension(375, 550));
        resTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        jScrollPane1.setViewportView(resTable);

        addResButton.setBackground(new java.awt.Color(241, 227, 185));
        addResButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 13));
        addResButton.setText("+ Dodaj Rezerwację");
        addResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addResButton.addActionListener(this::addResButtonActionPerformed);

        deleteResButton.setBackground(new java.awt.Color(241, 227, 185));
        deleteResButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        deleteResButton.setText("Usuń Rezerwację");
        deleteResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteResButton.addActionListener(this::deleteResButtonActionPerformed);

        editResButton.setBackground(new java.awt.Color(241, 227, 185));
        editResButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 13));
        editResButton.setText("Edytuj Rezerwację");
        editResButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editResButton.addActionListener(this::editResButtonActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 860, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(wrongResLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(searchResTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(322, 322, 322)
                                        .addComponent(addResButton))
                                    .addComponent(searchResLabel)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(360, 360, 360)
                                .addComponent(deleteResButton)
                                .addGap(35, 35, 35)
                                .addComponent(editResButton)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(searchResLabel)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchResTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(wrongResLabel))
                    .addComponent(addResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editResButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void addResButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new ReservationAddition().setVisible(true);
    }

    private void editResButtonActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        int row = resTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnego klienta.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            id = Integer.parseInt(model.getValueAt(row, 0).toString());
            firstName = model.getValueAt(row, 1).toString();
            lastName = model.getValueAt(row, 2).toString();
            departure = model.getValueAt(row, 3).toString();
            arrival = model.getValueAt(row, 4).toString();
            phoneNumber = model.getValueAt(row, 5).toString();
            if (firstName.equals("") || lastName.equals("") || departure.equals("") || arrival.equals("") || phoneNumber.equals("")) {
                JOptionPane.showMessageDialog(this, "Wprowadzono puste dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                Client.operate("editRes");
            }
        }
    }

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        StartPage.adminLogged = false;
        Client.operate("logOut");
        new StartPage().setVisible(true);
    }

    private void clientsButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        data.clear();
        new Clients().setVisible(true);
    }

    private void tripsButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Trips().setVisible(true);
    }

    private void panelButtonActionPerformed(ActionEvent evt) {
        dispose();
        data.clear();
        new Dashboard().setVisible(true);
    }

    private void deleteResButtonActionPerformed(java.awt.event.ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        int row = resTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej rezerwacji.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {resIDToRemove = Integer.parseInt(model.getValueAt(row, 0).toString());
            model.removeRow(row);
            Client.operate("deleteRes");
        }
    }

    private void generateData(){
        Client.operate("resUpdate");
        adminNameLabel.setText(Dashboard.adminName);
    }

    private void populateTable(){
        int counter = 0;
        int size = (listDataLength/6);
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{data.get(counter), data.get(counter+1), data.get(counter+2), data.get(counter+3),
                    data.get(counter+4), data.get(counter+5)});
            if(size > 1)
                counter+=6;
        }
    }

    private void searchRes(){
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rowSorter = new TableRowSorter<>(resTable.getModel());
        resTable.setRowSorter(rowSorter);
        searchResTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performLastNameValidation();
                if (lastNameToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + lastNameToSearch, 2));
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                performLastNameValidation();
                if (lastNameToSearch.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?s)" + lastNameToSearch));
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                performLastNameValidation();
                throw new UnsupportedOperationException("Unsupported event!");
            }
        });
    }

    private void performLastNameValidation() {
        lastNameToSearch = searchResTextField.getText();
        if(validation.lastNameIsValid(lastNameToSearch))
            wrongResLabel.setText("");
        else
            wrongResLabel.setText("Sprawdź czy podane nazwisko jest poprawne.");
        if(lastNameToSearch.equals(""))
            wrongResLabel.setText("");
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
            java.util.logging.Logger.getLogger(Reservations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Reservations().setVisible(true));
    }
}
