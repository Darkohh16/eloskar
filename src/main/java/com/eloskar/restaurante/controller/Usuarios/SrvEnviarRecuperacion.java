package com.eloskar.restaurante.controller.Usuarios;

import com.eloskar.restaurante.DTO.TokensDTO;
import com.eloskar.restaurante.DTO.UsuarioDTO;
import com.eloskar.restaurante.services.TokensService;
import com.eloskar.restaurante.services.UsuarioService;
import com.eloskar.restaurante.util.CorreoUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@WebServlet(name = "SrvEnviarRecuperacion", value = "/SrvEnviarRecuperacion")
public class SrvEnviarRecuperacion extends HttpServlet {

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
        TokensService serviceTokens = new TokensService();
        TokensDTO dto = new TokensDTO();
        int idUser = service.obtenerId(request.getParameter("correo"));

        if (idUser != 0) {
            String token = UUID.randomUUID().toString();
            LocalDateTime expiracion = LocalDateTime.now().plusMinutes(60);
            dto.setToken(token);
            dto.setExpiracion(expiracion.toString());
            dto.setIdUser(idUser);

            serviceTokens.insertarToken(dto);
            UsuarioDTO dtoU = service.obtenerDatosRecuperacion(idUser);

            String baseUrl = request.getRequestURL().toString().replace(request.getRequestURI(), request.getContextPath());
            String linkRecuperacion = baseUrl + "/jsp/eloskarJSP/login/restablecer.jsp?token=" + token;

            String asunto = "Recupera tu contraseña";
            String cuerpo = "Hola " + dtoU.getNombre() + ",\n\n"
                    + "Haz clic en el siguiente enlace para restablecer tu contraseña:\n"
                    + linkRecuperacion + "\n\n"
                    + "Este enlace expirará en 1 hora.";

            CorreoUtil.enviar(dtoU.getCorreo(), asunto, cuerpo);

            RequestDispatcher dp = request.getRequestDispatcher("/jsp/eloskarJSP/login/mensaje.jsp");
            dp.forward(request, response);
        }

    }
}
