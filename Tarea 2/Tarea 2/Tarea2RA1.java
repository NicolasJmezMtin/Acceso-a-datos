/*
Nicolás Jiménez Martín
DAM Online - Acceso a datos
RA1 - Tarea 2 - Random Access File
 */

import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

public class Tarea2RA1 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("\n===== TAREA 2 RA1 =====");
            System.out.println("1. Fichero aleatorio de empleados");
            System.out.println("2. Escritura y lectura de enteros");
            System.out.println("3. Acceso directo a slots");
            System.out.println("4. Cadenas con writeUTF/readUTF");
            System.out.println("5. Registros fijos: id y nombre");
            System.out.println("6. Actualizar edad de una persona");
            System.out.println("7. Índice por id");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                // EJERCICIO 1
                case 1: {
                    int[] ids = {1, 2, 3, 4};
                    String[] apellidos = {
                            "Garcia", "Lopez", "Martin", "Sanchez"
                    };
                    int[] departamentos = {10, 20, 10, 30};
                    double[] salarios = {
                            1800.50, 2100.00, 1950.75, 2300.00
                    };

                    try (RandomAccessFile f =
                                 new RandomAccessFile("empleados.dat", "rw")) {

                        f.setLength(0);

                        for (int i = 0; i < ids.length; i++) {
                            f.writeInt(ids[i]);

                            for (int j = 0; j < 10; j++) {
                                if (j < apellidos[i].length()) {
                                    f.writeChar(apellidos[i].charAt(j));
                                } else {
                                    f.writeChar(' ');
                                }
                            }

                            f.writeInt(departamentos[i]);
                            f.writeDouble(salarios[i]);
                        }

                        System.out.println("Tamaño del fichero: "
                                + f.length() + " bytes");
                        System.out.println("Tamaño de cada registro: 36 bytes");

                        f.seek(0);

                        while (f.getFilePointer() < f.length()) {
                            int id = f.readInt();

                            String apellido = "";
                            for (int j = 0; j < 10; j++) {
                                apellido += f.readChar();
                            }

                            int departamento = f.readInt();
                            double salario = f.readDouble();

                            System.out.println("ID: " + id
                                    + ", Apellido: " + apellido.trim()
                                    + ", Departamento: " + departamento
                                    + ", Salario: " + salario);
                        }
                    }
                    break;
                }

                // EJERCICIO 2
                case 2: {
                    try (RandomAccessFile f =
                                 new RandomAccessFile("nums.bin", "rw")) {

                        f.setLength(0);

                        for (int i = 0; i < 5; i++) {
                            System.out.print("Introduce el número "
                                    + (i + 1) + ": ");
                            int numero = sc.nextInt();
                            f.writeInt(numero);
                        }

                        System.out.println("Puntero después de escribir: "
                                + f.getFilePointer());

                        f.seek(0);

                        System.out.println("Números guardados:");
                        for (int i = 0; i < 5; i++) {
                            System.out.println(f.readInt());
                        }
                    }
                    break;
                }

                // EJERCICIO 3
                case 3: {
                    try (RandomAccessFile entrada =
                                 new RandomAccessFile("nums.bin", "r");
                         RandomAccessFile salida =
                                 new RandomAccessFile("slots.bin", "rw")) {

                        salida.setLength(0);

                        while (entrada.getFilePointer() < entrada.length()) {
                            salida.writeInt(entrada.readInt());
                        }

                        // El segundo entero está en la posición 1 * 4.
                        salida.seek(4);

                        System.out.println("Segundo valor: "
                                + salida.readInt());
                        System.out.println("Offset utilizado: 4 bytes");
                    }
                    break;
                }

                // EJERCICIO 4
                case 4: {
                    try (RandomAccessFile f =
                                 new RandomAccessFile("nombres.bin", "rw")) {

                        f.setLength(0);

                        f.writeUTF("Ana");
                        f.writeUTF("Carlos");
                        f.writeUTF("Lucia");

                        f.seek(0);

                        System.out.println(f.readUTF());
                        System.out.println(f.readUTF());
                        System.out.println(f.readUTF());
                    }
                    break;
                }

                // EJERCICIO 5
                case 5: {
                    String[] nombres = {"Ana", "Carlos", "Lucia"};
                    int[] ids = {1, 2, 3};

                    try (RandomAccessFile f =
                                 new RandomAccessFile("registros.dat", "rw")) {

                        f.setLength(0);

                        for (int i = 0; i < 3; i++) {
                            f.writeInt(ids[i]);

                            for (int j = 0; j < 10; j++) {
                                if (j < nombres[i].length()) {
                                    f.writeChar(nombres[i].charAt(j));
                                } else {
                                    f.writeChar(' ');
                                }
                            }
                        }

                        // Cada registro ocupa 24 bytes.
                        f.seek(2L * 24);

                        int id = f.readInt();
                        String nombre = "";

                        for (int j = 0; j < 10; j++) {
                            nombre += f.readChar();
                        }

                        System.out.println("Tercer registro:");
                        System.out.println("ID: " + id);
                        System.out.println("Nombre: " + nombre.trim());
                        System.out.println("Offset: 48 bytes");
                    }
                    break;
                }

                // EJERCICIO 6
                case 6: {
                    try (RandomAccessFile f =
                                 new RandomAccessFile("personas.dat", "rw")) {

                        if (f.length() == 0) {
                            int[] ids = {1, 2, 3};
                            String[] nombres = {"Ana", "Carlos", "Lucia"};
                            int[] edades = {20, 25, 30};

                            for (int i = 0; i < 3; i++) {
                                f.writeInt(ids[i]);

                                for (int j = 0; j < 10; j++) {
                                    if (j < nombres[i].length()) {
                                        f.writeChar(nombres[i].charAt(j));
                                    } else {
                                        f.writeChar(' ');
                                    }
                                }

                                f.writeInt(edades[i]);

                                // 20 bytes reservados: registro de 48 bytes.
                                f.write(new byte[20]);
                            }
                        }

                        System.out.print("Introduce el ID: ");
                        int idBuscado = sc.nextInt();

                        System.out.print("Introduce la nueva edad: ");
                        int nuevaEdad = sc.nextInt();

                        boolean encontrado = false;

                        for (long inicio = 0;
                             inicio + 48 <= f.length();
                             inicio += 48) {

                            f.seek(inicio);
                            int id = f.readInt();

                            if (id == idBuscado) {
                                // Edad: 4 bytes de ID + 20 de nombre.
                                f.seek(inicio + 24);
                                f.writeInt(nuevaEdad);

                                f.seek(inicio);

                                int idLeido = f.readInt();
                                String nombre = "";

                                for (int j = 0; j < 10; j++) {
                                    nombre += f.readChar();
                                }

                                int edad = f.readInt();

                                System.out.println("Registro actualizado:");
                                System.out.println("ID: " + idLeido);
                                System.out.println("Nombre: " + nombre.trim());
                                System.out.println("Edad: " + edad);

                                encontrado = true;
                                break;
                            }
                        }

                        if (!encontrado) {
                            System.out.println("ID no encontrado.");
                        }
                    }
                    break;
                }

                // EJERCICIO 7
                case 7: {
                    try (RandomAccessFile personas =
                                 new RandomAccessFile("personas.dat", "rw")) {

                        if (personas.length() == 0) {
                            int[] ids = {1, 2, 3};
                            String[] nombres = {"Ana", "Carlos", "Lucia"};
                            int[] edades = {20, 25, 30};

                            for (int i = 0; i < 3; i++) {
                                personas.writeInt(ids[i]);

                                for (int j = 0; j < 10; j++) {
                                    if (j < nombres[i].length()) {
                                        personas.writeChar(nombres[i].charAt(j));
                                    } else {
                                        personas.writeChar(' ');
                                    }
                                }

                                personas.writeInt(edades[i]);
                                personas.write(new byte[20]);
                            }
                        }

                        try (RandomAccessFile indice =
                                     new RandomAccessFile("personas.idx", "rw")) {

                            indice.setLength(0);

                            for (long offset = 0;
                                 offset + 48 <= personas.length();
                                 offset += 48) {

                                personas.seek(offset);
                                int id = personas.readInt();

                                indice.writeInt(id);
                                indice.writeLong(offset);
                            }
                        }
                    }

                    System.out.print("Introduce el ID que quieres buscar: ");
                    int idBuscado = sc.nextInt();

                    boolean encontrado = false;

                    try (RandomAccessFile indice =
                                 new RandomAccessFile("personas.idx", "r");
                         RandomAccessFile personas =
                                 new RandomAccessFile("personas.dat", "r")) {

                        while (indice.getFilePointer() < indice.length()) {
                            int id = indice.readInt();
                            long offset = indice.readLong();

                            if (id == idBuscado) {
                                personas.seek(offset);

                                int idLeido = personas.readInt();
                                String nombre = "";

                                for (int j = 0; j < 10; j++) {
                                    nombre += personas.readChar();
                                }

                                int edad = personas.readInt();

                                System.out.println("Persona encontrada:");
                                System.out.println("ID: " + idLeido);
                                System.out.println("Nombre: " + nombre.trim());
                                System.out.println("Edad: " + edad);
                                System.out.println("Offset utilizado: "
                                        + offset);

                                encontrado = true;
                                break;
                            }
                        }
                    }

                    if (!encontrado) {
                        System.out.println("ID no encontrado.");
                    }
                    break;
                }

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 0);

        sc.close();
    }
}

/*
Diferencia entre datos de tamaño fijo y variable:
Los datos de tamaño fijo ocupan siempre los mismos bytes, como un `int` (4 bytes).
Esto permite acceder directamente a un registro usando `seek()`.
Los datos de tamaño variable**, como las cadenas escritas con `writeUTF()`,
ocupan distintos bytes según su contenido,
por lo que es más difícil calcular su posición directamente.
En conclusión, el tamaño fijo facilita el acceso directo,
mientras que el variable permite almacenar datos de diferentes longitudes.
 */