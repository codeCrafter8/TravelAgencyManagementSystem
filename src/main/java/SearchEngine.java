import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchEngine extends javax.swing.JFrame {
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
    public static int adultsQuantity;
    public static int childrenQuantity;
    public static Map<Integer, Integer> idRows = new TreeMap<>();
    public static int idTripToShow = 0;
    private final Validation validation;
    private javax.swing.JComboBox<String> destinationChoice;
    private javax.swing.JSpinner adultsQuantitySpinner;
    private javax.swing.JSpinner childrenQuantitySpinner;
    private javax.swing.JLabel imagesPanel;
    private javax.swing.JTextField arrivalTextField;
    private javax.swing.JComboBox<String> departureCityChoice;
    private javax.swing.JTable table;
    private javax.swing.JTextField departureTextField;
    private javax.swing.JComboBox<String> managing;
    private javax.swing.JTextField leaveNumber;
    public SearchEngine() {
        validation = new Validation();
        initComponents();
        childrenQuantity = 0;
        adultsQuantity = 1;
        leaveNumber.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (leaveNumber.getText().equals("Zostaw nr tel. - oddzwonimy do ciebie")) {
                leaveNumber.setText("");
                leaveNumber.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (leaveNumber.getText().isEmpty()) {
                leaveNumber.setForeground(Color.GRAY);
                leaveNumber.setText("Zostaw nr tel. - oddzwonimy do ciebie");
            }
        }
        });
        departureTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (departureTextField.getText().equals("04/07/2023")) {
                departureTextField.setText("");
                departureTextField.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (departureTextField.getText().isEmpty()) {
                departureTextField.setForeground(Color.GRAY);
                departureTextField.setText("04/07/2023");
            }
        }
        });
        arrivalTextField.addFocusListener(new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
            if (arrivalTextField.getText().equals("11/07/2023")) {
                arrivalTextField.setText("");
                arrivalTextField.setForeground(Color.BLACK);
            }
        }
        @Override
        public void focusLost(FocusEvent e) {
            if (arrivalTextField.getText().isEmpty()) {
                arrivalTextField.setForeground(Color.GRAY);
                arrivalTextField.setText("11/07/2023");
            }
        }
        });
        showPhotos();
        generate_data();
        destination.clear();
        departure.clear();
        getDestination();
        getDeparture();
        populateTable();
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
    }

    private void getDestination() {
        Client.operate("getDestination");
        destinationChoice.removeAllItems();
        for(String s : destination)
            destinationChoice.addItem(s);
    }
    private void getDeparture() {
        Client.operate("getDeparture");
        departureCityChoice.removeAllItems();
        for(String s : departure)
            departureCityChoice.addItem(s);
    }

    private void generate_data() {
        Client.operate("tripsListPopulation");
    }

    private void populateTable(){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for(int i=0; i<size; i++){
            model.addRow(new Object[]{data.get(counter), data.get(counter+1), (data.get(counter+2) + " - " + data.get(counter+3)),
                    data.get(counter+4) + " zł"});
            idRows.put(i, Integer.parseInt(data.get(counter+7)));
            if(size > 1)
                counter+=attributesQuantity;
        }
    }

    private void showPhotos()
    {
        imagesPanel.setIcon(new javax.swing.ImageIcon("src/img/photo1.jpg"));
        Timer time = new Timer(3000, e -> {
            imagesPanel.setIcon(new ImageIcon("src/img/photo" + counter + ".jpg"));
            if (counter == 5) counter = 0;
            counter++;
        });
        time.start();
    }
    private void initComponents() {
        JScrollPane mainScroll = new JScrollPane();
        JPanel mainWindow = new JPanel();
        JPanel mainPanel = new JPanel();
        JPanel countriesPanel = new JPanel();
        JButton tripsButton = new JButton();
        JButton summer2023Button = new JButton();
        JButton lastMinuteButton = new JButton();
        JButton exoticButton = new JButton();
        JButton greeceButton = new JButton();
        JButton spainButton = new JButton();
        JButton turkeyButton = new JButton();
        JButton egyptButton = new JButton();
        JButton italyButton = new JButton();
        JButton bulgariaButton = new JButton();
        JLabel officeName = new JLabel();
        JPanel searchPanel = new JPanel();
        JLabel tripDirection = new JLabel();
        JLabel departueCity = new JLabel();
        JLabel departureArrival = new JLabel();
        JLabel peopleQuantity = new JLabel();
        JPanel searchPanelIntroduction = new JPanel();
        destinationChoice = new javax.swing.JComboBox<>();
        departureCityChoice = new javax.swing.JComboBox<>();
        departureTextField = new javax.swing.JTextField();
        arrivalTextField = new javax.swing.JTextField();
        adultsQuantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        childrenQuantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, 4, 1));
        JButton searchButton = new JButton();
        imagesPanel = new javax.swing.JLabel();
        JScrollPane tableScroll = new JScrollPane();
        table = new javax.swing.JTable();
        managing = new javax.swing.JComboBox<>();
        JPanel footer = new JPanel();
        leaveNumber = new javax.swing.JTextField();
        JButton sendButton = new JButton();
        JLabel headphonesImage = new JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        mainScroll.setBorder(null);
        mainScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScroll.setMaximumSize(new java.awt.Dimension(1022, 400));
        mainScroll.setMinimumSize(new java.awt.Dimension(1022, 400));
        mainScroll.setPreferredSize(new java.awt.Dimension(1022, 729));

        mainWindow.setMaximumSize(new java.awt.Dimension(1022, 729));
        mainWindow.setMinimumSize(new java.awt.Dimension(1022, 729));
        mainWindow.setPreferredSize(new java.awt.Dimension(1022, 720));

        mainPanel.setPreferredSize(new java.awt.Dimension(955, 2000));

        tripsButton.setBackground(new java.awt.Color(151, 123, 92));
        tripsButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        tripsButton.setForeground(new java.awt.Color(255, 255, 255));
        tripsButton.setText("Wczasy");
        tripsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tripsButton.setFocusPainted(false);
        tripsButton.setFocusable(false);
        tripsButton.addActionListener(this::wczasyActionPerformed);

        summer2023Button.setBackground(new java.awt.Color(151, 123, 92));
        summer2023Button.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        summer2023Button.setForeground(new java.awt.Color(255, 255, 255));
        summer2023Button.setText("Lato 2023");
        summer2023Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        summer2023Button.setFocusable(false);
        summer2023Button.addActionListener(this::lato2023ActionPerformed);

        lastMinuteButton.setBackground(new java.awt.Color(151, 123, 92));
        lastMinuteButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        lastMinuteButton.setForeground(new java.awt.Color(255, 255, 255));
        lastMinuteButton.setText("Last Minute");
        lastMinuteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastMinuteButton.setFocusable(false);
        lastMinuteButton.addActionListener(this::lastminuteActionPerformed);

        exoticButton.setBackground(new java.awt.Color(151, 123, 92));
        exoticButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        exoticButton.setForeground(new java.awt.Color(255, 255, 255));
        exoticButton.setText("Egzotyka");
        exoticButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exoticButton.setFocusable(false);
        exoticButton.addActionListener(this::egzotykaActionPerformed);

        greeceButton.setBackground(new java.awt.Color(151, 123, 92));
        greeceButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        greeceButton.setForeground(new java.awt.Color(255, 255, 255));
        greeceButton.setText("Grecja");
        greeceButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        greeceButton.setFocusable(false);
        greeceButton.addActionListener(this::grecjaActionPerformed);

        spainButton.setBackground(new java.awt.Color(151, 123, 92));
        spainButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        spainButton.setForeground(new java.awt.Color(255, 255, 255));
        spainButton.setText("Hiszpania");
        spainButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        spainButton.setFocusable(false);
        spainButton.addActionListener(this::hiszpaniaActionPerformed);

        turkeyButton.setBackground(new java.awt.Color(151, 123, 92));
        turkeyButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        turkeyButton.setForeground(new java.awt.Color(255, 255, 255));
        turkeyButton.setText("Turcja");
        turkeyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        turkeyButton.setFocusable(false);
        turkeyButton.addActionListener(this::TurcjaActionPerformed);

        egyptButton.setBackground(new java.awt.Color(151, 123, 92));
        egyptButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        egyptButton.setForeground(new java.awt.Color(255, 255, 255));
        egyptButton.setText("Egipt");
        egyptButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        egyptButton.setFocusable(false);
        egyptButton.addActionListener(this::EgiptActionPerformed);

        italyButton.setBackground(new java.awt.Color(151, 123, 92));
        italyButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        italyButton.setForeground(new java.awt.Color(255, 255, 255));
        italyButton.setText("Włochy");
        italyButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        italyButton.setFocusable(false);
        italyButton.addActionListener(this::wlochyActionPerformed);

        bulgariaButton.setBackground(new java.awt.Color(151, 123, 92));
        bulgariaButton.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 14));
        bulgariaButton.setForeground(new java.awt.Color(255, 255, 255));
        bulgariaButton.setText("Bułgaria");
        bulgariaButton.setFocusable(false);
        bulgariaButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bulgariaButton.addActionListener(this::bulgariaActionPerformed);

        javax.swing.GroupLayout panel_KrajeLayout = new javax.swing.GroupLayout(countriesPanel);
        countriesPanel.setLayout(panel_KrajeLayout);
        panel_KrajeLayout.setHorizontalGroup(
            panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KrajeLayout.createSequentialGroup()
                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(summer2023Button, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lastMinuteButton)
                .addGap(0, 0, 0)
                .addComponent(exoticButton)
                .addGap(0, 0, 0)
                .addComponent(greeceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(spainButton)
                .addGap(0, 0, 0)
                .addComponent(turkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(egyptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(italyButton)
                .addGap(0, 0, 0)
                .addComponent(bulgariaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panel_KrajeLayout.setVerticalGroup(
            panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_KrajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(tripsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(summer2023Button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lastMinuteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(exoticButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(greeceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(spainButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(turkeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(egyptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(italyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(bulgariaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        officeName.setBackground(new java.awt.Color(151, 123, 92));
        officeName.setFont(new java.awt.Font("Segoe Print", Font.BOLD, 24));
        officeName.setForeground(new java.awt.Color(151, 123, 92));
        officeName.setText("Travel Agency");

        searchPanel.setBackground(new java.awt.Color(151, 123, 92));

        tripDirection.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        tripDirection.setForeground(new java.awt.Color(255, 255, 255));
        tripDirection.setText("Kierunek podróży");

        departueCity.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        departueCity.setForeground(new java.awt.Color(255, 255, 255));
        departueCity.setText("Miejsce wylotu");

        departureArrival.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        departureArrival.setForeground(new java.awt.Color(255, 255, 255));
        departureArrival.setText("Wyjazd/Przyjazd");

        peopleQuantity.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        peopleQuantity.setForeground(new java.awt.Color(255, 255, 255));
        peopleQuantity.setText("Ilość dorosłych/dzieci");

        searchPanelIntroduction.setBackground(new java.awt.Color(151, 123, 92));

        destinationChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dokąd?"}));
        destinationChoice.setFocusable(false);

        departureCityChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Skąd?"}));
        departureCityChoice.setFocusable(false);

        departureTextField.setForeground(new java.awt.Color(153, 153, 153));
        departureTextField.setText("04/07/2023");
        departureTextField.setToolTipText("");
        departureTextField.setAutoscrolls(false);
        departureTextField.setSelectionColor(new java.awt.Color(255, 255, 255));

        arrivalTextField.setForeground(new java.awt.Color(153, 153, 153));
        arrivalTextField.setText("11/07/2023");
        arrivalTextField.setAutoscrolls(false);
        adultsQuantitySpinner.setFocusable(false);

        childrenQuantitySpinner.setFocusable(false);

        javax.swing.GroupLayout panel_wyszukiwarka_wprowadzanieLayout = new javax.swing.GroupLayout(searchPanelIntroduction);
        searchPanelIntroduction.setLayout(panel_wyszukiwarka_wprowadzanieLayout);
        panel_wyszukiwarka_wprowadzanieLayout.setHorizontalGroup(
            panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destinationChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departureCityChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                        .addComponent(departureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(arrivalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                        .addComponent(adultsQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(childrenQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panel_wyszukiwarka_wprowadzanieLayout.setVerticalGroup(
            panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createSequentialGroup()
                .addComponent(destinationChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(departureCityChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arrivalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(departureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(panel_wyszukiwarka_wprowadzanieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adultsQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(childrenQuantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        searchButton.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("img/search-icon-png-0.png"))));
        searchButton.setContentAreaFilled(false);
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.addActionListener(this::lupaActionPerformed);

        javax.swing.GroupLayout panel_wyszukiwarkaLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(panel_wyszukiwarkaLayout);
        panel_wyszukiwarkaLayout.setHorizontalGroup(
            panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tripDirection)
                    .addComponent(departureArrival)
                    .addComponent(departueCity)
                    .addComponent(peopleQuantity))
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchPanelIntroduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addGap(29, 29, 29))))
        );
        panel_wyszukiwarkaLayout.setVerticalGroup(
            panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panel_wyszukiwarkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addComponent(searchPanelIntroduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton))
                    .addGroup(panel_wyszukiwarkaLayout.createSequentialGroup()
                        .addComponent(tripDirection, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(departueCity, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(departureArrival)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(peopleQuantity)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableScroll.setBorder(null);
        tableScroll.setToolTipText("");
        tableScroll.setColumnHeaderView(null);

        table.setAutoCreateRowSorter(true);
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kraj", "Miasto", "Termin", "Cena/Osoba"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFocusable(false);
        table.setGridColor(new java.awt.Color(255, 255, 255));
        table.setPreferredSize(new java.awt.Dimension(300, 355));
        table.setSelectionBackground(new java.awt.Color(151, 123, 92));
        table.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(true);
        table.getTableHeader().setResizingAllowed(false);
        tableScroll.setViewportView(table);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectedRow = table.rowAtPoint(evt.getPoint());
                if (selectedRow >= 0) {
                    idTripToShow = idRows.get(selectedRow);
                    dispose();
                    new Offer().setVisible(true);
                }
            }
        });

        managing.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Strona Główna", "Moje Konto", "Wyloguj" }));
        managing.setBorder(null);
        managing.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        managing.setFocusable(false);
        managing.setLightWeightPopupEnabled(false);
        managing.addActionListener(this::zarzadzanieActionPerformed);

        javax.swing.GroupLayout glowneLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(glowneLayout);
        glowneLayout.setHorizontalGroup(
            glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(countriesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(glowneLayout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(imagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, glowneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(officeName, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(255, 255, 255)
                .addComponent(managing, 0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(tableScroll)
        );
        glowneLayout.setVerticalGroup(
            glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(officeName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(managing, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(countriesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(glowneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout glowne_oknoLayout = new javax.swing.GroupLayout(mainWindow);
        mainWindow.setLayout(glowne_oknoLayout);
        glowne_oknoLayout.setHorizontalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(glowne_oknoLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 938, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        glowne_oknoLayout.setVerticalGroup(
            glowne_oknoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
        );

        mainScroll.setViewportView(mainWindow);

        footer.setBackground(new java.awt.Color(151, 123, 92));

        leaveNumber.setForeground(new java.awt.Color(153, 153, 153));
        leaveNumber.setText("Zostaw nr tel. - oddzwonimy do ciebie");
        leaveNumber.setMinimumSize(new java.awt.Dimension(64, 27));
        leaveNumber.setPreferredSize(new java.awt.Dimension(64, 22));

        sendButton.setForeground(new java.awt.Color(151, 123, 92));
        sendButton.setText("Wyślij");
        sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sendButton.setFocusable(false);
        sendButton.addActionListener(this::wyslij_stopkaActionPerformed);

        headphonesImage.setIcon(new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("img/slucahwki.png"))));

        javax.swing.GroupLayout stopkaLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(stopkaLayout);
        stopkaLayout.setHorizontalGroup(
            stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stopkaLayout.createSequentialGroup()
                .addGap(349, 349, 349)
                .addComponent(headphonesImage, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leaveNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        stopkaLayout.setVerticalGroup(
            stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, stopkaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(leaveNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(stopkaLayout.createSequentialGroup()
                        .addGroup(stopkaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendButton)
                            .addComponent(headphonesImage))
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, 1022, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainScroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    private void zarzadzanieActionPerformed(ActionEvent evt) {
        if(Objects.equals(managing.getSelectedItem(), "Wyloguj")) {
            Object[] options = {"Tak", "Nie"};
            if(JOptionPane.showOptionDialog(null,"Czy na pewno chcesz się wylogować?","Potwierdzenie",
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, null)== JOptionPane.YES_OPTION){
                dispose();
                Client.operate("logOut");
                StartPage.clientLogged = false;
                new StartPage().setVisible(true);
            }
        }
        else if(Objects.equals(managing.getSelectedItem(), "Moje Konto")){
            dispose();
            new MyAccount().setVisible(true);
        }
    }

    private void lato2023ActionPerformed(ActionEvent evt) {
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String start = "2023-07-01";
        String end = "2023-08-31";
        Date date1 = null;
        Date date2 = null;
        Date startDate = null;
        Date endDate = null;
        int rowCounter = 0;
        try{
            startDate = formatter.parse(start);
            endDate = formatter.parse(end);
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
            assert date1 != null;
            if(date1.compareTo(startDate) >= 0) {
                assert date2 != null;
                if (date2.compareTo(endDate) <= 0) {
                    model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                            data.get(counter + 4) + " zł"});
                    idRows.put(rowCounter, Integer.parseInt(data.get(counter + 7)));
                    rowCounter++;
                }
            }
            if(size > 1)
                counter+=attributesQuantity;
        }
    }
    private void wyslij_stopkaActionPerformed(java.awt.event.ActionEvent evt) {
        number = leaveNumber.getText();
        if(validation.phoneNumberIsValid(number)){
            leaveNumber.setText("");
            Client.operate("sendNumbers");
        }
        else {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisany numer telefonu.", "Informacja", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bulgariaActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Bulgaria");
    }

    private void wlochyActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Wlochy");
    }

    private void EgiptActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Egipt");
    }

    private void TurcjaActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Turcja");
    }

    private void hiszpaniaActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Hiszpania");
    }

    private void grecjaActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Grecja");

    }

    private void filterTable(String country){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            if(data.get(counter).equals(country)) {
                model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                        data.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(data.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=attributesQuantity;
        }
    }

    private void filterTable(String country1, String country2){
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            if(data.get(counter).equals(country1) || data.get(counter).equals(country2)) {
                model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                        data.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(data.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=attributesQuantity;
        }
    }
    private void egzotykaActionPerformed(java.awt.event.ActionEvent evt) {
        filterTable("Kuba", "Emiraty Arabskie");
    }

    private void lastminuteActionPerformed(java.awt.event.ActionEvent evt) {
        int counter = 0;
        int size = (listDataLength/attributesQuantity);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date now = new Date();
        idRows.clear();
        int rowCounter = 0;
        for(int i=0; i<size; i++){
            try {
                date1 = formatter.parse(String.valueOf(data.get(counter+2)));
            }catch (ParseException e) {
                e.printStackTrace();
            }
            assert date1 != null;
            if(getDifferenceDays(now, date1) < 5) {
                model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                        data.get(counter + 4) + " zł"});
                idRows.put(rowCounter, Integer.parseInt(data.get(counter + 7)));
                rowCounter++;
            }
            if(size > 1)
                counter+=attributesQuantity;
        }
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
    private void wczasyActionPerformed(java.awt.event.ActionEvent evt) {
        populateTable();
    }

    private void lupaActionPerformed(java.awt.event.ActionEvent evt) {
        boolean isDateValid = checkDate();
        if(isDateValid) {
            adultsQuantity = (int) adultsQuantitySpinner.getValue();
            childrenQuantity = (int) childrenQuantitySpinner.getValue();
            String country = (String) destinationChoice.getSelectedItem();
            String departure_city = (String) departureCityChoice.getSelectedItem();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatterSearch = new SimpleDateFormat("dd/MM/yyyy");
            Date departure = null;
            Date arrival = null;
            Date date1 = null;
            Date date2 = null;

            int counter = 0;
            int size = (listDataLength / attributesQuantity);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            idRows.clear();
            int rowCounter = 0;
            for (int i = 0; i < size; i++) {
                try {
                    date1 = formatter.parse(String.valueOf(data.get(counter + 2)));
                    date2 = formatter.parse(String.valueOf(data.get(counter + 3)));
                    departure = formatterSearch.parse(departureTextField.getText());
                    arrival = formatterSearch.parse(this.arrivalTextField.getText());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (data.get(counter).equals(country) && data.get(counter + 5).equals(departure_city)) {
                    assert departure != null;
                    if (departure.compareTo(date1) == 0) {
                        assert arrival != null;
                        if (arrival.compareTo(date2) == 0 && (childrenQuantity + adultsQuantity) <= Integer.parseInt(data.get(counter + 6))) {
                            model.addRow(new Object[]{data.get(counter), data.get(counter + 1), (data.get(counter + 2) + " - " + data.get(counter + 3)),
                                    data.get(counter + 4) + " zł"});
                            idRows.put(rowCounter, Integer.parseInt(data.get(counter + 7)));
                            rowCounter++;
                        }
                    }
                }
                if (size > 1)
                    counter += attributesQuantity;
            }
        }
    }

    private boolean checkDate() {
        if(!validation.dateIsValid(departureTextField.getText()) || !validation.dateIsValid(arrivalTextField.getText())) {
            JOptionPane.showMessageDialog(null, "Niepoprawnie wpisana data.", "Informacja", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new SearchEngine().setVisible(true));
    }
}