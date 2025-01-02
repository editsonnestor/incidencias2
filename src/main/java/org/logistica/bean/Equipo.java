package org.logistica.bean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EQUIPO")
public class Equipo implements Serializable{

    @Id
    @SequenceGenerator(name = "GID_EQUIPO", sequenceName = "GID_EQUIPO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GID_EQUIPO")
    @Column(name = "ID_EQUIPO")
    private Integer idEquipo;

    @Column(name = "CODIGO")
    private String codigo;

    @Column(name = "DETALLE")
    private String detalle;

    @Column(name = "COMENTARIO")
    private String comentario;

    @OneToMany(mappedBy = "Equipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> ListaIncidencias;

    public Equipo(Integer idEquipo, String codigo, String detalle, String comentario) {
        this.idEquipo = idEquipo;
        this.codigo = codigo;
        this.detalle = detalle;
        this.comentario = comentario;
    }

    public Equipo(String codigo, String detalle, String comentario) {
        this.codigo = codigo;
        this.detalle = detalle;
        this.comentario = comentario;
    }

}
