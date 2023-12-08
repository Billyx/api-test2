package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="cuenta")
@Entity(name = "cuenta")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCuenta",nullable = false, unique = true)
    private long idCuenta;

    @OneToOne
    @JoinColumn(name="idCliente")
    private Cliente cliente;

    private String numeroCuenta;

    @OneToOne
    @JoinColumn(name="tipoCuenta")
    private TipoCuenta tipoCuenta;

    private double saldoInicial;
    private int estado;
}
