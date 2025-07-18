package com.eloskar.restaurante.controller.Reservas;

import com.eloskar.restaurante.services.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SrvActualizarEstadoReserva", value = "/SrvActualizarEstadoReserva")
public class SrvActualizarEstadoReserva extends HttpServlet {
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
        String idStr = request.getParameter("id");
        String estado = request.getParameter("estado");
        int id = 0;
        try { id = Integer.parseInt(idStr); } catch (Exception e) { id = 0; }
        boolean exito = false;
        if (id > 0 && ("confirmada".equals(estado) || "cancelada".equals(estado))) {
            ReservaService service = new ReservaService();
            exito = service.actualizarEstado(id, estado);
        }
        response.setContentType("text/html;charset=UTF-8");
        if (exito) {
            response.getWriter().write("<script>alert('Estado actualizado con éxito'); window.location='SrvListarReservasDashboard';</script>");
        } else {
            response.getWriter().write("<script>alert('Error al actualizar el estado'); window.location='SrvListarReservasDashboard';</script>");
        }
    }
}