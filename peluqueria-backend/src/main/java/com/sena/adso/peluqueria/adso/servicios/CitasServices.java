package com.sena.adso.peluqueria.adso.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.CitasEntity;
import com.sena.adso.peluqueria.adso.repositorios.CitasRepository;


@Service 

public class CitasServices {
    private final CitasRepository repository;

    public CitasServices(CitasRepository repository) {
        this.repository = repository;
    }

    public List<CitasEntity> getALL() {
        return repository.findAll();
    }

    public Optional<CitasEntity> getById(Integer id) {
        return repository.findById(id);
    }

    public List<CitasEntity> getByIdCliente(Integer idCliente) {
        return repository.findByIdCliente(idCliente);
    }
    
    public List<CitasEntity> getByIdEmpleado(Integer idEmpleado) {
        return repository.findByIdEmpleado(idEmpleado);
    }
    
    public List<CitasEntity> getByEstado(String estado) {
        return repository.findByEstado(estado);
    }
    
    public CitasEntity save(CitasEntity entity) {
        return repository.save(entity);
    }

    public CitasEntity update(CitasEntity entity) {
        if (entity.getId() != null && repository.existsById(entity.getId())) {
            return repository.save(entity);
        }
        return null;   
    }

    public CitasEntity CambiarEstado(Integer id, String NuevoEstado){
        Optional<CitasEntity> citaOpt = repository.findById(id);
        if (citaOpt.isPresent()) {
            CitasEntity cita = citaOpt.get();
            cita.setEstado(NuevoEstado);
            return repository.save(cita);
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