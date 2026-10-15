package com.sena.adso.peluqueria.adso.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.peluqueria.adso.entidades.RolesEntity;
import com.sena.adso.peluqueria.adso.servicios.RolesServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/roles")

public class RolesController {
    
    private final RolesServices servicio;

    public RolesController(RolesServices servicio) {
        this.servicio = servicio;
    }
    @GetMapping 
    public List<RolesEntity> getAll() {
        return servicio.getALL();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesEntity> getById(@PathVariable Integer id) {
        Optional<RolesEntity> entity = servicio.getById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping 
    public RolesEntity save(@RequestBody RolesEntity entity) {
        return servicio.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesEntity> update(@PathVariable Integer id, @RequestBody RolesEntity entity){
        entity.setId(id);
        RolesEntity actualizado = servicio.update(entity);
        if (actualizado == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(actualizado);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (servicio.getById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            servicio.delete(id);
            return ResponseEntity.noContent().build();
        }
    }






}
