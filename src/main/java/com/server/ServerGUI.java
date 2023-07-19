package com.server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa zawierająca pola i metody obsługujące konstrukcję GUI i stronę techniczną serwera
 */
public class ServerGUI extends javax.swing.JFrame {
    /**
     * Atrybut będący listą wszystkich wątków serwera
     */
    private final List<Thread> threads = new ArrayList<>();
    /**
     * Atrybut określający ilość zalogowanych użytkowników
     */
    public int connectedUsersNumber;
    /**
     * Atrybut określający czy serwer jest uruchomiony
     */
    private boolean running = true;
    /**
     * Atrybut będący gniazdem, z którego podłącza się klient
     */
    public Socket socket;
    /**
     * Atrybut będący gniazdem serwera
     */
    public ServerSocket serverSocket;
    /**
     * Konstruktor odpowiadający za inicjalizację GUI aplikacji serwera
     */
    public ServerGUI() {
        initComponents();
    }
    /**
     * Metoda zapisująca do pliku liczbę zalogowanych użytkowników
     */
    public void saveConnectedUsersNumber(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("connectedUsersNumber.dat"))) {
            writer.write(connectedUsersNumberLabel.getText());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metoda ustawiająca zmienną, która określa czy serwer jest uruchomiony
     * @param running określa, czy serwer jest uruchomiony
     */
    public void setRunning(boolean running){
        this.running = running;
    }
    /**
     * Metoda inicjalizująca komponenty graficzne aplikacji serwera
     */
    private void initComponents() {
        setWindowProperties();
        createMenuPanel();
        createServerPanel();
        setLabels();
        createOptionsPanel();
        setButtons();
        createConnectedUsersPanel();
        createLayout();
    }
    private void setWindowProperties(){
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikacja Serwera");
        setPreferredSize(new java.awt.Dimension(1024, 768));
        getContentPane().setBackground(new Color(215, 198, 151));
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    new Database("logOutEveryone", new ArrayList<>()).connectWithDatabase();
                    connectedUsersNumberLabel.setText("0");
                    saveConnectedUsersNumber();
                    running = false;
                    for (Thread thread : threads) {
                        thread.interrupt();
                    }
                    dispose();
                } catch (Exception ex) {
                    new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
                    dispose();
                }
            }
        });
    }
    private void createMenuPanel(){
        menuPanel.setBackground(new java.awt.Color(118, 98, 75));
        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(serverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
                menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(menuPanelLayout.createSequentialGroup()
                                .addComponent(serverPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void createServerPanel(){
        serverPanel.setBackground(new java.awt.Color(118, 98, 75));
        javax.swing.GroupLayout serverPanelLayout = new javax.swing.GroupLayout(serverPanel);
        serverPanel.setLayout(serverPanelLayout);
        serverPanelLayout.setHorizontalGroup(
                serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(serverLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, serverPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(serverIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74))
        );
        serverPanelLayout.setVerticalGroup(
                serverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(serverPanelLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(serverIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serverLabel)
                                .addContainerGap(31, Short.MAX_VALUE))
        );
    }
    private void setLabels(){
        serverIconLabel.setIcon(new javax.swing.ImageIcon("img\\serverICON.png"));
        serverIconLabel.setMaximumSize(new java.awt.Dimension(70, 70));
        serverIconLabel.setMinimumSize(new java.awt.Dimension(70, 70));
        serverIconLabel.setPreferredSize(new java.awt.Dimension(70, 70));

        serverLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        serverLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverLabel.setText("Serwer");

        connectedUsersNumberLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 42));
        connectedUsersNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersNumberLabel.setPreferredSize(new java.awt.Dimension(178, 57));
        try (BufferedReader reader = new BufferedReader(new FileReader("connectedUsersNumber.dat"))) {
            connectedUsersNumber = Integer.parseInt(reader.readLine());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        connectedUsersNumberLabel.setText(String.valueOf(connectedUsersNumber));

        connectedUsersLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        connectedUsersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connectedUsersLabel.setText("Połączonych Użytkowników");
    }
    private void createOptionsPanel(){
        optionsPanel.setBackground(new java.awt.Color(151, 123, 92));
        optionsPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        optionsPanel.setMinimumSize(new java.awt.Dimension(180, 200));
        optionsPanel.setPreferredSize(new java.awt.Dimension(180, 230));
        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
                optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(manageServerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                        .addComponent(clientsLogsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(adminsLogsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(serverLogsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        optionsPanelLayout.setVerticalGroup(
                optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(optionsPanelLayout.createSequentialGroup()
                                .addComponent(manageServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(clientsLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(adminsLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(serverLogsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void setButtons(){
        manageServerButton.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 16));
        manageServerButton.setText("   Zarządzaj pracą serwera");
        manageServerButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        manageServerButton.setContentAreaFilled(false);
        manageServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        manageServerButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Color hoverColor = new Color(190, 190, 192);
        manageServerButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                manageServerButton.setForeground(hoverColor);
                manageServerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                manageServerButton.setForeground(null);
        });

        clientsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        clientsLogsButton.setText("Logi klientów");
        clientsLogsButton.setActionCommand("Logi klientów");
        clientsLogsButton.setContentAreaFilled(false);
        clientsLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clientsLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientsLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        clientsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                clientsLogsButton.setForeground(hoverColor);
                clientsLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                clientsLogsButton.setForeground(null);
        });
        clientsLogsButton.addActionListener(this::clientsLogsButtonActionPerformed);

        adminsLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        adminsLogsButton.setText("Logi administratorów");
        adminsLogsButton.setContentAreaFilled(false);
        adminsLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adminsLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        adminsLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        adminsLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                adminsLogsButton.setForeground(hoverColor);
                adminsLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                adminsLogsButton.setForeground(null);
        });
        adminsLogsButton.addActionListener(this::adminsLogsButtonActionPerformed);

        serverLogsButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 16));
        serverLogsButton.setText("Logi serwera");
        serverLogsButton.setContentAreaFilled(false);
        serverLogsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        serverLogsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        serverLogsButton.setPreferredSize(new java.awt.Dimension(75, 46));
        serverLogsButton.getModel().addChangeListener(e -> {
            ButtonModel model = (ButtonModel) e.getSource();
            if(model.isRollover()) {
                serverLogsButton.setForeground(hoverColor);
                serverLogsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            else
                serverLogsButton.setForeground(null);
        });
        serverLogsButton.addActionListener(this::serverLogsButtonActionPerformed);

        startServerButton.setBackground(new java.awt.Color(189, 165, 111));
        startServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        startServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        startServerButton.addActionListener(this::startServerButtonActionPerformed);

        restartServerButton.setBackground(new java.awt.Color(189, 165, 111));
        restartServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        restartServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        restartServerButton.addActionListener(this::restartServerButtonActionPerformed);

        stopServerButton.setBackground(new java.awt.Color(189, 165, 111));
        stopServerButton.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 20));
        stopServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        stopServerButton.addActionListener(this::stopServerButtonActionPerformed);

        startServerButton.setText("<html><center>Uruchom<br><center>Serwer</html>");
        restartServerButton.setText("<html><center>Zrestartuj<br><center>Serwer</html>");
        stopServerButton.setText("<html><center>Zatrzymaj<br><center>Serwer</html>");
    }
    private void createConnectedUsersPanel(){
        connectedUsersPanel.setBackground(new java.awt.Color(175, 157, 121));
        connectedUsersPanel.setPreferredSize(new java.awt.Dimension(280, 130));
        javax.swing.GroupLayout connectedUsersPanelLayout = new javax.swing.GroupLayout(connectedUsersPanel);
        connectedUsersPanel.setLayout(connectedUsersPanelLayout);
        connectedUsersPanelLayout.setHorizontalGroup(
                connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(connectedUsersNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(connectedUsersLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );
        connectedUsersPanelLayout.setVerticalGroup(
                connectedUsersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(connectedUsersPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(connectedUsersNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(connectedUsersLabel)
                                .addContainerGap(20, Short.MAX_VALUE))
        );
    }
    private void createLayout(){
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(248, 248, 248)
                                                .addComponent(connectedUsersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(80, 80, 80)
                                                .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(85, 85, 85)
                                                .addComponent(restartServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                                                .addComponent(stopServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(80, 80, 80))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(stopServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(connectedUsersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(99, 99, 99)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(startServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(restartServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(324, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Logi serwera"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void serverLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new ServerLogs().setVisible(true);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Logi administratorów"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void adminsLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new AdminsLogs().setVisible(true);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Logi klientów"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void clientsLogsButtonActionPerformed(ActionEvent evt) {
        saveConnectedUsersNumber();
        dispose();
        new ClientsLogs().setVisible(true);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Zrestartuj serwer"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void restartServerButtonActionPerformed(ActionEvent evt) {
        if(running){
            setRunning(false);
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został zrestartowany.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer nie jest uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Zatrzymaj serwer"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void stopServerButtonActionPerformed(ActionEvent evt) {
        if(running) {
            setRunning(false);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został zatrzymany.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer nie jest uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Metoda obsługująca sytuację wciśnięcia przycisku "Uruchom serwer"
     * @param evt Bufor pobierający event utworzony podczas kliknięcia przycisku
     */
    private void startServerButtonActionPerformed(ActionEvent evt) {
        if(!running) {
            setRunning(true);
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został uruchomiony.");
        }
        else
            JOptionPane.showMessageDialog(null, "Serwer jest już uruchomiony.", "Informacja", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Metoda, której zadaniem jest akceptowanie połączeń nowych klientów do serwera
     */
    private void operate(){
        try {
            serverSocket = new ServerSocket(1522);
            serverSocket.setReuseAddress(true);
            while (running) {
                socket = serverSocket.accept();
                SwingWorker<Void, Void> worker = new GUIHandler();
                worker.execute();
            }
        }
        catch(IOException ex){
            System.out.println("Ex: " + ex);
            new LogsServer("ServerGUI", "fatal", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
    }
    /**
     * Metoda pozwalająca na uruchomienie okna aplikacji serwera
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
            new LogsServer("ServerGUI", "error", "[ " + new java.util.Date() + " ] " + ex.getMessage());
        }
        new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Serwer został uruchomiony.");
        ServerGUI serverGUI = new ServerGUI();
        java.awt.EventQueue.invokeLater(() -> serverGUI.setVisible(true));
        serverGUI.operate();
    }
    /**
     * Klasa pozwalająca na działanie wątków niezależnie od GUI serwera
     */
    class GUIHandler extends SwingWorker<Void, Void> implements Serializable{
        /**
         * Przesłonięcie metody konwertującej dane na typ łańcucha znaków
         */
        @Override
        public String toString(){
            return super.toString();
        }
        /**
         * Klasa implementująca interfejs Runnable tak, aby można było tworzyć nowe wątki na serwerze
         */
        private class ClientThread implements Runnable {
            /**
             * Atrybut będący listą zawierającą dane przekazywane z serwera do bazy danych
             */
            private final List<String> data = new ArrayList<>();
            /**
             * Metoda wysyłająca dane do klienta
             */
            private void sendData() {
                try {
                    OutputStream outputStream = socket.getOutputStream();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                    Database database = new Database(data.get(0), data);
                    objectOutputStream.writeObject(database.getReturningData());
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    outputStream.write(byteArray);
                    outputStream.flush();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            /**
             * Przesłonięcie metody run, obsługującej uruchomiony wątek
             */
            @Override
            public void run() {
                try {
                    InputStream inputStream = socket.getInputStream();
                    byte[] bytes = new byte[2048];
                    Object object = new ObjectInputStream(new ByteArrayInputStream(bytes, 0, inputStream.read(bytes))).readObject();
                    data.clear();
                    data.addAll((List<String>) object);
                    sendData();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        /**
         * Przesłonięcie metody doInBackground, dzięki czemu wątki mogą działać w tle nie wpływając na GUI serwera
         * @return Nie zwraca nic
         */
        @Override
        protected Void doInBackground() {
            Thread thread = new Thread(new ClientThread());
            threads.add(thread);
            thread.start();
            new LogsServer("ServerGUI", "info", "[ " + new java.util.Date() + " ] " + "Nowy wątek o nazwie " + thread.getName() + " został utworzony.");
            return null;
        }
    }
    //GUI variables
    /**
     * Przycisk umożliwiający przejście do zakładki Logi administratorów
     */
    private final javax.swing.JButton adminsLogsButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do zakładki Logi klientów
     */
    private final javax.swing.JButton clientsLogsButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do zakładki Zarządzaj pracą serwera
     */
    private final javax.swing.JButton manageServerButton = new JButton();
    /**
     * Przycisk umożliwiający zrestartowanie serwera
     */
    private final javax.swing.JButton restartServerButton = new JButton();
    /**
     * Przycisk umożliwiający przejście do zakładki Logi serwera
     */
    private final javax.swing.JButton serverLogsButton = new JButton();
    /**
     * Przycisk umożliwiający uruchomienie serwera
     */
    private final javax.swing.JButton startServerButton = new JButton();
    /**
     * Przycisk umożliwiający zatrzymanie serwera
     */
    private final javax.swing.JButton stopServerButton = new JButton();
    /**
     * Etykieta z liczbą połączonych użytkowników
     */
    private final javax.swing.JLabel connectedUsersNumberLabel = new JLabel();
    private final JPanel menuPanel = new JPanel();
    private final JPanel serverPanel = new JPanel();
    private final JLabel serverIconLabel = new JLabel();
    private final JLabel serverLabel = new JLabel();
    private final JPanel optionsPanel = new JPanel();
    private final JPanel connectedUsersPanel = new JPanel();
    private final JLabel connectedUsersLabel = new JLabel();
}