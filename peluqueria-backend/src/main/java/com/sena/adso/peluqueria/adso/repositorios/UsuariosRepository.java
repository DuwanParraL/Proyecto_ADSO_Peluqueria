package com.sena.adso.peluqueria.adso.repositorios;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.adso.peluqueria.adso.entidades.UsuariosEntity;

public interface UsuariosRepository  extends JpaRepository<UsuariosEntity, Integer> {
    
  List<UsuariosEntity> findByNombreLike(String nombre);
}
