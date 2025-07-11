package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.TokensDTO;
import com.eloskar.restaurante.services.TokensService;
import com.eloskar.restaurante.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvCambiarContrasenia", value = "/SrvCambiarContrasenia")
public class SrvCambiarContrasenia extends HttpServlet {

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
        String token = request.getParameter("token");
        String nuevaPassword = request.getParameter("nueva_password");
        String contextPath = request.getContextPath();


        TokensService tokenService = new TokensService();
        UsuarioService usuarioService = new UsuarioService();

        // Verificar token
        TokensDTO tokenDTO = tokenService.buscarToken(token);
        if (tokenDTO == null || tokenDTO.isUsado() || tokenService.tokenExpirado(tokenDTO.getExpiracion())) {
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('El token a expirado, reenvie la solicitud nuevamente.'); " +
                    "window.location='/jsp/eloskarJSP/login/login.jsp';</script>");
        }

        // Cambiar contraseña del usuario y marcar token como usado
        int idUser = tokenDTO.getIdUser();
        if (usuarioService.cambiarPass(idUser, nuevaPassword)) {
            tokenService.marcarTokenUsado(token);
        }
        response.setContentType("text/html");
        response.getWriter().write("<script>alert('Contraseña actualizada con exito.'); " +
                "window.location='" + contextPath + "/jsp/eloskarJSP/login/login.jsp';</script>");
    }
}
