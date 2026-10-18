/*
   URBAN LOOK - JAVASCRIPT PRINCIPAL
   Gestión de vistas, pestañas, modales y selección de citas
  */

document.addEventListener('DOMContentLoaded', () => {
  initNavigation();
  initServicesFilter();
  initServiceSelection();
  initSubtabs();
  initModals();
  checkSessionStatus();
  checkBackendStatus();
});

async function checkBackendStatus() {
  const statusPill = document.getElementById('backend-status-pill');
  if (window.UrbanLookAPI) {
    const online = await window.UrbanLookAPI.verificarConexionBackend();
    if (statusPill) {
      if (online) {
        statusPill.innerHTML = '<i class="bi bi-circle-fill text-success" style="font-size:0.55rem;"></i> Backend: Online (8080)';
        statusPill.className = 'badge rounded-pill bg-success-subtle border border-success text-success small d-none d-md-inline-flex align-items-center gap-1 py-1 px-2';
        console.log('🟢 [URBAN LOOK] Backend Spring Boot conectado exitosamente en http://localhost:8080');
      } else {
        statusPill.innerHTML = '<i class="bi bi-circle-fill text-warning" style="font-size:0.55rem;"></i> Backend: Offline (Modo Local)';
        statusPill.className = 'badge rounded-pill bg-dark border border-warning text-warning small d-none d-md-inline-flex align-items-center gap-1 py-1 px-2';
        console.info('🟡 [URBAN LOOK] Backend Spring Boot no detectado en http://localhost:8080. La aplicación funciona en modo demostración local.');
      }
    }
  }
}

// ---------------------------------------------------------
// 1. NAVEGACIÓN POR PESTAÑAS (TABS)
// ---------------------------------------------------------
function initNavigation() {
  const navLinks = document.querySelectorAll('.nav-link-custom');
  const sections = document.querySelectorAll('.section-panel');
  const heroContent = document.getElementById('hero-banner-content');

  navLinks.forEach(link => {
    link.addEventListener('click', (e) => {
      e.preventDefault();
      const targetId = link.getAttribute('data-target');

      // Remover activo de todos los links
      navLinks.forEach(l => l.classList.remove('active'));
      link.classList.add('active');

      // Si selecciona una sección específica
      if (targetId) {
        sections.forEach(sec => sec.classList.remove('active'));
        const targetSec = document.getElementById(targetId);
        if (targetSec) {
          targetSec.classList.add('active');
          // Desplazar suavemente a la sección
          targetSec.scrollIntoView({ behavior: 'smooth', block: 'start' });
        }
      }
    });
  });

  // Botón "AGENDA TU CITA" del Hero
  const heroBtnAgenda = document.getElementById('hero-btn-agenda');
  if (heroBtnAgenda) {
    heroBtnAgenda.addEventListener('click', () => {
      const agendaTab = document.querySelector('[data-target="section-agendar"]');
      if (agendaTab) agendaTab.click();
    });
  }

  // Logo lleva al Inicio
  const logoBrand = document.querySelector('.logo-brand');
  if (logoBrand) {
    logoBrand.addEventListener('click', (e) => {
      e.preventDefault();
      navLinks.forEach(l => l.classList.remove('active'));
      sections.forEach(sec => sec.classList.remove('active'));
      window.scrollTo({ top: 0, behavior: 'smooth' });
    });
  }
}

// ---------------------------------------------------------
// 2. FILTRO DE CATEGORÍAS DE SERVICIOS
// ---------------------------------------------------------
function initServicesFilter() {
  const filterBtns = document.querySelectorAll('.category-pill-btn');
  const serviceCards = document.querySelectorAll('.service-card-item');

  filterBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      filterBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');

      const category = btn.getAttribute('data-category');

      serviceCards.forEach(card => {
        if (category === 'all' || card.getAttribute('data-category') === category) {
          card.style.display = 'block';
          card.style.animation = 'fadeIn 0.3s ease';
        } else {
          card.style.display = 'none';
        }
      });
    });
  });
}

// ---------------------------------------------------------
// 3. SELECCIÓN DINÁMICA DE SERVICIOS Y BARRA FLOTANTE
// ---------------------------------------------------------
let selectedServices = [];

function initServiceSelection() {
  const selectBtns = document.querySelectorAll('.btn-select-service');
  const floatingBar = document.getElementById('floating-cart-bar');
  const countLabel = document.getElementById('cart-service-count');
  const totalLabel = document.getElementById('cart-total-price');
  const btnNext = document.getElementById('cart-btn-next');

  selectBtns.forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.stopPropagation();
      const card = btn.closest('.service-card');
      const id = card.getAttribute('data-id');
      const name = card.getAttribute('data-name');
      const price = parseInt(card.getAttribute('data-price'), 10);

      const index = selectedServices.findIndex(s => s.id === id);
      if (index >= 0) {
        selectedServices.splice(index, 1);
        card.classList.remove('selected');
        btn.textContent = 'Seleccionar';
      } else {
        selectedServices.push({ id, name, price });
        card.classList.add('selected');
        btn.textContent = '✓ Seleccionado';
      }

      updateFloatingBar();
    });
  });

  function updateFloatingBar() {
    if (!floatingBar) return;
    if (selectedServices.length > 0) {
      const total = selectedServices.reduce((sum, item) => sum + item.price, 0);
      countLabel.textContent = `${selectedServices.length} servicio${selectedServices.length > 1 ? 's' : ''} seleccionado${selectedServices.length > 1 ? 's' : ''}`;
      totalLabel.textContent = `$${total.toLocaleString('es-CO')}`;
      floatingBar.classList.add('visible');
    } else {
      floatingBar.classList.remove('visible');
    }
  }

  if (btnNext) {
    btnNext.addEventListener('click', () => {
      const user = localStorage.getItem('usuarioActivo');
      if (!user) {
        openModal('modal-login');
      } else {
        const agendaTab = document.querySelector('[data-target="section-agendar"]');
        if (agendaTab) agendaTab.click();
      }
    });
  }
}

// ---------------------------------------------------------
// 4. SUBPESTAÑAS QUIÉNES SOMOS (HISTORIA Y RESEÑAS)
// ---------------------------------------------------------
function initSubtabs() {
  const subtabBtns = document.querySelectorAll('.subtab-btn');
  const subtabPanels = document.querySelectorAll('.subtab-content');

  subtabBtns.forEach(btn => {
    btn.addEventListener('click', () => {
      subtabBtns.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');

      const targetId = btn.getAttribute('data-subtab');
      subtabPanels.forEach(panel => {
        panel.style.display = panel.id === targetId ? 'block' : 'none';
      });
    });
  });
}

// ---------------------------------------------------------
// 5. GESTIÓN DE MODALES
// ---------------------------------------------------------
function initModals() {
  // Botones para abrir login
  document.querySelectorAll('.btn-open-login').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.preventDefault();
      closeModal('modal-registro');
      openModal('modal-login');
    });
  });

  // Botones para abrir registro
  document.querySelectorAll('.btn-open-registro').forEach(btn => {
    btn.addEventListener('click', (e) => {
      e.preventDefault();
      closeModal('modal-login');
      openModal('modal-registro');
    });
  });

  // Botones de cierre (X)
  document.querySelectorAll('.btn-close-modal').forEach(btn => {
    btn.addEventListener('click', () => {
      const modal = btn.closest('.modal-backdrop-custom');
      if (modal) modal.classList.remove('active');
    });
  });

  // Cerrar al dar clic fuera de la tarjeta modal
  document.querySelectorAll('.modal-backdrop-custom').forEach(modal => {
    modal.addEventListener('click', (e) => {
      if (e.target === modal) {
        modal.classList.remove('active');
      }
    });
  });

  // Alternar visibilidad de contraseña
  document.querySelectorAll('.btn-toggle-pwd').forEach(btn => {
    btn.addEventListener('click', () => {
      const targetInput = document.getElementById(btn.getAttribute('data-input'));
      if (targetInput) {
        targetInput.type = targetInput.type === 'password' ? 'text' : 'password';
      }
    });
  });

  // Formulario de Registro (Conectado con Spring Boot /api/usuarios)
  const formRegistro = document.getElementById('formRegistro');
  if (formRegistro) {
    formRegistro.addEventListener('submit', async (e) => {
      e.preventDefault();

      const nombre = document.getElementById('regNombre')?.value.trim() || '';
      const apellido = document.getElementById('regApellido')?.value.trim() || '';
      const identification = document.getElementById('regDocumento')?.value.trim() || '';
      const correo = document.getElementById('regEmail')?.value.trim() || '';
      const celular = document.getElementById('regCelular')?.value.trim() || '';
      const password = document.getElementById('regPassword')?.value || '';

      const nuevoUsuario = {
        nombre: nombre,
        apellido: apellido,
        identification: identification,
        correo: correo,
        user: correo.split('@')[0] || nombre.toLowerCase(),
        password: password,
        id_roles: 1 // 1 = Cliente por defecto
      };

      console.log('// CONEXIÓN BACKEND: Enviando usuario a http://localhost:8080/api/usuarios', nuevoUsuario);

      try {
        if (window.UrbanLookAPI) {
          await window.UrbanLookAPI.registrarUsuario(nuevoUsuario);
          alert(`✅ ¡CUENTA CREADA EN LA BASE DE DATOS!\n\nBienvenido ${nombre} ${apellido}.\nTu usuario ha sido registrado en Spring Boot / MySQL.`);
        }
      } catch (err) {
        console.warn('// CONEXIÓN BACKEND: Backend offline. Guardando simulado:', err.message);
        alert(`✅ ¡CUENTA REGISTRADA (MODO SIMULACIÓN)!\n\nBienvenido ${nombre}.\n[NOTA PARA DESARROLLO]: Cuando tu compañero inicie el backend Spring Boot en el puerto 8080, el registro se guardará en MySQL.`);
      }

      closeModal('modal-registro');
      openModal('modal-login');
    });
  }

  // Formulario de Contacto
  const formContacto = document.getElementById('formContacto');
  if (formContacto) {
    formContacto.addEventListener('submit', (e) => {
      e.preventDefault();
      alert('¡Gracias por escribirnos! Tu mensaje ha sido recibido.');
      formContacto.reset();
    });
  }
}

function openModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.add('active');
}

function closeModal(modalId) {
  const modal = document.getElementById(modalId);
  if (modal) modal.classList.remove('active');
}

// ---------------------------------------------------------
// 6. ESTADO DE SESIÓN (LOGIN / PERFIL)
// ---------------------------------------------------------
function checkSessionStatus() {
  const userJson = localStorage.getItem('usuarioActivo');
  const btnLoginHeader = document.getElementById('header-btn-login');
  const userProfileDropdown = document.getElementById('header-user-profile');
  const userNameDisplay = document.getElementById('display-user-name');
  const userRoleBadge = document.getElementById('display-user-role');
  const panelAdminLink = document.getElementById('dropdown-link-admin');

  if (userJson) {
    try {
      const user = JSON.parse(userJson);
      if (btnLoginHeader) btnLoginHeader.style.display = 'none';
      if (userProfileDropdown) userProfileDropdown.style.display = 'flex';
      if (userNameDisplay) userNameDisplay.textContent = user.usuario || 'Usuario';
      if (userRoleBadge) userRoleBadge.textContent = user.rol || 'CLIENTE';

      if (panelAdminLink) {
        panelAdminLink.style.display = (user.rol === 'ADMIN' || user.rol === 'EMPLEADO') ? 'flex' : 'none';
      }
    } catch (err) {
      console.error(err);
    }
  } else {
    if (btnLoginHeader) btnLoginHeader.style.display = 'inline-flex';
    if (userProfileDropdown) userProfileDropdown.style.display = 'none';
  }

  // Toggle dropdown del perfil
  const btnAvatar = document.getElementById('btn-toggle-profile-menu');
  const menuDropdown = document.getElementById('profile-menu-dropdown');
  if (btnAvatar && menuDropdown) {
    btnAvatar.addEventListener('click', (e) => {
      e.stopPropagation();
      menuDropdown.classList.toggle('active');
    });
    document.addEventListener('click', () => {
      menuDropdown.classList.remove('active');
    });
  }
}

// Función global para cerrar sesión
window.cerrarSesion = function () {
  localStorage.removeItem('usuarioActivo');
  window.location.reload();
};
