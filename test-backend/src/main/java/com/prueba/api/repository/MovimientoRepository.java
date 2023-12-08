package com.prueba.api.repository;

import com.prueba.api.bean.ReporteMovimientosBean;
import com.prueba.api.model.Cuenta;
import com.prueba.api.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Movimiento findMovimientoByIdMovimiento(long idMovimiento);
    List<Movimiento> findMovimientoByCuenta(Cuenta cuenta);

    @Query("Select m.saldo from movimiento m where cuenta.idCuenta = ?1 order by m.idMovimiento desc")
    List<Double> obtenerUltimoSaldo(long idCuenta);

    @Query("select m.fecha, p.nombre, c.numeroCuenta , tc.descripcion as tipoCuenta, c.estado , m.valor , m.saldo " +
            " from movimiento m" +
            " join cuenta c on m.cuenta.idCuenta = c.idCuenta \n" +
            " join tipocuenta tc on tc.idTipo = c.tipoCuenta.idTipo \n" +
            " join cliente cl on c.cliente.idCliente = cl.idCliente \n" +
            " join persona p on cl.persona.idPersona = p.idPersona" +
            " where cl.idCliente = ?1 " +
            " order by tc.idTipo,m.idMovimiento")
    List<Object[]> listarReporteMovimientosPorCliente(long idCliente);

    @Query("select m.fecha, p.nombre, c.numeroCuenta , tc.descripcion as tipoCuenta, c.estado , m.valor , m.saldo " +
            " from movimiento m" +
            " join cuenta c on m.cuenta.idCuenta = c.idCuenta \n" +
            " join tipocuenta tc on tc.idTipo = c.tipoCuenta.idTipo \n" +
            " join cliente cl on c.cliente.idCliente = cl.idCliente \n" +
            " join persona p on cl.persona.idPersona = p.idPersona" +
            " where cl.idCliente = ?1" +
            " and m.fecha between CAST(?2 AS date) and CAST(?3 AS date)" +
            " order by tc.idTipo,m.idMovimiento")
    List<Object[]> listarReporteMovimientosPorClientePorFechas(long idCliente, String fechaInicio, String fechaFin);

}
