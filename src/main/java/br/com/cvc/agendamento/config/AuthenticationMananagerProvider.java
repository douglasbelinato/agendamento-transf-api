package br.com.cvc.agendamento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Provedor personalizado para o authentication manager.
 * Necessário pois na versão 2 do SpringBoot, o Authentication Manager
 * não é gerado automaticamente pelo Spring, como era nas versões 1.X. 
 *
 */
@Configuration
public class AuthenticationMananagerProvider extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
