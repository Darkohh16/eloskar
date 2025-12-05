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
  <link rel="stylesheet" href="css/stylesDashboard.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
  <div id="menu-lateral"></div>
  <main class="main-content">
    <header class="header">
      <h1>Dashboard</h1>
      <div class="user-info">
        <span><%= rol.toUpperCase() %></span>
      </div>
    </header>

    <div class="dashboard-grid">
      <!-- COLUMNA IZQUIERDA: Métricas principales -->
      <div class="dashboard-left">
        <!-- Métricas de Hoy -->
        <section class="metrics-compact">
          <div class="card-compact">
            <div class="card-icon">📅</div>
            <div class="card-info">
              <h3>Reservas Hoy</h3>
              <p class="card-number"><%= request.getAttribute("reservasHoy") != null ? request.getAttribute("reservasHoy") : 0 %></p>
            </div>
          </div>
          <div class="card-compact">
            <div class="card-icon">🛒</div>
            <div class="card-info">
              <h3>Pedidos Hoy</h3>
              <p class="card-number"><%= request.getAttribute("pedidosHoy") != null ? request.getAttribute("pedidosHoy") : 0 %></p>
            </div>
          </div>
          <div class="card-compact">
            <div class="card-icon">💰</div>
            <div class="card-info">
              <h3>Ingresos Hoy</h3>
              <p class="card-number">S/ <%= request.getAttribute("ingresosHoy") != null ? String.format("%.0f", (Double)request.getAttribute("ingresosHoy")) : "0" %></p>
            </div>
          </div>
          <div class="card-compact">
            <div class="card-icon">📊</div>
            <div class="card-info">
              <h3>Ingresos Mes</h3>
              <p class="card-number">S/ <%= request.getAttribute("ingresosMes") != null ? String.format("%.0f", (Double)request.getAttribute("ingresosMes")) : "0" %></p>
            </div>
          </div>
        </section>

        <!-- Gráficos -->
        <section class="charts-compact">
          <%
            int pedidosPendientes = request.getAttribute("pedidosPendientes") != null ? (Integer)request.getAttribute("pedidosPendientes") : 0;
            int pedidosEntregados = request.getAttribute("pedidosEntregados") != null ? (Integer)request.getAttribute("pedidosEntregados") : 0;
            int totalPedidos = pedidosPendientes + pedidosEntregados;
            double porcentajePendientesPedidos = totalPedidos > 0 ? (pedidosPendientes * 100.0 / totalPedidos) : 0;
            double porcentajeEntregados = totalPedidos > 0 ? (pedidosEntregados * 100.0 / totalPedidos) : 0;
          %>
          <div class="chart-compact">
            <h4>Pedidos</h4>
            <div class="progress-bar-compact">
              <div class="progress-segment pending" style="width: <%= String.format("%.1f", porcentajePendientesPedidos) %>%"></div>
              <div class="progress-segment delivered" style="width: <%= String.format("%.1f", porcentajeEntregados) %>%"></div>
            </div>
            <div class="chart-stats">
              <span class="stat-item"><span class="dot pending"></span>Pendientes: <%= pedidosPendientes %></span>
              <span class="stat-item"><span class="dot delivered"></span>Entregados: <%= pedidosEntregados %></span>
            </div>
          </div>

          <%
            int reservasPendientes = request.getAttribute("reservasPendientes") != null ? (Integer)request.getAttribute("reservasPendientes") : 0;
            int reservasConfirmadas = request.getAttribute("reservasConfirmadas") != null ? (Integer)request.getAttribute("reservasConfirmadas") : 0;
            int totalReservas = reservasPendientes + reservasConfirmadas;
            double porcentajePendientesReservas = totalReservas > 0 ? (reservasPendientes * 100.0 / totalReservas) : 0;
            double porcentajeConfirmadas = totalReservas > 0 ? (reservasConfirmadas * 100.0 / totalReservas) : 0;
          %>
          <div class="chart-compact">
            <h4>Reservas</h4>
            <div class="progress-bar-compact">
              <div class="progress-segment pending" style="width: <%= String.format("%.1f", porcentajePendientesReservas) %>%"></div>
              <div class="progress-segment confirmed" style="width: <%= String.format("%.1f", porcentajeConfirmadas) %>%"></div>
            </div>
            <div class="chart-stats">
              <span class="stat-item"><span class="dot pending"></span>Pendientes: <%= reservasPendientes %></span>
              <span class="stat-item"><span class="dot confirmed"></span>Confirmadas: <%= reservasConfirmadas %></span>
            </div>
          </div>

          <%
            int productosDisponibles = request.getAttribute("productosDisponibles") != null ? (Integer)request.getAttribute("productosDisponibles") : 0;
            int productosDeshabilitados = request.getAttribute("productosDeshabilitados") != null ? (Integer)request.getAttribute("productosDeshabilitados") : 0;
            int totalProductos = productosDisponibles + productosDeshabilitados;
            double porcentajeDisponibles = totalProductos > 0 ? (productosDisponibles * 100.0 / totalProductos) : 0;
          %>
          <div class="chart-compact">
            <h4>Productos</h4>
            <div class="progress-bar-compact">
              <div class="progress-segment available" style="width: <%= String.format("%.1f", porcentajeDisponibles) %>%"></div>
              <div class="progress-segment unavailable" style="width: <%= String.format("%.1f", 100 - porcentajeDisponibles) %>%"></div>
            </div>
            <div class="chart-stats">
              <span class="stat-item"><span class="dot available"></span>Disponibles: <%= productosDisponibles %></span>
              <span class="stat-item"><span class="dot unavailable"></span>Deshabilitados: <%= productosDeshabilitados %></span>
            </div>
          </div>
        </section>
      </div>

      <!-- COLUMNA DERECHA: Notificaciones y Resumen -->
      <div class="dashboard-right">
        <!-- Notificaciones -->
        <section class="notifications-compact">
          <h4>🔔 Notificaciones</h4>
          <ul class="notifications-list-compact">
            <% if (reservasPendientes > 0) { %>
            <li class="notification-item warning">
              <span class="notification-icon">⏳</span>
              <span class="notification-text">Reservas por confirmar: <strong><%= reservasPendientes %></strong></span>
            </li>
            <% } %>
            <% if (pedidosPendientes > 0) { %>
            <li class="notification-item info">
              <span class="notification-icon">📦</span>
              <span class="notification-text">Pedidos por entregar: <strong><%= pedidosPendientes %></strong></span>
            </li>
            <% } %>
            <% if (productosDeshabilitados > 0) { %>
            <li class="notification-item alert">
              <span class="notification-icon">🚫</span>
              <span class="notification-text">Productos deshabilitados: <strong><%= productosDeshabilitados %></strong></span>
            </li>
            <% } %>
            <% if (reservasPendientes == 0 && pedidosPendientes == 0 && productosDeshabilitados == 0) { %>
            <li class="notification-item success">
              <span class="notification-icon">✅</span>
              <span class="notification-text">Todo al día</span>
            </li>
            <% } %>
          </ul>
        </section>

        <!-- Resumen Rápido -->
        <section class="quick-summary">
          <h4>📈 Resumen General</h4>
          <div class="summary-item">
            <span class="summary-label">Total Usuarios</span>
            <span class="summary-value"><%= request.getAttribute("totalUsuarios") != null ? request.getAttribute("totalUsuarios") : 0 %></span>
          </div>
          <div class="summary-item">
            <span class="summary-label">Ingresos Totales</span>
            <span class="summary-value">S/ <%= request.getAttribute("ingresosTotales") != null ? String.format("%.0f", (Double)request.getAttribute("ingresosTotales")) : "0" %></span>
          </div>
          <div class="summary-item">
            <span class="summary-label">Promedio Venta</span>
            <span class="summary-value">S/ <%= request.getAttribute("promedioVenta") != null ? String.format("%.0f", (Double)request.getAttribute("promedioVenta")) : "0" %></span>
          </div>
          <div class="summary-item">
            <span class="summary-label">Total Productos</span>
            <span class="summary-value"><%= totalProductos %></span>
          </div>
        </section>
      </div>
    </div>
  </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
