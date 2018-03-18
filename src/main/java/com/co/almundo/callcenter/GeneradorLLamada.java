package com.co.almundo.callcenter;

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
	@Scheduled(fixedDelay = 1000, initialDelay = 2000)
    public void generarLLamada() {
		LOGGER.info("....GENERANDO LLAMADA....");
		Llamada llamada = new Llamada();
		LlamadaQueue.encolarLlamada(llamada);
		LOGGER.info("....LLAMADA ENCOLADA....");
		LOGGER.info("LLAMADAS EN ESPERA: " + LlamadaQueue.sizeQueue());
		
    }
}
