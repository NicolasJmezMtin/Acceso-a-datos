/*
Nicolás Jiménez Martín - DAM Online
Acceso a datos - RA1 - Tarea 1
*/

package Tarea1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        File ejercicios = new File("C:\\AD\\Ejercicios");
        File miDirectorio = new File(ejercicios, "miDirectorio");
        File fichero1 = new File(miDirectorio, "fichero_de_texto.txt");
        File nuevoDirectorio = new File(ejercicios, "nuevoDirectorio");
        File fichero2 = new File(nuevoDirectorio, "fichero_de_texto2.txt");

        int opcion;

        do {
            System.out.println("\n===== MENÚ =====");
            System.out.println("1. Crear nuevoDirectorio");
            System.out.println("2. Crear fichero_de_texto2.txt");
            System.out.println("3. Eliminar fichero_de_texto.txt");
            System.out.println("4. Eliminar nuevoDirectorio");
            System.out.println("5. Escribir provincias de Andalucía");
            System.out.println("6. Salir");
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
                    if (fichero1.exists()) {
                        if (fichero1.delete()) {
                            System.out.println("fichero_de_texto.txt eliminado.");
                        } else {
                            System.out.println("No se ha podido eliminar.");
                        }
                    } else {
                        System.out.println("El fichero no existe.");
                        System.out.println(fichero1.getAbsolutePath());
                    }
                    break;

                case 4:
                    if (fichero2.exists()) {
                        fichero2.delete();
                    }

                    if (nuevoDirectorio.delete()) {
                        System.out.println("nuevoDirectorio eliminado.");
                    } else {
                        System.out.println("No se ha podido eliminar el directorio.");
                    }
                    break;

                case 5:
                    if (!nuevoDirectorio.exists()) {
                        System.out.println("Primero crea nuevoDirectorio.");
                    } else if (!fichero2.exists()) {
                        System.out.println("Primero crea fichero_de_texto2.txt.");
                    } else {
                        try {
                            FileWriter escritor = new FileWriter(fichero2);
                            BufferedWriter buffer = new BufferedWriter(escritor);

                            buffer.write("Almería");
                            buffer.newLine();

                            buffer.write("Cádiz");
                            buffer.newLine();

                            buffer.write("Córdoba");
                            buffer.newLine();

                            buffer.write("Granada");
                            buffer.newLine();

                            buffer.write("Huelva");
                            buffer.newLine();

                            buffer.write("Jaén");
                            buffer.newLine();

                            buffer.write("Málaga");
                            buffer.newLine();

                            buffer.write("Sevilla");
                            buffer.newLine();

                            buffer.close();

                            System.out.println("Provincias escritas correctamente.");

                        } catch (IOException e) {
                            System.out.println("Error al escribir en el fichero.");
                        }
                    }
                    break;

                case 6:
                    System.out.println("Programa terminado.");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }
        } while (opcion != 6);

        teclado.close();
    }
}
