<%@ page import="com.eloskar.restaurante.DTO.CarritoDTO" %>
<%@ page import="com.eloskar.restaurante.DTO.CarritoDetalleDTO" %>
<%@ page import="com.eloskar.restaurante.DTO.ProductoDTO" %>
<%@ page import="com.eloskar.restaurante.services.ProductoService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  CarritoDTO carrito = (CarritoDTO) request.getAttribute("carrito");
  List<CarritoDetalleDTO> detalles = carrito != null ? carrito.getDetalles() : null;
  ProductoService productoService = new ProductoService();
  List<com.eloskar.restaurante.DTO.MetodoPagoDTO> metodosPago = (List<com.eloskar.restaurante.DTO.MetodoPagoDTO>) request.getAttribute("metodosPago");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Carrito - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesCarrito.css">
</head>
<body data-context-path="${pageContext.request.contextPath}" data-user-id="<%= session.getAttribute("idUser") != null ? session.getAttribute("idUser") : "" %>">

<jsp:include page="components/header.jsp" />

<main class="carrito-main">
  <div class="carrito-container">
    <h1 class="carrito-title">Mi Carrito</h1>
    
    <div class="carrito-content">
      <div class="productos-lista">
        <h2>Productos (<%= detalles != null ? detalles.size() : 0 %>)</h2>
        <% 
          double subtotal = 0;
          if (detalles != null && !detalles.isEmpty()) {
            for (CarritoDetalleDTO det : detalles) {
              ProductoDTO prod = productoService.obtenerProductoPorId(det.getProducto_id());
              if (prod == null) continue;
              double precio = prod.getPrecio();
              subtotal += precio * det.getCantidad();
        %>
        <div class="producto-item" data-producto-id="<%= prod.getIdProd() %>">
          <div class="producto-imagen">
            <img src="<%= prod.getImagen() %>" alt="<%= prod.getNombre() %>">
          </div>
          <div class="producto-info">
            <h3><%= prod.getNombre() %></h3>
            <p><%= prod.getDescripcion() %></p>
            <div class="producto-precio">
              <span class="precio" data-precio-unitario="<%= precio %>">S/. <%= precio %></span>
            </div>
          </div>
          <div class="producto-acciones">
            <form action="SrvActualizarDetalleCarrito" method="post" style="display:inline;">
              <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
              <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
              <button name="accion" value="restar" class="btn-cantidad">-</button>
            </form>
            <span class="cantidad"><%= det.getCantidad() %></span>
            <form action="SrvActualizarDetalleCarrito" method="post" style="display:inline;">
              <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
              <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
              <button name="accion" value="sumar" class="btn-cantidad">+</button>
            </form>
            <form action="SrvActualizarDetalleCarrito" method="post" style="display:inline;">
              <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
              <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
              <button name="accion" value="eliminar" class="btn-eliminar">
                <span class="icon-eliminar">🗑️</span>
              </button>
            </form>
          </div>
        </div>
        <%   }
          } else { %>
        <p>No hay productos en el carrito.</p>
        <% } %>
      </div>
      <section class="datos-pedido-section">
        <h2 class="titulo-seccion">Datos para el pedido</h2>
        <form id="formPedido" method="post" action="SrvConfirmarPedido">
          <div class="form-pedido-flex">
            <div class="form-pedido-entrega">
              <input type="radio" id="entrega_delivery" name="tipo_entrega" value="delivery" checked>
              <label for="entrega_delivery" class="label-radio-margin">Delivery a domicilio</label>
              <input type="radio" id="entrega_pickup" name="tipo_entrega" value="pickup">
              <label for="entrega_pickup">Recoger en local</label>
            </div>
            <div class="form-pedido-campo">
              <label for="direccion">Dirección de entrega:</label>
              <input type="text" id="direccion" name="direccion" placeholder="Ej: Av. Principal 123" required>
            </div>
            <div class="form-pedido-campo">
              <label for="metodo_pago">Método de pago:</label>
              <select id="metodo_pago" name="metodo_pago" required>
                <option value="">Selecciona...</option>
                <% if (metodosPago != null) {
                     for (com.eloskar.restaurante.DTO.MetodoPagoDTO mp : metodosPago) { %>
                       <option value="<%= mp.getIdPago() %>"><%= mp.getNombre() %></option>
                <%   }
                   } %>
              </select>
            </div>
            <div class="form-pedido-boton">
              <button type="submit" class="btn-finalizar no-margin">Confirmar Pedido</button>
            </div>
          </div>
        </form>
      </section>

      <div class="resumen-pedido">
        <a href="SrvBuscarProducto" class="btn-seguir-comprando">Seguir Comprando</a>
      </div>
    </div>
  </div>
</main>

<jsp:include page="components/footer.jsp" />
<script src="js/scriptCarrito.js"></script>
</body>
</html> 