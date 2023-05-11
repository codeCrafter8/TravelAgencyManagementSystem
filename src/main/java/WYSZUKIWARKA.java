/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//package com.mycompany.front;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 *
 * @author Kamil
 */
public class WYSZUKIWARKA extends javax.swing.JFrame {
    private static final String phoneNumberPattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private static final Pattern compiledPhoneNumberPattern = Pattern.compile(phoneNumberPattern);
    private static Pattern datePattern = Pattern.compile(
            "^\\d{2}/\\d{2}/\\d{4}$");
    private Timer time;
    private int counter = 1;
    public static int attributesQuantity = 10;
    public static List<String> data = new ArrayList<>();
    public static List<String> destination = new ArrayList<>();
    public static int destinationListLength;
    public static List<String> departure = new ArrayList<>();
    public static int departureListLength;
    public static int listDataLength = 0;
    public static String number;
    public static int selectedRow;
    public static int howManyAdults;
    public static int howManyChildren;
    /**
     * Creates new form WYSZUKIWARKA
     */
    public WYSZUKIWARKA() {
        initComponents();
        howManyChildren = 0;
        howManyAdults = 1;
        //nowe
        zostaw_numer.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (zostaw_numer.getText().equals("Zostaw nr tel. - oddzwonimy do ciebie")) {
                zostaw_numer.setText("");
                zostaw_numer.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (zostaw_numer.getText().isEmpty()) {
                zostaw_numer.setForeground(Color.GRAY);
                zostaw_numer.setText("Zostaw nr tel. - oddzwonimy do ciebie");
            }
        }
        });
        wyjazd.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (wyjazd.getText().equals("04/07/2023")) {
                wyjazd.setText("");
                wyjazd.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (wyjazd.getText().isEmpty()) {
                wyjazd.setForeground(Color.GRAY);
                wyjazd.setText("04/07/2023");
            }
        }
        });
        przyjazd.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (przyjazd.getText().equals("11/07/2023")) {
                przyjazd.setText("");
                przyjazd.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (przyjazd.getText().isEmpty()) {
                przyjazd.setForeground(Color.GRAY);
                przyjazd.setText("11/07/2023");
            }
        }
        });
        photos();
        generate_data();
        //nowe
        destination.clear();
        departure.clear();
        getDestination();
        getDeparture();
        populateTable();
    }

    private void getDestination() {
        Client.operate("getDestination");
        dokad.removeAllItems();
        for(String s : destination)
            dokad.addItem(s);
    }
    private void getDeparture() {
        Client.operate("getDeparture");
        skad.removeAllItems();
        for(String s : departure)
            skad.addItem(s);
    }

    private void generate_data() {
        Client.operate("tripsListPopulation");
    }

    private void populateTable(){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                    data.get(counter+4) + " zł"});
            if(size > 1)
                counter+=attributesQuantity;
        }
    }

    private void photos()
    {
        panel_zdjecia.setIcon(new javax.swing.ImageIcon("src/img/photo1.jpg"));
        time = new Timer(3000, new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panel_zdjecia.setIcon(new javax.swing.ImageIcon("src/img/photo"+counter+".jpg"));
                if(counter==5) counter=0;
                counter++;
            }
        });
        time.start();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jColorChooser1 = new javax.swing.JColorChooser();
        Scrool_cale_okno = new javax.swing.JScrollPane();
        glowne_okno = new javax.swing.JPanel();
        glowne = new javax.swing.JPanel();
        panel_Kraje = new javax.swing.JPanel();
        wczasy = new javax.swing.JButton();
        lato2023 = new javax.swing.JButton();
        lastminute = new javax.swing.JButton();
        egzotyka = new javax.swing.JButton();
        grecja = new javax.swing.JButton();
        hiszpania = new javax.swing.JButton();
        Turcja = new javax.swing.JButton();
        Egipt = new javax.swing.JButton();
        wlochy = new javax.swing.JButton();
        bulgaria = new javax.swing.JButton();
        nazwa_biura = new javax.swing.JLabel();
        panel_wyszukiwarka = new javax.swing.JPanel();
        kierunek_podrozy = new javax.swing.JLabel();
        miejsce_wylotu = new javax.swing.JLabel();
        wyjazd_przyjazd = new javax.swing.JLabel();
        ilosc_osob = new javax.swing.JLabel();
        panel_wyszukiwarka_wprowadzanie = new javax.swing.JPanel();
        dokad = new javax.swing.JComboBox<>();
        skad = new javax.swing.JComboBox<>();
        wyjazd = new javax.swing.JTextField();
        przyjazd = new javax.swing.JTextField();
        //nowe
        ilosc_doroslych = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        //nowe
        ilosc_dzieci = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));
        lupa = new javax.swing.JButton();
        panel_zdjecia = new javax.swing.JLabel();
        scroll_tabela = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        zarzadzanie = new javax.swing.JComboBox<>();
        stopka = new javax.swing.JPanel();
        zostaw_numer = new javax.swing.JTextField();
        wyslij_stopka = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        Scrool_cale_okno.setBorder(null);
        Scrool_cale_okno.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scrool_cale_okno.setMaximumSize(new java.awt.Dimension(1022, 400));
        Scrool_cale_okno.setMinimumSize(new java.awt.Dimension(1022, 400));
        Scrool_cale_okno.setPreferredSize(new java.awt.Dimension(1022, 729));

        glowne_okno.setMaximumSize(new java.awt.Dimension(1022, 729));
        glowne_okno.setMinimumSize(new java.awt.Dimension(1022, 729));
        glowne_okno.setPreferredSize(new java.awt.Dimension(1022, 720));

        glowne.setPreferredSize(new java.awt.Dimension(955, 2000));

        wczasy.setBackground(new java.awt.Color(151, 123, 92));
        wczasy.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        wczasy.setForeground(new java.awt.Color(255, 255, 255));
        wczasy.setText("Wczasy");
        wczasy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        wczasy.setFocusPainted(false);
        wczasy.setFocusable(false);
        wczasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wczasyActionPerformed(evt);
            }
        });

        lato2023.setBackground(new java.awt.Color(151, 123, 92));
        lato2023.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        lato2023.setForeground(new java.awt.Color(255, 255, 255));
        lato2023.setText("Lato 2023");
        lato2023.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lato2023.setFocusable(false);
        //nowe
        lato2023.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lato2023ActionPerformed(evt);
            }
        });

        lastminute.setBackground(new java.awt.Color(151, 123, 92));
        lastminute.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        lastminute.setForeground(new java.awt.Color(255, 255, 255));
        lastminute.setText("Last Minute");
        lastminute.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastminute.setFocusable(false);
        lastminute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastminuteActionPerformed(evt);
            }
        });

        egzotyka.setBackground(new java.awt.Color(151, 123, 92));
        egzotyka.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        egzotyka.setForeground(new java.awt.Color(255, 255, 255));
        egzotyka.setText("Egzotyka");
        egzotyka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        egzotyka.setFocusable(false);
        egzotyka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                egzotykaActionPerformed(evt);
            }
        });

        grecja.setBackground(new java.awt.Color(151, 123, 92));
        grecja.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        grecja.setForeground(new java.awt.Color(255, 255, 255));
        grecja.setText("Grecja");
        grecja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        grecja.setFocusable(false);
        grecja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grecjaActionPerformed(evt);
            }
        });

        hiszpania.setBackground(new java.awt.Color(151, 123, 92));
        hiszpania.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        hiszpania.setForeground(new java.awt.Color(255, 255, 255));
        hiszpania.setText("Hiszpania");
        hiszpania.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hiszpania.setFocusable(false);
        hiszpania.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiszpaniaActionPerformed(evt);
            }
        });

        Turcja.setBackground(new java.awt.Color(151, 123, 92));
        Turcja.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        Turcja.setForeground(new java.awt.Color(255, 255, 255));
        Turcja.setText("Turcja");
        Turcja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Turcja.setFocusable(false);
        Turcja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TurcjaActionPerformed(evt);
            }
        });

        Egipt.setBackground(new java.awt.Color(151, 123, 92));
        Egipt.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        Egipt.setForeground(new java.awt.Color(255, 255, 255));
        Egipt.setText("Egipt");
        Egipt.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Egipt.setFocusable(false);
        Egipt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EgiptActionPerformed(evt);
            }
        });

        wlochy.setBackground(new java.awt.Color(151, 123, 92));
        wlochy.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        wlochy.setForeground(new java.awt.Color(255, 255, 255));
        wlochy.setText("Włochy");
        wlochy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        wlochy.setFocusable(false);
        wlochy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wlochyActionPerformed(evt);
            }
        });

        bulgaria.setBackground(new java.awt.Color(151, 123, 92));
        bulgaria.setFont(new java.awt.Font("Segoe Print", 1, 14)); // NOI18N
        bulgaria.setForeground(new java.awt.Color(255, 255, 255));
        bulgaria.setText("Bułgaria");
        bulgaria.setFocusable(false);
        //nowe
        bulgaria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bulgaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bulgariaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_KrajeLayout = new javax.swing.GroupLayout(panel_Kraje);
        panel_Kraje.setLayout(panel_KrajeLayout);
        panel_KrajeLayout.setHorizontalGroup(
            panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KrajeLayout.createSequentialGroup()
                .addComponent(wczasy, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lato2023, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastminute)
                .addGap(0, 0, 0)
                .addComponent(egzotyka)
                .addGap(0, 0, 0)
                .addComponent(grecja, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(hiszpania)
                .addGap(0, 0, 0)
                .addComponent(Turcja, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Egipt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(wlochy)
                .addGap(0, 0, 0)
                .addComponent(bulgaria, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panel_KrajeLayout.setVerticalGroup(
            panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(wczasy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lato2023, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lastminute, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(egzotyka, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(grecja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(hiszpania, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Turcja, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(Egipt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(wlochy, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bulgaria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        nazwa_biura.setBackground(new java.awt.Color(151, 123, 92));
        nazwa_biura.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        nazwa_biura.setForeground(new java.awt.Color(151, 123, 92));
        nazwa_biura.setText("Travel Agency");

        panel_wyszukiwarka.setBackground(new java.awt.Color(151, 123, 92));

        kierunek_podrozy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kierunek_podrozy.setForeground(new java.awt.Color(255, 255, 255));
        kierunek_podrozy.setText("Kierunek podróży");

        miejsce_wylotu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        miejsce_wylotu.setForeground(new java.awt.Color(255, 255, 255));
        miejsce_wylotu.setText("Miejsce wylotu");

        wyjazd_przyjazd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        wyjazd_przyjazd.setForeground(new java.awt.Color(255, 255, 255));
        wyjazd_przyjazd.setText("Wyjazd/Przyjazd");

        ilosc_osob.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ilosc_osob.setForeground(new java.awt.Color(255, 255, 255));
        ilosc_osob.setText("Ilość dorosłych/dzieci");

        panel_wyszukiwarka_wprowadzanie.setBackground(new java.awt.Color(151, 123, 92));

        dokad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dokąd?"}));
        dokad.setFocusable(false);

        skad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Skąd?"}));
        skad.setFocusable(false);
        skad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skadActionPerformed(evt);
            }
        });

        wyjazd.setForeground(new java.awt.Color(153, 153, 153));
        wyjazd.setText("04/07/2023");
        wyjazd.setToolTipText("");
        wyjazd.setAutoscrolls(false);
        wyjazd.setSelectionColor(new java.awt.Color(255, 255, 255));
        wyjazd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyjazdActionPerformed(evt);
            }
        });

        przyjazd.setForeground(new java.awt.Color(153, 153, 153));
        przyjazd.setText("11/07/2023");
        przyjazd.setAutoscrolls(false);
        przyjazd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                przyjazdActionPerformed(evt);
            }
        });
        ilosc_doroslych.setFocusable(false);

        ilosc_dzieci.setFocusable(false);

        javax.swing.GroupLayout panel_wyszukiwarka_wprowadzanieLayout = new javax.swing.GroupLayout(panel_wyszukiwarka_wprowadzanie);
        panel_wyszukiwarka_wprowadzanie.setLayout(panel_wyszukiwarka_wprowadzanieLayout);
        panel_wyszukiwarka_wprowadzanieLayout.setHorizontalGroup(
            panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dokad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(skad, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                        .addComponent(wyjazd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(przyjazd, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                        .addComponent(ilosc_doroslych, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ilosc_dzieci, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panel_wyszukiwarka_wprowadzanieLayout.setVerticalGroup(
            panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                .addComponent(dokad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(skad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(przyjazd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wyjazd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ilosc_doroslych, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ilosc_dzieci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        lupa.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/search-icon-png-0.png"))); // NOI18N
        lupa.setContentAreaFilled(false);
        lupa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lupa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_wyszukiwarkaLayout = new javax.swing.GroupLayout(panel_wyszukiwarka);
        panel_wyszukiwarka.setLayout(panel_wyszukiwarkaLayout);
        panel_wyszukiwarkaLayout.setHorizontalGroup(
            panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kierunek_podrozy)
                    .addComponent(wyjazd_przyjazd)
                    .addComponent(miejsce_wylotu)
                    .addComponent(ilosc_osob))
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel_wyszukiwarka_wprowadzanie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lupa)
                        .addGap(29, 29, 29))))
        );
        panel_wyszukiwarkaLayout.setVerticalGroup(
            panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addComponent(panel_wyszukiwarka_wprowadzanie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lupa))
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addComponent(kierunek_podrozy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miejsce_wylotu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(wyjazd_przyjazd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ilosc_osob)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll_tabela.setBorder(null);
        scroll_tabela.setToolTipText("");
        scroll_tabela.setColumnHeaderView(null);

        tabela.setAutoCreateRowSorter(true);
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kraj", "Miasto", "Termin", "Cena/Osoba"
            }
        ));
        tabela.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setFocusable(false);
        tabela.setGridColor(new java.awt.Color(255, 255, 255));
        tabela.setPreferredSize(new java.awt.Dimension(300, 355));
        tabela.setSelectionBackground(new java.awt.Color(151, 123, 92));
        tabela.setSelectionForeground(new java.awt.Color(255, 255, 255));
        tabela.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabela.setShowGrid(true);
        tabela.getTableHeader().setResizingAllowed(false);
        scroll_tabela.setViewportView(tabela);
        //nowe
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedRow = tabela.rowAtPoint(evt.getPoint());
                if (selectedRow >= 0) {
                    int counter = 0;
                    int size = (listDataLength/attributesQuantity);
                    for(int i=0; i<size; i++) {
                        if(Integer.parseInt(WYSZUKIWARKA.data.get(counter+7)) == WYSZUKIWARKA.selectedRow + 1){
                                dispose();
                                new oferty().setVisible(true);
                        }
                        if(size > 1)
                            counter+=attributesQuantity;
                    }
                }
            }
        });

        zarzadzanie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Moje konto", "Moje rezerwacje", "Wyloguj" }));
        zarzadzanie.setBorder(null);
        zarzadzanie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        zarzadzanie.setFocusable(false);
        zarzadzanie.setLightWeightPopupEnabled(false);
        //nowe
        zarzadzanie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zarzadzanieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout glowneLayout = new javax.swing.GroupLayout(glowne);
        glowne.setLayout(glowneLayout);
        glowneLayout.setHorizontalGroup(
            glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_Kraje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(glowneLayout.createSequentialGroup()
                .addComponent(panel_wyszukiwarka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_zdjecia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glowneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nazwa_biura, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255)
                .addComponent(zarzadzanie, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(scroll_tabela)
        );
        glowneLayout.setVerticalGroup(
            glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nazwa_biura, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zarzadzanie, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_Kraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_wyszukiwarka, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_zdjecia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll_tabela, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout glowne_oknoLayout = new javax.swing.GroupLayout(glowne_okno);
        glowne_okno.setLayout(glowne_oknoLayout);
        glowne_oknoLayout.setHorizontalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowne_oknoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(glowne, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        glowne_oknoLayout.setVerticalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(glowne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        Scrool_cale_okno.setViewportView(glowne_okno);

        stopka.setBackground(new java.awt.Color(151, 123, 92));

        zostaw_numer.setForeground(new java.awt.Color(153, 153, 153));
        zostaw_numer.setText("Zostaw nr tel. - oddzwonimy do ciebie");
        zostaw_numer.setMinimumSize(new java.awt.Dimension(64, 27));
        zostaw_numer.setPreferredSize(new java.awt.Dimension(64, 22));
        zostaw_numer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zostaw_numerActionPerformed(evt);
            }
        });

        wyslij_stopka.setForeground(new java.awt.Color(151, 123, 92));
        wyslij_stopka.setText("Wyślij");
        wyslij_stopka.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        wyslij_stopka.setFocusable(false);
        //nowe
        wyslij_stopka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wyslij_stopkaActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("img/slucahwki.png"))); // NOI18N

        javax.swing.GroupLayout stopkaLayout = new javax.swing.GroupLayout(stopka);
        stopka.setLayout(stopkaLayout);
        stopkaLayout.setHorizontalGroup(
            stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stopkaLayout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zostaw_numer, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wyslij_stopka)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stopkaLayout.setVerticalGroup(
            stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stopkaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zostaw_numer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(stopkaLayout.createSequentialGroup()
                        .addGroup(stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(wyslij_stopka)
                            .addComponent(jLabel2))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(stopka, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Scrool_cale_okno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Scrool_cale_okno, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stopka, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //nowe
    private void zarzadzanieActionPerformed(ActionEvent evt) {
        if(zarzadzanie.getSelectedItem().equals("Wyloguj")) {
            Object[] options = {"Tak", "Nie"};
            if(JOptionPane.showOptionDialog(null,"Czy na pewno chcesz się wylogować?","Potwierdzenie",
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, null)== JOptionPane.YES_OPTION){
                dispose();
                Client.operate("logOut");
                StartPageFrame.client_logged = false;
                new StartPageFrame().setVisible(true);
            }
        }
    }
    //nowe
    private void lato2023ActionPerformed(ActionEvent evt) {
        //nowe
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start = "2023-07-01";
        String end = "2023-08-31";
        Date date1 = null;
        Date date2 = null;
        Date startDate = null;
        Date endDate = null;
        try{
            startDate = formatter.parse(String.valueOf(start));
            endDate = formatter.parse(String.valueOf(end));
        }catch (ParseException e) {
            e.printStackTrace();
        }
        for(int i=0; i<size; i++){
            try {
                date1 = formatter.parse(String.valueOf(data.get(counter+2)));
                date2 = formatter.parse(String.valueOf(data.get(counter+3)));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            if(date1.compareTo(startDate) >= 0 && date2.compareTo(endDate) <= 0)
                model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                    data.get(counter+4) + " zł"});
            if(size > 1)
                counter+=attributesQuantity;
        }
    }

    private void zostaw_numerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zostaw_numerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zostaw_numerActionPerformed
    //nowe
    private void wyslij_stopkaActionPerformed(java.awt.event.ActionEvent evt) {
        number = zostaw_numer.getText();
        if(compiledPhoneNumberPattern.matcher(number).matches()){
            zostaw_numer.setText("");
            Client.operate("sendNumbers");
            //Dashboard.phoneNumbers.add(number);
        }
        else {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisany numer telefonu.", "Informacja", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_zostaw_numerActionPerformed
    private void wyjazdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wyjazdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wyjazdActionPerformed

    private void skadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_skadActionPerformed

    private void bulgariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bulgariaActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Bulgaria");
    }//GEN-LAST:event_bulgariaActionPerformed

    private void wlochyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wlochyActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Wlochy");
    }//GEN-LAST:event_wlochyActionPerformed

    private void EgiptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EgiptActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Egipt");
    }//GEN-LAST:event_EgiptActionPerformed

    private void TurcjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TurcjaActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Turcja");
    }//GEN-LAST:event_TurcjaActionPerformed

    private void hiszpaniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiszpaniaActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Hiszpania");
    }//GEN-LAST:event_hiszpaniaActionPerformed

    private void grecjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grecjaActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Grecja");

    }//GEN-LAST:event_grecjaActionPerformed
    //nowe
    private void filterTable(String country){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            if(data.get(counter).equals(country))
                model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                        data.get(counter+4) + " zł"});
            if(size > 1)
                counter+=attributesQuantity;
        }
    }
    //nowe
    private void filterTable(String country1, String country2){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            if(data.get(counter).equals(country1) || data.get(counter).equals(country2))
                model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                        data.get(counter+4) + " zł"});
            if(size > 1)
                counter+=attributesQuantity;
        }
    }
    private void egzotykaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_egzotykaActionPerformed
        // TODO add your handling code here:
        //nowe
        filterTable("Kuba", "Emiraty Arabskie");
    }//GEN-LAST:event_egzotykaActionPerformed

    private void lastminuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastminuteActionPerformed
        // TODO add your handling code here:
        //nowe
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date now = new Date();
        for(int i=0; i<size; i++){
            try {
                date1 = formatter.parse(String.valueOf(data.get(counter+2)));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            if(getDifferenceDays(now, date1) < 5)
                model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                        data.get(counter+4) + " zł"});
            if(size > 1)
                counter+=attributesQuantity;
        }
    }//GEN-LAST:event_lastminuteActionPerformed
    //nowe
    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    private void wczasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wczasyActionPerformed
        // TODO add your handling code here:
        //nowe
        populateTable();
    }//GEN-LAST:event_wczasyActionPerformed

    private void lupaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaActionPerformed
        // TODO add your handling code here:
        //nowe
        boolean isDateValid = checkDate();
        if(isDateValid) {
            howManyAdults = (int) ilosc_doroslych.getValue();
            howManyChildren = (int) ilosc_dzieci.getValue();
            if (howManyAdults == 0 && howManyChildren > 0) {
                JOptionPane.showMessageDialog(null,"Wymagany jest przynajmniej jeden dorosły.","Alert",JOptionPane.WARNING_MESSAGE);
            } else {
                String country = (String) dokad.getSelectedItem();
                String departure_city = (String) skad.getSelectedItem();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat formatterSearch = new SimpleDateFormat("dd/MM/yyyy");
                Date departure = null;
                Date arrival = null;
                Date date1 = null;
                Date date2 = null;

                int counter = 0;
                int size = (listDataLength / attributesQuantity);
                DefaultTableModel model = (DefaultTableModel) tabela.getModel();
                model.setRowCount(0);
                for (int i = 0; i < size; i++) {
                    try {
                        date1 = formatter.parse(String.valueOf(data.get(counter + 2)));
                        date2 = formatter.parse(String.valueOf(data.get(counter + 3)));
                        departure = formatterSearch.parse(wyjazd.getText());
                        arrival = formatterSearch.parse(przyjazd.getText());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (data.get(counter).equals(country) && data.get(counter + 5).equals(departure_city) && departure.compareTo(date1) == 0
                            && arrival.compareTo(date2) == 0 && (howManyChildren + howManyAdults) <= Integer.parseInt(data.get(counter + 6)))
                        model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                                data.get(counter + 4) + " zł"});
                    if (size > 1)
                        counter += attributesQuantity;
                }
            }
        }
    }//GEN-LAST:event_lupaActionPerformed

    private boolean checkDate() {
        if(!datePattern.matcher(wyjazd.getText()).matches() || !datePattern.matcher(przyjazd.getText()).matches()) {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisana data.", "Informacja", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void przyjazdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_przyjazdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_przyjazdActionPerformed

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
            java.util.logging.Logger.getLogger(WYSZUKIWARKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WYSZUKIWARKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WYSZUKIWARKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WYSZUKIWARKA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WYSZUKIWARKA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Egipt;
    private javax.swing.JScrollPane Scrool_cale_okno;
    private javax.swing.JButton Turcja;
    private javax.swing.JButton bulgaria;
    private javax.swing.JComboBox<String> dokad;
    private javax.swing.JButton egzotyka;
    private javax.swing.JPanel glowne;
    private javax.swing.JPanel glowne_okno;
    private javax.swing.JButton grecja;
    private javax.swing.JButton hiszpania;
    private javax.swing.JSpinner ilosc_doroslych;
    private javax.swing.JSpinner ilosc_dzieci;
    private javax.swing.JLabel ilosc_osob;
    private javax.swing.JColorChooser jColorChooser1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel kierunek_podrozy;
    private javax.swing.JButton lastminute;
    private javax.swing.JButton lato2023;
    private javax.swing.JButton lupa;
    private javax.swing.JLabel miejsce_wylotu;
    private javax.swing.JLabel nazwa_biura;
    private javax.swing.JPanel panel_Kraje;
    private javax.swing.JPanel panel_wyszukiwarka;
    private javax.swing.JPanel panel_wyszukiwarka_wprowadzanie;
    private javax.swing.JLabel panel_zdjecia;
    private javax.swing.JTextField przyjazd;
    private javax.swing.JScrollPane scroll_tabela;
    private javax.swing.JComboBox<String> skad;
    private javax.swing.JPanel stopka;
    private javax.swing.JTable tabela;
    private javax.swing.JButton wczasy;
    private javax.swing.JButton wlochy;
    private javax.swing.JTextField wyjazd;
    private javax.swing.JLabel wyjazd_przyjazd;
    private javax.swing.JButton wyslij_stopka;
    private javax.swing.JComboBox<String> zarzadzanie;
    private javax.swing.JTextField zostaw_numer;
    // End of variables declaration//GEN-END:variables
}
