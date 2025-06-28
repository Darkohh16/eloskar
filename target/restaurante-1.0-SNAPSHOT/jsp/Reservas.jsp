<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Reservas - Restaurante</title>
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
      <h1>Gestión de Reservas</h1>
      <div class="user-info">
        <span>Admin</span>
      </div>
    </header>
    <section class="filters-section">
      <form class="filters-form">
        <input type="text" placeholder="Buscar por cliente, fecha o estado..." class="input-search">
        <select>
          <option value="">Estado</option>
          <option value="pendiente">Pendiente</option>
          <option value="confirmada">Confirmada</option>
          <option value="cancelada">Cancelada</option>
        </select>
        <input type="date" placeholder="Fecha" class="input-date">
        <button type="submit" class="btn-filtrar">Filtrar</button>
      </form>
    </section>
    <section class="table-section">
      <table class="main-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Cliente</th>
          <th>Fecha</th>
          <th>Hora</th>
          <th>Personas</th>
          <th>Estado</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>101</td>
          <td>Juan Pérez</td>
          <td>2024-06-10</td>
          <td>19:00</td>
          <td>4</td>
          <td><span class="estado pendiente">Pendiente</span></td>
          <td>
            <button class="btn-accion confirmar">Confirmar</button>
            <button class="btn-accion cancelar">Cancelar</button>
            <button class="btn-accion ver">Ver</button>
          </td>
        </tr>
        <tr>
          <td>102</td>
          <td>María López</td>
          <td>2024-06-10</td>
          <td>20:30</td>
          <td>2</td>
          <td><span class="estado confirmada">Confirmada</span></td>
          <td>
            <button class="btn-accion ver">Ver</button>
          </td>
        </tr>
        <tr>
          <td>103</td>
          <td>Carlos Ruiz</td>
          <td>2024-06-11</td>
          <td>18:00</td>
          <td>6</td>
          <td><span class="estado cancelada">Cancelada</span></td>
          <td>
            <button class="btn-accion ver">Ver</button>
          </td>
        </tr>
        </tbody>
      </table>
    </section>
  </main>
</div>
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
</body>
</html>
