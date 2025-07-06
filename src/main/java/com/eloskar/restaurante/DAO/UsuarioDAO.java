package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public int insertU(UsuarioDTO dto) {
        String sql = "INSERT INTO usuarios (dni, celular, nombre, correo, rol, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getDni());
            pstm.setString(2, dto.getCel());
            pstm.setString(3, dto.getNombre());
            pstm.setString(4, dto.getCorreo());
            pstm.setString(5, dto.getRol());
            pstm.setString(6, dto.getPassword());

            return pstm.executeUpdate(); // retorna 1 si se insertó con éxito

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar usuario: " + ex.getMessage(), ex);
        }
    }


    public int updateU(UsuarioDTO dto) {
        StringBuilder sql = new StringBuilder("UPDATE usuarios SET ");
        sql.append("dni = ?, celular = ?, nombre = ?, correo = ?, rol = ?");
        boolean pass = dto.getPassword() != null && !dto.getPassword().isBlank();
        if (pass) {
            sql.append(", password = ?");
        }
        sql.append(" WHERE idUser = ?");
        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql.toString())) {

            pstm.setString(1, dto.getDni());
            pstm.setString(2, dto.getCel());
            pstm.setString(3, dto.getNombre());
            pstm.setString(4, dto.getCorreo());
            pstm.setString(5, dto.getRol());
            int p = 6;
            if (pass) {
                pstm.setString(p, dto.getPassword());
                p++;
            }
            pstm.setInt(p, dto.getIdUser());

            return pstm.executeUpdate();

        } catch  (SQLException ex) {
            throw new RuntimeException("Error al actualizar usuario: " + ex.getMessage(), ex);
        }
    }


    public int deleteU(int id) {
        String sql = "DELETE usuarios WHERE idUser = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, id);

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar usuario: " + ex.getMessage(), ex);
        }
    }


    public List<UsuarioDTO> buscarTodosU(String filtro) {
        String sql = "SELECT idUser, nombre, dni, correo, celular, rol, fecha_registro FROM usuarios WHERE "
                + "nombre LIKE ? OR correo LIKE ? OR dni LIKE ?";
        List<UsuarioDTO> usuarios = new ArrayList<>();

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, "%" + filtro + "%");
            pstm.setString(2, "%" + filtro + "%");
            pstm.setString(3, "%" + filtro + "%");

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.setIdUser(rs.getInt("idUser"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setDni(rs.getString("dni"));
                    dto.setCorreo(rs.getString("correo"));
                    dto.setCel(rs.getString("celular"));
                    dto.setRol(rs.getString("rol"));
                    dto.setFechaRegistro(rs.getString("fecha_registro"));
                    usuarios.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al cargar usuarios", ex);
        }

        return usuarios;
    }

}
