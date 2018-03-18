package com.co.almundo.callcenter.model;

import com.co.almundo.callcenter.util.CargoEmpleado;
import com.co.almundo.callcenter.util.EstadoEmpleado;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


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
	
	@OneToMany(cascade = CascadeType.ALL,
	            fetch = FetchType.LAZY,
	            mappedBy = "empleado")
	private Set<Llamada> llamadas = new HashSet<>();
	
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

	public Set<Llamada> getLlamadas() {
		return llamadas;
	}

	public void setllamadas(Set<Llamada> llamadas) {
		this.llamadas = llamadas;
	}
	
	

}
