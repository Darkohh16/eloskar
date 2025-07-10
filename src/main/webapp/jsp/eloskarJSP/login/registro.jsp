<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 09/07/2025
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://fonts.cdnfonts.com/css/radley" rel="stylesheet" />
    <title>Registrarse - El Oskar</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="icon" href="img/logo.png" type="image/jpg">
    <link rel="stylesheet" href="css/stylesLogin.css" />
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
    <div class="centrado">
        <div class="card">
            <h1 class="titulo">REGISTRARSE</h1>

            <!-- ✅ Logo justo después del título -->
            <div style="text-align: center; margin-bottom: 25px">
                <img src="img/logo.png" alt="Logo El Oskar" class="logo" />
            </div>

            <form action="#" method="POST">
                <input
                        class="input-text"
                        type="text"
                        name="dni"
                        placeholder="DNI"
                        required
                />
                <input
                        class="input-text"
                        type="text"
                        name="nombre"
                        placeholder="Nombre completo"
                        required
                />
                <input
                        class="input-text"
                        type="text"
                        name="celular"
                        placeholder="Celular"
                        required
                />
                <input
                        class="input-text"
                        type="email"
                        name="correo"
                        placeholder="Correo electrónico"
                        required
                />
                <input
                        class="input-text"
                        type="password"
                        name="password"
                        placeholder="Contraseña"
                        required
                />
                <input
                        class="input-text"
                        type="password"
                        name="confirm_password"
                        placeholder="Confirmar contraseña"
                        required
                />
                <button class="boton" type="submit">Registrarse</button>
            </form>

            <div class="texto-secundario links" style="margin-top: 15px">
                ¿Ya tienes una cuenta? <a href="jsp/eloskarJSP/login/login.jsp">Inicia sesión</a>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/scriptLogin.js"></script>
</body>
</html>

