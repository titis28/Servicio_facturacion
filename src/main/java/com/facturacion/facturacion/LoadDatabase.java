package com.facturacion.facturacion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(FacturaRepository repository) {
  
      return args -> {
        Factura factura1 = new Factura();
            factura1.setFecha(LocalDateTime.now());

            DetalleFactura p1 = new DetalleFactura(101, "Mouse inalámbrico", 1, 300.0);
            DetalleFactura p2 = new DetalleFactura(102, "Teclado mecánico", 1, 1200.0);

            factura1.setProductos(List.of(p1, p2));
            factura1.calcularTotales();
            log.info("Precarga " + repository.save(factura1));
      };
    }
}