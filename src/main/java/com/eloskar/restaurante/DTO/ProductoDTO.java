package com.eloskar.restaurante.DTO;

import com.eloskar.restaurante.DAO.ProductoDAO;

import java.math.BigDecimal;
import java.util.List;

public class ProductoDTO {
    private int idProd;
    private String nombre;
    private String decripcion;
    private float precio;
    private String imagen;
    private boolean disponible;
    private CategoriaDTO categoria;

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDTO categoria) {
        this.categoria = categoria;
    }


    public boolean insertarU(){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProd(idProd);
        dto.setNombre(nombre);
        dto.setDecripcion(decripcion);
        dto.setPrecio(precio);
        dto.setImagen(imagen);
        dto.setDisponible(disponible);
        dto.setCategoria(categoria);

        ProductoDAO dao = new ProductoDAO();
        if (dao.insertProd(dto) > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean actualizarProd(){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProd(idProd);
        dto.setNombre(nombre);
        dto.setDecripcion(decripcion);
        dto.setPrecio(precio);
        dto.setImagen(imagen);
        dto.setCategoria(categoria);

        ProductoDAO dao = new ProductoDAO();
        if (dao.updateProd(dto) > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean eliminarProd(){
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProd(idProd);
        ProductoDAO dao = new ProductoDAO();
        if (dao.deleteProd(dto) > 0){
            return true;
        }else{
            return false;
        }
    }

    public List<ProductoDTO> cargarDatosProductos(String filtro, String cate){
        ProductoDAO dao = new ProductoDAO();
        if (filtro.isBlank()){
            filtro = "";
        }

        return dao.buscarTodosProd(filtro, cate);
    }
}
