package org.logistica.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "INCIDENCIA", indexes = {
    @Index(name = "ID_EQUIPO", columnList = "ID_EQUIPO"),
    @Index(name = "ID_OFICINA", columnList = "ID_OFICINA")})
public class Incidencia implements Serializable {

    @Id
    @SequenceGenerator(name = "GID_INCIDENCIA", sequenceName = "GID_INCIDENCIA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GID_INCIDENCIA")
    @Column(name = "ID_INCIDENCIA")
    private Integer idIncidencia;

    @Column(name = "FECHA")
    private Date fecha;

    @Column(name = "GLOSA")
    private String glosa;

    @Column(name = "PRIORIDAD")
    private String prioridad;

    @Column(name = "ESTADO")
    private String estado;  

    @Column(name = "COMENTARIO")
    private String comentario;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_EQUIPO", foreignKey = @ForeignKey(name = "FK_INCIDENCIA_EQUIPOS"))
    private Equipo equipo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_OFICINA", foreignKey = @ForeignKey(name = "FK_INCIDENCIA_OFICINAS"))
    private Oficina oficina;

    public Incidencia(Date fecha, String glosa, String prioridad, String estado, String comentario, Equipo equipo, Oficina oficina) {
        this.fecha = fecha;
        this.glosa = glosa;
        this.prioridad = prioridad;
        this.estado = estado;
        this.comentario = comentario;
        this.equipo = equipo;
        this.oficina = oficina;
    }

}
