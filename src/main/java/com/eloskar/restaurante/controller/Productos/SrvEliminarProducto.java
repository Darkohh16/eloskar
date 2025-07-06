package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.ProductoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvEliminarProducto", value = "/SrvEliminarProducto")
public class SrvEliminarProducto extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("idProd"));

        ProductoService service = new ProductoService();
        if (service.eliminarProd(id)){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Producto Eliminado con éxito'); " +
                    "window.location='SrvBuscarProducto';</script>");
        }
    }
}