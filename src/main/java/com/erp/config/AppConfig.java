package com.erp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class AppConfig {

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setCacheable(true);
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(this.templateResolver());
		engine.setEnableSpringELCompiler(true);
		return engine;
	}

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(this.templateEngine());
		return viewResolver;
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
		return databaseBuilder
			.setName("customerdb")
			.setScriptEncoding("UTF-8")
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:/customer_table.sql").build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource());
		return jdbcTemplate;
	}
}
