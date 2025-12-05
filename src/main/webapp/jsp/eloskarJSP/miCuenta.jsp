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
  <h1 class="page-title">
    <span class="icon-page">👤</span>
    Mi Cuenta
  </h1>

  <div class="micuenta-container">
    <!-- Columna Izquierda: Mi Perfil -->
    <section class="perfil-section">
      <div class="perfil-card">
        <h2 class="section-title">
          <span class="icon-section">📋</span>
          Mi Perfil
        </h2>

        <div class="usuario-avatar">
          <div class="avatar-circle">
            <%= usuario != null && usuario.getNombre() != null ? usuario.getNombre().substring(0, 1).toUpperCase() : "U" %>
          </div>
          <h3 class="usuario-nombre"><%= usuario != null ? usuario.getNombre() : "" %></h3>
        </div>

        <form id="formPerfil" method="post" action="SrvActualizarPerfil" class="perfil-form">
          <div class="form-group">
            <label for="nombre">
              <span class="label-icon">👤</span>
              Nombre Completo
            </label>
            <input type="text" id="nombre" name="nombre" value="<%= usuario != null ? usuario.getNombre() : "" %>" required placeholder="Ingresa tu nombre">
          </div>

          <div class="form-group">
            <label for="correo">
              <span class="label-icon">📧</span>
              Correo Electrónico
            </label>
            <input type="email" id="correo" name="correo" value="<%= usuario != null ? usuario.getCorreo() : "" %>" required placeholder="tu@email.com">
          </div>

          <div class="form-group">
            <label for="celular">
              <span class="label-icon">📱</span>
              Celular
            </label>
            <input type="text" id="celular" name="celular" value="<%= usuario != null ? usuario.getCel() : "" %>" required pattern="[0-9]{9}" placeholder="999999999">
          </div>

          <div class="form-group">
            <label for="dni">
              <span class="label-icon">🪪</span>
              DNI
            </label>
            <input type="text" id="dni" name="dni" value="<%= usuario != null ? usuario.getDni() : "" %>" required pattern="[0-9]{8}" placeholder="12345678">
          </div>

          <div class="form-group">
            <label for="password">
              <span class="label-icon">🔒</span>
              Contraseña
            </label>
            <input type="password" id="password" name="password" placeholder="••••••••" required>
          </div>

          <button type="submit" class="btn-guardar-perfil">
            <span class="btn-icon">💾</span>
            Guardar Cambios
          </button>
        </form>
      </div>
    </section>

    <!-- Columna Derecha: Mis Pedidos -->
    <section class="pedidos-section">
      <div class="pedidos-card">
        <h2 class="section-title">
          <span class="icon-section">🛍️</span>
          Mis Pedidos
        </h2>
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
          <td>
            <%
              String fecha = p.getFecha();
              String fechaSolo = "";
              String horaMin = "";
              if (fecha != null && fecha.length() >= 16) {
                try {
                  // Parse la fecha/hora desde el formato almacenado
                  java.text.SimpleDateFormat sdfInput = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  sdfInput.setTimeZone(java.util.TimeZone.getTimeZone("UTC")); // La BD guarda en UTC

                  java.util.Date fechaDate = sdfInput.parse(fecha);

                  // Convertir a zona horaria de Perú (UTC-5)
                  java.text.SimpleDateFormat sdfOutput = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
                  sdfOutput.setTimeZone(java.util.TimeZone.getTimeZone("America/Lima"));

                  String fechaFormateada = sdfOutput.format(fechaDate);
                  fechaSolo = fechaFormateada.substring(0, 10);
                  horaMin = fechaFormateada.substring(11, 16);
                } catch (Exception e) {
                  // Si hay error, usar el formato original
                  fechaSolo = fecha.substring(0, 10);
                  horaMin = fecha.substring(11, 16);
                }
              }
            %>
            <%= fechaSolo %> <%= horaMin %>
          </td>
          <td>S/ <%= p.getTotal() %></td>
          <td><span class="estado <%= p.getEstado() %>"><%= p.getEstado().substring(0,1).toUpperCase() + p.getEstado().substring(1) %></span></td>
          <td><%= p.getTipo_entrega() != null ? (p.getTipo_entrega().equals("pickup") ? "Recoger" : "Delivery") : "" %></td>
          <td><button class="btn-detalles" type="button" data-id="<%= p.getIdPedid() %>">Detalles</button></td>
        </tr>
        <%   }
           } else { %>
        <tr>
          <td>1001</td>
          <td>2024-07-10 13:45</td>
          <td>S/ 85.00</td>
          <td><span class="estado pendiente">Pendiente</span></td>
          <td>Delivery</td>
          <td><button class="btn-detalles" type="button" data-id="1001">Detalles</button></td>
        </tr>
        <tr>
          <td>1002</td>
          <td>2024-07-12 15:20</td>
          <td>S/ 120.00</td>
          <td><span class="estado entregado">Entregado</span></td>
          <td>Recoger</td>
          <td><button class="btn-detalles" type="button" data-id="1002">Detalles</button></td>
        </tr>
        <tr>
          <td>1003</td>
          <td>2024-07-15 18:00</td>
          <td>S/ 60.00</td>
          <td><span class="estado cancelada">Cancelada</span></td>
          <td>Delivery</td>
          <td><button class="btn-detalles" type="button" data-id="1003">Detalles</button></td>
        </tr>
        <% } %>
      </tbody>
        </table>
      </div>
    </section>
  </div>
</main>

<jsp:include page="components/footer.jsp" />

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

<script src="js/mobileMenu.js"></script>
<script src="js/scriptMiCuenta.js"></script>
</body>
</html> 