package com.eloskar.restaurante.DTO;

public class PedidoDetalleDTO {
    private int idDetPedid;
    private int pedido_id;
    private int plato_id;
    private int cantidad;
    private double precio_unitario;
    private String nombreProducto;

    public int getIdDetPedid() {
        return idDetPedid;
    }

    public void setIdDetPedid(int idDetPedid) {
        this.idDetPedid = idDetPedid;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getPlato_id() {
        return plato_id;
    }

    public void setPlato_id(int plato_id) {
        this.plato_id = plato_id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
