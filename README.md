# Person Service (Microservicio MVP)

Microservicio para la gestión de personas desarrollado con **Spring Boot 3**, aplicando **Arquitectura Hexagonal**, persistencia en **PostgreSQL** y seguridad básica con **Spring Security**.

---

## 🏗️ Arquitectura

El proyecto está diseñado bajo los principios de **Arquitectura Hexagonal (Ports & Adapters)** para aislar la lógica de negocio de los detalles técnicos y frameworks externos:

```text
com.microservice.person_service
 ├── domain/                   # Núcleo del negocio (Modelos y Puertos de entrada/salida)
 ├── application/              # Casos de uso y servicios del dominio
 └── infrastructure/           # Adaptadores REST, Persistencia (JPA/Postgres) y Seguridad
