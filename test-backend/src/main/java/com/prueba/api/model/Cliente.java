package com.prueba.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Schema
@Data
@Entity(name="cliente")
@Table(name="cliente")
//@SuperBuilder
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCliente",nullable = false, unique = true)
    private long idCliente;

    @Schema(description = "Data correspondiente a la persona")
    @OneToOne
    @JoinColumn(name="idPersona")
    Persona persona;
    /*
    @OneToMany
    @JoinColumn(name="idCuenta")
    List<Cuenta> cuenta*/

    private String contrasena;
    private int estado;

}
