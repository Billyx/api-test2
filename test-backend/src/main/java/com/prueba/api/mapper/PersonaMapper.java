package com.prueba.api.mapper;

import com.prueba.api.model.Persona;
import org.mapstruct.Mapper;
import com.prueba.api.bean.ClienteBean;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonaMapper {

    PersonaMapper MAPPER = Mappers.getMapper( PersonaMapper.class );
    ClienteBean PersonaToClienteBean(Persona persona);

    Persona ClienteBeanToPersona(ClienteBean clienteBean);

}
