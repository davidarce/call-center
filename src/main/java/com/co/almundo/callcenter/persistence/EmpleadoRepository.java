package com.co.almundo.callcenter.persistence;

import org.springframework.data.repository.CrudRepository;

import com.co.almundo.callcenter.model.Empleado;

public interface EmpleadoRepository extends CrudRepository<Empleado, Long>  {

}
