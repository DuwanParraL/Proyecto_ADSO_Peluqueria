package com.sena.adso.peluqueria.adso.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.peluqueria.adso.entidades.EmpleadosEntity;

public interface EmpleadosRepository extends JpaRepository<EmpleadosEntity, Integer> {
    List<EmpleadosEntity> findByActivoTrue();

    @Query ("SELECT e FROM EmpleadosEntity e WHERE e.idUsuario = :idUsuario")
    Optional<EmpleadosEntity> findByUsuario(@Param("idUsuario") Integer idUsuario);
}
