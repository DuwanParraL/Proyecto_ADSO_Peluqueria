package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.UsuariosEntity;
import com.sena.adso.peluqueria.adso.repositorios.UsuariosRepository;

@Service
public class UsuariosServices  {

private final UsuariosRepository repositorio;

  UsuariosServices(UsuariosRepository repositorio) {
    this.repositorio = repositorio;
  }  

public List<UsuariosEntity> getdAll(){
    return repositorio.findAll();
}


public Optional<UsuariosEntity> getById(Integer id){
    return repositorio.findById(id);
}

public UsuariosEntity save(UsuariosEntity entity) {
    return repositorio.save(entity);
}

public UsuariosEntity update(UsuariosEntity entity) {
    Optional<UsuariosEntity> usuario = repositorio.findById(entity.getId());
    if (usuario.isPresent()) {
        return repositorio.save(entity);
    }
    return null;
}

public void delete(Integer id) {
    repositorio.deleteById(id);
}
public List<UsuariosEntity> getByNombres(String nombres){
    return repositorio.findByNombreLike('%'+ nombres +'%');
}


}

/* CRUD
CREATE--save
READ--getAll, getById
UPDATE--update
DELETE--delete
*/