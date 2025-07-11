<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 09/07/2025
  Time: 23:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String token = request.getParameter("token");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://fonts.cdnfonts.com/css/radley" rel="stylesheet" />
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="img/logo.png" type="image/jpg">
    <link rel="stylesheet" href="css/stylesLogin.css" />
    <title>Restablecer contraseña - El Oskar</title>
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
    <div class="centrado">
        <div class="card">
            <h1 class="titulo">RESTABLECER CONTRASEÑA</h1>

            <div style="text-align: center; margin-bottom: 25px">
                <img src="img/logo.png" alt="Logo El Oskar" class="logo" />
            </div>

            <form action="SrvCambiarContrasenia" method="POST">
                <input type="hidden" name="token" value="<%= token %>" />
                <input
                        class="input-text"
                        type="password"
                        name="nueva_password"
                        placeholder="Nueva contraseña"
                        required
                />
                <input
                        class="input-text"
                        type="password"
                        name="confirmar_password"
                        placeholder="Confirmar nueva contraseña"
                        required
                />
                <button class="boton" type="submit">Guardar contraseña</button>
            </form>


            <div class="texto-secundario links" style="margin-top: 15px">
                <a href="jsp/eloskarJSP/login/login.jsp">Volver al inicio de sesión</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/scriptLogin.js"></script>
</body>
</html>

