/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kamil
 */
public class oferty extends javax.swing.JFrame {
    public static int peopleQuantity = 0;
    public static int userID = 0;
    public static int tripID = 0;
    public static String insurance = "";
    /**
     * Creates new form oferty
     */
    public oferty() {
        initComponents();
        generate_data();
        miasto_kraj.setFont(new Font("Segoe Print", Font.PLAIN, 15));
        nazwa_hotelu.setFont(new Font("Segoe Print", Font.PLAIN, 20));
        miejsce_na_opis.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    Client.operate("logOut");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    //new Logs("[ " + new java.util.Date() + " ] " + ex.getMessage(), "EkranGlownyAdmin", "error");
                }
            }
        });
        //nazwa_hotelu.setText("Binkowski");
        //typePrice.setText("15 407 zł");
        //miasto_kraj.setText("Francja, Paryż"); 
        //miejsce_wylotu_dane.setText("Krakow");
        //liczba_osob_dane.setText("5");
        //termin_dane.setText("od");
        //dlugosc_pobytu_dane.setText("2");
        //jLabel10.setText("<html>Hotel to idealne miejsce dla osób, które cenią sobie komfortowy wypoczynek w otoczeniu pięknej przyrody. Nasz hotel znajduje się w urokliwej okolicy, z dala od zgiełku miasta. W naszej ofercie znajdą Państwo różnorodne pokoje, które zostały urządzone z myślą o zapewnieniu maksymalnego komfortu i wypoczynku. Wliczone w cenę noclegu śniadania to połączenie tradycyjnych smaków z nowoczesnymi trendami kulinarnej sztuki. W restauracji hotelowej serwujemy również obiady i kolacje, które zachwycą każde podniebienie. Dodatkowo, nasz hotel oferuje wiele atrakcji, takich jak basen, spa, sala fitness czy siłownia. Zapraszamy do skorzystania z naszych usług i spędzenia niezapomnianego wypoczynku w Hotelu.</html>");
    }
    private void generate_data() {
        int counter = 0;
        int size = (WYSZUKIWARKA.listDataLength/WYSZUKIWARKA.attributesQuantity);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        for(int i=0; i<size; i++){
            if(Integer.parseInt(WYSZUKIWARKA.data.get(counter+7)) == WYSZUKIWARKA.idTripToShow){
                try {
                    date1 = formatter.parse(String.valueOf(WYSZUKIWARKA.data.get(counter+2)));
                    date2 = formatter.parse(String.valueOf(WYSZUKIWARKA.data.get(counter+3)));
                    daysBetween = (int) WYSZUKIWARKA.getDifferenceDays(date1, date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                miasto_kraj.setText(WYSZUKIWARKA.data.get(counter) + ", " + WYSZUKIWARKA.data.get(counter+1));
                termin_dane.setText("od " + WYSZUKIWARKA.data.get(counter+2) + " do " + WYSZUKIWARKA.data.get(counter+3));
                miejsce_wylotu_dane.setText(WYSZUKIWARKA.data.get(counter+5));
                dlugosc_pobytu_dane.setText(daysBetween + " dni");
                peopleQuantity = WYSZUKIWARKA.howManyAdults + WYSZUKIWARKA.howManyChildren;
                System.out.println("people quantity: " + peopleQuantity);
                insurance = "";
                liczba_osob_dane.setText(String.valueOf(peopleQuantity) + " (" +
                        String.valueOf(WYSZUKIWARKA.howManyAdults) + " dorosly, " + WYSZUKIWARKA.howManyChildren + " dzieci)");
                zdjecie.setIcon(new javax.swing.ImageIcon("src/img/zdjecie" + (WYSZUKIWARKA.selectedRow + 1) + ".jpg"));
                miejsce_na_opis.setText("<html>" + WYSZUKIWARKA.data.get(counter+8) + "<html>");
                nazwa_hotelu.setText(WYSZUKIWARKA.data.get(counter+9));
                if(WYSZUKIWARKA.howManyAdults == 0 && WYSZUKIWARKA.howManyChildren == 0)
                    typePrice.setText(WYSZUKIWARKA.data.get(counter+4) + " zł");
                else
                    typePrice.setText(Integer.parseInt(WYSZUKIWARKA.data.get(counter+4)) * (WYSZUKIWARKA.howManyAdults + WYSZUKIWARKA.howManyChildren) + " zł");
            }
            if(size > 1)
                counter+=WYSZUKIWARKA.attributesQuantity;
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        glowne_okno = new javax.swing.JPanel();
        nazwa = new javax.swing.JLabel();
        zdjecie = new javax.swing.JLabel();
        panel_rezerwuj = new javax.swing.JPanel();
        nazwa_hotelu = new javax.swing.JLabel();
        miasto_kraj = new javax.swing.JLabel();
        termin = new javax.swing.JLabel();
        dlugosc_pobytu = new javax.swing.JLabel();
        miejsce_wylotu = new javax.swing.JLabel();
        termin_dane = new javax.swing.JLabel();
        dlugosc_pobytu_dane = new javax.swing.JLabel();
        miejsce_wylotu_dane = new javax.swing.JLabel();
        rezerwuj = new javax.swing.JButton();
        cena = new javax.swing.JLabel();
        typePrice = new javax.swing.JLabel();
        liczba_osob = new javax.swing.JLabel();
        liczba_osob_dane = new javax.swing.JLabel();
        ubezpieczenie = new javax.swing.JLabel();
        ubezpieczenie_wariant1 = new javax.swing.JCheckBox();
        ubezpieczenie_wariant2 = new javax.swing.JCheckBox();
        ubezpieczenie_wariant_3 = new javax.swing.JCheckBox();
        opis_hotelu = new javax.swing.JLabel();
        miejsce_na_opis = new javax.swing.JLabel();
        cofnij = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1067, 388));

        glowne_okno.setMaximumSize(new java.awt.Dimension(1067, 388));
        glowne_okno.setMinimumSize(new java.awt.Dimension(1067, 388));

        nazwa.setBackground(new java.awt.Color(151, 123, 92));
        nazwa.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        nazwa.setForeground(new java.awt.Color(151, 123, 92));
        nazwa.setText("Travel Agency");

        zdjecie.setMaximumSize(new java.awt.Dimension(550, 225));
        zdjecie.setMinimumSize(new java.awt.Dimension(550, 225));
        zdjecie.setPreferredSize(new java.awt.Dimension(550, 225));

        miasto_kraj.setToolTipText("");

        termin.setText("Termin:");

        dlugosc_pobytu.setText("Długość pobytu:");

        miejsce_wylotu.setText("Miejsce wylotu:");

        rezerwuj.setBackground(new java.awt.Color(151, 123, 92));
        rezerwuj.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        rezerwuj.setForeground(new java.awt.Color(255, 255, 255));
        rezerwuj.setText("Rezerwuj");
        rezerwuj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rezerwuj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rezerwujActionPerformed(evt);
            }
        });

        cena.setText("CENA:");

        liczba_osob.setText("Liczba osób:");

        javax.swing.GroupLayout panel_rezerwujLayout = new javax.swing.GroupLayout(panel_rezerwuj);
        panel_rezerwuj.setLayout(panel_rezerwujLayout);
        panel_rezerwujLayout.setHorizontalGroup(
            panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_rezerwujLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_rezerwujLayout.createSequentialGroup()
                        .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(miasto_kraj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nazwa_hotelu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                            .addGroup(panel_rezerwujLayout.createSequentialGroup()
                                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_rezerwujLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(miejsce_wylotu, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(termin)
                                        .addComponent(dlugosc_pobytu)
                                        .addComponent(liczba_osob)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(liczba_osob_dane, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dlugosc_pobytu_dane, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(miejsce_wylotu_dane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(termin_dane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_rezerwujLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cena)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(typePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rezerwuj, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel_rezerwujLayout.setVerticalGroup(
            panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_rezerwujLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(nazwa_hotelu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(miasto_kraj)
                .addGap(81, 81, 81)
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(liczba_osob)
                    .addComponent(liczba_osob_dane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(termin)
                    .addComponent(termin_dane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dlugosc_pobytu)
                    .addComponent(dlugosc_pobytu_dane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(miejsce_wylotu)
                    .addComponent(miejsce_wylotu_dane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(panel_rezerwujLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rezerwuj, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(typePrice))
                .addContainerGap())
        );

        ubezpieczenie.setText("Ubezpieczenie: (ubezpieczenie obowiązuje tylko na okres podróży)");

        //buttonGroup1.add(ubezpieczenie_wariant1);
        ubezpieczenie_wariant1.setText("Standard (cena:150zł)");
        ubezpieczenie_wariant1.setBorder(null);
        //nowe
        ubezpieczenie_wariant1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubezpieczenie_wariant1ActionPerformed(evt);
            }
        });

        //buttonGroup1.add(ubezpieczenie_wariant2);
        ubezpieczenie_wariant2.setText("Komfort (cena: 300zł)");
        ubezpieczenie_wariant2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubezpieczenie_wariant2ActionPerformed(evt);
            }
        });

        //buttonGroup1.add(ubezpieczenie_wariant_3);
        ubezpieczenie_wariant_3.setText("Premium (cena: 600zł)");
        //nowe
        ubezpieczenie_wariant_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubezpieczenie_wariant3ActionPerformed(evt);
            }
        });

        opis_hotelu.setText("Opis hotelu:");

        miejsce_na_opis.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        miejsce_na_opis.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        cofnij.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cofnij.setForeground(new java.awt.Color(151, 123, 92));
        cofnij.setText("Cofnij");
        cofnij.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cofnij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cofnijActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glowne_oknoLayout = new javax.swing.GroupLayout(glowne_okno);
        glowne_okno.setLayout(glowne_oknoLayout);
        glowne_oknoLayout.setHorizontalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowne_oknoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ubezpieczenie)
                    .addComponent(ubezpieczenie_wariant1)
                    .addComponent(ubezpieczenie_wariant2)
                    .addComponent(opis_hotelu)
                    .addComponent(ubezpieczenie_wariant_3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glowne_oknoLayout.createSequentialGroup()
                .addGroup(glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(glowne_oknoLayout.createSequentialGroup()
                        .addGap(420, 420, 420)
                        .addComponent(nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cofnij))
                    .addGroup(glowne_oknoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(miejsce_na_opis, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(glowne_oknoLayout.createSequentialGroup()
                                .addComponent(zdjecie, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panel_rezerwuj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(48, 48, 48))
        );
        glowne_oknoLayout.setVerticalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowne_oknoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nazwa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cofnij))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_rezerwuj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zdjecie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ubezpieczenie)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ubezpieczenie_wariant1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ubezpieczenie_wariant2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ubezpieczenie_wariant_3)
                .addGap(18, 18, 18)
                .addComponent(opis_hotelu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(miejsce_na_opis, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(glowne_okno, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(glowne_okno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rezerwujActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rezerwujActionPerformed
        // TODO add your handling code here:
        //nowe
        new Payment().setVisible(true);
        //System.out.println("insurance: " + insurance);
    }//GEN-LAST:event_rezerwujActionPerformed
    //nowe
    private void ubezpieczenie_wariant1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int index;
        if(ubezpieczenie_wariant1.isSelected()){
            insurance = ubezpieczenie_wariant1.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(ubezpieczenie_wariant2.isSelected())
                ubezpieczenie_wariant2.setSelected(false);
            if(ubezpieczenie_wariant_3.isSelected())
                ubezpieczenie_wariant_3.setSelected(false);
        }
    }
    private void ubezpieczenie_wariant2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubezpieczenie_wariant2ActionPerformed
        // TODO add your handling code here:
        //nowe
        int index;
        if(ubezpieczenie_wariant2.isSelected()){
            insurance = ubezpieczenie_wariant2.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(ubezpieczenie_wariant1.isSelected())
                ubezpieczenie_wariant1.setSelected(false);
            if(ubezpieczenie_wariant_3.isSelected())
                ubezpieczenie_wariant_3.setSelected(false);
        }
    }//GEN-LAST:event_ubezpieczenie_wariant2ActionPerformed
    //nowe
    private void ubezpieczenie_wariant3ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        int index;
        if(ubezpieczenie_wariant_3.isSelected()){
            insurance = ubezpieczenie_wariant_3.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if(ubezpieczenie_wariant1.isSelected())
                ubezpieczenie_wariant1.setSelected(false);
            if(ubezpieczenie_wariant2.isSelected())
                ubezpieczenie_wariant2.setSelected(false);
        }
    }
    private void cofnijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cofnijActionPerformed
        // TODO add your handling code here:
        //nowe
        dispose();
        new WYSZUKIWARKA().setVisible(true);
    }//GEN-LAST:event_cofnijActionPerformed

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
            java.util.logging.Logger.getLogger(oferty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(oferty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(oferty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(oferty.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new oferty().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cena;
    private javax.swing.JButton cofnij;
    private javax.swing.JLabel dlugosc_pobytu;
    private javax.swing.JLabel dlugosc_pobytu_dane;
    private javax.swing.JPanel glowne_okno;
    private javax.swing.JLabel liczba_osob;
    private javax.swing.JLabel liczba_osob_dane;
    private javax.swing.JLabel miasto_kraj;
    private javax.swing.JLabel miejsce_na_opis;
    private javax.swing.JLabel miejsce_wylotu;
    private javax.swing.JLabel miejsce_wylotu_dane;
    private javax.swing.JLabel nazwa;
    private javax.swing.JLabel nazwa_hotelu;
    private javax.swing.JLabel opis_hotelu;
    private javax.swing.JPanel panel_rezerwuj;
    private javax.swing.JButton rezerwuj;
    private javax.swing.JLabel termin;
    private javax.swing.JLabel termin_dane;
    private javax.swing.JLabel typePrice;
    private javax.swing.JLabel ubezpieczenie;
    private javax.swing.JCheckBox ubezpieczenie_wariant1;
    private javax.swing.JCheckBox ubezpieczenie_wariant2;
    private javax.swing.JCheckBox ubezpieczenie_wariant_3;
    private javax.swing.JLabel zdjecie;
    // End of variables declaration//GEN-END:variables
}
