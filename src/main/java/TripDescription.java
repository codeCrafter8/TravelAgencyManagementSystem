/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zuzan
 */
public class TripDescription extends javax.swing.JFrame {
    public String insurance = "";
    /**
     * Creates new form TripDescription
     */
    public TripDescription() {
        initComponents();
        generateData();
        //typeHotelDescriptionLabel.setText("<html>Hotel to miejsce, w ktorym zawsze poczujesz sie wyjatkowo. Nasz hotel to polaczenie stylowego wnetrza z najnowszymi trendami w branzy hotelarskiej. W naszej ofercie znajduja sie pokoje o roznym standardzie, ktore zapewnia maksymalny komfort i wypoczynek. Wliczone w cene noclegu sniadania to idealny sposob na rozpoczecie dnia pelnego wrazen. Dodatkowo, nasza restauracja oferuje obiady i kolacje, ktore zadowola najbardziej wymagajacych gosci. Nasz hotel oferuje rowniez wiele udogodnien, takich jak spa, basen, centrum fitness czy silownia. Dla milosnikow aktywnego wypoczynku mamy rowniez wiele atrakcji, takich jak wypozyczalnia rowerow czy korty tenisowe. Zapraszamy do skorzystania z naszych uslug i spedzenia niezapomnianego wypoczynku w Hotelu.<html>");
    }

    private void generateData() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        int counter = 0;
        int size = (MyAccount.resDataListLength/11);
        for(int i=0; i<size; i++){
            if(i == MyAccount.row) {
                try {
                    date1 = formatter.parse(String.valueOf(MyAccount.resData.get(counter+5)));
                    date2 = formatter.parse(String.valueOf(MyAccount.resData.get(counter+6)));
                    daysBetween = (int) WYSZUKIWARKA.getDifferenceDays(date1, date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                typeCountryLabel.setText(MyAccount.resData.get(counter+1));
                typeCityLabel.setText(MyAccount.resData.get(counter+2));
                typeDepartureCityLabel.setText(MyAccount.resData.get(counter+8));
                typeDateLabel.setText("od " + MyAccount.resData.get(counter+5) + " do " + MyAccount.resData.get(counter+6));
                typeDaysLabel.setText(String.valueOf(daysBetween));
                typePeopleLabel.setText(MyAccount.resData.get(counter+4));
                typePriceLabel.setText(MyAccount.resData.get(counter+3) + " zł");
                insurance = MyAccount.resData.get(counter+7);
                typeInsuranceLabel.setText(insurance);
                typeHotelNameLabel.setText(MyAccount.resData.get(counter+9));
                typeHotelDescriptionLabel.setText("<html>" + MyAccount.resData.get(counter+10)+ "<html>");
            }
            if(size > 1)
                counter+=11;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        countryLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        departureCityLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        daysLabel = new javax.swing.JLabel();
        peopleLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        insuranceLabel = new javax.swing.JLabel();
        hotelNameLabel = new javax.swing.JLabel();
        hotelDescriptionLabel = new javax.swing.JLabel();
        typeCountryLabel = new javax.swing.JLabel();
        typeCityLabel = new javax.swing.JLabel();
        typeDepartureCityLabel = new javax.swing.JLabel();
        typeDateLabel = new javax.swing.JLabel();
        typeDaysLabel = new javax.swing.JLabel();
        typePeopleLabel = new javax.swing.JLabel();
        typePriceLabel = new javax.swing.JLabel();
        typeInsuranceLabel = new javax.swing.JLabel();
        typeHotelNameLabel = new javax.swing.JLabel();
        typeHotelDescriptionLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Szczegóły wycieczki");

        countryLabel.setText("Kraj:");

        cityLabel.setText("Miasto:");

        departureCityLabel.setText("Miejsce wylotu/przylotu:");

        dateLabel.setText("Data wycieczki:");

        daysLabel.setText("Liczba dni:");

        peopleLabel.setText("Liczba osób:");

        priceLabel.setText("Cena za osobę:");

        insuranceLabel.setText("Ubezpieczenie:");

        hotelNameLabel.setText("Nazwa hotelu:");

        hotelDescriptionLabel.setText("Opis hotelu:");

        typeHotelDescriptionLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jButton1.setBackground(new java.awt.Color(102, 102, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("src/img/questionMark.png")); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setMargin(new java.awt.Insets(0, 14, 0, 14));
        jButton1.setPreferredSize(new java.awt.Dimension(22, 24));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hotelDescriptionLabel)
                    .addComponent(priceLabel)
                    .addComponent(peopleLabel)
                    .addComponent(daysLabel)
                    .addComponent(dateLabel)
                    .addComponent(countryLabel)
                    .addComponent(cityLabel)
                    .addComponent(departureCityLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(insuranceLabel))
                    .addComponent(hotelNameLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeCountryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeDepartureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeDaysLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typePeopleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typePriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeInsuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeHotelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(typeHotelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(countryLabel)
                    .addComponent(typeCountryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(typeCityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(departureCityLabel)
                    .addComponent(typeDepartureCityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateLabel)
                    .addComponent(typeDateLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(daysLabel)
                    .addComponent(typeDaysLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(typePriceLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(peopleLabel)
                            .addComponent(typePeopleLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(priceLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(typeInsuranceLabel)
                            .addComponent(insuranceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(typeHotelNameLabel)
                            .addComponent(hotelNameLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(typeHotelDescriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hotelDescriptionLabel))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //nowe
    private void jButtonActionPerformed(ActionEvent evt) {
        /*int index;
        index = insurance.indexOf(" ");
        insurance = insurance.substring(0, index);*/
        if(insurance.equals("Standard"))
            JOptionPane.showMessageDialog(null, "Ubezpieczenie Standard (cena: 150 zł).\n\nObejmuje ubezpieczenia:\nkosztów leczenia i transportu do 20 000 EUR,\n" +
                    "następstw nieszczęśliwych wypadków,\n", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Komfort"))
            JOptionPane.showMessageDialog(null, "Ubezpieczenie Komfort (cena: 300 zł).\n\nObejmuje:\nkoszty leczenia i transportu do 50 000 EUR, w tym koszty powstałe na skutek zachorowania na COVID-19,\n" +
                    "dodatkowe świadczenie na wypadek kwarantanny w wysokości 1 000 EUR,\n" +
                    "koszty następstw nieszczęśliwych wypadków,\n" +
                    "koszty przerwania podróży (do 5 000 PLN),\n" +
                    "ubezpieczenie bagażu do 2 000 PLN,\n" +
                    "opóźnienie dostarczenia bagażu do 1 000 PLN,\n" +
                    "koszty ratownictwa i poszukiwań (do 5 000 EUR).", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Premium"))
            JOptionPane.showMessageDialog(null, "Ubezpieczenie Premium (cena: 600 zł).\n\nObejmuje:\nkoszty leczenia i transportu do 100 000 EUR, w tym koszty powstałe na skutek zachorowania na COVID-19 i następstw chorób przewlekłych,\n" +
                    "dodatkowe świadczenia na wypadek kwarantanny w wysokości 1 000 EUR,\n" +
                    "koszty następstw nieszczęśliwych wypadków,\n" +
                    "koszty przerwania podróży (do 5 000 PLN),\n" +
                    "ubezpieczenie bagażu do 3 000 PLN,\n" +
                    "opóźnienie dostarczenia bagażu do 1 000 PLN,\n" +
                    "koszty ratownictwa i poszukiwań (do 5 000 EUR).\n", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Brak"))
            JOptionPane.showMessageDialog(null, "Ubezpieczenie nie zostało wykupione.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TripDescription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel daysLabel;
    private javax.swing.JLabel departureCityLabel;
    private javax.swing.JLabel hotelDescriptionLabel;
    private javax.swing.JLabel hotelNameLabel;
    private javax.swing.JLabel insuranceLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel peopleLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JLabel typeCityLabel;
    private javax.swing.JLabel typeCountryLabel;
    private javax.swing.JLabel typeDateLabel;
    private javax.swing.JLabel typeDaysLabel;
    private javax.swing.JLabel typeDepartureCityLabel;
    private javax.swing.JLabel typeHotelDescriptionLabel;
    private javax.swing.JLabel typeHotelNameLabel;
    private javax.swing.JLabel typeInsuranceLabel;
    private javax.swing.JLabel typePeopleLabel;
    private javax.swing.JLabel typePriceLabel;
    // End of variables declaration//GEN-END:variables
}
