package com.co.almundo.callcenter.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.persistence.EmpleadoRepository;
import com.co.almundo.callcenter.persistence.LlamadaRepository;
import com.co.almundo.callcenter.service.AsynchronousService;
import com.co.almundo.callcenter.util.Constants;

@RestController
public class Dispatcher {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);
	private Random random = new Random();
	
	@Autowired
    EmpleadoRepository empleadoRepository;
	
	@Autowired
	LlamadaRepository llamadaRepository;
	
	
	@Autowired
    private AsynchronousService anAsynchronousService;
	
	@RequestMapping("/generarLlamada")
	void dispatchCall(){
		
		//Almacena la llamada en BD
		System.out.println("---GENERANDO LLAMADA---");
		Llamada llamada = new Llamada();
		Long duracion = (long) (Constants.RANGO_LLAMADA + random.nextInt(Constants.RANGO_LLAMADA));
		llamada.setDuracion(duracion);
		llamadaRepository.save(llamada);
		System.out.println("---LLAMADA ALMACENADA---");
		//anAsynchronousService.procesarLlamada(llamada);
	}

}
