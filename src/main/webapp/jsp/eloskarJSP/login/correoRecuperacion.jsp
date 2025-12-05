<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Restablecer contraseña</title>
    <style>
        /* Estilos pensados para correos (simples y robustos) */
        body, html {
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            font-family: Helvetica, Arial, sans-serif;
        }
        .email-container {
            max-width: 650px;
            margin: 30px auto;
            background-color: #ffffff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            border-top: 6px solid #ff9800; /* toque gastronómico */
        }
        .banner {
            width: 100%;
            line-height: 0; /* elimina espacios raros alrededor de la imagen */
        }
        .banner img {
            display: block;
            width: 100%;
            max-height: 200px;
            object-fit: cover;
        }
        .header-title {
            padding: 16px 24px 8px 24px;
            background-color: #ffffff;
        }
        .header-title h1 {
            margin: 0;
            font-size: 24px;
            color: #e65100; /* naranja oscuro tipo restaurante */
        }
        .header-subtitle {
            padding: 0 24px 10px 24px;
            color: #757575;
            font-size: 13px;
        }
        .content {
            padding: 20px 24px 28px 24px;
            color: #333333;
            font-size: 14px;
            line-height: 1.6;
        }
        .content h2 {
            color: #ff9800;
            margin-top: 0;
            margin-bottom: 10px;
            font-size: 18px;
        }
        .btn-recuperar {
            display: inline-block;
            background-color: #43a047; /* verde agradable */
            color: #ffffff !important;
            text-decoration: none;
            padding: 12px 26px;
            border-radius: 4px;
            margin-top: 18px;
            font-weight: bold;
            font-size: 14px;
        }
        .btn-recuperar:hover {
            background-color: #2e7d32;
        }
        .nota {
            margin-top: 18px;
            font-size: 13px;
            color: #616161;
        }
        .footer {
            background-color: #fafafa;
            padding: 16px 20px;
            text-align: center;
            font-size: 11px;
            color: #9e9e9e;
            border-top: 1px solid #eeeeee;
        }
    </style>
</head>
<body>
<div class="email-container">
    <!-- Banner con imagen (más fiable en clientes de correo) -->
    <div class="banner">
        <img src="https://www.bbva.pe/content/dam/public-web/peru/photos/blog-img/marquees/Subtitle-Desktop-dia-del-ceviche-.im1687969711143im.jpg"
             alt="Restaurante - El Oskar">
    </div>

    <div class="header-title">
        <h1>Restablecer contraseña</h1>
    </div>
    <div class="header-subtitle">
        Solicitud de recuperación de acceso a tu cuenta de El Oskar.
    </div>

    <div class="content">
        <h2>Hola <%= request.getAttribute("nombre") %>,</h2>

        <p>
            Hemos recibido una solicitud para restablecer la contraseña de tu cuenta.
            Para continuar con el proceso de recuperación, por favor haz clic en el siguiente botón:
        </p>

        <p style="text-align:center;">
            <a href="<%= request.getAttribute("url") %>" class="btn-recuperar">
                Restablecer contraseña
            </a>
        </p>

        <p class="nota">
            Si tú no solicitaste este cambio, puedes ignorar este mensaje y tu contraseña actual seguirá siendo válida.
        </p>

        <p class="nota">
            <strong>Importante:</strong> este enlace de recuperación expirará en <strong>1 hora</strong> por motivos de seguridad.
        </p>
    </div>

    <div class="footer">
        &copy; 2025 Sistema El Oskar — Todos los derechos reservados.<br>
        Este es un correo automático, por favor no respondas a este mensaje.
    </div>
</div>
</body>
</html>
