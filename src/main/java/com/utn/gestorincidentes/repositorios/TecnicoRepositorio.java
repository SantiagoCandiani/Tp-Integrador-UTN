package com.utn.gestorincidentes.repositorios;

import com.utn.gestorincidentes.entidades.Cliente;
import com.utn.gestorincidentes.entidades.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TecnicoRepositorio extends JpaRepository<Tecnico, String> {
    List<Tecnico> findByNombreAndApellido(String nombre, String apellido);
}
