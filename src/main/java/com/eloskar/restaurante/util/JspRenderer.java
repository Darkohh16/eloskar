package com.eloskar.restaurante.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class JspRenderer {

    public static String renderJSP(HttpServletRequest request, HttpServletResponse response, String jspPath)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);

        StringWriter stringWriter = new StringWriter();

        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(stringWriter);
            }
        };

        dispatcher.include(request, wrapper);

        return stringWriter.toString();
    }
}
