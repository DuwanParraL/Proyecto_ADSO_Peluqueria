package com.sena.adso.peluqueria.adso.servicios;

import com.sena.adso.peluqueria.adso.repositorios.EmpleadosRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.sena.adso.peluqueria.adso.entidades.CitasEntity;
import com.sena.adso.peluqueria.adso.entidades.EmpleadosEntity;
import com.sena.adso.peluqueria.adso.repositorios.CitasRepository;
import com.sena.adso.peluqueria.adso.repositorios.DiasLibreRepository;


@Service 

public class CitasServices {
    private final EmpleadosRepository empleadosRepository;

    private final CitasRepository repository;
    private final DiasLibreRepository diasLibreRepository;

    public CitasServices(CitasRepository repository, DiasLibreRepository diasLibreRepository, EmpleadosServices empleadosServices, EmpleadosRepository empleadosRepository) {
        this.repository = repository;
        this.diasLibreRepository = diasLibreRepository;

        this.empleadosRepository = empleadosRepository;
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
    public List<CitasEntity> getAgendaEmpleadoPorFecha(Integer idEmpleado, LocalDate fecha) {
        return repository.findByEmpleadoAndFecha(idEmpleado, fecha);
    }
    public long contarPendientesEmpleado(Integer idEmpleado) {
        return repository.contarPendientesPorEmpleado(idEmpleado);
    }

    public long contarTodosPendientes(){
        return repository.contarTodosLosPendientes();
    }

    public String validarDisponibilidadPeluquero(CitasEntity entity) {
        if (diasLibreRepository.tienenDiasAprobados(entity.getIdEmpleado(), entity.getFecha())) {
            return "El barbero seleccionado tiene día libre en la fecha indicada.";
        }
        Optional<EmpleadosEntity> empleadoOpt = empleadosRepository.findById(entity.getIdEmpleado());
            if (empleadoOpt.isPresent()){
                EmpleadosEntity empleado = empleadoOpt.get();
               if (!Boolean.TRUE.equals(empleado.getActivo())) {
                return "El barbero seleccionado no está activo.";
        }
                if (entity.getHora().isBefore(empleado.getHoraEntrada()) || entity.getHora().isAfter(empleado.getHoraSalida())){
                    return  "El barbero no esta disponible en el horario seleccionado. Su horario de trabajo es de " + empleado.getHoraEntrada() + " a " + empleado.getHoraSalida() + "."; 
                }
            }
            if (repository.existeTurnoOcupado(entity.getIdEmpleado(), entity.getFecha(), entity.getHora())){
                return "El barbero seleccionado ya tiene una cita en la fecha y hora indicada.";
            } 
            return null;
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