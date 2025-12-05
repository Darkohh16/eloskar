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

        // ===== MÉTRICAS DE HOY =====
        int reservasHoy = reservaService.contarReservasHoy();
        int pedidosHoy = pedidoService.contarPedidosHoy();
        double ingresosHoy = pedidoService.sumarIngresosHoy();
        int totalPersonasHoy = reservaService.contarTotalPersonasHoy();

        // ===== MÉTRICAS DEL MES =====
        int reservasMes = reservaService.contarReservasMes();
        int pedidosMes = pedidoService.contarPedidosMes();
        double ingresosMes = pedidoService.sumarIngresosMes();

        // ===== MÉTRICAS GENERALES =====
        int totalUsuarios = usuarioService.contarUsuarios();
        int totalProductos = productoService.contarTotalProductos();
        int productosDisponibles = productoService.contarProductosDisponibles();
        int productosDeshabilitados = productoService.contarProductosDeshabilitados();

        // ===== MÉTRICAS DE PEDIDOS =====
        int pedidosPendientes = pedidoService.contarPedidosPendientes();
        int pedidosEntregados = pedidoService.contarPedidosEntregados();
        double ingresosTotales = pedidoService.sumarIngresosTotales();
        double promedioVenta = pedidoService.calcularPromedioVenta();

        // ===== MÉTRICAS DE RESERVAS =====
        int reservasPendientes = reservaService.contarReservasPendientes();
        int reservasConfirmadas = reservaService.contarReservasConfirmadas();

        // ===== CALCULAR PORCENTAJES Y TASAS =====
        double tasaDisponibilidadProductos = totalProductos > 0
            ? (productosDisponibles * 100.0 / totalProductos) : 0;

        double porcentajePedidosEntregados = (pedidosEntregados + pedidosPendientes) > 0
            ? (pedidosEntregados * 100.0 / (pedidosEntregados + pedidosPendientes)) : 0;

        // ===== MÉTRICAS PARA HOY =====
        request.setAttribute("reservasHoy", reservasHoy);
        request.setAttribute("pedidosHoy", pedidosHoy);
        request.setAttribute("ingresosHoy", ingresosHoy);
        request.setAttribute("totalPersonasHoy", totalPersonasHoy);

        // ===== MÉTRICAS DEL MES =====
        request.setAttribute("reservasMes", reservasMes);
        request.setAttribute("pedidosMes", pedidosMes);
        request.setAttribute("ingresosMes", ingresosMes);

        // ===== MÉTRICAS GENERALES =====
        request.setAttribute("totalUsuarios", totalUsuarios);
        request.setAttribute("totalProductos", totalProductos);
        request.setAttribute("productosDisponibles", productosDisponibles);
        request.setAttribute("productosDeshabilitados", productosDeshabilitados);

        // ===== MÉTRICAS DE PEDIDOS =====
        request.setAttribute("pedidosPendientes", pedidosPendientes);
        request.setAttribute("pedidosEntregados", pedidosEntregados);
        request.setAttribute("ingresosTotales", ingresosTotales);
        request.setAttribute("promedioVenta", promedioVenta);

        // ===== MÉTRICAS DE RESERVAS =====
        request.setAttribute("reservasPendientes", reservasPendientes);
        request.setAttribute("reservasConfirmadas", reservasConfirmadas);

        // ===== PORCENTAJES Y TASAS =====
        request.setAttribute("tasaDisponibilidadProductos", tasaDisponibilidadProductos);
        request.setAttribute("porcentajePedidosEntregados", porcentajePedidosEntregados);

        request.getRequestDispatcher("/jsp/dashboardJSP/DashboardPrincipal.jsp").forward(request, response);
    }
}