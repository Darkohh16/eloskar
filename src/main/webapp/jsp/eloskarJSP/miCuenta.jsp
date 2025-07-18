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
      <div class="perfil-form-vertical">
        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" value="<%= usuario != null ? usuario.getNombre() : "" %>" required>
        <label for="correo">Correo:</label>
        <input type="email" id="correo" name="correo" value="<%= usuario != null ? usuario.getCorreo() : "" %>" required>
        <label for="celular">Celular:</label>
        <input type="text" id="celular" name="celular" value="<%= usuario != null ? usuario.getCel() : "" %>" required pattern="[0-9]{9}">
        <label for="dni">DNI:</label>
        <input type="text" id="dni" name="dni" value="<%= usuario != null ? usuario.getDni() : "" %>" required pattern="[0-9]{8}">
        <label for="password">Contraseña:</label>
        <input type="password" id="password" name="password" placeholder="••••••••">
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
          <th>Acciones</th>
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
          <td><button class="btn-detalles" type="button">Detalles</button></td>
        </tr>
        <%   }
           } else { %>
        <tr>
          <td>1001</td>
          <td>2024-07-10 13:45</td>
          <td>S/ 85.00</td>
          <td><span class="estado pendiente">Pendiente</span></td>
          <td>Delivery</td>
          <td><button class="btn-detalles" type="button">Detalles</button></td>
        </tr>
        <tr>
          <td>1002</td>
          <td>2024-07-12 15:20</td>
          <td>S/ 120.00</td>
          <td><span class="estado entregado">Entregado</span></td>
          <td>Recoger</td>
          <td><button class="btn-detalles" type="button">Detalles</button></td>
        </tr>
        <tr>
          <td>1003</td>
          <td>2024-07-15 18:00</td>
          <td>S/ 60.00</td>
          <td><span class="estado cancelada">Cancelada</span></td>
          <td>Delivery</td>
          <td><button class="btn-detalles" type="button">Detalles</button></td>
        </tr>
        <% } %>
      </tbody>
    </table>
  </section>
</main>

<jsp:include page="components/footer.jsp" />

<!-- Modal de detalles de pedido (estático de ejemplo) -->
<div class="modal-detalles" id="modalDetalles">
  <div class="modal-detalles-content">
    <button class="btn-cerrar-modal" id="cerrarModalDetalles" title="Cerrar">&times;</button>
    <h3>Detalles del Pedido #1001</h3>
    <table>
      <thead>
        <tr>
          <th>Producto</th>
          <th>Cantidad</th>
          <th>Precio Unitario</th>
          <th>Subtotal</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Ceviche Mixto</td>
          <td>2</td>
          <td>S/ 30.00</td>
          <td>S/ 60.00</td>
        </tr>
        <tr>
          <td>Chicha Morada</td>
          <td>1</td>
          <td>S/ 8.00</td>
          <td>S/ 8.00</td>
        </tr>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="3" style="text-align:right;font-weight:bold;">Total:</td>
          <td>S/ 68.00</td>
        </tr>
      </tfoot>
    </table>
  </div>
</div>

<script src="js/scriptMiCuenta.js"></script>
</body>
</html> 