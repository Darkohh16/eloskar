<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.eloskar.restaurante.DTO.PedidoDTO" %>
<%
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
    <title>Pedidos - Restaurante</title>
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
            <h1>Gestión de Pedidos</h1>
            <div class="user-info">
                <span>Admin</span>
            </div>
        </header>
        <section class="filters-section">
            <form class="filters-form" method="get" action="SrvListarPedidoDashboard">
                <input type="text" name="cliente" value="<%= request.getParameter("cliente") != null ? request.getParameter("cliente") : "" %>" placeholder="Buscar por cliente..." class="input-search">
                <select name="estado">
                    <option value="">Todos</option>
                    <option value="pendiente" <%= "pendiente".equals(request.getParameter("estado")) ? "selected" : "" %>>Pendiente</option>
                    <option value="entregado" <%= "entregado".equals(request.getParameter("estado")) ? "selected" : "" %>>Entregado</option>
                    <option value="cancelado" <%= "cancelado".equals(request.getParameter("estado")) ? "selected" : "" %>>Cancelado</option>
                </select>
                <input type="date" name="fecha" value="<%= request.getParameter("fecha") != null ? request.getParameter("fecha") : "" %>" class="input-date">
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
                    <th>Total</th>
                    <th>Estado</th>
                    <th>Método de Pago</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <% 
                  List<PedidoDTO> pedidos = (List<PedidoDTO>) request.getAttribute("pedidos");
                  Map<Integer, String> nombresUsuarios = (Map<Integer, String>) request.getAttribute("nombresUsuarios");
                  if (pedidos != null && !pedidos.isEmpty()) {
                    for (PedidoDTO p : pedidos) {
                %>
                <tr>
                    <td><%= p.getIdPedid() %></td>
                    <td><%= nombresUsuarios.get(p.getUsuario_id()) %></td>
                    <td><%= p.getFecha() != null && p.getFecha().length() >= 16 ? p.getFecha().substring(0,16) : p.getFecha() %></td>
                    <td>S/ <%= String.format("%.2f", p.getTotal()) %></td>
                    <td>
                      <span class="estado <%= p.getEstado().toLowerCase().trim() %>">
                        <%= p.getEstado().substring(0,1).toUpperCase() + p.getEstado().substring(1).toLowerCase() %>
                      </span>
                    </td>
                    <td><%= p.getMetodo_pago_id() == 1 ? "Efectivo" : p.getMetodo_pago_id() == 2 ? "Tarjeta" : p.getMetodo_pago_id() == 3 ? "Yape" : "-" %></td>
                    <td>
                        <% if ("pendiente".equals(p.getEstado())) { %>
                            <form method="post" action="SrvActualizarEstadoPedido" style="display:inline;">
                                <input type="hidden" name="id" value="<%= p.getIdPedid() %>" />
                                <input type="hidden" name="estado" value="entregado" />
                                <button type="submit" class="btn-accion entregar">Entregar</button>
                            </form>
                            <form method="post" action="SrvActualizarEstadoPedido" style="display:inline;">
                                <input type="hidden" name="id" value="<%= p.getIdPedid() %>" />
                                <input type="hidden" name="estado" value="cancelado" />
                                <button type="submit" class="btn-accion cancelar">Cancelar</button>
                            </form>
                        <% } else if ("entregado".equals(p.getEstado())) { %>
                            <!-- No acciones -->
                        <% } else if ("cancelado".equals(p.getEstado())) { %>
                            <!-- No acciones -->
                        <% } %>
                    </td>
                </tr>
                <%   }
                  } else { %>
                <tr><td colspan="7" style="text-align:center;">No hay pedidos registrados.</td></tr>
                <% } %>
                </tbody>
            </table>
        </section>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/scriptPedido.js"></script>
</body>
</html>
