<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Integer idUser = (Integer) session.getAttribute("idUser");
  String nombreUsuario = (String) session.getAttribute("nombre");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservas - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesReservas.css">
</head>
<body data-context-path="${pageContext.request.contextPath}" data-user-id="<%= idUser != null ? idUser : "" %>">

<!-- Include Header -->
<jsp:include page="components/header.jsp" />

<main class="reservas-main">
  <h1 class="main-title">Reservas</h1>

  <section class="form-reserva-section" style="max-width: 600px; margin: 0 auto 40px auto;">
    <h2 style="color: #009fe3; margin-bottom: 18px;">Realizar una nueva reserva</h2>
    <form id="formReserva" method="post" action="SrvReserva">
      <div style="display: flex; gap: 18px; flex-wrap: wrap;">
        <div style="flex: 1 1 200px;">
          <label for="fecha">Fecha:</label>
          <input type="date" id="fecha" name="fecha" required min="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>">
        </div>
        <div style="flex: 1 1 120px;">
          <label for="hora">Hora:</label>
          <input type="time" id="hora" name="hora" required>
        </div>
        <div style="flex: 1 1 120px;">
          <label for="personas">Personas:</label>
          <input type="number" id="personas" name="personas" min="1" max="20" required>
        </div>
      </div>
      <button type="submit" class="btn-finalizar" style="margin-top: 18px;">Reservar</button>
    </form>
  </section>
  <section class="tabla-reservas-section" style="max-width: 900px; margin: 0 auto;">
    <h2 style="color: #009fe3; margin-bottom: 18px;">Mis Reservas</h2>
    <table class="main-table">
      <thead>
      <tr>
        <th>ID</th>
        <th>Fecha</th>
        <th>Hora</th>
        <th>Personas</th>
        <th>Estado</th>
      </tr>
      </thead>
      <tbody>
      <%-- Aquí se debe iterar sobre la lista de reservas del usuario --%>
      <%-- Ejemplo de fila estática --%>
      <tr>
        <td>1</td>
        <td>2024-07-10</td>
        <td>19:00</td>
        <td>4</td>
        <td><span class="estado pendiente">Pendiente</span></td>
      </tr>
      <tr>
        <td>2</td>
        <td>2024-07-12</td>
        <td>20:30</td>
        <td>2</td>
        <td><span class="estado confirmada">Confirmada</span></td>
      </tr>
      <%-- Fin ejemplo --%>
      </tbody>
    </table>
  </section>
</main>

<!-- Include Footer -->
<jsp:include page="components/footer.jsp" />

<script src="js/scriptReserva.js"></script>
</body>
</html> 