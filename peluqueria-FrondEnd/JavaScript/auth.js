/**
 * URBAN LOOK - Módulo de Autenticación Real
 * Conexión directa con POST /api/auth/login del backend Spring Boot
 */

function obtenerUsuarioSesion() {
  const data = localStorage.getItem('usuarioActivo');
  if (!data) return null;
  try {
    return JSON.parse(data);
  } catch (e) {
    localStorage.removeItem('usuarioActivo');
    return null;
  }
}

async function procesarInicioSesion(usuario, password, origen = 'modal', errorElementId = null) {
  if (!usuario || !password) {
    mostrarMensajeError('Por favor ingresa usuario/correo y contraseña.', errorElementId);
    return;
  }

  mostrarMensajeCargando(true, origen);

  try {
    if (!window.UrbanLookAPI) {
      throw new Error('El módulo de API no está inicializado.');
    }

    const respuesta = await window.UrbanLookAPI.login(usuario.trim(), password);

    // Guardar sesión real retornada por Spring Boot / MySQL
    localStorage.setItem('usuarioActivo', JSON.stringify(respuesta));

    const rol = (respuesta.rol || 'CLIENTE').toUpperCase();

    // Redirección basada en rol real
    const isInSubfolder = window.location.pathname.includes('/cliente/') || window.location.pathname.includes('/admin/');
    const basePath = isInSubfolder ? '../' : './';

    if (rol === 'ADMIN' || rol === 'EMPLEADO') {
      window.location.href = `${basePath}admin/citas.html`;
    } else {
      if (origen === 'standalone') {
        window.location.href = `${basePath}cliente/catalogo.html`;
      } else {
        window.location.reload();
      }
    }
  } catch (error) {
    console.error('[Auth Error]', error);
    mostrarMensajeError(error.message || 'Error al iniciar sesión. Verifica tus credenciales.', errorElementId);
  } finally {
    mostrarMensajeCargando(false, origen);
  }
}

function mostrarMensajeError(mensaje, elementId) {
  if (elementId) {
    const el = document.getElementById(elementId);
    if (el) {
      el.textContent = mensaje;
      el.classList.remove('d-none');
      return;
    }
  }
  alert(`⚠️ ${mensaje}`);
}

function mostrarMensajeCargando(cargando, origen) {
  const btnSubmit = origen === 'modal' 
    ? document.querySelector('#formLoginModal button[type="submit"]')
    : document.querySelector('#formLogin button[type="submit"]');

  if (btnSubmit) {
    if (cargando) {
      btnSubmit.disabled = true;
      btnSubmit.dataset.originalText = btnSubmit.innerHTML;
      btnSubmit.innerHTML = '<span class="spinner-border spinner-border-sm me-2"></span> Verificando...';
    } else {
      btnSubmit.disabled = false;
      if (btnSubmit.dataset.originalText) {
        btnSubmit.innerHTML = btnSubmit.dataset.originalText;
      }
    }
  }
}

// Handler formulario login en modal (index.html)
document.addEventListener('DOMContentLoaded', () => {
  const formModal = document.getElementById('formLoginModal');
  if (formModal) {
    formModal.addEventListener('submit', async function (e) {
      e.preventDefault();
      const usuarioInput = document.getElementById('usuarioModal');
      const passwordInput = document.getElementById('passwordModal');
      const usuario = usuarioInput ? usuarioInput.value : '';
      const password = passwordInput ? passwordInput.value : '';
      await procesarInicioSesion(usuario, password, 'modal', 'login-modal-error');
    });
  }

  // Handler formulario login en login.html
  const formLogin = document.getElementById('formLogin');
  if (formLogin) {
    formLogin.addEventListener('submit', async function (e) {
      e.preventDefault();
      const usuarioInput = document.getElementById('usuario');
      const passwordInput = document.getElementById('password');
      const usuario = usuarioInput ? usuarioInput.value : '';
      const password = passwordInput ? passwordInput.value : '';
      await procesarInicioSesion(usuario, password, 'standalone', 'login-error-msg');
    });
  }
});

function cerrarSesion() {
  localStorage.removeItem('usuarioActivo');
  const isInSubfolder = window.location.pathname.includes('/cliente/') || window.location.pathname.includes('/admin/');
  if (isInSubfolder) {
    window.location.href = '../index.html';
  } else {
    window.location.reload();
  }
}

window.obtenerUsuarioSesion = obtenerUsuarioSesion;
window.cerrarSesion = cerrarSesion;
