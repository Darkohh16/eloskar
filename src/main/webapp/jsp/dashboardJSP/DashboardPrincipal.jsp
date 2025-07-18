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
  String rol = (String) session.getAttribute("rol");
  if (rol == null || !(rol.equals("admin") || rol.equals("encargado"))) {
%>
  <script>
    alert("Privilegios inválidos");
    history.back();
  </script>
<%
    return;
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
        <span><%= rol.toUpperCase() %></span>
      </div>
    </header>
    <section class="summary-cards">
      <div class="card">
        <h3>Usuarios</h3>
        <p class="card-number"><%= request.getAttribute("totalUsuarios") != null ? request.getAttribute("totalUsuarios") : 0 %></p>
      </div>
      <div class="card">
        <h3>Reservas Hoy</h3>
        <p class="card-number"><%= request.getAttribute("reservasHoy") != null ? request.getAttribute("reservasHoy") : 0 %></p>
      </div>
      <div class="card">
        <h3>Pedidos Hoy</h3>
        <p class="card-number"><%= request.getAttribute("pedidosHoy") != null ? request.getAttribute("pedidosHoy") : 0 %></p>
      </div>
      <div class="card">
        <h3>Ingresos Hoy</h3>
        <p class="card-number">S/ <%= request.getAttribute("ingresosHoy") != null ? String.format("%.2f", request.getAttribute("ingresosHoy")) : "0.00" %></p>
      </div>
    </section>
    <section class="notifications-section">
      <h4>Notificaciones</h4>
      <ul class="notifications-list">
        <% int reservasPendientes = request.getAttribute("reservasPendientes") != null ? (int) request.getAttribute("reservasPendientes") : 0;
       int pedidosPendientes = request.getAttribute("pedidosPendientes") != null ? (int) request.getAttribute("pedidosPendientes") : 0;
       int productosDeshabilitados = request.getAttribute("productosDeshabilitados") != null ? (int) request.getAttribute("productosDeshabilitados") : 0;
       if (reservasPendientes > 0) { %>
         <li>Reservas pendientes de confirmación: <%= reservasPendientes %></li>
    <% } %>
    <% if (pedidosPendientes > 0) { %>
         <li>Pedidos pendientes de entrega: <%= pedidosPendientes %></li>
    <% } %>
    <% if (productosDeshabilitados > 0) { %>
         <li>Productos Deshabilitados: <%= productosDeshabilitados %></li>
    <% } %>
    <% if (reservasPendientes == 0 && pedidosPendientes == 0 && productosDeshabilitados == 0) { %>
         <li>No hay notificaciones pendientes.</li>
    <% } %>
  </ul>
    </section>
  </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
