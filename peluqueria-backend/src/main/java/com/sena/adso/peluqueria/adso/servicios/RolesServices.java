package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.RolesEntity;
import com.sena.adso.peluqueria.adso.repositorios.RolesRepository;

@Service 
public class RolesServices {
    
    private final RolesRepository repository;

    public RolesServices(RolesRepository repository) {
        this.repository = repository;
    }
    
    public List<RolesEntity> getALL() {
        return repository.findAll();
    }
    
    public Optional<RolesEntity> getById(Integer id) {
        return repository.findById(id);
    }
    
    public RolesEntity save(RolesEntity entity) {
        return repository.save(entity);
    }

    public RolesEntity update(RolesEntity entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }
    public void delete(Integer id) {
        repository.deleteById(id);
    }


}
