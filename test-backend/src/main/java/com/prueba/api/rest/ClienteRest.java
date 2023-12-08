package com.prueba.api.rest;

import com.prueba.api.bean.ClienteBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cliente;
import com.prueba.api.service.ClienteService;
import com.prueba.api.service.PersonaService;
import com.prueba.api.util.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRest {

    private final ClienteService clienteService;
    private final PersonaService personaService;
    ResponseAppBean responseAppBean = new ResponseAppBean();

    public ClienteRest(ClienteService clienteService, PersonaService personaService) {
        this.clienteService = clienteService;
        this.personaService = personaService;
    }

    @Operation(summary = "Crea un nuevo cliente", description = "Crea un nuevo cliente con la información proporcionada en la solicitud.")
    @PostMapping(value = "crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteBean cliente){

        try{

            Cliente nuevoCliente = clienteService.crearCliente(cliente);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            responseAppBean.setCode(Constante.RESPONSE_OK);
            responseAppBean.setMessage("Cliente registrado satisfactoriamente");
            responseAppBean.setData(nuevoCliente);

            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

        }catch(Exception e){

            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping(value="editar", produces = "application/json")
    public  ResponseEntity<?> editarCliente(@Valid @RequestBody ClienteBean cliente){

        try{
            ResponseAppBean responseAppBean = clienteService.verificarExisteCliente(cliente.getIdCliente());
            if(responseAppBean.getCode() == "0"){
                responseAppBean.setStatus(Constante.RESPONSE_ERROR);
                responseAppBean.setMessage("Cliente no existe en la base de datos");
                return new ResponseEntity<>(responseAppBean, HttpStatus.OK);
            }

            Cliente nuevoCliente = clienteService.editarCliente(cliente);

            responseAppBean.setStatus(Constante.RESPONSE_OK);
            responseAppBean.setMessage("Cliente editado satisfactoriamente");
            responseAppBean.setData(nuevoCliente);
            responseAppBean.setCode(Constante.RESPONSE_OK);
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

        }catch(Exception e){

            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage(e.getMessage());
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="eliminar/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable("idCliente") long idCliente){

        try {

            clienteService.eliminarCliente(idCliente);
            responseAppBean.setStatus(Constante.RESPONSE_OK);
            responseAppBean.setMessage("Cliente eliminardo satisfactoriamente");
            return new ResponseEntity<>(responseAppBean, HttpStatus.OK);

        }catch(Exception e){
            responseAppBean.setStatus(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage("Error al eliminar cliente");
            return new ResponseEntity<>(responseAppBean, HttpStatus.BAD_REQUEST);
        }
    }
}
