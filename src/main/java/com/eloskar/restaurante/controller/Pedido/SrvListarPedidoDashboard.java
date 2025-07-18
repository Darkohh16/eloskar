package com.eloskar.restaurante.controller.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvListarPedidoDashboard", value = "/SrvListarPedidoDashboard")
public class SrvListarPedidoDashboard extends HttpServlet {

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

        com.eloskar.restaurante.services.PedidoService pedidoService = new com.eloskar.restaurante.services.PedidoService();
        com.eloskar.restaurante.services.UsuarioService usuarioService = new com.eloskar.restaurante.services.UsuarioService();
        java.util.List<com.eloskar.restaurante.DTO.PedidoDTO> pedidos;
        if ((cliente != null && !cliente.trim().isEmpty()) ||
            (estado != null && !estado.trim().isEmpty()) ||
            (fecha != null && !fecha.trim().isEmpty())) {
            pedidos = pedidoService.buscarPedidosPorFiltros(cliente, estado, fecha);
        } else {
            pedidos = pedidoService.listarTodosPedidos();
        }
        java.util.Map<Integer, String> nombresUsuarios = new java.util.HashMap<>();
        for (com.eloskar.restaurante.DTO.PedidoDTO p : pedidos) {
            int idUser = p.getUsuario_id();
            if (!nombresUsuarios.containsKey(idUser)) {
                com.eloskar.restaurante.DTO.UsuarioDTO usuario = usuarioService.obtenerDatosRecuperacion(idUser);
                nombresUsuarios.put(idUser, usuario != null ? usuario.getNombre() : "-");
            }
        }
        request.setAttribute("pedidos", pedidos);
        request.setAttribute("nombresUsuarios", nombresUsuarios);
        request.getRequestDispatcher("/jsp/dashboardJSP/Pedidos.jsp").forward(request, response);
    }
}