package com.sena.adso.peluqueria.adso.entidades;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
@Entity 
@Table (name = "empleados")
public class EmpleadosEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column (name = "id")
    private Integer id;

    @Column (name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @Column (name = "especialidades", nullable = false, length = 100)
    private String especialidades;

    @Column (name = "telefono",  length = 20)
    private String telefono;

    @Column (name = "horaEntrada", nullable = false)
    private LocalTime horaEntrada = LocalTime.of(8, 0);

    @Column (name = "horaSalida", nullable = false)
    private LocalTime horaSalida = LocalTime.of(17, 0);

    @Column (name = "estado", length = 50)
    private String estado = "ACTIVO";

    @Column (name = "activo", nullable = false)
    private Boolean activo = true;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(String especialidades) {
        this.especialidades = especialidades;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
