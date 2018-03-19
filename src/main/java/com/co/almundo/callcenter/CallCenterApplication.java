package com.co.almundo.callcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.co.almundo.callcenter.controller.Dispatcher;

/**
 * Clase principal encargada de ejecutar la aplicacion
 * @author Oscar David Arce <davidarce2915@gmail.com>
 * @version 1.0
 *
 */
@SpringBootApplication
public class CallCenterApplication implements CommandLineRunner{
	
	@Autowired
	Dispatcher dispatcher;


	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
		 
	}
	
    @Override
    public void run(String... args) throws Exception {
    		dispatcher.run();
    }
	
}
