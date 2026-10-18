package com.sena.adso.peluqueria.adso.controladores;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sena.adso.peluqueria.adso.entidades.DiasLibresEntity;
import com.sena.adso.peluqueria.adso.servicios.DiasLibresServices;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController 
@RequestMapping("/diaslibres")
public class DiasLibresController {
    
    private final DiasLibresServices servicio;
    
    public DiasLibresController(DiasLibresServices servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public List<DiasLibresEntity> getAll() {
        return servicio.getALL();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiasLibresEntity> getById(@PathVariable Integer id){
        Optional<DiasLibresEntity> entity = servicio.getById(id);
        return entity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    
    }

    @PostMapping
    public DiasLibresEntity save(@RequestBody DiasLibresEntity entity) {
        return servicio.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiasLibresEntity> update(@PathVariable Integer id, @RequestBody DiasLibresEntity entity){
        entity.setId(id);
        DiasLibresEntity actualizado = servicio.update(entity);
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
