package com.facturacion.facturacion;

public class FacturaNotFoundException extends RuntimeException {
    public FacturaNotFoundException(Long id) {
        super("No se encontró la factura con ID: " + id);
    }
}

