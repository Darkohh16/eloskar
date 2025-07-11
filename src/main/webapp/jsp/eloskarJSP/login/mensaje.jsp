<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 09/07/2025
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link href="https://fonts.cdnfonts.com/css/radley" rel="stylesheet" />
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <link rel="stylesheet" href="css/stylesLogin.css" />
  <title>Correo enviado - El Oskar</title>
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
  <div class="centrado">
    <div class="card">
      <h1 class="titulo">¡Revisa tu correo!</h1>

      <div style="text-align: center; margin-bottom: 25px">
        <img src="img/logo.png" alt="Logo El Oskar" class="logo" />
      </div>

      <p
              class="texto-secundario"
              style="text-align: center; font-size: 16px"
      >
        Te hemos enviado un enlace para restablecer tu contraseña. Si no lo
        ves, revisa tu bandeja de spam o verifica que el correo ingresado sea el correcto.
      </p>

      <div
              class="texto-secundario links"
              style="text-align: center; margin-top: 20px"
      >
        <a href="jsp/eloskarJSP/login/login.jsp">Volver al inicio de sesión</a>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="js/scriptLogin.js"></script>
</body>
</html>

