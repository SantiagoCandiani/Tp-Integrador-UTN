package com.utn.gestorincidentes.servicios;

import com.utn.gestorincidentes.entidades.Cliente;
import com.utn.gestorincidentes.repositorios.ClienteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ClienteServicio {
    private final ClienteRepositorio clienteRepo;
    private final Scanner scanner;

    public ClienteServicio(ClienteRepositorio clienteRepo) {
        this.clienteRepo = clienteRepo;
        this.scanner = new Scanner(System.in).useDelimiter("\n");
    }

    // Método para obtener todos los clientes
    public void obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepo.findAll();
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getIdCliente());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Apellido: " + cliente.getApellido());
            System.out.println("Razón Social: " + cliente.getRazonSocial());
            System.out.println("CUIT: " + cliente.getCuitCliente());
            System.out.println("Email: " + cliente.getMailCliente());
            System.out.println("Fecha de Alta: " + cliente.getAltaCliente());
            System.out.println("------------------------");
        }
    }

    // Método para obtener un cliente por ID
    public Optional<Cliente> obtenerClientePorId(String id) {

        return clienteRepo.findById(id);
    }

    // Método para eliminar un cliente
    @Transactional
    public void eliminarCliente(String id) {

        clienteRepo.deleteById(id);
    }

    // Método para eliminar todos los clientes
    @Transactional
    public void eliminarTodosLosClientes() {

        clienteRepo.deleteAll();
    }

    // Método para ingresar un cliente
    @Transactional
    public void ingresarCliente() {

        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();

        System.out.println("Ingrese la razón social del cliente:");
        String razonSocial = scanner.nextLine();

        System.out.println("Ingrese el CUIT del cliente:");
        String cuit = scanner.nextLine();

        System.out.println("Ingrese el correo electrónico del cliente:");
        String mail = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setRazonSocial(razonSocial);
        cliente.setCuitCliente(cuit);
        cliente.setMailCliente(mail);

        clienteRepo.save(cliente);

        System.out.println("Cliente ingresado exitosamente.");
    }

    // Método para cargar clientes desde un archivo
    @Transactional
    public void ingresarClientesDesdeArchivo() {
        String rutaArchivo = "clientes.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");  // Asume que los datos están separados por comas
                Cliente cliente = new Cliente();
                cliente.setNombre(datos[0]);
                cliente.setApellido(datos[1]);
                cliente.setRazonSocial(datos[2]);
                cliente.setCuitCliente(datos[3]);
                cliente.setMailCliente(datos[4]);
                clienteRepo.save(cliente);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para editar un cliente por ID
    @Transactional
    public void editarCliente(String id) {
        Optional<Cliente> optCliente = clienteRepo.findById(id);
        if (optCliente.isPresent()) {
            Cliente cliente = optCliente.get();

            System.out.println("Ingrese el nombre del cliente:");
            String nombre = scanner.nextLine();
            cliente.setNombre(nombre);

            System.out.println("Ingrese el apellido del cliente:");
            String apellido = scanner.nextLine();
            cliente.setApellido(apellido);

            System.out.println("Ingrese la razón social del cliente:");
            String razonSocial = scanner.nextLine();
            cliente.setRazonSocial(razonSocial);

            System.out.println("Ingrese el CUIT del cliente:");
            String cuit = scanner.nextLine();
            cliente.setCuitCliente(cuit);

            System.out.println("Ingrese el correo electrónico del cliente:");
            String mail = scanner.nextLine();
            cliente.setMailCliente(mail);

            clienteRepo.save(cliente);

            System.out.println("Cliente editado exitosamente.");
        } else {
            throw new IllegalArgumentException("No se encontró un cliente con el ID proporcionado");
        }
    }

    public void mostrarCliente(Optional<Cliente> optCliente) {
        if (optCliente.isPresent()) {
            System.out.println("-" + optCliente.get());
        } else {
            System.out.println("No se encontró un cliente con ese ID.");
        }
    }

    public List<Cliente> buscarClientesPorNombreYApellido(String nombre, String apellido) {
        return clienteRepo.findByNombreAndApellido(nombre, apellido);
    }

    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("No se encontraron clientes.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("-" + cliente);
            }
        }
    }
}
