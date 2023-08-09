package com.client;

import com.client.utils.ColorUtils;
import com.client.utils.DimensionUtils;
import com.server.LogsClients;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * A class containing fields and methods for handling the window with functionality of My Account for the client.
 */
public class MyAccount extends javax.swing.JFrame {

    /**
     * A dimension used for the buttons in the user interface, specifying the width and height.
     * The buttons created with this dimension will have a width of 150 pixels and a height of 43 pixels.
     */
    private static final Dimension BUTTON_DIMENSION = new Dimension(150, 43);

    /**
     * A dimension used for the main window of the user interface, specifying the width and height.
     * The window created with this dimension will have a width of 989 pixels and a height of 770 pixels.
     */
    private static final Dimension WINDOW_DIMENSION = new Dimension(989, 770);

    /**
     * A list storing client's personal data.
     */
    private final List<String> clientData = new ArrayList<>();
    
    /**
     * An attribute representing the selected row number in the table.
     */
    private int row;
    
    /**
     * An attribute being an object of the Client class.
     */
    private Client client;
    
    /**
     * An attribute being a list that stores data passed to the Client class.
     */
    private final List<String> data = new ArrayList<>();
    
    /**
     * A list storing client's reservation data.
     */
    private final List<String> resData = new ArrayList<>();
    
    /**
     * An attribute being a list that stores data returned from the Client class.
     */
    List<String> returningData = new ArrayList<>();

    /**
     * A helper constructor.
     */
    public MyAccount() {
    }

    /**
     * A constructor responsible for GUI initialization and relevant elements.
     *
     * @param client object of the Client class
     */
    public MyAccount(Client client) {
        this.client = client;
        initComponents();
        generateData();
    }

    /**
     * Initializes graphic components used in the window.
     */
    private void initComponents() {
        setWindowProperties();
        setManagingComboBox();
        createResTable();
        setLabels();
        setButtons();
        createUserDataPanel();
        createLayout();
    }

    /**
     * Sets the properties of the window, such as the default close operation,
     * title, preferred size, and window listener for handling the window closing event.
     * When the window is closing, it performs necessary actions like logging out the user.
     */
    private void setWindowProperties() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Moje konto");
        setPreferredSize(WINDOW_DIMENSION);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsClients("MyAccount", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }

    /**
     * Sets up the managing combo box with initial values and event listener.
     * The combo box provides options for managing different sections of the user interface.
     */
    private void setManagingComboBox() {
        managingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Strona Główna", "Moje konto", "Wyloguj"}));
        managingComboBox.setSelectedIndex(1);
        managingComboBox.setBorder(null);
        managingComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managingComboBox.setFocusable(false);
        managingComboBox.setLightWeightPopupEnabled(false);
        managingComboBox.addActionListener(this::managingActionPerformed);
    }

    /**
     * Creates and configures the reservation table along with the scroll pane used to display it.
     * The table includes various columns to display reservation information.
     */
    private void createResTable() {
        resTableScrollPane.setBackground(Color.WHITE);
        resTableScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        resTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Id", "Kraj", "Miasto", "Cena", "Wyjazd", "Przyjazd"
                }
        ));
        resTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resTable.setMaximumSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setMinimumSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setPreferredSize(DimensionUtils.TABLE_DIMENSION);
        resTable.setSelectionBackground(ColorUtils.BEIGE);
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resTableScrollPane.setViewportView(resTable);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(ColorUtils.LIGHT_BROWN);
        for (int i = 0; i < resTable.getModel().getColumnCount(); i++) {
            resTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }

    /**
     * Sets up various labels used in the user interface, including reservation labels and user data labels.
     */
    private void setLabels() {
        myReservationsLabel.setFont(new java.awt.Font("Arial", Font.ITALIC, 18));
        myReservationsLabel.setText("Moje rezerwacje");

        infoConfirmLabel.setForeground(Color.RED);
        infoConfirmLabel.setText("   ");

        userIconLabel.setIcon(new javax.swing.ImageIcon("img\\user.png"));

        nameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Imię:");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        lastNameLabel.setText("Nazwisko:");

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail:");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        phoneNumberLabel.setText("Numer telefonu:");

        typeNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typeLastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typeEmailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typePhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
    }

    /**
     * Sets up various buttons used in the user interface, such as canceling a reservation,
     * downloading trip descriptions, editing user information, and changing passwords.
     */
    private void setButtons() {
        cancelReservationButton.setBackground(ColorUtils.LIGHT_BROWN);
        cancelReservationButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        cancelReservationButton.setForeground(Color.WHITE);
        cancelReservationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelReservationButton.setFocusable(false);
        cancelReservationButton.setPreferredSize(BUTTON_DIMENSION);
        cancelReservationButton.addActionListener(this::cancelReservationButtonActionPerformed);
        cancelReservationButton.setText("<html><center>" + "Anuluj" + "<br>" + "rezerwację" + "</center></html>");

        downloadTripDescriptionButton.setBackground(ColorUtils.LIGHT_BROWN);
        downloadTripDescriptionButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        downloadTripDescriptionButton.setForeground(Color.WHITE);
        downloadTripDescriptionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadTripDescriptionButton.setFocusable(false);
        downloadTripDescriptionButton.setPreferredSize(BUTTON_DIMENSION);
        downloadTripDescriptionButton.addActionListener(this::downloadTripDescriptionActionPerformed);
        downloadTripDescriptionButton.setText("<html><center>" + "Szczegóły" + "<br>" + "rezerwacji" + "</center></html>");

        editButton.setBackground(ColorUtils.LIGHT_BROWN);
        editButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        editButton.setForeground(Color.WHITE);
        editButton.setText("Edytuj dane");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setFocusable(false);
        editButton.setPreferredSize(BUTTON_DIMENSION);
        editButton.addActionListener(this::editButtonActionPerformed);

        changePasswordButton.setBackground(ColorUtils.LIGHT_BROWN);
        changePasswordButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setText("Zmień hasło");
        changePasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordButton.setFocusable(false);
        changePasswordButton.setPreferredSize(BUTTON_DIMENSION);
        changePasswordButton.addActionListener(this::changePasswordButtonActionPerformed);
    }

    /**
     * Creates the user data panel that contains labels displaying the user's name, last name,
     * email, phone number, along with user icon or avatar.
     */
    private void createUserDataPanel() {
        userDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(ColorUtils.GREY));
        javax.swing.GroupLayout userDataPanelLayout = new javax.swing.GroupLayout(userDataPanel);
        userDataPanel.setLayout(userDataPanelLayout);
        userDataPanelLayout.setHorizontalGroup(
                userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(userDataPanelLayout.createSequentialGroup()
                                                .addComponent(userIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(phoneNumberLabel)
                                                        .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(12, 12, 12)
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(typeEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                                        .addComponent(typeLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(typeNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(typePhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userDataPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        userDataPanelLayout.setVerticalGroup(
                userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(userDataPanelLayout.createSequentialGroup()
                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(userDataPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(nameLabel)
                                                        .addComponent(typeNameLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lastNameLabel)
                                                        .addComponent(typeLastNameLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(emailLabel)
                                                        .addComponent(typeEmailLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(phoneNumberLabel)
                                                        .addComponent(typePhoneNumberLabel)))
                                        .addGroup(userDataPanelLayout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(userIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(userDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(changePasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
    }

    /**
     * Creates the layout for the user interface by arranging different components in a specific order.
     * This includes managing combo box, user data panel, reservation table, and various labels and buttons.
     */
    private void createLayout() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addComponent(myReservationsLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(managingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(resTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(18, Short.MAX_VALUE)
                                                .addComponent(infoConfirmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(395, 395, 395)))
                                .addGap(39, 39, 39))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(584, 584, 584)
                                                .addComponent(infoInsuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(320, 320, 320)
                                                .addComponent(downloadTripDescriptionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(32, 32, 32)
                                                .addComponent(cancelReservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(240, 240, 240)
                                                .addComponent(userDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(managingComboBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(userDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(myReservationsLabel)
                                .addGap(15, 15, 15)
                                .addComponent(resTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(infoConfirmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(downloadTripDescriptionButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelReservationButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(89, 89, 89)
                                .addComponent(infoInsuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    
    /**
     * Handles the click event of the "Change Password" button.
     *
     * @param evt ActionEvent object generated on button click
     */
    private void changePasswordButtonActionPerformed(ActionEvent evt) {
        new PasswordChange(this.returningData.get(4), client).setVisible(true);
    }
    
    /**
     * Handles the click event of the "Cancel Reservation" button.
     *
     * @param evt ActionEvent object generated on button click
     */
    private void cancelReservationButtonActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        row = resTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "No reservation selected.", "Information", JOptionPane.ERROR_MESSAGE);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date now = new Date();
            try {
                date1 = formatter.parse(model.getValueAt(row, 4).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            if (SearchEngine.getDifferenceDays(now, date1) < 14) {
                JOptionPane.showMessageDialog(this, "Nie można anulować rezerwacji, do której zostało mniej niż 14 dni.", "Błąd", JOptionPane.ERROR_MESSAGE);
            } else {
                Object[] options = {"Yes", "No"};
                if (JOptionPane.showOptionDialog(null, "Czy na pewno chcesz anulować rezerwację?", "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Pomyślnie anulowano rezerwację. Pieniądze zostaną zwrócone na konto, z którego dokonano rezerwacji w przeciągu 14 dni roboczych.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    data.clear();
                    data.add("deleteRes");
                    data.add(resTable.getValueAt(resTable.getSelectedRow(), 0).toString());
                    new Client(data);
                    data.clear();
                    model.removeRow(row);
                }
            }
        }
    }
    
    /**
     * Handles the popup menu.
     *
     * @param evt ActionEvent object generated on button click
     */
    private void managingActionPerformed(ActionEvent evt) {
        switch (String.valueOf(managingComboBox.getSelectedItem())) {
            case "Wyloguj" -> {
                Object[] options = {"Yes", "No"};
                if (JOptionPane.showOptionDialog(null, "Czy na pewno chcesz się wylogować?", "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION) {
                    dispose();
                    resData.clear();
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                    new StartPage().setVisible(true);
                }
            }
            case "Strona Główna" -> {
                dispose();
                resData.clear();
                new SearchEngine(client).setVisible(true);
            }
        }
    }
    
    /**
     * Populates the reservation table.
     */
    private void populateTable() {
        int counter = 0;
        int size = (resData.size()) / 11;
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        for (int i = 0; i < size; i++) {
            model.addRow(new Object[]{resData.get(counter), resData.get(counter + 1), resData.get(counter + 2), Integer.parseInt(resData.get(counter + 3)) * Integer.parseInt(resData.get(counter + 4)) + " zł",
                    resData.get(counter + 5), resData.get(counter + 6)});
            if (size > 1)
                counter += 11;
        }
    }
    
    /**
     * Updates the labels with client's personal data.
     */
    public void updateLabels() {
        typeNameLabel.setText(returningData.get(0));
        typeLastNameLabel.setText(returningData.get(1));
        typeEmailLabel.setText(returningData.get(2));
        typePhoneNumberLabel.setText(returningData.get(3));
    }

    /**
     * Fetches data from the Client class.
     */
    public void generateData() {
        data.clear();
        data.add("myAccountUpdate");
        data.add(client.getUserEmail());
        Client client2 = new Client(data);
        data.clear();
        returningData.addAll(client2.getReturningData());
        for (int i = 0; i < returningData.size(); i++) {
            if (i >= 5)
                resData.add(returningData.get(i));
        }
        for (int i = 0; i < returningData.size(); i++) {
            if (i < 5)
                clientData.add(returningData.get(i));
        }
        updateLabels();
        populateTable();
    }

    /**
     * Handles the click event of the "Edit Data" button.
     *
     * @param evt ActionEvent object generated on button click
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new DataEdition(clientData, client).setVisible(true);
    }

    /**
     * Handles the click event of the "Trip Details" button.
     *
     * @param evt ActionEvent object generated on button click
     */
    private void downloadTripDescriptionActionPerformed(java.awt.event.ActionEvent evt) {
        row = resTable.getSelectedRow();
        if (row == -1)
            JOptionPane.showMessageDialog(null, "No reservation selected.", "Information", JOptionPane.ERROR_MESSAGE);
        else {
            new TripDescription(this.resData, row).setVisible(true);
        }
    }

    /**
     * Allows the window to run.
     *
     * @param args Arguments passed during application startup
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
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MyAccount().setVisible(true));
    }

    /**
     * A table with client's reservations.
     */
    private final javax.swing.JTable resTable = new JTable();

    /**
     * A label with the client's email.
     */
    private final javax.swing.JLabel typeEmailLabel = new JLabel();

    /**
     * A label with the client's first name.
     */
    private final javax.swing.JLabel typeNameLabel = new JLabel();

    /**
     * A label with the client's phone number.
     */
    private final javax.swing.JLabel typePhoneNumberLabel = new JLabel();

    /**
     * A label with the client's last name.
     */
    private final javax.swing.JLabel typeLastNameLabel = new JLabel();

    /**
     * A popup menu with options: Logout, Home Page.
     */
    private final javax.swing.JComboBox<String> managingComboBox = new JComboBox<>();

    /**
     * A scroll pane used to display the reservation table in the user interface.
     */
    private final JScrollPane resTableScrollPane = new JScrollPane();

    /**
     * A label that represents the caption for the "My Reservations" section in the user interface.
     */
    private final JLabel myReservationsLabel = new JLabel();

    /**
     * A label that provides information or confirmation messages in the user interface.
     */
    private final JLabel infoConfirmLabel = new JLabel();

    /**
     * A button used to cancel a reservation in the user interface.
     */
    private final JButton cancelReservationButton = new JButton();

    /**
     * A button used to download the description of a trip.
     */
    private final JButton downloadTripDescriptionButton = new JButton();

    /**
     * A label that provides information about insurance in the user interface.
     */
    private final JLabel infoInsuranceLabel = new JLabel();

    /**
     * A panel that contains user data in the user interface.
     */
    private final JPanel userDataPanel = new JPanel();

    /**
     * A label that represents the user icon or avatar in the user interface.
     */
    private final JLabel userIconLabel = new JLabel();

    /**
     * A label that displays the user's first name in the user interface.
     */
    private final JLabel nameLabel = new JLabel();

    /**
     * A label that displays the user's last name in the user interface.
     */
    private final JLabel lastNameLabel = new JLabel();

    /**
     * A label that displays the user's email in the user interface.
     */
    private final JLabel emailLabel = new JLabel();

    /**
     * A label that displays the user's phone number in the user interface.
     */
    private final JLabel phoneNumberLabel = new JLabel();

    /**
     * A button used to edit user information in the user interface.
     */
    private final JButton editButton = new JButton();

    /**
     * A button used to change the user's password in the user interface.
     */
    private final JButton changePasswordButton = new JButton();
}