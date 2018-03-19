package com.co.almundo.callcenter.controller;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.co.almundo.callcenter.business.LlamadaQueue;
import com.co.almundo.callcenter.model.CargoEmpleado;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.model.LlamadaEmpleado;
import com.co.almundo.callcenter.persistence.EmpleadoRepository;
import com.co.almundo.callcenter.persistence.LlamadaRepository;
import com.co.almundo.callcenter.service.CallCenterService;
import com.co.almundo.callcenter.util.Constants;
import com.co.almundo.callcenter.util.MessageEvent;

/**
 * Clase encargarda de manejar las llamadas que llegan 
 * para asiganarlas a un empleado disponible
 * @author Oscar David Arce <davidarce2915@gmail.com>
 * @version 1.0
 *
 */
@Component
public class Dispatcher implements Runnable, ApplicationListener<MessageEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Dispatcher.class);
	
	private Random random = new Random();
	
	private List<Empleado> empleadosDisponibles;
	
	private boolean running;
	
	@Autowired
    EmpleadoRepository empleadoRepository;
	
	@Autowired
	LlamadaRepository llamadaRepository;
	

	@Autowired
    private CallCenterService callCenterService;
	
	/**
	 * Metodo encargado de asignar la llamada a un empleado disponible en el call center
	 * @param empleado, empleado que recibe la llamada
	 * @param llamada, llamada recibida
	 */
	public void dispatchCall(Empleado empleado, Llamada llamada){
		//Se almance la llamadaen BD para llevar un registro de estas
		LOGGER.debug("---RECIBIENDO LLAMADA---");
		llamadaRepository.save(llamada);
		LOGGER.debug("---LLAMADA ID:" + llamada.getId() + " ALMACENADA---");
		
		//Se calcula la duracion de la llamada en un rango de 5 a 10 segundos
		Long duracion = (long) (Constants.RANGO_LLAMADA + random.nextInt(Constants.RANGO_LLAMADA));
		
		//Se crea la asignacion de la llamada al empleado disponible y se envia a procesar
		LlamadaEmpleado llamadaEmpleado = new LlamadaEmpleado(empleado, llamada, duracion);
		callCenterService.procesarLlamada(llamadaEmpleado);
		
		LOGGER.debug("---EMPLEADOS DISPONIBLES---" + empleadosDisponibles.size());
	}

	/**
	 * Metodo run encargado de verificar constantemente la lista de llamadas que van
	 * llegando al call center
	 */
	@Override
	public void run() {
		listarEmpleados();
		Empleado empleadoDisponible = null;
		Llamada llamada = null;
		
		while (running) {
			try {
				//Se valida si hay empleados disponibles para recibir llamadas
				if(!empleadosDisponibles.isEmpty()) {
					llamada = LlamadaQueue.recibirLlamada();
					if(llamada != null) {
						empleadoDisponible = buscarEmpleadoDisponible();
						//Si encuentra un empleado disponible se asigna la llamada
						dispatchCall(empleadoDisponible, llamada);
					}					
				}else {
					//En caso de no encontrar empleados disponinles se encolan las llamadas en estado de espera
					LOGGER.debug("---NO HAY EMPLEADOS DISPONIBLES, ESPERE UN MOMENTO---");
					Thread.sleep(2000);
				}
			} catch (InterruptedException e) {
				LOGGER.error("Error dispatcher asignando llamada");
			}
		}
	}
	
	/**
	 * Metodo que consulta los empleados disponibles 
	 * @return List<Empleado>, lista de empleados disponibles
	 */
	private void listarEmpleados(){
		empleadosDisponibles = Collections.synchronizedList(empleadoRepository.listarEmpleadosDisponibles());
	}
	
	/**
	 * Metodo encargado de buscar un empleado disponible
	 * para atender una llamada
	 * @return Empleado, el empleado que se encuentra disponible
	 */
	private Empleado buscarEmpleadoDisponible() {
		Empleado empleado = null;

		//Se busca de la lista de empleados algun operador disponible
		List<Empleado> operadores = filtrarEmpleadoPorCargo(CargoEmpleado.OPERADOR);

		if(operadores != null && !operadores.isEmpty()){
			empleado = operadores.get(0);
			this.empleadosDisponibles.remove(empleado);
			log(empleado);
		}else {
			LOGGER.info("operadores no disponibles, buscando SUPERVISOR");
			//En caso de no encontrar operador disponible se buscar un supervisor
			List<Empleado> supervisores = filtrarEmpleadoPorCargo(CargoEmpleado.SUPERVISOR);
			
			if(supervisores != null && !supervisores.isEmpty()) {
				empleado = supervisores.get(0);
				this.empleadosDisponibles.remove(empleado);
				log(empleado);
			}else {
				LOGGER.info("supervisores no disponibles, buscando DIRECTOR");
				//Si no hay operadores y supervisores disponibles se pasa la llamada a un director
				List<Empleado> directores = filtrarEmpleadoPorCargo(CargoEmpleado.DIRECTOR);
				
				if(directores != null && !directores.isEmpty()) {
					empleado = directores.get(0);
					this.empleadosDisponibles.remove(empleado);
					log(empleado);
				}
			}
			
		}
		return empleado;
	}	
	
	/*
	 * Metodo encargado de filtrar la lista de emplados segun su cargo
	 */
	private List<Empleado> filtrarEmpleadoPorCargo(CargoEmpleado cargo) {
		return this.empleadosDisponibles.stream().filter(
				employee -> (employee.getCargo().equals(cargo))).collect(Collectors.toList());
	}
	
	/*
	 * log de empleado disponible encontrado
	 */
	private void log(Empleado empleado) {
		LOGGER.info("empleado encontrado con ID: "+empleado.getId()+" cargo: "+empleado.getCargo() + ""
				+ " employees availables: "+ this.empleadosDisponibles.size());
	}

	/**
	 * Evento que se dispara cuando un empleado finaliza una llamada y se
	 * encuentra disponible para recibir otra
	 * 
	 */
	@Override
	public void onApplicationEvent(MessageEvent event) {
		this.empleadosDisponibles.add(event.getEmpleado());
		
	}
	
	/*
	 * para propositos de test
	 */

	public void setRunning(boolean running) {
		this.running = running;
	}

}
