package com.example.tpgrupal4.Practico_Grupal_4_SpringBoot.entities;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author italo
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)

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

    
}
