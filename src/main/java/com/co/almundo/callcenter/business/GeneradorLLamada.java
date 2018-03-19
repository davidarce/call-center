package com.co.almundo.callcenter.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.co.almundo.callcenter.controller.Dispatcher;
import com.co.almundo.callcenter.model.Llamada;

/**
 * Componente utilizado para simular la generacion de
 * llamadas al call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 * @version 1.0
 */
@Component
public class GeneradorLLamada {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);
	
	/**
	 * Metodo que genera una llamada cada n tiempo configurado
	 */
	@Scheduled(fixedDelay = 2000)
    public void generarLLamada() {
		LOGGER.debug("....GENERANDO LLAMADA....");
		Llamada llamada = new Llamada();
		LlamadaQueue.encolarLlamada(llamada);
		LOGGER.debug("....LLAMADA ENCOLADA....");
		LOGGER.debug("LLAMADAS EN ESPERA: " + LlamadaQueue.sizeQueue());
		
    }
}
