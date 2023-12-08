package com.prueba.api.bean;

import lombok.Data;

@Data
public class ResponseAppBean<T> {
    private String status;
    private String code;
    private String message;
    private T data;
}
