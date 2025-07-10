package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.services.ProductoService;
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
        String destino = request.getParameter("destino");

        //PRODUCTOS============================================================
        String filtro = request.getParameter("filtro");
        String cat = request.getParameter("catF");
        String pagina = request.getParameter("pagina");
        String filtroF = request.getParameter("filtroF");
        String catFiltro = request.getParameter("catFiltro");
        
        int pag;
        int totalPag;
        int entradasMax;
        if ("dashboard".equals(destino)) {
            entradasMax = 5;
        } else {
            entradasMax = Integer.MAX_VALUE;
        }

        
        // Manejo de página con validación
        if (pagina == null || pagina.trim().isEmpty()) {
            pag = 1;
        } else {
            try {
                pag = Integer.parseInt(pagina.trim());
                if (pag < 1) pag = 1;
            } catch (NumberFormatException e) {
                pag = 1;
            }
        }
        
        // Optimizar manejo de filtros
        if (filtroF != null && !filtroF.trim().isEmpty()) {
            filtro = filtroF.trim();
        } else if (filtro != null) {
            filtro = filtro.trim();
        } else {
            filtro = "";
        }
        
        // Optimizar manejo de categoría
        if (catFiltro != null && !catFiltro.trim().isEmpty()) {
            cat = catFiltro.trim();
        } else if (cat != null) {
            cat = cat.trim();
        } else {
            cat = "Todos";
        }

        ProductoService service = new ProductoService();
        
        // Calcular total de páginas
        totalPag = service.totalProductos(entradasMax, filtro, cat);
        
        // Validar que la página no exceda el total
        if (totalPag > 0 && pag > totalPag) {
            pag = 1;
        }
        
        // Cargar productos
        List<ProductoDTO> productos = service.cargarDatosProductos(filtro, cat, pag, entradasMax);

        //CATEGORIAS===========================================================
        CategoriaDTO categ = new CategoriaDTO();
        List<CategoriaDTO> categorias = categ.cargarDatosCat();

        //DEPLOY============================================================
        request.setAttribute("producto", productos);
        request.setAttribute("categoria", categorias);
        request.setAttribute("catF", cat);
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pagina", pag);
        request.setAttribute("filtro", filtro);
        
//        RequestDispatcher dp = request.getRequestDispatcher("/jsp/dashboardJSP/Productos.jsp");
//        dp.forward(request, response);

        // Determinar el destino
        String jspPath;

        if ("dashboard".equals(destino)) {
            jspPath = "/jsp/dashboardJSP/Productos.jsp";
        } else {
            jspPath = "/jsp/eloskarJSP/index.jsp";
        }

        request.setAttribute("jspPath", jspPath);

        RequestDispatcher dp = request.getRequestDispatcher(jspPath);
        dp.forward(request, response);
    }
}
