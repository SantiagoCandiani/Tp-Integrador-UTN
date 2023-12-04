package com.utn.gestorincidentes;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.utn.gestorincidentes.menus.MenuPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class GestorIncidentesApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GestorIncidentesApplication.class, args);
        try {
            MenuPrincipal menuP = context.getBean(MenuPrincipal.class);
            menuP.menuPrincipal();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}