package com.utn.gestorincidentes.menus;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuPrincipal {

    private final MenuCliente menuCli;
    private final MenuTecnico menuTec;
    //private final MenuIncidente menuInc;
    private final Scanner scanner;

    public MenuPrincipal(MenuCliente menuCli, MenuTecnico menuTec) {
        this.menuCli = menuCli;
        this.menuTec = menuTec;
        //this.menuInc = menuInc;
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    public void presioneTecla() {
        System.out.print("");
        System.out.print("Presione ENTER para continuar...");
        scanner.next();
    }

    public void menuPrincipal() {
        try {

            System.out.println("************************************************************");
            System.out.println("*                       INCIDENT REPORT                    *");
            System.out.println("************************************************************");
            System.out.println("<-------------SISTEMA DE GESTION DE INCIDENTES------------->");
            System.out.println("1) Menu de Cliente.");
            System.out.println("2) Menu de Tecnico.");
            //System.out.println("3) Menu de Incidentes.");
            System.out.println("4) Salir del programa.");

            System.out.println("");
            System.out.print("Ingrese una opcion: ");
            System.out.println("");

            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    menuCli.menuClientes();
                    presioneTecla();
                    menuPrincipal();
                    break;
                case 2:
                    menuTec.menuTecnicos();
                    presioneTecla();
                    menuPrincipal();
                    break;
//                case 3:
//                    menuInc.menuIncidentes();
//                    presioneTecla();
//                    menuPrincipal();
//                    break;
                case 4:
                    System.out.println("Usted Salio del programa con Exito.");
                    break;
                default:
                    System.out.println("Opcion incorrecta. Seleccione un numero de la lista opciones del menu");
                    break;
            }
        } catch (Exception e) {
            System.out.println("DEBE ingresar un numero de la lista, no simbolos ni letras");
        }
    }

}//class
