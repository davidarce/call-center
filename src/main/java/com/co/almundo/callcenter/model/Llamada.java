package com.co.almundo.callcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entidad que representa una llamada recibida 
 * en el call center
 * @author Oscar David Arce <davidarce2915@gmail.com>
 *
 */
@Entity
@Table(name = "llamada", schema="SPRING_DATA_JPA_EXAMPLE")
public class Llamada {
	
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SequenceGenerator(name="llamada_generator", sequenceName = "llamada_seq", allocationSize=1)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private EstadoLlamada estado;
	
	
	public void llamada() {
		
	}
	
	public Llamada() {
		this.estado = EstadoLlamada.EN_ESPERA;
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

	public EstadoLlamada getEstado() {
		return estado;
	}

	public void setEstado(EstadoLlamada estado) {
		this.estado = estado;
	}	

}
