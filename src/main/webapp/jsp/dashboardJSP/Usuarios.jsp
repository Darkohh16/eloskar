<%@ page import="com.eloskar.restaurante.DTO.UsuarioDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String rol = (String) session.getAttribute("rol");
  if (rol == null || !rol.equals("admin")) {
%>
  <script>
    alert("Privilegios inválidos");
    history.back();
  </script>
<%
    return;
  }
  List<UsuarioDTO> usuarios = (List<UsuarioDTO>) request.getAttribute("usuario");
%>
<html lang="es">
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/scriptUsuario.js"></script>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Usuarios - Restaurante</title>
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
      <h1>Gestión de Usuarios</h1>
      <div class="user-info">
        <span>Admin</span>
      </div>
    </header>
    <section class="filters-section">
      <div class="filters-bar">
        <button class="btn-accion agregar">Agregar Usuario</button>
        <form method="get" action="SrvBuscarUsuario" class="filters-form">
          <input type="text" name = "filtro" placeholder="Buscar por nombre, correo o dni..." class="input-search">
          <button type="submit" class="btn-filtrar">Filtrar</button>
        </form>
      </div>
    </section>

    <section class="table-section">
      <table class="main-table">
        <thead>
        <tr>
          <th>ID</th>
          <th>Nombre</th>
          <th>Dni</th>
          <th>Correo</th>
          <th>Celular</th>
          <th>Rol</th>
          <th>Fecha de Registro</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <%
          if (usuarios != null) {
            for(UsuarioDTO u : usuarios) {
        %>
        <tr>
          <td><%= u.getIdUser() %></td>
          <td><%= u.getNombre() %></td>
          <td><%= u.getDni()%></td>
          <td><%= u.getCorreo() %></td>
          <td><%= u.getCel()%></td>
          <td><span class="rol <%= u.getRol().toLowerCase() %>"><%= u.getRol().substring(0,1).toUpperCase() + u.getRol().substring(1) %></span></td>
          <td><%= u.getFechaRegistro().substring(0, 19)%></td>
          <td>
            <button class="btn-accion editar">Editar</button>
            <button class="btn-accion eliminar" data-id="<%= u.getIdUser()%>">Eliminar</button>
          </td>
        </tr>
        <%
          }
        } else {
        %>
        <tr>
          <td colspan="8"> No se encontraron usuarios. </td>
        </tr>
        <%
          }
        %>
        <!-- Modal para editar usuario -->
        <div id="modal-editar-usuario" class="modal">
          <div class="modal-content">
            <span class="close" id="cerrar-modal">&times;</span>
            <h2>Editar Usuario</h2>
            <form id="form-editar-usuario" method="post">
              <input type="hidden" id="edit-id" name="id">
              <label>Nombre:</label>
              <input type="text" id="edit-nombre" name="nombre" required>
              <label>DNI:</label>
              <input type="text" id="edit-dni" name="dni" required>
              <label>Correo:</label>
              <input type="text" id="edit-correo" name="correo" required>
              <label>Celular:</label>
              <input type="text" id="edit-celular" name="celular" required>
              <label>Rol:</label>
              <select id="edit-rol" name="rol" required>
                <option value="cliente">Cliente</option>
                <option value="encargado">Encargado</option>
                <option value="admin">Admin</option>
              </select>
              <button type="submit" class="btn-filtrar">Guardar Cambios</button>
            </form>
          </div>
        </div>

        <!-- Modal para agregar usuario -->
        <div id="modal-agregar-usuario" class="modal">
          <div class="modal-content">
            <span class="close" id="cerrar-modal-agregar">&times;</span>
            <h2>Agregar Usuario</h2>
            <form id="form-agregar-usuario" method="post">
              <input type="hidden" id="agregar-id" name="id">
              <label>Nombre:</label>
              <input type="text" id="agregar-nombre" name="nombre" required>
              <label>DNI:</label>
              <input type="text" id="agregar-dni" name="dni" required>
              <label>Correo:</label>
              <input type="text" id="agregar-correo" name="correo" required>
              <label>Celular:</label>
              <input type="text" id="agregar-celular" name="celular" required>
              <label>Contrasenia:</label>
              <input type="password" id="agregar-password" name="password" required>
              <button type="submit" class="btn-agregar">Guardar Cambios</button>
            </form>
          </div>
        </div>
        </tbody>
      </table>
    </section>
  </main>
</div>
</body>
</html>
