package org.example.tp.repository;

import org.example.tp.dtos.ValoracionDTO;
import org.example.tp.entities.Valoracion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValoracionRepository extends JpaRepository<Valoracion, Integer> {

    // HU02: Sistema de valoración para la cita posteriormente dada
    @Query(value = "INSERT INTO Valoracion (comentario, puntuacion, cita.id) " +
                    "VALUES (:#{#valoracionDTO.comentario}, :#{#valoracionDTO.puntuacion}, :#{#valoracionDTO.citaId})", nativeQuery = true)
    void crearValoracion(@Param("valoracionDTO") ValoracionDTO valoracionDTO);
}
