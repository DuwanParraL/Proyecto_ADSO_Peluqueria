# Prompt de contexto completo - URBAN LOOK / Peluqueria ADSO

Usa este documento como contexto obligatorio antes de analizar, depurar, explicar o modificar este proyecto. Lee el codigo real antes de asumir comportamientos. Distingue entre funcionalidad implementada, funcionalidad simulada, archivos vacios, datos estaticos y codigo no conectado.

## Instruccion principal

Actua como ingeniero senior responsable del repositorio. Antes de realizar cambios:

1. Identifica el archivo exacto que controla el comportamiento.
2. Determina si el problema pertenece al frontend, al servidor Node, al backend Spring Boot, a JPA, a MySQL o a varias capas.
3. Respeta los nombres actuales de archivos, clases, variables, rutas y endpoints.
4. No inventes credenciales, permisos, relaciones, validaciones ni funcionalidades.
5. Conserva el codigo no relacionado con la tarea.
6. Comprueba los contratos entre frontend y backend.
7. Ejecuta la validacion mas cercana disponible despues de modificar.
8. Informa los archivos afectados, la validacion y las limitaciones pendientes.

No confundas una pantalla visual con una funcionalidad conectada al backend.

## Ubicacion

Raiz del proyecto:

`c:\Users\Usuario\Desktop\peluqueria adso`

El nombre `peluqueria-FrondEnd` esta escrito asi en el repositorio. Debe conservarse exactamente en las rutas.

## Arbol del proyecto

```text
peluqueria adso/
├── .gitignore
├── .vscode/settings.json
├── index.html
├── server.js
├── docs/
│   ├── PROMPT_MAESTRO_CONTEXTUALIZADO.md
│   └── PROMPT_CONTEXTO_COMPLETO.md
├── peluqueria-FrondEnd/
│   ├── index.html
│   ├── login.html
│   ├── registro.html
│   ├── admin/
│   │   ├── citas.html
│   │   ├── dias_libres.html
│   │   └── empleados.html
│   ├── cliente/
│   │   ├── agendar.html
│   │   └── catalogo.html
│   ├── css/estilos.css
│   ├── JavaScript/
│   │   ├── api.js
│   │   ├── auth.js
│   │   ├── cliente.js
│   │   ├── admin.js
│   │   └── main.js
│   ├── img/
│   └── Librerias/
│       ├── bootstrap/css/bootstrap.min.css
│       ├── bootstrap/js/bootstrap.bundle.min.js
│       └── jquery/jquery-4.0.0.min.js
└── peluqueria-backend/
    ├── pom.xml
    ├── mvnw
    ├── mvnw.cmd
    ├── .mvn/wrapper/maven-wrapper.properties
    ├── src/main/resources/application.properties
    ├── src/main/java/com/sena/adso/peluqueria/adso/
    │   ├── PeluqueriaAdsoApplication.java
    │   ├── ServletInitializer.java
    │   ├── configuracion/corsConfig.java
    │   ├── controladores/
    │   │   ├── AuthController.java
    │   │   ├── CitasController.java
    │   │   ├── DiasLibresController.java
    │   │   ├── EmpleadosController.java
    │   │   ├── RolesController.java
    │   │   ├── ServiciosController.java
    │   │   └── usuariosController.java
    │   ├── entidades/
    │   │   ├── CitasEntity.java
    │   │   ├── DiasLibresEntity.java
    │   │   ├── EmpleadosEntity.java
    │   │   ├── RolesEntity.java
    │   │   ├── ServiciosEntity.java
    │   │   └── UsuariosEntity.java
    │   ├── repositorios/
    │   │   ├── CitasRepository.java
    │   │   ├── DiasLibreRepository.java
    │   │   ├── EmpleadosRepository.java
    │   │   ├── RolesRepository.java
    │   │   ├── ServiciosRepository.java
    │   │   └── UsuariosRepository.java
    │   └── servicios/
    │       ├── CitasServices.java
    │       ├── DiasLibresServices.java
    │       ├── EmpleadosServices.java
    │       ├── RolesServices.java
    │       ├── ServiciosServices.java
    │       ├── UsuariosServices.java
    │       └── usuariosEntity.java
    └── src/test/java/com/sena/adso/peluqueria/adso/
        └── PeluqueriaAdsoApplicationTests.java
```

`peluqueria-backend/target/` es salida generada de Maven y no es fuente de verdad.

## Arquitectura y arranque

El frontend es HTML, CSS y JavaScript. `server.js` solo sirve archivos estaticos. El backend es una API REST con Spring Boot, Spring Data JPA y MySQL.

Flujo:

```text
Navegador
  -> http://localhost:3000
  -> server.js
  -> peluqueria-FrondEnd
  -> JavaScript/api.js
  -> http://localhost:8080
  -> controladores Spring
  -> servicios Java
  -> repositorios JPA
  -> MySQL: peluqueria_adso
```

Frontend:

```powershell
node server.js
```

- Puerto: `3000`.
- Escucha en `0.0.0.0`.
- Sirve `peluqueria-FrondEnd`.
- No procesa API, sesiones ni base de datos.

Backend, desde `peluqueria-backend`:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
.\mvnw.cmd clean package
```

- Puerto predeterminado: `8080`.
- Java: `21`.
- Empaquetado: `war`.

## server.js

Archivo: `server.js`

Variables:

- `http`
- `fs`
- `path`
- `PORT = 3000`
- `BASE_DIR`
- `MIME_TYPES`
- `server`
- `req`
- `res`
- `reqPath`
- `filePath`
- `err`
- `stats`
- `ext`
- `contentType`
- `os`
- `nets`
- `name`
- `net`
- `ip`

Funcion:

- `getLocalIp()` busca la primera interfaz IPv4 no interna.

Comportamiento:

- `/` se convierte en `/index.html`.
- Se sirven HTML, CSS, JS, JSON, PNG, JPG, JPEG, GIF, SVG e ICO.
- Los archivos inexistentes devuelven `404 Not Found`.
- La raiz `/index.html` redirige a `./peluqueria-FrondEnd/index.html` mediante `meta refresh` y `window.location.replace`.

## Frontend

### peluqueria-FrondEnd/index.html

Pagina principal de URBAN LOOK.

Incluye navegacion, login, registro, profesionales, servicios, historia, resenas, contacto, formulario rapido de citas, modales y carrito visual.

Profesionales estaticos:

- Andres Morales.
- Mateo Restrepo.
- Sebastian Castro.

Categorias de servicios:

- `all`
- `cabello`
- `barba`
- `cejas`
- `faciales`
- `promos`

Funciones inline:

- `seleccionarBarberoYAgendar(nombreBarbero)`
- `procesarAgendamiento()`

Carga `api.js`, `auth.js`, `main.js`, Bootstrap y jQuery. El catalogo principal es HTML estatico; no se alimenta directamente de `GET /api/servicios`.

### login.html

Login independiente. Contiene usuario, password y seleccion visual de rol:

- `CLIENTE`
- `EMPLEADO`
- `ADMIN`

No valida contra Spring Boot. Guarda el usuario en `localStorage` mediante `auth.js`.

### registro.html

Registro visual independiente con nombre, apellido, tipo y numero de documento, correo, celular, password y confirmacion.

Actualmente muestra un `alert` y redirige a `login.html`. No llama al backend ni persiste datos.

### cliente/catalogo.html

Catalogo estatico de tres servicios. Usa enlaces `agendar.html?servicio=1`, `2` y `3`. No consume `/api/servicios`.

### cliente/agendar.html

Formulario de barbero, servicio, fecha, hora y notas.

Funcion principal:

- `confirmarCitaCliente()`

Objeto enviado:

- `idCliente`
- `idEmpleado`
- `idServicio`
- `fecha`
- `hora`
- `estado`
- `observaciones`

Mapeo actual:

- Andres: empleado `1`.
- Mateo: empleado `2`.
- Otra opcion: empleado `3`.
- Precio `29.750`: servicio `1`.
- Otro servicio: servicio `2`.
- AM: `09:00:00`.
- PM: `15:00:00`.
- Estado: `PENDIENTE`.

Usa `UrbanLookAPI.crearCita()`. El cliente queda fijo en `1`, no se resuelve desde una sesion real y no se comprueba disponibilidad desde el formulario.

### admin/citas.html

Panel visual de citas.

Endpoints:

- `GET /citas`
- `PUT /citas/{id}/estado/{estado}`

Estados usados:

- `COMPLETADA`
- `CANCELADA`

Tiene citas precargadas y fallback visual. No valida autenticacion ni rol. La ruta CSS `./css/estilos.css` es probablemente incorrecta desde la carpeta `admin`; la ruta esperada seria `../css/estilos.css`.

### Archivos vacios

- `admin/dias_libres.html`
- `admin/empleados.html`
- `JavaScript/cliente.js`
- `JavaScript/admin.js`
- `servicios/usuariosEntity.java` en el backend no tiene uso funcional conocido.

## JavaScript

### JavaScript/api.js

Configuracion:

- `API_CONFIG.BASE_URL = 'http://localhost:8080'`
- `API_CONFIG.TIMEOUT_MS = 3000`
- `API_CONFIG.ENDPOINTS`

Endpoints configurados:

- `/api/servicios`
- `/api/servicios/estado`
- `/citas`
- `/citas/cliente/{id}`
- `/citas/empleado/{id}`
- `/citas/{id}/estado/{estado}`
- `/api/usuarios`
- `/api/usuarios/findNombres/{nombres}`
- `/roles`
- `/diaslibres`

Objeto global: `window.UrbanLookAPI`.

Metodos:

- `request(endpoint, options)`
- `verificarConexionBackend()`
- `obtenerServicios()`
- `crearServicio(servicio)`
- `obtenerTodasLasCitas()`
- `obtenerCitasPorCliente(idCliente)`
- `crearCita(cita)`
- `cambiarEstadoCita(idCita, nuevoEstado)`
- `registrarUsuario(usuario)`
- `obtenerUsuarios()`
- `buscarPorNombre(nombre)`

`request` combina la URL base, agrega JSON headers, usa `fetch`, `AbortController` y timeout de tres segundos, lanza errores HTTP, devuelve `null` para `204` y convierte las demas respuestas a JSON.

### JavaScript/auth.js

Funciones:

- `procesarInicioSesion(usuario, rol, origen)`
- `cerrarSesion()`

Clave de almacenamiento:

- `localStorage.usuarioActivo`

Estructura:

```json
{
  "usuario": "...",
  "rol": "CLIENTE|EMPLEADO|ADMIN",
  "fechaLogin": "..."
}
```

No hay JWT, cookies, token, password real ni autorizacion backend.

### JavaScript/main.js

Funciones:

- `initNavigation()`
- `initServicesFilter()`
- `initServiceSelection()`
- `initSubtabs()`
- `initModals()`
- `openModal(modalId)`
- `closeModal(modalId)`
- `checkSessionStatus()`
- `checkBackendStatus()`

Variables y elementos relevantes:

- `selectedServices`
- `usuarioActivo`
- `backend-status-pill`
- `formLoginModal`
- `formLogin`
- `regPassword`
- `regPasswordVerify`
- `loginModal`
- `registerModal`

El registro modal usa `POST /api/usuarios` y envia `nombre`, `apellido`, `identification`, `correo`, `user`, `password` e `id_roles: 1`. Lee celular y tipo de documento, pero no los envia. No valida correctamente la confirmacion de password.

## CSS y librerias

`css/estilos.css` define tema oscuro premium con acentos dorados, variables CSS, tipografias, layout responsive, header, hero, tarjetas, servicios, carrito, formularios, modales, perfil y footer.

Bootstrap y jQuery estan almacenados localmente en `Librerias/`. No asumir que jQuery es necesario, porque el codigo propio usa principalmente JavaScript nativo y `fetch`.

Las imagenes de `img/` son recursos binarios visuales. No son codigo ni deben modificarse salvo solicitud expresa.

## Backend Java

Paquete raiz:

`com.sena.adso.peluqueria.adso`

### Entidades

`CitasEntity` -> tabla `citas`:

- `id: Integer`
- `idCliente: Integer`
- `idEmpleado: Integer`
- `idServicio: Integer`
- `fecha: LocalDate`
- `hora: LocalTime`
- `estado: String`, inicial `PENDIENTE`
- `observaciones: String`

`DiasLibresEntity` -> tabla `dias_libres`:

- `id: Integer`
- `idEmpleado: Integer`
- `fecha: LocalDate`
- `motivo: String`
- `estado: String`, inicial `APROBADO`

`EmpleadosEntity` -> tabla `empleados`:

- `id: Integer`
- `idUsuario: Integer`
- `especialidades: String`
- `telefono: String`
- `horaEntrada: LocalTime`, inicial `08:00`
- `horaSalida: LocalTime`, inicial `17:00`
- `activo: Boolean`, inicial `true`

Importante: el campo real es `activo`, no `estado`.

`RolesEntity` -> tabla `roles`:

- `id: Integer`
- `nombre: String`, obligatorio y unico
- `descripcion: String`

`ServiciosEntity` -> tabla `servicios`:

- `id: Integer`
- `nombre: String`
- `descripcion: String`
- `precio: Double`
- `precioPromocion: Double`
- `duracionMinutos: Integer`
- `estado: Boolean`, inicial `true`

`UsuariosEntity` -> tabla `usuarios`:

- `id: Integer`
- `nombre: String`
- `apellido: String`
- `identification: String`
- `correo: String`
- `user: String`
- `password: String`
- `id_roles: Integer`

La password tiene acceso JSON de escritura solamente, pero se almacena sin hashing.

No existen relaciones JPA `@ManyToOne` ni `@OneToMany`. Las relaciones son solo IDs enteros:

```text
Usuarios.id_roles -> Roles.id
Empleados.idUsuario -> Usuarios.id
Citas.idCliente -> Usuarios.id
Citas.idEmpleado -> Empleados.id
Citas.idServicio -> Servicios.id
DiasLibres.idEmpleado -> Empleados.id
```

### Repositorios

`CitasRepository`:

- `findByIdCliente(idCliente)`
- `findByIdEmpleado(idEmpleado)`
- `findByEmpleadoAndFecha(idEmpleado, fecha)`
- `existeTurnoOcupado(idEmpleado, fecha, hora)`
- `contarPendientesPorEmpleado(idEmpleado)`
- `contarTodosLosPendientes()`
- `findByEstado(estado)`

`DiasLibreRepository`:

- `FindByIdEmpleado(idEmpleado)`
- `tienenDiasAprobados(idEmpleado, fecha)`

`EmpleadosRepository`:

- `findByActivoTrue()`
- `findByUsuario(idUsuario)`

`RolesRepository`: operaciones heredadas de `JpaRepository`.

`ServiciosRepository`:

- `findByEstadoTrue()`

`UsuariosRepository`:

- `findByNombreLike(nombre)`
- `findByUserOrCorreo(credencial)`
- `findByIdRoles(id_roles)`

Riesgo: revisar las consultas JPQL que usen `c.id_empleado`; JPQL debe utilizar propiedades Java como `idEmpleado`.

### Servicios

`CitasServices`:

- CRUD de citas.
- Consultas por cliente, empleado y fecha.
- Conteo de pendientes.
- `validarDisponibilidadPeluquero(entity)`.
- `CambiarEstado(id, nuevoEstado)`.

La validacion revisa dia libre aprobado, empleado activo, horario laboral y citas ocupadas no canceladas. No restringe completamente los estados posibles.

`DiasLibresServices`: CRUD completo.

`EmpleadosServices`:

- CRUD.
- `getByUsuarios(idUsuario)`.
- `getActivos()`.

`RolesServices`: CRUD completo.

`ServiciosServices`:

- CRUD.
- `getActive()`.

`UsuariosServices`:

- `getdAll()`.
- `getById(id)`.
- `save(entity)`.
- `update(entity)`.
- `delete(id)`.
- `getByNombres(nombres)`.

## API REST

`AuthController`:

- Base: `/api/auth`
- `POST /api/auth/login`

Recibe:

```json
{
  "usuario": "correo_o_usuario",
  "password": "contraseña"
}
```

Devuelve datos de usuario, rol y, si existe, datos del empleado asociado. El frontend actual no usa este endpoint.

`CitasController`:

- `GET /citas`
- `GET /citas/{id}`
- `GET /citas/cliente/{idCliente}`
- `GET /citas/empleado/{idEmpleado}`
- `GET /citas/empleado/{idEmpleado}/fecha/{fecha}`
- `GET /citas/pendientes/contador`
- `GET /citas/pendientes/contador?idEmpleado={idEmpleado}`
- `POST /citas`
- `PUT /citas/{id}`
- `PUT /citas/{id}/estado/{nuevoEstado}`
- `DELETE /citas/{id}`

`DiasLibresController`:

- `GET /diaslibres`
- `GET /diaslibres/{id}`
- `POST /diaslibres`
- `PUT /diaslibres/{id}`
- `DELETE /diaslibres/{id}`

`EmpleadosController`:

- `GET /api/empleados`
- `GET /api/empleados/activos`
- `GET /api/empleados/{id}`
- `GET /api/empleados/usuario/{idUsuario}`
- `POST /api/empleados`
- `PUT /api/empleados`
- `DELETE /api/empleados/{id}`

Problemas actuales: usa `@Controller`, el POST no declara `@RequestBody` y el PUT espera `@PathVariable` aunque la ruta no contiene `/{id}`.

`RolesController`:

- `GET /roles`
- `GET /roles/{id}`
- `POST /roles`
- `PUT /roles/{id}`
- `DELETE /roles/{id}`

`ServiciosController`:

- `GET /api/servicios`
- `GET /api/servicios/estado`
- `GET /api/servicios/{id}`
- `POST /api/servicios`
- `PUT /api/servicios/{id}`
- `DELETE /api/servicios/{id}`

Problema actual: el DELETE comprueba la existencia despues de intentar eliminar.

`usuariosController`:

- `GET /api/usuarios`
- `GET /api/usuarios/{id}`
- `POST /api/usuarios`
- `PUT /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`
- `GET /api/usuarios/findNombres/{nombres}`

Problema actual: el PUT declara `{id}` pero usa el ID del cuerpo.

## Configuracion

Archivo: `peluqueria-backend/src/main/resources/application.properties`

```properties
spring.application.name=peluqueria-adso
spring.datasource.url=jdbc:mysql://localhost:3306/peluqueria_adso?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

Archivo `pom.xml`:

- Spring Boot `4.1.1`.
- Java `21`.
- Spring Actuator.
- Spring Data JPA.
- Spring Web MVC.
- DevTools.
- MySQL Connector/J.
- Tomcat provided.
- Dependencias de prueba JPA y Web MVC.

## Prueba existente

Archivo: `PeluqueriaAdsoApplicationTests.java`

Solo contiene una prueba `contextLoads()` con `@SpringBootTest`. No cubre endpoints, repositorios, autenticacion, seguridad, disponibilidad, frontend ni persistencia funcional.

## Riesgos y limitaciones conocidas

- Login frontend simulado y desconectado de `/api/auth/login`.
- El usuario puede escoger libremente `ADMIN`.
- No hay autorizacion real de endpoints.
- Passwords sin hashing.
- No se validan completamente IDs relacionados.
- `idCliente` fijo en el frontend.
- Conversion incompleta de servicios y horarios.
- El carrito no envia necesariamente todos los servicios seleccionados.
- Catalogos estaticos duplicados.
- Registro modal e independiente tienen comportamientos diferentes.
- Fallback visual no persiste citas.
- Panel administrativo sin proteccion real.
- Pantallas de empleados y dias libres vacias.
- Scripts `cliente.js` y `admin.js` vacios.
- CORS permite cualquier origen y cualquier header.
- `ddl-auto=update` modifica automaticamente el esquema.
- Algunas consultas JPQL o firmas de controladores requieren revision.
- Las librerias minificadas y las imagenes son recursos externos/binarios, no logica propia.

## Regla final

Cuando recibas una nueva tarea sobre este proyecto, responde y trabaja con este orden:

1. Diagnostico del flujo real.
2. Archivo o archivos responsables.
3. Variables, clases, funciones o endpoints relacionados.
4. Cambio minimo necesario.
5. Validacion ejecutada.
6. Riesgos o limitaciones restantes.

Mantén separadas las capas: `server.js` sirve archivos, el frontend consume HTTP, los controladores exponen rutas, los servicios contienen logica, los repositorios acceden a JPA y las entidades representan tablas.
