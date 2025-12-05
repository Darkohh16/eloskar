document.addEventListener('DOMContentLoaded', function() {
  const form = document.getElementById('formPerfil');
  if (form) {
    form.addEventListener('submit', function(e) {
      const nombre = document.getElementById('nombre').value.trim();
      const correo = document.getElementById('correo').value.trim();
      const celular = document.getElementById('celular').value.trim();
      const dni = document.getElementById('dni').value.trim();
      let error = '';
      if (!nombre) error = 'El nombre es obligatorio.';
      else if (!correo) error = 'El correo es obligatorio.';
      else if (!/^[0-9]{9}$/.test(celular)) error = 'El celular debe tener 9 dígitos.';
      else if (!/^[0-9]{8}$/.test(dni)) error = 'El DNI debe tener 8 dígitos.';
      if (error) {
        e.preventDefault();
        alert(error);
        return false;
      }
    });
  }
});

// Modal de detalles de pedido (dinámico)
document.addEventListener('DOMContentLoaded', function() {
  var modal = document.getElementById('modalDetalles');
  var cerrar = document.getElementById('cerrarModalDetalles');
  var botones = document.querySelectorAll('.btn-detalles');
  var modalContent = modal ? modal.querySelector('.modal-detalles-content') : null;
  if (modal && cerrar && botones.length > 0 && modalContent) {
    botones.forEach(function(btn) {
      btn.addEventListener('click', function() {
        var idPedido = btn.getAttribute('data-id');

        // Actualizar el título del modal con el ID del pedido
        var titulo = modalContent.querySelector('h3');
        if (titulo && idPedido) {
          titulo.textContent = 'Detalles del Pedido #' + idPedido;
        }

        if (idPedido) {
          // Intentar cargar datos desde el backend
          fetch('SrvVerDetallePedido?id=' + idPedido)
            .then(resp => {
              if (!resp.ok) throw new Error('No hay datos del servidor');
              return resp.text();
            })
            .then(html => {
              // Reemplazar la tabla con datos del backend
              var tabla = modalContent.querySelector('table');
              if (tabla) tabla.remove();
              var tempDiv = document.createElement('div');
              tempDiv.innerHTML = html;
              var nuevaTabla = tempDiv.querySelector('table');
              if (nuevaTabla) {
                modalContent.insertBefore(nuevaTabla, cerrar.nextSibling.nextSibling);
              }
              modal.classList.add('active');
            })
            .catch(error => {
              // Si falla el backend, mostrar modal con datos estáticos pero ID actualizado
              console.log('Mostrando datos de ejemplo para pedido #' + idPedido);
              modal.classList.add('active');
            });
        } else {
          modal.classList.add('active');
        }
      });
    });
    cerrar.addEventListener('click', function() {
      modal.classList.remove('active');
    });
    window.addEventListener('click', function(e) {
      if (e.target === modal) {
        modal.classList.remove('active');
      }
    });
  }
}); 