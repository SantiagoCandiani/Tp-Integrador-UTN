package com.utn.gestorincidentes.menus;

import com.utn.gestorincidentes.servicios.ClienteServicio;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuCliente {

    private final Scanner scanner;
    private final ClienteServicio clienteServ;

    public MenuCliente(ClienteServicio clienteServ) {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.clienteServ = clienteServ;
    }


    public void presioneTecla() {
        System.out.print("");
        System.out.print("Presione ENTER para continuar...");
        scanner.next();
    }

    public void menuClientes() {
        try {

            System.out.println("--------Menu Clientes--------");
            System.out.print("");
            System.out.println("1) Insertar Lista Precargada.");
            System.out.println("2) Ver Lista Completa.");
            System.out.println("3) Insertar un nuevo Cliente.");
            System.out.println("4) Editar un Cliente.");
            System.out.println("5) Eliminar un Cliente.");
            System.out.println("6) Eliminar todos los Clientes.");
            System.out.println("7) Buscar un Cliente por ID.");
            System.out.println("8) Buscar un Cliente por su nombre completo.");
            System.out.println("9) Salir al menu principal.");

            System.out.print("");
            System.out.print("Ingrese una opcion: ");
            System.out.print("");

            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    clienteServ.ingresarClientesDesdeArchivo();
                    presioneTecla();
                    menuClientes();
                    break;
                case 2:
                clienteServ.obtenerTodosLosClientes();
                presioneTecla();
                menuClientes();
                break;
                case 3:
                    clienteServ.ingresarCliente();
                    presioneTecla();
                    menuClientes();
                    break;
                case 4:
                    System.out.println("Seleccione el ID del Cliente a modificar de la siguiente lista:");
                    clienteServ.obtenerTodosLosClientes();
                    clienteServ.editarCliente(scanner.next());
                    presioneTecla();
                    menuClientes();
                    break;
                case 5:
                    System.out.println("Seleccione el ID del Cliente a eliminar de la siguiente lista:");
                    clienteServ.obtenerTodosLosClientes();
                    clienteServ.eliminarCliente(scanner.next());
                    presioneTecla();
                    menuClientes();
                    break;
                case 6:
                    clienteServ.eliminarTodosLosClientes();
                    presioneTecla();
                    menuClientes();
                    break;
                case 7:
                    System.out.println("Ingrese el ID del Cliente:");
                    clienteServ.mostrarCliente(clienteServ.obtenerClientePorId(scanner.next()));
                    presioneTecla();
                    menuClientes();
                    break;
                case 8:
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre = scanner.next();
                    System.out.println("Ingrese el apellido del cliente:");
                    String apellido = scanner.next();
                    clienteServ.mostrarClientes(clienteServ.buscarClientesPorNombreYApellido(nombre, apellido));
                    presioneTecla();
                    menuClientes();
                case 9:
                    System.out.println("Saliendo del menu Cliente...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    presioneTecla();
                    menuClientes();
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
            presioneTecla();
            menuClientes();
        }
    }//menuClientes
}//Class
