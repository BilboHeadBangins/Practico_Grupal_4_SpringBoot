package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

/**
 *
 * @author italo
 */
@MappedSuperclass
public abstract class AuditoriaApp extends EntityId{
    @Column(name="fecha_alta",nullable = false)
    protected LocalDateTime fechaAlta;
    @Column(name="fecha_baja")
    protected LocalDateTime fechaBaja;
    @Column(name="fecha_modificacion",nullable = false)
    protected LocalDateTime fechaModificacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_carga", nullable=false)
    protected Usuario usuarioCarga;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_baja")
    protected Usuario usuarioBaja;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_modificacion", nullable=false)
    protected Usuario usuarioModificacion;

    public LocalDateTime getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateTime fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateTime getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(LocalDateTime fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public LocalDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Usuario getUsuarioCarga() {
        return usuarioCarga;
    }

    public void setUsuarioCarga(Usuario usuarioCarga) {
        this.usuarioCarga = usuarioCarga;
    }

    public Usuario getUsuarioBaja() {
        return usuarioBaja;
    }

    public void setUsuarioBaja(Usuario usuarioBaja) {
        this.usuarioBaja = usuarioBaja;
    }

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }
    
    
}
