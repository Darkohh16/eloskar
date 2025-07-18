package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    public List<CategoriaDTO> buscarTodosCateg() {
        String sql = "SELECT idCat, nombre, descripcion FROM categorias;";
        List<CategoriaDTO> categorias = new ArrayList<>();

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                CategoriaDTO dto = new CategoriaDTO();
                dto.setIdCat(rs.getInt("idCat"));
                dto.setNombre(rs.getString("nombre"));
                dto.setDecripcion(rs.getString("descripcion"));
                categorias.add(dto);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al cargar categorias", ex);
        }

        return categorias;
    }
}
