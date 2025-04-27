package com.facturacion.facturacion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
class FacturaController {

    private final FacturaRepository repository;
    private final ProductoRepository productoRepository;
    FacturaController(FacturaRepository repository, ProductoRepository productoRepository) {
        this.repository = repository;
        this.productoRepository = productoRepository;
    }

  //Obtener todas las facturas
  @GetMapping("/facturas")
  List<Factura> all() {
    return repository.findAll();
  }

    @PostMapping("/facturas")
    public Factura nuevaFactura(@RequestBody List<DetalleFactura> detallesEntrada) {
        List<DetalleFactura> detallesCompletos = new ArrayList<>();

        for (DetalleFactura entrada : detallesEntrada) {
            Producto producto = productoRepository.findById(entrada.getId_producto())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + entrada.getId_producto()));

            DetalleFactura detalle = new DetalleFactura(
                    producto.getId_producto(),
                    producto.getNombre_producto(),
                    entrada.getCantidad(),
                    producto.getPrecio_unitario()
            );

            detallesCompletos.add(detalle);
        }

        Factura factura = new Factura();
        factura.setFecha(LocalDateTime.now());
        factura.setProductos(detallesCompletos);
        factura.calcularTotales();

        return repository.save(factura);
    }


  
  //Obtener una factura por su ID
  @GetMapping("/facturas/{id}")
  Factura unFactura(@PathVariable Long id) {
    
    return repository.findById(id)
      .orElseThrow(() -> new FacturaNotFoundException(id));
  }

  //Actualizar una factura existente
  @PutMapping("/facturas/{id}")
  Factura reemplazarFactura(@RequestBody Factura nuevaFactura, @PathVariable Long id) {
    return repository.findById(id)
            .map(factura -> {
                factura.setProductos(nuevaFactura.getProductos()); // recalcula totales
                factura.setFecha(java.time.LocalDateTime.now());
                return repository.save(factura);
            })
            .orElseGet(() -> {
                nuevaFactura.setId_factura(id);
                nuevaFactura.setFecha(java.time.LocalDateTime.now());
                nuevaFactura.calcularTotales();
                return repository.save(nuevaFactura);
            });
  }
  // Eliminar una factura
  @DeleteMapping("/facturas/{id}")
  void eliminarFactura(@PathVariable Long id) {
    repository.deleteById(id);
  }
}
