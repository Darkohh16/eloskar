package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.MetodoPagoDTO;
import com.eloskar.restaurante.util.PoolConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoDAO {
    public List<MetodoPagoDTO> listarMetodosPago() {
        List<MetodoPagoDTO> metodos = new ArrayList<>();
        String sql = "SELECT idPago, nombre FROM metodos_pago ORDER BY idPago";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                MetodoPagoDTO dto = new MetodoPagoDTO();
                dto.setIdPago(rs.getInt("idPago"));
                dto.setNombre(rs.getString("nombre"));
                metodos.add(dto);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar métodos de pago: " + ex.getMessage(), ex);
        }
        return metodos;
    }
}
