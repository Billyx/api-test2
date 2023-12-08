package com.prueba.api.model;

import jakarta.persistence.*;

@Table(name="tipocuenta")
@Entity(name="tipocuenta")
public class TipoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTipo;
    private String descripcion;
    private boolean estado;
}
