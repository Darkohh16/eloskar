package com.eloskar.restaurante.controller.Reservas;

import com.eloskar.restaurante.DTO.ReservaDTO;
import com.eloskar.restaurante.services.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvAgregarReserva", value = "/SrvAgregarReserva")
public class SrvAgregarReserva extends HttpServlet {

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
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        String personasStr = request.getParameter("personas");
        int cantidadPersonas = 1;
        try {
            cantidadPersonas = Integer.parseInt(personasStr);
        } catch (Exception e) {
            cantidadPersonas = 1;
        }
        ReservaDTO dto = new ReservaDTO();
        dto.setUsuario_id(idUser);
        dto.setFecha(fecha);
        dto.setHora(hora);
        dto.setCantidad_personas(cantidadPersonas);
        dto.setEstado("pendiente");

        ReservaService service = new ReservaService();
        response.setContentType("text/html");
        if (service.insertarReserva(dto)) {
            response.getWriter().write("<script>alert('Reserva registrada con éxito'); " +
                    "window.location='SrvListarReservas';</script>");
        } else {
            response.getWriter().write("<script>alert('Error al registrar la reserva'); " +
                    "window.location='SrvListarReservas';</script>");
        }
    }
}