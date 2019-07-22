package br.com.cvc.agendamento.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ClientCredentialsGrant;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de configuração do Swagger para documentação do serviços REST.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Value("${api.oauth2.clientId}")
	private String clientId;
	
	@Value("${api.oauth2.clientSecret}")
	private String clientSecret;
	
	@Value("${api.oauth2.host}")
	private String host;
	
	@Value("${server.port}")
	private String serverPort;
	
	@Value("${api.oauth2.tokenUri}")
	private String tokenUrl;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.cvc"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securitySchema()))
                .securityContexts(Collections.singletonList(securityContext()))
                .pathMapping("/")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
    }
    
    /**
	 * Especifica tipo de autenticação que o serviço usa (nesse caso oAuth2 com grant type ClientCredentialsGrant) 
	 * 
	 * @return
	 */
	private OAuth securitySchema() {
		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
        authorizationScopeList.add(new AuthorizationScope("read", "read all"));
        authorizationScopeList.add(new AuthorizationScope("trust", "trust all"));
        authorizationScopeList.add(new AuthorizationScope("write", "access all"));

        List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ClientCredentialsGrant(host + serverPort + tokenUrl);
		
		grantTypes.add(creGrant);

        return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
	}

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/**"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope("read", "read all");
        authorizationScopes[1] = new AuthorizationScope("trust", "trust all");
        authorizationScopes[2] = new AuthorizationScope("write", "write all");

        return Collections.singletonList(new SecurityReference("oauth2schema", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("agendamento-transf-api")
                .description("API de agendamentos de transferências")
                .version("v1")
                .license("CVC")
                .licenseUrl("")
                .contact(new Contact("Equipe CVC", "", "equipecvc@cvc.com.br"))
                .build();
    }
    
    @Bean
    public SecurityConfiguration securityInfo() {
		return new SecurityConfiguration(clientId, clientSecret, "", "", " ", null, true);
    }
}
