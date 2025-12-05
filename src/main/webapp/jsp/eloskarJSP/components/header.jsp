<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int idUser = -1;
  String rol = "";
  String nombreUsuario = "";
  if (session != null && session.getAttribute("idUser") != null) {
    idUser = (Integer) session.getAttribute("idUser");
    rol = (String) session.getAttribute("rol");
    if (session.getAttribute("usuario") != null) {
      nombreUsuario = ((com.eloskar.restaurante.DTO.UsuarioDTO)session.getAttribute("usuario")).getNombre();
    }
  }
%>
<header class="header">
  <div class="logo-area">
    <img src="img/logo.png" alt="El Oskar" class="logo-img" />
    <span class="logo-text">El Oskar</span>
  </div>
  <nav class="nav">
    <% if (session != null && "admin".equals(rol) || "encargado".equals(rol)) { %>
      <a href="SrvDashboardPrincipal"><span class="icon">&#128200;</span> Dashboard</a>
    <% } %>
    <a href="SrvBuscarProducto"><span class="icon">&#129424;</span> Carta</a>
    <a href="SrvListarReservas"><span class="icon">&#128197;</span> Reservas</a>
    <a href="jsp/eloskarJSP/sobreNosotros.jsp"><span class="icon">&#128100;</span> Sobre Nosotros</a>
    <a href="jsp/eloskarJSP/contacto.jsp"><span class="icon">&#9993;</span> Contacto</a>
    <a href="SrvCarrito" class="cart"><span class="icon">&#128722;</span> Carrito</a>
    <% if (session.getAttribute("idUser") == null) { %>
      <a href="jsp/eloskarJSP/login/login.jsp" class="account"><span class="icon">&#128100;</span> Mi Cuenta</a>
    <% } else { %>
      <a href="SrvMiCuenta" class="account"><span class="icon">&#128100;</span> Mi Cuenta</a>
      <% if (!nombreUsuario.isEmpty()) { %>
        <span class="usuario-header"><span class="icon">&#128081;</span> <%= nombreUsuario %></span>
      <% } %>
      <form action="SrvCerrarSesion" method="post" class="logout-form" style="display:inline; margin:0; padding:0; align-self:center; vertical-align:middle;">
        <button type="submit" class="btn-cerrar-sesion" title="Cerrar sesión">
          <span class="icon-logout">
            <svg width="16" height="16" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg" style="vertical-align:middle;"><path d="M13.5 13.5L17 10M17 10L13.5 6.5M17 10H7.5M10 17C6.13401 17 3 13.866 3 10C3 6.13401 6.13401 3 10 3" stroke="currentColor" stroke-width="1.7" stroke-linecap="round" stroke-linejoin="round"/></svg>
          </span>
          Salir
        </button>
      </form>
    <% } %>
  </nav>
</header> 