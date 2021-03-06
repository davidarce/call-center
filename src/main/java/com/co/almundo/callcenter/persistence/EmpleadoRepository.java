package com.co.almundo.callcenter.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.co.almundo.callcenter.model.Empleado;

/**
 * Repository para manejar las operaciones CRUD a nivel de bd de los empleados del call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
public interface EmpleadoRepository extends CrudRepository<Empleado, Long>  {
	
	@Query("SELECT e FROM Empleado e where e.estado = 'DISPONIBLE'")
	List<Empleado> listarEmpleadosDisponibles();
	
	@Query("SELECT e FROM Empleado e where e.estado = 'DISPONIBLE' and e.email = :email") 
	Empleado findByEmail(@Param("email") String email);
	
	@Query("SELECT e FROM Empleado e where e.estado = 'OCUPADO'")
	List<Empleado> listarEmpleadoOcupados();
	

}
