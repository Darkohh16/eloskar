package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
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
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProd(Integer.parseInt(request.getParameter("id")));
        dto.setDisponible(!Boolean.parseBoolean(request.getParameter("disponible")));

        if (dto.actualizarDispProd()){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Disponibilidad producto actualizado con éxito'); window.location='jsp/dashboardJSP/Productos.jsp';</script>");
        }
    }
}