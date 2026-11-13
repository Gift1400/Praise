# Praise Church Management System

A full-stack web application built to help churches manage their day-to-day operations — members, events, announcements, donations, and more.

---

## What it does

- Member registration and management
- Event creation and RSVP tracking
- Church announcements by type (General, Event, Support)
- Donation records
- Prayer requests and testimonies
- Outreach program participation
- Bible verse lookup by life situation
- Leader and sermon management

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.2 |
| Database | MySQL |
| ORM | Spring Data JPA |
| Security | Spring Security |
| Build Tool | Maven |
| Frontend | Vue.js |

## Project Structure


```
Praise/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── controller/       # REST API endpoints
│   │   │   │   ├── domain/           # Domain entities and models
│   │   │   │   ├── factory/          # Object creation and builder pattern factories
│   │   │   │   ├── repository/       # Data access and JPA repositories
│   │   │   │   └── util/             # Utility classes and helper methods
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   │           ├── factoryTest/
│   │           ├── repositoryTest/
│   │           ├── serviceTest/
│   │           └── controllerTest/
│   └── pom.xml
│
├── frontend/
│   ├── public/                       # Static assets
│   ├── src/
│   │   ├── assets/                   # Images, icons and styles
│   │   ├── components/               # Reusable Vue components
│   │   ├── views/                    # Application pages
│   │   ├── router/                   # Frontend navigation
│   │   ├── services/                 # API calls to the backend
│   │   ├── stores/                   # Application state management (if used)
│   │   ├── App.vue                   # Root Vue component
│   │   └── main.js                   # Frontend entry point
│   ├── index.html
│   └── package.json                  # Frontend dependencies and scripts
│
└── README.md
```


## Domain Classes

| Class | Description |
|---|---|
| `Member` | Registered church member |
| `Leader` | Church leader with contact details |
| `Event` | Church event with RSVP support |
| `RSVP` | Member response to an event |
| `Announcement` | Church-wide announcement |
| `AnnouncementType` | Enum — EVENT, SUPPORT, GENERAL |
| `Donation` | Member donation record |
| `Testimony` | Member testimony |
| `PrayerRequest` | Member prayer request |
| `OutreachProgram` | Community outreach initiative |
| `Sermon` | Sermon delivered by a leader |
| `Attendance` | Tracks member attendance at events |
| `ChurchSite` | Physical church location |
| `BibleVerse` | Verse mapped to a life situation |
| `LifeSituation` | Enum — HAPPY, SAD, ANXIETY, GRIEF, CONFUSION |

---

## UML Diagram
<img width="3989" height="1915" alt="Praise_UML" src="https://github.com/user-attachments/assets/9bcd1afa-f7c5-4154-a79e-cdfac3b22c98" />

---

## Getting Started

### Requirements

- Java 21
- Maven
- MySQL

### Setup

1. Clone the repo

```bash
git clone https://github.com/your-username/Praise.git
```

2. Create the database in MySQL

```sql
CREATE DATABASE praisedb;
```

3. Update `application.properties` with your credentials

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/praisedb
spring.datasource.username=root
spring.datasource.password=your_password_here
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
server.port=8080
```

4. Open the project in IntelliJ — select the `Praise` root folder
5. Right-click `pom.xml` → Maven → Sync Project
6. Run `PraiseApplication.java`





