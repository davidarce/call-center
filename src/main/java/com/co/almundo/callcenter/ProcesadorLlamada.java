package com.co.almundo.callcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.service.AsynchronousService;
import com.co.almundo.callcenter.util.EstadoEmpleado;
import com.co.almundo.callcenter.util.EstadoLlamada;

@Component
@Scope("prototype")
public class ProcesadorLlamada implements Runnable{
	
	@Autowired
    private AsynchronousService checkAsyncService;
	private Llamada llamada;
	private Empleado empleado;

	@Override
	public void run() {
		
		/*
		 * Actualiza llamada a EN_PROGRESO
		 * Calcula la duracion de la llamada
		 * 
		 * 
		 */
		checkAsyncService.updateEstadoLlamada(llamada, EstadoLlamada.EN_PROGRESO);
		
		
		/*
		 * Actualiza el estado de la llamada a FINALIZADA
		 * Actualiza el estado del empleado a DISPONIBLE
		 */
		
		checkAsyncService.updateEstadoLlamada(llamada, EstadoLlamada.FINALIZADA);
		checkAsyncService.updateEstadoEmpleado(empleado, EstadoEmpleado.DISPONIBLE);
		
	}
	
	

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}



	public Llamada getLlamada() {
		return llamada;
	}

	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}

}
