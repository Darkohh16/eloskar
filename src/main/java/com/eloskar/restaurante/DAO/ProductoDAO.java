package com.eloskar.restaurante.DAO;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.util.PoolConexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public int insertProd(ProductoDTO dto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, imagen, disponible, categoria_id) " +
                "VALUES (?, ?, ?, ?, ?, (SELECT idCat FROM categorias WHERE nombre = ?))";

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getNombre());
            pstm.setString(2, dto.getDescripcion());
            pstm.setFloat(3, dto.getPrecio());
            pstm.setString(4, dto.getImagen());
            pstm.setBoolean(5, dto.isDisponible());
            pstm.setString(6, dto.getCategoria().getNombre());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al insertar producto: " + ex.getMessage(), ex);
        }
    }


    public int updateProd(ProductoDTO dto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, imagen = ?, categoria_id = " +
                "(SELECT idCat FROM categorias WHERE nombre = ?) WHERE idProd = ?;";

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setString(1, dto.getNombre());
            pstm.setString(2, dto.getDescripcion());
            pstm.setFloat(3, dto.getPrecio());
            pstm.setString(4, dto.getImagen());
            pstm.setString(5, dto.getCategoria().getNombre());
            pstm.setInt(6, dto.getIdProd());

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar producto: " + ex.getMessage(), ex);
        }
    }

    public int updateDispProd(int id, boolean disponible) {
        String sql = "UPDATE productos SET disponible = ? WHERE idProd = ?;";

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setBoolean(1, disponible);
            pstm.setInt(2, id);

            //=======================================
//            int updated = pstm.executeUpdate();
//            if (updated == 0) {
//                throw new RuntimeException("No se actualizó ningún producto. ID no encontrado.");
//            }
            return pstm.executeUpdate();
            //=======================================

        } catch (SQLException ex) {
            throw new RuntimeException("Error al actualizar disponibilidad producto: " + ex.getMessage(), ex);
        }
    }

    public int deleteProd(int id) {
        String sql = "DELETE productos WHERE idProd = ?";

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {

            pstm.setInt(1, id);

            return pstm.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException("Error al eliminar producto: " + ex.getMessage(), ex);
        }
    }


    public List<ProductoDTO> buscarTodosProd(String filtro, String cate, int pagina, int entradasMax, int offset) {
        StringBuilder sql = new StringBuilder("SELECT p.idProd, p.nombre, c.nombre AS cate, c.idCat AS idcate, c.descripcion AS descate, p.descripcion, "
                + "p.precio, p.disponible, p.imagen FROM productos p "
                + "INNER JOIN categorias c ON p.categoria_id = c.idCat ");
        
        // Optimizar la condición WHERE - priorizar búsquedas por nombre
        if (filtro == null || filtro.trim().isEmpty()) {
            // Si no hay filtro, solo filtrar por categoría
            if (!"Todos".equals(cate)) {
                sql.append("WHERE c.nombre = ? ");
            }
        } else {
            // Si hay filtro, usar LIKE optimizado - priorizar nombre sobre descripción
            sql.append("WHERE (p.nombre LIKE ? OR p.descripcion LIKE ?) ");
            if (!"Todos".equals(cate)) {
                sql.append("AND c.nombre = ? ");
            }
        }
        
        sql.append("ORDER BY p.idProd OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
        
        List<ProductoDTO> productos = new ArrayList<>();

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql.toString())) {
            
            int paramIndex = 1;
            
            if (filtro != null && !filtro.trim().isEmpty()) {
                // Usar filtro más específico para mejor rendimiento
                String filtroOptimizado = "%" + filtro.trim() + "%";
                pstm.setString(paramIndex++, filtroOptimizado);
                pstm.setString(paramIndex++, filtroOptimizado);
            }
            
            // Agregar parámetro de categoría solo si no es "Todos"
            if (!"Todos".equals(cate)) {
                pstm.setString(paramIndex++, cate);
            }
            
            pstm.setInt(paramIndex++, offset);
            pstm.setInt(paramIndex, entradasMax);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    CategoriaDTO dtoC = new CategoriaDTO();
                    dtoC.setIdCat(rs.getInt("idcate"));
                    dtoC.setNombre(rs.getString("cate"));
                    dtoC.setDecripcion(rs.getString("descate"));

                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProd(rs.getInt("idProd"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setDescripcion(rs.getString("descripcion"));
                    dto.setPrecio(rs.getFloat("precio"));
                    dto.setDisponible(rs.getBoolean("disponible"));
                    dto.setImagen(rs.getString("imagen"));
                    dto.setCategoria(dtoC);
                    productos.add(dto);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al cargar productos", ex);
        }

        return productos;
    }

    public int obtNumProductos(String filtro, String categoria) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) AS registros FROM productos p ");
        sql.append("INNER JOIN categorias c ON p.categoria_id = c.idCat ");
        
        // Optimizar la condición WHERE
        if (filtro == null || filtro.trim().isEmpty()) {
            // Si no hay filtro, solo contar por categoría
            if (!"Todos".equals(categoria)) {
                sql.append("WHERE c.nombre = ?");
            }
        } else {
            // Si hay filtro, usar LIKE optimizado
            sql.append("WHERE (p.nombre LIKE ? OR p.descripcion LIKE ?) ");
            if (!"Todos".equals(categoria)) {
                sql.append("AND c.nombre = ?");
            }
        }
        
        int numRegistros = 0;

        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            
            if (filtro != null && !filtro.trim().isEmpty()) {
                pstm.setString(paramIndex++, "%" + filtro + "%");
                pstm.setString(paramIndex++, "%" + filtro + "%");
            }
            
            if (!"Todos".equals(categoria)) {
                pstm.setString(paramIndex, categoria);
            }

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    numRegistros = rs.getInt("registros");
                }
            }

        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar productos.", ex);
        }

        return numRegistros;
    }

    public ProductoDTO buscarProdPorId(int idProd) {
        String sql = "SELECT p.idProd, p.nombre, c.nombre AS cate, c.idCat AS idcate, c.descripcion AS descate, p.descripcion, p.precio, p.disponible, p.imagen FROM productos p INNER JOIN categorias c ON p.categoria_id = c.idCat WHERE p.idProd = ?";
        try (Connection con = PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, idProd);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    CategoriaDTO dtoC = new CategoriaDTO();
                    dtoC.setIdCat(rs.getInt("idcate"));
                    dtoC.setNombre(rs.getString("cate"));
                    dtoC.setDecripcion(rs.getString("descate"));

                    ProductoDTO dto = new ProductoDTO();
                    dto.setIdProd(rs.getInt("idProd"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setDescripcion(rs.getString("descripcion"));
                    dto.setPrecio(rs.getFloat("precio"));
                    dto.setDisponible(rs.getBoolean("disponible"));
                    dto.setImagen(rs.getString("imagen"));
                    dto.setCategoria(dtoC);
                    return dto;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al buscar producto por ID", ex);
        }
        return null;
    }

    public int contarProductosDeshabilitados() {
        String sql = "SELECT COUNT(*) FROM productos WHERE disponible = 0";
        try (Connection con = com.eloskar.restaurante.util.PoolConexion.getConnection();
             PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al contar productos deshabilitados: " + ex.getMessage(), ex);
        }
        return 0;
    }
}