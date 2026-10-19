package com.sena.adso.peluqueria.adso.controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.peluqueria.adso.entidades.CitasEntity;
import com.sena.adso.peluqueria.adso.servicios.CitasServices;
import org.springframework.web.bind.annotation.PutMapping;



@RestController 
@RequestMapping ("/citas")
public class CitasController {
    
    private final CitasServices servicio;

    public CitasController(CitasServices servicio) {
        this.servicio = servicio;
    }

    @GetMapping 
    public List<CitasEntity> getAll(){
        return servicio.getALL();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitasEntity> getById(@PathVariable Integer id){
        Optional<CitasEntity> cita = servicio.getById(id);
        return cita.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        
    }
    
    @GetMapping("/cliente/{idCliente}")
    public List<CitasEntity> getByCliente(@PathVariable Integer idCliente) {
        return servicio.getByIdCliente(idCliente);
    }

    @GetMapping("/empleado/{idEmpleado}")
    public List<CitasEntity> getByEmpleado(@PathVariable Integer idEmpleado) {
        return servicio.getByIdEmpleado(idEmpleado);
    }

    @GetMapping("/empleado/{idEmpleado}/fecha/{fecha}")
    public List<CitasEntity> getAgendaPorFecha(@PathVariable Integer idEmpleado, @PathVariable LocalDate fecha) {
        return servicio.getAgendaEmpleadoPorFecha(idEmpleado, fecha);
    }

    @GetMapping ("pendientes/contador")
    public Map<String, Long> contarPendientes(@RequestParam(required = false) Integer idEmpleado) {
        long total = (idEmpleado!= null)
        ? servicio.contarPendientesEmpleado(idEmpleado)
        : servicio.contarTodosPendientes();
        return Map.of("totalPendientes", total);
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody CitasEntity entity) {
       String errorDisponibilidad = servicio.validarDisponibilidadPeluquero(entity);
        if (errorDisponibilidad != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("mensaje", errorDisponibilidad));
        }
       return ResponseEntity.status(HttpStatus.CREATED).body(servicio.save(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitasEntity> update(@PathVariable Integer id, @RequestBody CitasEntity entity){
        entity.setId(id);
        CitasEntity actualizado = servicio.update(entity);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(actualizado);
        }
    }
    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<CitasEntity> cambiarEstado(@PathVariable Integer id, @PathVariable String nuevoEstado){
        CitasEntity actualizado = servicio.CambiarEstado(id, nuevoEstado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(actualizado);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if (servicio.getById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
