package com.eloskar.restaurante.controller.Carrito;

import com.eloskar.restaurante.services.DetalleCarritoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvActualizarDetalleCarrito", value = "/SrvActualizarDetalleCarrito")
public class SrvActualizarDetalleCarrito extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
        DetalleCarritoService service = new DetalleCarritoService();
        if ("sumar".equals(accion)) {
            int cantidadActual = Integer.parseInt(request.getParameter("cantidad"));
            service.actualizarDetalle(idDetalle, cantidadActual + 1);
        } else if ("restar".equals(accion)) {
            int cantidadActual = Integer.parseInt(request.getParameter("cantidad"));
            if (cantidadActual > 1) {
                service.actualizarDetalle(idDetalle, cantidadActual - 1);
            } else {
                service.eliminarDetalle(idDetalle);
            }
        } else if ("eliminar".equals(accion)) {
            service.eliminarDetalle(idDetalle);
        }
        response.sendRedirect("SrvCarrito");
    }
}