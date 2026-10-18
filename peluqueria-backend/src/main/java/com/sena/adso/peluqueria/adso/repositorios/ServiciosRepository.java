package com.sena.adso.peluqueria.adso.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.adso.peluqueria.adso.entidades.ServiciosEntity;


public interface ServiciosRepository extends JpaRepository<ServiciosEntity, Integer> {
    List<ServiciosEntity>findByEstadoTrue();
}
