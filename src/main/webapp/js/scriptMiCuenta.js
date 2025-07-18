// Validación simple del formulario de perfil

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