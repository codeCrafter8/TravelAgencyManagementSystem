package com.client;

import com.server.LogsAdmins;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność dodania rezerwacji
 */
public class ReservationAddition extends javax.swing.JFrame {
    /**
     * Atrybut będący listą przechowującą dane przekazywane do klasy Client
     */
    private final List<String> data = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane wycieczek zwracane z klasy Client
     */
    private final List<String> tripsData = new ArrayList<>();
    /**
     * Atrybut będący listą przechowującą dane klientów zwracane z klasy Client
     */
    private final List<String> clientsData = new ArrayList<>();
    /**
     * Atrybut będący obiektem klasy Client
     */
    private final Client client;
    /**
     * Atrybut będący imieniem administratora
     */
    private final String adminName;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param client parametr przechowujący obiekt klasy Klient
     * @param adminName parametr przechowujący imię administratora
     */
    public ReservationAddition(Client client, String adminName) {
        this.client = client;
        this.adminName = adminName;
        initComponents();
        generateData();
        populateClientsTable();
        populateTripsTable();
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        setTripsTable();
        setClientsTable();
        setButtons();
        setPeopleQuantityComponents();
        setInsuranceComponents();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1040, 770));
        setTitle("Dodawanie rezerwacji");
        getContentPane().setBackground(ColorUtils.BEIGE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    data.add(client.getUserEmail());
                    new Client(data);
                    data.clear();
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsAdmins("ReservationAddition", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
    private void setTripsTable(){
        tripsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Kraj/Miasto", "Cena/Osoba", "Limit osób", "Termin"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tripsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsTable.setMaximumSize(new java.awt.Dimension(375, 550));
        tripsTable.setMinimumSize(new java.awt.Dimension(375, 550));
        tripsTable.setPreferredSize(new java.awt.Dimension(375, 550));
        tripsTable.setSelectionBackground(ColorUtils.BEIGE);
        tripsTableScrollPane.setViewportView(tripsTable);
    }
    private void setClientsTable(){
        clientsTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Id", "Imię", "Nazwisko", "E-mail", "Nr telefonu"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsTable.setMaximumSize(new java.awt.Dimension(375, 550));
        clientsTable.setMinimumSize(new java.awt.Dimension(375, 550));
        clientsTable.setPreferredSize(new java.awt.Dimension(375, 550));
        clientsTable.setSelectionBackground(ColorUtils.BEIGE);
        clientsTableScrollPane.setViewportView(clientsTable);
    }
    private void setButtons(){
        submitButton.setBackground(ColorUtils.DARK_BEIGE);
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        submitButton.setText("Dodaj rezerwację");
        submitButton.addActionListener(evt -> submitButtonActionPerformed());
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(ColorUtils.DARK_BEIGE);
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new java.awt.Dimension(116, 27));
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed());
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
    private void setPeopleQuantityComponents(){
        peopleQuantitySpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        peopleQuantityLabel.setText("Liczba osób:");
    }
    private void setInsuranceComponents(){
        insuranceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brak", "Standard", "Komfort", "Premium" }));
        insuranceLabel.setText("Ubezpieczenie:");
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 234, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                                        .addComponent(clientsTableScrollPane))
                                .addGap(150, 150, 150))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(333, 333, 333)
                                                .addComponent(peopleQuantityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(peopleQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(insuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(insuranceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(374, 374, 374)
                                                .addComponent(submitButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(clientsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tripsTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(peopleQuantityLabel)
                                        .addComponent(peopleQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(insuranceLabel)
                                        .addComponent(insuranceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda pobierająca odpowiednie dane z klasy Client
     */
    private void generateData(){
        data.clear();
        data.add("clientsUpdate");
        Client client1 = new Client(data);
        data.clear();
        data.add("tripsListPopulation");
        clientsData.addAll(client1.getReturningData());
        Client client2 = new Client(data);
        tripsData.addAll(client2.getReturningData());
    }
    /**
     * Metoda wypełniająca tabelę z klientami
     */
    private void populateClientsTable(){
        int counter = 0;
        int size = (clientsData.size()/5);
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{clientsData.get(counter), clientsData.get(counter+1), clientsData.get(counter+2), clientsData.get(counter+3),
                    clientsData.get(counter+4)});
            if(size > 1)
                counter+=5;
        }
    }
    /**
     * Metoda wypełniająca tabelę z wycieczkami
     */
    private void populateTripsTable(){
        int counter = 0;
        int size = (tripsData.size()/10);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{tripsData.get(counter+7), tripsData.get(counter) + '/' + tripsData.get(counter+1), tripsData.get(counter+4) + " zł", tripsData.get(counter+6),(tripsData.get(counter+2) + " - " + tripsData.get(counter+3)),});
            if(size > 1)
                counter+=10;
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Dodaj rezerwację"
     */
    private void submitButtonActionPerformed() {
        DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
        int clientsRow = clientsTable.getSelectedRow();
        DefaultTableModel tripsModel = (DefaultTableModel) tripsTable.getModel();
        int tripsRow = tripsTable.getSelectedRow();
        if(Integer.parseInt(tripsModel.getValueAt(tripsRow, 3).toString()) < Integer.parseInt(peopleQuantitySpinner.getValue().toString())){
            JOptionPane.showMessageDialog(null, "Zbyt duża liczba osób.", "Informacja", JOptionPane.ERROR_MESSAGE);
        }
        else{
            data.clear();
            data.add("addReservation");
            data.add(tripsModel.getValueAt(tripsRow, 0).toString());
            data.add(clientsModel.getValueAt(clientsRow, 0).toString());
            data.add(peopleQuantitySpinner.getValue().toString());
            String item = (String) insuranceComboBox.getSelectedItem();
            if(Objects.equals(item, "Brak"))
                data.add("");
            else
                data.add(item);
            new Client(data);
            data.clear();
            dispose();
            new Reservations(client,adminName).setVisible(true);
        }
    }
    /**
     * Metoda obsługująca kliknięcie przycisku "Anuluj rezerwację"
     */
    private void cancelButtonActionPerformed() {
        dispose();
        new Reservations(client,adminName).setVisible(true);
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
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationAddition.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ReservationAddition(null,null).setVisible(true));
    }
    //GUI variables
    /**
     * Tabela z klientami
     */
    private final javax.swing.JTable clientsTable = new JTable();
    /**
     * Menu podręczne z opcjami ubezpieczenia
     */
    private final javax.swing.JComboBox<String> insuranceComboBox = new JComboBox<>();
    /**
     * Element umożliwiający wybór ilości osób
     */
    private final javax.swing.JSpinner peopleQuantitySpinner = new JSpinner();
    /**
     * Tabela z wycieczkami
     */
    private final javax.swing.JTable tripsTable = new JTable();
    private final JScrollPane tripsTableScrollPane = new JScrollPane();
    private final JScrollPane clientsTableScrollPane = new JScrollPane();
    private final JButton submitButton = new JButton();
    private final JButton cancelButton = new JButton();
    private final JLabel peopleQuantityLabel = new JLabel();
    private final JLabel insuranceLabel = new JLabel();
}