/**
 * URBAN LOOK - Cliente API REST para Backend Spring Boot
 * Conecta el Front-End directamente con MySQL a través de Spring Boot en el puerto 8080.
 */

const API_CONFIG = {
  BASE_URL: 'http://localhost:8080',
  TIMEOUT_MS: 10000,
  ENDPOINTS: {
    // Autenticación
    LOGIN: '/api/auth/login',

    // Servicios
    SERVICIOS: '/api/servicios',
    SERVICIOS_ACTIVOS: '/api/servicios/estado',

    // Citas
    CITAS: '/api/citas',
    CITAS_POR_CLIENTE: (idCliente) => `/api/citas/cliente/${idCliente}`,
    CITAS_POR_EMPLEADO: (idEmpleado) => `/api/citas/empleado/${idEmpleado}`,
    CITAS_POR_EMPLEADO_FECHA: (idEmpleado, fecha) => `/api/citas/empleado/${idEmpleado}/fecha/${fecha}`,
    CAMBIAR_ESTADO_CITA: (id, nuevoEstado) => `/api/citas/${id}/estado/${nuevoEstado}`,
    CITAS_PENDIENTES_CONTADOR: (idEmpleado) => idEmpleado ? `/api/citas/pendientes/contador?idEmpleado=${idEmpleado}` : '/api/citas/pendientes/contador',

    // Usuarios
    USUARIOS: '/api/usuarios',
    USUARIOS_POR_NOMBRE: (nombres) => `/api/usuarios/findNombres/${nombres}`,

    // Empleados
    EMPLEADOS: '/api/empleados',
    EMPLEADOS_ACTIVOS: '/api/empleados/activos',
    EMPLEADOS_POR_USUARIO: (idUsuario) => `/api/empleados/usuario/${idUsuario}`,

    // Roles y Días Libres
    ROLES: '/api/roles',
    DIAS_LIBRES: '/api/diaslibres'
  }
};

const UrbanLookAPI = {
  /**
   * Helper genérico para peticiones HTTP
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

      // Si la respuesta no tiene contenido (204 No Content)
      if (response.status === 204) return null;

      const data = await response.json().catch(() => null);

      if (!response.ok) {
        const errorMsg = (data && data.mensaje) || (data && data.error) || `Error ${response.status}: ${response.statusText}`;
        const error = new Error(errorMsg);
        error.status = response.status;
        error.data = data;
        throw error;
      }

      return data;
    } catch (error) {
      clearTimeout(timeoutId);
      if (error.name === 'AbortError') {
        throw new Error('Tiempo de espera agotado al conectar con el servidor.');
      }
      throw error;
    }
  },

  /**
   * Verifica el estado de conexión con el backend
   */
  async verificarConexionBackend() {
    try {
      await this.request(API_CONFIG.ENDPOINTS.SERVICIOS_ACTIVOS, { method: 'GET' });
      return true;
    } catch (err) {
      return false;
    }
  },

  // ============================================================================
  // 1. MÓDULO DE AUTENTICACIÓN (/api/auth)
  // ============================================================================
  /**
   * Inicia sesión contra el backend con usuario/correo y contraseña
   * @param {string} usuario Nombre de usuario o correo electrónico
   * @param {string} password Contraseña
   * @returns {Promise<Object>} { idUsuario, usuario, nombreCompleto, idRol, rol, idEmpleado? }
   */
  async login(usuario, password) {
    return await this.request(API_CONFIG.ENDPOINTS.LOGIN, {
      method: 'POST',
      body: JSON.stringify({ usuario, password })
    });
  },

  // ============================================================================
  // 2. MÓDULO DE USUARIOS (/api/usuarios)
  // ============================================================================
  async registrarUsuario(usuario) {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS, {
      method: 'POST',
      body: JSON.stringify(usuario)
    });
  },

  async obtenerUsuarios() {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS, { method: 'GET' });
  },

  async obtenerUsuarioPorId(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.USUARIOS}/${id}`, { method: 'GET' });
  },

  async actualizarUsuario(id, usuario) {
    return await this.request(`${API_CONFIG.ENDPOINTS.USUARIOS}/${id}`, {
      method: 'PUT',
      body: JSON.stringify(usuario)
    });
  },

  async eliminarUsuario(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.USUARIOS}/${id}`, { method: 'DELETE' });
  },

  async buscarUsuariosPorNombre(nombres) {
    return await this.request(API_CONFIG.ENDPOINTS.USUARIOS_POR_NOMBRE(nombres), { method: 'GET' });
  },

  // ============================================================================
  // 3. MÓDULO DE SERVICIOS (/api/servicios)
  // ============================================================================
  async obtenerServicios() {
    return await this.request(API_CONFIG.ENDPOINTS.SERVICIOS, { method: 'GET' });
  },

  async obtenerServiciosActivos() {
    return await this.request(API_CONFIG.ENDPOINTS.SERVICIOS_ACTIVOS, { method: 'GET' });
  },

  async obtenerServicioPorId(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.SERVICIOS}/${id}`, { method: 'GET' });
  },

  async crearServicio(servicio) {
    return await this.request(API_CONFIG.ENDPOINTS.SERVICIOS, {
      method: 'POST',
      body: JSON.stringify(servicio)
    });
  },

  async actualizarServicio(id, servicio) {
    return await this.request(`${API_CONFIG.ENDPOINTS.SERVICIOS}/${id}`, {
      method: 'PUT',
      body: JSON.stringify(servicio)
    });
  },

  async eliminarServicio(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.SERVICIOS}/${id}`, { method: 'DELETE' });
  },

  // ============================================================================
  // 4. MÓDULO DE CITAS (/api/citas)
  // ============================================================================
  async obtenerTodasLasCitas() {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS, { method: 'GET' });
  },

  async obtenerCitasPorCliente(idCliente) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS_POR_CLIENTE(idCliente), { method: 'GET' });
  },

  async obtenerCitasPorEmpleado(idEmpleado) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS_POR_EMPLEADO(idEmpleado), { method: 'GET' });
  },

  async obtenerAgendaEmpleadoPorFecha(idEmpleado, fecha) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS_POR_EMPLEADO_FECHA(idEmpleado, fecha), { method: 'GET' });
  },

  async contarCitasPendientes(idEmpleado = null) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS_PENDIENTES_CONTADOR(idEmpleado), { method: 'GET' });
  },

  async crearCita(cita) {
    return await this.request(API_CONFIG.ENDPOINTS.CITAS, {
      method: 'POST',
      body: JSON.stringify(cita)
    });
  },

  async cambiarEstadoCita(idCita, nuevoEstado) {
    return await this.request(API_CONFIG.ENDPOINTS.CAMBIAR_ESTADO_CITA(idCita, nuevoEstado), {
      method: 'PUT'
    });
  },

  async eliminarCita(idCita) {
    return await this.request(`${API_CONFIG.ENDPOINTS.CITAS}/${idCita}`, { method: 'DELETE' });
  },

  // ============================================================================
  // 5. MÓDULO DE EMPLEADOS (/api/empleados)
  // ============================================================================
  async obtenerEmpleados() {
    return await this.request(API_CONFIG.ENDPOINTS.EMPLEADOS, { method: 'GET' });
  },

  async obtenerEmpleadosActivos() {
    return await this.request(API_CONFIG.ENDPOINTS.EMPLEADOS_ACTIVOS, { method: 'GET' });
  },

  async obtenerEmpleadoPorId(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.EMPLEADOS}/${id}`, { method: 'GET' });
  },

  async obtenerEmpleadoPorUsuario(idUsuario) {
    return await this.request(API_CONFIG.ENDPOINTS.EMPLEADOS_POR_USUARIO(idUsuario), { method: 'GET' });
  },

  async crearEmpleado(empleado) {
    return await this.request(API_CONFIG.ENDPOINTS.EMPLEADOS, {
      method: 'POST',
      body: JSON.stringify(empleado)
    });
  },

  async actualizarEmpleado(id, empleado) {
    return await this.request(`${API_CONFIG.ENDPOINTS.EMPLEADOS}/${id}`, {
      method: 'PUT',
      body: JSON.stringify(empleado)
    });
  },

  async eliminarEmpleado(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.EMPLEADOS}/${id}`, { method: 'DELETE' });
  },

  // ============================================================================
  // 6. MÓDULO DE DÍAS LIBRES (/api/diaslibres)
  // ============================================================================
  async obtenerDiasLibres() {
    return await this.request(API_CONFIG.ENDPOINTS.DIAS_LIBRES, { method: 'GET' });
  },

  async crearDiaLibre(diaLibre) {
    return await this.request(API_CONFIG.ENDPOINTS.DIAS_LIBRES, {
      method: 'POST',
      body: JSON.stringify(diaLibre)
    });
  },

  async actualizarDiaLibre(id, diaLibre) {
    return await this.request(`${API_CONFIG.ENDPOINTS.DIAS_LIBRES}/${id}`, {
      method: 'PUT',
      body: JSON.stringify(diaLibre)
    });
  },

  async eliminarDiaLibre(id) {
    return await this.request(`${API_CONFIG.ENDPOINTS.DIAS_LIBRES}/${id}`, { method: 'DELETE' });
  },

  // ============================================================================
  // 7. MÓDULO DE ROLES (/api/roles)
  // ============================================================================
  async obtenerRoles() {
    return await this.request(API_CONFIG.ENDPOINTS.ROLES, { method: 'GET' });
  }
};

window.UrbanLookAPI = UrbanLookAPI;
