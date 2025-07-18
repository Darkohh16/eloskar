package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.util.CorreoUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SrvContacto", value = "/SrvContacto")
public class SrvContacto extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String asunto = request.getParameter("asunto");
        String mensaje = request.getParameter("mensaje");

        String destinatario = "eloskarpruebas@gmail.com";
        String asuntoCorreo = "[Contacto Web] " + asunto;
        String cuerpo = "Mensaje desde el formulario de contacto de El Oskar:\n\n" +
                "Nombre: " + nombre + "\n" +
                "Correo: " + correo + "\n" +
                "Asunto: " + asunto + "\n" +
                "Mensaje: " + mensaje;

        boolean enviado = false;
        try {
            CorreoUtil.enviar(destinatario, asuntoCorreo, cuerpo);
            enviado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setContentType("text/html;charset=UTF-8");
        if (enviado) {
            response.getWriter().write("<script>alert('¡Mensaje enviado con éxito!'); window.location='jsp/eloskarJSP/contacto.jsp';</script>");
        } else {
            response.getWriter().write("<script>alert('Error al enviar el mensaje. Intenta más tarde.'); window.location='jsp/eloskarJSP/contacto.jsp';</script>");
        }
    }
}