package com.eloskar.restaurante.controller.Dashboard;

import com.eloskar.restaurante.services.PedidoService;
import com.eloskar.restaurante.services.ProductoService;
import com.eloskar.restaurante.services.ReservaService;
import com.eloskar.restaurante.services.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "SrvDashboardPrincipal", value = "/SrvDashboardPrincipal")
public class SrvDashboardPrincipal extends HttpServlet {

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
        UsuarioService usuarioService = new UsuarioService();
        ReservaService reservaService = new ReservaService();
        PedidoService pedidoService = new PedidoService();
        ProductoService productoService = new ProductoService();

        int totalUsuarios = usuarioService.contarUsuarios();
        int reservasHoy = reservaService.contarReservasHoy();
        int pedidosHoy = pedidoService.contarPedidosHoy();
        double ingresosHoy = pedidoService.sumarIngresosHoy();
        int reservasPendientes = reservaService.contarReservasPendientes();
        int pedidosPendientes = pedidoService.contarPedidosPendientes();
        int productosDeshabilitados = productoService.contarProductosDeshabilitados();

        request.setAttribute("totalUsuarios", totalUsuarios);
        request.setAttribute("reservasHoy", reservasHoy);
        request.setAttribute("pedidosHoy", pedidosHoy);
        request.setAttribute("ingresosHoy", ingresosHoy);
        request.setAttribute("reservasPendientes", reservasPendientes);
        request.setAttribute("pedidosPendientes", pedidosPendientes);
        request.setAttribute("productosDeshabilitados", productosDeshabilitados);

        request.getRequestDispatcher("/jsp/dashboardJSP/DashboardPrincipal.jsp").forward(request, response);
    }
}