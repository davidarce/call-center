package com.co.almundo.callcenter.util;

import org.springframework.context.ApplicationEvent;

import com.co.almundo.callcenter.model.Empleado;

/**
 * Event personalizado para notificar disponibilidad de un empleado
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
public class MessageEvent extends ApplicationEvent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Empleado empleado;
	
	public MessageEvent(Object source, Empleado empleado) {
		super(source);
		this.empleado = empleado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}
	
}
