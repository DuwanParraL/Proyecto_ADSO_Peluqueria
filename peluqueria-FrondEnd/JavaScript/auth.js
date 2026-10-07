document.getElementById('formLogin')?.addEventListener('submit', function (e) {
  e.preventDefault();

  const usuario = document.getElementById('usuario').value;
  const rol = document.getElementById('rolSimulado').value;

  localStorage.setItem('usuarioActivo', JSON.stringify({ usuario, rol }));

  if (rol === 'CLIENTE') {
    window.location.href = './cliente/catalogo.html';
  } else if (rol === 'EMPLEADO' || rol === 'ADMIN') {
    window.location.href = './admin/citas.html';
  }
});

function cerrarSesion() {
  localStorage.removeItem('usuarioActivo');
  window.location.href = '../login.html';
}
