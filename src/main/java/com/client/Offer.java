package com.client;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Offer extends javax.swing.JFrame {
    public int peopleQuantity = 0;
    public int userID = 0;
    public int tripID = 0;
    public String insurance = "";
    private javax.swing.JLabel tripLengthData;
    private javax.swing.JLabel peopleNumberData;
    private javax.swing.JLabel cityCountry;
    private javax.swing.JLabel descriptionSpace;
    private javax.swing.JLabel departureCityData;
    private javax.swing.JLabel hotelName;
    private javax.swing.JLabel dateData;
    private javax.swing.JLabel typePrice;
    private javax.swing.JCheckBox insurance1;
    private javax.swing.JCheckBox insurance2;
    private javax.swing.JCheckBox insurance3;
    private javax.swing.JLabel photo;
    private Client client;
    private java.util.List<String> data = new ArrayList<>();
    private int attributeQuantity;
    private int adultsQuantity;
    private int childrenQuantity;
    private int idTripToShow;
    private int selectedRow;
    public Offer(Client client, java.util.List<String> data, int attributeQuantity, int adultsQuantity, int childrenQuantity, int idTripToShow, int selectedRow) {
        this.client = client;
        this.data.addAll(data);
        this.attributeQuantity = attributeQuantity;
        this.adultsQuantity = adultsQuantity;
        this.childrenQuantity = childrenQuantity;
        this.idTripToShow = idTripToShow;
        this.selectedRow = selectedRow;
        initComponents();
        generate_data();
        cityCountry.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        hotelName.setFont(new Font("Segoe Print", Font.PLAIN, 20));
        descriptionSpace.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    new Client("logOut", new ArrayList<>());
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new com.server.Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
    }
    public Offer(boolean overrided){}
    private void generate_data() {
        int counter = 0;
        int size = (data.size()/ attributeQuantity);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        for(int i=0; i<size; i++){
            if(Integer.parseInt(data.get(counter+7)) == idTripToShow){
                try {
                    date1 = formatter.parse(String.valueOf(data.get(counter+2)));
                    date2 = formatter.parse(String.valueOf(data.get(counter+3)));
                    daysBetween = (int) SearchEngine.getDifferenceDays(date1, date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                cityCountry.setText(data.get(counter) + ", " + data.get(counter+1));
                dateData.setText("od " + data.get(counter+2) + " do " + data.get(counter+3));
                departureCityData.setText(data.get(counter+5));
                tripLengthData.setText(daysBetween + " dni");
                peopleQuantity = adultsQuantity + childrenQuantity;
                insurance = "";
                peopleNumberData.setText(peopleQuantity + " (" +
                        adultsQuantity + " dorosly, " + childrenQuantity + " dzieci)");
                photo.setIcon(new javax.swing.ImageIcon("src/img/zdjecie" + (selectedRow + 1) + ".jpg"));
                descriptionSpace.setText("<html>" + data.get(counter+8) + "<html>");
                hotelName.setText(data.get(counter+9));
                if(adultsQuantity == 0 && childrenQuantity == 0)
                    typePrice.setText(data.get(counter+4) + " zł");
                else
                    typePrice.setText(Integer.parseInt(data.get(counter+4)) * (adultsQuantity + childrenQuantity) + " zł");
            }
            if(size > 1)
               counter+= attributeQuantity;
        }
    }
    private void initComponents() {
        JPanel mainWindow = new JPanel();
        JLabel travelAgencyText = new JLabel();
        photo = new javax.swing.JLabel();
        JPanel reservationPanel = new JPanel();
        hotelName = new javax.swing.JLabel();
        cityCountry = new javax.swing.JLabel();
        JLabel date = new JLabel();
        JLabel tripLength = new JLabel();
        JLabel departureCity = new JLabel();
        dateData = new javax.swing.JLabel();
        tripLengthData = new javax.swing.JLabel();
        departureCityData = new javax.swing.JLabel();
        JButton reservationButton = new JButton();
        JLabel price = new JLabel();
        typePrice = new javax.swing.JLabel();
        JLabel peopleNumber = new JLabel();
        peopleNumberData = new javax.swing.JLabel();
        JLabel insuranceLabel = new JLabel();
        insurance1 = new javax.swing.JCheckBox();
        insurance2 = new javax.swing.JCheckBox();
        insurance3 = new javax.swing.JCheckBox();
        JLabel hotelDescription = new JLabel();
        descriptionSpace = new javax.swing.JLabel();
        JButton cancel = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1067, 388));

        mainWindow.setMaximumSize(new java.awt.Dimension(1067, 388));
        mainWindow.setMinimumSize(new java.awt.Dimension(1067, 388));

        travelAgencyText.setBackground(new java.awt.Color(151, 123, 92));
        travelAgencyText.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 24)); // NOI18N
        travelAgencyText.setForeground(new java.awt.Color(151, 123, 92));
        travelAgencyText.setText("Travel Agency");

        photo.setMaximumSize(new java.awt.Dimension(550, 225));
        photo.setMinimumSize(new java.awt.Dimension(550, 225));
        photo.setPreferredSize(new java.awt.Dimension(550, 225));

        cityCountry.setToolTipText("");

        date.setText("Termin:");

        tripLength.setText("Długość pobytu:");

        departureCity.setText("Miejsce wylotu:");

        reservationButton.setBackground(new java.awt.Color(151, 123, 92));
        reservationButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14)); // NOI18N
        reservationButton.setForeground(new java.awt.Color(255, 255, 255));
        reservationButton.setText("Rezerwuj");
        reservationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationButton.addActionListener(this::reservationButtonActionPerformed);

        price.setText("CENA:");

        peopleNumber.setText("Liczba osób:");

        javax.swing.GroupLayout reservationPanelLayout = new javax.swing.GroupLayout(reservationPanel);
        reservationPanel.setLayout(reservationPanelLayout);
        reservationPanelLayout.setHorizontalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cityCountry, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hotelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                            .addGroup(reservationPanelLayout.createSequentialGroup()
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(reservationPanelLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(departureCity, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(date)
                                        .addComponent(tripLength)
                                        .addComponent(peopleNumber)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(peopleNumberData, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tripLengthData, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(departureCityData, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateData, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reservationPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(price)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        reservationPanelLayout.setVerticalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(hotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityCountry)
                .addGap(81, 81, 81)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(peopleNumber)
                    .addComponent(peopleNumberData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(date)
                    .addComponent(dateData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tripLength)
                    .addComponent(tripLengthData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureCity)
                    .addComponent(departureCityData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typePrice))
                .addContainerGap())
        );

        insuranceLabel.setText("Ubezpieczenie: (ubezpieczenie obowiązuje tylko na okres podróży)");

        insurance1.setText("Standard (cena:150zł)");
        insurance1.setBorder(null);
        insurance1.addActionListener(this::insurance1ActionPerformed);

        insurance2.setText("Komfort (cena: 300zł)");
        insurance2.addActionListener(this::insurance2ActionPerformed);

        insurance3.setText("Premium (cena: 600zł)");
        insurance3.addActionListener(this::insurance3ActionPerformed);

        hotelDescription.setText("Opis hotelu:");

        descriptionSpace.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        descriptionSpace.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        cancel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        cancel.setForeground(new java.awt.Color(151, 123, 92));
        cancel.setText("Cofnij");
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel.addActionListener(this::cancelActionPerformed);

        javax.swing.GroupLayout mainWindowLayout = new javax.swing.GroupLayout(mainWindow);
        mainWindow.setLayout(mainWindowLayout);
        mainWindowLayout.setHorizontalGroup(
            mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insuranceLabel)
                    .addComponent(insurance1)
                    .addComponent(insurance2)
                    .addComponent(hotelDescription)
                    .addComponent(insurance3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainWindowLayout.createSequentialGroup()
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(mainWindowLayout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(travelAgencyText, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancel))
                    .addGroup(mainWindowLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descriptionSpace, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(mainWindowLayout.createSequentialGroup()
                                .addComponent(photo, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(48, 48, 48))
        );
        mainWindowLayout.setVerticalGroup(
            mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(travelAgencyText, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(reservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(photo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insuranceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insurance1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insurance2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insurance3)
                .addGap(18, 18, 18)
                .addComponent(hotelDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionSpace, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mainWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void reservationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.out.println(insurance);
        new Payment(client,idTripToShow,insurance,peopleQuantity).setVisible(true);
    }

    private void insurance1ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if(insurance1.isSelected()){
            insurance = insurance1.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(insurance2.isSelected())
                insurance2.setSelected(false);
            if(insurance3.isSelected())
                insurance3.setSelected(false);
        }
    }
    private void insurance2ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if(insurance2.isSelected()){
            insurance = insurance2.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(insurance1.isSelected())
                insurance1.setSelected(false);
            if(insurance3.isSelected())
                insurance3.setSelected(false);
        }
    }
    private void insurance3ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if(insurance3.isSelected()){
            insurance = insurance3.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(insurance1.isSelected())
                insurance1.setSelected(false);
            if(insurance2.isSelected())
                insurance2.setSelected(false);
        }
    }
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new SearchEngine(client).setVisible(true);
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
            java.util.logging.Logger.getLogger(Offer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Offer(true).setVisible(true));
    }
}
