package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.services.ProductoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvAgregarProducto", value = "/SrvAgregarProducto")
public class SrvAgregarProducto extends HttpServlet {

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
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(request.getParameter("nombre"));
        dto.setDescripcion(request.getParameter("descripcion"));
        dto.setPrecio(Float.parseFloat(request.getParameter("precio")));
        dto.setImagen(request.getParameter("rImagen"));

        CategoriaDTO cat = new CategoriaDTO();
        cat.setNombre(request.getParameter("cat"));

        dto.setCategoria(cat);

        ProductoService service = new ProductoService();
        if (service.insertarProd(dto)){
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Producto registrado con éxito'); " +
                    "window.location='SrvBuscarProducto?destino=dashboard';</script>");
        }
    }
}