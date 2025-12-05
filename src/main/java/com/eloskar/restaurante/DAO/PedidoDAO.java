package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.PedidoDTO;
import com.eloskar.restaurante.DTO.PedidoDetalleDTO;
import com.eloskar.restaurante.util.PoolConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public int insertPedido(PedidoDTO dto) {
        String sql = "INSERT INTO pedidos (usuario_id, fecha, total, estado, metodo_pago_id, tipo_entrega, direccion) VALUES (?, SYSDATETIME(), ?, ?, ?, ?, ?)";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getUsuario_id());
            pstm.setDouble(2, dto.getTotal());
            pstm.setString(3, dto.getEstado());
            pstm.setInt(4, dto.getMetodo_pago_id());
            pstm.setString(5, dto.getTipo_entrega());
            pstm.setString(6, dto.getDireccion());
            int res = pstm.executeUpdate();
            if (res > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {
                    if (rs.next()) {
                        dto.setIdPedid(rs.getInt(1));
                    }
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar pedido: " + ex.getMessage(), ex);
        }
    }

    public List<PedidoDTO> listarPedidosPorUsuario(int usuarioId) {
        String sql = "SELECT idPedid, usuario_id, fecha, total, estado, metodo_pago_id, tipo_entrega, direccion FROM pedidos WHERE usuario_id = ? ORDER BY fecha DESC";
        List<PedidoDTO> pedidos = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, usuarioId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setIdPedid(rs.getInt("idPedid"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setTotal(rs.getDouble("total"));
                    dto.setEstado(rs.getString("estado"));
                    dto.setMetodo_pago_id(rs.getInt("metodo_pago_id"));
                    dto.setTipo_entrega(rs.getString("tipo_entrega"));
                    dto.setDireccion(rs.getString("direccion"));
                    pedidos.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar pedidos por usuario: " + ex.getMessage(), ex);
        }
        return pedidos;
    }

    public List<PedidoDTO> listarTodosPedidos() {
        String sql = "SELECT idPedid, usuario_id, fecha, total, estado, metodo_pago_id, tipo_entrega, direccion FROM pedidos ORDER BY fecha DESC";
        List<PedidoDTO> pedidos = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setIdPedid(rs.getInt("idPedid"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setTotal(rs.getDouble("total"));
                    dto.setEstado(rs.getString("estado"));
                    dto.setMetodo_pago_id(rs.getInt("metodo_pago_id"));
                    dto.setTipo_entrega(rs.getString("tipo_entrega"));
                    dto.setDireccion(rs.getString("direccion"));
                    pedidos.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar todos los pedidos: " + ex.getMessage(), ex);
        }
        return pedidos;
    }

    public PedidoDTO buscarPedidoPorId(int idPedid) {
        String sql = "SELECT idPedid, usuario_id, fecha, total, estado, metodo_pago_id, tipo_entrega, direccion FROM pedidos WHERE idPedid = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idPedid);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setIdPedid(rs.getInt("idPedid"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setTotal(rs.getDouble("total"));
                    dto.setEstado(rs.getString("estado"));
                    dto.setMetodo_pago_id(rs.getInt("metodo_pago_id"));
                    dto.setTipo_entrega(rs.getString("tipo_entrega"));
                    dto.setDireccion(rs.getString("direccion"));
                    return dto;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar pedido por ID: " + ex.getMessage(), ex);
        }
        return null;
    }

    public int actualizarEstado(int idPedid, String nuevoEstado) {
        String sql = "UPDATE pedidos SET estado = ? WHERE idPedid = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, nuevoEstado);
            pstm.setInt(2, idPedid);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar estado de pedido: " + ex.getMessage(), ex);
        }
    }

    public int eliminarPedido(int idPedid) {
        String sql = "DELETE FROM pedidos WHERE idPedid = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idPedid);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar pedido: " + ex.getMessage(), ex);
        }
    }

    public List<PedidoDTO> buscarPedidosPorFiltros(String cliente, String estado, String fecha) {
        StringBuilder sql = new StringBuilder("SELECT p.idPedid, p.usuario_id, p.fecha, p.total, p.estado, p.metodo_pago_id, p.tipo_entrega, p.direccion FROM pedidos p INNER JOIN usuarios u ON p.usuario_id = u.idUser WHERE 1=1");
        List<Object> params = new ArrayList<>();
        if (cliente != null && !cliente.trim().isEmpty()) {
            sql.append(" AND u.nombre LIKE ?");
            params.add("%" + cliente.trim() + "%");
        }
        if (estado != null && !estado.trim().isEmpty()) {
            sql.append(" AND p.estado = ?");
            params.add(estado.trim());
        }
        if (fecha != null && !fecha.trim().isEmpty()) {
            sql.append(" AND CONVERT(date, p.fecha) = ?");
            params.add(fecha.trim());
        }
        sql.append(" ORDER BY p.fecha DESC");
        List<PedidoDTO> pedidos = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) {
                pstm.setObject(i + 1, params.get(i));
            }
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    PedidoDTO dto = new PedidoDTO();
                    dto.setIdPedid(rs.getInt("idPedid"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));
                    dto.setFecha(rs.getString("fecha"));
                    dto.setTotal(rs.getDouble("total"));
                    dto.setEstado(rs.getString("estado"));
                    dto.setMetodo_pago_id(rs.getInt("metodo_pago_id"));
                    dto.setTipo_entrega(rs.getString("tipo_entrega"));
                    dto.setDireccion(rs.getString("direccion"));
                    pedidos.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar pedidos por filtros: " + ex.getMessage(), ex);
        }
        return pedidos;
    }

    public int contarPedidosHoy() {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE CONVERT(date, fecha) = CONVERT(date, GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar pedidos de hoy: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public double sumarIngresosHoy() {
        String sql = "SELECT SUM(total) FROM pedidos WHERE estado = 'entregado' AND CONVERT(date, fecha) = CONVERT(date, GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al sumar ingresos de hoy: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarPedidosPendientes() {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE estado = 'pendiente'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar pedidos pendientes: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarPedidosEntregados() {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE estado = 'entregado'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar pedidos entregados: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public double sumarIngresosTotales() {
        String sql = "SELECT SUM(total) FROM pedidos WHERE estado = 'entregado'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al sumar ingresos totales: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public double calcularPromedioVenta() {
        String sql = "SELECT AVG(total) FROM pedidos WHERE estado = 'entregado'";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al calcular promedio de venta: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public int contarPedidosMes() {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE MONTH(fecha) = MONTH(GETDATE()) AND YEAR(fecha) = YEAR(GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar pedidos del mes: " + ex.getMessage(), ex);
        }
        return 0;
    }

    public double sumarIngresosMes() {
        String sql = "SELECT SUM(total) FROM pedidos WHERE estado = 'entregado' AND MONTH(fecha) = MONTH(GETDATE()) AND YEAR(fecha) = YEAR(GETDATE())";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al sumar ingresos del mes: " + ex.getMessage(), ex);
        }
        return 0;
    }
}
