package com.prueba.api.repository;

import com.prueba.api.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findPersonaByIdPersona(long idPersona);
}
