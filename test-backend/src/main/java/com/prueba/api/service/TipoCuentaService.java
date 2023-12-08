package com.prueba.api.service;

import com.prueba.api.model.TipoCuenta;
import com.prueba.api.repository.TipoCuentaRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoCuentaService {

    private final TipoCuentaRepository tipoCuentaRepository;

    public TipoCuentaService(TipoCuentaRepository tipoCuentaRepository) {
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    public TipoCuenta obtenerTipoCuentaPorId(int idTipoCuenta){
        return this.tipoCuentaRepository.findTipoCuentaByIdTipo(idTipoCuenta);
    }
}
