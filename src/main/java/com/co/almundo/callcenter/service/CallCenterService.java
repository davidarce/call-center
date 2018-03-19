package com.co.almundo.callcenter.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.co.almundo.callcenter.business.ProcesadorLlamada;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.model.LlamadaEmpleado;

/**
 * Clase service encargada de implementar la logica de negocio
 * del procesamiento de las operacioes del call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
@Service
@Transactional
public class CallCenterService {
	
	@Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    /**
     * Metodo encargado de invocar el procesador de llamadas
     * @param llamadaEmpleado, llamada asignada al empleado
     */
    public void procesarLlamada(LlamadaEmpleado llamadaEmpleado) {
		ProcesadorLlamada procesador = applicationContext.getBean(ProcesadorLlamada.class);
		procesador.setLlamadaEmpleado(llamadaEmpleado);
		taskExecutor.execute(procesador);
	}
   
   /**
    * Metodo encargado de actualizar el estado de una llamada 
    * @param llamada, llamada a actualizar
    */
    public void updateEstadoLlamada(Llamada llamada){
    		entityManager.merge(llamada); 		
    }
    
    /**
     * Metodo que registra la asignacion de la llamada a un 
     * empleado del call center
     * @param llamadaEmpleado, asignacion de la llamada
     */
	public void registrarLlamadaEmpleado(LlamadaEmpleado llamadaEmpleado) {
		entityManager.persist(llamadaEmpleado);
	
	}
    
	/**
	 * Metodo encargado de actualizar el estado de un empleado 
	 * @param empleado, empleado a actualizar
	 */
	public void updateEstadoEmpleado(Empleado empleado) {
		entityManager.merge(empleado);
	}

}
