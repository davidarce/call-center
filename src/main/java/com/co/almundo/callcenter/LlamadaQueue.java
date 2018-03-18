package com.co.almundo.callcenter;

import java.util.LinkedList;
import java.util.Queue;

import com.co.almundo.callcenter.model.Llamada;

/**
 * Clase encargada de Almacenar las llamadas en una cola
 * para su posterior procesamiento
 * @author Oscar David Arce <davidarce2915@gmail.com>
 * @version 1.0
 */
public class LlamadaQueue {
	
	/*
	 * Instancia de la clase
	 */
	private static LlamadaQueue instance;
	
	/*
	 * Cola de llamadas
	 */
	private Queue<Llamada> queueLLamadas;
	
	private LlamadaQueue() {
		this.queueLLamadas = new LinkedList<>();
	}
	
	/**
	 * Metodo encargado de encolar la llamada en la lista de espera
	 * @param llamada, llamada que ha sido recibida
	 */
	public static void encolarLlamada(Llamada llamada){
		getInstance().queueLLamadas.add(llamada);
    }
	
	/**
	 * Metodo encargado de obtener de la lista de colas de llamadas
	 * para su procesamiento
	 * @return
	 */
	public static Llamada recibirLlamada(){
        return getInstance().queueLLamadas.poll();
    }
	
	/*
	 * Singleton instance
	 */
	public static LlamadaQueue getInstance() {
		if(instance == null) {
			instance = new LlamadaQueue();
		}
		return instance;
	}
	
	/*
	 * longitud de la cola de llamas
	 */
	public static int sizeQueue() {
		return getInstance().queueLLamadas.size();
	}
	

}
