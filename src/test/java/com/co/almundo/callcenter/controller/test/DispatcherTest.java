package com.co.almundo.callcenter.controller.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.almundo.callcenter.TestApplicationConfiguration;
import com.co.almundo.callcenter.business.LlamadaQueue;
import com.co.almundo.callcenter.controller.Dispatcher;
import com.co.almundo.callcenter.model.CargoEmpleado;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.persistence.EmpleadoRepository;
import com.co.almundo.callcenter.persistence.LlamadaRepository;
import com.co.almundo.callcenter.service.CallCenterService;
import com.co.almundo.callcenter.testdatabuilder.EmpleadoTestDataBuilder;
import com.co.almundo.callcenter.testdatabuilder.LlamadaTestDataBuilder;
import com.co.almundo.callcenter.util.Constants;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
public class DispatcherTest {

	@TestConfiguration
	static class DispatcherTestContextConfiguration {
		@Bean
		public Dispatcher dispatcher() {
			return new Dispatcher();
		}
		
		@Bean
		public CallCenterService callCenterService() {
			return new CallCenterService();
		}
	}
	
	@Autowired
    private Dispatcher dispatcher;
	
	@MockBean
    private EmpleadoRepository empleadoRepository;
	
	@MockBean
    private LlamadaRepository llamadaRepository;
	
	@Mock
	private EntityManager entityManager;
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	@Before
	public void setUp() {
	    List<Empleado> empleadosDisponibles = listarEmpleados();
	    Mockito.when(empleadoRepository.listarEmpleadosDisponibles()).thenReturn(empleadosDisponibles);
	}
	
	
	@Test
	public void whenExisten10Llamadas_todasSonProcesadas() throws Exception {
		//arrange
		encolarLlamadas(10);
		
		//act
		dispatcher.setRunning(true);
		executor.execute(dispatcher);
		
		//assert
		Thread.sleep(10000);
		dispatcher.setRunning(false);
		Assert.assertTrue(LlamadaQueue.sizeQueue() == Constants.CERO);
		
	}
	
	@Test
	public void whenExisten20Llamadas_hayLlamadasEnEspera() throws Exception {
		//arrange
		encolarLlamadas(20);
		
		//act
		executor.execute(dispatcher);
		
		//assert
		Thread.sleep(5000);
		Assert.assertTrue(LlamadaQueue.sizeQueue() > Constants.CERO);
		
	}

	
	/*
	 * para propositos de test unitarios
	 */
	private void encolarLlamadas(int llamadas) {
		for (int i = 1; i <= llamadas; i++) {
			LlamadaQueue.encolarLlamada(new LlamadaTestDataBuilder().conId((long) i ).build());
		}
		
	}

	private List<Empleado> listarEmpleados() {
		List<Empleado> empleados = new ArrayList<>();
		Empleado temp;
		
		//se crean 6 operadores
		for (int i = 1; i <= 6; i++) {
			temp = new EmpleadoTestDataBuilder().conId((long)i).build();
			empleados.add(temp);
		}
		
		//se crean 3 supervisores
		for (int i = 7; i <= 9; i++) {
			temp = new EmpleadoTestDataBuilder().conCargo((long) i, CargoEmpleado.SUPERVISOR).build();
			empleados.add(temp);
		}
		
		empleados.add(new EmpleadoTestDataBuilder().conCargo((long)10, CargoEmpleado.DIRECTOR).build());
		return empleados;
	}
	
	
}
