package com.eloskar.restaurante.controller.Reservas;

import com.eloskar.restaurante.DTO.ReservaDTO;
import com.eloskar.restaurante.services.ReservaService;
import com.eloskar.restaurante.services.UsuarioService;
import com.eloskar.restaurante.DTO.UsuarioDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@WebServlet(name = "SrvListarReservasDashboard", value = "/SrvListarReservasDashboard")
public class SrvListarReservasDashboard extends HttpServlet {
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
        String cliente = request.getParameter("cliente");
        String estado = request.getParameter("estado");
        String fecha = request.getParameter("fecha");

        ReservaService reservaService = new ReservaService();
        UsuarioService usuarioService = new UsuarioService();
        List<ReservaDTO> reservas;
        if ((cliente != null && !cliente.trim().isEmpty()) ||
            (estado != null && !estado.trim().isEmpty()) ||
            (fecha != null && !fecha.trim().isEmpty())) {
            reservas = reservaService.buscarReservasPorFiltros(cliente, estado, fecha);
        } else {
            reservas = reservaService.listarTodasReservas();
        }

        Map<Integer, String> nombresUsuarios = new HashMap<>();
        for (ReservaDTO r : reservas) {
            int idUser = r.getUsuario_id();
            if (!nombresUsuarios.containsKey(idUser)) {
                UsuarioDTO usuario = usuarioService.obtenerDatosRecuperacion(idUser);
                nombresUsuarios.put(idUser, usuario != null ? usuario.getNombre() : "-");
            }
        }
        request.setAttribute("reservas", reservas);
        request.setAttribute("nombresUsuarios", nombresUsuarios);
        request.getRequestDispatcher("/jsp/dashboardJSP/Reservas.jsp").forward(request, response);
    }
}