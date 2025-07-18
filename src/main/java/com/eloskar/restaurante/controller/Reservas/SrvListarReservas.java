package com.eloskar.restaurante.controller.Reservas;

import com.eloskar.restaurante.DTO.ReservaDTO;
import com.eloskar.restaurante.services.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvListarReservas", value = "/SrvListarReservas")
public class SrvListarReservas extends HttpServlet {

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
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");if (idUser == null) {
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Debes loguearte para reservar.'); " +
                    "window.location='" + request.getContextPath() +
                    "/jsp/eloskarJSP/login/login.jsp';</script>");
            return;
        }

        ReservaService reservaService = new ReservaService();
        List<ReservaDTO> reservas = reservaService.listarReservasPorUsuario(idUser);
        request.setAttribute("reservasUsuario", reservas);
        request.getRequestDispatcher("/jsp/eloskarJSP/reservas.jsp").forward(request, response);
    }
}
