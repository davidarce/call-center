package com.co.almundo.callcenter.persistence;

import org.springframework.data.repository.CrudRepository;

import com.co.almundo.callcenter.model.Llamada;

public interface LlamadaRepository extends CrudRepository<Llamada, Long>{

}
