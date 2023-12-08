package com.prueba.api.bean;

import lombok.Data;
import java.util.Date;

@Data
public class ReporteMovimientosBean {
    private Date fecha;
    private String nombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private Integer estado;
    private Double valor;
    private Double saldo;

    public ReporteMovimientosBean(){}
}
