===============================================================================
MICROSERVICES PLATFORM – SPRING BOOT / SPRING CLOUD
===============================================================================

Proyecto backend basado en arquitectura de microservicios desarrollado con
Java y el ecosistema Spring Boot / Spring Cloud.

El objetivo del proyecto es experimentar con patrones de arquitectura
utilizados en sistemas backend modernos:

- API Gateway
- Service Discovery
- Event-driven architecture
- Autenticación centralizada
- Comunicación síncrona y asíncrona entre servicios
- Arquitectura hexagonal
- Despliegue mediante contenedores Docker


===============================================================================
ARQUITECTURA GENERAL
===============================================================================

La plataforma está compuesta por varios microservicios independientes:

API Gateway
↓
Eureka Service Discovery
↓
Auth Service
User Service
↓
Kafka (mensajería entre servicios)

Servicios auxiliares:

- Keycloak (gestión de identidad y autenticación)
- PostgreSQL (persistencia de datos)
- Kafka + Zookeeper (event streaming)
- Docker Compose (orquestación local)


===============================================================================
SERVICIOS DEL SISTEMA
===============================================================================


-----------------------------------------
API GATEWAY
-----------------------------------------

Tecnología:
Spring Cloud Gateway

Responsabilidades:

- Punto de entrada único al sistema
- Enrutamiento de peticiones hacia microservicios
- Aplicación de filtros de seguridad
- Validación de tokens JWT
- Centralización de acceso a la API

Esto permite desacoplar completamente a los clientes de la estructura
interna de los microservicios.


-----------------------------------------
EUREKA SERVER
-----------------------------------------

Tecnología:
Spring Cloud Netflix Eureka

Responsabilidades:

- Registro de microservicios
- Descubrimiento dinámico de servicios
- Permite que los servicios se comuniquen por nombre lógico
  en lugar de direcciones IP.

Los microservicios se registran automáticamente al arrancar.


-----------------------------------------
AUTH SERVICE
-----------------------------------------

Responsabilidades principales:

- Registro de usuarios
- Login de usuarios
- Integración con Keycloak
- Emisión de tokens
- Publicación de eventos de autenticación

Arquitectura interna:

application
    casos de uso del sistema

domain
    modelo de dominio
    puertos de salida (ports)

infrastructure
    implementación de adaptadores externos

Patrón aplicado:

Arquitectura Hexagonal (Ports and Adapters)


Ejemplo de flujo de registro:

1. Cliente envía petición de registro
2. Auth Service crea usuario en Keycloak
3. Publica evento "UserRegisteredEvent"
4. Otros servicios pueden reaccionar al evento


Eventos publicados:

- UserRegisteredEvent
- UserLoggedInEvent


Comunicación asíncrona:

Apache Kafka



-----------------------------------------
USER SERVICE
-----------------------------------------

Responsabilidades:

- Gestión del perfil de usuario
- Persistencia de datos de usuario
- Consumo de eventos publicados por Auth Service

Base de datos:

PostgreSQL


Flujo típico:

1. Auth Service registra usuario
2. Se publica evento Kafka
3. User Service recibe el evento
4. Se crea el perfil de usuario en la base de datos


Esto desacopla completamente los servicios.


===============================================================================
EVENT CONTRACT MODULE
===============================================================================

Módulo compartido entre servicios que contiene:

- Definición de eventos
- Modelos de comunicación entre microservicios

Ejemplo:

UserRegisteredEventV1
UserLoggedInEventV1

Esto evita duplicación de modelos y mantiene consistencia
entre productores y consumidores de eventos.


===============================================================================
SEGURIDAD
===============================================================================

Sistema de autenticación basado en:

Keycloak
+
JWT
+
Spring Security

Flujo de autenticación:

Cliente
↓
Auth Service
↓
Keycloak
↓
Token JWT
↓
Cliente usa token para acceder a la API


El API Gateway valida los tokens antes de enrutar las peticiones.


===============================================================================
MENSAJERÍA
===============================================================================

Sistema de comunicación asíncrona:

Apache Kafka

Motivación:

Permitir comunicación desacoplada entre servicios.

Ejemplo:

Auth Service publica evento:
UserRegisteredEvent

User Service lo consume y crea el perfil de usuario.


Esto permite:

- escalabilidad
- resiliencia
- desacoplamiento


===============================================================================
DESPLIEGUE
===============================================================================

El sistema se ejecuta mediante Docker Compose.

Servicios definidos:

- api-gateway
- eureka-server
- auth-service
- user-service
- kafka
- zookeeper
- keycloak
- postgres

Para iniciar la plataforma completa:

    docker-compose up --build

Esto construye las imágenes de los microservicios y levanta toda la
infraestructura necesaria.

-------------------------------------------------------------------------------
Nota sobre configuración de rutas
-------------------------------------------------------------------------------

Actualmente, los microservicios utilizan rutas `localhost` en sus archivos
`application.yml` (por ejemplo, `localhost:5432` para PostgreSQL o
`localhost:9092` para Kafka). Esto se debe a que el proyecto está configurado
para ejecutarse tanto en entorno local como dentro de contenedores Docker.

Cuando los servicios se ejecutan en Docker Compose, cada microservicio corre en
su propio contenedor. En ese escenario, las rutas deberían apuntar a los nombres
de los servicios definidos en el `docker-compose.yml`, por ejemplo:

- `postgres:5432` en lugar de `localhost:5432`
- `kafka:9092` en lugar de `localhost:9092`
- `keycloak:8080` en lugar de `localhost:8083`

En esta versión del proyecto se mantiene `localhost` para facilitar las pruebas
locales sin necesidad de levantar toda la infraestructura. En un despliegue
completamente dockerizado, estas rutas deben actualizarse para usar los nombres
de los contenedores.

===============================================================================
FRONTEND DE PRUEBA
===============================================================================

Se incluye un cliente simple desarrollado en Angular.

Objetivo:

- probar autenticación
- probar endpoints protegidos
- consumir API Gateway

Funcionalidades básicas:

- login
- visualización de perfil
- almacenamiento de JWT
- interceptor HTTP para añadir token


Este frontend se utiliza únicamente como cliente de pruebas.


===============================================================================
TECNOLOGÍAS UTILIZADAS
===============================================================================

Lenguaje

Java


Framework backend

Spring Boot
Spring Security
Spring Cloud


Arquitectura

Microservicios
Hexagonal Architecture


Mensajería

Apache Kafka


Seguridad

JWT
OAuth2
OpenID Connect
Keycloak


Persistencia

PostgreSQL
JPA / Hibernate


Infraestructura

Docker
Docker Compose
Linux


Frontend

Angular (cliente de pruebas)


===============================================================================
OBJETIVO DEL PROYECTO
===============================================================================

Este proyecto fue desarrollado como laboratorio personal para aprender y
experimentar con arquitectura backend moderna basada en microservicios
utilizando el ecosistema Java.

Se centra en:

- diseño de APIs
- arquitectura distribuida
- comunicación entre servicios
- seguridad centralizada
- patrones de arquitectura backend


===============================================================================

