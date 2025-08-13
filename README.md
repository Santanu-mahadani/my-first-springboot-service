# Student Course Management System

This is a Spring Boot microservice for managing students, courses, departments, instructors, and enrollments. The service provides RESTful APIs for performing CRUD operations on these entities.

## Technology Stack

- Java 17
- Spring Boot 3.1.2
- Spring Data JPA
- H2 Database
- SpringDoc OpenAPI (Swagger)
- Maven
- Lombok

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6+

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the following command:
   ```bash
   mvn spring-boot:run
   ```

The application will start on port 8080.

### Accessing the Application

- H2 Database Console: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:studentdb
  - Username: sa
  - Password: (leave empty)

- Swagger UI: http://localhost:8080/swagger-ui.html
- API Documentation: http://localhost:8080/api-docs

## API Endpoints

### Students
- GET /api/students - Get all students
- GET /api/students/{id} - Get student by ID
- POST /api/students - Create new student
- PUT /api/students/{id} - Update student
- DELETE /api/students/{id} - Delete student

Similar endpoints exist for Departments, Courses, Instructors, and Enrollments.

## Database Schema

The application uses the following entity relationships:
- Student belongs to one Department
- Instructor belongs to one Department
- Course is taught by one Instructor
- Enrollment connects Students with Courses

## Development

### Building the Project
```bash
mvn clean install
```

### Running Tests
```bash
mvn test
```

## Logging

The application uses SLF4J with the following log levels:
- DEBUG: Detailed information for debugging
- INFO: General application flow
- ERROR: Error conditions

Logs are output to the console with timestamp, level, and message.
