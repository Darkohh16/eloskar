package com.eloskar.restaurante.util;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.config.TransportStrategy;

public class CorreoUtil {

    public static void enviar(String destino, String asunto, String cuerpo) {
        Email email = EmailBuilder.startingBlank()
                .from("El Oskar", "tu-correo@gmail.com")
                .to("Usuario", destino)
                .withSubject(asunto)
                .withPlainText(cuerpo)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "eloskarpruebas@gmail.com", "ixup rmuw dbih vhop")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .buildMailer();

        mailer.sendMail(email);
    }
}
