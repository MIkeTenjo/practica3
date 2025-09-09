import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTextos {
    public static void main(String[] args) {
        String archivo1 = "rosa.txt";
        String archivo2 = "elDicho.txt";

        try {
            /*Aquí va tu código*/

        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando conjuntos: " + e.getMessage());
        }
    }

    /*Aquí van tus métodos auxiliares en caso de necesitarlos*/
}

