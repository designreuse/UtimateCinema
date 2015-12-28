package com.cinema.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * AppConfig
 * Created by rayn on 2015/12/22.
 */

@Configuration
@ComponentScan("com.cinema")
@Import(PersistenceConfig.class)
@PropertySource(value = {"classpath:app.properties"})
public class AppConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
