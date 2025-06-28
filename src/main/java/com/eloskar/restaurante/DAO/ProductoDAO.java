package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public int insertProd(ProductoDTO dto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, imagen, disponible, categoria_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getNombre());
            pstm.setString(2, dto.getDecripcion());
            pstm.setFloat(3, dto.getPrecio());
            pstm.setString(4, dto.getImagen());
            pstm.setBoolean(5, dto.isDisponible());
            pstm.setInt(6, dto.getCategoria().getIdCat());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar producto: " + ex.getMessage(), ex);
        }
    }


    public int updateProd(ProductoDTO dto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ?, categoria_id = " +
                "(SELECT idCat FROM categorias WHERE nombre = ?) WHERE idProd = ?;";

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getNombre());
            pstm.setString(2, dto.getDecripcion());
            pstm.setFloat(3, dto.getPrecio());
            pstm.setString(4, dto.getImagen());
            pstm.setString(5, dto.getCategoria().getNombre());
            pstm.setInt(6, dto.getIdProd());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar producto: " + ex.getMessage(), ex);
        }
    }


    public int deleteProd(ProductoDTO dto) {
        String sql = "DELETE productos WHERE idProd = ?";

        try (Connection con = Conexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, dto.getIdProd());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar producto: " + ex.getMessage(), ex);
        }
    }


    public List<ProductoDTO> buscarTodosProd(String filtro, String cate) {
        StringBuilder sql = new StringBuilder("SELECT p.idProd, p.nombre, c.nombre AS cate, c.idCat AS idcate, c.descripcion AS descate, p.descripcion, "
                + "p.precio, p.disponible, p.imagen FROM productos p "
                + "INNER JOIN categorias c ON p.categoria_id = c.idCat "
                + "WHERE (p.nombre LIKE ? OR p.descripcion LIKE ?) ");
        List<ProductoDTO> productos = new ArrayList<>();

        try (Connection con = Conexion.getConnection()) {
            if (!"Todos".equals(cate)) {
                sql.append("AND c.idCat = (SELECT idCat FROM categorias WHERE nombre = ?)");
            }

            try (PreparedStatement pstm = con.prepareStatement(sql.toString())) {
                pstm.setString(1, "%" + filtro + "%");
                pstm.setString(2, "%" + filtro + "%");
                if (!"Todos".equals(cate)) {
                    pstm.setString(3, cate);
                }

                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        CategoriaDTO dtoC = new CategoriaDTO();
                        dtoC.setIdCat(rs.getInt("idcate"));
                        dtoC.setNombre(rs.getString("cate"));
                        dtoC.setDecripcion(rs.getString("descate"));

                        ProductoDTO dto = new ProductoDTO();
                        dto.setIdProd(rs.getInt("idProd"));
                        dto.setNombre(rs.getString("nombre"));
                        dto.setDecripcion(rs.getString("descripcion"));
                        dto.setPrecio(rs.getFloat("precio"));
                        dto.setDisponible(rs.getBoolean("disponible"));
                        dto.setImagen(rs.getString("imagen"));
                        dto.setCategoria(dtoC);
                        productos.add(dto);
                    }
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al cargar productos", ex);
        }

        return productos;
    }
}