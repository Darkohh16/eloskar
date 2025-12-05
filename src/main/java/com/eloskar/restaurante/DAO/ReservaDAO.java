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

    public List<ReservaDTO> buscarReservasPorFiltros(String cliente, String estado, String fecha) {
        StringBuilder sql = new StringBuilder("SELECT r.idReser, r.usuario_id, r.fecha, r.hora, r.cantidad_personas, r.estado FROM reservas r INNER JOIN usuarios u ON r.usuario_id = u.idUser WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (cliente != null && !cliente.trim().isEmpty()) {
            sql.append(" AND u.nombre LIKE ?");
            params.add("%" + cliente.trim() + "%");
        }
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append(" AND r.estado = ?");
            params.add(estado.trim());
        }
        if (fecha != null && !fecha.trim().isEmpty()) {
            sql.append(" AND r.fecha = ?");
            params.add(fecha.trim());
        }
        sql.append(" ORDER BY r.fecha DESC, r.hora DESC");
        List<ReservaDTO> reservas = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
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
            throw new RuntimeException("Error al buscar reservas por filtros: " + ex.getMessage(), ex);
        }
        return reservas;
    }

    public int contarReservasHoy() {
        String sql = "SELECT COUNT(*) FROM reservas WHERE fecha = CONVERT(date, GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar reservas de hoy: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarReservasPendientes() {
        String sql = "SELECT COUNT(*) FROM reservas WHERE estado = 'pendiente'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar reservas pendientes: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarReservasConfirmadas() {
        String sql = "SELECT COUNT(*) FROM reservas WHERE estado = 'confirmada'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar reservas confirmadas: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarReservasMes() {
        String sql = "SELECT COUNT(*) FROM reservas WHERE MONTH(fecha) = MONTH(GETDATE()) AND YEAR(fecha) = YEAR(GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar reservas del mes: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarTotalPersonasHoy() {
        String sql = "SELECT SUM(cantidad_personas) FROM reservas WHERE fecha = CONVERT(date, GETDATE()) AND estado IN ('pendiente', 'confirmada')";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar total de personas hoy: " + ex.getMessage(), ex);
        }
        return 0;
    }
}
