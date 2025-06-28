document.addEventListener("DOMContentLoaded", () => {
    const cP = document.body.dataset.contextPath;

    document.addEventListener("click", function (e) {
        if (e.target.matches(".btn-accion.eliminar")) {
            const id = e.target.dataset.id;

            if (!id) {
                alert("No se encontró el ID del usuario.");
                return;
            }

            if (confirm("¿Eliminar producto con ID " + id + "?")) {
                fetch(cP + `/SrvEliminarProducto?idProd=${id}`, {
                    method: "POST"
                })
                    .then(response => {
                        if (response.ok) {
                            alert("Producto eliminado correctamente.");
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
            document.getElementById("modal-agregar-producto").style.display = "flex";
        }
    });

    document.addEventListener("click", function (e) {
        if (e.target.matches(".btn-accion.editar")) {
            const fila = e.target.closest("tr");

            document.getElementById("edit-id").value = fila.children[0].textContent;
            document.getElementById("edit-nombre").value = fila.children[1].textContent;
            document.getElementById("edit-descripcion").value = fila.children[3].textContent;
            document.getElementById("edit-precio").value = fila.children[4].textContent;
            document.getElementById("edit-imagen").value = fila.children[5].textContent;
            document.getElementById('edit-cat').value = fila.querySelector('.cat').textContent.trim();

            document.getElementById("modal-editar-producto").style.display = "flex";
        }
    });

    window.onclick = function (event) {
        const modalEditar = document.getElementById("modal-editar-producto");
        const modalAgregar = document.getElementById("modal-agregar-producto");

        if (event.target === modalEditar) {
            modalEditar.style.display = "none";
        }
        if (event.target === modalAgregar) {
            modalAgregar.style.display = "none";
        }
    };

    document.getElementById('cerrar-modal')?.addEventListener('click', () => {
        document.getElementById('modal-editar-producto').style.display = 'none';
    });

    document.getElementById('cerrar-modal-agregar')?.addEventListener('click', () => {
        document.getElementById('modal-agregar-producto').style.display = 'none';
    });

    document.getElementById("form-editar-producto").action = cP + "/SrvActualizarProducto";
    document.getElementById("form-agregar-producto").action = cP + "/SrvAgregarProducto";
});
