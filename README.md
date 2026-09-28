# NutriCare – Full Spring Boot + Thymeleaf + MySQL Project

A beginner-friendly full-stack NutriCare application using Spring Boot, Thymeleaf, MySQL, JPA and Spring Security.

## Main flow

Open website → Registration → BCrypt password → Login → Spring Security → Home → Dietitians → Book Appointment → My Appointments → Logout.

## Technology

- Java 23
- Spring Boot 3.3.5
- Spring Security 6
- Spring Data JPA / Hibernate
- Thymeleaf
- MySQL 8
- Maven
- HTML / CSS / JavaScript

## Eclipse setup

1. Extract the ZIP.
2. Eclipse → File → Import → Maven → Existing Maven Projects.
3. Select the extracted `nutricare-backend` folder.
4. Open `src/main/resources/application.properties`.
5. Set your MySQL password:

```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

Or set the environment variable `DB_PASSWORD`.

6. Make sure MySQL Server is running.
7. Run `com.nutricare.NutriCareApplication` as Java Application.
8. Open http://localhost:8080/

The JDBC URL contains `createDatabaseIfNotExist=true`, so the `nutricare` database can be created automatically if the MySQL account has permission.

## Important

Do not commit a real MySQL password to GitHub. Use the environment variable `DB_PASSWORD` for public repositories.

## Features

### Registration
- Full name
- Email
- Password and confirmation
- Duplicate email check
- BCrypt password hashing
- MySQL persistence

### Login
- Email/password authentication
- Spring Security
- BCrypt verification
- Invalid-login message

### Forgot password
- Registered email lookup
- New password confirmation
- BCrypt update

This demo uses direct email + new-password reset. A production system should use an email/OTP/token flow.

### Dietitians
Dietitians are seeded automatically when the table is empty.

### Appointments
- Select dietitian
- Select date and time
- Logged-in patient details are taken from the authenticated account
- Past dates are rejected
- Appointment is saved with `PENDING` status
- My Appointments shows only the logged-in patient's records

### Logout
Spring Security invalidates the session and redirects to Login.

## REST API

- GET `/api/dietitians`
- GET `/api/appointments`
- POST `/api/appointments`
- PUT `/api/appointments/{id}/status?value=CONFIRMED`

## Database tables

JPA creates/updates:

- `users`
- `dietitians`
- `appointments`
