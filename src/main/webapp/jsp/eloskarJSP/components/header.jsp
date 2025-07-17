<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  int idUser;
  String rol = "";
  if (session != null && session.getAttribute("idUser") != null) {
    idUser = (Integer) session.getAttribute("idUser");
    rol = (String) session.getAttribute("rol");
  }
%>

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
    <a href="SrvListarReservas">Reservas</a>
    <a href="#">Contacto</a>
    <a href="SrvCarrito" class="cart">Carrito</a>
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

    <%-- Para pruebas --%>
    <%
      if (session.getAttribute("idUser") != null) {
    %>
        <a href="SrvCerrarSesion" class="cerrarMomentaneo">Cerrar Sesion</a>
    <%
      }
    %>
  </nav>
</header> 