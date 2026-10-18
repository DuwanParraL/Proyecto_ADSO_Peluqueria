/* 
   Soporte para Login modal y Login independiente
 */

function procesarInicioSesion(usuario, rol, origen = 'modal') {
  if (!usuario || !rol) return;

  const dataUsuario = {
    usuario: usuario,
    rol: rol,
    fechaLogin: new Date().toISOString()
  };

  localStorage.setItem('usuarioActivo', JSON.stringify(dataUsuario));

  if (origen === 'standalone') {
    if (rol === 'CLIENTE') {
      window.location.href = './cliente/catalogo.html';
    } else if (rol === 'EMPLEADO' || rol === 'ADMIN') {
      window.location.href = './admin/citas.html';
    }
  } else {
    // Si inicia sesión desde el modal en index.html
    if (rol === 'CLIENTE') {
      window.location.reload();
    } else {
      window.location.href = './admin/citas.html';
    }
  }
}

// Handler formulario login en modal
document.getElementById('formLoginModal')?.addEventListener('submit', function (e) {
  e.preventDefault();
  const usuario = document.getElementById('usuarioModal').value;
  const rol = document.getElementById('rolSimuladoModal').value;
  procesarInicioSesion(usuario, rol, 'modal');
});

// Handler formulario login en login.html tradicional
document.getElementById('formLogin')?.addEventListener('submit', function (e) {
  e.preventDefault();
  const usuario = document.getElementById('usuario').value;
  const rol = document.getElementById('rolSimulado').value;
  procesarInicioSesion(usuario, rol, 'standalone');
});

function cerrarSesion() {
  localStorage.removeItem('usuarioActivo');
  // Si estamos en un subdirectorio como cliente/ o admin/
  if (window.location.pathname.includes('/cliente/') || window.location.pathname.includes('/admin/')) {
    window.location.href = '../index.html';
  } else {
    window.location.reload();
  }
}
