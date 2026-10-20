/**
 * URBAN LOOK - Módulo de Funciones del Cliente
 */

const ClienteModule = {
  /**
   * Formatea un valor numérico a moneda colombiana (COP)
   */
  formatoCOP(valor) {
    return '$' + Number(valor || 0).toLocaleString('es-CO');
  },

  /**
   * Verifica si el usuario actual tiene rol de cliente o está logueado
   */
  verificarAccesoCliente() {
    const sesion = window.obtenerUsuarioSesion ? window.obtenerUsuarioSesion() : null;
    return sesion;
  }
};

window.ClienteModule = ClienteModule;

