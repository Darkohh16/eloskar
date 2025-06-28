document.addEventListener("DOMContentLoaded", () => {
    const cP = document.body.dataset.contextPath;


    document.addEventListener("click", function (e) {
        if (e.target.matches(".btn-accion.eliminar")) {
            const id = e.target.dataset.id;

            if (!id) {
                alert("No se encontró el ID del usuario.");
                return;
            }

            if (confirm("¿Eliminar usuario con ID " + id + "?")) {
                fetch(cP + `/SrvEliminarUsuario?idUser=${id}`, {
                    method: "POST"
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Usuario eliminado correctamente.");
                            location.reload();
                        } else {
                            alert("Error al eliminar. Código: " + response.status);
                        }
                    })
                    .catch(error => {
                        console.error("Error:", error);
                        alert("Error en la solicitud.");
                    });
            }
        }
    });

    document.addEventListener("click", function (e) {
        if (e.target.matches(".btn-accion.agregar")) {
            document.getElementById("modal-agregar-usuario").style.display = "flex";
        }
    });

    document.addEventListener("click", function (e) {
        if (e.target.matches(".btn-accion.editar")) {
            const fila = e.target.closest("tr");

            document.getElementById("edit-id").value = fila.children[0].textContent;
            document.getElementById("edit-nombre").value = fila.children[1].textContent;
            document.getElementById("edit-dni").value = fila.children[2].textContent;
            document.getElementById("edit-correo").value = fila.children[3].textContent;
            document.getElementById("edit-celular").value = fila.children[4].textContent;
            const rolElement = fila.querySelector('.rol');
            if (rolElement.classList.contains('admin')) {
                document.getElementById('edit-rol').value = 'admin';
            } else if (rolElement.classList.contains('encargado')) {
                document.getElementById('edit-rol').value = 'encargado';
            } else {
                document.getElementById('edit-rol').value = 'cliente';
            }

            document.getElementById("modal-editar-usuario").style.display = "flex";
        }
    });

    window.onclick = function (event) {
        const modalEditar = document.getElementById("modal-editar-usuario");
        const modalAgregar = document.getElementById("modal-agregar-usuario");

        if (event.target === modalEditar) {
            modalEditar.style.display = "none";
        }
        if (event.target === modalAgregar) {
            modalAgregar.style.display = "none";
        }
    };

    document.getElementById('cerrar-modal')?.addEventListener('click', () => {
        document.getElementById('modal-editar-usuario').style.display = 'none';
    });

    document.getElementById('cerrar-modal-agregar')?.addEventListener('click', () => {
        document.getElementById('modal-agregar-usuario').style.display = 'none';
    });

    document.getElementById("form-agregar-usuario").action = cP + "/SrvAgregarUsuario";
    document.getElementById("form-editar-usuario").action = cP + "/SrvActualizarUsuario";
});
