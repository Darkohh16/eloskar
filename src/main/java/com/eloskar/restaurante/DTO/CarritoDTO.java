package com.eloskar.restaurante.DTO;

import java.util.List;

public class CarritoDTO {
    private  int idCarrito;
    private int usuario_id;
    private List<CarritoDetalleDTO> detalles;

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public List<CarritoDetalleDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CarritoDetalleDTO> detalles) {
        this.detalles = detalles;
    }
}
