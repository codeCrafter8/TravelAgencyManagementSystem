package com.client;

import com.client.utils.ColorUtils;
import com.server.LogsClients;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class containing fields and methods for handling the offer window functionality
 */
public class Offer extends javax.swing.JFrame {
    private static final Dimension HOTEL_PHOTO_DIMENSION = new Dimension(550, 225);
    private static final Dimension WINDOW_DIMENSION = new Dimension(1067, 388);
    /**
     * Attribute representing the number of people
     */
    private int peopleQuantity;
    /**
     * Attribute representing the client identifier
     */
    int userID;
    /**
     * Attribute representing the selected insurance
     */
    private String insurance;
    /**
     * Attribute representing the list of offer data
     */
    private final java.util.List<String> offerData = new ArrayList<>();
    /**
     * Attribute representing the list of data sent to the Client class
     */
    private final java.util.List<String> data = new ArrayList<>();
    /**
     * Attribute representing the quantity of data in the offer data list
     */
    private int attributeQuantity;
    /**
     * Attribute representing the quantity of adults
     */
    private int adultsQuantity;
    /**
     * Attribute representing the quantity of children
     */
    private int childrenQuantity;
    /**
     * Attribute representing the trip ID
     */
    private int idTrip;
    /**
     * Attribute representing the selected row number in the trip table in the SearchEngine class
     */
    private int selectedRow;
    /**
     * Attribute representing the Client class object
     */
    private Client client;
    /**
     * Constructor responsible for initializing GUI and relevant elements
     * @param client Parameter holding an object of the Client class
     * @param offerData Parameter representing a list containing offer data
     * @param attributeQuantity Parameter representing the quantity of data in the offer data list
     * @param adultsQuantity Parameter representing the quantity of adults
     * @param childrenQuantity Parameter representing the quantity of children
     * @param idTrip Parameter representing the trip ID
     * @param selectedRow Parameter representing the selected row number in the trip table in the SearchEngine class
     */
    public Offer(Client client, java.util.List<String> offerData, int attributeQuantity, int adultsQuantity, int childrenQuantity, int idTrip, int selectedRow) {
        this.offerData.addAll(offerData);
        this.client = client;
        this.attributeQuantity = attributeQuantity;
        this.adultsQuantity = adultsQuantity;
        this.childrenQuantity = childrenQuantity;
        this.idTrip = idTrip;
        this.selectedRow = selectedRow;
        initComponents();
        fillLabels();
    }
    /**
     * Helper constructor responsible for initializing GUI
     */
    public Offer(){initComponents();}
    /**
     * Method filling the appropriate labels with offer data
     */
    private void fillLabels() {
        int counter = 0;
        int size = (offerData.size()/ attributeQuantity);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        Date date2;
        int daysBetween = 0;
        for(int i=0; i<size; i++){
            if(Integer.parseInt(offerData.get(counter+7)) == idTrip){
                try {
                    date1 = formatter.parse(String.valueOf(offerData.get(counter+2)));
                    date2 = formatter.parse(String.valueOf(offerData.get(counter+3)));
                    daysBetween = (int) SearchEngine.getDifferenceDays(date1, date2);
                }catch (ParseException e) {
                    e.printStackTrace();
                }
                cityCountryLabel.setText(offerData.get(counter) + ", " + offerData.get(counter+1));
                dateData.setText("from " + offerData.get(counter+2) + " to " + offerData.get(counter+3));
                departureCityData.setText(offerData.get(counter+5));
                tripLengthData.setText(daysBetween + " days");
                peopleQuantity = adultsQuantity + childrenQuantity;
                insurance = "";
                peopleNumberData.setText(peopleQuantity + " (" +
                        adultsQuantity + " adults, " + childrenQuantity + " children)");
                hotelPhotoLabel.setIcon(new javax.swing.ImageIcon("img\\photo" + (selectedRow + 1) + ".jpg"));
                descriptionSpaceLabel.setText("<html>" + offerData.get(counter+8) + "<html>");
                hotelName.setText(offerData.get(counter+9));
                if(adultsQuantity == 0 && childrenQuantity == 0)
                    typePrice.setText(offerData.get(counter+4) + " zł");
                else
                    typePrice.setText(Integer.parseInt(offerData.get(counter+4)) * (adultsQuantity + childrenQuantity) + " zł");
            }
            if(size > 1)
                counter+= attributeQuantity;
        }
    }
    /**
     * Method initializing graphical components used in the window
     */
    private void initComponents() {
        setWindowProperties();
        createMainPanel();
        setLabels();
        setButtons();
        createTripDataPanel();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(WINDOW_DIMENSION);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    data.clear();
                    data.add("logOut");
                    new Client(data);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, ex, "Informacja", JOptionPane.INFORMATION_MESSAGE);
                    new LogsClients("Offer", "error", "[ " + new java.util.Date() + " ] " + "Błąd zamykania okna.");
                }
            }
        });
    }
    private void createMainPanel(){
        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(insuranceLabel)
                                        .addComponent(insurance1Label)
                                        .addComponent(insurance2Label)
                                        .addComponent(hotelDescriptionLabel)
                                        .addComponent(insurance3Label))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addGap(420, 420, 420)
                                                .addComponent(travelAgencyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton))
                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(descriptionSpaceLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(mainPanelLayout.createSequentialGroup()
                                                                .addComponent(hotelPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tripDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addGap(48, 48, 48))
        );
        mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(travelAgencyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancelButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tripDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(hotelPhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(insuranceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insurance1Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insurance2Label)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insurance3Label)
                                .addGap(18, 18, 18)
                                .addComponent(hotelDescriptionLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionSpaceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(19, Short.MAX_VALUE))
        );
    }
    private void setLabels(){
        travelAgencyLabel.setBackground(ColorUtils.LIGHT_BROWN);
        travelAgencyLabel.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 24));
        travelAgencyLabel.setForeground(ColorUtils.LIGHT_BROWN);
        travelAgencyLabel.setText("Travel Agency");

        hotelPhotoLabel.setPreferredSize(HOTEL_PHOTO_DIMENSION);

        cityCountryLabel.setToolTipText("");
        cityCountryLabel.setFont(new Font("Segoe Print", Font.PLAIN, 15));

        dateLabel.setText("Termin:");
        tripLengthLabel.setText("Długość pobytu:");
        departureCityLabel.setText("Miejsce wylotu:");
        priceLabel.setText("CENA:");
        peopleNumberLabel.setText("Liczba osób:");
        hotelName.setFont(new Font("Segoe Print", Font.PLAIN, 20));

        descriptionSpaceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descriptionSpaceLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        descriptionSpaceLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        insuranceLabel.setText("Ubezpieczenie: (ubezpieczenie obowiązuje tylko na okres podróży)");

        insurance1Label.setText("Standard (cena:150zł)");
        insurance1Label.setBorder(null);
        insurance1Label.addActionListener(this::insurance1ActionPerformed);

        insurance2Label.setText("Komfort (cena: 300zł)");
        insurance2Label.addActionListener(this::insurance2ActionPerformed);

        insurance3Label.setText("Premium (cena: 600zł)");
        insurance3Label.addActionListener(this::insurance3ActionPerformed);

        hotelDescriptionLabel.setText("Opis hotelu:");
    }
    private void setButtons(){
        reservationButton.setBackground(ColorUtils.LIGHT_BROWN);
        reservationButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        reservationButton.setForeground(Color.WHITE);
        reservationButton.setText("Rezerwuj");
        reservationButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reservationButton.addActionListener(this::reservationButtonActionPerformed);

        cancelButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 12));
        cancelButton.setForeground(ColorUtils.LIGHT_BROWN);
        cancelButton.setText("Cofnij");
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(this::cancelActionPerformed);
    }
    private void createTripDataPanel(){
        javax.swing.GroupLayout tripDataPanelLayout = new javax.swing.GroupLayout(tripDataPanel);
        tripDataPanel.setLayout(tripDataPanelLayout);
        tripDataPanelLayout.setHorizontalGroup(
                tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tripDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(tripDataPanelLayout.createSequentialGroup()
                                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                .addComponent(cityCountryLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(hotelName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                                                        .addGroup(tripDataPanelLayout.createSequentialGroup()
                                                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(tripDataPanelLayout.createSequentialGroup()
                                                                                .addGap(6, 6, 6)
                                                                                .addComponent(departureCityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(dateLabel)
                                                                                .addComponent(tripLengthLabel)
                                                                                .addComponent(peopleNumberLabel)))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(peopleNumberData, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(tripLengthData, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(departureCityData, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(dateData, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tripDataPanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(priceLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(typePrice, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(reservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        tripDataPanelLayout.setVerticalGroup(
                tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tripDataPanelLayout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(hotelName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cityCountryLabel)
                                .addGap(81, 81, 81)
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(peopleNumberLabel)
                                        .addComponent(peopleNumberData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateLabel)
                                        .addComponent(dateData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tripLengthLabel)
                                        .addComponent(tripLengthData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(departureCityLabel)
                                        .addComponent(departureCityData))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addGroup(tripDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(reservationButton, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(typePrice))
                                .addContainerGap())
        );
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1022, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Method handling the "Reserve" button click
     * @param evt The received event when the button is clicked
     */
    private void reservationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new Payment(client, idTrip, insurance, peopleQuantity).setVisible(true);
    }
    /**
     * Method handling the first insurance checkbox selection
     * @param evt The received event when the checkbox is clicked
     */
    private void insurance1ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if (insurance1Label.isSelected()) {
            insurance = insurance1Label.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if (insurance2Label.isSelected())
                insurance2Label.setSelected(false);
            if (insurance3Label.isSelected())
                insurance3Label.setSelected(false);
        }
    }
    /**
     * Method handling the second insurance checkbox selection
     * @param evt The received event when the checkbox is clicked
     */
    private void insurance2ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if (insurance2Label.isSelected()) {
            insurance = insurance2Label.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if (insurance1Label.isSelected())
                insurance1Label.setSelected(false);
            if (insurance3Label.isSelected())
                insurance3Label.setSelected(false);
        }
    }
    /**
     * Method handling the third insurance checkbox selection
     * @param evt The received event when the checkbox is clicked
     */
    private void insurance3ActionPerformed(java.awt.event.ActionEvent evt) {
        int index;
        if (insurance3Label.isSelected()) {
            insurance = insurance3Label.getText();
            index = insurance.indexOf(" ");
            insurance = insurance.substring(0, index);
            if (insurance1Label.isSelected())
                insurance1Label.setSelected(false);
            if (insurance2Label.isSelected())
                insurance2Label.setSelected(false);
        }
    }
    /**
     * Method handling the "Cancel" button click
     * @param evt The received event when the button is clicked
     */
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
        new SearchEngine(client).setVisible(true);
    }
    /**
     * Method allowing the window to run
     * @param args Arguments received when running the application
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
            java.util.logging.Logger.getLogger(Offer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new Offer().setVisible(true));
    }
    // GUI variables
    /**
     * Label displaying the trip length
     */
    private final javax.swing.JLabel tripLengthData = new JLabel();
    /**
     * Label displaying the number of people
     */
    private final javax.swing.JLabel peopleNumberData = new JLabel();
    /**
     * Label displaying the city and country of the trip
     */
    private final javax.swing.JLabel cityCountryLabel = new JLabel();
    /**
     * Label displaying the hotel description
     */
    private final javax.swing.JLabel descriptionSpaceLabel = new JLabel();
    /**
     * Label displaying the departure city
     */
    private final javax.swing.JLabel departureCityData = new JLabel();
    /**
     * Label displaying the hotel name
     */
    private final javax.swing.JLabel hotelName = new JLabel();
    /**
     * Label displaying the trip dates
     */
    private final javax.swing.JLabel dateData = new JLabel();
    /**
     * Label displaying the trip price
     */
    private final javax.swing.JLabel typePrice = new JLabel();
    /**
     * Checkbox for the first insurance option
     */
    private final javax.swing.JCheckBox insurance1Label = new JCheckBox();
    /**
     * Checkbox for the second insurance option
     */
    private final javax.swing.JCheckBox insurance2Label = new JCheckBox();
    /**
     * Checkbox for the third insurance option
     */
    private final javax.swing.JCheckBox insurance3Label = new JCheckBox();
    /**
     * Label with the hotel photo
     */
    private final javax.swing.JLabel hotelPhotoLabel = new JLabel();
    private final JPanel mainPanel = new JPanel();
    private final JLabel travelAgencyLabel = new JLabel();
    private final JPanel tripDataPanel = new JPanel();
    private final JLabel dateLabel = new JLabel();
    private final JLabel tripLengthLabel = new JLabel();
    private final JLabel departureCityLabel = new JLabel();
    private final JButton reservationButton = new JButton();
    private final JLabel priceLabel = new JLabel();
    private final JLabel peopleNumberLabel = new JLabel();
    private final JLabel insuranceLabel = new JLabel();
    private final JLabel hotelDescriptionLabel = new JLabel();
    private final JButton cancelButton = new JButton();
}