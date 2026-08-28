import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static final String prompt = "\u001B[31msoporte\u001B[0m:\u001B[34m~\u001B[0m$ ";
    static int contadorId = 1;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        // Aqui guardamos los tickets urgentes como pila
        Deque<Ticket> pila = new ArrayDeque<>();

        // Aqui van los tickets normales como cola
        Deque<Ticket> cola = new ArrayDeque<>();

        // Esta lista guarda todos los tickets que se han creado
        List<Ticket> lista = new ArrayList<>();

        int opcion;
        do {
            limpiarPantalla();
            System.out.println("\u001B[34m" + """
                                                                                                          
 ▄▄▄▄▄▄▄                                         ▄▄         ▄▄▄▄▄▄▄▄▄                                     
███▀▀▀▀▀               ██   ▀▀                   ██         ▀▀▀███▀▀▀ ▀▀        ▄▄            ██          
███       ▄█▀█▄ ▄█▀▀▀ ▀██▀▀ ██  ▄███▄ ████▄   ▄████ ▄█▀█▄      ███    ██  ▄████ ██ ▄█▀ ▄█▀█▄ ▀██▀▀ ▄█▀▀▀  
███  ███▀ ██▄█▀ ▀███▄  ██   ██  ██ ██ ██ ██   ██ ██ ██▄█▀      ███    ██  ██    ████   ██   ▀███▄  
▀██████▀  ▀█▄▄▄ ▄▄▄█▀  ██   ██▄ ▀███▀ ██ ██   ▀████ ▀█▄▄▄      ███    ██▄ ▀████ ██ ▀█▄ ▀█▄▄▄  ██   ▄▄▄█▀  
                                                                                                                                                                                                              
""" + "\u001B[0m");
            System.out.println("═".repeat(40));
            System.out.println("1. Crear ticket");
            System.out.println("2. Gestionar tickets urgentes");
            System.out.println("3. Gestionar tickets normales");
            System.out.println("4. Registro de tickets");
            System.out.println("5. Ver todos los tickets pendientes");
            System.out.println("6. Salir");
            System.out.println("═".repeat(40));
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    crearTicket(teclado, pila, cola, lista);
                    pausar(teclado);
                    break;
                case 2:
                    menuPila(teclado, pila);
                    break;
                case 3:
                    menuCola(teclado, cola);
                    break;
                case 4:
                    menuLista(teclado, lista);
                    break;
                case 5:
                    verPendientes(pila, cola, teclado);
                    break;
                case 6:
                    System.out.println("Saliendo.");
                    break;
                default:
                    System.out.println("Opcion invalida.");
                    break;
            }
        } while (opcion != 6);

        teclado.close();
    }


    // Aqui creamos el ticket y dependiendo de la prioridad lo mandamos a pila o cola
    static void crearTicket(Scanner teclado, Deque<Ticket> pila, Deque<Ticket> cola, List<Ticket> lista) {

        System.out.print("Descripcion del ticket: ");
        String descripcion = teclado.nextLine();

        System.out.print("Departamento: ");
        String departamento = teclado.nextLine();

        String prioridad;

        // Repetimos hasta que el usuario escriba una prioridad valida
        while (true) {
            System.out.print("Prioridad (Urgente/Normal): ");
            prioridad = teclado.nextLine();

            if (prioridad.equalsIgnoreCase("Urgente")) {
                break;
            } else if (prioridad.equalsIgnoreCase("Normal")) {
                break;
            } else {
                System.out.println("Opcion incorrecta. Escribe Urgente o Normal.");
            }
        }

        // Aqui se crea el ticket con su id y todos sus datos
        Ticket t = new Ticket(contadorId++, descripcion, departamento, prioridad);

        // Si es urgente entra a la pila, si es normal entra a la cola
        if (prioridad.equalsIgnoreCase("Urgente")) {
            pila.push(t);
        } else {
            cola.offerLast(t);
        }

        // Tambien lo guardamos en la lista para poder buscarlo despues
        lista.add(t);
        System.out.println("Ticket creado: " + t);
    }



    // Menu para manejar los tickets urgentes
    static void menuPila(Scanner teclado, Deque<Ticket> pila) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("\u001B[34m" + """
                                                                                                
▄▄▄▄▄▄▄▄▄                                                                                       
▀▀▀███▀▀▀ ▀▀        ▄▄            ██                                          ██                
   ███    ██  ▄████ ██ ▄█▀ ▄█▀█▄ ▀██▀▀ ▄█▀▀▀   ██ ██ ████▄ ▄████ ▄█▀█▄ ████▄ ▀██▀▀ ▄█▀█▄ ▄█▀▀▀  
   ███    ██  ██    ████   ██▄█▀  ██   ▀███▄   ██ ██ ██ ▀▀ ██ ██ ██▄█▀ ██ ██  ██   ██▄█▀ ▀███▄  
   ███    ██▄ ▀████ ██ ▀█▄ ▀█▄▄▄  ██   ▄▄▄█▀   ▀██▀█ ██    ▀████ ▀█▄▄▄ ██ ██  ██   ▀█▄▄▄ ▄▄▄█▀  
                                                              ██                                
                                                          ▀▀▀                                 
""" + "\u001B[0m");
            System.out.println("═".repeat(40));
            System.out.println("1. Atender ultimo ticket");
            System.out.println("2. Ver ultimo ticket");
            System.out.println("3. Ver todos los tickets urgentes");
            System.out.println("4. Volver");
            System.out.println("═".repeat(40));
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    // Si no hay nada en la pila no podemos atender ningun ticket
                    if (pila.isEmpty()) {
                        System.out.println("No hay tickets urgentes pendientes.");
                    } else {
                        // Sacamos el ultimo ticket que entro a la pila
                        Ticket atendido = pila.pop();
                        atendido.estado = "Atendido";
                        System.out.println("Atendiendo: " + atendido);
                    }
                    break;
                case 2:
                    // Aqui solo vemos el siguiente sin sacarlo de la pila
                    if (pila.isEmpty()) {
                        System.out.println("No hay tickets urgentes pendientes.");
                    } else {
                        System.out.println("Proximo a atender: " + pila.peek());
                    }
                    break;
                case 3:
                    // Mostramos todos los tickets urgentes que siguen en la pila
                    if (pila.isEmpty()) {
                        System.out.println("No hay tickets urgentes pendientes.");
                    } else {
                        for (Ticket t : pila) {
                            System.out.println(t);
                        }
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


    // Menu para manejar los tickets normales
    static void menuCola(Scanner teclado, Deque<Ticket> cola) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("\u001B[34m" + """
                                              
▄▄▄▄▄▄▄▄▄                                     
▀▀▀███▀▀▀ ▀▀        ▄▄            ██          
   ███    ██  ▄████ ██ ▄█▀ ▄█▀█▄ ▀██▀▀ ▄█▀▀▀  
   ███    ██  ██    ████   ██▄█▀  ██   ▀███▄  
   ███    ██▄ ▀████ ██ ▀█▄ ▀█▄▄▄  ██   ▄▄▄█▀  
                                              
""" + "\u001B[0m");
            System.out.println("═".repeat(40));
            System.out.println("1. Atender siguiente ticket");
            System.out.println("2. Ver siguiente ticket");
            System.out.println("3. Ver todos los tickets normales");
            System.out.println("4. Volver");
            System.out.println("═".repeat(40));
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    // Si la cola esta vacia no hay nada que atender
                    if (cola.isEmpty()) {
                        System.out.println("No hay tickets normales pendientes.");
                    } else {
                        // Sacamos el ticket que lleva mas tiempo esperando
                        Ticket atendido = cola.pollFirst();
                        atendido.estado = "Atendido";
                        System.out.println("Atendiendo: " + atendido);
                    }
                    break;
                case 2:
                    if (cola.isEmpty()) {
                        System.out.println("No hay tickets normales pendientes.");
                    } else {
                        System.out.println("Siguiente a atender: " + cola.peekFirst());
                    }
                    break;
                case 3:
                    // Mostramos todos los tickets normales que siguen pendientes
                    if (cola.isEmpty()) {
                        System.out.println("No hay tickets normales pendientes.");
                    } else {
                        for (Ticket t : cola) {
                            System.out.println(t);
                        }
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


    // Aqui se manejan los tickets que estan guardados en la lista
    static void menuLista(Scanner teclado, List<Ticket> lista) {
        int opcion;
        do {
            limpiarPantalla();
            System.out.println("\u001B[34m" + """
                                                   
▄▄▄▄▄▄▄                                            
███▀▀███▄             ▀▀         ██                
███▄▄███▀ ▄█▀█▄ ▄████ ██  ▄█▀▀▀ ▀██▀▀ ████▄ ▄███▄  
███▀▀██▄  ██▄█▀ ██ ██ ██  ▀███▄  ██   ██ ▀▀ ██ ██  
███  ▀███ ▀█▄▄▄ ▀████ ██▄ ▄▄▄█▀  ██   ██    ▀███▀  
                   ██                              
                 ▀▀▀                               
""" + "\u001B[0m");
            System.out.println("═".repeat(40));
            System.out.println("1. Buscar ticket por id");
            System.out.println("2. Ver tickets por departamento");
            System.out.println("3. Eliminar registro por id");
            System.out.println("4. Ver todos los tickets registrados");
            System.out.println("5. Volver");
            System.out.println("═".repeat(40));
            System.out.print(prompt);
            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    System.out.print("ID del ticket a buscar: ");
                    int idBuscar = leerEntero(teclado);

                    // Buscamos el ticket y si existe lo myestra
                    Ticket encontradoBuscar = buscarPorId(lista, idBuscar);
                    if (encontradoBuscar != null) {
                        System.out.println("Encontrado: " + encontradoBuscar);
                    } else {
                        System.out.println("No encontrado.");
                    }
                    break;

                case 2:
                    // Mandamos la lista al metodo que busca por departamento
                    buscarPorDepartamento(lista, teclado);
                    break;

                case 3:
                    System.out.print("ID del ticket a eliminar: ");

                    int idEliminar = leerEntero(teclado);
                    Ticket encontradoEliminar = buscarPorId(lista, idEliminar);

                    if (encontradoEliminar != null) {

                        // Solo dejamos borrar tickets que ya fueron atendidos
                        if (encontradoEliminar.estado.equalsIgnoreCase("Atendido")) {
                            lista.remove(encontradoEliminar);
                            System.out.println("Ticket eliminado: " + encontradoEliminar);
                        } else {
                            System.out.println("No se puede eliminar el ticket porque todavía no está cerrado.");
                        }

                    } else {
                        System.out.println("No se encontro un ticket con ese id.");
                    }
                    break;

                case 4:
                    // Recorremos la lista y mostramos todos los tickets
                    if (lista.isEmpty()) {
                        System.out.println("No hay tickets registrados.");
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


    // Recorremos la lista hasta encontrar el id que estamos buscando
    static Ticket buscarPorId(List<Ticket> lista, int id) {
        for (Ticket t : lista) {
            if (t.id == id) {
                return t;
            }
        }

        return null;
    }


    // Recorremos todos los tickets y mostramos los que sean del departamento indicado
    static void buscarPorDepartamento(List<Ticket> lista, Scanner teclado) {
        System.out.print("Departamento a consultar: ");
        String depto = teclado.nextLine();
        boolean encontrado = false;

        for (Ticket t : lista) {
            if (t.departamento.equalsIgnoreCase(depto)) {
                System.out.println(t);
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No hay tickets para ese departamento.");
        }
    }


    // Aqui  los tickets que siguen pendientes en la pila y en la cola
    static void verPendientes(Deque<Ticket> pila, Deque<Ticket> cola, Scanner teclado) {
        limpiarPantalla();
        System.out.println("\u001B[34m" + """
                                                               
▄▄▄▄▄▄▄                  ▄▄                                    
███▀▀███▄                ██ ▀▀               ██                
███▄▄███▀ ▄█▀█▄ ████▄ ▄████ ██  ▄█▀█▄ ████▄ ▀██▀▀ ▄█▀█▄ ▄█▀▀▀  
███▀▀▀▀   ██▄█▀ ██ ██ ██ ██ ██  ██▄█▀ ██ ██  ██   ██▄█▀ ▀███▄  
███       ▀█▄▄▄ ██ ██ ▀████ ██▄ ▀█▄▄▄ ██ ██  ██   ▀█▄▄▄ ▄▄▄█▀  
 
                                                               
""" + "\u001B[0m");

        System.out.println("═".repeat(40));

        System.out.println("\nUrgentes");

        if (pila.isEmpty()) {
            System.out.println("(sin tickets)");
        }

        // Recorremos la pila y mostramos solamente los pendientes
        for (Ticket t : pila) {
            if (t.estado.equalsIgnoreCase("Pendiente")) {
                System.out.println(t);
            }
        }

        System.out.println("\nNormales");

        if (cola.isEmpty()) {
            System.out.println("(sin tickets)");
        }

        // Recorremos la cola y mostramos solamente los pendientes
        for (Ticket t : cola) {
            if (t.estado.equalsIgnoreCase("Pendiente")) {
                System.out.println(t);
            }
        }

        System.out.println("═".repeat(40));
        pausar(teclado);
    }


    // Aqui leemos numeros y si el usuario mete otra cosa le volvemos a pedir
    static int leerEntero(Scanner teclado) {
        while (true) {
            String linea = teclado.nextLine().trim();

            try {
                return Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida ingresa un numero.");
                System.out.println("");
                System.out.print(prompt);
            }
        }
    }


    // Limpiamos la consola 
    static void limpiarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Pausa el programa 
    static void pausar(Scanner teclado) {
        System.out.println("\nPresiona enter para continuar.");
        teclado.nextLine();
    }
}