/*
Nicolás Jiménez Martín - DAM Online
Acceso a datos - RA1 - Tarea 1
*/

package Tarea1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Ejercicio4 {
    public static void main(String[] args) {
        File fichero = new File("C:\\AD\\Ejercicios\\Empleados.txt");

        try {
            FileWriter escritor = new FileWriter(fichero);
            BufferedWriter buffer = new BufferedWriter(escritor);

            buffer.write("1 - Juan");
            buffer.newLine();

            buffer.write("2 - María");
            buffer.newLine();

            buffer.write("3 - Pedro");
            buffer.newLine();

            buffer.write("4 - Ana");
            buffer.newLine();

            buffer.write("5 - Carlos");
            buffer.newLine();

            buffer.write("6 - Laura");
            buffer.newLine();

            buffer.write("7 - David");
            buffer.newLine();

            buffer.write("8 - Elena");
            buffer.newLine();

            buffer.write("9 - Pablo");
            buffer.newLine();

            buffer.write("10 - Sara");
            buffer.newLine();

            buffer.close();

            System.out.println("Empleados.txt creado correctamente.");

        } catch (IOException e) {
            System.out.println("Error al crear el fichero.");
        }

        try {
            FileReader lector = new FileReader(fichero);
            BufferedReader buffer = new BufferedReader(lector);

            String linea;

            System.out.println("\nEmpleados:");

            while ((linea = buffer.readLine()) != null) {
                System.out.println(linea);
            }

            buffer.close();

        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
}