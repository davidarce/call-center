package com.co.almundo.callcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidad que representa un empleado
 * en el call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
@Entity
@Table(name = "empleado", schema="SPRING_DATA_JPA_EXAMPLE")
public class Empleado {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String email;
	
	@Enumerated(EnumType.STRING)
	private EstadoEmpleado estado;
	
	@Enumerated(EnumType.STRING)
	private CargoEmpleado cargo;
	
	public Empleado(){
		
	}
	
	public Empleado(EstadoEmpleado estado, CargoEmpleado cargo) {
		this.estado = estado;
		this.cargo = cargo;
	}
	
	/*
	 * getters and setters
	 */
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
		
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EstadoEmpleado getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoEmpleado estado) {
		this.estado = estado;
	}
	
	public CargoEmpleado getCargo() {
		return cargo;
	}
	
	public void setCargo(CargoEmpleado cargo) {
		this.cargo = cargo;
	}	

}
