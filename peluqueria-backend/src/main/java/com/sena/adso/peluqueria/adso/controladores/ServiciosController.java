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

import com.sena.adso.peluqueria.adso.entidades.ServiciosEntity;
import com.sena.adso.peluqueria.adso.servicios.ServiciosServices;






@RestController 
@RequestMapping("/api/servicios")
public class ServiciosController {
    private final ServiciosServices servicios;

    public ServiciosController(ServiciosServices servicios) {
        this.servicios = servicios;
    }

    @GetMapping
    public List<ServiciosEntity> getAll() {
        return servicios.getALL();
    }
    
    @GetMapping("/estado")
        public List<ServiciosEntity> getActive() {
        return servicios.getActive();    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ServiciosEntity> getById(@PathVariable Integer id) {
        Optional<ServiciosEntity> item = servicios.getById(id);
        return item.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build()); 
    }
    @PostMapping
    public ServiciosEntity save(@RequestBody ServiciosEntity entity) {
        return servicios.save(entity);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiciosEntity> update(@PathVariable Integer id, @RequestBody ServiciosEntity entity) {
        entity.setId(id);
        ServiciosEntity actualizado = servicios.update(entity);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(actualizado);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        servicios.delete(id);
        if (servicios.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
    
}
