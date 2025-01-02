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
@Table(name = "OFICINA")
public class Oficina implements Serializable {

    @Id
    @SequenceGenerator(name = "GID_OFICINA", sequenceName = "GID_OFICINA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GID_OFICINA")
    @Column(name = "ID_OFICINA")
    private Integer idOficina;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "RESPONSABLE")
    private String responsable;

    @Column(name = "COMENTARIO")
    private String comentario;

    @OneToMany(mappedBy = "Oficina", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencia> ListaIncidencias;

    public Oficina(Integer idOficina, String nombre, String responsable, String comentario) {
        this.idOficina = idOficina;
        this.nombre = nombre;
        this.responsable = responsable;
        this.comentario = comentario;
    }

    public Oficina(String nombre, String responsable, String comentario) {
        this.nombre = nombre;
        this.responsable = responsable;
        this.comentario = comentario;
    }

}
