package com.utn.gestorincidentes.servicios;

import com.utn.gestorincidentes.entidades.Tecnico;
import com.utn.gestorincidentes.repositorios.TecnicoRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class TecnicoServicio {

    private final TecnicoRepositorio tecnicoRepo;
    private final Scanner scanner;

    public TecnicoServicio(TecnicoRepositorio tecnicoRepo) {
        this.tecnicoRepo = tecnicoRepo;
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    // Método para obtener todos los tecnicos
    public void obtenerTodosLosTecnicos() {
        List<Tecnico> tecnicos = tecnicoRepo.findAll();
        for (Tecnico tecnico : tecnicos) {
            System.out.println("ID: " + tecnico.getIdTecnico());
            System.out.println("Nombre: " + tecnico.getNombre());
            System.out.println("Apellido: " + tecnico.getApellido());
            System.out.println("Especialidad: " + tecnico.getEspecialidad());
            System.out.println("Sistema Operativo: " + tecnico.getSistemaOperativo());
            System.out.println("Fecha de Alta: " + tecnico.getAltaTecnico());
            System.out.println("------------------------");
        }
    }

    // Método para obtener un tecnico por ID
    public Optional<Tecnico> obtenerTecnicoPorId(String id) {
        return tecnicoRepo.findById(id);
    }

    // Método para eliminar un tecnico
    @Transactional
    public void eliminarTecnico(String id) {

        tecnicoRepo.deleteById(id);
    }

    // Método para eliminar todos los tecnicos
    @Transactional
    public void eliminarTodosLosTecnicos() {
        tecnicoRepo.deleteAll();
    }

    // Método para ingresar un tecnico
    @Transactional
    public void ingresarTecnico() {

        System.out.println("Ingrese el nombre del tecnico:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del tecnico:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese la especialidad del tecnico:");
        String especialidad = scanner.nextLine();

        System.out.println("Ingrese el sistema operativo que usa el tecnico:");
        String sistemaOperativo = scanner.nextLine();

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre(nombre);
        tecnico.setApellido(apellido);
        tecnico.setEspecialidad(especialidad);
        tecnico.setSistemaOperativo(sistemaOperativo);

        tecnicoRepo.save(tecnico);

        System.out.println("Tecnico ingresado exitosamente.");
    }

    // Método para cargar tecnicos desde un archivo
    @Transactional
    public void ingresarTecnicosDesdeArchivo() {
        String rutaArchivo = "tecnicos.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");  // Asume que los datos están separados por comas
                Tecnico tecnico = new Tecnico();
                tecnico.setNombre(datos[0]);
                tecnico.setApellido(datos[1]);
                tecnico.setEspecialidad(datos[2]);
                tecnico.setSistemaOperativo(datos[3]);

                tecnicoRepo.save(tecnico);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para editar un tecnico por ID
    @Transactional
    public void editarTecnico(String id) {
        Optional<Tecnico> optTecnico = tecnicoRepo.findById(id);
        if (optTecnico.isPresent()) {
            Tecnico tecnico = optTecnico.get();

            System.out.println("Ingrese el nombre del tecnico:");
            String nombre = scanner.nextLine();
            tecnico.setNombre(nombre);

            System.out.println("Ingrese el apellido del tecnico:");
            String apellido = scanner.nextLine();
            tecnico.setApellido(apellido);

            System.out.println("Ingrese la especialidad del tecnico:");
            String especialidad = scanner.nextLine();
            tecnico.setEspecialidad(especialidad);

            System.out.println("Ingrese el sistema operativo que usa el tecnico:");
            String sistemaOperativo = scanner.nextLine();
            tecnico.setSistemaOperativo(sistemaOperativo);

            tecnicoRepo.save(tecnico);

            System.out.println("Tecnico editado exitosamente.");
        } else {
            throw new IllegalArgumentException("No se encontró un tecnico con el ID proporcionado");
        }
    }

    public void mostrarTecnico(Optional<Tecnico> optTecnico) {
        if (optTecnico.isPresent()) {
            System.out.println("-" + optTecnico.get());
        } else {
            System.out.println("No se encontró un tecnico con ese ID.");
        }
    }

    public List<Tecnico> buscarTecnicosPorNombreYApellido(String nombre, String apellido) {
        return tecnicoRepo.findByNombreAndApellido(nombre, apellido);
    }

    public void mostrarTecnicos(List<Tecnico> tecnicos) {
        if (tecnicos.isEmpty()) {
            System.out.println("No se encontraron tecnicos.");
        } else {
            for (Tecnico tecnico : tecnicos) {
                System.out.println("-" + tecnico);
            }
        }
    }
}
