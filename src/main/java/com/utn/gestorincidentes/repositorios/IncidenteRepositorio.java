package com.utn.gestorincidentes.repositorios;

import com.utn.gestorincidentes.entidades.Incidente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidenteRepositorio extends JpaRepository<Incidente, String> {
}
