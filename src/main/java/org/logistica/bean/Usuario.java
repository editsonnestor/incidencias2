package org.logistica.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

    @Id
    @SequenceGenerator(name = "GID_USUARIO", sequenceName = "GID_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GID_USUARIO")
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "USUARIO")
    private String usuario;

    @Column(name = "NIVEL_ACCESO")
    private String nivelAceso;

    @Column(name = "CLAVE")
    private String clave;

    public Usuario(String usuario, String nivelAceso, String clave) {
        this.usuario = usuario;
        this.nivelAceso = nivelAceso;
        this.clave = clave;
    }
   
}
