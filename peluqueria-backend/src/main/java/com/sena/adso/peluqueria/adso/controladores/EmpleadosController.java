package com.sena.adso.peluqueria.adso.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.peluqueria.adso.entidades.EmpleadosEntity;
import com.sena.adso.peluqueria.adso.servicios.EmpleadosServices;

@RestController 
@RequestMapping ("/api/empleados")
public class EmpleadosController {
    private final EmpleadosServices servicio;
    public EmpleadosController(EmpleadosServices servicio) {
        this.servicio = servicio;
    }
    
    @GetMapping 
    public List<EmpleadosEntity> getALL(){
        return servicio.getALL();
    }

    @GetMapping ("/activos")
    public List<EmpleadosEntity> getActivos(){
        return servicio.getActivos();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<EmpleadosEntity> getByUsuario(@PathVariable Integer id){
        Optional<EmpleadosEntity> empleado = servicio.getById(id);
        return empleado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping ("/usuario/{idUsuario}")
    public ResponseEntity<EmpleadosEntity> getByUsuarioId(@PathVariable Integer idUsuario){
        Optional<EmpleadosEntity> empleado = servicio.getByUsuarios(idUsuario);
        return empleado.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping 
    public EmpleadosEntity save(@RequestBody EmpleadosEntity entity){
        return servicio.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadosEntity> update(@PathVariable Integer id, @RequestBody EmpleadosEntity entity){
        entity.setId(id);
        EmpleadosEntity actualizado = servicio.update(entity);
        if(actualizado == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (servicio.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }
}
