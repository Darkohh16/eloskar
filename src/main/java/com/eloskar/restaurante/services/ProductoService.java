package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.ProductoDAO;
import com.eloskar.restaurante.DAO.UsuarioDAO;
import com.eloskar.restaurante.DTO.ProductoDTO;

import java.util.List;

public class ProductoService {
    private  ProductoDAO productoDAO;

    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    public boolean insertarProd(ProductoDTO dto){
        dto.setIdProd(dto.getIdProd());
        dto.setNombre(dto.getNombre());
        dto.setDescripcion(dto.getDescripcion());
        dto.setPrecio(dto.getPrecio());
        dto.setImagen(dto.getImagen());
        dto.setDisponible(dto.isDisponible());
        dto.setCategoria(dto.getCategoria());

        return productoDAO.insertProd(dto) > 0;
    }

    public boolean actualizarProd(ProductoDTO dto){
        dto.setIdProd(dto.getIdProd());
        dto.setNombre(dto.getNombre());
        dto.setDescripcion(dto.getDescripcion());
        dto.setPrecio(dto.getPrecio());
        dto.setImagen(dto.getImagen());
        dto.setCategoria(dto.getCategoria());

        return productoDAO.updateProd(dto) > 0;
    }

    public boolean actualizarDispProd(int id, boolean disponible){

        return productoDAO.updateDispProd(id, disponible) > 0;
    }

    public boolean eliminarProd(int idProd){

        return productoDAO.deleteProd(idProd) > 0;
    }

    public List<ProductoDTO> cargarDatosProductos(String filtro, String cate, int pagina, int entradasMax){
        int offset = (pagina - 1) * entradasMax;
        return productoDAO.buscarTodosProd(filtro, cate, pagina, entradasMax, offset);
    }

    public int totalProductos (int entradasMax, String filtro, String cate) {
        if (filtro == null) {
            filtro = "";
        }
        if (cate == null) {
            cate = "Todos";
        }
        int productos;
        int total;

        productos = productoDAO.obtNumProductos(filtro, cate);
        total = (int)Math.ceil((double) productos /entradasMax);
        return total;
    }

    public ProductoDTO obtenerProductoPorId(int idProd) {
        return productoDAO.buscarProdPorId(idProd);
    }
}
