package com.eloskar.restaurante.DTO;

import com.eloskar.restaurante.DAO.CategoriaDAO;
import com.eloskar.restaurante.DAO.ProductoDAO;

import java.util.List;

public class CategoriaDTO {
    private int idCat;
    private String nombre;
    private String decripcion;

    public int getIdCat() {
        return idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
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

    public List<CategoriaDTO> cargarDatosCat(){
        CategoriaDAO dao = new CategoriaDAO();

        return dao.buscarTodosCateg();
    }
}
