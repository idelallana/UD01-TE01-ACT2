package ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class errores {

    public static void main(String[] args) {

        // Contador de líneas ERROR
        int totalErrores = 0;

        // try-with-resources: BufferedReader y BufferedWriter se cierran solos automáticamente al terminar el try, aunque salte una excepción.
        try (
            BufferedReader lector = new BufferedReader(new FileReader("accesos.log"));
            BufferedWriter escritor = new BufferedWriter(new FileWriter("errores.log"))
        ) {

            String linea;

            // readLine() devuelve una línea completa del fichero, o null cuando ya no quedan más.
            // while: el programa funciona independientemente de las líneas que tenga el fichero.
            while ((linea = lector.readLine()) != null) {

                // Separamos la línea por el carácter ";" -> [fecha, usuario, resultado]
                String[] partes = linea.split(";");

                // Comprobamos que la línea tenga el formato esperado (3 partes)
                // y que la última parte sea exactamente "ERROR"
                if (partes.length == 3 && partes[2].trim().equalsIgnoreCase("ERROR")) {

                    escritor.write(linea);      // Escribimos la línea tal cual en errores.log
                    escritor.newLine();         // Salto de línea 
                    totalErrores++;             // Sumamos 1 al contador de errores
                }
            }

            // Al final del fichero, añadimos la línea con el total de errores encontrados
            escritor.write("Total de errores: " + totalErrores);
            escritor.newLine();

            System.out.println("Proceso completado. Se han encontrado " + totalErrores + " errores.");

        } catch (IOException e) {
            System.out.println("Error al procesar los ficheros: " + e.getMessage());
        }
    }
}

