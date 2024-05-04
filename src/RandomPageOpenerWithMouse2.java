import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class RandomPageOpenerWithMouse2 {

    public static boolean running = false;

    public static final String DRAWN_NUMBERS_FILE = "drawn_numbers.txt";
    // Utworzenie zbioru do przechowywania wylosowanych liczb
    private static final Set<Integer> drawnNumbers = new HashSet<>();

    static int timework = 30;




    // Metoda do kopiowania linku do schowka
    private static String copyLinkToClipboard() {
        try {
            Robot robot = new Robot();
            // Kliknij klawisz Ctrl + C do skopiowania linku
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_C);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            // Poczekaj chwilę na skopiowanie
            Thread.sleep(1000);
            // Pobierz zawartość ze schowka
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            Transferable contents = clipboard.getContents(null);
            return (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (AWTException | InterruptedException | UnsupportedFlavorException | IOException ex) {
            ex.printStackTrace(System.out);
            return "";
        }
    }

    // Metoda do modyfikacji linku w schowku
    private static String modifyLinkInClipboard(String originalLink, String appendedText) {
        // Dodaj nowy tekst do oryginalnego linku
        return originalLink + appendedText;
    }

    private static void saveDrawnNumbers() {
        try {

            if (publicVariable.checekedbox == 0) {
                FileWriter writer = new FileWriter(DRAWN_NUMBERS_FILE);
                for (int number : drawnNumbers) {
                    writer.write(number + "\n");
                }
                writer.close();

            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void loadDrawnNumbers() {
        File file = new File(DRAWN_NUMBERS_FILE);
        if (file.exists()) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    int number = Integer.parseInt(scanner.nextLine());
                    drawnNumbers.add(number);
                }


                scanner.close();

                // Wyświetlenie wczytanych liczb na konsoli
               // System.out.println("Wczytane liczby: " + drawnNumbers);
            } catch (FileNotFoundException e) {
                e.printStackTrace(System.out);
            }
        }
    }

    public void StartProgram(){

        System.out.println(publicVariable.numbermods);

        running = true;


        // Wczytanie już wylosowanych liczb z pliku
        loadDrawnNumbers();

        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 100);

// Ustawienie pozycji na dole ekranu
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth / 2 - frame.getWidth() / 2, screenHeight - frame.getHeight());

// Ustawienie zawsze na wierzchu
        frame.setAlwaysOnTop(true);

        JLabel label = new JLabel();
        JPanel panel = new JPanel(new BorderLayout()); // Ustawienie układu BorderLayout
        panel.setBackground(Color.gray);

        label.setBackground(Color.black);
        panel.add(label, BorderLayout.CENTER); // Dodanie etykiety do panelu w centralnej części

        int totalTimeInSeconds = timework * publicVariable.numbermods;


        int[] remainingTimeInSeconds = {totalTimeInSeconds};



            Timer timer = new Timer(1000, e -> {

                if (running && remainingTimeInSeconds[0] >= 0) {
                    int minutes = remainingTimeInSeconds[0] / 60;
                    int seconds = remainingTimeInSeconds[0] % 60;
                    if (minutes > 0) {
                        label.setText("Czas pozostały do zakończenia losowania: " + minutes + " minut " + seconds + " sekund");
                    } else {
                        label.setText("Czas pozostały do zakończenia losowania: " + seconds + " sekund");
                    }
                    remainingTimeInSeconds[0]--;
                } else {

                    ((Timer) e.getSource()).stop();
                    frame.dispose();
                    System.out.println("Losowanie zakończone!");
                    if (publicVariable.checekedbox == 1){
                        drawnNumbers.clear();

                    }
                    saveDrawnNumbers();
                    publicVariable.buttoncheck = 1;

                }
            });

            timer.start();







        frame.add(panel); // Dodanie panelu do ramki
        frame.setVisible(true);





        //---------------------------------------------------------------------------//


        Thread mouseThread = new Thread(() -> {

        // Symulacja ruchu myszy i kliknięcia lewym przyciskiem myszy
        try {

            Robot robot = new Robot();

            // Losowanie i otwieranie
            if (publicVariable.numbermods > 0) {
                for (int i = 0; i < publicVariable.numbermods; i++) {
                    // Losowanie unikalnej liczby od 1 do ----
                    int randomNumber;

                    do {

                        Random random = new Random();
                        randomNumber = random.nextInt(publicVariable.pulanumbers) + 1;
                        System.out.println(randomNumber);
                    } while (drawnNumbers.contains(randomNumber));
                    drawnNumbers.add(randomNumber);


                    // Adres strony do otwarcia
                    String pageURL = "https://www.curseforge.com/minecraft/search?page=" + randomNumber
                            + "&pageSize=1&sortBy=relevancy&class=mc-mods&version=" + publicVariable.TextVersion + "&gameVersionTypeId="+publicVariable.typmod;

                    // Otwieranie strony w przeglądarce internetowej
                    Desktop.getDesktop().browse(new URI(pageURL));
                    System.out.println("Strona została otwarta: " + pageURL);

                    // Pauza na 4 sekund
                    Thread.sleep(4000);

                    // Przesunięcie myszy na określone współrzędne
                    //wejce do moda strona curseforge
                    robot.mouseMove(881, 614);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");


                    // Pauza na 4 sekund
                    Thread.sleep(4000);

                    // Przesunięcie myszy na określone współrzędne
                    //wejce do miejsca linka strony
                    robot.mouseMove(410, 50);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    // Poczekaj chwilę, aby zaznaczenie zostało wykonane
                    Thread.sleep(1000);

                    // Skopiowanie zaznaczonego linku
                    String copiedLink = copyLinkToClipboard();
                    System.out.println("Link został skopiowany: " + copiedLink);

                    // Pauza na 1 sekundę
                    Thread.sleep(1000);

                    // Modyfikacja linku w schowku
                    String modifiedLink = modifyLinkInClipboard(copiedLink, "/files/all?page=1&pageSize=20&version=1.20.1&gameVersionTypeId=1");
                    System.out.println("Zmodyfikowany link w schowku: " + modifiedLink);

                    // Pobranie systemowego schowka
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

                    // Utworzenie obiektu Transferable z treścią linku
                    Transferable transferable = new StringSelection(modifiedLink);

                    // Umieszczenie treści w schowku
                    clipboard.setContents(transferable, null);


                    // Wklejenie zmodyfikowanego linku w to samo miejsce
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    System.out.println("Wklejono zmodyfikowany link.");

                    // Wciśnięcie klawisza Enter, aby przejść do nowego adresu
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    System.out.println("Przejście do nowego adresu.");


                    // Pauza na 2 sekund
                    Thread.sleep(3000);

                    // Przesunięcie myszy na określone współrzędne
                    //wejce do moda wybranego
                    robot.mouseMove(496, 447);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    // Pauza na 1 sekund
                    Thread.sleep(2000);

                    // Przesunięcie myszy na określone współrzędne
                    //wejce do moda wybranego
                    robot.mouseMove(1154, 331);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");


                    // Poczekaj 4 sekundy
                    Thread.sleep(4000);

                    // Przesunięcie myszy na inne współrzędne
                    //wynranie profilu
                    robot.mouseMove(648, 438);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    // Poczekaj 3 sekundy
                    Thread.sleep(3000);

                    // Przesunięcie myszy na inne współrzędne
                    //klikniecie w wybranie paczki
                    robot.mouseMove(367, 252);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    // Poczekaj 1 sekundy
                    Thread.sleep(1000);

                    // Przesunięcie myszy na inne współrzędne
                    //klikniece w pobierz
                    robot.mouseMove(722, 736);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    Thread.sleep(1000);

                    // Przesunięcie myszy na inne współrzędne
                    // zminimilozawanie curse forge
                    robot.mouseMove(1436, 18);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");

                    Thread.sleep(2000);

                    // Przesunięcie myszy na inne współrzędne
                    // usuniecie karty
                    robot.mouseMove(459, 15);
                    // Kliknięcie lewym przyciskiem myszy
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    System.out.println("Kliknięcie lewym przyciskiem myszy wykonane.");


                    Thread.sleep(1000);


                }

            }} catch (IOException | URISyntaxException | AWTException | InterruptedException e) {
            e.printStackTrace(System.out);
        }
        });
        mouseThread.start();




}

    public void StartProgram(String version, int i, int i1, int selectedType) {
    }
}
