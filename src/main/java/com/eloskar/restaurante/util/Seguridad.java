package com.eloskar.restaurante.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

public class Seguridad {

    public static boolean verificarAccesoAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("idUser") == null) {
            // No hay sesión → redirigir al login
            response.sendRedirect(request.getContextPath() + "/jsp/eloskarJSP/login/login.jsp");
            return false;
        }

        String rol = (String) session.getAttribute("rol");

        if (!"admin".equalsIgnoreCase(rol)) {
            // Tiene sesión, pero no es admin → acceso denegado
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('No tienes permiso para eso'); " +
                    "window.location='SrvBuscarProducto';</script>");
            return false;
        }

        return true; // Tiene sesión y es admin
    }
}
