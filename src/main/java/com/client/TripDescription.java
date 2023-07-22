package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Klasa zawierająca pola i metody służące do obsługi okna zawierającego funkcjonalność wyświetlenia szczegółów rezerwacji
 */
public class TripDescription extends javax.swing.JFrame {
    private static final Color BLUE = new Color(102, 102, 255);
    /**
     * Atrybut przechowujący ubezpieczenie wycieczki
     */
    private String insurance;
    /**
     * Atrybut będący listą z danymi wycieczki
     */
    private List<String> resData = new ArrayList<>();
    /**
     * Atrybut przechowujący numerem zaznaczonego wiersza w tabeli z rezerwacjami w klasie MyAccount
     */
    private int selectedRow;
    /**
     * Pomocniczy konstruktor odpowiadający za inicjalizację GUI
     */
    public TripDescription(){initComponents();}
    /**
     * Konstruktor odpowiadający za inicjalizację GUI oraz odpowiednich elementów
     * @param resData parametr będący listą przechowującą dane wycieczki
     * @param selectedRow parametr będący numerem zaznaczonego wiersza w tabeli z rezerwacjami w klasie MyAccount
     */
    public TripDescription(List<String> resData, int selectedRow) {
        this.resData = resData;
        this.selectedRow = selectedRow;
        initComponents();
        fillLabels();
    }
    /**
     * Metoda wypełniająca odpowiednie etykiety danymi wycieczki
     */
    private void fillLabels() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        int counter = 0;
        int size = (resData.size())/11;
        for(int i=0; i<size; i++){
            if(i == selectedRow) {
                try {
                    date1 = formatter.parse(String.valueOf(resData.get(counter+5)));
                    date2 = formatter.parse(String.valueOf(resData.get(counter+6)));
                    daysBetween = (int) SearchEngine.getDifferenceDays(date1, date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                typeCountryLabel.setText(resData.get(counter+1));
                typeCityLabel.setText(resData.get(counter+2));
                typeDepartureCityLabel.setText(resData.get(counter+8));
                typeDateLabel.setText("od " + resData.get(counter+5) + " do " + resData.get(counter+6));
                typeDaysLabel.setText(String.valueOf(daysBetween));
                typePeopleLabel.setText(resData.get(counter+4));
                typePriceLabel.setText(resData.get(counter+3) + " zł");
                insurance = resData.get(counter+7);
                typeInsuranceLabel.setText(insurance);
                typeHotelNameLabel.setText(resData.get(counter+9));
                typeHotelDescriptionLabel.setText("<html>" + resData.get(counter+10)+ "<html>");
            }
            if(size > 1)
                counter+=11;
        }
    }
    /**
     * Metoda inicjalizująca komponenty graficzne wykorzystywane w oknie
     */
    private void initComponents() {
        setWindowProperties();
        setLabels();
        setButton();
        createLayout();
    }
    private void setLabels(){
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
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Szczegóły wycieczki");
    }
    private void setButton(){
        questionMarkButton.setBackground(BLUE);
        questionMarkButton.setIcon(new javax.swing.ImageIcon("img\\questionMark.png"));
        questionMarkButton.setBorderPainted(false);
        questionMarkButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        questionMarkButton.setMargin(new java.awt.Insets(0, 14, 0, 14));
        questionMarkButton.setPreferredSize(new java.awt.Dimension(22, 24));
        questionMarkButton.addActionListener(this::jButtonActionPerformed);
    }
    private void createLayout(){
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
                                                .addComponent(questionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(questionMarkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    }
    /**
     * Metoda obsługująca kliknięcie przycisku pytajnika
     * @param evt Przyjęty event podczas kliknięcia przycisku
     */
    private void jButtonActionPerformed(ActionEvent evt) {
        if(insurance.equals("Standard"))
            JOptionPane.showMessageDialog(null, """
                    Ubezpieczenie Standard (cena: 150 zł).

                    Obejmuje ubezpieczenia:
                    kosztów leczenia i transportu do 20 000 EUR,
                    następstw nieszczęśliwych wypadków,
                    """, "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Komfort"))
            JOptionPane.showMessageDialog(null, """
                    Ubezpieczenie Komfort (cena: 300 zł).

                    Obejmuje:
                    koszty leczenia i transportu do 50 000 EUR, w tym koszty powstałe na skutek zachorowania na COVID-19,
                    dodatkowe świadczenie na wypadek kwarantanny w wysokości 1 000 EUR,
                    koszty następstw nieszczęśliwych wypadków,
                    koszty przerwania podróży (do 5 000 PLN),
                    ubezpieczenie bagażu do 2 000 PLN,
                    opóźnienie dostarczenia bagażu do 1 000 PLN,
                    koszty ratownictwa i poszukiwań (do 5 000 EUR).""", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Premium"))
            JOptionPane.showMessageDialog(null, """
                    Ubezpieczenie Premium (cena: 600 zł).

                    Obejmuje:
                    koszty leczenia i transportu do 100 000 EUR, w tym koszty powstałe na skutek zachorowania na COVID-19 i następstw chorób przewlekłych,
                    dodatkowe świadczenia na wypadek kwarantanny w wysokości 1 000 EUR,
                    koszty następstw nieszczęśliwych wypadków,
                    koszty przerwania podróży (do 5 000 PLN),
                    ubezpieczenie bagażu do 3 000 PLN,
                    opóźnienie dostarczenia bagażu do 1 000 PLN,
                    koszty ratownictwa i poszukiwań (do 5 000 EUR).
                    """, "Informacja", JOptionPane.INFORMATION_MESSAGE);
        if(insurance.equals("Brak"))
            JOptionPane.showMessageDialog(null, "Ubezpieczenie nie zostało wykupione.", "Informacja", JOptionPane.INFORMATION_MESSAGE);
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
            java.util.logging.Logger.getLogger(TripDescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new TripDescription().setVisible(true));
    }
    //GUI variables
    private final JLabel countryLabel = new JLabel();
    private final JLabel cityLabel = new JLabel();
    private final JLabel departureCityLabel = new JLabel();
    private final JLabel dateLabel = new JLabel();
    private final JLabel daysLabel = new JLabel();
    private final JLabel peopleLabel = new JLabel();
    private final JLabel priceLabel = new JLabel();
    private final JLabel insuranceLabel = new JLabel();
    private final JLabel hotelNameLabel = new JLabel();
    private final JLabel hotelDescriptionLabel = new JLabel();
    private final JButton questionMarkButton = new JButton();
    /**
     * Etykieta z miastem wycieczki
     */
    private final javax.swing.JLabel typeCityLabel = new JLabel();
    /**
     * Etykieta z krajem wycieczki
     */
    private final javax.swing.JLabel typeCountryLabel = new JLabel();
    /**
     * Etykieta z datą wycieczki
     */
    private final javax.swing.JLabel typeDateLabel = new JLabel();
    /**
     * Etykieta z ilością dni wycieczki
     */
    private final javax.swing.JLabel typeDaysLabel = new JLabel();
    /**
     * Etykieta z miastem wylotu/przylotu wycieczki
     */
    private final javax.swing.JLabel typeDepartureCityLabel = new JLabel();
    /**
     * Etykieta z opisem hotelu wycieczki
     */
    private final javax.swing.JLabel typeHotelDescriptionLabel = new JLabel();
    /**
     * Etykieta z nazwą hotelu wycieczki
     */
    private final javax.swing.JLabel typeHotelNameLabel = new JLabel();
    /**
     * Etykieta z ubezpieczeniem wycieczki
     */
    private final javax.swing.JLabel typeInsuranceLabel = new JLabel();
    /**
     * Etykieta z ilością osób wycieczki
     */
    private final javax.swing.JLabel typePeopleLabel = new JLabel();
    /**
     * Etykieta z ceną wycieczki
     */
    private final javax.swing.JLabel typePriceLabel = new JLabel();
}