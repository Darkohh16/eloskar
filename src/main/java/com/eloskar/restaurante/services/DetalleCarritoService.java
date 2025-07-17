package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.CarritoDetalleDAO;
import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;

import java.util.List;

public class DetalleCarritoService {
    private CarritoDetalleDAO carritoDetalleDAO;

    public DetalleCarritoService() {
        this.carritoDetalleDAO = new CarritoDetalleDAO();
    }

    public List<CarritoDetalleDTO> cargarDetalleCarritos(int idCarrito){

        return carritoDetalleDAO.buscarDetallesPorCarrito(idCarrito);
    }

    public boolean insertarDetalle(CarritoDetalleDTO dto){
        try {
            dto.setCarrito_id(dto.getCarrito_id());
            dto.setProducto_id(dto.getProducto_id());
            dto.setCantidad(dto.getCantidad());
            dto.setNotas(dto.getNotas());
            return carritoDetalleDAO.insertDetalle(dto) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean actualizarDetalle(int idDetalle, int cantidad){

        return carritoDetalleDAO.updateCantidad(idDetalle, cantidad) > 0;
    }

    public boolean eliminarDetalle(int idDetalle){

        return carritoDetalleDAO.deleteDetalle(idDetalle) > 0;
    }
}
