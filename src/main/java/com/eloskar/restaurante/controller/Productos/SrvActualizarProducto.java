package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.DTO.UsuarioDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "SrvActualizarProducto", value = "/SrvActualizarProducto")
public class SrvActualizarProducto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProd(Integer.parseInt(request.getParameter("id")));
        dto.setNombre(request.getParameter("nombre"));
        dto.setDecripcion(request.getParameter("descripcion"));
        dto.setPrecio(Float.parseFloat(request.getParameter("precio")));
        dto.setImagen(request.getParameter("imagen"));

        CategoriaDTO categoria = new CategoriaDTO();
        categoria.setNombre(request.getParameter("cat"));
        dto.setCategoria(categoria);

        if (dto.actualizarProd()){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Producto Actualizado con éxito'); window.location='jsp/Productos.jsp';</script>");
        }
    }
}
