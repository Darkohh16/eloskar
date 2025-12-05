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
  <!-- Título Principal -->
  <h1 class="contacto-title">Contáctanos</h1>
  <p class="contacto-subtitle">Estamos aquí para atenderte. Contáctanos a través de cualquiera de nuestros canales.</p>

  <div class="contacto-container">
    <!-- Sección Izquierda: Información de Contacto -->
    <section class="contacto-info-section">
      <h2 class="section-title">
        <span class="icon-title">📞</span>
        Información de Contacto
      </h2>

      <div class="contacto-cards">
        <div class="contacto-card">
          <div class="card-icon">📍</div>
          <h3>Dirección</h3>
          <p>Lima 204, Pimentel<br>Chiclayo, Perú</p>
        </div>

        <div class="contacto-card">
          <div class="card-icon">📱</div>
          <h3>Teléfono</h3>
          <p>(01) 555-0123<br>WhatsApp disponible</p>
        </div>

        <div class="contacto-card">
          <div class="card-icon">✉️</div>
          <h3>Email</h3>
          <p>eloskarpruebas@gmail.com<br>Respuesta en 24 horas</p>
        </div>

        <div class="contacto-card">
          <div class="card-icon">🕐</div>
          <h3>Horario de Atención</h3>
          <p>Lunes a Domingo<br>11:00 AM - 10:00 PM</p>
        </div>
      </div>

      <!-- Mapa -->
      <div class="contacto-mapa">
        <h3 class="mapa-title">📍 Encuéntranos</h3>
        <div class="mapa-container">
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3900.0000000000005!2d-79.84000000000002!3d-6.830000000000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zNsKwNDknNDguMCJTIDc5wrA1MCcwNC4wIlc!5e0!3m2!1ses-419!2spe!4v1680000000000!5m2!1ses-419!2spe" width="100%" height="300" style="border:0; border-radius:12px;" allowfullscreen="" loading="lazy"></iframe>
        </div>
      </div>
    </section>

    <!-- Sección Derecha: Formulario de Contacto -->
    <section class="contacto-form-section">
      <h2 class="section-title">
        <span class="icon-title">💬</span>
        Envíanos un Mensaje
      </h2>

      <form id="formContacto" method="post" action="SrvContacto" class="contacto-form">
        <div class="form-group">
          <label for="nombre">
            <span class="label-icon">👤</span>
            Nombre Completo
          </label>
          <input type="text" id="nombre" name="nombre" placeholder="Ingresa tu nombre" required>
        </div>

        <div class="form-group">
          <label for="correo">
            <span class="label-icon">📧</span>
            Correo Electrónico
          </label>
          <input type="email" id="correo" name="correo" placeholder="tu@email.com" required>
        </div>

        <div class="form-group">
          <label for="asunto">
            <span class="label-icon">📝</span>
            Asunto
          </label>
          <input type="text" id="asunto" name="asunto" placeholder="¿Sobre qué quieres escribirnos?" required>
        </div>

        <div class="form-group form-group-full">
          <label for="mensaje">
            <span class="label-icon">💭</span>
            Mensaje
          </label>
          <textarea id="mensaje" name="mensaje" rows="6" placeholder="Escribe tu mensaje aquí..." required></textarea>
        </div>

        <button type="submit" class="btn-enviar-contacto">
          <span class="btn-icon">📤</span>
          Enviar Mensaje
        </button>
      </form>
    </section>
  </div>
</main>

<jsp:include page="components/footer.jsp" />
<script src="js/mobileMenu.js"></script>
<script src="js/scriptContacto.js"></script>
</body>
</html> 