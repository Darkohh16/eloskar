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
    <h1 class="carrito-title">
      <span class="icon-carrito">🛒</span>
      Mi Carrito de Compras
    </h1>

    <%
      double subtotal = 0;
      if (detalles != null && !detalles.isEmpty()) {
        for (CarritoDetalleDTO det : detalles) {
          ProductoDTO prod = productoService.obtenerProductoPorId(det.getProducto_id());
          if (prod == null) continue;
          double precio = prod.getPrecio();
          subtotal += precio * det.getCantidad();
        }
      }
    %>

    <% if (detalles != null && !detalles.isEmpty()) { %>
    <div class="carrito-content">
      <!-- Columna Izquierda: Productos -->
      <div class="productos-section">
        <div class="productos-header">
          <h2>
            <span class="icon-productos">📦</span>
            Tus Productos (<%= detalles.size() %>)
          </h2>
          <a href="SrvBuscarProducto" class="btn-seguir-comprando-mobile">
            <span class="icon-volver">←</span>
            Seguir Comprando
          </a>
        </div>

        <div class="productos-lista">
          <%
            for (CarritoDetalleDTO det : detalles) {
              ProductoDTO prod = productoService.obtenerProductoPorId(det.getProducto_id());
              if (prod == null) continue;
              double precio = prod.getPrecio();
              double subtotalItem = precio * det.getCantidad();
          %>
          <div class="producto-card" data-producto-id="<%= prod.getIdProd() %>">
            <div class="producto-imagen">
              <img src="<%= prod.getImagen() %>" alt="<%= prod.getNombre() %>">
            </div>

            <div class="producto-info">
              <h3 class="producto-nombre"><%= prod.getNombre() %></h3>
              <p class="producto-descripcion"><%= prod.getDescripcion() %></p>
              <div class="producto-precio-unit">
                <span class="label-precio">Precio unitario:</span>
                <span class="precio-valor" data-precio-unitario="<%= precio %>">S/. <%= String.format("%.2f", precio) %></span>
              </div>
            </div>

            <div class="producto-controles">
              <div class="cantidad-control">
                <form action="SrvActualizarDetalleCarrito" method="post" class="form-cantidad">
                  <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
                  <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
                  <button name="accion" value="restar" class="btn-cantidad btn-menos" type="submit">
                    <span>−</span>
                  </button>
                </form>
                <span class="cantidad-valor"><%= det.getCantidad() %></span>
                <form action="SrvActualizarDetalleCarrito" method="post" class="form-cantidad">
                  <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
                  <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
                  <button name="accion" value="sumar" class="btn-cantidad btn-mas" type="submit">
                    <span>+</span>
                  </button>
                </form>
              </div>

              <div class="producto-subtotal">
                <span class="label-subtotal">Subtotal:</span>
                <span class="subtotal-valor">S/. <%= String.format("%.2f", subtotalItem) %></span>
              </div>

              <form action="SrvActualizarDetalleCarrito" method="post" class="form-eliminar">
                <input type="hidden" name="idDetalle" value="<%= det.getIdDetalle() %>"/>
                <input type="hidden" name="cantidad" value="<%= det.getCantidad() %>"/>
                <button name="accion" value="eliminar" class="btn-eliminar" type="submit" title="Eliminar producto">
                  <span class="icon-eliminar">🗑️</span>
                  <span class="text-eliminar">Eliminar</span>
                </button>
              </form>
            </div>
          </div>
          <% } %>
        </div>
      </div>

      <!-- Columna Derecha: Resumen y Datos -->
      <div class="resumen-section">
        <!-- Resumen del Pedido -->
        <div class="resumen-card">
          <h2 class="resumen-title">
            <span class="icon-resumen">💰</span>
            Resumen del Pedido
          </h2>

          <div class="resumen-detalles">
            <div class="resumen-linea">
              <span class="resumen-label">Subtotal (<%= detalles.size() %> producto<%= detalles.size() > 1 ? "s" : "" %>):</span>
              <span class="resumen-valor">S/. <%= String.format("%.2f", subtotal) %></span>
            </div>
            <div class="resumen-linea">
              <span class="resumen-label">Delivery:</span>
              <span class="resumen-valor delivery-costo">S/. 5.00</span>
            </div>
            <div class="resumen-separador"></div>
            <div class="resumen-linea resumen-total">
              <span class="resumen-label-total">Total:</span>
              <span class="resumen-valor-total">S/. <%= String.format("%.2f", subtotal + 5.00) %></span>
            </div>
          </div>
        </div>

        <!-- Datos del Pedido -->
        <div class="datos-pedido-card">
          <h2 class="datos-title">
            <span class="icon-datos">📋</span>
            Datos de Entrega
          </h2>

          <form id="formPedido" method="post" action="SrvConfirmarPedido" class="form-pedido">
            <div class="form-group tipo-entrega">
              <label class="label-principal">
                <span class="label-icon">🚚</span>
                Tipo de Entrega
              </label>
              <div class="radio-group">
                <label class="radio-option">
                  <input type="radio" id="entrega_delivery" name="tipo_entrega" value="delivery" checked>
                  <span class="radio-custom"></span>
                  <span class="radio-label">
                    <span class="radio-icon">🏠</span>
                    Delivery a domicilio
                  </span>
                </label>
                <label class="radio-option">
                  <input type="radio" id="entrega_pickup" name="tipo_entrega" value="pickup">
                  <span class="radio-custom"></span>
                  <span class="radio-label">
                    <span class="radio-icon">🏪</span>
                    Recoger en local
                  </span>
                </label>
              </div>
            </div>

            <div class="form-group">
              <label for="direccion" class="label-principal">
                <span class="label-icon">📍</span>
                Dirección de Entrega
              </label>
              <input type="text" id="direccion" name="direccion" placeholder="Ej: Av. Principal 123, Pimentel" required class="input-field">
            </div>

            <div class="form-group">
              <label for="metodo_pago" class="label-principal">
                <span class="label-icon">💳</span>
                Método de Pago
              </label>
              <select id="metodo_pago" name="metodo_pago" required class="select-field">
                <option value="">Selecciona un método...</option>
                <% if (metodosPago != null) {
                     for (com.eloskar.restaurante.DTO.MetodoPagoDTO mp : metodosPago) { %>
                       <option value="<%= mp.getIdPago() %>"><%= mp.getNombre() %></option>
                <%   }
                   } %>
              </select>
            </div>

            <button type="submit" class="btn-confirmar-pedido">
              <span class="btn-icon">✓</span>
              Confirmar Pedido
            </button>
          </form>
        </div>

        <!-- Botón Seguir Comprando -->
        <a href="SrvBuscarProducto" class="btn-seguir-comprando">
          <span class="icon-volver">←</span>
          Seguir Comprando
        </a>
      </div>
    </div>
    <% } else { %>
    <!-- Carrito Vacío -->
    <div class="carrito-vacio">
      <div class="vacio-icon">🛒</div>
      <h2>Tu carrito está vacío</h2>
      <p>Agrega productos desde nuestra carta para comenzar tu pedido</p>
      <a href="SrvBuscarProducto" class="btn-ir-carta">
        <span class="icon-carta">🍽️</span>
        Ver Carta
      </a>
    </div>
    <% } %>
  </div>
</main>

<jsp:include page="components/footer.jsp" />
 <script src="js/mobileMenu.js"></script>
<script src="js/scriptCarrito.js"></script>
</body>
</html> 