package com.prueba.api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ClienteBean {

    @JsonProperty("idCliente")
    @Min(value = 0L, message = "Debe ser valor numérico")
    private long idCliente;

    @JsonProperty("nombre")
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3)
    private String nombre;

    @JsonProperty("genero")
    @Size(min = 1, max = 1, message = "Género inválido, usar 'M' o 'F'")
    @Pattern(regexp = "^[{F}{M}]$", message = "Género inválido, usar 'M' o 'F'")
    private String genero;

    @JsonProperty("edad")
    @Digits(integer = 2, fraction = 0, message = "Debe ser un número válido")
    private Integer edad;

    @JsonProperty("identificacion")
    @Pattern(regexp = "^[0-9]+$", message = "Dato debe ser numérico")
    @Size(min=8, max=8, message = "Debe tener 8 números")
    private String identificacion;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("telefono")
    @Pattern(regexp = "^[0-9\\-]+$", message = "El campo debe contener solo números")
    private String telefono;

    @JsonProperty("contrasena")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "La contraseña no debe contener caracteres extraños")
    @Size(min=8, message = "La constraseña debe tener al menos 8 caracteres")
    private String contrasena;

    @JsonProperty("estado")
    @Digits(integer = 1 , fraction = 0, message = "El estado debe ser 1 ó 0")
    @Max(1)
    @Min(0)
    @NotNull(message = "El estado es obligatorio")
    private int estado;
}
