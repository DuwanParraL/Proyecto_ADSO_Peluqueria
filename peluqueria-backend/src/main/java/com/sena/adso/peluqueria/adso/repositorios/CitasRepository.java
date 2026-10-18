package com.sena.adso.peluqueria.adso.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.peluqueria.adso.entidades.CitasEntity;

public interface CitasRepository extends JpaRepository<CitasEntity, Integer> {
    
    @Query ("SELECT c FROM CitasEntity c WHERE c.idCliente = :idCliente")
    List<CitasEntity> findByIdCliente(@Param("idCliente") Integer idCliente);

    @Query ("SELECT c FROM CitasEntity c WHERE c.idEmpleado = :idEmpleado")
    List<CitasEntity> findByIdEmpleado(@Param("idEmpleado") Integer idEmpleado);

    List<CitasEntity> findByEstado(String estado);
}
