package com.sena.adso.peluqueria.adso.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.adso.peluqueria.adso.entidades.UsuariosEntity;



public interface UsuariosRepository  extends JpaRepository<UsuariosEntity, Integer> {
    
  List<UsuariosEntity> findByNombreLike(String nombre);

  @Query( "SELECT u FROM UsuariosEntity u WHERE u.id_roles = :id_roles")
  List<UsuariosEntity> findByIdRoles(@Param("id_roles") Integer id_roles);
  Optional<UsuariosEntity> findByUser(String user);
  

}
