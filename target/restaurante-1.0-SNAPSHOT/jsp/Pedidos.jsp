<%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedidos - Restaurante</title>
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
            <h1>Gestión de Pedidos</h1>
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
                    <option value="entregado">Entregado</option>
                    <option value="cancelado">Cancelado</option>
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
                    <th>Total</th>
                    <th>Estado</th>
                    <th>Método de Pago</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>201</td>
                    <td>Juan Pérez</td>
                    <td>2024-06-10 13:45</td>
                    <td>S/ 85.00</td>
                    <td><span class="estado pendiente">Pendiente</span></td>
                    <td>Efectivo</td>
                    <td>
                        <button class="btn-accion entregar">Entregar</button>
                        <button class="btn-accion cancelar">Cancelar</button>
                        <button class="btn-accion ver">Ver</button>
                    </td>
                </tr>
                <tr>
                    <td>202</td>
                    <td>María López</td>
                    <td>2024-06-10 14:20</td>
                    <td>S/ 120.00</td>
                    <td><span class="estado entregado">Entregado</span></td>
                    <td>Tarjeta</td>
                    <td>
                        <button class="btn-accion ver">Ver</button>
                    </td>
                </tr>
                <tr>
                    <td>203</td>
                    <td>Carlos Ruiz</td>
                    <td>2024-06-11 12:10</td>
                    <td>S/ 60.00</td>
                    <td><span class="estado cancelado">Cancelado</span></td>
                    <td>Yape</td>
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
