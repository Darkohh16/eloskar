<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.eloskar.restaurante.DTO.ReservaDTO" %>
<%
  Integer idUser = (Integer) session.getAttribute("idUser");
  String nombreUsuario = (String) session.getAttribute("nombre");
%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservas - Restaurante</title>
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body data-context-path="${pageContext.request.contextPath}" data-user-id="<%= idUser != null ? idUser : "" %>">
<div class="container">
  <div id="menu-lateral"></div>
  <main class="main-content">
    <header class="header">
      <h1>Gestión de Reservas</h1>
      <div class="user-info">
        <span>Admin</span>
      </div>
    </header>
    <section class="filters-section">
      <form class="filters-form">
        <input type="text" placeholder="Buscar por cliente, fecha o estado..." class="input-search">
        <select>
          <option value="">Estado</option>
          <option value="pendiente">Pendiente</option>
          <option value="confirmada">Confirmada</option>
          <option value="cancelada">Cancelada</option>
        </select>
        <input type="date" placeholder="Fecha" class="input-date">
        <button type="submit" class="btn-filtrar">Filtrar</button>
      </form>
    </section>
    <section class="table-section">
      <table class="main-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Cliente</th>
          <th>Fecha</th>
          <th>Hora</th>
          <th>Personas</th>
          <th>Estado</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <% 
          List<ReservaDTO> reservas = (List<ReservaDTO>) request.getAttribute("reservas");
          Map<Integer, String> nombresUsuarios = (Map<Integer, String>) request.getAttribute("nombresUsuarios");
          if (reservas != null && !reservas.isEmpty()) {
            for (ReservaDTO r : reservas) {
        %>
        <tr>
          <td><%= r.getIdReser() %></td>
          <td><%= nombresUsuarios.get(r.getUsuario_id()) %></td>
          <td><%= r.getFecha() %></td>
          <td><%= r.getHora() != null && r.getHora().length() >= 5 ? r.getHora().substring(0,5) : r.getHora() %></td>
          <td><%= r.getCantidad_personas() %></td>
          <td><span class="estado <%= r.getEstado() %>"><%= r.getEstado().substring(0,1).toUpperCase() + r.getEstado().substring(1) %></span></td>
          <td>
            <% if ("pendiente".equals(r.getEstado())) { %>
              <form method="post" action="SrvActualizarEstadoReserva" style="display:inline;">
                <input type="hidden" name="id" value="<%= r.getIdReser() %>" />
                <input type="hidden" name="estado" value="confirmada" />
                <button type="submit" class="btn-accion confirmar">Confirmar</button>
              </form>
              <form method="post" action="SrvActualizarEstadoReserva" style="display:inline;">
                <input type="hidden" name="id" value="<%= r.getIdReser() %>" />
                <input type="hidden" name="estado" value="cancelada" />
                <button type="submit" class="btn-accion cancelar">Cancelar</button>
              </form>
            <% } else if ("confirmada".equals(r.getEstado())) { %>
              <form method="post" action="SrvActualizarEstadoReserva" style="display:inline;">
                <input type="hidden" name="id" value="<%= r.getIdReser() %>" />
                <input type="hidden" name="estado" value="cancelada" />
                <button type="submit" class="btn-accion cancelar">Cancelar</button>
              </form>
            <% } %>
          </td>
        </tr>
        <%   }
          } else { %>
        <tr><td colspan="7" style="text-align:center;">No hay reservas registradas.</td></tr>
        <% } %>
        </tbody>
      </table>
    </section>
  </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/scriptReserva.js"></script>
</body>
</html>
