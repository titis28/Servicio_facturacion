package com.facturacion.facturacion;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import com.fasterxml.jackson.annotation.*;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Factura {

    private @Id @GeneratedValue Long id_factura; //id de la factura 
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm") //Agrega la fecha y hora en formato "dd/MM/yyyy HH:mm"
    private LocalDateTime fecha; //fecha del ticket 
    private double subtotal; //cantidad sin IVA 
    private double IVA; //IVA = 16%
    private double total; //subtotal + IVA 

    @ElementCollection
    private List<DetalleFactura> productos = new ArrayList<>();

    public Factura(){
        this.fecha = LocalDateTime.now();
    }

    public void calcularTotales(){
        this.subtotal = productos.stream()
                .mapToDouble(DetalleFactura::getTotal)
                .sum();
        this.IVA = subtotal * 0.16;
        this.total = subtotal + IVA;
    }

    public void setProductos(List<DetalleFactura> productos){
        this.productos = productos;
        calcularTotales();
    }


    public Long getId_factura(){
        return this.id_factura;
    }

    public void setId_factura(Long id_factura){
        this.id_factura = id_factura;
    }

    public LocalDateTime getFecha(){
        return this.fecha;
    }

    public void setFecha(LocalDateTime fecha){
        this.fecha = fecha;
    }

    public double getSubtotal(){
        return this.subtotal;
    }

    public void setSubtotal(double subtotal){
        this.subtotal = subtotal;
    }

    public double getIVA(){
        return IVA;
    }

    public void setIVA(double IVA){
        this.IVA = IVA;
    }

    public double getTotal(){
        return total;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public List<DetalleFactura> getProductos() {
        return productos;
    }


    @Override
    public String toString(){
        return "Factura: {" + "id = " + this.id_factura +
                            ", fecha = " + this.fecha +
                            ", subtotal = " + this.subtotal +
                            ", IVA = " + this.IVA +
                            ", total = " + this.total + '}';   
    }
}
