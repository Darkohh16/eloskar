package com.eloskar.restaurante.services;

import com.eloskar.restaurante.DAO.PedidoDAO;
import com.eloskar.restaurante.DAO.PedidoDetalleDAO;
import com.eloskar.restaurante.DTO.PedidoDTO;
import com.eloskar.restaurante.DTO.PedidoDetalleDTO;

import java.util.List;

public class PedidoService {
    private PedidoDAO pedidoDAO;
    private PedidoDetalleDAO pedidoDetalleDAO;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
        this.pedidoDetalleDAO = new PedidoDetalleDAO();
    }

    public boolean insertarPedido(PedidoDTO pedido, List<PedidoDetalleDTO> detalles) {
        boolean exito = pedidoDAO.insertPedido(pedido) > 0;
        if (exito && pedido.getIdPedid() > 0) {
            for (PedidoDetalleDTO det : detalles) {
                det.setPedido_id(pedido.getIdPedid());
                pedidoDetalleDAO.insertDetalle(det);
            }
        }
        return exito;
    }

    public List<PedidoDTO> listarPedidosPorUsuario(int usuarioId) {
        return pedidoDAO.listarPedidosPorUsuario(usuarioId);
    }

    public PedidoDTO buscarPedidoPorId(int idPedid) {
        return pedidoDAO.buscarPedidoPorId(idPedid);
    }

    public List<PedidoDetalleDTO> listarDetallesPorPedido(int pedidoId) {
        return pedidoDetalleDAO.listarDetallesPorPedido(pedidoId);
    }

    public List<PedidoDTO> buscarPedidosPorFiltros(String cliente, String estado, String fecha) {
        return pedidoDAO.buscarPedidosPorFiltros(cliente, estado, fecha);
    }

    public boolean actualizarEstado(int idPedid, String nuevoEstado) {
        return pedidoDAO.actualizarEstado(idPedid, nuevoEstado) > 0;
    }

    public List<PedidoDTO> listarTodosPedidos() {
        return pedidoDAO.listarTodosPedidos();
    }

    public int contarPedidosHoy() {
        return pedidoDAO.contarPedidosHoy();
    }

    public double sumarIngresosHoy() {
        return pedidoDAO.sumarIngresosHoy();
    }

    public int contarPedidosPendientes() {
        return pedidoDAO.contarPedidosPendientes();
    }
} 