import java.awt.MouseInfo;
import java.awt.Point;

public class MousePosition2 {
    public static void main(String[] args) throws InterruptedException {


        while (true){
            Thread.sleep(1000);
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            System.out.println("Aktualna pozycja myszy: x = " + mouseLocation.x + ", y = " + mouseLocation.y);
        }
        // Tutaj dodaj kod do kliknięcia myszą


    }
}
