package com.prueba.api.rest;

import com.prueba.api.bean.CuentaBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cuenta;
import com.prueba.api.service.ClienteService;
import com.prueba.api.service.CuentaService;
import com.prueba.api.service.TipoCuentaService;
import com.prueba.api.util.Constante;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaRest {

    private final CuentaService cuentaService;
    private final ClienteService clienteService;

    private final TipoCuentaService tipoCuentaService;
    ResponseAppBean responseAppBean = new ResponseAppBean();

    public CuentaRest(CuentaService cuentaService, ClienteService clienteService, TipoCuentaService tipoCuentaService) {
        this.cuentaService = cuentaService;
        this.clienteService = clienteService;
        this.tipoCuentaService = tipoCuentaService;
    }

    @PostMapping(value = "crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody CuentaBean cuenta) {

        ResponseAppBean responseBean = new ResponseAppBean();
        try{
            // Verifica si el cliente existe
            responseBean = clienteService.verificarExisteCliente(cuenta.getIdCliente());
            if(responseBean.getCode() == "0"){
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }

            // Verifica si existe el tipo de cuenta
            responseBean = cuentaService.verificarExisteTipoDeCuenta(cuenta.getTipoCuenta());
            if(responseBean.getCode() == "0"){
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }

            Cuenta nuevacuenta = cuentaService.crearNuevaCuenta(cuenta);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            responseAppBean.setCode(Constante.RESPONSE_OK);
            responseAppBean.setMessage("Cuenta registrada satisfactoriamente");
            responseAppBean.setData(nuevacuenta);

            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

        }catch(Exception e){

            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "editar", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> editarCuenta(@Valid @RequestBody CuentaBean cuentaBean) {

        ResponseAppBean responseBean = new ResponseAppBean();

        try{
            // Verifica si existe el cliente
            responseBean = clienteService.verificarExisteCliente(cuentaBean.getIdCliente());
            if(responseBean.getCode() == "0"){
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }

            // Verifica si existe el tipo de cuenta
            responseBean = cuentaService.verificarExisteTipoDeCuenta(cuentaBean.getTipoCuenta());
            if(responseBean.getCode() == "0"){
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }

            // Verifica si existe la cuenta
            Cuenta cuentaedit = cuentaService.obtenerCuentaPorId(cuentaBean.getIdCuenta());
            if(cuentaedit == null){
                responseBean.setCode(Constante.RESPONSE_ERROR);
                responseBean.setMessage("Error: Cuenta no existe en la base de datos");
                responseBean.setData("ID Cuenta: " + cuentaBean.getIdCuenta());
                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }else{

                cuentaedit.setCliente(clienteService.obtenerClientePorId(cuentaBean.getIdCliente()));
                cuentaedit.setNumeroCuenta(cuentaBean.getNumeroCuenta());
                cuentaedit.setTipoCuenta(tipoCuentaService.obtenerTipoCuentaPorId(cuentaBean.getTipoCuenta()));
                cuentaedit.setSaldoInicial(cuentaBean.getSaldoInicial());
                cuentaedit.setEstado(cuentaBean.getEstado());

                Cuenta cuentaEditada = cuentaService.guardarCuenta(cuentaedit);
                responseBean.setStatus(Constante.RESPONSE_OK);
                responseBean.setCode(Constante.RESPONSE_OK);
                responseBean.setMessage("La cuenta se editó satisfactoriamente");
                responseBean.setData(cuentaEditada);

                return new ResponseEntity<>(responseBean, HttpStatus.OK);
            }
        }catch(Exception e){

            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setCode(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="eliminar/{idCuenta}")
    public ResponseEntity<?> eliminarCliente(@PathVariable("idCuenta") long idCuenta) {

        try {
            Cuenta cuenta = cuentaService.obtenerCuentaPorId(idCuenta);
            if (cuenta == null) {
                responseAppBean.setCode(Constante.RESPONSE_ERROR);
                responseAppBean.setMessage("Error: Cuenta no existe en la base de datos");
                responseAppBean.setData("ID Cuenta: " + idCuenta);


            } else {
                cuentaService.eliminarCuenta(cuenta);
                responseAppBean.setCode(Constante.RESPONSE_OK);
                responseAppBean.setMessage("Cuenta eliminada satisfactoriamente");
                responseAppBean.setData("ID Cuenta: " + idCuenta);

            }
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

        } catch (Exception e) {
            responseAppBean.setCode(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }
    }
}
