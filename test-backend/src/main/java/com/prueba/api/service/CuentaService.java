package com.prueba.api.service;

import com.prueba.api.bean.CuentaBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cliente;
import com.prueba.api.model.Cuenta;
import com.prueba.api.model.TipoCuenta;
import com.prueba.api.repository.ClienteRepository;
import com.prueba.api.repository.CuentaRepository;
import com.prueba.api.repository.TipoCuentaRepository;
import com.prueba.api.util.Constante;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    private final CuentaRepository cuentaRepository;
    private final ClienteRepository clienteRepository;
    private final TipoCuentaRepository tipoCuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository, ClienteRepository clienteRepository, TipoCuentaRepository tipoCuentaRepository) {
        this.cuentaRepository = cuentaRepository;
        this.clienteRepository = clienteRepository;
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    public Cuenta crearNuevaCuenta(CuentaBean cuentabean){

        Cliente cliente = clienteRepository.findClienteByIdCliente(cuentabean.getIdCliente());
        TipoCuenta tipoCuenta = tipoCuentaRepository.findTipoCuentaByIdTipo(cuentabean.getTipoCuenta());

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(cuentabean.getNumeroCuenta());
        cuenta.setTipoCuenta(tipoCuenta);
        cuenta.setSaldoInicial(cuentabean.getSaldoInicial());
        cuenta.setEstado(cuentabean.getEstado());
        return cuentaRepository.save(cuenta);
    }

    public Cuenta guardarCuenta(Cuenta cuenta){
        return cuentaRepository.save(cuenta);
    }

    public void eliminarCuenta(Cuenta cuenta){
        cuentaRepository.delete(cuenta);
    }

    public ResponseAppBean verificarExisteCuenta(long idCuenta){
        ResponseAppBean responseAppBean = new ResponseAppBean();
        Cuenta cuenta = cuentaRepository.findCuentaByIdCuenta(idCuenta);
        if(cuenta == null){
            responseAppBean.setCode(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage("Error: Cuenta no existe en la base de datos");
            responseAppBean.setData("ID Cuenta: "+idCuenta);

        }else{
            responseAppBean.setCode(Constante.RESPONSE_OK);
            responseAppBean.setData(cuenta);
        }

        return responseAppBean;
    }

    public ResponseAppBean verificarExisteTipoDeCuenta(int idTipoCuenta){

        ResponseAppBean responseAppBean = new ResponseAppBean();
        TipoCuenta tipoCuenta = tipoCuentaRepository.findTipoCuentaByIdTipo(idTipoCuenta);

        if(tipoCuenta == null){
            responseAppBean.setCode(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage("Error: Tipo de cuenta no existe en la base de datos");
            responseAppBean.setData("ID Tipo cuenta: "+idTipoCuenta);
            return responseAppBean;

        }else{
            responseAppBean.setCode(Constante.RESPONSE_OK);
        }

        return responseAppBean;
    }

    public Cuenta obtenerCuentaPorId(long idCuenta){
        return cuentaRepository.findCuentaByIdCuenta(idCuenta);
    }

}
