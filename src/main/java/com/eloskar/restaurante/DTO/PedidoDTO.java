package com.eloskar.restaurante.DTO;

import java.util.List;

public class PedidoDTO {
    private int idPedid;
    private int usuario_id;
    private String fecha; // DATETIME2 como String
    private double total;
    private String estado;
    private int metodo_pago_id;
    private String tipo_entrega;
    private String direccion;
    private List<PedidoDetalleDTO> detalles;

    public int getIdPedid() {
        return idPedid;
    }

    public void setIdPedid(int idPedid) {
        this.idPedid = idPedid;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMetodo_pago_id() {
        return metodo_pago_id;
    }

    public void setMetodo_pago_id(int metodo_pago_id) {
        this.metodo_pago_id = metodo_pago_id;
    }

    public String getTipo_entrega() {
        return tipo_entrega;
    }

    public void setTipo_entrega(String tipo_entrega) {
        this.tipo_entrega = tipo_entrega;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<PedidoDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<PedidoDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
