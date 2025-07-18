package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.UsuarioService;
import com.eloskar.restaurante.services.PedidoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SrvMiCuenta", value = "/SrvMiCuenta")
public class SrvMiCuenta extends HttpServlet {

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
        UsuarioService usuarioService = new UsuarioService();
        PedidoService pedidoService = new PedidoService();
        UsuarioDTO usuario = usuarioService.obtenerDatosRecuperacion(idUser);
        request.getSession().setAttribute("usuario", usuario);
        request.setAttribute("pedidosUsuario", pedidoService.listarPedidosPorUsuario(idUser));
        request.getRequestDispatcher("/jsp/eloskarJSP/miCuenta.jsp").forward(request, response);
    }
}
