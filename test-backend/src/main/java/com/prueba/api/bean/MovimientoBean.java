package com.prueba.api.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.Date;

@Data
public class MovimientoBean {

    @JsonProperty("idMovimiento")
    @Min(value = 0L, message = "Debe ser valor numérico")
    private long idMovimiento;

    @JsonProperty("fecha")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Date fecha;

    @JsonProperty("valor")
    @JsonFormat(pattern ="^[0-9]+$")
    private double valor;

    @JsonProperty("idCuenta")
    @Min(value = 0L, message = "Debe ser valor numérico")
    private long idCuenta;
}
