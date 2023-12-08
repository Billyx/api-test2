package com.prueba.api.service;

import com.prueba.api.bean.ClienteBean;
import com.prueba.api.model.Cliente;
import com.prueba.api.model.Cuenta;
import com.prueba.api.model.Persona;
import com.prueba.api.repository.ClienteRepository;
import com.prueba.api.repository.PersonaRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ClienteServiceTest {

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    PersonaRepository personaRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void crearCliente() {
        Persona persona = new Persona();
        persona.setNombre("Pepe Suárez");
        persona.setGenero("MF");
        persona.setEdad(32);
        persona.setDireccion("Av. Los Cántaros 321, La Victoria");
        persona.setIdentificacion("42401990");
        persona.setTelefono("991839901");

        ClienteBean clienteBean = new ClienteBean();
        clienteBean.setIdCliente(1);
        clienteBean.setNombre(persona.getNombre());
        clienteBean.setEdad(persona.getEdad());
        clienteBean.setGenero(persona.getGenero());
        clienteBean.setIdentificacion(persona.getIdentificacion());
        clienteBean.setDireccion(persona.getDireccion());
        clienteBean.setTelefono(persona.getTelefono());
        clienteBean.setContrasena("123123123");
        clienteBean.setEstado(1);

        Cliente cliente = new Cliente();
        cliente.setIdCliente(clienteBean.getIdCliente());
        cliente.setPersona(persona);
        cliente.setContrasena(clienteBean.getContrasena());
        cliente.setEstado(clienteBean.getEstado());

        Mockito.when(clienteRepository.save(Mockito.any(Cliente.class))).thenReturn(cliente);
        Cliente nuevoCliente = clienteService.crearCliente(clienteBean);

        Mockito.verify(clienteRepository, Mockito.times(1)).save(cliente); // Estático

        // Pruebas parametrizadas
        // @Parametrized
        // Insertar valores @csvSource

        // Test Service (Pruebas unitarias)
        // Test Controlador (levantar el servicio)
        // Test para repository

        // Modelo por capas
        // Map struct - Conversion DTO a Model
        // Utilizar Gradle .yml
        // Scopes de los Beans (como lo maneja Spring)
        // por ejemplo Singleton, Request (creación de Beans)
        // Revisar @SuperBuilder
        // Mutation test - Verificacion de pruebas.
        // Complejidad en programación

        assertNotNull(nuevoCliente);

        assertEquals("42401990", nuevoCliente.getPersona().getIdentificacion());
    }
}