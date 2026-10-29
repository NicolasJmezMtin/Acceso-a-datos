/*
Nicolás Jiménez Martín - DAM Online
Acceso a datos - RA1 - Tarea 1
*/

package Tarea1;

import java.io.File;
import java.io.IOException;

public class Ejercicio1 {

    public static void main(String[] args) {

        // Ruta donde queremos trabajar
        String ruta = "C:\\AD\\Ejercicios";

        // Creamos el directorio miDirectorio
        File directorio = new File(ruta, "miDirectorio");

        // mkdirs() crea también las carpetas padre si no existen
        if (directorio.mkdirs()) {
            System.out.println("Directorio creado correctamente.");
        } else if (directorio.exists()) {
            System.out.println("El directorio ya existe.");
        } else {
            System.out.println("No se ha podido crear el directorio.");
        }

        // Creamos el fichero dentro de miDirectorio
        File fichero = new File(directorio, "fichero_de_texto.txt");

        try {

            if (fichero.createNewFile()) {
                System.out.println("Fichero creado correctamente.");
            } else {
                System.out.println("El fichero ya existe.");
            }

        } catch (IOException e) {

            System.out.println("Error al crear el fichero.");
            e.printStackTrace();
        }
    }
}
