<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Aquí normalmente recibirías los datos del carrito y usuario desde el backend
  // Para el diseño, se usan datos de ejemplo
  String nombreUsuario = (String) session.getAttribute("nombre");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Confirmar Pedido - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesPedidos.css">
</head>
<body data-context-path="${pageContext.request.contextPath}" data-user-id="<%= session.getAttribute("idUser") != null ? session.getAttribute("idUser") : "" %>">

<!-- Include Header -->
<jsp:include page="components/header.jsp" />

<main class="pedido-main">
  <h1 class="main-title">Confirmar Pedido</h1>

  <section class="resumen-pedido-section" style="max-width: 900px; margin: 0 auto 32px auto;">
    <h2 style="color: #009fe3; margin-bottom: 18px;">Resumen de tu pedido</h2>
    <table class="main-table">
      <thead>
        <tr>
          <th>Producto</th>
          <th>Cantidad</th>
          <th>Precio Unitario</th>
          <th>Subtotal</th>
        </tr>
      </thead>
      <tbody>
        <%-- Ejemplo de productos en el pedido --%>
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
        <%-- Fin ejemplo --%>
      </tbody>
      <tfoot>
        <tr>
          <td colspan="3" style="text-align: right; font-weight: bold;">Envío:</td>
          <td>S/ 5.00</td>
        </tr>
        <tr>
          <td colspan="3" style="text-align: right; font-weight: bold;">Total:</td>
          <td>S/ 73.00</td>
        </tr>
      </tfoot>
    </table>
  </section>

  <section class="datos-pedido-section" style="max-width: 600px; margin: 0 auto 32px auto;">
    <h2 style="color: #009fe3; margin-bottom: 18px;">Datos para el pedido</h2>
    <form id="formPedido" method="post" action="SrvConfirmarPedido">
      <div style="display: flex; gap: 18px; flex-wrap: wrap; align-items: flex-end;">
        <!-- Elimino el campo de nombre y celular, ya no son necesarios -->
        <div style="flex: 1 1 220px; display: flex; align-items: center; gap: 10px; margin-top: 24px;">
          <input type="radio" id="entrega_delivery" name="tipo_entrega" value="delivery" checked>
          <label for="entrega_delivery" style="margin-right: 12px;">Delivery a domicilio</label>
          <input type="radio" id="entrega_pickup" name="tipo_entrega" value="pickup">
          <label for="entrega_pickup">Recoger en local</label>
        </div>
        <div style="flex: 1 1 200px;">
          <label for="direccion">Dirección de entrega:</label>
          <input type="text" id="direccion" name="direccion" placeholder="Ej: Av. Principal 123" required>
        </div>
        <!-- Celular eliminado -->
        <div style="flex: 1 1 200px;">
          <label for="metodo_pago">Método de pago:</label>
          <select id="metodo_pago" name="metodo_pago" required>
            <option value="">Selecciona...</option>
            <option value="1">Efectivo</option>
            <option value="2">Tarjeta</option>
            <option value="3">Yape</option>
          </select>
        </div>
        <div style="flex: 0 0 auto; margin-left: auto;">
          <button type="submit" class="btn-finalizar" style="margin-top: 0;">Confirmar Pedido</button>
        </div>
      </div>
    </form>
  </section>
</main>

<!-- Include Footer -->
<jsp:include page="components/footer.jsp" />

<script src="js/scriptPedidos.js"></script>
</body>
</html> 