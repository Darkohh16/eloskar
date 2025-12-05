<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
  <base href="${pageContext.request.contextPath}/">
  <title></title>
</head>
<aside class="sidebar">
  <h2>El Oskar</h2>
  <nav>
    <ul>
      <li><a href="SrvDashboardPrincipal">Dashboard</a></li>
      <li><a href="SrvListarReservasDashboard">Reservas</a></li>
      <li><a href="SrvListarPedidoDashboard">Pedidos</a></li>
      <li><a href="SrvBuscarProducto?destino=dashboard">Productos</a></li>
      <li><a href="SrvBuscarUsuario">Usuarios</a></li>
      <li><a href="SrvBuscarProducto">Volver al inicio</a></li>
    </ul>
  </nav>
</aside>
