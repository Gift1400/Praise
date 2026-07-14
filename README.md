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
| Frontend | Coming soon |

## Project Structure

```
Praise/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── controller/       # REST API endpoints
│   │   │   │   ├── domain/           # Entity classes
│   │   │   │   ├── factory/          # Builder pattern factories
│   │   │   │   ├── repository/       # JPA repositories
│   │   │   │   └── util/             # Helper/utility classes
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   │       └── java/
│   │           ├── factoryTest/      # Unit tests
│   │           ├── repositoryTest/   # Database operations
│   │           ├── serviceTest/      # Business logic
│   │           └── controllerTest/   # API endpoints
│   └── pom.xml
├── frontend/ #comming soon
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

<img width="1442" height="681" alt="Screenshot 2026-06-15 183355" src="https://github.com/user-attachments/assets/318f1f6b-a29b-4f67-a65f-4d69a086f086" />

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





