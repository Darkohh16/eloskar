<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  int idUser;
  String rol = "";
  if (session != null && session.getAttribute("idUser") != null) {
    idUser = (Integer) session.getAttribute("idUser");
    rol = (String) session.getAttribute("rol");
  }
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

<!-- Header -->
<header class="header">
  <div class="logo">
    <img src="img/logo.png" alt="El Oskar">
    <span>El Oskar</span>
  </div>
  <nav class="nav">
    <%
      if (session != null) {
        if (rol.equals("admin")) {
    %>
        <a href="jsp/dashboardJSP/DashboardPrincipal.jsp">Dashboard</a>
    <%
        }
      }
    %>
    <a href="#">Inicio</a>
    <a href="SrvBuscarProducto">Carta</a>
    <a href="#">Reservas</a>
    <a href="#">Contacto</a>
    <a href="jsp/eloskarJSP/carrito.jsp" class="cart active">Carrito <span class="cart-badge">3</span></a>
    <%
      if (session.getAttribute("idUser") == null) {
    %>
        <a href="jsp/eloskarJSP/login/login.jsp" class="account">Mi Cuenta</a>
    <%
      } else {
    %>
        <a href="#" class="account">Mi Cuenta</a>
    <%
      }
    %>
    <a href="SrvCerrarSesion" class="cerrarMomentaneo">Cerrar Sesion</a>
  </nav>
</header>

<!-- Contenido principal -->
<main class="carrito-main">
  <div class="carrito-container">
    <h1 class="carrito-title">Mi Carrito</h1>
    
    <div class="carrito-content">
      <!-- Lista de productos -->
      <div class="productos-lista">
        <h2>Productos (3)</h2>
        
        <!-- Producto 1 -->
        <div class="producto-item" data-producto-id="1">
          <div class="producto-imagen">
            <img src="img/ceviche_clasico.jpg" alt="Ceviche Clásico">
          </div>
          <div class="producto-info">
            <h3>Ceviche Clásico</h3>
            <p>Pescado fresco marinado en limón con cebolla, cilantro y ají limo</p>
            <div class="producto-precio">
              <span class="precio" data-precio-unitario="25.00">S/. 25.00</span>
            </div>
          </div>
          <div class="producto-acciones">
            <button class="btn-cantidad" >-</button>
            <span class="cantidad">1</span>
            <button class="btn-cantidad" >+</button>
            <button class="btn-eliminar" >
              <span class="icon-eliminar">🗑️</span>
            </button>
          </div>
        </div>
        
        <!-- Producto 2 -->
        <div class="producto-item" data-producto-id="2">
          <div class="producto-imagen">
            <img src="img/tiradito.jpg" alt="Tiradito">
          </div>
          <div class="producto-info">
            <h3>Tiradito</h3>
            <p>Pescado fresco cortado fino con salsa de ají amarillo y limón</p>
            <div class="producto-precio">
              <span class="precio" data-precio-unitario="28.00">S/. 28.00</span>
            </div>
          </div>
          <div class="producto-acciones">
            <button class="btn-cantidad" >-</button>
            <span class="cantidad">2</span>
            <button class="btn-cantidad" >+</button>
            <button class="btn-eliminar" >
              <span class="icon-eliminar">🗑️</span>
            </button>
          </div>
        </div>
        
        <!-- Producto 3 -->
        <div class="producto-item" data-producto-id="3">
          <div class="producto-imagen">
            <img src="img/pisco_sour.jpg" alt="Pisco Sour">
          </div>
          <div class="producto-info">
            <h3>Pisco Sour</h3>
            <p>Cóctel peruano con pisco, limón, azúcar y clara de huevo</p>
            <div class="producto-precio">
              <span class="precio" data-precio-unitario="15.00">S/. 15.00</span>
            </div>
          </div>
          <div class="producto-acciones">
            <button class="btn-cantidad" >-</button>
            <span class="cantidad">1</span>
            <button class="btn-cantidad" >+</button>
            <button class="btn-eliminar" >
              <span class="icon-eliminar">🗑️</span>
            </button>
          </div>
        </div>
      </div>
      
      <!-- Resumen del pedido -->
      <div class="resumen-pedido">
        <h2>Resumen del Pedido</h2>
        
        <div class="resumen-item">
          <span>Subtotal:</span>
          <span>S/. 93.00</span>
        </div>
        
        <div class="resumen-item">
          <span>Envío:</span>
          <span>S/. 5.00</span>
        </div>
        
        <div class="resumen-item total">
          <span>Total:</span>
          <span>S/. 98.00</span>
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

</body>
</html> 