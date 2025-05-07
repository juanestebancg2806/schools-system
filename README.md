# 🏫 Schools System – Microservices Demo

This is a **study project** designed to explore and understand the fundamentals of **microservices architecture** using **Spring Boot 3** and **Java 17**. It showcases how multiple services can interact through an **API Gateway** with **Basic Authentication**.


---

## 🧱 Architecture Overview

The system is composed of the following microservices:

- **`student-service`**: Manages student-related operations.
- **`school-service`**: Handles school information and management.
- **`course-service`**: Manages course offerings and details.
- **`enrollment-service`**: Oversees student enrollments in courses.
- **`service-registry`**: Implements service discovery using Eureka.
- **`cloud-gateway`**: Acts as the API Gateway, routing requests to appropriate services and enforcing security.

Each service is designed to operate independently, promoting scalability and maintainability.


## 🐳 Docker Database Setup

Some services, like the `course-service`, are configured to use a MySQL database. You can quickly spin up a MySQL container using Docker with the following command:

```bash
docker run --detach \
  --env MYSQL_ROOT_PASSWORD=admin \
  --env MYSQL_USER=admin \
  --env MYSQL_PASSWORD=admin \
  --env MYSQL_DATABASE=coursedb \
  --name mysqlCourseDb \
  --publish 3308:3306 \
  mysql:8-oracle
```
take a look inside each application.properties of each service to run the respective command.

---

## 🔐 Security

The **API Gateway** (`cloud-gateway`) is configured with **Basic Authentication** to secure endpoints and control access to the microservices.

---

## 🚀 Getting Started

### Prerequisites

- **Java 17** installed
- **Maven** or **Gradle** for building the projects

### Running the Application

1. **Clone the repository:**

   ```bash
   git clone https://github.com/juanestebancg2806/schools-system.git
   cd schools-system
   ```
2. **Start the services in the following order:**

- **Service Registry**  
   Navigate to the `service-registry` directory and run:  
   `./mvnw spring-boot:run`

- **API Gateway**  
   Navigate to the `cloud-gateway` directory and run:  
   `./mvnw spring-boot:run`

- **Student Service**  
   Navigate to the `student-service` directory and run:  
   `./mvnw spring-boot:run`

- **School Service**  
   Navigate to the `school-service` directory and run:  
   `./mvnw spring-boot:run`

- **Course Service**  
   Navigate to the `course-service` directory and run:  
   `./mvnw spring-boot:run`

- **Enrollment Service**  
   Navigate to the `enrollment-service` directory and run:  
   `./mvnw spring-boot:run`

