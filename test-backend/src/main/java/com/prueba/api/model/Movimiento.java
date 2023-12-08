package com.prueba.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Table(name="movimiento")
@Entity(name="movimiento")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMovimiento",nullable = false, unique = true)
    private long idMovimiento;
    private Date fecha;
    private double valor;
    private double saldo;
    @OneToOne
    @JoinColumn(name="idCuenta")
    private Cuenta cuenta;
}
