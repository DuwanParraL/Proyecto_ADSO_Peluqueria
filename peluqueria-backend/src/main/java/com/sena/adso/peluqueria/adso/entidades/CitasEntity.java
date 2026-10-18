package com.sena.adso.peluqueria.adso.entidades;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "citas")

public class CitasEntity {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    
    @Column (name = "id")
    private Integer id;

    @Column (name = "idCliente", nullable = false)
    private Integer idCliente;

    @Column (name = "idEmpleado", nullable = false)
    private Integer idEmpleado;

    @Column (name ="idServicio", nullable = false)
    private Integer idServicio;

    @Column (name = "fecha", nullable = false)
    private LocalTime fecha;

    @Column (name = "hora", nullable = false)
    private LocalTime hora;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado = "PENDIENTE";

    @Column (name = "observaciones", nullable = true, length = 200) 
    private String observaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public LocalTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalTime fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


}
