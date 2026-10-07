package com.sena.adso.peluqueria.adso.entidades;

import org.jspecify.annotations.NonNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")

public class UsuariosEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "nombre", nullable = false, length = 50)
        @NonNull
        private String nombre;

        @Column(name = "apellido", nullable = false, length = 50)
        @NonNull
        private String apellido;

        @Column(name = "identificacion", nullable = false, length = 20)
        @NonNull
        private String identification;

        @Column(name = "correo", nullable = false, length = 100)
        @NonNull
        private String correo;

        @Column(name = "user", nullable = false, length = 50)
        @NonNull
        private String user;

        @Column(name = "password", nullable = false, length = 200)
        @NonNull
        private String password;

        @Column(name = "id_roles", nullable = false)
        @NonNull    
        private Integer id_roles;


        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getNombre() {
            return nombre;
        }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        public String getApellido() {
            return apellido;
        }
        public void setApellido(String apellido) {
            this.apellido = apellido;
        }
        public String getIdentification() {
            return identification;
        }
        public void setIdentification(String identification) {
            this.identification = identification;
        }
        public String getCorreo() {
            return correo;
        }
        public void setCorreo(String correo) {
            this.correo = correo;
        }
        public String getUser() {
            return user;
        }
        public void setUser(String user) {
            this.user = user;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }
        public Integer getId_roles() {
            return id_roles;
        }
        public void setId_roles(Integer id_roles) {
            this.id_roles = id_roles;
        }


}
