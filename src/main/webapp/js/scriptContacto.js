document.addEventListener('DOMContentLoaded', function() {
  const form = document.getElementById('formContacto');
  if (form) {
    form.addEventListener('submit', function(e) {
      const nombre = document.getElementById('nombre').value.trim();
      const correo = document.getElementById('correo').value.trim();
      const asunto = document.getElementById('asunto').value.trim();
      const mensaje = document.getElementById('mensaje').value.trim();
      let error = '';
      if (!nombre) error = 'El nombre es obligatorio.';
      else if (!correo) error = 'El correo es obligatorio.';
      else if (!asunto) error = 'El asunto es obligatorio.';
      else if (!mensaje) error = 'El mensaje es obligatorio.';
      if (error) {
        e.preventDefault();
        alert(error);
        return false;
      }
    });
  }
}); 