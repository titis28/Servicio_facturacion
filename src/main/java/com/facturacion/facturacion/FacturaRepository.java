package com.facturacion.facturacion;

import org.springframework.data.jpa.repository.JpaRepository;

interface FacturaRepository extends JpaRepository<Factura, Long> {

}