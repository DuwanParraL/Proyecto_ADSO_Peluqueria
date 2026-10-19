package com.sena.adso.peluqueria.adso.controladores;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.adso.peluqueria.adso.entidades.EmpleadosEntity;
import com.sena.adso.peluqueria.adso.entidades.RolesEntity;
import com.sena.adso.peluqueria.adso.entidades.UsuariosEntity;
import com.sena.adso.peluqueria.adso.repositorios.EmpleadosRepository;
import com.sena.adso.peluqueria.adso.repositorios.RolesRepository;
import com.sena.adso.peluqueria.adso.repositorios.UsuariosRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuariosRepository usuariosRepository;
    private final RolesRepository rolesRepository;
    private final EmpleadosRepository empleadosRepository;

    public AuthController(UsuariosRepository usuariosRepository,
                           RolesRepository rolesRepository,
                           EmpleadosRepository empleadosRepository) {
        this.usuariosRepository = usuariosRepository;
        this.rolesRepository = rolesRepository;
        this.empleadosRepository = empleadosRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credenciales) {
        String usuarioOCorreo = credenciales.get("usuario");
        String password = credenciales.get("password");

        if (usuarioOCorreo == null || usuarioOCorreo.isBlank() || password == null || password.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("mensaje", "Debe enviar usuario y password"));
        }

        Optional<UsuariosEntity> usuarioOpt = usuariosRepository.findByUserOrCorreo(usuarioOCorreo.trim());

        if (usuarioOpt.isEmpty() || !Objects.equals(usuarioOpt.get().getPassword(), password)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensaje", "Usuario o contraseña incorrectos"));
        }

        UsuariosEntity usuario = usuarioOpt.get();

 
        String nombreRol = "CLIENTE";
        Optional<RolesEntity> rolOpt = rolesRepository.findById(usuario.getId_roles());
        if (rolOpt.isPresent()) {
            String nombre = rolOpt.get().getNombre();
            if (nombre != null && !nombre.isBlank()) {
                nombreRol = nombre.toUpperCase();
            }
        }


        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idUsuario", usuario.getId());
        respuesta.put("usuario", usuario.getUser());
        respuesta.put("nombreCompleto", usuario.getNombre() + " " + usuario.getApellido());
        respuesta.put("idRol", usuario.getId_roles());
        respuesta.put("rol", nombreRol);


        Optional<EmpleadosEntity> empOpt = empleadosRepository.findByUsuario(usuario.getId());
        if (empOpt.isPresent()) {
            respuesta.put("idEmpleado", empOpt.get().getId());
            respuesta.put("especialidad", empOpt.get().getEspecialidades());
            respuesta.put("horaEntrada", empOpt.get().getHoraEntrada());
            respuesta.put("horaSalida", empOpt.get().getHoraSalida());
        }

        return ResponseEntity.ok(respuesta);
    }
}