package com.co.almundo.callcenter.service.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.almundo.callcenter.TestApplicationConfiguration;
import com.co.almundo.callcenter.model.Empleado;
import com.co.almundo.callcenter.model.EstadoEmpleado;
import com.co.almundo.callcenter.persistence.EmpleadoRepository;
import com.co.almundo.callcenter.service.CallCenterService;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TestApplicationConfiguration.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
public class CallCenterServiceTest {
	
	@Autowired
	private CallCenterService callCenterService;
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Test
	public void whenUnEmpleadoRecibeUnaLlamada_suEstadoEsOcupado() {
		//arrange
		String email = "operador1@gmail.com";
		Empleado empleado = empleadoRepository.findByEmail(email);
		empleado.setEstado(EstadoEmpleado.OCUPADO);
		
		//act
		callCenterService.updateEstadoEmpleado(empleado);
		List<Empleado> empleadosOcupados = empleadoRepository.listarEmpleadoOcupados();
		
		//assert
		Assert.assertFalse(empleadosOcupados.isEmpty());
		
	}

}
