package com.co.almundo.callcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.co.almundo.callcenter.util.EstadoLlamada;

@Entity
@Table(name = "empleado", schema="SPRING_DATA_JPA_EXAMPLE")
public class Llamada {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Long duracion;
	
	@Enumerated(EnumType.STRING)
	private EstadoLlamada estado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	
	public Llamada() {
		this.estado = EstadoLlamada.EN_PROGRESO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDuracion() {
		return duracion;
	}

	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public EstadoLlamada getEstado() {
		return estado;
	}

	public void setEstado(EstadoLlamada estado) {
		this.estado = estado;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	

}
