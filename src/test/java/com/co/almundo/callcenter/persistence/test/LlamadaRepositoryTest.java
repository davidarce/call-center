package com.co.almundo.callcenter.persistence.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.co.almundo.callcenter.TestApplicationConfiguration;
import com.co.almundo.callcenter.model.Llamada;
import com.co.almundo.callcenter.persistence.LlamadaRepository;
import com.co.almundo.callcenter.testdatabuilder.LlamadaTestDataBuilder;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = TestApplicationConfiguration.class)
@TestPropertySource(properties = "app.scheduling.enable=false")
public class LlamadaRepositoryTest {
	
	@Autowired
	private LlamadaRepository llamadaRepository;
	
	@Test
	public void whenSeGeneraUnaLlamada_debeAlmacenarce() {
		//arrange
		Llamada llamada = new LlamadaTestDataBuilder().conId(null).build();
		llamadaRepository.save(llamada);
		
		//act
		Optional<Llamada> found = llamadaRepository.findById(llamada.getId());
		
		//assert
		Assert.assertTrue(found.isPresent());
		Assert.assertEquals(llamada.getId(), found.get().getId());
	}

}
