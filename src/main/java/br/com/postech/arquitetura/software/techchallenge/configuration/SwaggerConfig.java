package br.com.postech.arquitetura.software.techchallenge.configuration;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.com.postech.arquitetura.software.techchallenge.util.Constantes;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Glenio Montovani
 *
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Value("${version}")
	private String version;
	@Value("${api.url.authorization.swagger}")
	private String AUTH_SERVER;

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage(ParameterConfig.PACKAGE_CONTROLLERS))
						.paths(PathSelectors.any()).build()
				.ignoredParameterTypes(Principal.class)
				.securitySchemes(Collections.singletonList(securitySchema()))
				.securityContexts(Collections.singletonList(securityContext()))
				.apiInfo(apiInfo());
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(Constantes.PATH_VIEW_GENERAL_SWAGGER).addResourceLocations(Constantes.PATH_VIEW_SWAGGER);
		registry.addResourceHandler(Constantes.PATH_VIEW_GENERAL_IMAGES).addResourceLocations(Constantes.PATH_VIEW_IMAGES);
		registry.addResourceHandler(Constantes.PATH_VIEW_GENERAL_CHAT).addResourceLocations(Constantes.PATH_VIEW_CHAT);
	}

	@Bean
	public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()
	        .clientId(Constantes.CLIENTE_ID)
	        .clientSecret(Constantes.SECRET_CLIENTE_ID)
	        .scopeSeparator(Constantes.STRING_ESPACO)
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}

	private OAuth securitySchema() {        
        List<GrantType> grantTypes = new ArrayList<GrantType>();
        GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant(AUTH_SERVER);

        grantTypes.add(creGrant);

        return new OAuth(Constantes.SERVER_NAME, Arrays.asList(scopes()), grantTypes);
    }

	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope(Constantes.SCOPE_READ, Constantes.SCOPE_READ_DESCRIPTION), 
	      new AuthorizationScope(Constantes.SCOPE_WRITE, Constantes.SCOPE_WRITE_DESCRIPTION), 
	    };

	    return scopes;
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		return Collections.singletonList(new SecurityReference(Constantes.SERVER_NAME, scopes()));//oauth2schema
	}

	private ApiInfo apiInfo() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		apiInfoBuilder.title(Constantes.SERVER_NAME);// <style>.models{display: none !important}</style>
		apiInfoBuilder.description("API para entrega se serviço Restfull.");
		apiInfoBuilder.version(version);

		return apiInfoBuilder.build();
	}
}
