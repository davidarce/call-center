package com.co.almundo.callcenter.config;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource createMainDataSource() {

		JdbcDataSource ds = new JdbcDataSource();
		ds.setURL("jdbc:h2:"+System.getProperty("java.io.tmpdir")+"/testdata");
		return ds;
	}
}