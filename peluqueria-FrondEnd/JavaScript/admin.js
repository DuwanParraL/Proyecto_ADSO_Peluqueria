/**
 * URBAN LOOK - Módulo de Administración
 * Control de acceso y utilidades para las vistas del panel admin
 */

const AdminModule = {
  /**
   * Verifica si el usuario activo tiene permiso para ver el panel de administración
   */
  verificarPermisosAdmin() {
    const sesion = window.obtenerUsuarioSesion ? window.obtenerUsuarioSesion() : null;
    if (!sesion) {
      alert('Debes iniciar sesión con una cuenta autorizada para acceder al panel de administración.');
      window.location.href = '../login.html';
      return false;
    }

    const rol = (sesion.rol || '').toUpperCase();
    if (rol !== 'ADMIN' && rol !== 'EMPLEADO') {
      alert('Acceso denegado: tu cuenta no posee permisos administrativos.');
      window.location.href = '../index.html';
      return false;
    }

    return true;
  },

  /**
   * Obtiene la clase CSS de Bootstrap para un estado de cita
   */
  obtenerBadgeEstado(estado) {
    switch ((estado || '').toUpperCase()) {
      case 'COMPLETADA': return 'bg-success';
      case 'EN ATENCION': return 'bg-info text-dark';
      case 'CANCELADA': return 'bg-danger';
      case 'PENDIENTE':
      default:
        return 'bg-warning text-dark';
    }
  }
};

window.AdminModule = AdminModule;

