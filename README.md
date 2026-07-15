# 💧 Water Tracker

A full-stack web application to help users track their daily water intake, monitor hydration progress, and compare intake across different dates.

🌐 **Live Demo:** https://water-tracker-5sfa.onrender.com

## Features

- User Signup & Login
- Session-based Authentication
- Dashboard with Daily Progress
- Add Water Intake (Glass, ML, Litre)
- Automatic Unit Conversion
- One Entry per Day
- View Water Intake History
- Edit & Delete Entries
- Compare Water Intake Between Two Dates
- Update Daily Goal & Profile
- Hydration Reminder Settings

## Tech Stack

**Backend**
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- Maven

**Frontend**
- HTML
- CSS
- JavaScript

**Database**
- PostgreSQL (Production)
- MySQL (Development)

**Deployment**
- Docker
- Render
- GitHub

## Run Locally

Clone the repository:

```bash
git clone https://github.com/Adithya880/Water-Tracker.git
cd Water-Tracker
```

Configure your database in `application-local.properties`, then run:

```bash
mvn spring-boot:run
```

The application will be available at:

```
http://localhost:8080
```

## Project Structure

```
src
├── main
│   ├── java
│   ├── resources
│   │   ├── static
│   │   └── application.properties
└── test
```

## Author

**Adithya Suresh**

GitHub: https://github.com/Adithya880
