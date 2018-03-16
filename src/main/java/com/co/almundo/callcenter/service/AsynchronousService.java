package com.co.almundo.callcenter.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.co.almundo.callcenter.ProcesadorLlamada;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.util.EstadoEmpleado;
import com.co.almundo.callcenter.util.EstadoLlamada;

@Service
public class AsynchronousService {
	
	@Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private ApplicationContext applicationContext;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    
    public Empleado buscarOperadorDisponible(){
    	return null;
    }
    
    public Llamada obtenerLlamadaEnEspera(){
    	return null;
    }
    
    public void updateEstadoLlamada(Llamada llamada, EstadoLlamada estadoLlamada){
    	
    }
    

	public void updateEstadoEmpleado(Empleado empleado, EstadoEmpleado disponible) {
		
	}
        
    
    
    public void procesarLlamada(Llamada llamada) {

    	/*
    	 * Busca un Empleado disponible
    	 * Crear llamada en BD
    	 * Actualizar estado de empleado A OCUPADO
    	 */
    	Empleado empleado = null;
    	
    	ProcesadorLlamada procesador = applicationContext.getBean(ProcesadorLlamada.class);
    	procesador.setLlamada(llamada);
    	procesador.setEmpleado(empleado);
        taskExecutor.execute(procesador);
        
        
        /*
         * Actualiza la llamada a EN_ESPERA en caso de no encontrar un empleado dispoible
         */
    }



}
