package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.CarritoDAO;
import com.eloskar.restaurante.DTO.CarritoDTO;


public class CarritoService {
    private CarritoDAO carritoDAO;

    public CarritoService() {
        this.carritoDAO = new CarritoDAO();
    }

    public boolean insertarCarrito(CarritoDTO dto){
        dto.setUsuario_id(dto.getUsuario_id());

        return carritoDAO.insertCarrito(dto) > 0;
    }

    public CarritoDTO cargarCarritos(int usuarioId){

        return carritoDAO.buscarCarritoPorUsuario(usuarioId);
    }

    public boolean eliminarCarrito(int idCarrito){

        return carritoDAO.vaciarCarrito(idCarrito) > 0;
    }
}
