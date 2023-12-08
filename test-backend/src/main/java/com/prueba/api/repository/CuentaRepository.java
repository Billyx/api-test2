package com.prueba.api.repository;

import com.prueba.api.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    public Cuenta findCuentaByIdCuenta(long idCuenta);
}
