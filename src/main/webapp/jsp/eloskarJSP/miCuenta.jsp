<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  com.eloskar.restaurante.DTO.UsuarioDTO usuario = (com.eloskar.restaurante.DTO.UsuarioDTO) session.getAttribute("usuario");
  java.util.List<com.eloskar.restaurante.DTO.PedidoDTO> pedidos = (java.util.List<com.eloskar.restaurante.DTO.PedidoDTO>) request.getAttribute("pedidosUsuario");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mi Cuenta - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesMiCuenta.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<jsp:include page="components/header.jsp" />

<main class="micuenta-main">
  <section class="perfil-section">
    <h1 class="main-title">Mi Perfil</h1>
    <form id="formPerfil" method="post" action="SrvActualizarPerfil">
      <div class="perfil-form-grid">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" name="nombre" value="<%= usuario != null ? usuario.getNombre() : "" %>" required>
        </div>
        <div>
          <label for="correo">Correo:</label>
          <input type="email" id="correo" name="correo" value="<%= usuario != null ? usuario.getCorreo() : "" %>" required>
        </div>
        <div>
          <label for="celular">Celular:</label>
          <input type="text" id="celular" name="celular" value="<%= usuario != null ? usuario.getCel() : "" %>" required pattern="[0-9]{9}">
        </div>
        <div>
          <label for="dni">DNI:</label>
          <input type="text" id="dni" name="dni" value="<%= usuario != null ? usuario.getDni() : "" %>" required pattern="[0-9]{8}">
        </div>
        <div>
          <label for="password">Contraseña:</label>
          <input type="password" id="password" name="password" placeholder="••••••••">
        </div>
      </div>
      <button type="submit" class="btn-guardar">Guardar Cambios</button>
    </form>
  </section>

  <section class="pedidos-section">
    <h2 class="main-title">Mis Pedidos</h2>
    <table class="main-table">
      <thead>
        <tr>
          <th>ID</th>
          <th>Fecha</th>
          <th>Total</th>
          <th>Estado</th>
          <th>Entrega</th>
        </tr>
      </thead>
      <tbody>
        <% if (pedidos != null && !pedidos.isEmpty()) {
             for (com.eloskar.restaurante.DTO.PedidoDTO p : pedidos) { %>
        <tr>
          <td><%= p.getIdPedid() %></td>
          <td><%= p.getFecha() %></td>
          <td>S/ <%= p.getTotal() %></td>
          <td><span class="estado <%= p.getEstado() %>"><%= p.getEstado().substring(0,1).toUpperCase() + p.getEstado().substring(1) %></span></td>
          <td><%= p.getTipo_entrega() != null ? (p.getTipo_entrega().equals("pickup") ? "Recoger" : "Delivery") : "" %></td>
        </tr>
        <%   }
           } else { %>
        <tr><td colspan="5" class="tabla-pedidos-vacia">No tienes pedidos registrados.</td></tr>
        <% } %>
      </tbody>
    </table>
  </section>
</main>

<jsp:include page="components/footer.jsp" />
<script src="js/scriptMiCuenta.js"></script>
</body>
</html> 