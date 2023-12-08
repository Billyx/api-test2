package com.prueba.api.service;

import com.prueba.api.bean.MovimientoBean;
import com.prueba.api.bean.ReporteMovimientosBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cuenta;
import com.prueba.api.model.Movimiento;
import com.prueba.api.repository.CuentaRepository;
import com.prueba.api.repository.MovimientoRepository;
import com.prueba.api.util.Constante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;

    @Autowired
    public MovimientoService(MovimientoRepository movimientoRepository, CuentaRepository cuentaRepository) {
        this.movimientoRepository = movimientoRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public ResponseAppBean guardarMovimiento(MovimientoBean movimiento, Cuenta cuenta){

        Double ultimoSaldo;
        ResponseAppBean responseBean = new ResponseAppBean();

        Movimiento newmovimiento = new Movimiento();
        newmovimiento.setFecha(movimiento.getFecha());
        newmovimiento.setValor(movimiento.getValor());
        newmovimiento.setCuenta(cuenta);

        // Verificar si ya existen movimientos para obtener saldo
        if(this.existenMovimientosPorCuenta(cuenta)){
            List<Double> listaSaldos =  this.movimientoRepository.obtenerUltimoSaldo(cuenta.getIdCuenta());
            ultimoSaldo = listaSaldos.get(0).doubleValue();

            if ( (ultimoSaldo + movimiento.getValor()) < 0 ){
                responseBean.setStatus(Constante.RESPONSE_OK);
                responseBean.setCode(Constante.RESPONSE_ERROR);
                responseBean.setMessage("Saldo No disponible. Se puede retirar como máximo: " + ultimoSaldo);
                responseBean.setData(null);
                return responseBean;
            }
            newmovimiento.setSaldo(ultimoSaldo + movimiento.getValor());

        // Si no se obtiene el saldo como referencia del saldo inicial
        }else{
            newmovimiento.setSaldo(cuenta.getSaldoInicial() + movimiento.getValor());
        }

        Movimiento nmovimiento = movimientoRepository.save(newmovimiento);
        responseBean.setStatus(Constante.RESPONSE_OK);
        responseBean.setCode("1");
        responseBean.setMessage("Se registró el movimiento satisfactoriamente");
        responseBean.setData(nmovimiento);

        return responseBean;

    }

    public boolean existenMovimientosPorCuenta(Cuenta cuenta){
        List<Movimiento> movimientos = movimientoRepository.findMovimientoByCuenta(cuenta);
        if( movimientos.size() == 0){
            return false;
        }else{
            return true;
        }
    }

   public List<ReporteMovimientosBean> obtenerMovimientosPorCliente(long idCliente){
       List<Object[]> list =  movimientoRepository.listarReporteMovimientosPorCliente(idCliente);
       List<ReporteMovimientosBean> movimientosBeanList = new ArrayList<ReporteMovimientosBean>();

       movimientosBeanList = this.construirListaResultados(list,movimientosBeanList);
       return movimientosBeanList;

   }

    public List<ReporteMovimientosBean> obtenerMovimientosPorClientePorFechas(long idCliente, String fechaInicio, String fechaFin){

        List<Object[]> list =  movimientoRepository.listarReporteMovimientosPorClientePorFechas(idCliente, fechaInicio, fechaFin );
        List<ReporteMovimientosBean> movimientosBeanList = new ArrayList<ReporteMovimientosBean>();
        movimientosBeanList = this.construirListaResultados(list,movimientosBeanList);
        return movimientosBeanList;
    }

    public List<ReporteMovimientosBean> construirListaResultados(List<Object[]> result, List<ReporteMovimientosBean> listaReporteMovimientosBean ){
        for (Object[] resultado : result) {

            ReporteMovimientosBean rmb = new ReporteMovimientosBean();
            rmb.setFecha((Date) resultado[0]);
            rmb.setNombre((String) resultado[1]);
            rmb.setNumeroCuenta((String) resultado[2]);
            rmb.setTipoCuenta((String) resultado[3]);
            rmb.setEstado((Integer) resultado[4]);
            rmb.setValor((Double) resultado[5]);
            rmb.setSaldo((Double) resultado[6]);

            listaReporteMovimientosBean.add(rmb);
        }
        return listaReporteMovimientosBean;
    }

}
