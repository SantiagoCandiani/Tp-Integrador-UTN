package com.utn.gestorincidentes.repositorios;

import com.utn.gestorincidentes.entidades.AsistenciaTecnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaTecnicaRepositorio extends JpaRepository<AsistenciaTecnica, String> {
}
