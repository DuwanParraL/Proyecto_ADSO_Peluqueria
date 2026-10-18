package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.ServiciosEntity;
import com.sena.adso.peluqueria.adso.repositorios.ServiciosRepository;

@Service 
public class ServiciosServices {
    
    private final ServiciosRepository repository;

    public ServiciosServices(ServiciosRepository repository) {
        this.repository = repository;
    }

    public List<ServiciosEntity> getALL() {
        return repository.findAll();
    }
    public Optional<ServiciosEntity> getById(Integer id) {
    return repository.findById(id);
    }
    public List<ServiciosEntity> getActive() {
        return repository.findByEstadoTrue();
    }

    public ServiciosEntity save(ServiciosEntity entity) {
        return repository.save(entity);
    }

    public ServiciosEntity update(ServiciosEntity entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
