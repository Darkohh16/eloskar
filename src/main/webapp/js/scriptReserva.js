document.addEventListener('DOMContentLoaded', function() {
  // Validación de login (debe estar en el body como data-user-id)
  const userId = document.body.getAttribute('data-user-id');
  if (!userId) {
    alert('Debes iniciar sesión para poder reservar.');
    window.location.href = 'jsp/eloskarJSP/login/login.jsp';
    return;
  }

  // Validación del formulario de reservas
  const form = document.getElementById('formReserva');
  if (form) {
    form.addEventListener('submit', function(e) {
      // Validación simple del lado del cliente
      const fecha = document.getElementById('fecha').value;
      const hora = document.getElementById('hora').value;
      const personas = document.getElementById('personas').value;
      if (!fecha || !hora || !personas || parseInt(personas) < 1) {
        e.preventDefault();
        alert('Por favor, completa todos los campos correctamente.');
        return false;
      }
    });
  }

  document.addEventListener('submit', function(e) {
    if (e.target.matches('form') && e.target.querySelector('.btn-accion.confirmar')) {
      if (!confirm('¿Deseas confirmar esta reserva?')) {
        e.preventDefault();
      }
    }
    if (e.target.matches('form') && e.target.querySelector('.btn-accion.cancelar')) {
      if (!confirm('¿Deseas cancelar esta reserva?')) {
        e.preventDefault();
      }
    }
  });
});
