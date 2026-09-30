package org.example.presentacion;

import org.example.bussines.Curso;
import org.example.bussines.CursoService;

import java.util.Scanner;

public class CursoUI {
    private static final CursoService service = new CursoService();

    public static void mostrarMenu(Scanner sc) {
        int opcion;

        do {
            System.out.println("\n=== GESTIÓN DE CURSOS ===");
            System.out.println("1. Registrar");
            System.out.println("2. Listar");
            System.out.println("3. Actualizar");
            System.out.println("4. Eliminar");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Opción no válida");
                sc.nextLine();
                opcion = -1;
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    registrar(sc);
                    break;
                case 2:
                    listar();
                    break;
                case 3:
                    actualizar(sc);
                    break;
                case 4:
                    eliminar(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (opcion != 0);
    }

    private static void registrar(Scanner sc) {
        int id = leerEntero(sc, "ID: ");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        int creditos = leerEntero(sc, "Créditos: ");

        service.registrar(new Curso(id, nombre, creditos));
        System.out.println("Curso registrado.");
    }

    private static void listar() {
        if (service.listar().isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }

        service.listar().forEach(curso -> System.out.println(
                curso.getId() + " - " + curso.getNombre() + " - " + curso.getCreditos() + " créditos"));
    }

    private static void actualizar(Scanner sc) {
        int id = leerEntero(sc, "ID: ");
        System.out.print("Nuevo nombre: ");
        String nombre = sc.nextLine();
        int creditos = leerEntero(sc, "Nuevos créditos: ");

        boolean actualizado = service.actualizar(new Curso(id, nombre, creditos));
        System.out.println(actualizado ? "Curso actualizado." : "Curso no encontrado.");
    }

    private static void eliminar(Scanner sc) {
        int id = leerEntero(sc, "ID: ");
        boolean eliminado = service.eliminar(id);
        System.out.println(eliminado ? "Curso eliminado." : "Curso no encontrado.");
    }

    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            }
            System.out.println("El valor debe ser un número entero.");
            sc.nextLine();
        }
    }
}
