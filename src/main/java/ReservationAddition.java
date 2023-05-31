import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class ReservationAddition extends javax.swing.JFrame {
    private javax.swing.JTable clientsTable;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tripsTable;
    public ReservationAddition() {
        initComponents();
        getContentPane().setBackground(new Color(215,198,151));
        generateData();
        populateClientsTable();
        populateTripsTable();
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    Client.operate("logOut");
                    Clients.data.clear();
                    dispose();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        tripsTable = new javax.swing.JTable();
        JScrollPane jScrollPane2 = new JScrollPane();
        clientsTable = new javax.swing.JTable();
        JButton submitButton = new JButton();
        JButton cancelButton = new JButton();
        jSpinner1 = new javax.swing.JSpinner();
        JLabel jLabel1 = new JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        JLabel jLabel2 = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1040, 770));
        setTitle("Dodawanie rezerwacji");
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
        tripsTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        jScrollPane1.setViewportView(tripsTable);

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
        clientsTable.setSelectionBackground(new java.awt.Color(202, 186, 143));
        jScrollPane2.setViewportView(clientsTable);

        submitButton.setBackground(new java.awt.Color(189, 165, 111));
        submitButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14)); // NOI18N
        submitButton.setText("Dodaj rezerwację");
        submitButton.addActionListener(evt -> submitButtonActionPerformed());
        submitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cancelButton.setBackground(new java.awt.Color(189, 165, 111));
        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 14)); // NOI18N
        cancelButton.setText("Anuluj");
        cancelButton.setPreferredSize(new java.awt.Dimension(116, 27));
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed());
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));

        jLabel1.setText("Liczba osób:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Brak", "Standard", "Komfort", "Premium" }));

        jLabel2.setText("Ubezpieczenie:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 234, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(150, 150, 150))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        pack();
        setLocationRelativeTo(null);
    }
    private void generateData(){
        Client.operate("clientsUpdate");
        Client.operate("tripsListPopulation");
    }
    private void populateClientsTable(){
        int counter = 0;
        int size = (Clients.listDataLength/5);
        DefaultTableModel model = (DefaultTableModel) clientsTable.getModel();
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{Clients.data.get(counter), Clients.data.get(counter+1), Clients.data.get(counter+2), Clients.data.get(counter+3),
                    Clients.data.get(counter+4)});
            if(size > 1)
                counter+=5;
        }
    }
    private void populateTripsTable(){
        int counter = 0;
        int size = (SearchEngine.listDataLength/ SearchEngine.attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tripsTable.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{SearchEngine.data.get(counter+7), SearchEngine.data.get(counter) + '/' + SearchEngine.data.get(counter+1), SearchEngine.data.get(counter+4) + " zł", SearchEngine.data.get(counter+6),(SearchEngine.data.get(counter+2) + " - " + SearchEngine.data.get(counter+3)),});
            if(size > 1)
                counter+= SearchEngine.attributesQuantity;
        }
    }
    private void submitButtonActionPerformed() {
        DefaultTableModel clientsModel = (DefaultTableModel) clientsTable.getModel();
        int clientsRow = clientsTable.getSelectedRow();
        DefaultTableModel tripsModel = (DefaultTableModel) tripsTable.getModel();
        int tripsRow = tripsTable.getSelectedRow();
        if(Integer.parseInt(tripsModel.getValueAt(tripsRow, 3).toString()) < Integer.parseInt(jSpinner1.getValue().toString())){
            JOptionPane.showMessageDialog(null, "Zbyt duża liczba osób.", "Informacja", JOptionPane.ERROR_MESSAGE);
        }
        else{
            Offer.userID = Integer.parseInt(clientsModel.getValueAt(clientsRow, 0).toString());
            Offer.tripID = Integer.parseInt(tripsModel.getValueAt(tripsRow, 0).toString());
            Offer.peopleQuantity = Integer.parseInt(jSpinner1.getValue().toString());
            String item = (String) jComboBox1.getSelectedItem();
            if(Objects.equals(item, "Brak"))
                Offer.insurance = "";
            else
                Offer.insurance = item;
            Client.operate("addReservation");
            dispose();
            Clients.data.clear();
            Trips.data.clear();
            Reservations.data.clear();
            new Reservations().setVisible(true);
        }
    }

    private void cancelButtonActionPerformed() {
        Clients.data.clear();
        dispose();
        new Reservations().setVisible(true);
    }

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
        java.awt.EventQueue.invokeLater(() -> new ReservationAddition().setVisible(true));
    }
}
