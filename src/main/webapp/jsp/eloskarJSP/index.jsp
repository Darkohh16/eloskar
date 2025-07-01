<%@ page import="com.eloskar.restaurante.DTO.CategoriaDTO" %>
<%@ page import="com.eloskar.restaurante.DTO.ProductoDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 30/06/2025
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ProductoDTO> productos = (List<ProductoDTO>) request.getAttribute("producto");
  if (productos == null) {
    response.sendRedirect(request.getContextPath() + "/SrvBuscarProducto?ruta=/jsp/eloskarJSP/index.jsp");
    return;
  }
  List<CategoriaDTO> categorias = (List<CategoriaDTO>) request.getAttribute("categoria");
%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>El Oskar - Nuestra Carta</title>
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" href="css/stylesPrincipal.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<!-- Header -->
<header class="header">
  <div class="logo">
    <img src="img/logo.jpg" alt="El Oskar">
    <span>El Oskar</span>
  </div>
  <nav class="nav">
    <a href="jsp/dashboardJSP/DashboardPrincipal.jsp">Dashboard</a>
    <a href="#">Inicio</a>
    <a href="#">Carta</a>
    <a href="#">Reservas</a>
    <a href="#">Contacto</a>
    <a href="#" class="cart">Carrito <span class="cart-badge">1</span></a>
    <a href="#" class="account">Mi Cuenta</a>
  </nav>
</header>

<!-- Título principal -->
<main>
  <h1 class="main-title">Nuestra Carta</h1>

  <%
    for (CategoriaDTO categoria : categorias) {
  %>
  <section class="menu-section">
    <h2 class="section-title"><%= categoria.getNombre() %></h2>
    <div class="menu-items">

      <%
        for (ProductoDTO p : productos) {
          if (p.getCategoria().getNombre().equals(categoria.getNombre())) {
      %>
      <div class="menu-card <%= !p.isDisponible() ? "no-disponible": "" %> ">
        <img src="<%= p.getImagen() %>" alt="<%= p.getNombre() %>">
        <div class="menu-info">
          <h3><%= p.getNombre() %></h3>
          <p><%= p.getDescripcion() %></p>
          <span class="price">S/. <%= p.getPrecio() %></span>

          <% if (!p.isDisponible()) { %>
          <p class="no-disponible-msg">Este producto está agotado para envío, pero puede realizar una reservación.</p>
          <% } %>

          <% if (p.isDisponible()) { %>
          <button class="add-cart">
            <i class="icon-cart"></i>
          </button>
          <% } %>
        </div>
      </div>
      <%
          }
        }
      %>
    </div>
  </section>
  <%
    }
  %>
</main>

<!-- Footer -->
<footer class="footer">
  <div class="footer-section">
    <h4>Horario</h4>
    <p>Lunes a Domingo<br>11:00 AM - 6:00 PM</p>
  </div>
  <div class="footer-section">
    <h4>Ubicación</h4>
    <p>Lima 204, Pimentel</p>
  </div>
  <div class="footer-section">
    <h4>Contacto</h4>
    <p>Tel: (01) 555-0123<br>Email: eloskar@cevicheria.com</p>
  </div>
</footer>

<script src="script.js"></script>
</body>
</html>

