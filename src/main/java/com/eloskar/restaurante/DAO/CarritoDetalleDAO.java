package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDetalleDAO {
    public int insertDetalle(CarritoDetalleDTO dto) {
        String sql = "INSERT INTO carrito_detalle (carrito_id, producto_id, cantidad, notas) VALUES (?, ?, ?, ?)";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, dto.getCarrito_id());
            pstm.setInt(2, dto.getProducto_id());
            pstm.setInt(3, dto.getCantidad());
            pstm.setString(4, dto.getNotas());
            int res = pstm.executeUpdate();
            if (res > 0) {
                try (ResultSet rs = pstm.getGeneratedKeys()) {
                    if (rs.next()) {
                        dto.setIdDetalle(rs.getInt(1));
                    }
                }
            }
            return res;
        } catch (SQLException ex) {
            throw new RuntimeException("Error al agregar producto al carrito: " + ex.getMessage(), ex);
        }
    }

    public int updateCantidad(int idDetalle, int cantidad) {
        String sql = "UPDATE carrito_detalle SET cantidad = ? WHERE idDetalle = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, cantidad);
            pstm.setInt(2, idDetalle);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar cantidad: " + ex.getMessage(), ex);
        }
    }

    public int deleteDetalle(int idDetalle) {
        String sql = "DELETE FROM carrito_detalle WHERE idDetalle = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, idDetalle);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar detalle: " + ex.getMessage(), ex);
        }
    }

    public List<CarritoDetalleDTO> buscarDetallesPorCarrito(int idCarrito) {
        String sql = "SELECT idDetalle, carrito_id, producto_id, cantidad, notas " +
                "FROM carrito_detalle WHERE carrito_id = ?";
        List<CarritoDetalleDTO> detalles = new ArrayList<>();
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, idCarrito);
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    CarritoDetalleDTO dto = new CarritoDetalleDTO();
                    dto.setIdDetalle(rs.getInt("idDetalle"));
                    dto.setCarrito_id(rs.getInt("carrito_id"));
                    dto.setProducto_id(rs.getInt("producto_id"));
                    dto.setCantidad(rs.getInt("cantidad"));
                    dto.setNotas(rs.getString("notas"));
                    detalles.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar detalles del carrito: " + ex.getMessage(), ex);
        }
        return detalles;
    }
}
