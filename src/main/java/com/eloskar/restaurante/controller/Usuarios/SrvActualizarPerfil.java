package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvActualizarPerfil", value = "/SrvActualizarPerfil")
public class SrvActualizarPerfil extends HttpServlet {

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
        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String celular = request.getParameter("celular");
        String dni = request.getParameter("dni");
        String password = request.getParameter("password");

        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUser(idUser);
        dto.setNombre(nombre);
        dto.setCorreo(correo);
        dto.setCel(celular);
        dto.setDni(dni);
        if (password != null && !password.trim().isEmpty()) {
            dto.setPassword(password);
        }

        UsuarioService service = new UsuarioService();
        boolean exito = service.actualizarPerfil(dto);

        response.setContentType("text/html");
        if (exito) {
            response.getWriter().write("<script>alert('Perfil actualizado con éxito'); window.location='SrvMiCuenta';</script>");
        } else {
            response.getWriter().write("<script>alert('Error al actualizar el perfil'); window.location='SrvMiCuenta';</script>");
        }
    }
}