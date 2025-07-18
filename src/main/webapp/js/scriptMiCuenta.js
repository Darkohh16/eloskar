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
        if (idPedido) {
          fetch('SrvVerDetallePedido?id=' + idPedido)
            .then(resp => resp.text())
            .then(html => {
              // Solo reemplazo la tabla, mantengo el título y botón cerrar
              var tabla = modalContent.querySelector('table');
              if (tabla) tabla.remove();
              var tempDiv = document.createElement('div');
              tempDiv.innerHTML = html;
              var nuevaTabla = tempDiv.querySelector('table');
              if (nuevaTabla) {
                modalContent.insertBefore(nuevaTabla, cerrar.nextSibling.nextSibling); // después del título
              }
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