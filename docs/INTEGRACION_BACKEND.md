# Guía de Conexión e Integración Frontend - Backend
**Proyecto Formativo ADSO - Peluquería / Barbería "URBAN LOOK"**

Este documento detalla la arquitectura de integración entre el cliente web (**Frontend**) y el servidor Spring Boot (**Backend**), los endpoints activos, el formato JSON de los datos y las instrucciones para el desarrollador del Backend.

---

## 1. Arquitectura y Puertos de Desarrollo

| Componente | Tecnología | Puerto por defecto | URL Base |
| :--- | :--- | :--- | :--- |
| **Frontend** | HTML5, CSS3, JS (Vanilla) | `3000` (Node.js) | `http://localhost:3000` |
| **Backend** | Java 21, Spring Boot 4.x / 3.x | `8080` (Tomcat) | `http://localhost:8080` |
| **Base de Datos** | MySQL | `3306` | `jdbc:mysql://localhost:3306/peluqueria_adso` |

> **Nota sobre CORS:** La clase `corsConfig.java` ya tiene habilitado `allowedOrigins("*")`, permitiendo peticiones desde `http://localhost:3000` o desde `file:///`.

---

## 2. Ajustes Realizados en el Backend (Importante)

Se realizaron dos correcciones clave en el código Java del backend para garantizar el correcto intercambio de datos:

1. **`CitasEntity.java`**:
   * El atributo `fecha` estaba definido como `LocalTime fecha;` (formato de hora). Se corrigió a `LocalDate fecha;` con sus respectivos getters y setters para admitir fechas en formato estándar ISO (`YYYY-MM-DD`).
2. **`CitasController.java`**:
   * En los métodos `update` y `updateEstado`: la condición `if (actualizado != null)` retornaba `404 Not Found`. Se corrigió a `if (actualizado == null)` para que retorne `200 OK` con el objeto actualizado.
   * En `@PutMapping("/{id}/estado/{nuevoEstado}")`, se corrigió el parámetro de `@RequestBody` a `@PathVariable String nuevoEstado`.

---

## 3. Catálogo de Endpoints Conectados

El archivo encargado de centralizar todas las llamadas en el Frontend es:
📂 **`peluqueria-FrondEnd/JavaScript/api.js`**

### A. Módulo de Usuarios (`/api/usuarios`)

#### 1. Registrar Nuevo Usuario
* **Método:** `POST`
* **Ruta:** `/api/usuarios`
* **Llamado desde el Frontend:** Modal de Registro (`formRegistro` en `index.html`).
* **Payload JSON enviado por el Front:**
  ```json
  {
    "nombre": "Carlos",
    "apellido": "Gómez",
    "identification": "1098765432",
    "correo": "carlos@correo.com",
    "user": "carlos",
    "password": "Password123*",
    "id_roles": 1
  }
  ```
* **Respuesta esperada:** `200 OK` o `201 Created` con la entidad creada incluyendo su `id`.

#### 2. Listar Usuarios
* **Método:** `GET`
* **Ruta:** `/api/usuarios`
* **Respuesta esperada:** Array de usuarios.

---

### B. Módulo de Citas (`/citas`)

#### 1. Crear / Agendar Cita
* **Método:** `POST`
* **Ruta:** `/citas`
* **Llamado desde el Frontend:**
  * Formulario rápido en `index.html` (`procesarAgendamiento()`).
  * Formulario de cliente en `cliente/agendar.html` (`confirmarCitaCliente()`).
* **Payload JSON enviado por el Front:**
  ```json
  {
    "idCliente": 1,
    "idEmpleado": 1,
    "idServicio": 1,
    "fecha": "2026-10-20",
    "hora": "09:00:00",
    "estado": "PENDIENTE",
    "observaciones": "Cliente: Carlos Gómez - Tel: 318 456 7890"
  }
  ```
* **Respuesta esperada:** `200 OK` con el objeto `CitasEntity` guardado.

#### 2. Listar Todas las Citas
* **Método:** `GET`
* **Ruta:** `/citas`
* **Llamado desde el Frontend:** `admin/citas.html` al cargar la tabla de turnos.
* **Respuesta esperada:**
  ```json
  [
    {
      "id": 1,
      "idCliente": 1,
      "idEmpleado": 1,
      "idServicio": 1,
      "fecha": "2026-10-20",
      "hora": "09:00:00",
      "estado": "PENDIENTE",
      "observaciones": "Corte Clásico"
    }
  ]
  ```

#### 3. Cambiar Estado de Cita
* **Método:** `PUT`
* **Ruta:** `/citas/{id}/estado/{nuevoEstado}`
* **Llamado desde el Frontend:** Botones **Completar** (`COMPLETADA`) y **Cancelar** (`CANCELADA`) en `admin/citas.html`.

---

### C. Módulo de Servicios (`/api/servicios`)

#### 1. Listar Servicios
* **Método:** `GET`
* **Ruta:** `/api/servicios`
* **Llamado desde el Frontend:** `UrbanLookAPI.obtenerServicios()`.
* **Respuesta esperada:**
  ```json
  [
    {
      "id": 1,
      "nombre": "Corte Clásico + Barba",
      "descripcion": "Perfilado con navaja y toalla caliente",
      "precio": 35000.0,
      "precioPromocion": 29750.0,
      "duracionMinutos": 45,
      "estado": true
    }
  ]
  ```

---

## 4. Script SQL para la Base de Datos (`peluqueria_adso`)

Si el desarrollador del Backend necesita crear la base de datos con datos de prueba iniciales:

```sql
CREATE DATABASE IF NOT EXISTS peluqueria_adso CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE peluqueria_adso;

-- Tabla Roles
CREATE TABLE IF NOT EXISTS roles (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL
);

INSERT INTO roles (id, nombre) VALUES 
(1, 'CLIENTE'), 
(2, 'EMPLEADO'), 
(3, 'ADMIN');

-- Tabla Servicios
CREATE TABLE IF NOT EXISTS servicios (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion VARCHAR(200),
  precio DOUBLE NOT NULL,
  precioPromocion DOUBLE NOT NULL,
  duracionMinutos INT NOT NULL,
  estado BOOLEAN DEFAULT TRUE
);

INSERT INTO servicios (id, nombre, descripcion, precio, precioPromocion, duracionMinutos, estado) VALUES
(1, 'Corte Clásico + Barba', 'Corte con tijera o máquina, toalla caliente y navaja', 35000, 29750, 45, true),
(2, 'Fade / Degradado', 'Transición milimétrica moderna a máquina y shaver', 22000, 22000, 40, true),
(3, 'Perfilado de Barba Spa', 'Alineado con navaja y bálsamo hidratante', 18000, 18000, 30, true),
(4, 'Facial Vapor de Ozono', 'Apertura de poros y mascarilla purificante', 35000, 35000, 35, true);
```

---

## 5. Modo de Tolerancia a Fallos (Fallback)

Para que el proyecto **siempre funcione durante exposiciones o revisiones**, el frontend cuenta con detección automática:
* Si Spring Boot está **encendido** (`http://localhost:8080`), la cabecera muestra `Backend: Online (8080)` y los datos van directo a MySQL.
* Si Spring Boot está **apagado** o MySQL no está corriendo, la cabecera muestra `Backend: Offline (Modo Local)` y la interfaz continúa funcionando en modo simulación interactiva sin generar errores ni pantallas en blanco.
