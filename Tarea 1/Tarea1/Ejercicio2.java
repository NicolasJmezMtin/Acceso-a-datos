/*
Nicolás Jiménez Martín - DAM Online
Acceso a datos - RA1 - Tarea 1
*/

package Tarea1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // Carpeta principal
        File ejercicios = new File("C:\\AD\\Ejercicios");

        // Directorio que creamos en el ejercicio 1
        File miDirectorio = new File(ejercicios, "miDirectorio");

        // Fichero del ejercicio 1
        File fichero1 = new File(miDirectorio, "fichero_de_texto.txt");

        // Nuevo directorio
        File nuevoDirectorio = new File(ejercicios, "nuevoDirectorio");

        // Segundo fichero
        File fichero2 = new File(nuevoDirectorio, "fichero_de_texto2.txt");

        int opcion;

        do {

            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Crear nuevoDirectorio");
            System.out.println("2. Crear fichero_de_texto2.txt");
            System.out.println("3. Eliminar fichero_de_texto.txt");
            System.out.println("4. Eliminar nuevoDirectorio");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = teclado.nextInt();

            switch (opcion) {

                case 1:
                    if (nuevoDirectorio.mkdir()) {
                        System.out.println("Directorio creado.");
                    } else {
                        System.out.println("El directorio ya existe.");
                    }
                    break;


                case 2:

                    try {
                        if (fichero2.createNewFile()) {
                            System.out.println("Fichero creado.");
                        } else {
                            System.out.println("El fichero ya existe.");
                        }
                    } catch (IOException e) {

                        System.out.println("Error al crear el fichero.");
                    }
                    break;


                case 3:
                    if (fichero1.delete()) {
                        System.out.println("Fichero eliminado.");
                    } else {
                        System.out.println("No se ha podido eliminar el fichero.");
                    }
                    break;


                case 4:
                    if (nuevoDirectorio.delete()) {
                        System.out.println("Directorio eliminado.");
                    } else {
                        System.out.println(
                                "No se puede eliminar porque contiene archivos."
                        );
                    }
                    break;


                case 5:
                    System.out.println("Programa terminado.");
                    break;


                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 5);

        teclado.close();
    }
}
