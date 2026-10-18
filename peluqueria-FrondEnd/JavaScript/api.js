

const API_CONFIG = {
  BASE_URL: 'http://localhost:8080',
  TIMEOUT_MS: 3000,
  ENDPOINTS: {
    // Servicios
    SERVICIOS: '/api/servicios',
    SERVICIOS_ACTIVOS: '/api/servicios/estado',
    
    // Citas
    CITAS: '/citas',
    CITAS_POR_CLIENTE: (idCliente) => `/citas/cliente/${idCliente}`,
    CITAS_POR_EMPLEADO: (idEmpleado) => `/citas/empleado/${idEmpleado}`,
    CAMBIAR_ESTADO_CITA: (id, nuevoEstado) => `/citas/${id}/estado/${nuevoEstado}`,
    
    // Usuarios y Autenticación
    USUARIOS: '/api/usuarios',
    USUARIOS_POR_NOMBRE: (nombres) => `/api/usuarios/findNombres/${nombres}`,
    
    // Roles y Días Libres
    ROLES: '/roles',
    DIAS_LIBRES: '/diaslibres'
  }
};

const UrbanLookAPI = {

  /**
   * Helper genérico para peticiones fetch con timeout y manejo de errores
   */
  async request(endpoint, options = {}) {
    const url = `${API_CONFIG.BASE_URL}${endpoint}`;
    const defaultHeaders = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    };

    const controller = new AbortController();
    const timeoutId = setTimeout(() => controller.abort(), API_CONFIG.TIMEOUT_MS);

    try {
      const response = await fetch(url, {
        ...options,
        headers: { ...defaultHeaders, ...options.headers },
        signal: controller.signal
      });

      clearTimeout(timeoutId);

      if (!response.ok) {
        throw new Error(`HTTP Error ${response.status}: ${response.statusText}`);
      }

      // Si la respuesta no tiene contenido (ej: 204 No Content)
      if (response.status === 204) return null;

      return await response.json();
    } catch (error) {
      clearTimeout(timeoutId);
      console.warn(`[UrbanLookAPI] Backend no disponible o error en ${endpoint}:`, error.message);
      throw error;
    }
  },

  /**
   * Verifica si el backend en Spring Boot está activo
   */
  async verificarConexionBackend() {
    try {
      await this.request(API_CONFIG.ENDPOINTS.SERVICIOS, { method: 'GET' });
      return true;
    } catch (err) {
      return false;
    }
  },

  // ============================================================================
  // 1. MÓDULO DE SERVICIOS (/api/servicios)
  // ============================================================================

  /**
   * Obtiene todos los servicios registrados desde la base de datos
   * @returns {Promise<Array>} Lista de servicios
   */
  async obtenerServicios() {
    try {
      return await this.request(API_CONFIG.ENDPOINTS.SERVICIOS, { method: 'GET' });
    } catch (error) {
      console.info('[UrbanLookAPI] Usando servicios locales simulados de respaldo.');
      return null; // El frontend usará el catálogo predeterminado
    }
  },

  /**
   * Registra un nuevo servicio en el backend
   * @param {Object} servicio { nombre, descripcion, precio, precioPromocion, duracionMinutos, estado }
   */
  async crearServicio(servicio) {
    return await this.request(API_CONFIG.ENDPOINTS.SERVICIOS, {
      method: 'POST',
      body: JSON.stringify(servicio)
    });
  },

  // ============================================================================
  // 2. MÓDULO DE CITAS (/citas)
  // ============================================================================

  /**
   * Obtiene todas las citas (para panel de administración)
   */
  async obtenerTodasLasCitas() {
    try {
      return await this.request(API_CONFIG.ENDPOINTS.CITAS, { method: 'GET' });
    } catch (error) {
      return null;
    }
  },

  /**
   * Obtiene las citas de un cliente específico
   */
  async obtenerCitasPorCliente(idCliente) {
    try {
      return await this.request(API_CONFIG.ENDPOINTS.CITAS_POR_CLIENTE(idCliente), { method: 'GET' });
    } catch (error) {
      return null;
    }
  },

  /**
   * Registra una nueva cita en el backend
   * @param {Object} cita { idCliente, idEmpleado, idServicio, fecha (YYYY-MM-DD), hora (HH:mm:ss), estado, observaciones }
   */
  async crearCita(cita) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS, {
      method: 'POST',
      body: JSON.stringify(cita)
    });
  },

  /**
   * Cambia el estado de una cita (PENDIENTE, EN ATENCION, COMPLETADA, CANCELADA)
   */
  async cambiarEstadoCita(idCita, nuevoEstado) {
    return await this.request(API_CONFIG.ENDPOINTS.CAMBIAR_ESTADO_CITA(idCita, nuevoEstado), {
      method: 'PUT'
    });
  },

  // ============================================================================
  // 3. MÓDULO DE USUARIOS Y AUTENTICACIÓN (/api/usuarios)
  // ============================================================================

  /**
   * Registra un nuevo usuario en la base de datos MySQL
   * @param {Object} usuario { nombre, apellido, identification, correo, user, password, id_roles }
   */
  async registrarUsuario(usuario) {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS, {
      method: 'POST',
      body: JSON.stringify(usuario)
    });
  },

  /**
   * Obtiene todos los usuarios
   */
  async obtenerUsuarios() {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS, { method: 'GET' });
  },

  /**
   * Busca usuario por nombre o username
   */
  async buscarPorNombre(nombre) {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS_POR_NOMBRE(nombre), { method: 'GET' });
  }
};

// Exportar en ventana global para uso directo en todos los scripts del front
window.UrbanLookAPI = UrbanLookAPI;
