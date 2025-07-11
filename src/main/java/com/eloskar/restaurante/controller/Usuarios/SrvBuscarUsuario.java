package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DAO.UsuarioDAO;
import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvBuscarUsuario", value = "/SrvBuscarUsuario")
public class SrvBuscarUsuario extends HttpServlet {

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
        String filtro = request.getParameter("filtro");
        if (filtro == null) {
            filtro = "";
        }

        UsuarioService service = new UsuarioService();
        List<UsuarioDTO> usuarios = service.cargarDatosUsuarios(filtro);

        request.setAttribute("usuario", usuarios);
        RequestDispatcher dp = request.getRequestDispatcher("/jsp/dashboardJSP/Usuarios.jsp");
        dp.forward(request, response);
    }
}
