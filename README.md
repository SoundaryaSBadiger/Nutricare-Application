# 🥗 NutriCare – Dietitian & Nutrition Management System

> A full-stack nutrition management platform for patient registration, secure login, dietitian discovery, and appointment booking.

## ✨ Features

- 👤 Patient Registration & Login
- 🔐 Spring Security & BCrypt Password Encryption
- 🔑 Forgot Password
- 🏠 Patient Home Dashboard
- 👩‍⚕️ Dietitian Discovery
- 📅 Book Appointments
- 📋 View My Appointments
- 🔄 Appointment Status Management
- 🗄️ MySQL Database Integration
- 🌐 REST APIs
- ✨ Responsive UI & Smooth Animations

## 🛠️ Technologies

- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** Thymeleaf, HTML, CSS, JavaScript
- **Database:** MySQL
- **Build Tool:** Maven
- **Tools:** Eclipse, VS Code, Postman, Git & GitHub

## 🔄 Application Flow


🥗 NutriCare
     ↓
👤 Registration
     ↓
🔐 BCrypt Password
     ↓
🔑 Login
     ↓
🏠 Home
     ↓
👩‍⚕️ Dietitians
     ↓
📅 Book Appointment
     ↓
🗄️ MySQL
     ↓
📋 My Appointments
     ↓
🚪 Logout



## 🔄 NutriCare Workflow


                         🥗 NUTRICARE
                              │
                              ▼
                    👤 Patient Registration
                              │
                              ▼
                     🗄️ Save User in MySQL
                              │
                              ▼
                       🔐 BCrypt Password
                              │
                              ▼
                         🔑 Login
                              │
                 ┌────────────┴────────────┐
                 ▼                         ▼
          ❌ Invalid Login          ✅ Successful Login
                 │                         │
                 ▼                         ▼
             Login Page              🏠 Home Dashboard
                                           │
                    ┌──────────────────────┼──────────────────────┐
                    │                      │                      │
                    ▼                      ▼                      ▼
              👩‍⚕️ Dietitians        📅 Book Appointment    📋 My Appointments
                    │                      │                      │
                    │                      ▼                      │
                    │              Select Dietitian              │
                    │                      │                      │
                    │                      ▼                      │
                    │              Select Date & Time             │
                    │                      │                      │
                    │                      ▼                      │
                    │              🗄️ Save Appointment             │
                    │                      │                      │
                    └──────────────────────┼──────────────────────┘
                                           ▼
                                  📋 Appointment Dashboard
                                           │
                                           ▼
                                      🚪 Logout
                                           │
                                           ▼
                                      🔑 Login Page


### 🔑 Forgot Password Flow


🔑 Login
   │
   ▼
❓ Forgot Password
   │
   ▼
📧 Enter Registered Email
   │
   ▼
🔐 Set New Password
   │
   ▼
🗄️ Update Password in MySQL
   │
   ▼
🔑 Login
