package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.PedidoDetalleDTO;
import com.eloskar.restaurante.util.PoolConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetalleDAO {
    public int insertDetalle(PedidoDetalleDTO dto) {
        String sql = "INSERT INTO pedido_detalle (pedido_id, plato_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getPedido_id());
            pstm.setInt(2, dto.getPlato_id());
            pstm.setInt(3, dto.getCantidad());
            pstm.setDouble(4, dto.getPrecio_unitario());
            int res = pstm.executeUpdate();
            if (res > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {
                    if (rs.next()) {
                        dto.setIdDetPedid(rs.getInt(1));
                    }
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar detalle de pedido: " + ex.getMessage(), ex);
        }
    }

    public List<PedidoDetalleDTO> listarDetallesPorPedido(int pedidoId) {
        String sql = "SELECT pd.idDetPedid, pd.pedido_id, pd.plato_id, pd.cantidad, pd.precio_unitario, p.nombre as nombre_producto " +
                "FROM pedido_detalle pd INNER JOIN productos p ON pd.plato_id = p.idProd WHERE pd.pedido_id = ?";
        List<PedidoDetalleDTO> detalles = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, pedidoId);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    PedidoDetalleDTO dto = new PedidoDetalleDTO();
                    dto.setIdDetPedid(rs.getInt("idDetPedid"));
                    dto.setPedido_id(rs.getInt("pedido_id"));
                    dto.setPlato_id(rs.getInt("plato_id"));
                    dto.setCantidad(rs.getInt("cantidad"));
                    dto.setPrecio_unitario(rs.getDouble("precio_unitario"));
                    dto.setNombreProducto(rs.getString("nombre_producto"));
                    detalles.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar detalles de pedido: " + ex.getMessage(), ex);
        }
        return detalles;
    }

    public int eliminarDetalle(int idDetPedid) {
        String sql = "DELETE FROM pedido_detalle WHERE idDetPedid = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idDetPedid);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar detalle de pedido: " + ex.getMessage(), ex);
        }
    }
}
