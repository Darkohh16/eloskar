<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 30/06/2025
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>El Oskar - Nuestra Carta</title>
  <base href="${pageContext.request.contextPath}/">
  <link rel="stylesheet" href="css/stylesPrincipal.css">
</head>
<body>
<!-- Header -->
<header class="header">
  <div class="logo">
    <img src="logo.png" alt="El Oskar">
    <span>El Oskar</span>
  </div>
  <nav class="nav">
    <a href="jsp/dashboardJSP/DashboardPrincipal.jsp">Dashboard</a>
    <a href="#">Inicio</a>
    <a href="#">Carta</a>
    <a href="#">Reservas</a>
    <a href="#">Contacto</a>
    <a href="#" class="cart">Carrito <span class="cart-badge">1</span></a>
    <a href="#" class="account">Mi Cuenta</a>
  </nav>
</header>

<!-- Título principal -->
<main>
  <h1 class="main-title">Nuestra Carta</h1>

  <!-- Sección: Ceviches -->
  <section class="menu-section">
    <h2 class="section-title">Ceviches</h2>
    <div class="menu-items">
      <!-- Item 1 -->
      <div class="menu-card">
        <img src="ceviche-clasico.jpg" alt="Ceviche Clásico">
        <div class="menu-info">
          <h3>Ceviche Clásico</h3>
          <p>Pescado fresco, limón, cebolla, cilantro, ají</p>
          <span class="price">S/. 35.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <!-- Item 2 -->
      <div class="menu-card">
        <img src="ceviche-mixto.jpg" alt="Ceviche Mixto">
        <div class="menu-info">
          <h3>Ceviche Mixto</h3>
          <p>Pescado, mariscos variados, limón, cebolla</p>
          <span class="price">S/. 45.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <!-- Item 3 -->
      <div class="menu-card">
        <img src="ceviche-carretillero.jpg" alt="Ceviche Carretillero">
        <div class="menu-info">
          <h3>Ceviche Carretillero</h3>
          <p>Pescado, chicharrón, camote, choclo</p>
          <span class="price">S/. 40.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
    </div>
  </section>

  <!-- Sección: Platos Calientes -->
  <section class="menu-section">
    <h2 class="section-title">Platos Calientes</h2>
    <div class="menu-items">
      <div class="menu-card">
        <img src="arroz-mariscos.jpg" alt="Arroz con Mariscos">
        <div class="menu-info">
          <h3>Arroz con Mariscos</h3>
          <p>Arroz, mariscos variados, vegetales</p>
          <span class="price">S/. 38.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <div class="menu-card">
        <img src="chaufa-mariscos.jpg" alt="Chaufa de Mariscos">
        <div class="menu-info">
          <h3>Chaufa de Mariscos</h3>
          <p>Arroz, mariscos, huevo, cebolla china</p>
          <span class="price">S/. 35.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <div class="menu-card">
        <img src="chicharron-pescado.jpg" alt="Chicharrón de Pescado">
        <div class="menu-info">
          <h3>Chicharrón de Pescado</h3>
          <p>Pescado frito, yuca, salsa criolla</p>
          <span class="price">S/. 32.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
    </div>
  </section>

  <!-- Sección: Bebidas -->
  <section class="menu-section">
    <h2 class="section-title">Bebidas</h2>
    <div class="menu-items">
      <div class="menu-card">
        <img src="limonada.jpg" alt="Limonada">
        <div class="menu-info">
          <h3>Limonada</h3>
          <p>Limón fresco, hierba buena</p>
          <span class="price">S/. 8.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <div class="menu-card">
        <img src="chicha-morada.jpg" alt="Chicha Morada">
        <div class="menu-info">
          <h3>Chicha Morada</h3>
          <p>Maíz morado, piña, manzana, canela</p>
          <span class="price">S/. 10.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
      <div class="menu-card">
        <img src="pisco-sour.jpg" alt="Pisco Sour">
        <div class="menu-info">
          <h3>Pisco Sour</h3>
          <p>Pisco, limón, clara de huevo, amargo</p>
          <span class="price">S/. 20.00</span>
          <button class="add-cart"><i class="icon-cart"></i></button>
        </div>
      </div>
    </div>
  </section>
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

<script src="script.js"></script>
</body>
</html>

