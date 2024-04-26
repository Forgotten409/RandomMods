import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

public class Menu {

    public static void main(String[] args) {

        RandomPageOpenerWithMouse2 randomP = new RandomPageOpenerWithMouse2();

        JFrame frame1 = new JFrame("Random mods automatic");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setResizable(false);

        // Utworzenie panelu głównego z GridBagLayout
        JPanel panel = new JPanel(new GridBagLayout());

        panel.setBackground(Color.gray);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // Marginesy

        // Dodanie komponentów do panelu
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Wpisz wersje losowania:"), gbc);

        gbc.gridx = 1;
        JTextField version  = new JTextField(20);
        version.setText("1.20.1");
        panel.add(version, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Wybierz ilosc modow do losowania:"), gbc);

        gbc.gridx = 1;
        JTextField numbermods  = new JTextField(20);
        numbermods.setText("1");
        panel.add(numbermods, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Wybiersz pule  z kturej losujemy:"), gbc);

        gbc.gridx = 1;
        JTextField pula  = new JTextField(20);
        pula.setText("5000");
        panel.add(pula, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Mod Loaders:"), gbc);

        gbc.gridx = 1;
        JComboBox<String> typ = new JComboBox<>();
        typ.addItem("Forge");
        typ.addItem("Fabric");
        panel.add(typ, gbc);

        gbc.gridx =1;
        gbc.gridy = 4;
        JCheckBox checkboc = new JCheckBox("Zapisywanie numeruw modow?(źeby nie losowac tycha samych modów)");
        panel.add(checkboc,gbc);
        checkboc.setBackground(Color.gray);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel kreska = new JLabel("----------------------------------------------------------------------" +
                "---------------------------------------------------------" +
                "----------------------------------------------------------------");
        panel.add(kreska, gbc);




        checkboc.addActionListener(e -> {
            if (checkboc.isSelected()){
                publicVariable.checekedbox = 0;

            }else if (!checkboc.isSelected()){
                publicVariable.checekedbox = 1;
            }
        });




        // Dodanie informacji
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        panel.add(new JLabel("(Wersja --> Np: 1.12.2 , 1.20.1 , 1.16.5 itp)"), gbc);

        gbc.gridy = 7;
        panel.add(new JLabel("(Ilosc modów -- > Np: 10,32 itp. ps. Im wiecej tym wiekszy czas czekania)"), gbc);

        gbc.gridy = 8;
        JTextPane info3 = new JTextPane();
        info3.setEditable(false);
        info3.setBackground(panel.getBackground());
        info3.setContentType("text/html");
        info3.setText("<html>(Pula -- > Ilosc moduw znajdujacych sie mozesz sprawdzic np. z tej strony(na niej  wybierz wersje i Mod Loaders wtedy mozesz zobaczyc ilosc modów) <a href=\"https://www.curseforge.com/minecraft/search?page=1&pageSize=1&sortBy=relevancy&class=mc-mods\">tutaj</a>.</html>");
        info3.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(new URI(e.getURL().toString()));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        });
        panel.add(info3, gbc);

        // Dodanie przycisku
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton jButton = new JButton("Losuj");
        panel.add(jButton, gbc);

        // Dodanie przycisku
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton jButton3 = new JButton("Wazne info?");
        panel.add(jButton3, gbc);


        // Dodanie przycisku
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JButton jButton2 = new JButton("Usun Zapisane numer modów");
        panel.add(jButton2, gbc);

        // Dodanie panelu do ramki
        frame1.add(panel);
        frame1.pack();
        frame1.setLocationRelativeTo(null); // Wycentrowanie ramki na ekranie
        frame1.setVisible(true);

        Thread teste = new Thread(()->{
            try {
                while (true) {
                    if (publicVariable.buttoncheck == 1) {
                        jButton.setEnabled(true);

                    }else {
                        Thread.sleep(1000);

                    }
                }
            }catch (Exception ignored){

            }

        });

        teste.start();

        JFrame frame = new JFrame("Message Dialog Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jButton3.addActionListener(e -> {
            // Wyświetlenie okna dialogowego z informacją
            JOptionPane.showMessageDialog(frame, "Wazne informacje kture musisz wiedziec zeby program dzialal!(W pracy...)", "Informacja", JOptionPane.INFORMATION_MESSAGE);
        });


        jButton2.addActionListener(e -> {
            try {
                // Otwarcie pliku w trybie zapisu (true - append)
                FileWriter writer = new FileWriter(RandomPageOpenerWithMouse2.DRAWN_NUMBERS_FILE, false);
                // Zamknięcie pliku
                writer.close();
                System.out.println("Zawartość pliku została wyczyszczona.");
            } catch (IOException exception) {
                System.out.println("Wystąpił błąd podczas czyszczenia pliku: " + exception.getMessage());
            }



        });

        typ.addActionListener(e -> {
            // Pobranie wybranej opcji
            String selectedOption = (String) typ.getSelectedItem();
            if (Objects.equals(selectedOption, "Fabric")){
                publicVariable.typmod = 4;
            }else if (Objects.equals(selectedOption, "Forge")){
                publicVariable.typmod = 1;
            }
        });




        // Nasłuchiwanie przycisku
        jButton.addActionListener(e -> {
            publicVariable.TextVersion = version.getText(); // Pobranie wersji
            publicVariable.numbermods = Integer.parseInt(numbermods.getText());// Pobranie liczby modów
            publicVariable.pulanumbers = Integer.parseInt(pula.getText());// Pobranie puli modów

            // Dodanie nasłuchiwacza zdarzeń do listy rozwijalnej



                jButton.setEnabled(false);





            // Wywołanie metody StartProgram() z odpowiednimi danymi
            randomP.StartProgram();
        });
    }
}
