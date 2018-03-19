package com.co.almundo.callcenter.persistence.test;

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
import com.co.almundo.callcenter.persistence.EmpleadoRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TestApplicationConfiguration.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
public class EmpleadoRepositoryTest {
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Test
	public void whenBuscaUnEmpleadoDisponible_retornaUnOperador() {
		//arrange
		List<Empleado> empleadosDisponibles;
		
		//act
		empleadosDisponibles = empleadoRepository.listarEmpleadosDisponibles();
		
		//assert
		Assert.assertNotNull(empleadosDisponibles);
		Assert.assertFalse(empleadosDisponibles.isEmpty());
	}
	
	
	

}
