<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sobre Nosotros - El Oskar</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
  <link rel="stylesheet" href="css/stylesSobreNosotros.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<jsp:include page="components/header.jsp" />

<main class="sobre-main">
  <section class="sobre-hero">
    <h1>Sobre Nosotros</h1>
    <p class="sobre-desc">En nuestro Restaurant El Oskar, te llevamos a un viaje por los auténticos sabores del mar. Nos especializamos en ofrecer platillos frescos, cuidadosamente preparados con ingredientes de la mejor calidad, en un ambiente cálido y acogedor.</p>
  </section>
  <section class="sobre-mision-vision">
    <div class="sobre-mision">
      <h2>MISIÓN</h2>
      <p>Brindar a nuestros clientes una experiencia culinaria excepcional, combinando lo mejor de los sabores marinos y de la parrilla, con productos frescos, un servicio amable y un ambiente acogedor que convierte cada visita en un momento especial.</p>
    </div>
    <div class="sobre-vision">
      <h2>VISIÓN</h2>
      <p>Convertirnos en la cevichería y parrilla más querida de la región, reconocidos por la excelencia de nuestra gastronomía, la dedicación a nuestros clientes y la constante creatividad en nuestros potajes.</p>
    </div>
  </section>
  <section class="sobre-ubicacion">
    <h2>Ubicación</h2>
    <div class="mapa-container">
      <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d819.7763920545221!2d-79.93727411660092!3d-6.836213385224719!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x904cfb2fa8b17033%3A0x2954afd890cb0a5b!2sRestaurante%20%22El%20Oskar%22!5e0!3m2!1ses-419!2spe!4v1752814932506!5m2!1ses-419!2spe" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
    </div>
  </section>
</main>

<jsp:include page="components/footer.jsp" />
</body>
</html> 