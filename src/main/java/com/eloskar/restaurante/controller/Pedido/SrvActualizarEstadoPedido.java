package com.eloskar.restaurante.controller.Pedido;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvActualizarEstadoPedido", value = "/SrvActualizarEstadoPedido")
public class SrvActualizarEstadoPedido extends HttpServlet {

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
        boolean exito = false;
        if (idStr != null && !idStr.trim().isEmpty()) {
            try { id = Integer.parseInt(idStr); } catch (Exception e) { id = 0; }
        }
        if (id > 0 && ("entregado".equals(estado) || "cancelado".equals(estado))) {
            com.eloskar.restaurante.services.PedidoService service = new com.eloskar.restaurante.services.PedidoService();
            exito = service.actualizarEstado(id, estado);
        }
        response.setContentType("text/html;charset=UTF-8");
        if (exito) {
            response.getWriter().write("<script>alert('Estado actualizado con éxito'); window.location='SrvListarPedidoDashboard';</script>");
        } else {
            response.getWriter().write("<script>alert('Error al actualizar el estado'); window.location='SrvListarPedidoDashboard';</script>");
        }
    }
}