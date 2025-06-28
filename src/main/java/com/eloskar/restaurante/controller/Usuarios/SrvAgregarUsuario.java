package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.UsuarioDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvAgregarUsuario", value = "/SrvAgregarUsuario")
public class SrvAgregarUsuario extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        UsuarioDTO dto = new UsuarioDTO();
        dto.setDni(request.getParameter("dni"));
        dto.setCel(request.getParameter("celular"));
        dto.setNombre(request.getParameter("nombre"));
        dto.setCorreo(request.getParameter("correo"));
        dto.setPassword(request.getParameter("password"));
        if (dto.insertarU()){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Usuario registrado con éxito'); window.location='jsp/Usuarios.jsp';</script>");
        }
    }
}
