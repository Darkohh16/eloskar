<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reportes - Restaurante</title>
    <base href="${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
</head>
<body data-context-path="${pageContext.request.contextPath}">
<div class="container">
    <div id="menu-lateral"></div>
    <main class="main-content">
        <header class="header">
            <h1>Reportes y Estadísticas</h1>
            <div class="user-info">
                <span>Admin</span>
            </div>
        </header>
        <section class="charts-section">
            <div class="chart-card">
                <h4>Ingresos por Mes</h4>
                <div class="chart-placeholder">[Gráfica de líneas]</div>
            </div>
            <div class="chart-card">
                <h4>Reservas por Estado</h4>
                <div class="chart-placeholder">[Gráfica de pastel]</div>
            </div>
            <div class="chart-card">
                <h4>Pedidos por Estado</h4>
                <div class="chart-placeholder">[Gráfica de barras]</div>
            </div>
            <div class="chart-card">
                <h4>Productos más vendidos</h4>
                <div class="chart-placeholder">[Gráfica de barras]</div>
            </div>
        </section>
        <section class="export-section">
            <button class="btn-filtrar">Exportar Reporte (PDF)</button>
            <button class="btn-filtrar">Exportar Reporte (Excel)</button>
        </section>
    </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
