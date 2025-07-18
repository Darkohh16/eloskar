package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.MetodoPagoDAO;
import com.eloskar.restaurante.DTO.MetodoPagoDTO;
import java.util.List;

public class MetodoPagoService {
    private MetodoPagoDAO metodoPagoDAO;

    public MetodoPagoService() {
        this.metodoPagoDAO = new MetodoPagoDAO();
    }

    public List<MetodoPagoDTO> listarMetodosPago() {
        return metodoPagoDAO.listarMetodosPago();
    }
} 