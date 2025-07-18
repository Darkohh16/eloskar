// Script para la vista de pedidos en el dashboard

document.addEventListener('DOMContentLoaded', function() {
  // Confirmaciones para botones de pedidos en el dashboard
  document.addEventListener('submit', function(e) {
    if (e.target.matches('form') && e.target.querySelector('.btn-accion.entregar')) {
      if (!confirm('¿Deseas marcar este pedido como ENTREGADO?')) {
        e.preventDefault();
      }
    }
    if (e.target.matches('form') && e.target.querySelector('.btn-accion.cancelar')) {
      if (!confirm('¿Deseas CANCELAR este pedido?')) {
        e.preventDefault();
      }
    }
  });
}); 