package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.ReservaDAO;
import com.eloskar.restaurante.DTO.ReservaDTO;

import java.util.List;

public class ReservaService {
    private ReservaDAO reservaDAO;

    public ReservaService() {
        this.reservaDAO = new ReservaDAO();
    }

    public boolean insertarReserva(ReservaDTO dto) {
        dto.setUsuario_id(dto.getUsuario_id());
        dto.setFecha(dto.getFecha());
        dto.setHora(dto.getHora());
        dto.setCantidad_personas(dto.getCantidad_personas());
        dto.setEstado(dto.getEstado());
        return reservaDAO.insertReserva(dto) > 0;
    }

    public List<ReservaDTO> listarReservasPorUsuario(int usuarioId) {
        return reservaDAO.listarReservasPorUsuario(usuarioId);
    }

    public List<ReservaDTO> listarTodasReservas() {
        return reservaDAO.listarTodasReservas();
    }

    public boolean actualizarEstado(int idReser, String nuevoEstado) {
        return reservaDAO.actualizarEstado(idReser, nuevoEstado) > 0;
    }

    public boolean eliminarReserva(int idReser) {
        return reservaDAO.eliminarReserva(idReser) > 0;
    }
}
