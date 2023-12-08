package com.prueba.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
public class CuentaBean {

    @JsonProperty("idCuenta")
    @Min(value = 0L, message = "Debe ser valor numérico")
    private long idCuenta;

    @JsonProperty("idCliente")
    @Min(value = 0L, message = "Debe ser valor numérico")
    @NotNull(message = "Valor no puede ser nulo")
    private long idCliente;

    @JsonProperty("numeroCuenta")
    @Pattern(regexp = "^[0-9]+$", message = "Dato debe ser numérico")
    @Size(min=18, max=18, message = "Debe tener 18 números")
    private String numeroCuenta;

    @JsonProperty("tipoCuenta")
    @Digits(integer = 2, fraction = 0, message = "Debe ser un número válido")
    private Integer tipoCuenta;

    @JsonProperty("saldoInicial")
    @Min(value = 0L, message = "Debe ser valor numérico")
    @NotNull(message = "Debe tener un saldo inicial")
    private double saldoInicial;

    @JsonProperty("estado")
    @Digits(integer = 1 , fraction = 0, message = "El valor del estado debe ser 1 ó 0")
    @NotNull(message = "El estado es obligatorio")
    @Max(1)
    private int estado;

}
