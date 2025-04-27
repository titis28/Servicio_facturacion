package com.facturacion.facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class FacturacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);
	}

	
    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI().info(new Info()
                            .title("Servicio de Facturación electrónica")
                            .description("Servicio creado por González Rojas Alma Cristina y Meza Hernández Emilio"));
    }

    /* Link para ingresar: http://localhost:8080/swagger-ui/index.html#/ */
}
