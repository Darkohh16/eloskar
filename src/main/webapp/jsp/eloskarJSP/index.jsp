<%@ page import="com.eloskar.restaurante.DTO.CategoriaDTO" %>
<%@ page import="com.eloskar.restaurante.DTO.ProductoDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 30/06/2025
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<ProductoDTO> productos = (List<ProductoDTO>) request.getAttribute("producto");
  List<CategoriaDTO> categorias = (List<CategoriaDTO>) request.getAttribute("categoria");
%>
<html lang="es">
<head>
  <base href="${pageContext.request.contextPath}/">
  <link rel="icon" href="img/logo.png" type="image/jpg">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>El Oskar - Nuestra Carta</title>
  <link rel="stylesheet" href="css/stylesPrincipal.css">
</head>
<body data-context-path="${pageContext.request.contextPath}">

<jsp:include page="/jsp/eloskarJSP/components/header.jsp" />

<!-- Hero Section con Carrusel -->
<section class="hero-section">
  <div class="hero-carousel">
    <div class="carousel-slide active">
      <img src="img/ceviche_clasico.jpg" alt="Ceviche Clásico">
      <div class="carousel-overlay"></div>
    </div>
    <div class="carousel-slide">
      <img src="img/jalea.jpg" alt="Jalea de Mariscos">
      <div class="carousel-overlay"></div>
    </div>
    <div class="carousel-slide">
      <img src="img/arroz_mariscos.jpg" alt="Arroz con Mariscos">
      <div class="carousel-overlay"></div>
    </div>
    <div class="carousel-slide">
      <img src="img/tiradito.jpg" alt="Tiradito">
      <div class="carousel-overlay"></div>
    </div>
    <div class="carousel-slide">
      <img src="img/pisco_sour.jpg" alt="Pisco Sour">
      <div class="carousel-overlay"></div>
    </div>
  </div>

  <div class="hero-content">
    <h1 class="hero-title">Les Presentamos Nuestra Carta</h1>
    <p class="hero-subtitle">Sabores auténticos del mar peruano en cada plato</p>
    <div class="hero-decorative-line"></div>
  </div>

  <!-- Controles del carrusel -->
  <button class="carousel-control prev" onclick="moveCarousel(-1)">
    <span>❮</span>
  </button>
  <button class="carousel-control next" onclick="moveCarousel(1)">
    <span>❯</span>
  </button>

  <!-- Indicadores -->
  <div class="carousel-indicators">
    <span class="indicator active" onclick="goToSlide(0)"></span>
    <span class="indicator" onclick="goToSlide(1)"></span>
    <span class="indicator" onclick="goToSlide(2)"></span>
    <span class="indicator" onclick="goToSlide(3)"></span>
    <span class="indicator" onclick="goToSlide(4)"></span>
  </div>
</section>

<main>

  <%
    for (CategoriaDTO categoria : categorias) {
  %>
  <section class="menu-section accordion-section">
    <div class="accordion-header" onclick="toggleAccordion(this)">
      <h2 class="section-title"><%= categoria.getNombre() %></h2>
      <span class="accordion-icon">▼</span>
    </div>
    <div class="accordion-content">
      <div class="menu-items">

        <%
          for (ProductoDTO p : productos) {
            if (p.getCategoria().getNombre().equals(categoria.getNombre())) {
        %>
        <div class="menu-card <%= !p.isDisponible() ? "no-disponible": "" %> ">
          <img src="<%= p.getImagen() %>" alt="<%= p.getNombre() %>">
          <div class="menu-info">
            <h3><%= p.getNombre() %></h3>
            <p><%= p.getDescripcion() %></p>
            <span class="price">S/. <%= p.getPrecio() %></span>

            <% if (!p.isDisponible()) { %>
            <p class="no-disponible-msg">Este producto está agotado para envío.</p>
            <% } %>

            <% if (p.isDisponible()) { %>
            <form action="SrvAgregarAlCarrito" method="post" style="display:inline;">
              <input type="hidden" name="idProducto" value="<%= p.getIdProd() %>"/>
              <button class="add-cart" type="submit">
                <i class="icon-cart"></i>
              </button>
            </form>
            <% } %>
          </div>
        </div>
        <%
            }
          }
        %>
      </div>
    </div>
  </section>
  <%
    }
  %>
</main>

<jsp:include page="/jsp/eloskarJSP/components/footer.jsp" />

<script src="script.js"></script>
<script src="js/scriptCarrito.js"></script>
<script src="js/mobileMenu.js"></script>

<!-- Script del Carrusel -->
<script>
let currentSlide = 0;
const slides = document.querySelectorAll('.carousel-slide');
const indicators = document.querySelectorAll('.indicator');
const totalSlides = slides.length;

function showSlide(index) {
    // Asegurar que el índice esté en el rango válido
    if (index >= totalSlides) {
        currentSlide = 0;
    } else if (index < 0) {
        currentSlide = totalSlides - 1;
    } else {
        currentSlide = index;
    }

    // Remover clase active de todos
    slides.forEach(slide => slide.classList.remove('active'));
    indicators.forEach(indicator => indicator.classList.remove('active'));

    // Agregar clase active al actual
    slides[currentSlide].classList.add('active');
    indicators[currentSlide].classList.add('active');
}

function moveCarousel(direction) {
    showSlide(currentSlide + direction);
}

function goToSlide(index) {
    showSlide(index);
}

// Auto-play del carrusel
let autoPlayInterval = setInterval(() => {
    moveCarousel(1);
}, 5000);

// Pausar auto-play cuando el usuario interactúa
document.querySelector('.hero-carousel').addEventListener('mouseenter', () => {
    clearInterval(autoPlayInterval);
});

document.querySelector('.hero-carousel').addEventListener('mouseleave', () => {
    autoPlayInterval = setInterval(() => {
        moveCarousel(1);
    }, 5000);
});

// Touch support para móviles
let touchStartX = 0;
let touchEndX = 0;

document.querySelector('.hero-carousel').addEventListener('touchstart', (e) => {
    touchStartX = e.changedTouches[0].screenX;
});

document.querySelector('.hero-carousel').addEventListener('touchend', (e) => {
    touchEndX = e.changedTouches[0].screenX;
    handleSwipe();
});

function handleSwipe() {
    if (touchEndX < touchStartX - 50) {
        moveCarousel(1);
    }
    if (touchEndX > touchStartX + 50) {
        moveCarousel(-1);
    }
}
</script>

<!-- Script del Acordeón de Categorías -->
<script>
function toggleAccordion(header) {
    const section = header.parentElement;
    const content = section.querySelector('.accordion-content');
    const icon = header.querySelector('.accordion-icon');

    // Toggle active class
    section.classList.toggle('active');

    // Cambiar icono
    if (section.classList.contains('active')) {
        icon.textContent = '▲';
    } else {
        icon.textContent = '▼';
    }
}

// Abrir la primera categoría por defecto
document.addEventListener('DOMContentLoaded', function() {
    const firstAccordion = document.querySelector('.accordion-section .accordion-header');
    if (firstAccordion) {
        toggleAccordion(firstAccordion);
    }
});
</script>
</body>
</html>

