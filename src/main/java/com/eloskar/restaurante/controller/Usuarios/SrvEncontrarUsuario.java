package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvEncontrarUsuario", value = "/SrvEncontrarUsuario")
public class SrvEncontrarUsuario extends HttpServlet {

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

        UsuarioService service = new UsuarioService();
        int id = service.validarLogin(request.getParameter("correo"), request.getParameter("password"));
        String rol = service.obtenerRol(id);

        if (id == 0) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('Usuario o contraseña incorrecta.'); " +
                    "window.location='/jsp/eloskarJSP/login/login.jsp';</script>");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("idUser", id);
            session.setAttribute("rol", rol);

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<script>alert('Bienvenido ' ); " +
                    "window.location='SrvBuscarProducto';</script>");
        }
    }
}
