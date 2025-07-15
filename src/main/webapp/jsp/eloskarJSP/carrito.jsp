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

<!-- Include Header -->
<jsp:include page="components/header.jsp" />

<!-- Contenido principal -->
<main class="carrito-main">
  <div class="carrito-container">
    <h1 class="carrito-title">Mi Carrito</h1>
    
    <div class="carrito-content">
      <!-- Lista de productos -->
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
      
      <!-- Resumen del pedido -->
      <div class="resumen-pedido">
        <h2>Resumen del Pedido</h2>
        
        <div class="resumen-item">
          <span>Subtotal:</span>
          <span>S/. <%= subtotal %></span>
        </div>
        
        <div class="resumen-item">
          <span>Envío:</span>
          <span>S/. 5.00</span>
        </div>
        
        <div class="resumen-item total">
          <span>Total:</span>
          <span>S/. <%= subtotal + 5 %></span>
        </div>
        
        <div class="opciones-entrega">
          <h3>Opciones de Entrega</h3>
          <div class="opcion-entrega">
            <input type="radio" id="delivery" name="entrega" value="delivery" checked>
            <label for="delivery">Delivery a domicilio</label>
          </div>
          <div class="opcion-entrega">
            <input type="radio" id="pickup" name="entrega" value="pickup">
            <label for="pickup">Recoger en local</label>
          </div>
        </div>
        
        <div class="horario-entrega">
          <h3>Horario de Entrega</h3>
          Aqui ira el tiempo estimado de entrega
        </div>
        
        <button class="btn-finalizar" >
          Finalizar Pedido
        </button>
        
        <a href="SrvBuscarProducto" class="btn-seguir-comprando">
          Seguir Comprando
        </a>
      </div>
    </div>
  </div>
</main>

<!-- Include Footer -->
<jsp:include page="components/footer.jsp" />

</body>
</html> 