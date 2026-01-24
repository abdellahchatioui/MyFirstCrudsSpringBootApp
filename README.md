# 🚀 Spring Boot CRUD REST API – Full Course Project

A complete **Spring Boot CRUD REST API** built step by step during a full backend learning course.  
This project demonstrates **real-world backend practices**: layered architecture, validation, exception handling, database integration, and security basics.

---

## 📌 Project Overview

This project is a **User Management REST API** that allows you to:

- Create users
- Read users (all / by ID)
- Update users
- Delete users
- Validate input data
- Prevent duplicate emails and names
- Handle errors globally
- Use an in-memory H2 database
- Prepare the project for real production use

---

## ▶ How to Run the Project
```http
git clone https://github.com/abdellahchatioui/MyFirstCrudsSpringBootApp.git
```
```http
cd  MyFirstCrudsSpringBootApp
```
```http
mvn spring-boot:run
```
Then open:
```http
http://localhost:8080
```

---
## 🧠 What I Learned in This Course

✔ Spring Boot fundamentals  
✔ REST API design  
✔ MVC architecture (Controller / Service / Repository)  
✔ JPA & Hibernate  
✔ H2 Database  
✔ Validation (`@Valid`)  
✔ Custom exceptions  
✔ Global exception handling  
✔ Pagination & sorting  
✔ Best practices for clean code  
✔ Preparing APIs for security  

---

## 🧩 Technologies Used

| Technology | Purpose |
|----------|--------|
| Java 17 | Programming language |
| Spring Boot | Backend framework |
| Spring Data JPA | Database abstraction |
| Hibernate | ORM |
| H2 Database | In-memory database |
| Maven | Dependency management |
| Postman | API testing |

---

## 📡 API Endpoints

### 🔹 Get all users
```http
GET /api/users
```
### 🔹 Get user by ID
```http
GET /api/users/{id}
```
### 🔹 Create user
```http
POST /api/users
```

##### Request Body
```http
{
  "name": "John Doe",
  "email": "john@example.com"
}
```
### 🔹 Update user
```http
PUT /api/users/{id}
```
### 🔹 Delete user
```http
DELETE /api/users/{id}
```
## ⚠ Validation Rules

 * Email must be unique
 * Name must be unique  
 * Request body must be valid
 * Errors are handled globally

## 🧯 Error Handling

The project uses a `GlobalExceptionHandler` to return clean JSON error responses instead of raw errors.
##### Example:
```http
{
  "status": 400,
  "message": "Email already exists"
}
```

