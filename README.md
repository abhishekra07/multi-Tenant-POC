# Multi-Tenant Clinic Management POC

This is a Proof of Concept (POC) for a multi-tenant **Hospital/Clinic Management System** using **Spring Boot 3**, **Hibernate 6**, **MySQL**, and **Liquibase**.

The project implements a **schema-per-tenant** multitenancy strategy without using deprecated Hibernate configurations.

---

## 🚀 Features

- Dynamic tenant schema creation
- Schema-per-tenant strategy using Hibernate 6
- Liquibase-based migration for each tenant
- Basic tenant context management using HTTP header (`X-Tenant-ID`)
- Entity and repository setup for core tables:
    - `Patient`
    - `User`
    - `Clinic`
    - `Doctor`
    - `Appointment`
    - `MedicalRecord`

---

## 📦 Tech Stack

- **Spring Boot 3.x**
- **Hibernate 6.x**
- **Liquibase**
- **MySQL**
- **HikariCP**
- **Jakarta Persistence (JPA)**

---

## 🧠 How It Works

1. A client sends a request with an `X-Tenant-ID` header.
2. `TenantFilter` reads this header and sets it in a `ThreadLocal` context.
3. `CurrentTenantIdentifierResolverImpl` uses this context to identify the schema for that request.
4. Hibernate dynamically switches schemas for each session.
5. When a new tenant (schema) is created, Liquibase migrations are applied to initialize required tables.

---

## 🧪 Running the App

### Pre-requisites

- MySQL Server running

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/hospital_admin
    username: root
    password: your_password
  jpa:
    hibernate:
      ddl-auto: none
    show-sql: true
```



