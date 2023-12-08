package com.prueba.api.service;

import com.prueba.api.bean.ClienteBean;
import com.prueba.api.bean.ResponseAppBean;
import com.prueba.api.model.Cliente;
import com.prueba.api.model.Persona;
import com.prueba.api.repository.ClienteRepository;
import com.prueba.api.repository.PersonaRepository;
import com.prueba.api.util.Constante;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.prueba.api.mapper.PersonaMapper;
import com.prueba.api.mapper.PersonaMapper;

@Service
public class ClienteService {


    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;
    //private final PersonaMapper personaMapper;

    public ClienteService(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
        //this.personaMapper = personaMapper;
    }

    @Transactional
    public Cliente crearCliente(ClienteBean clienteBean){

        Persona persona = new Persona();

        Persona newPersona = this.guardarPersona(persona,clienteBean);

        Cliente newCliente = this.guardarCliente(newPersona, clienteBean, null);

        return clienteRepository.save(newCliente);
    }

    @Transactional
    public Cliente editarCliente(ClienteBean clienteBean){
        Cliente cliente =  clienteRepository.findClienteByIdCliente(clienteBean.getIdCliente());
        Persona personaEditada = this.guardarPersona(cliente.getPersona(), clienteBean);
        Cliente clienteEditado = this.guardarCliente(cliente.getPersona(), clienteBean, cliente.getIdCliente());
        return clienteEditado;
    }

    @Transactional
    public void eliminarCliente(Long idCliente){
        Cliente cliente = clienteRepository.findClienteByIdCliente(idCliente);
        clienteRepository.delete(cliente);
        Persona persona = personaRepository.findPersonaByIdPersona(cliente.getPersona().getIdPersona());
        personaRepository.delete(persona);
    }


    public Persona guardarPersona(Persona persona, ClienteBean clienteBean){

        /*Persona npersona = personaMapper.ClienteBeanToPersona(clienteBean);
        if(  !String.valueOf(persona.getIdPersona()).equals(null))
            npersona.setIdPersona(persona.getIdPersona()); */

        Persona personax = PersonaMapper.MAPPER.ClienteBeanToPersona( clienteBean );

        persona.setNombre(clienteBean.getNombre());
        persona.setGenero(clienteBean.getGenero());
        persona.setEdad(clienteBean.getEdad());
        persona.setIdentificacion(clienteBean.getIdentificacion());
        persona.setDireccion(clienteBean.getDireccion());
        persona.setTelefono(clienteBean.getTelefono());


        return personaRepository.save(persona);
    }

    public Cliente guardarCliente(Persona persona, ClienteBean clientebean, Long idCliente){

        Cliente cliente;

        if(idCliente != null){
            cliente = clienteRepository.findClienteByIdCliente(idCliente);
        }else{
            cliente = new Cliente();
        }

        cliente.setContrasena(clientebean.getContrasena());
        cliente.setEstado(clientebean.getEstado());
        cliente.setPersona(persona);
        return clienteRepository.save(cliente);
    }

    public ResponseAppBean verificarExisteCliente(long idCliente){

        ResponseAppBean responseAppBean = new ResponseAppBean();
        Cliente cliente = clienteRepository.findClienteByIdCliente(idCliente);

        if(cliente == null){
            responseAppBean.setCode(Constante.RESPONSE_ERROR);
            responseAppBean.setMessage("Error: Cliente no existe en la base de datos");
            responseAppBean.setData("ID Cliente: "+idCliente);

        }else{
            responseAppBean.setCode(Constante.RESPONSE_OK);
        }
        return responseAppBean;
    }

    public Cliente obtenerClientePorId(long idCliente){
        return clienteRepository.findClienteByIdCliente(idCliente);
    }


}
