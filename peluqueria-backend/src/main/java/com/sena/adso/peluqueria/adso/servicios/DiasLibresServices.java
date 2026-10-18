package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.DiasLibresEntity;
import com.sena.adso.peluqueria.adso.repositorios.DiasLibreRepository;

@Service 
public class DiasLibresServices {
    private final DiasLibreRepository repository;

    public DiasLibresServices(DiasLibreRepository repository) {
        this.repository = repository;
    }

    public List<DiasLibresEntity> getALL() {
        return repository.findAll();
    }

    public Optional<DiasLibresEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public DiasLibresEntity save(DiasLibresEntity entity) {
        return repository.save(entity);
    }
    
    public DiasLibresEntity update(DiasLibresEntity entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;   
    }
    
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

/*getAll= atrapa las citas de findALL para devolver lo registrado 
CRUD

READ--getById
CREATE--save
UPDATE--update
DELETE--delete
*/