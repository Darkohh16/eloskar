<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contacto - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesContacto.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<jsp:include page="components/header.jsp" />

<main class="contacto-main">
  <section class="contacto-info">
    <h1>Contacto</h1>
    <div class="contacto-datos">
      <div>
        <h3>Dirección</h3>
        <p>Lima 204, Pimentel</p>
      </div>
      <div>
        <h3>Teléfono</h3>
        <p>(01) 555-0123</p>
      </div>
      <div>
        <h3>Email</h3>
        <p>eloskarpruebas@gmail.com</p>
      </div>
      <div>
        <h3>Horario</h3>
        <p>Lunes a Domingo<br>11:00 AM - 6:00 PM</p>
      </div>
    </div>
  </section>

  <section class="contacto-form-section">
    <h2>Envíanos un mensaje</h2>
    <form id="formContacto" method="post" action="SrvContacto">
      <div class="form-contacto-grid">
        <div>
          <label for="nombre">Nombre:</label>
          <input type="text" id="nombre" name="nombre" required>
        </div>
        <div>
          <label for="correo">Correo:</label>
          <input type="email" id="correo" name="correo" required>
        </div>
        <div>
          <label for="asunto">Asunto:</label>
          <input type="text" id="asunto" name="asunto" required>
        </div>
        <div class="form-contacto-mensaje">
          <label for="mensaje">Mensaje:</label>
          <textarea id="mensaje" name="mensaje" rows="4" required></textarea>
        </div>
      </div>
      <button type="submit" class="btn-enviar">Enviar</button>
    </form>
  </section>

  <section class="contacto-mapa">
    <h2>Búscanos en</h2>
    <div class="mapa-container">
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3900.0000000000005!2d-79.84000000000002!3d-6.830000000000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zNsKwNDknNDguMCJTIDc5wrA1MCcwNC4wIlc!5e0!3m2!1ses-419!2spe!4v1680000000000!5m2!1ses-419!2spe" width="100%" height="320" style="border:0; border-radius:10px;" allowfullscreen="" loading="lazy"></iframe>
    </div>
  </section>
</main>

<jsp:include page="components/footer.jsp" />
<script src="js/scriptContacto.js"></script>
</body>
</html> 