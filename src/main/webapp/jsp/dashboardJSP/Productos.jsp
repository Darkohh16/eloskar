<%@ page import="com.eloskar.restaurante.DTO.ProductoDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eloskar.restaurante.DTO.CategoriaDTO" %><%--
  Created by IntelliJ IDEA.
  User: joseg
  Date: 27/06/2025
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<ProductoDTO> productos = (List<ProductoDTO>) request.getAttribute("producto");
    String d, a;
    List<CategoriaDTO> categorias = (List<CategoriaDTO>) request.getAttribute("categoria");
    int totalPaginas = (int)request.getAttribute("totalPag");
    int paginaActual = (int)request.getAttribute("pagina");

%>

<html lang="es">
<script src="${pageContext.request.contextPath}/js/scripts.js"></script>
<script src="${pageContext.request.contextPath}/js/scriptProducto.js"></script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Productos - Restaurante</title>
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
            <h1>Gestión de Productos</h1>
            <div class="user-info">
                <span>Admin</span>
            </div>
        </header>
        <section class="filters-section">
            <div class="filters-bar">
                <button class="btn-accion agregar">Agregar Producto</button>
                <form method="get" action="SrvBuscarProducto" class="filters-form">
                    <input type="text" name="filtro" placeholder="Buscar por nombre o descripcion..." 
                           class="input-search" value="<%= request.getAttribute("filtro") != null ? request.getAttribute("filtro") : "" %>">
                    <select id="edit-catF" name="catF">
                        <option value="Todos" <%= "Todos".equals(request.getAttribute("catF")) ? "selected" : "" %>>Todos</option>
                        <%
                            if (categorias != null) {
                                for(CategoriaDTO c : categorias) {
                        %>
                        <option value="<%= c.getNombre() %>" 
                                <%= c.getNombre().equals(request.getAttribute("catF")) ? "selected" : "" %>>
                            <%= c.getNombre() %>
                        </option>
                        <%
                                }
                            }
                        %>
                    </select>
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
                    <th>Categoría</th>
                    <th>Descripcion</th>
                    <th>Precio</th>
                    <th>Imagen</th>
                    <th>Disponible</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <%
                    if (productos != null) {
                        for(ProductoDTO p : productos) {
                %>
                <tr>
                    <td><%= p.getIdProd() %></td>
                    <td><%= p.getNombre() %></td>
                    <td class="cat"><%= p.getCategoria().getNombre() %></td>
                    <td><%= p.getDescripcion() %></td>
                    <td><%= p.getPrecio() %></td>
                    <td><%= p.getImagen() %></td>
                    <% if (p.isDisponible()) {
                        d = "Sí";
                        a = "si";
                    } else {
                        d = "No";
                        a = "no";
                    }
                    %>
                    <td><span class="disponible <%= a %> "> <%= d %> </span></td>
                    <td>
                        <button class="btn-accion editar">Editar</button>
                        <button class="btn-accion eliminar" data-id="<%= p.getIdProd() %>">Eliminar</button>
                        <button
                                class="btn-accion cambiar <%= a %>"
                                data-id1="<%= p.getIdProd() %>"
                                data-id2="<%= p.isDisponible() %>">
                                <%= d.equals("No") ? "Disponible" : "No disponible" %>
                        </button>
                    </td>
                </tr>
                <%      }
                }
                %>

                <!-- Modal para editar productos -->
                <div id="modal-editar-producto" class="modal">
                    <div class="modal-content">
                        <span class="close" id="cerrar-modal">&times;</span>
                        <h2>Editar Producto</h2>
                        <form id="form-editar-producto" method="post">
                            <input type="hidden" id="edit-id" name="id">
                            <label>Nombre:</label>
                            <input type="text" id="edit-nombre" name="nombre" required>
                            <label>Descripcion:</label>
                            <input type="text" id="edit-descripcion" name="descripcion" required>
                            <label>Precio:</label>
                            <input type="text" id="edit-precio" name="precio" required>
                            <label>Imagen:</label>
                            <input type="text" id="edit-imagen" name="imagen" required>
                            <label>Categoria:</label>
                            <select  id="edit-cat" name="cat" required>
                                <%
                                    if (categorias != null) {
                                        for(CategoriaDTO c : categorias) {
                                %>
                                    <option value="<%= c.getNombre() %>"> <%= c.getNombre() %> </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <button type="submit" class="btn-filtrar">Guardar Cambios</button>
                        </form>
                    </div>
                </div>

                <!-- Modal para agregar producto -->
                <div id="modal-agregar-producto" class="modal">
                    <div class="modal-content">
                        <span class="close" id="cerrar-modal-agregar">&times;</span>
                        <h2>Agregar Producto</h2>
                        <form id="form-agregar-producto" method="post">
                            <label>Nombre:</label>
                            <input type="text" id="agregar-nombre" name="nombre" required>
                            <label>Descripcion:</label>
                            <input type="text" id="agregar-descripcion" name="descripcion" required>
                            <label>Precio:</label>
                            <input type="text" id="agregar-precio" name="precio" required>
                            <label>Ruta Imagen:</label>
                            <input type="text" id="agregar-rImagen" name="rImagen" required>
                            <label>Categoria:</label>
                            <select  id="agregar-cat" name="cat" required>
                                <%
                                    if (categorias != null) {
                                        for(CategoriaDTO c : categorias) {
                                %>
                                <option value="<%= c.getNombre() %>"> <%= c.getNombre() %> </option>
                                <%
                                        }
                                    }
                                %>
                            </select>
                            <button type="submit" class="btn-agregar">Guardar Cambios</button>
                        </form>
                    </div>
                </div>

                </tbody>
            </table>

            <div class="pagination">
                <form method="get" action="SrvBuscarProducto" class="filters-form">
                    <input type="hidden" name="filtro" value="<%= request.getAttribute("filtro") != null ? request.getAttribute("filtro") : "" %>">
                    <input type="hidden" name="catF" value="<%= request.getAttribute("catF") != null ? request.getAttribute("catF") : "Todos" %>">
                    <%
                        for (int i = 1; i <= totalPaginas; i++) {
                            if (i == paginaActual) {
                    %>
                    <button type="button" class="btn-page active"><%= i %></button>
                    <%  } else { %>
                    <button type="submit" name="pagina" value="<%= i %>" class="btn-page">
                        <%= i %>
                    </button>
                    <%  }
                    } %>
                </form>
            </div>
        </section>
    </main>
</div>
</body>
</html>
