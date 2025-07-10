<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int idUser;
  String rol = "";
  if (session != null && session.getAttribute("idUser") != null) {
    idUser = (Integer) session.getAttribute("idUser");
    rol = (String) session.getAttribute("rol");
  }

  if (!"admin".equals(rol)) {
%>
  <script>
    alert("Acceso denegado. Solo el administrador puede ingresar aquí.");
    history.back();
  </script>
<%
  }
%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dashboard Principal - Restaurante</title>
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
  <div id="menu-lateral"></div>
  <main class="main-content">
    <header class="header">
      <h1>Dashboard Principal</h1>
      <div class="user-info">
        <span>Admin</span>
      </div>
    </header>
    <section class="summary-cards">
      <div class="card">
        <h3>Usuarios</h3>
        <p class="card-number">120</p>
      </div>
      <div class="card">
        <h3>Reservas Hoy</h3>
        <p class="card-number">15</p>
      </div>
      <div class="card">
        <h3>Pedidos Hoy</h3>
        <p class="card-number">32</p>
      </div>
      <div class="card">
        <h3>Ingresos Hoy</h3>
        <p class="card-number">S/ 1,250.00</p>
      </div>
    </section>
    <section class="charts-section">
      <div class="chart-card">
        <h4>Reservas por Estado</h4>
        <div class="chart-placeholder">[Gráfica de pastel]</div>
      </div>
      <div class="chart-card">
        <h4>Pedidos por Estado</h4>
        <div class="chart-placeholder">[Gráfica de barras]</div>
      </div>
      <div class="chart-card">
        <h4>Ingresos por Mes</h4>
        <div class="chart-placeholder">[Gráfica de líneas]</div>
      </div>
      <div class="chart-card">
        <h4>Productos más vendidos</h4>
        <div class="chart-placeholder">[Gráfica de barras]</div>
      </div>
    </section>
    <section class="notifications-section">
      <h4>Notificaciones</h4>
      <ul class="notifications-list">
        <li>3 reservas pendientes de confirmación</li>
        <li>2 productos sin stock</li>
        <li>5 pedidos pendientes de entrega</li>
      </ul>
    </section>
  </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
