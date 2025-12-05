document.addEventListener('DOMContentLoaded', function() {
  // Lógica para tipo de entrega
  const radioDelivery = document.getElementById('entrega_delivery');
  const radioPickup = document.getElementById('entrega_pickup');
  const direccionInput = document.getElementById('direccion');

  function actualizarDireccion() {
    if (radioPickup && radioPickup.checked) {
      if (direccionInput) {
        direccionInput.value = '';
        direccionInput.disabled = true;
        direccionInput.required = false;
        direccionInput.parentElement.style.opacity = 0.5;
      }
    } else if (radioDelivery && radioDelivery.checked) {
      if (direccionInput) {
        direccionInput.disabled = false;
        direccionInput.required = true;
        direccionInput.parentElement.style.opacity = 1;
      }
    }
  }
  if (radioDelivery && radioPickup && direccionInput) {
    radioDelivery.addEventListener('click', function() { setTimeout(actualizarDireccion, 0); });
    radioPickup.addEventListener('click', function() { setTimeout(actualizarDireccion, 0); });
    radioDelivery.addEventListener('change', actualizarDireccion);
    radioPickup.addEventListener('change', actualizarDireccion);
    actualizarDireccion();
  }

  // Validación del formulario
  const form = document.getElementById('formPedido');
  if (form) {
    form.addEventListener('submit', function(e) {
      const tipoEntrega = document.querySelector('input[name="tipo_entrega"]:checked').value;
      const direccion = direccionInput ? direccionInput.value.trim() : '';
      const metodoPago = document.getElementById('metodo_pago').value;
      let error = '';
      if (tipoEntrega === 'delivery' && !direccion) error = 'Debes ingresar la dirección de entrega.';
      else if (!metodoPago) error = 'Selecciona un método de pago.';
      if (error) {
        e.preventDefault();
        alert(error);
        return false;
      }
    });
  }
});
