package com.co.almundo.callcenter.testdatabuilder;

import com.co.almundo.callcenter.model.CargoEmpleado;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.EstadoEmpleado;

public class EmpleadoTestDataBuilder {
	
	private static final Long ID = 1L;
	private static final String EMAIL = "test@gmail.com";
	private static final EstadoEmpleado ESTADO = EstadoEmpleado.DISPONIBLE;
	private static final CargoEmpleado CARGO = CargoEmpleado.OPERADOR;
	
	private Long id;
	private String email;
	private EstadoEmpleado estado;
	private CargoEmpleado cargo;
	
	public EmpleadoTestDataBuilder() {
		this.id = ID;
		this.email = EMAIL;
		this.estado = ESTADO;
		this.cargo = CARGO;
	}
	
	public Empleado build() {
		Empleado empleado = new Empleado();
		empleado.setId(this.id);
		empleado.setEmail(this.email);
		empleado.setEstado(this.estado);
		empleado.setCargo(this.cargo);
		
		return empleado;
	}
	
	public EmpleadoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public EmpleadoTestDataBuilder conCargo(Long id, CargoEmpleado cargo) {
		this.id = id;
		this.cargo = cargo;
		return this;
		
	}

}
