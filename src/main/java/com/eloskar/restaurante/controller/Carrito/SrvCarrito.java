package com.eloskar.restaurante.controller.Carrito;

import com.eloskar.restaurante.DTO.CarritoDTO;
import com.eloskar.restaurante.services.CarritoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvCarrito", value = "/SrvCarrito")
public class SrvCarrito extends HttpServlet {

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
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/eloskarJSP/login/login.jsp");
            return;
        }
        CarritoService carritoService = new CarritoService();
        CarritoDTO carrito = carritoService.cargarCarritos(idUser);
        request.setAttribute("carrito", carrito);
        request.getRequestDispatcher("/jsp/eloskarJSP/carrito.jsp").forward(request, response);
    }
}
