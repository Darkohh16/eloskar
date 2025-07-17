package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.ReservaDTO;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    public int insertReserva(ReservaDTO dto) {
        String sql = "INSERT INTO reservas (usuario_id, fecha, hora, cantidad_personas, estado) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getUsuario_id());
            pstm.setString(2, dto.getFecha());
            pstm.setString(3, dto.getHora());
            pstm.setInt(4, dto.getCantidad_personas());
            pstm.setString(5, dto.getEstado());
            int res = pstm.executeUpdate();
            if (res > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {
                    if (rs.next()) {
                        dto.setIdReser(rs.getInt(1));
                    }
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar reserva: " + ex.getMessage(), ex);
        }
    }

    public List<ReservaDTO> listarReservasPorUsuario(int usuarioId) {
        String sql = "SELECT idReser, usuario_id, fecha, hora, cantidad_personas, estado FROM reservas WHERE usuario_id = ? ORDER BY fecha DESC, hora DESC";
        List<ReservaDTO> reservas = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, usuarioId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    ReservaDTO dto = new ReservaDTO();
                    dto.setIdReser(rs.getInt("idReser"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setHora(rs.getString("hora"));
                    dto.setCantidad_personas(rs.getInt("cantidad_personas"));
                    dto.setEstado(rs.getString("estado"));
                    reservas.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar reservas por usuario: " + ex.getMessage(), ex);
        }
        return reservas;
    }

    public List<ReservaDTO> listarTodasReservas() {
        String sql = "SELECT idReser, usuario_id, fecha, hora, cantidad_personas, estado FROM reservas ORDER BY fecha DESC, hora DESC";
        List<ReservaDTO> reservas = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    ReservaDTO dto = new ReservaDTO();
                    dto.setIdReser(rs.getInt("idReser"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setHora(rs.getString("hora"));
                    dto.setCantidad_personas(rs.getInt("cantidad_personas"));
                    dto.setEstado(rs.getString("estado"));
                    reservas.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar todas las reservas: " + ex.getMessage(), ex);
        }
        return reservas;
    }

    public int actualizarEstado(int idReser, String nuevoEstado) {
        String sql = "UPDATE reservas SET estado = ? WHERE idReser = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, nuevoEstado);
            pstm.setInt(2, idReser);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar estado de reserva: " + ex.getMessage(), ex);
        }
    }

    public int eliminarReserva(int idReser) {
        String sql = "DELETE FROM reservas WHERE idReser = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idReser);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar reserva: " + ex.getMessage(), ex);
        }
    }
}
