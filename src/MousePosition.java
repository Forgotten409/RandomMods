import java.awt.MouseInfo;
import java.awt.Point;

public class MousePosition {
    public static void main(String[] args) {


                try {
                    // Poczekaj 5 sekund
                    Thread.sleep(3000);
                    System.out.println("Po 3 sekundach!");

                    // Tutaj dodaj kod do kliknięcia myszą
                    Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
                    System.out.println("Aktualna pozycja myszy: x = " + mouseLocation.x + ", y = " + mouseLocation.y);
                } catch (InterruptedException e) {
                    System.out.println("Przerwano wątek.");
                }
            }
        }




