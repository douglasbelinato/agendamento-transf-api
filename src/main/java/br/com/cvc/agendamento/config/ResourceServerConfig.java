package br.com.cvc.agendamento.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Resource Server do oAuth2 para manipular as requests
 * autenticadas com um token válido.
 * 
 * Também utilizado para liberar URLs que não precisam passar
 * pela autenticação do oAuth2 (exemplo: URLs do H2 e Swagger-UI). 
 *
 */
@Configuration
@EnableWebSecurity
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui/**", "/swagger-resources/**",
						"/configuration/security/**", "/swagger-ui.html", "/webjars/**", "/h2-console/**")
				.permitAll()
				.anyRequest().authenticated().and().httpBasic().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
		
		http.headers().frameOptions().disable();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.stateless(true);
	}

}