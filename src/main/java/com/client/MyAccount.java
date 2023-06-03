package com.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
public class MyAccount extends javax.swing.JFrame {
    public List<String> clientData = new ArrayList<>();
    public int dataListLength = 0;
    static int row = 0;
    private Client client;
    public List<String> data = new ArrayList<>();
    private final List<String> resData = new ArrayList<>();
    public List<String> returningData = new ArrayList<>();
    private javax.swing.JButton cancelReservationButton;
    private javax.swing.JButton downloadInsuranceButton;
    private javax.swing.JTable resTable;
    private javax.swing.JLabel typeEmailLabel;
    private javax.swing.JLabel typeNameLabel;
    private javax.swing.JLabel typePhoneNumberLabel;
    private javax.swing.JLabel typeLastNameLabel;
    private javax.swing.JComboBox<String> managing;
    public MyAccount() {
        initApp();
    }
    public MyAccount(boolean overrided){}
    public MyAccount(Client client){
        this.client = client;
        initApp();
    }
    private void initApp(){
        initComponents();
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(151,123,92));
        for (int i = 0; i < resTable.getModel().getColumnCount(); i++) {
            resTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        downloadInsuranceButton.setText("<html><center>"+"Szczegóły"+"<br>"+"rezerwacji"+"</center></html>");
        cancelReservationButton.setText("<html><center>"+"Anuluj"+"<br>"+"rezerwację"+"</center></html>");
        resTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        generateData();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.add(client.getUserEmail());
                    new Client("logOut",data);
                    data.clear();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    private void initComponents() {
        managing = new javax.swing.JComboBox<>();
        JScrollPane jScrollPane1 = new JScrollPane();
        resTable = new javax.swing.JTable();
        JLabel myReservationsLabel = new JLabel();
        JLabel infoConfirmLabel = new JLabel();
        cancelReservationButton = new javax.swing.JButton();
        downloadInsuranceButton = new javax.swing.JButton();
        JLabel infoInsuranceLabel = new JLabel();
        JPanel userPanel = new JPanel();
        JLabel iconUser = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel lastNameLabel = new JLabel();
        JLabel emailLabel = new JLabel();
        JLabel phoneNumberLabel = new JLabel();
        typeNameLabel = new javax.swing.JLabel();
        typeLastNameLabel = new javax.swing.JLabel();
        typeEmailLabel = new javax.swing.JLabel();
        typePhoneNumberLabel = new javax.swing.JLabel();
        JButton editButton = new JButton();
        JButton editButton1 = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Moje konto");
        setPreferredSize(new java.awt.Dimension(989, 770));

        managing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Strona Główna", "Moje konto", "Wyloguj" }));
        managing.setSelectedIndex(1);
        managing.setBorder(null);
        managing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managing.setFocusable(false);
        managing.setLightWeightPopupEnabled(false);
        managing.addActionListener(this::managingActionPerformed);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

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
        jScrollPane1.setViewportView(resTable);

        myReservationsLabel.setFont(new java.awt.Font("Arial", Font.ITALIC, 18));
        myReservationsLabel.setText("Moje rezerwacje");

        infoConfirmLabel.setForeground(new java.awt.Color(255, 0, 0));
        infoConfirmLabel.setText("   ");

        cancelReservationButton.setBackground(new java.awt.Color(151, 123, 92));
        cancelReservationButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        cancelReservationButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelReservationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelReservationButton.setFocusable(false);
        cancelReservationButton.setPreferredSize(new java.awt.Dimension(150, 43));
        cancelReservationButton.addActionListener(this::cancelReservationButtonActionPerformed);

        downloadInsuranceButton.setBackground(new java.awt.Color(151, 123, 92));
        downloadInsuranceButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 16));
        downloadInsuranceButton.setForeground(new java.awt.Color(255, 255, 255));
        downloadInsuranceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadInsuranceButton.setFocusable(false);
        downloadInsuranceButton.setPreferredSize(new java.awt.Dimension(150, 43));
        downloadInsuranceButton.addActionListener(this::downloadInsuranceButtonActionPerformed);

        userPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        iconUser.setIcon(new javax.swing.ImageIcon("img\\345564670_3623767247845558_3120332870085421246_n.png"));

        nameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setText("Imię:");

        lastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        lastNameLabel.setText("Nazwisko:");

        emailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        emailLabel.setText("E-mail:");

        phoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        phoneNumberLabel.setText("Numer telefonu:");

        typeNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typeNameLabel.setText("Anna");

        typeLastNameLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typeLastNameLabel.setText("Kowalska");

        typeEmailLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typeEmailLabel.setText("anna@wp.pl");

        typePhoneNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        typePhoneNumberLabel.setText("123456778");

        editButton.setBackground(new java.awt.Color(151, 123, 92));
        editButton.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("Edytuj dane");
        editButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton.setFocusable(false);
        editButton.setPreferredSize(new java.awt.Dimension(150, 43));
        editButton.addActionListener(this::editButtonActionPerformed);

        editButton1.setBackground(new java.awt.Color(151, 123, 92));
        editButton1.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));
        editButton1.setForeground(new java.awt.Color(255, 255, 255));
        editButton1.setText("Zmień hasło");
        editButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editButton1.setFocusable(false);
        editButton1.setPreferredSize(new java.awt.Dimension(150, 43));
        editButton1.addActionListener(this::editButton1ActionPerformed);

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addComponent(iconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberLabel)
                            .addComponent(emailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeEmailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                            .addComponent(typeLastNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typePhoneNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(typeNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lastNameLabel)
                            .addComponent(typeLastNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(typeEmailLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneNumberLabel)
                            .addComponent(typePhoneNumberLabel)))
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(iconUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

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
                                .addComponent(managing, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(downloadInsuranceButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(cancelReservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(userPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(managing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(userPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(myReservationsLabel)
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoConfirmLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downloadInsuranceButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelReservationButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(89, 89, 89)
                .addComponent(infoInsuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    private void editButton1ActionPerformed(ActionEvent evt) {
        //new PasswordChange(this.clientData.get(4),client).setVisible(true);
        new PasswordChange(this.returningData.get(4),client).setVisible(true);
    }

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
                    data.add(resTable.getValueAt(resTable.getSelectedRow(),0).toString());
                    new Client("deleteRes",data);
                    data.clear();
                    model.removeRow(row);
                }
            }
        }
    }
    private void managingActionPerformed(ActionEvent evt) {
        if(Objects.equals(managing.getSelectedItem(), "Wyloguj")) {
            Object[] options = {"Tak", "Nie"};
            if(JOptionPane.showOptionDialog(null,"Czy na pewno chcesz się wylogować?","Potwierdzenie",
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, null)== JOptionPane.YES_OPTION){
                dispose();
                resData.clear();
                data.add(client.getUserEmail());
                new Client("logOut",data);
                data.clear();
                new StartPage().setVisible(true);
            }
        }
        else if(Objects.equals(managing.getSelectedItem(), "Strona Główna")){
            dispose();
            resData.clear();
            new SearchEngine(client).setVisible(true);
        }
    }
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
    public void setLabels(){
        typeNameLabel.setText("");
        typeLastNameLabel.setText("");
        typeEmailLabel.setText("");
        typePhoneNumberLabel.setText("");
        typeNameLabel.setText(returningData.get(0));
        typeLastNameLabel.setText(returningData.get(1));
        typeEmailLabel.setText(returningData.get(2));
        typePhoneNumberLabel.setText(returningData.get(3));
    }
    public void generateData(){
        data.add(client.getUserEmail());
        Client client2 = new Client("myAccountUpdate",data);
        data.clear();
        returningData.addAll(client2.getMyAccountData());
        for(int i = 0; i < returningData.size(); i++) {
            if(i >= 5)
                resData.add(returningData.get(i));
        }
        for(int i = 0; i < returningData.size(); i++) {
            if(i < 5)
                clientData.add(returningData.get(i));
        }
        setLabels();
        populateTable();
    }
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new DataEdition(clientData, client).setVisible(true);
    }

    private void downloadInsuranceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadInsuranceButtonActionPerformed
        row = resTable.getSelectedRow();
        if(row == -1)
            JOptionPane.showMessageDialog(null, "Nie wybrano żadnej rezerwacji.", "Informacja", JOptionPane.ERROR_MESSAGE);
        else {
            new TripDescription(this.resData).setVisible(true);
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
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new MyAccount().setVisible(true));
    }
}
