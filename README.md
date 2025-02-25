# Product Management API 🚀

[![Java](https://img.shields.io/badge/Java-17-%23ED8B00?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-%236DB33F?logo=spring)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.9.6-%23C71A36?logo=apachemaven)](https://maven.apache.org/)
[![Redis](https://img.shields.io/badge/Redis-7-%23DC382D?logo=redis)](https://redis.io/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-%23316192?logo=postgresql)](https://www.postgresql.org/)

A robust RESTful API for product management with advanced features and full documentation.

## 📋 Project Overview

Modern solution for product management operations, featuring:
- Complete CRUD operations
- Redis caching strategies
- Comprehensive validation
- Multi-environment support
- Automated API documentation

## ✨ Key Features

- **Full Product Lifecycle Management**
- **Advanced Caching** with Redis
- **Auto-Generated Documentation** via Swagger/OpenAPI
- **Multi-Database Support** (H2 + PostgreSQL)
- **Validation Framework** with custom messages
- **3 Environment Profiles** (dev/test/prod)
- **Performance Optimizations**
- **Internationalization Ready**

## 🛠 Technologies

| Category       | Technologies                                                                 |
|----------------|------------------------------------------------------------------------------|
| **Backend**    | Java 17, Spring Boot 3.2.5, Spring Data JPA, Spring Validation              |
| **Database**   | H2 (Development/Test), PostgreSQL (Production)                              |
| **Caching**    | Redis 7, Spring Cache                                                       |
| **Docs**       | SpringDoc OpenAPI 3.0, Swagger UI                                           |
| **Testing**    | JUnit 5, Mockito, Embedded Redis                                            |
| **Tools**      | Maven, Lombok, Log4j2                                                       |

## ⚙ Prerequisites

- JDK 17+
- Maven 3.9+
- Redis 7+ (optional for dev profile)
- PostgreSQL 15+ (for production)

## 🚀 Installation

```bash
# Clone repository
git clone https://github.com/FelipeDeMoraes19/project-dio.me-2025.git

# Build project
mvn clean install

# Run application 
mvn spring-boot:run
```
