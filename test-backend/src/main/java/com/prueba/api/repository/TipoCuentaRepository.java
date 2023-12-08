package com.prueba.api.repository;

import com.prueba.api.model.TipoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCuentaRepository extends JpaRepository<TipoCuenta, Long> {
    public TipoCuenta findTipoCuentaByIdTipo(int idTipo);
}
