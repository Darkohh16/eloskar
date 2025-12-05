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
  <!-- Hero Section Minimalista -->
  <section class="sobre-hero">
    <div class="hero-background"></div>
    <div class="hero-content">
      <div class="hero-badge">El Oskar</div>
      <h1>Sobre Nosotros</h1>
      <p class="sobre-desc">Auténticos sabores del mar con productos frescos y de calidad, en un ambiente cálido que hace cada visita especial.</p>
    </div>
  </section>

  <!-- Contenedor Principal: Misión/Visión (Izquierda) + Ubicación (Derecha) -->
  <div class="sobre-contenedor">
    <!-- Misión y Visión - Lado Izquierdo -->
    <section class="sobre-mision-vision">
      <div class="sobre-mision">
        <div class="card-media">
          <img src="img/SobreNosotros/Mision.jpg" alt="Misión El Oskar">
        </div>
        <div class="card-content">
          <h2>Misión</h2>
          <p>Brindar a nuestros clientes una experiencia culinaria excepcional, combinando lo mejor de los sabores marinos y de la parrilla, con productos frescos, un servicio amable y un ambiente acogedor que convierte cada visita en un momento especial y memorable.</p>
        </div>
      </div>
      <div class="sobre-vision">
        <div class="card-media">
          <img src="img/SobreNosotros/vision.jpg" alt="Visión El Oskar">
        </div>
        <div class="card-content">
          <h2>Visión</h2>
          <p>Convertirnos en la cevichería y parrilla más querida de la región, reconocidos por la excelencia de nuestra gastronomía, la dedicación a nuestros clientes y la constante creatividad en nuestros potajes, siendo referencia de calidad y sabor auténtico.</p>
        </div>
      </div>
    </section>

    <!-- Ubicación - Lado Derecho -->
    <section class="sobre-ubicacion">
      <h2>Encuéntranos</h2>
      <div class="mapa-container">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d819.7763920545221!2d-79.93727411660092!3d-6.836213385224719!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x904cfb2fa8b17033%3A0x2954afd890cb0a5b!2sRestaurante%20%22El%20Oskar%22!5e0!3m2!1ses-419!2spe!4v1752814932506!5m2!1ses-419!2spe" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
      </div>
    </section>
  </div>

  <!-- Carrusel Minimalista -->
  <section class="experiencias-section">
    <div class="seccion-header">
      <span class="seccion-tag">Experiencias</span>
      <h2>Momentos Especiales</h2>
    </div>

    <div class="carrusel-moderno">
      <div class="carrusel-track">
        <div class="slide-moderno active" data-slide="0">
          <div class="slide-img-wrapper">
            <img src="img/SobreNosotros/clientes1.jpg" alt="Experiencia única">
          </div>
          <div class="slide-info">
            <h3>Experiencia Gastronómica</h3>
            <p>Los mejores sabores del mar en un ambiente familiar</p>
          </div>
        </div>

        <div class="slide-moderno" data-slide="1">
          <div class="slide-img-wrapper">
            <img src="img/SobreNosotros/clientes2.jpg" alt="Ambiente acogedor">
          </div>
          <div class="slide-info">
            <h3>Ambiente Acogedor</h3>
            <p>El espacio perfecto para compartir con familia y amigos</p>
          </div>
        </div>

        <div class="slide-moderno" data-slide="2">
          <div class="slide-img-wrapper">
            <img src="img/SobreNosotros/clientes3.jpg" alt="Celebraciones especiales">
          </div>
          <div class="slide-info">
            <h3>Celebraciones Especiales</h3>
            <p>Momentos inolvidables alrededor de la mejor gastronomía</p>
          </div>
        </div>
      </div>

      <div class="carrusel-controles">
        <button class="ctrl-btn" onclick="cambiarSlide(-1)">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="15 18 9 12 15 6"></polyline>
          </svg>
        </button>
        <div class="dots">
          <span class="dot active" onclick="irASlide(0)"></span>
          <span class="dot" onclick="irASlide(1)"></span>
          <span class="dot" onclick="irASlide(2)"></span>
        </div>
        <button class="ctrl-btn" onclick="cambiarSlide(1)">
          <svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
            <polyline points="9 18 15 12 9 6"></polyline>
          </svg>
        </button>
      </div>
    </div>
  </section>

  <script>
    let slideActual = 0;
    let autoAvanceInterval;
    const slides = document.querySelectorAll('.slide-moderno');
    const dots = document.querySelectorAll('.dot');

    function mostrarSlide(n) {
      slides.forEach(slide => slide.classList.remove('active'));
      dots.forEach(dot => dot.classList.remove('active'));

      slideActual = (n + slides.length) % slides.length;

      slides[slideActual].classList.add('active');
      dots[slideActual].classList.add('active');
    }

    function cambiarSlide(direccion) {
      mostrarSlide(slideActual + direccion);
      reiniciarAutoAvance();
    }

    function irASlide(n) {
      mostrarSlide(n);
      reiniciarAutoAvance();
    }

    function reiniciarAutoAvance() {
      clearInterval(autoAvanceInterval);
      autoAvanceInterval = setInterval(() => {
        cambiarSlide(1);
      }, 5000);
    }

    // Iniciar auto-avance
    reiniciarAutoAvance();
  </script>
</main>

<jsp:include page="components/footer.jsp" />
</body>
</html>

