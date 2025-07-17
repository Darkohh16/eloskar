package com.eloskar.restaurante.DTO;

public class ReservaDTO {
    private int idReser;
    private int usuario_id;
    private String fecha; // formato yyyy-MM-dd
    private String hora;  // formato HH:mm:ss
    private int cantidad_personas;
    private String estado;

    public int getIdReser() {
        return idReser;
    }

    public void setIdReser(int idReser) {
        this.idReser = idReser;
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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidad_personas() {
        return cantidad_personas;
    }

    public void setCantidad_personas(int cantidad_personas) {
        this.cantidad_personas = cantidad_personas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
