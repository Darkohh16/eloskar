package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.services.ProductoService;
import com.eloskar.restaurante.util.Seguridad;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvActualizarDispProducto", value = "/SrvActualizarDispProducto")
public class SrvActualizarDispProducto extends HttpServlet {

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
        if (!Seguridad.verificarAccesoAdmin(request, response)) {
            return; // Se redirigió por falta de sesión o acceso
        }

        int id = Integer.parseInt(request.getParameter("id"));
        boolean disponible = !Boolean.parseBoolean(request.getParameter("disponible"));

        ProductoService service = new ProductoService();
        if (service.actualizarDispProd(id, disponible)){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Disponibilidad producto actualizado con éxito'); " +
                    "window.location='SrvBuscarProducto';</script>");
        }
    }
}