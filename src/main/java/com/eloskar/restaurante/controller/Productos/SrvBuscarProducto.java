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
        //PRODUCTOS============================================================
        String rutaP = request.getParameter("ruta");
        String filtro = request.getParameter("filtro");
        String cat = request.getParameter("catF");
        String pagina = request.getParameter("pagina");
        String filtroF = request.getParameter("filtroF");
        String catFiltro = request.getParameter("catFiltro");
        
        int pag;
        int totalPag;
        int entradasMax = 5;
        
        // Manejo de página
        if (pagina == null) {
            pag = 1;
        } else {
            try {
                pag = Integer.parseInt(pagina);
            } catch (NumberFormatException e) {
                pag = 1;
            }
        }
        
        // Manejo de filtro - usar filtroF si está disponible (para paginación)
        if (filtroF != null && !filtroF.isEmpty()) {
            filtro = filtroF;
        } else if (filtro == null) {
            filtro = "";
        }
        
        // Manejo de categoría - usar catFiltro si está disponible (para paginación)
        if (catFiltro != null && !catFiltro.isEmpty()) {
            cat = catFiltro;
        } else if (cat == null) {
            cat = "Todos";
        }

        ProductoService service = new ProductoService();
        totalPag = service.totalProductos(entradasMax, filtro, cat);
        
        // Validar que la página no exceda el total
        if (totalPag > 0 && pag > totalPag) {
            pag = 1;
        }
        
        List<ProductoDTO> productos = service.cargarDatosProductos(filtro, cat, pag, entradasMax);

        //CATEGORIAS===========================================================
        CategoriaDTO categ = new CategoriaDTO();
        List<CategoriaDTO> categorias = categ.cargarDatosCat();

        //DEPLOY============================================================
        request.setAttribute("producto", productos);//Mostrar productos
        request.setAttribute("categoria", categorias);//Mostrar lista categorias en filtro
        request.setAttribute("catF", cat);
        request.setAttribute("totalPag", totalPag);
        request.setAttribute("pagina", pag);
        request.setAttribute("filtro", filtro);
        RequestDispatcher dp = request.getRequestDispatcher("/jsp/dashboardJSP/Productos.jsp");
        dp.forward(request, response);
    }
}
