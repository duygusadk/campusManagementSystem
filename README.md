# Campus Management System

## Overview
Campus Management System is a RESTful web application built with Spring Boot that manages core academic processes within an educational institution.

The system provides structured management of students, courses, departments, professors, enrollments, and extracurricular activities, following best practices in layered architecture and database design.

---

## Key Features

- Student management (CRUD operations)
- Address management (One-to-One relationship)
- Course and department management
- Professor management
- Student enrollment in courses
- Student club management (Many-to-Many)
- Dynamic search with filtering capabilities

---

## Architecture

The application follows a **layered architecture**:

- **Controller Layer**
  - Handles HTTP requests 
- **Service Layer**
  - Contains business logic
- **Repository Layer**
  - Handles database operations using Spring Data JPA

---

## Domain Model

### Entities

- Student
- Address
- Course
- Department
- Professor
- Enrollment
- Club

### Relationships

- Student ↔ Address → One-to-One
- Student ↔ Enrollment → One-to-Many
- Enrollment ↔ Course → Many-to-One
- Department ↔ Course → One-to-Many
- Department ↔ Professor → One-to-Many
- Student ↔ Club → Many-to-Many

---

## Dynamic Search

The system supports flexible and dynamic querying using:

- JPA Specification / Criteria API

### Supported Filters

- Student name
- City (via Address)
- Club name
- Course name
- Enrollment year
  
### Example Filter DTO

```json
{
  "name": "Ivan",
  "city": "Sofia",
  "clubName": "Chess Club",
  "courseName": "Mathematics",
  "enrollmentYear": 2024
}
````
---

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|------------|
| POST   | /api/students | Create a new student |
| PUT    | /api/students/{id} | Update student |
| DELETE | /api/students/{id} | Delete student |
| GET    | /api/courses | Get all courses |
| POST   | /api/enrollments | Enroll student in course |
| GET    | /api/clubs | Get all clubs |
| POST   | /api/students/search | Dynamic student search |

---

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- REST API
- H2
- Maven

---

## Project Structure

```text
src/
├── controller/
├── service/
├── repository/
├── entity/
└── dto/

