package com.sena.adso.peluqueria.adso.controladores;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.peluqueria.adso.entidades.UsuariosEntity;
import com.sena.adso.peluqueria.adso.servicios.UsuariosServices;


@RestController 
@RequestMapping("/api/usuarios")

public class usuariosController {

    private final UsuariosServices servicio;

    usuariosController(UsuariosServices servicio) {
        this.servicio = servicio;
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return ResponseEntity.ok(servicio.getdAll());
    }

    @CrossOrigin(origins = "*")

    @GetMapping("/{id}")
    public ResponseEntity<?> ListarPorId(@PathVariable(value= "id") Integer id){

        Optional<UsuariosEntity> entidad = servicio.getById(id);

        if (entidad.isPresent())
            return ResponseEntity.ok(entidad.get());
        else
            return ResponseEntity.notFound().build();
    }

    
    @CrossOrigin(origins = "*")
    @PostMapping
    public ResponseEntity<?> crearEntidad(@RequestBody UsuariosEntity entity){

        return ResponseEntity.ok(servicio.save(entity));
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEntidad(@RequestBody UsuariosEntity entity){

        return ResponseEntity.ok(servicio.update(entity));
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEntidad(@PathVariable(value= "id") Integer id){
    Optional<UsuariosEntity> entidad = servicio.getById(id);

        if (entidad.isPresent()){
            servicio.delete(id);
            return ResponseEntity.ok(entidad);  
        }
         
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/findNombres/{nombres}")
    public ResponseEntity<?> ListarPorNombres(@PathVariable(value= "nombres") String nombres){
        List<UsuariosEntity> entidad = servicio.getByNombres(nombres);
        return ResponseEntity.ok(entidad);
    }


}
/* CRUD
CREATE--save
READ--getAll, getById
UPDATE--update
DELETE--delete
*/