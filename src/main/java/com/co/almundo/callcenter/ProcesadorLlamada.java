package com.co.almundo.callcenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.EstadoEmpleado;
import com.co.almundo.callcenter.model.EstadoLlamada;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.model.LlamadaEmpleado;
import com.co.almundo.callcenter.service.CallCenterService;
import com.co.almundo.callcenter.util.Constants;
import com.co.almundo.callcenter.util.MessageEvent;

/**
 * Componete encargado de procesar la llamada
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
@Component
@Scope("prototype")
public class ProcesadorLlamada implements Runnable{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcesadorLlamada.class);
	
	@Autowired
    private CallCenterService checkAsyncService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private LlamadaEmpleado llamadaEmpleado;

	/**
	 * Metodo que procesa la llamada asignada al empleado disponible
	 * aqui se realiza la simulacion de la duracion de la llamada y
	 * se finaliza el proceso
	 */
	@Override
	public void run() {
		try {
			Llamada llamadaActual = llamadaEmpleado.getLlamada();
			Empleado empleado = llamadaEmpleado.getEmpleado();
			/*
			 * Actualiza llamada a EN_PROGRESO
			 * Calcula la duracion de la llamada
			 */
			LOGGER.info("LLAMADA ASIGNADA A EMPLEADO email: " + llamadaEmpleado.getEmpleado().getEmail());
			empleado.setEstado(EstadoEmpleado.OCUPADO);
			checkAsyncService.updateEstadoEmpleado(empleado);
			
			LOGGER.info("ACTUALIZANDO LLAMADA ID: " + llamadaActual.getId() + "... EN PROGRESO");
			llamadaActual.setEstado(EstadoLlamada.EN_PROGRESO);
			checkAsyncService.updateEstadoLlamada(llamadaActual);

			//Se simula el tiempo de la llamada en el proceso
			Thread.sleep(llamadaEmpleado.getDuracion() * Constants.MILISECONDS);
			LOGGER.info("REGISTRANDO LLAMADA_EMPLEADO: " + llamadaActual.getId());
			checkAsyncService.registrarLlamadaEmpleado(llamadaEmpleado);

			/*
			 * Actualiza el estado de la llamada a FINALIZADA
			 */
			LOGGER.info("ACTUALIZANDO LLAMADA ID: " + llamadaActual.getId() + "... FINALIZADA");
			llamadaActual.setEstado(EstadoLlamada.FINALIZADA);
			checkAsyncService.updateEstadoLlamada(llamadaEmpleado.getLlamada());
			
			LOGGER.info("LLAMADA FINALIZADA EMPLEADO email: " + empleado.getEmail());
			empleado.setEstado(EstadoEmpleado.DISPONIBLE);
			checkAsyncService.updateEstadoEmpleado(empleado);
			applicationEventPublisher.publishEvent(new MessageEvent(this, empleado));
			
		} catch (InterruptedException e) {
			LOGGER.error("Error procesando llamada id: " + llamadaEmpleado.getLlamada().getId());
		}
		
	}

	/*
	 * getter and setter
	 * 
	 */
	
	public LlamadaEmpleado getLlamadaEmpleado() {
		return llamadaEmpleado;
	}

	public void setLlamadaEmpleado(LlamadaEmpleado llamadaEmpleado) {
		this.llamadaEmpleado = llamadaEmpleado;
	}
	

}
