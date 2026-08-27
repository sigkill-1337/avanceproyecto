import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String prompt = "\u001B[31mhelpdesk\u001B[0m:\u001B[34m~\u001B[0m$ ";
    static int contadorId = 1;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        Deque<Ticket> pila = new ArrayDeque<>();   // tickets urgentes
        Deque<Ticket> cola = new ArrayDeque<>();   // tickets normales (FIFO)
        List<Ticket> lista = new ArrayList<>();    // tickets por departamento

        int opcion;
        do {
            limpiarPantalla();
            System.out.println("=== Sistema de Gestion de Tickets - Help Desk ===");
            System.out.println("1. Gestionar tickets urgentes (Pila)");
            System.out.println("2. Gestionar tickets normales (Cola)");
            System.out.println("3. Gestionar tickets por departamento (Lista)");
            System.out.println("4. Ver todos los tickets pendientes");
            System.out.println("5. Salir");
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    menuPila(teclado, pila);
                    break;
                case 2:
                    menuCola(teclado, cola);
                    break;
                case 3:
                    menuLista(teclado, lista);
                    break;
                case 4:
                    verTodos(pila, cola, lista);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 5);

        teclado.close();
    }

    // ---------------- PILA (tickets urgentes) ----------------
    static void menuPila(Scanner teclado, Deque<Ticket> pila) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("--- Tickets Urgentes (Pila) ---");
            System.out.println("1. Agregar ticket urgente (push)");
            System.out.println("2. Atender ultimo ticket (pop)");
            System.out.println("3. Ver ultimo ticket (peek)");
            System.out.println("4. Volver");
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    Ticket t1 = crearTicket(teclado);
                    pila.push(t1);
                    System.out.println("Ticket agregado: " + t1);
                    break;
                case 2:
                    if (pila.isEmpty()) {
                        System.out.println("No hay tickets urgentes pendientes.");
                    } else {
                        System.out.println("Atendiendo: " + pila.pop());
                    }
                    break;
                case 3:
                    if (pila.isEmpty()) {
                        System.out.println("No hay tickets urgentes pendientes.");
                    } else {
                        System.out.println("Proximo a atender: " + pila.peek());
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
            pausar(teclado);
        } while (opcion != 4);
    }

    // ---------------- COLA (tickets normales, FIFO) ----------------
    static void menuCola(Scanner teclado, Deque<Ticket> cola) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("--- Tickets Normales (Cola) ---");
            System.out.println("1. Agregar ticket (enqueue)");
            System.out.println("2. Atender siguiente ticket (dequeue)");
            System.out.println("3. Ver siguiente ticket (front)");
            System.out.println("4. Volver");
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    Ticket t2 = crearTicket(teclado);
                    cola.offerLast(t2);
                    System.out.println("Ticket agregado: " + t2);
                    break;
                case 2:
                    if (cola.isEmpty()) {
                        System.out.println("No hay tickets normales pendientes.");
                    } else {
                        System.out.println("Atendiendo: " + cola.pollFirst());
                    }
                    break;
                case 3:
                    if (cola.isEmpty()) {
                        System.out.println("No hay tickets normales pendientes.");
                    } else {
                        System.out.println("Siguiente a atender: " + cola.peekFirst());
                    }
                    break;
                case 4:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
            pausar(teclado);
        } while (opcion != 4);
    }

    // ---------------- LISTA (tickets por departamento) ----------------
    static void menuLista(Scanner teclado, List<Ticket> lista) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("--- Tickets por Departamento (Lista) ---");
            System.out.println("1. Agregar ticket (insert)");
            System.out.println("2. Eliminar ticket por id (delete)");
            System.out.println("3. Buscar ticket por id (find)");
            System.out.println("4. Ver todos los tickets de la lista");
            System.out.println("5. Volver");
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    Ticket t3 = crearTicket(teclado);
                    lista.add(t3);
                    System.out.println("Ticket agregado: " + t3);
                    break;
                case 2:
                    System.out.print("ID del ticket a eliminar: ");
                    int idEliminar = leerEntero(teclado);
                    Ticket encontradoEliminar = buscarPorId(lista, idEliminar);
                    if (encontradoEliminar != null) {
                        lista.remove(encontradoEliminar);
                        System.out.println("Ticket eliminado: " + encontradoEliminar);
                    } else {
                        System.out.println("No se encontro un ticket con ese id.");
                    }
                    break;
                case 3:
                    System.out.print("ID del ticket a buscar: ");
                    int idBuscar = leerEntero(teclado);
                    Ticket encontradoBuscar = buscarPorId(lista, idBuscar);
                    if (encontradoBuscar != null) {
                        System.out.println("Encontrado: " + encontradoBuscar);
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;
                case 4:
                    if (lista.isEmpty()) {
                        System.out.println("No hay tickets en la lista.");
                    } else {
                        for (Ticket t : lista) {
                            System.out.println(t);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
            pausar(teclado);
        } while (opcion != 5);
    }

    static Ticket buscarPorId(List<Ticket> lista, int id) {
        for (Ticket t : lista) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    // ---------------- VER TODOS (urgencia y departamento) ----------------
    static void verTodos(Deque<Ticket> pila, Deque<Ticket> cola, List<Ticket> lista) {
        limpiarPantalla();
        System.out.println("=== TODOS LOS TICKETS PENDIENTES ===");

        System.out.println("\n-- Urgentes (Pila) --");
        if (pila.isEmpty()) {
            System.out.println("(sin tickets)");
        }
        for (Ticket t : pila) {
            System.out.println(t);
        }

        System.out.println("\n-- Normales (Cola) --");
        if (cola.isEmpty()) {
            System.out.println("(sin tickets)");
        }
        for (Ticket t : cola) {
            System.out.println(t);
        }

        System.out.println("\n-- Por departamento (Lista) --");
        if (lista.isEmpty()) {
            System.out.println("(sin tickets)");
        }
        for (Ticket t : lista) {
            System.out.println(t);
        }
    }

    // ---------------- Utilidades ----------------
    static Ticket crearTicket(Scanner teclado) {
        teclado.nextLine(); // limpiar buffer
        System.out.print("Descripcion del ticket: ");
        String descripcion = teclado.nextLine();
        System.out.print("Departamento (Redes/Software/Hardware/Cuentas): ");
        String departamento = teclado.nextLine();
        return new Ticket(contadorId++, descripcion, departamento);
    }

    static int leerEntero(Scanner teclado) {
        while (true) {
            try {
                return teclado.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada invalida, ingresa un numero.");
                teclado.next();
            }
        }
    }

    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void pausar(Scanner teclado) {
        System.out.println("\nPresiona ENTER para continuar...");
        teclado.nextLine();
        teclado.nextLine();
    }
}