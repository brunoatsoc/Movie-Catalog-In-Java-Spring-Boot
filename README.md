# 🎬 MovieCatalog API

A RESTful API developed with **Spring Boot** for managing movies and reviews. This project implements **CRUD** operations between two main entities: `Movie` and `Review`, which have a **One to Many** relationship (a movie can have several reviews).

---

## 📦 Project structure

The project follows a modular organization in packages:

- `model/` - Contains the JPA entities (`Movie`, `Review`).
- `repository/` - Contains the Spring Data JPA repositories.
  - `spec/` - Specification implementations for dynamic filters.
- `service/` - Application business logic.
- `controller/` - The application's REST controllers.
  - `common/` - Global exception handler (`ExceptionHandler`).
   - `dto/` - Data Transfer Objects used in requests and responses.
   - `mappers/` - Converters between entities and DTOs (using **MapStruct**).
- `validators/` - Custom data validators.
- `exceptions/` - Custom exceptions thrown by the API.


---

## 🚀 Technologies used

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA
- PostgreSQL
- Bean Validation (Jakarta)
- MapStruct
- Lombok
- Maven

---

## 🗂️ Main Endpoints

### 🎞️ `/movies`

| Method | Endpoint | Description |
|--------|------------------|-----------------------------------------------|
| POST | `/movies` | Creates a new movie |
| GET | `/movies/{id}` | Returns a movie's data (with ratings) |
| GET | `/movies` | Searches for movies by title, year and gender |
| PUT | `/movies/{id}` | Updates an existing movie |
| DELETE | `/movies/{id}` | Removes a movie from the catalog                  |

### ✍️ `/movies/{movieId}/reviews`

| Method | Endpoint | Description |
|--------|------------------------------------------------------|----------------------------------------|
| POST | `/movies/{movieId}/reviews` | Add a new review |
| GET | `/movies/{movieId}/reviews/{reviewId}` | Queries a specific review |
| GET | `/movies/{movieId}/reviews/movie-reviews` | List all reviews for a movie |
| PUT | `/movies/{movieId}/reviews/{reviewId}` | Update a review |
| DELETE | `/movies/{movieId}/reviews/{reviewId}` | Delete a review |

---

## ⚙️ How to Run

### Prerequisites

- Java 21
- Maven
- PostgreSQL (with a database configured in `application.properties`)

### Steps

# Clone the repository
git clone https://github.com/seu-usuario/moviecatalog.git

# Access the project folder
cd moviecatalog

# Run the application
./mvnw spring-boot:run

### Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Web
- PostgreSQL Driver
- Lombok
- Spring Boot Starter Test
- MapStruct
- Lombok MapStruct Binding

## 🧑‍💻 Author

**Bruno Costa**  
Java Developer | Spring Boot Enthusiast

[![LinkedIn](https://img.shields.io/badge/LinkedIn-brunocosta-blue?logo=linkedin)](https://www.linkedin.com/in/bruno-costa-698a82159/)  
📧 E-mail: [brunosantos20003237@gmail.com](mailto:brunosantos20003237@gmail.com)