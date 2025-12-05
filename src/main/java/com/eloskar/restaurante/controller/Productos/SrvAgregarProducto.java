package com.eloskar.restaurante.controller.Productos;

import com.eloskar.restaurante.DTO.CategoriaDTO;
import com.eloskar.restaurante.DTO.ProductoDTO;
import com.eloskar.restaurante.services.ProductoService;
import com.eloskar.restaurante.util.S3Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@WebServlet(name = "SrvAgregarProducto", value = "/SrvAgregarProducto")
@MultipartConfig(maxFileSize = 5 * 1024 * 1024) // 5MB
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

        request.setCharacterEncoding("UTF-8");

        try {
            ProductoDTO dto = new ProductoDTO();
            dto.setNombre(request.getParameter("nombre"));
            dto.setDescripcion(request.getParameter("descripcion"));
            dto.setPrecio(Float.parseFloat(request.getParameter("precio")));

            //subir archivo al S3
            String urlImagenFinal = null;
            Part filePart = null;

            try {
                filePart = request.getPart("imagen");
            } catch (Exception e) {
                //si no viene archivo, dele pa fuera mi loco
                filePart = null;
            }

            System.out.println("---- DEBUG ----");
            System.out.println("Nombre: " + request.getParameter("nombre"));
            System.out.println("Ruta imagen texto: " + request.getParameter("rImagen"));

            Part fp = request.getPart("imagen");
            if (fp == null) System.out.println("filePart = NULL");
            else {
                System.out.println("filename = " + fp.getSubmittedFileName());
                System.out.println("size = " + fp.getSize());
                System.out.println("content-type = " + fp.getContentType());
            }


            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName())
                        .getFileName().toString();
                String contentType = filePart.getContentType();
                long size = filePart.getSize();

                try (InputStream inputStream = filePart.getInputStream()) {
                    urlImagenFinal = S3Util.subirImagen(fileName, inputStream, size, contentType);
                }
            }

            //si no hay URL de S3
            String rImagen = request.getParameter("rImagen");
            if ((urlImagenFinal == null || urlImagenFinal.isEmpty()) &&
                    (rImagen == null || rImagen.trim().isEmpty())) {

                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().write(
                        "<script>alert('No se pudo obtener la imagen: suba un archivo o ingrese una ruta.');" +
                                "window.history.back();</script>"
                );
                return;
            }

            if (urlImagenFinal != null && !urlImagenFinal.isEmpty()) {
                dto.setImagen(urlImagenFinal);
            } else {
                dto.setImagen(rImagen.trim());
            }

            CategoriaDTO cat = new CategoriaDTO();
            cat.setNombre(request.getParameter("cat"));
            dto.setCategoria(cat);

            ProductoService service = new ProductoService();
            boolean ok = service.insertarProd(dto);

            response.setContentType("text/html; charset=UTF-8");
            if (ok) {
                response.getWriter().write(
                        "<script>alert('Producto registrado con éxito');" +
                                "window.location='SrvBuscarProducto?destino=dashboard';</script>"
                );
            } else {
                response.getWriter().write(
                        "<script>alert('No se pudo registrar el producto (insertarProd devolvió false).');" +
                                "window.history.back();</script>"
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().write(
                    "<script>alert('Ocurrió un error al registrar el producto: " + ex.getMessage() + "');" +
                            "window.history.back();</script>"
            );
        }
    }
}
