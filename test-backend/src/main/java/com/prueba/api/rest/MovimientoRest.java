package com.prueba.api.rest;

import com.prueba.api.bean.MovimientoBean;
import com.prueba.api.bean.ReporteMovimientosBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cuenta;
import com.prueba.api.service.CuentaService;
import com.prueba.api.service.MovimientoService;
import com.prueba.api.util.Constante;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoRest {

    private final CuentaService cuentaService;
    private final MovimientoService movimientoService;

    public MovimientoRest(CuentaService cuentaService, MovimientoService movimientoService) {
        this.cuentaService = cuentaService;
        this.movimientoService = movimientoService;
    }

    @Operation(summary = "Registra la operación bancaria (depósito o retiro)", description = "Registra el movimiento efectuados por el cliente")
    @PostMapping(value = "crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearMovimiento(@Valid @RequestBody MovimientoBean movimiento) {

        ResponseAppBean responseBean = new ResponseAppBean();
        double nuevoSaldo;

        // Verifica si existe la cuenta
        Cuenta cuenta = cuentaService.obtenerCuentaPorId(movimiento.getIdCuenta());
        if(cuenta == null){
            responseBean.setCode(Constante.RESPONSE_ERROR);
            responseBean.setMessage("Error: Cuenta no existe en la base de datos");
            responseBean.setData("ID Cuenta: " + cuenta.getIdCuenta());
            return new ResponseEntity<>(responseBean, HttpStatus.OK);
        }else{

            responseBean = movimientoService.guardarMovimiento(movimiento,cuenta);
            return new ResponseEntity<>(responseBean, HttpStatus.OK);
        }

    }

    @Operation(summary = "Obtiene el estado de cuenta del cliente", description = "Obtiene el detalle de movimientos efectuados por el cliente por cada cuenta que tenga")
    @GetMapping(value = "obtenerEstadoDeCuenta/{idCliente}", produces = "application/json")
    public List<ReporteMovimientosBean> obtenerEstadoDeCuenta(@PathVariable("idCliente") long idCliente) {
        return movimientoService.obtenerMovimientosPorCliente(idCliente);
    }

    @Operation(summary = "Obtiene el estado de cuenta del cliente entre dos fechas", description = "Obtiene el detalle de movimientos efectuados entre dos fechas por cada cuenta que tenga")
    @GetMapping(value = "obtenerEstadoDeCuentaPorFechas", produces = "application/json")
    public List<ReporteMovimientosBean> obtenerEstadoDeCuentaPorFechas(@RequestParam("idCliente") long idCliente,
                                                                       @RequestParam("fechaIni") String fechaIni,
                                                                       @RequestParam("fechaFin") String fechaFin) {
        return movimientoService.obtenerMovimientosPorClientePorFechas(idCliente,fechaIni, fechaFin);
    }
}
