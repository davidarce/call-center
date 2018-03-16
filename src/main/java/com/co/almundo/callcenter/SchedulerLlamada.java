package com.co.almundo.callcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.service.AsynchronousService;

@Component
public class SchedulerLlamada {
	
	@Autowired
    private AsynchronousService checkAsyncService;
	
	@Scheduled(fixedDelay = 10000)
    public void checkTheScedule() {
		
		/*
		 * Consulta la siguiente llamada en estado EN_ESPERA
		 */
		//Llamada llamada = null;
		//checkAsyncService.procesarLlamada(llamada);
    }

}
