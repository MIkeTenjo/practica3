import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainTextos {
    public static void main(String[] args) {
        String archivo1 = "rosa.txt";
        String archivo2 = "elDicho.txt";

        try {
            ConjuntoArreglo<String> c1 = new ConjuntoArreglo<String>();
            ConjuntoArreglo<String> c2 = new ConjuntoArreglo<String>();
            BufferedReader br = new BufferedReader(new FileReader(archivo1));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.trim().split("\\s+");
                for (String palabra : partes) {
                    palabra = palabra.replaceAll("[\\p{Punct}…]", "");
                    if(!palabra.isEmpty()){
                        c1.agregarElemento(palabra);
                    }
                }
            }
            
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(archivo2));
            String l;
            while ((l = br2.readLine()) != null) {
                String[] p = l.trim().split("\\s+");
                for (String pal : p) {
                    pal = pal.replaceAll("[\\p{Punct}…]", "");
                    pal = pal.replace("¡", "");
                    if(!pal.isEmpty()){
                        c2.agregarElemento(pal);
                    }
                }
            }

            br2.close();

            System.out.println("Las palabras sin repetir del archivo " + archivo1 + " son: ");

            for (String string : c1) {
                System.out.println(string);
            }

            System.out.println();

            System.out.println("Las palabras sin repetir del archivo " + archivo2 + " son: ");

            for (String s : c2) {
                System.out.println(s);
            }

            System.out.println();

            Conjunto<String> c3 = c1.interseccion(c2);

            System.out.println("Las palabras que aparecen en ambos archivos son: ");

            for (String string : c3) {
                System.out.println(string );
            }


        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error creando conjuntos: " + e.getMessage());
        }
            
        }

    /*Aquí van tus métodos auxiliares en caso de necesitarlos*/
}

