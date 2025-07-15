package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.CarritoDTO;
import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.services.DetalleCarritoService;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarritoDAO {
    public int insertCarrito(CarritoDTO dto) {
        String sql = "INSERT INTO carritos (usuario_id) VALUES (?)";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, dto.getUsuario_id());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al crear carrito: " + ex.getMessage(), ex);
        }
    }

    public int vaciarCarrito(int idCarrito) {
        String sql = "DELETE FROM carritos WHERE idCarrito = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, idCarrito);
            return pstm.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error al vaciar carrito: " + ex.getMessage(), ex);
        }
    }

    public CarritoDTO buscarCarritoPorUsuario(int usuarioId) {
        String sql = "SELECT idCarrito, usuario_id FROM carritos WHERE usuario_id = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, usuarioId);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    CarritoDTO dto = new CarritoDTO();
                    dto.setIdCarrito(rs.getInt("idCarrito"));
                    dto.setUsuario_id(rs.getInt("usuario_id"));

                    // Cargar detalles del carrito
                    DetalleCarritoService service = new DetalleCarritoService();
                    List<CarritoDetalleDTO> detalles = service.cargarDetalleCarritos(dto.getIdCarrito());
                    dto.setDetalles(detalles);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar carrito: " + ex.getMessage(), ex);
        }
        return null;
    }

}
