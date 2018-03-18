package com.co.almundo.callcenter.persistence;

import org.springframework.data.repository.CrudRepository;

import com.co.almundo.callcenter.model.Llamada;

/**
 * Repository para manejar las operaciones CRUS a nivel de BD de las lamadas del call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
public interface LlamadaRepository extends CrudRepository<Llamada, Long>{

}
