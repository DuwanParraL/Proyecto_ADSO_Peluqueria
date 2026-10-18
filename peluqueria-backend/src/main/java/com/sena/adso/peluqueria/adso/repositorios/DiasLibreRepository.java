package com.sena.adso.peluqueria.adso.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.peluqueria.adso.entidades.DiasLibresEntity;

public interface DiasLibreRepository extends JpaRepository<DiasLibresEntity, Integer> {
    @Query("SELECT d FROM DiasLibresEntity d WHERE d.idEmpleado = :idEmpleado")
    List<DiasLibresEntity> FindByIdEmpleado(@Param("idEmpleado") Integer idEmpleado);
    
    @Query("SELECT d FROM DiasLibresEntity d WHERE d.idEmpleado = :idEmpleado  AND d.fecha = :fecha")
    DiasLibresEntity findByEmpleadoAndFecha(@Param("idEmpleado") Integer idEmpleado, @Param("fecha") java.time.LocalDate fecha);
}
