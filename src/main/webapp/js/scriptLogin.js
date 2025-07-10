function correoValido(correo) {
    const regex = /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/;
    return regex.test(correo);
}

function dniValido(dni) {
    const regex = /^[1-9]\d{7}$/;
    return regex.test(dni);
}

function celularValido(celular) {
    const regex = /^9\d{8}$/;
    return regex.test(celular);
}

function mostrarAlerta(tipo, titulo, mensaje) {
    Swal.fire({
        icon: tipo,
        title: titulo,
        text: mensaje,
    });
}

function validarFormulario(form) {
    const inputs = form.querySelectorAll("input[required]");
    let valido = true;

    for (const input of inputs) {
        const valor = input.value.trim();
        const nombre = input.placeholder || input.name;

        if (valor === "") {
            mostrarAlerta("warning", "Campo vacío", `Completa el campo: ${nombre}`);
            input.focus();
            valido = false;
            break;
        }

        if (input.type === "email" && !correoValido(valor)) {
            mostrarAlerta(
                "error",
                "Correo inválido",
                "Ingresa un correo electrónico válido."
            );
            input.focus();
            valido = false;
            break;
        }

        if (input.name === "dni" && !dniValido(valor)) {
            mostrarAlerta(
                "error",
                "DNI inválido",
                "Debe tener 8 dígitos y no comenzar con 0."
            );
            input.focus();
            valido = false;
            break;
        }

        if (input.name === "celular" && !celularValido(valor)) {
            mostrarAlerta(
                "error",
                "Celular inválido",
                "Debe tener 9 dígitos y comenzar con 9."
            );
            input.focus();
            valido = false;
            break;
        }
    }

    const pass1 = form.querySelector(
        "input[name='password'], input[name='nueva_password']"
    );
    const pass2 = form.querySelector("input[name='confirmar_password']");

    if (pass1 && pass2 && pass1.value !== pass2.value) {
        mostrarAlerta(
            "error",
            "Contraseñas no coinciden",
            "Verifica que ambas contraseñas sean iguales."
        );
        pass2.focus();
        valido = false;
    }

    return valido;
}

document.addEventListener("DOMContentLoaded", () => {
    const formularios = document.querySelectorAll("form");

    formularios.forEach((form) => {
        form.addEventListener("submit", function (e) {
            if (!validarFormulario(this)) {
                e.preventDefault();
            }
        });
    });
});
