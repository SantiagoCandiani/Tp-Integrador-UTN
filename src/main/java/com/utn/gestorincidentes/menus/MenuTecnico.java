package com.utn.gestorincidentes.menus;

import com.utn.gestorincidentes.servicios.TecnicoServicio;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuTecnico {

    private final Scanner scanner;
    private final TecnicoServicio tecnicoServ;

    public MenuTecnico(TecnicoServicio tecnicoServ) {
        this.scanner = new Scanner(System.in).useDelimiter("\n");
        this.tecnicoServ = tecnicoServ;
    }


    public void presioneTecla() {
        System.out.print("");
        System.out.print("Presione ENTER para continuar...");
        scanner.next();
    }

    public void menuTecnicos() {
        try {

            System.out.println("--------Menu Tecnicos--------");
            System.out.print("");
            System.out.println("1) Insertar Lista Precargada.");
            System.out.println("2) Ver Lista Completa.");
            System.out.println("3) Insertar un nuevo Tecnico.");
            System.out.println("4) Editar un Tecnico.");
            System.out.println("5) Eliminar un Tecnico.");
            System.out.println("6) Eliminar todos los Tecnicos.");
            System.out.println("7) Buscar un Tecnico por ID.");
            System.out.println("8) Buscar un Tecnico por su nombre completo.");
            System.out.println("9) Salir al menu principal.");

            System.out.print("");
            System.out.print("Ingrese una opcion: ");
            System.out.print("");

            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    tecnicoServ.ingresarTecnicosDesdeArchivo();
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 2:
                    tecnicoServ.obtenerTodosLosTecnicos();
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 3:
                    tecnicoServ.ingresarTecnico();
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 4:
                    System.out.println("Seleccione el ID del Tecnico a modificar de la siguiente lista:");
                    tecnicoServ.obtenerTodosLosTecnicos();
                    tecnicoServ.editarTecnico(scanner.next());
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 5:
                    System.out.println("Seleccione el ID del Tecnico a eliminar de la siguiente lista:");
                    tecnicoServ.obtenerTodosLosTecnicos();
                    tecnicoServ.eliminarTecnico(scanner.next());
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 6:
                    tecnicoServ.eliminarTodosLosTecnicos();
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 7:
                    System.out.println("Ingrese el ID del Tecnico:");
                    tecnicoServ.mostrarTecnico(tecnicoServ.obtenerTecnicoPorId(scanner.next()));
                    presioneTecla();
                    menuTecnicos();
                    break;
                case 8:
                    System.out.println("Ingrese el nombre del tecnico:");
                    String nombre = scanner.next();
                    System.out.println("Ingrese el apellido del tecnico:");
                    String apellido = scanner.next();
                    tecnicoServ.mostrarTecnicos(tecnicoServ.buscarTecnicosPorNombreYApellido(nombre, apellido));
                    presioneTecla();
                    menuTecnicos();
                case 9:
                    System.out.println("Saliendo del menu Tecnico...");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    presioneTecla();
                    menuTecnicos();
                    break;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            //System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
            presioneTecla();
            menuTecnicos();
        }
    }
}