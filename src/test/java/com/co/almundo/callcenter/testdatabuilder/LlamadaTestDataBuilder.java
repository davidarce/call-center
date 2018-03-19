package com.co.almundo.callcenter.testdatabuilder;

import com.co.almundo.callcenter.model.EstadoLlamada;
import com.co.almundo.callcenter.model.Llamada;

public class LlamadaTestDataBuilder {
	
	private static final Long ID = 1L;
	private static final EstadoLlamada ESTADO = EstadoLlamada.EN_ESPERA;

	private Long id;
	private EstadoLlamada estado;
	
	public LlamadaTestDataBuilder() {
		this.id = ID;
		this.estado = ESTADO;
	}
	
	public Llamada build() {
		Llamada llamada = new Llamada();
		llamada.setId(id);
		llamada.setEstado(estado);
		
		return llamada;
	}

	public LlamadaTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
}
