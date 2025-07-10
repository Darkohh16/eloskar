<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 09/07/2025
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link href="https://fonts.cdnfonts.com/css/radley" rel="stylesheet" />
  <title>Iniciar sesión - El Oskar</title>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <link rel="stylesheet" href="css/stylesLogin.css" />
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
  <div class="centrado">
    <div class="card">
      <h1 class="titulo">BIENVENIDOS</h1>
      <img src="img/logo.png" alt="Logo El Oskar" class="logo" />

      <form action="SrvEncontrarUsuario" method="POST">
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
        <button class="boton" type="submit">Iniciar sesión</button>
      </form>

      <div class="texto-secundario links">
        <a href="jsp/eloskarJSP/login/registro.jsp">Registrarse</a><br />
        <a href="jsp/eloskarJSP/login/recuperar.jsp">¿Olvidaste tu contraseña?</a>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/scriptLogin.js"></script>
</body>
</html>

