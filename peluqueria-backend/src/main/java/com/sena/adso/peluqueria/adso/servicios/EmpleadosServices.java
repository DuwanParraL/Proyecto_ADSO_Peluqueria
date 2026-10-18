package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.EmpleadosEntity;
import com.sena.adso.peluqueria.adso.repositorios.EmpleadosRepository;

@Service 
public class EmpleadosServices {
    
    private final EmpleadosRepository repository;



    public EmpleadosServices(EmpleadosRepository repository) {
        this.repository = repository;
    }

    public List<EmpleadosEntity> getALL(){
        return repository.findAll();
    }

    public List<EmpleadosEntity> getActivos(){
        return repository.findByactivoTrue();
    }

    public Optional<EmpleadosEntity> getById(Integer id){
        return repository.findById(id);
    }

    public Optional<EmpleadosEntity> getByUsuarios(Integer idUsuario) {
        return repository.findByUsuario(idUsuario);
    }

    public EmpleadosEntity save(EmpleadosEntity entity){
        return repository.save(entity);
    }

    public EmpleadosEntity update(EmpleadosEntity entity){
        if(entity.getId() != null && repository.existsById(entity.getId())){
            return repository.save(entity);
        }
        return null;
    }

    public void delete(Integer id){
        repository.deleteById(id);
    }
}