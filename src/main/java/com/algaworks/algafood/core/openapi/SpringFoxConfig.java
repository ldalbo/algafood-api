package com.algaworks.algafood.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.OAS_30)
                .select().apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
              //  .paths(PathSelectors.ant("/restaurantes/*"))
                .build()
                .apiInfo(apiInfo())
                .tags(new Tag("Cidades","Gerencia Cidades"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Api Algafood")
                .description("Api aberta para clientes e restaurantes")
                .version("1.0.0")
                .contact(new Contact("Alagaworks", "algaworks.com.br", "algaworks.com.br"))
                .build();
    }
}
