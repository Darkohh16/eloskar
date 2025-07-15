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
  List<CategoriaDTO> categorias = (List<CategoriaDTO>) request.getAttribute("categoria");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>El Oskar - Nuestra Carta</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">

<!-- Include Header -->
<jsp:include page="/jsp/eloskarJSP/components/header.jsp" />

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
          <form action="SrvAgregarAlCarrito" method="post" style="display:inline;">
            <input type="hidden" name="idProducto" value="<%= p.getIdProd() %>"/>
            <button class="add-cart" type="submit">
              <i class="icon-cart"></i>
            </button>
          </form>
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

<!-- Include Footer -->
<jsp:include page="/jsp/eloskarJSP/components/footer.jsp" />

<script src="script.js"></script>
<script src="js/scriptCarrito.js"></script>
</body>
</html>

