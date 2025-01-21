# API REST de Foro

Este proyecto es una API REST para un foro, diseñada para manejar operaciones relacionadas con tópicos, usuarios, respuestas y cursos. 
La API utiliza Java Spring Boot como marco de desarrollo, Spring Security para la autenticación, y JWT (JSON Web Tokens) para la gestión de sesiones.

## Requisitos previos

Asegúrate de tener instalado lo siguiente:

- Java 17 o superior
- Maven 3.6 o superior
- Una base de datos compatible (por ejemplo, MySQL o PostgreSQL)

## Características

### 1. Tópicos
La API permite realizar operaciones CRUD completas sobre los tópicos:
- **Crear un tópico**
- **Leer uno o varios tópicos**
- **Actualizar un tópico**
- **Eliminar un tópico**

### 2. Usuarios
Para los usuarios, solo se permiten operaciones de:
- **Creación de usuarios**
- **Obtención de usuarios**

### 3. Respuestas
Para las respuestas, las operaciones disponibles son:
- **Publicar una respuesta**
- **Consultar respuestas**

### 4. Cursos
Para los cursos, las operaciones permitidas son:
- **Creación de cursos**
- **Obtención de cursos**

## Seguridad

La seguridad de la API está implementada mediante Spring Security y JWT. Los endpoints protegidos requieren un token JWT válido para ser accesibles. Los usuarios deben autenticarse para obtener su token mediante las credenciales proporcionadas al registrarse.

### Flujo de autenticación
1. El usuario envía sus credenciales al endpoint de autenticación.
2. Si las credenciales son válidas, se genera un token JWT y se devuelve al cliente.
3. El cliente debe incluir el token JWT en el encabezado `Authorization` de cada solicitud protegida.

## Endpoints principales

### Tópicos
- **GET** `/topicos` - Listar todos los tópicos
- **GET** `/topicos/{id}` - Obtener un tópico por ID
- **POST** `/topicos` - Crear un nuevo tópico
- **PUT** `/topicos/{id}` - Actualizar un tópico existente
- **DELETE** `/topicos/{id}` - Eliminar un tópico

### Usuarios
- **POST** `/usuarios` - Crear un nuevo usuario
- **GET** `/usuarios` - Obtener todos los usuarios

### Respuestas
- **POST** `/respuestas` - Crear una nueva respuesta
- **GET** `/respuestas` - Listar todas las respuestas

### Cursos
- **POST** `/cursos` - Crear un nuevo curso
- **GET** `/cursos` - Obtener todos los cursos

### Autenticación
- **POST** `/login` - Autenticarse y obtener un token JWT

