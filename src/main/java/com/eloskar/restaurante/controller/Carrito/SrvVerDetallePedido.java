package com.eloskar.restaurante.controller.Carrito;

import com.eloskar.restaurante.DTO.PedidoDetalleDTO;
import com.eloskar.restaurante.services.PedidoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SrvVerDetallePedido", value = "/SrvVerDetallePedido")
public class SrvVerDetallePedido extends HttpServlet {

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
        String idStr = request.getParameter("id");
        int idPedido = 0;
        try { idPedido = Integer.parseInt(idStr); } catch (Exception e) { idPedido = 0; }
        PedidoService service = new PedidoService();
        List<PedidoDetalleDTO> detalles = service.listarDetallesPorPedido(idPedido);
        response.setContentType("text/html;charset=UTF-8");
        double total = 0;
        StringBuilder html = new StringBuilder();
        html.append("<table><thead><tr><th>Producto</th><th>Cantidad</th><th>Precio Unitario</th><th>Subtotal</th></tr></thead><tbody>");
        for (PedidoDetalleDTO det : detalles) {
            double subtotal = det.getPrecio_unitario() * det.getCantidad();
            total += subtotal;
            html.append("<tr>")
                    .append("<td>").append(det.getNombreProducto() != null ? det.getNombreProducto() : det.getPlato_id()).append("</td>")
                    .append("<td>").append(det.getCantidad()).append("</td>")
                    .append("<td>S/ ").append(String.format("%.2f", det.getPrecio_unitario())).append("</td>")
                    .append("<td>S/ ").append(String.format("%.2f", subtotal)).append("</td>")
                    .append("</tr>");
        }
        html.append("</tbody><tfoot><tr><td colspan='3' style='text-align:right;font-weight:bold;'>Total:</td><td>S/ ")
                .append(String.format("%.2f", total)).append("</td></tr></tfoot></table>");
        response.getWriter().write(html.toString());
    }
}