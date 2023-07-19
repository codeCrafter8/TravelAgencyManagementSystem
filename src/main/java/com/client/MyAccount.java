package com.client;

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
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność Moje Konto po stronie klienta
 */
public class MyAccount extends javax.swing.JFrame {
    /**
     * Lista przechowująca dane osobowe klienta
     */
    private final List<String> clientData = new ArrayList<>();
    /**
     * Atrybut będący numerem zaznaczonego wiersza w tabeli
     */
    private int row;
    /**
     * Atrybut będący obiektem klasy Client
     */
    private Client client;
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Lista przechowująca dane rezerwacji klienta
     */
    private final List<String> resData = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane zwracane z klasy Client
     */
    List<String> returningData = new ArrayList<>();
    /**
     * Pomocniczy konstruktor
     */
    public MyAccount() {}
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Client
     */
    public MyAccount(Client client){
        this.client = client;
        initComponents();
        generateData();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
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
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Moje konto");
        setPreferredSize(new java.awt.Dimension(989, 770));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsClients("MyAccount", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
    private void setManagingComboBox(){
        managingComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Strona Główna", "Moje konto", "Wyloguj" }));
        managingComboBox.setSelectedIndex(1);
        managingComboBox.setBorder(null);
        managingComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managingComboBox.setFocusable(false);
        managingComboBox.setLightWeightPopupEnabled(false);
        managingComboBox.addActionListener(this::managingActionPerformed);
    }
    private void createResTable(){
        resTableScrollPane.setBackground(new java.awt.Color(255, 255, 255));
        resTableScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        resTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Kraj", "Miasto", "Cena", "Wyjazd", "Przyjazd"
                }
        ));
        resTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resTable.setMaximumSize(new java.awt.Dimension(375, 550));
        resTable.setMinimumSize(new java.awt.Dimension(375, 550));
        resTable.setPreferredSize(new java.awt.Dimension(375, 550));
        resTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resTableScrollPane.setViewportView(resTable);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(151,123,92));
        for (int i = 0; i < resTable.getModel().getColumnCount(); i++) {
            resTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
    }
    private void setLabels(){
        myReservationsLabel.setFont(new java.awt.Font("Arial", Font.ITALIC, 18));
        myReservationsLabel.setText("Moje rezerwacje");

        infoConfirmLabel.setForeground(new java.awt.Color(255, 0, 0));
        infoConfirmLabel.setText("   ");

        userIconLabel.setIcon(new javax.swing.ImageIcon("img\\345564670_3623767247845558_3120332870085421246_n.png"));

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
    private void setButtons(){
        cancelReservationButton.setBackground(new java.awt.Color(151, 123, 92));
        cancelReservationButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        cancelReservationButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelReservationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelReservationButton.setFocusable(false);
        cancelReservationButton.setPreferredSize(new java.awt.Dimension(150, 43));
        cancelReservationButton.addActionListener(this::cancelReservationButtonActionPerformed);
        cancelReservationButton.setText("<html><center>"+"Anuluj"+"<br>"+"rezerwację"+"</center></html>");

        downloadTripDescriptionButton.setBackground(new java.awt.Color(151, 123, 92));
        downloadTripDescriptionButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        downloadTripDescriptionButton.setForeground(new java.awt.Color(255, 255, 255));
        downloadTripDescriptionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadTripDescriptionButton.setFocusable(false);
        downloadTripDescriptionButton.setPreferredSize(new java.awt.Dimension(150, 43));
        downloadTripDescriptionButton.addActionListener(this::downloadTripDescriptionActionPerformed);
        downloadTripDescriptionButton.setText("<html><center>"+"Szczegóły"+"<br>"+"rezerwacji"+"</center></html>");

        editButton.setBackground(new java.awt.Color(151, 123, 92));
        editButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edytuj dane");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setFocusable(false);
        editButton.setPreferredSize(new java.awt.Dimension(150, 43));
        editButton.addActionListener(this::editButtonActionPerformed);

        changePasswordButton.setBackground(new java.awt.Color(151, 123, 92));
        changePasswordButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        changePasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        changePasswordButton.setText("Zmień hasło");
        changePasswordButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePasswordButton.setFocusable(false);
        changePasswordButton.setPreferredSize(new java.awt.Dimension(150, 43));
        changePasswordButton.addActionListener(this::changePasswordButtonActionPerformed);
    }
    private void createUserDataPanel(){
        userDataPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
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
    private void createLayout(){
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
     * Metoda obsługująca kliknięcie przycisku "Zmień hasło"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void changePasswordButtonActionPerformed(ActionEvent evt) {
        new PasswordChange(this.returningData.get(4),client).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Anuluj rezerwację"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void cancelReservationButtonActionPerformed(ActionEvent evt) {
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        row = resTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej rezerwacji.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = null;
            Date now = new Date();
            try {
                date1 = formatter.parse(model.getValueAt(row, 4).toString());
            }catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            if(SearchEngine.getDifferenceDays(now, date1) < 14){
                JOptionPane.showMessageDialog(this, "Nie można anulować tej rezerwacji, ponieważ do wyjazdu zostało mniej niż 14 dni.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Object[] options = {"Tak", "Nie"};
                if (JOptionPane.showOptionDialog(null, "Czy na pewno chcesz anulować rezerwację?", "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null) == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "Pomyślnie anulowano rezerwację. Pieniądze zostaną zwrócone na kartę kredytową, z której dokonano płatności w ciągu 14 dni roboczych.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    data.clear();
                    data.add("deleteRes");
                    data.add(resTable.getValueAt(resTable.getSelectedRow(),0).toString());
                    new Client(data);
                    data.clear();
                    model.removeRow(row);
                }
            }
        }
    }
    /**
     * Metoda obsługująca menu podręczne
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void managingActionPerformed(ActionEvent evt) {
        switch(String.valueOf(managingComboBox.getSelectedItem())){
            case "Wyloguj" -> {
                Object[] options = {"Tak", "Nie"};
                if(JOptionPane.showOptionDialog(null,"Czy na pewno chcesz się wylogować?","Potwierdzenie",
                        JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, null)== JOptionPane.YES_OPTION){
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
     * Metoda wypełniająca tabelę z rezerwacjami klienta
     */
    private void populateTable(){
        int counter = 0;
        int size = (resData.size())/11;
        DefaultTableModel model = (DefaultTableModel) resTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{resData.get(counter), resData.get(counter+1), resData.get(counter+2), Integer.parseInt(resData.get(counter+3)) * Integer.parseInt(resData.get(counter+4)) + " zł",
                    resData.get(counter+5), resData.get(counter+6)});
            if(size > 1)
                counter+=11;
        }
    }
    /**
     * Metoda wypełniająca odpowiednie etykiety danymi osobowymi klienta
     */
    public void updateLabels(){
        typeNameLabel.setText(returningData.get(0));
        typeLastNameLabel.setText(returningData.get(1));
        typeEmailLabel.setText(returningData.get(2));
        typePhoneNumberLabel.setText(returningData.get(3));
    }
    /**
     * Metoda pobierająca odpowiednie dane z klasy Client
     */
    public void generateData(){
        data.clear();
        data.add("myAccountUpdate");
        data.add(client.getUserEmail());
        Client client2 = new Client(data);
        data.clear();
        returningData.addAll(client2.getReturningData());
        for(int i = 0; i < returningData.size(); i++) {
            if(i >= 5)
                resData.add(returningData.get(i));
        }
        for(int i = 0; i < returningData.size(); i++) {
            if(i < 5)
                clientData.add(returningData.get(i));
        }
        updateLabels();
        populateTable();
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Edytuj dane"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new DataEdition(clientData, client).setVisible(true);
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Szczegóły rezerwacji"
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void downloadTripDescriptionActionPerformed(java.awt.event.ActionEvent evt) {
        row = resTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej rezerwacji.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            new TripDescription(this.resData, row).setVisible(true);
        }
    }
    /**
     * Metoda pozwalająca na uruchomienie okna
     * @param args Argumenty przyjmowane podczas uruchamiania aplikacji
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
    //GUI variables
    /**
     * Tabela z rezerwacjami klienta
     */
    private final javax.swing.JTable resTable = new JTable();
    /**
     * Etykieta z emailem klienta
     */
    private final javax.swing.JLabel typeEmailLabel = new JLabel();
    /**
     * Etykieta z imieniem klienta
     */
    private final javax.swing.JLabel typeNameLabel = new JLabel();
    /**
     * Etykieta z numerem telefonu klienta
     */
    private final javax.swing.JLabel typePhoneNumberLabel = new JLabel();
    /**
     * Etykieta z nazwiskiem klienta
     */
    private final javax.swing.JLabel typeLastNameLabel = new JLabel();
    /**
     * Menu podręczne z opcjami Strona Główna, Moje Konto, Wyloguj się
     */
    private final javax.swing.JComboBox<String> managingComboBox = new JComboBox<>();
    private final JScrollPane resTableScrollPane = new JScrollPane();
    private final JLabel myReservationsLabel = new JLabel();
    private final JLabel infoConfirmLabel = new JLabel();
    private final JButton cancelReservationButton = new JButton();
    private final JButton downloadTripDescriptionButton = new JButton();
    private final JLabel infoInsuranceLabel = new JLabel();
    private final JPanel userDataPanel = new JPanel();
    private final JLabel userIconLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();
    private final JLabel lastNameLabel = new JLabel();
    private final JLabel emailLabel = new JLabel();
    private final JLabel phoneNumberLabel = new JLabel();
    private final JButton editButton = new JButton();
    private final JButton changePasswordButton = new JButton();
}