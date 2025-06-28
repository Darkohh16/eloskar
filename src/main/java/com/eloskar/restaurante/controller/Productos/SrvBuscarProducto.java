package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvBuscarProducto", value = "/SrvBuscarProducto")
public class SrvBuscarProducto extends HttpServlet {

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
        //PRODUCTOS============================================================
        String filtro = request.getParameter("filtro");
        String cat = request.getParameter("catF");
        if (filtro == null) {
            filtro = "";
        }
        if (cat == null) {
            cat = "Todos";
        }
        ProductoDTO product = new ProductoDTO();
        List<ProductoDTO> productos = product.cargarDatosProductos(filtro, cat);

        //CATEGORIAS===========================================================
        CategoriaDTO categ = new CategoriaDTO();
        List<CategoriaDTO> categorias = categ.cargarDatosCat();

        //DEPLOY============================================================
        request.setAttribute("producto", productos);
        request.setAttribute("categoria", categorias);
        request.getRequestDispatcher("/jsp/Productos.jsp").forward(request, response);
    }
}
