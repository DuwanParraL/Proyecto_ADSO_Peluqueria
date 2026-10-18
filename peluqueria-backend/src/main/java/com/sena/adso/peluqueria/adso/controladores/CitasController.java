package com.sena.adso.peluqueria.adso.controladores;

import java.util.List;

import java.util.Optional;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    public CitasEntity save(@RequestBody CitasEntity entity) {
       return servicio.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitasEntity> update(@PathVariable Integer id, @RequestBody CitasEntity entity){
        entity.setId(id);
        CitasEntity actualizado = servicio.update(entity);
        if (actualizado != null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(actualizado);
        }
    }
    @PutMapping("/{id}/estado/{nuevoEstado}")
    public ResponseEntity<CitasEntity> update(@PathVariable Integer id, @RequestBody String nuevoEstado){
        CitasEntity actualizado = servicio.CambiarEstado(id, nuevoEstado);
        if (actualizado != null) {
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
