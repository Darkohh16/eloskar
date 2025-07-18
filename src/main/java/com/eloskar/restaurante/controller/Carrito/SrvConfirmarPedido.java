package com.eloskar.restaurante.controller.Carrito;

import com.eloskar.restaurante.DTO.CarritoDTO;
import com.eloskar.restaurante.DTO.CarritoDetalleDTO;
import com.eloskar.restaurante.DTO.PedidoDTO;
import com.eloskar.restaurante.DTO.PedidoDetalleDTO;
import com.eloskar.restaurante.services.CarritoService;
import com.eloskar.restaurante.services.DetalleCarritoService;
import com.eloskar.restaurante.services.PedidoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SrvConfirmarPedido", value = "/SrvConfirmarPedido")
public class SrvConfirmarPedido extends HttpServlet {

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
        Integer idUser = (Integer) request.getSession().getAttribute("idUser");
        if (idUser == null) {
            response.sendRedirect(request.getContextPath() + "/jsp/eloskarJSP/login/login.jsp");
            return;
        }
        // Obtener carrito y detalles
        CarritoService carritoService = new CarritoService();
        CarritoDTO carrito = carritoService.cargarCarritos(idUser);
        if (carrito == null || carrito.getDetalles() == null || carrito.getDetalles().isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('No hay productos en el carrito'); window.location='SrvCarrito';</script>");
            return;
        }
        // Datos del formulario
        String tipoEntrega = request.getParameter("tipo_entrega");
        String direccion = request.getParameter("direccion");
        String metodoPagoStr = request.getParameter("metodo_pago");
        int metodoPago = 0;
        try { metodoPago = Integer.parseInt(metodoPagoStr); } catch (Exception e) { metodoPago = 0; }
        // Calcular total
        double total = 0;
        List<PedidoDetalleDTO> detallesPedido = new ArrayList<>();
        DetalleCarritoService detalleCarritoService = new DetalleCarritoService();
        for (CarritoDetalleDTO det : carrito.getDetalles()) {
            PedidoDetalleDTO pd = new PedidoDetalleDTO();
            pd.setPlato_id(det.getProducto_id());
            pd.setCantidad(det.getCantidad());
            // Obtener precio unitario del producto
            pd.setPrecio_unitario(detalleCarritoService.obtenerPrecioProducto(det.getProducto_id()));
            total += pd.getPrecio_unitario() * pd.getCantidad();
            detallesPedido.add(pd);
        }
        // Sumar envío fijo si corresponde (puedes ajustar la lógica)
        if ("delivery".equals(tipoEntrega)) {
            total += 5.0;
        }
        // Crear pedido
        PedidoDTO pedido = new PedidoDTO();
        pedido.setUsuario_id(idUser);
        pedido.setTotal(total);
        pedido.setEstado("pendiente");
        pedido.setMetodo_pago_id(metodoPago);
        pedido.setTipo_entrega(tipoEntrega);
        pedido.setDireccion("pickup".equals(tipoEntrega) ? null : direccion);
        // Insertar pedido y detalles
        PedidoService pedidoService = new PedidoService();
        boolean exito = pedidoService.insertarPedido(pedido, detallesPedido);
        // Vaciar carrito
        if (exito) {
            carritoService.eliminarCarrito(carrito.getIdCarrito());
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Pedido registrado con éxito'); window.location='SrvCarrito';</script>");
        } else {
            response.setContentType("text/html");
            response.getWriter().write("<script>alert('Error al registrar el pedido'); window.location='SrvCarrito';</script>");
        }
    }
}
