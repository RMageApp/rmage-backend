# RMage Backend

This repository contains the source code for the Spring Boot backend and REST API for the **RMage (Rent Management Application)**.

## Overview

This application serves as the central hub for all business logic, data persistence, and user authentication for the RMage platform. It is designed to be a modular monolith, providing a scalable and maintainable foundation for managing properties, tenants, and payments.

## Tech Stack

- **Framework:** Spring Boot 3.x
- **Language:** Java 17
- **Database:** MySQL
- **Authentication:** Keycloak / Spring Security
- **Build Tool:** Maven

## Getting Started

### Prerequisites

- Java 17 (or higher)
- Apache Maven
- Docker Desktop (for running local database)

### Local Setup

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/RMageApp/rmage-backend.git
    cd rmage-backend
    ```
2.  **Run a local MySQL database:**
    ```bash
    docker run --name rmage-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=rmage_db -p 3306:3306 -d mysql:latest
    ```
3.  **Configure the application:**
    Update the `src/main/resources/application.properties` file with your local database credentials.
4.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
The API will be available at `http://localhost:8080`.

---
