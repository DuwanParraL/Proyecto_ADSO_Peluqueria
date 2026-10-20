# Contexto completo del proyecto — Urban Look / Peluquería ADSO

Usa este documento como contexto obligatorio antes de analizar, explicar, depurar o modificar el proyecto. Lee siempre el código actual antes de asumir comportamientos. Distingue entre funcionalidades implementadas, simuladas, estáticas, incompletas y realmente conectadas al backend.

## 1. Repositorio y estado de trabajo

Ruta absoluta:

```text
C:\Users\Usuario\Desktop\peluqueria adso
```

Repositorio:

```text
https://github.com/DuwanParraL/Proyecto_ADSO_Peluqueria
```

Rama actual:

```text
main
```

Rama remota:

```text
origin/main
```

Commit observado:

```text
7b9af59 preventivo
```

El árbol de trabajo contiene cambios locales previos. No se deben sobrescribir, revertir, eliminar ni confirmar sin autorización explícita. Antes de editar un archivo, comprobar siempre `git status` y revisar sus cambios locales.

Cambios locales observados:

- Eliminación de `docs/PROMPT_CONTEXTO_COMPLETO.md`.
- Modificaciones en varios archivos del frontend y backend.
- Archivo nuevo local `iniciar-backend.bat`.
- El documento actual se está creando nuevamente por solicitud expresa del usuario.

## 2. Arquitectura general

El proyecto tiene tres capas principales:

1. **Frontend estático:** HTML, CSS, JavaScript, Bootstrap local y jQuery local.
2. **Servidor Node.js:** `server.js`, encargado únicamente de servir archivos estáticos.
3. **Backend REST:** Spring Boot, Spring Data JPA, MySQL y Java 21.

Flujo:

```text
Navegador
  -> http://localhost:3000
  -> server.js
  -> peluqueria-FrondEnd
  -> JavaScript/api.js
  -> http://localhost:8080
  -> Controladores Spring Boot
  -> Servicios
  -> Repositorios JPA
  -> MySQL: peluqueria_adso
```

## 3. Estructura y ubicación de documentos

### Archivos raíz

```text
C:\Users\Usuario\Desktop\peluqueria adso\.gitignore
C:\Users\Usuario\Desktop\peluqueria adso\index.html
C:\Users\Usuario\Desktop\peluqueria adso\server.js
C:\Users\Usuario\Desktop\peluqueria adso\iniciar-backend.bat
```

### Documentación

```text
C:\Users\Usuario\Desktop\peluqueria adso\docs\
C:\Users\Usuario\Desktop\peluqueria adso\docs\PROMPT_CONTEXTO_COMPLETO.md
```

### Frontend

Ruta base:

```text
C:\Users\Usuario\Desktop\peluqueria adso\peluqueria-FrondEnd\
```

Páginas:

```text
peluqueria-FrondEnd\index.html
peluqueria-FrondEnd\login.html
peluqueria-FrondEnd\registro.html
peluqueria-FrondEnd\admin\citas.html
peluqueria-FrondEnd\admin\dias_libres.html
peluqueria-FrondEnd\admin\empleados.html
peluqueria-FrondEnd\cliente\agendar.html
peluqueria-FrondEnd\cliente\catalogo.html
```

JavaScript:

```text
peluqueria-FrondEnd\JavaScript\api.js
peluqueria-FrondEnd\JavaScript\auth.js
peluqueria-FrondEnd\JavaScript\cliente.js
peluqueria-FrondEnd\JavaScript\admin.js
peluqueria-FrondEnd\JavaScript\main.js
```

Estilos:

```text
peluqueria-FrondEnd\css\estilos.css
```

Librerías locales:

```text
peluqueria-FrondEnd\Librerias\bootstrap\css\bootstrap.min.css
peluqueria-FrondEnd\Librerias\bootstrap\js\bootstrap.bundle.min.js
peluqueria-FrondEnd\Librerias\jquery\jquery-4.0.0.min.js
```

Imágenes:

```text
peluqueria-FrondEnd\img\
```

Las imágenes y librerías minificadas son recursos visuales o de terceros. No modificarlas salvo solicitud expresa.

### Backend

Ruta base:

```text
C:\Users\Usuario\Desktop\peluqueria adso\peluqueria-backend\
```

Construcción y configuración:

```text
peluqueria-backend\pom.xml
peluqueria-backend\mvnw
peluqueria-backend\mvnw.cmd
peluqueria-backend\src\main\resources\application.properties
```

Clases principales:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\PeluqueriaAdsoApplication.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\ServletInitializer.java
```

Configuración:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\configuracion\corsConfig.java
```

Controladores:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\AuthController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\CitasController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\DiasLibresController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\EmpleadosController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\RolesController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\ServiciosController.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\controladores\usuariosController.java
```

Entidades:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\CitasEntity.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\DiasLibresEntity.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\EmpleadosEntity.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\RolesEntity.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\ServiciosEntity.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\entidades\UsuariosEntity.java
```

Repositorios:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\CitasRepository.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\DiasLibreRepository.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\EmpleadosRepository.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\RolesRepository.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\ServiciosRepository.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\repositorios\UsuariosRepository.java
```

Servicios:

```text
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\CitasServices.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\DiasLibresServices.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\EmpleadosServices.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\RolesServices.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\ServiciosServices.java
peluqueria-backend\src\main\java\com\sena\adso\peluqueria\adso\servicios\UsuariosServices.java
```

Pruebas:

```text
peluqueria-backend\src\test\java\com\sena\adso\peluqueria\adso\PeluqueriaAdsoApplicationTests.java
```

`peluqueria-backend\target\` es salida generada de Maven y no es fuente de verdad.

## 4. Arranque

### Frontend

Desde la raíz:

```powershell
node server.js
```

URL:

```text
http://localhost:3000
```

### Backend

Desde `peluqueria-backend`:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
.\mvnw.cmd clean package
```

URL predeterminada:

```text
http://localhost:8080
```

El archivo `iniciar-backend.bat` usa una ruta absoluta de Maven perteneciente al entorno local y puede no funcionar en otros equipos.

## 5. Frontend y contratos

`server.js` solo sirve archivos estáticos. No autentica usuarios, no administra sesiones y no accede directamente a MySQL.

`api.js` configura:

```javascript
BASE_URL: 'http://localhost:8080'
TIMEOUT_MS: 10000
```

El objeto global de integración es:

```javascript
window.UrbanLookAPI
```

Endpoints principales:

- `POST /api/auth/login`
- `GET /api/servicios`
- `GET /api/servicios/estado`
- `GET /api/citas`
- `GET /api/citas/cliente/{idCliente}`
- `GET /api/citas/empleado/{idEmpleado}`
- `GET /api/citas/empleado/{idEmpleado}/fecha/{fecha}`
- `GET /api/citas/pendientes/contador`
- `POST /api/citas`
- `PUT /api/citas/{id}`
- `PUT /api/citas/{id}/estado/{estado}`
- `DELETE /api/citas/{id}`
- Operaciones CRUD de usuarios, empleados, roles y días libres.

Antes de cambiar una llamada, comparar método HTTP, ruta, parámetros, nombres JSON, respuesta y códigos de estado del controlador Java.

`auth.js` gestiona el estado visual mediante:

```text
localStorage.usuarioActivo
```

Esto no constituye autenticación ni autorización segura. No asumir que un rol guardado en `localStorage` protege un endpoint.

## 6. Backend, entidades y base de datos

Tecnologías:

- Spring Boot 4.1.1.
- Java 21.
- Spring Data JPA.
- Spring Web MVC.
- Spring Actuator.
- MySQL Connector/J.
- Empaquetado WAR.

Tablas principales:

- `citas`
- `dias_libres`
- `empleados`
- `roles`
- `servicios`
- `usuarios`

Las relaciones principales se representan mediante IDs enteros:

```text
Usuarios.id_roles -> Roles.id
Empleados.idUsuario -> Usuarios.id
Citas.idCliente -> Usuarios.id
Citas.idEmpleado -> Empleados.id
Citas.idServicio -> Servicios.id
DiasLibres.idEmpleado -> Empleados.id
```

No asumir relaciones JPA (`@ManyToOne`, `@OneToMany`) si no están declaradas en el código actual.

Configuración actual en `application.properties`:

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

No publicar credenciales ni agregar secretos reales al código.

## 7. Entidades y reglas funcionales

### Citas

`CitasEntity` contiene:

- `id`
- `idCliente`
- `idEmpleado`
- `idServicio`
- `fecha`
- `hora`
- `estado`
- `observaciones`

La lógica de disponibilidad puede considerar:

- Día libre aprobado.
- Empleado activo.
- Horario laboral.
- Turno ocupado.
- Estado de la cita.

### Días libres

`DiasLibresEntity` contiene:

- `id`
- `idEmpleado`
- `fecha`
- `motivo`
- `estado`

### Empleados

`EmpleadosEntity` contiene, entre otros:

- `id`
- `idUsuario`
- `especialidades`
- `telefono`
- `horaEntrada`
- `horaSalida`
- `activo`
- `estado`

Verificar siempre cuál de `activo` o `estado` utiliza realmente cada operación.

### Servicios

`ServiciosEntity` contiene:

- `id`
- `nombre`
- `descripcion`
- `precio`
- `precioPromocion`
- `duracionMinutos`
- `estado`

### Usuarios y contraseñas

`UsuariosEntity` contiene:

- `id`
- `nombre`
- `apellido`
- `identification`
- `correo`
- `user`
- `password`
- `id_roles`

La contraseña es información sensible. No mostrarla, registrarla ni devolverla innecesariamente.

## 8. Limitaciones y riesgos conocidos

- La autenticación frontend depende de `localStorage` y debe contrastarse con `/api/auth/login`.
- No asumir autorización real basada en el rol visual.
- Las contraseñas deben considerarse almacenadas sin hashing hasta comprobar lo contrario.
- Hay catálogos estáticos y catálogos consumidos desde la API; no confundirlos.
- El frontend y backend pueden tener contratos distintos en nombres de campos, rutas o firmas.
- CORS debe revisarse ante errores entre los puertos 3000 y 8080.
- `ddl-auto=update` modifica automáticamente el esquema de la base de datos.
- Las consultas JPQL deben utilizar propiedades Java, no necesariamente nombres físicos de columnas.
- Las pantallas administrativas deben comprobar su protección real; una redirección frontend no es seguridad.
- No editar `target`.

## 9. Reglas obligatorias para futuras tareas

Antes de modificar:

1. Revisar el estado de Git y la rama actual.
2. Confirmar si el archivo tiene cambios locales.
3. Leer el archivo y sus dependencias inmediatas.
4. Identificar la capa responsable: frontend, `server.js`, controlador, servicio, repositorio, entidad, configuración o base de datos.
5. Confirmar el contrato entre frontend y backend.
6. No inventar endpoints, campos, roles, permisos o relaciones.
7. No sobrescribir cambios locales del usuario.
8. Mantener exactamente el nombre `peluqueria-FrondEnd`.
9. No modificar imágenes ni librerías de terceros sin solicitud.
10. Aplicar cambios quirúrgicos y relacionados únicamente con la tarea.
11. Ejecutar la validación más cercana disponible.
12. Informar archivos afectados, comportamiento cambiado, validación, resultados y limitaciones.

Toda respuesta técnica debe seguir este orden:

1. Diagnóstico del flujo real.
2. Archivo o archivos responsables.
3. Variables, clases, funciones y endpoints relacionados.
4. Diferencia entre el comportamiento actual y el esperado.
5. Cambio mínimo necesario.
6. Validación ejecutada.
7. Riesgos y limitaciones restantes.

No confundir una pantalla visual con una funcionalidad persistida. No asumir que `localStorage` representa autenticación segura. No ejecutar commits, migraciones, reinstalaciones, cambios de rama ni modificaciones de base de datos sin autorización explícita.
