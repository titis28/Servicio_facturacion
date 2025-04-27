package com.facturacion.facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class FacturacionApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(FacturacionApplication.class, args);

		FacturaRepository repo = context.getBean(FacturaRepository.class);

		DetalleFactura detalle = new DetalleFactura(2, "Cuaderno", 3, 22.5);
		Factura factura = new Factura();
			factura.setFecha(LocalDateTime.now());
		factura.setProductos(List.of(detalle));
		factura.calcularTotales();

		repo.save(factura);
		System.out.println("Factura creada desde main");
	}

	
    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI().info(new Info()
                            .title("Servicio de Facturación electrónica")
                            .description("Servicio creado por González Rojas Alma Cristina y Meza Hernández Emilio"));
    }

    /* Link para ingresar: http://localhost:8080/swagger-ui/index.html#/ */
}
