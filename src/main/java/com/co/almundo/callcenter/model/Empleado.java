package com.co.almundo.callcenter.model;

import com.co.almundo.callcenter.util.CargoEmpleado;
import com.co.almundo.callcenter.util.EstadoEmpleado;

import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "empleado", schema="SPRING_DATA_JPA_EXAMPLE")
public class Empleado {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EstadoEmpleado estado;
	
	@Enumerated(EnumType.STRING)
	private CargoEmpleado cargo;
	
	@OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL)
	private Set<Llamada> llamadas;
	
	public Empleado(){
		
	}
	
	public Empleado(EstadoEmpleado estado, CargoEmpleado cargo) {
		this.estado = estado;
		this.cargo = cargo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Set<Llamada> getLlamadas() {
		return llamadas;
	}

	public void setllamadas(Set<Llamada> llamadas) {
		this.llamadas = llamadas;
	}
	
	

}
