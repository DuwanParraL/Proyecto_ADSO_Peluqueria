package com.sena.adso.peluqueria.adso.repositorios;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.peluqueria.adso.entidades.CitasEntity;

public interface CitasRepository extends JpaRepository<CitasEntity, Integer> {
    
    @Query ("SELECT c FROM CitasEntity c WHERE c.idCliente = :idCliente ORDER BY c.fecha DESC, c.hora DESC")
    List<CitasEntity> findByIdCliente(@Param("idCliente") Integer idCliente);

    @Query ("SELECT c FROM CitasEntity c WHERE c.idEmpleado = :idEmpleado ORDER BY c.fecha DESC, c.hora DESC")
    List<CitasEntity> findByIdEmpleado(@Param("idEmpleado") Integer idEmpleado);

    @Query ("SELECT c FROM CitasEntity c WHERE c.idEmpleado = :idEmpleado AND c.fecha = :fecha ORDER BY c.hora DESC")
    List<CitasEntity> findByEmpleadoAndFecha(@Param("idEmpleado") Integer idEmpleado, @Param("fecha") LocalDate fecha);

    @Query ("SELECT c FROM CitasEntity c WHERE c.idEmpleado = :idEmpleado AND c.fecha = :fecha AND c.hora = :hora AND c.estado <> 'CANCELADA'")
    boolean existeTurnoOcupado(@Param("idEmpleado") Integer idEmpleado, @Param("fecha") LocalDate fecha, @Param("hora") LocalTime hora);

   @Query("SELECT COUNT(c) FROM CitasEntity c WHERE c.id_empleado = :idEmpleado AND c.estado = 'PENDIENTE'")
    long contarPendientesPorEmpleado(@Param("idEmpleado") Integer idEmpleado);

    @Query("SELECT COUNT(c) FROM CitasEntity c WHERE c.estado = 'PENDIENTE'")
    long contarTodosLosPendientes();

    List<CitasEntity> findByEstado(String estado);

}
