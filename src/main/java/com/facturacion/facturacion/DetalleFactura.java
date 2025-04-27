package com.facturacion.facturacion;

import jakarta.persistence.Embeddable;;

@Embeddable
public class DetalleFactura {
    private int id_producto;
    private String nombre_producto;
    private int cantidad;
    private double precio_unitario;
    private double total;

    public DetalleFactura() {}

    public DetalleFactura(int id_producto, String nombre_producto, int cantidad, double precio_unitario) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.total = cantidad * precio_unitario;
    }

    private void calcularTotal(){
        this.total = this.precio_unitario * this.cantidad;
    }

    public int getId_producto(){
        return this.id_producto;
    }

    public void setId_producto(int id_producto){
        this.id_producto = id_producto;
    }

    public String getNombre_producto(){
        return this.nombre_producto;
    }

    public void setNombre_producto(String nombre_producto){
        this.nombre_producto = nombre_producto;
    }

    public int getCantidad(){
        return this.cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
       calcularTotal();
    }

    public double getPrecio_unitario(){
        return this.precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario){
        this.precio_unitario = precio_unitario;
        calcularTotal();
    }

    public double getTotal(){
        return this.total;
    }

    public void setTotal(double total){
        this.total = total;
    }

}
