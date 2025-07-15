package com.eloskar.restaurante.controller.Carrito;

import com.eloskar.restaurante.DTO.CarritoDTO;
import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.services.CarritoService;
import com.eloskar.restaurante.services.DetalleCarritoService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SrvAgregarAlCarrito", value = "/SrvAgregarAlCarrito")
public class SrvAgregarAlCarrito extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/eloskarJSP/login/login.jsp");
            return;
        }
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        CarritoService carritoService = new CarritoService();
        CarritoDTO carrito = carritoService.cargarCarritos(idUser);
        if (carrito == null) {
            carrito = new CarritoDTO();
            carrito.setUsuario_id(idUser);
            carrito.setDetalles(new ArrayList<>());
            carritoService.insertarCarrito(carrito);
            // Recargar el carrito para obtener el idCarrito generado
            carrito = carritoService.cargarCarritos(idUser);
        }
        // Verificar si el producto ya está en el carrito
        boolean encontrado = false;
        for (CarritoDetalleDTO det : carrito.getDetalles()) {
            if (det.getProducto_id() == idProducto) {
                // Sumar cantidad
                DetalleCarritoService detalleService = new DetalleCarritoService();
                detalleService.actualizarDetalle(det.getIdDetalle(), det.getCantidad() + 1);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            // Agregar nuevo producto al carrito
            CarritoDetalleDTO nuevo = new CarritoDetalleDTO();
            nuevo.setCarrito_id(carrito.getIdCarrito());
            nuevo.setProducto_id(idProducto);
            nuevo.setCantidad(1);
            nuevo.setNotas("");
            DetalleCarritoService detalleService = new DetalleCarritoService();
            detalleService.insertarDetalle(nuevo);
        }
        response.sendRedirect("SrvBuscarProducto");
    }
} 