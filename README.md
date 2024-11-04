# Spring Boot Multi-Database User Management

This project is a Spring Boot application that connects to multiple databases (PostgreSQL and MySQL) and provides a RESTful API to manage users.

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Running the Application

1. Clone the repository:

   ```bash
   git clone https://github.com/kzahrevskyi/kzahrevskyi-test-task.git
   cd kzahrevskyi-test-task
2. Build and run the application using Docker Compose:
   ```bash
   docker compose up --build
3. Access the application at http://localhost:8087 <br> Acces to the swagger at http://localhost:8087/swagger-ui/index.html
5. To test locally, you can populate the database by executing the sql script, which can be found at `src/main/resources/db/migration`
