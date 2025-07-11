package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.TokensDTO;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TokensDAO {
    public int insertarToken(TokensDTO dto) {
        String sql = "INSERT INTO tokens_recuperacion (idUser, token, expiracion)" +
                " VALUES (?, ?, ?)";

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, dto.getIdUser());
            pstm.setString(2, dto.getToken());
            pstm.setString(3, dto.getExpiracion());

            return pstm.executeUpdate(); // retorna 1 si se insertó con éxito

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar usuario: " + ex.getMessage(), ex);
        }

    }

    public TokensDTO  buscarPorToken(String token) {
        String sql = "SELECT * FROM tokens_recuperacion WHERE token = ?";

        try (Connection conn = PoolConexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TokensDTO dto = new TokensDTO();
                    dto.setIdToken(rs.getInt("idToken"));
                    dto.setIdUser(rs.getInt("idUser"));
                    dto.setToken(rs.getString("token"));
                    dto.setExpiracion(rs.getString("expiracion"));
                    dto.setUsado(rs.getBoolean("usado"));
                    return dto;
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar: " + ex.getMessage(), ex);
        }

        return null;
    }

    public int marcarToken(String token) {
        String sql = "UPDATE tokens_recuperacion SET usado = 1 WHERE token = ?";

        try (Connection conn = PoolConexion.getConnection();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, token);

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al cambiar estado: " + ex.getMessage(), ex);
        }
    }


}
