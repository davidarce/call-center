package com.co.almundo.callcenter.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad que representa el registro de una llamada
 * asignada a un empleado
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
@Entity
@Table(name = "llamada_empleado", schema="SPRING_DATA_JPA_EXAMPLE")
public class LlamadaEmpleado {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "empleado_id")
	private Empleado empleado;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "llamada_id")
	private Llamada llamada;
	
	private Long duracion;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	
	public LlamadaEmpleado() {
	}

	public LlamadaEmpleado(Empleado empleado, Llamada llamada, Long duracion) {
		this.empleado = empleado;
		this.llamada = llamada;
		this.duracion = duracion;
		this.fecha = new Date();
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
	
	public Empleado getEmpleado() {
		return empleado;
	}
	
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public Llamada getLlamada() {
		return llamada;
	}
	
	public void setLlamada(Llamada llamada) {
		this.llamada = llamada;
	}
	
	public Long getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Long duracion) {
		this.duracion = duracion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}
