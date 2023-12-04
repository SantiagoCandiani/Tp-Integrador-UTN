package com.utn.gestorincidentes.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Incidente")
public class Incidente {
    @Id
    @GeneratedValue(generator = "uuid")
    //genera un valor de manera automatica al momento que el repo persita la entidad.
    @GenericGenerator(name = "uuid", strategy = "uuid2") //es una estrategia alternativa
    String idIncidente;
    String descripcion;
    String tipoProblema;
    final LocalDate altaIncidente = LocalDate.now();
    LocalDateTime fechaPosibleResolucion;
}
