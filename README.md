# Rewards API

A Spring Boot REST API that calculates customer reward points based on their transaction history.

## Overview

Customers earn reward points on every transaction:

- **2 points** for every dollar spent over **$100**, plus a flat **50 points**.
- **1 point** for every dollar spent between **$50 and $100**.
- No points for amounts of $50 or less.

For example, a $120 purchase earns `(120 - 100) * 2 + 50 = 90` points.

The API returns a customer's reward points broken down by month, along with the total.

## Tech Stack

- Java 17
- Spring Boot 4.0.6 (Web MVC, Data JPA, Validation)
- H2 in-memory database
- Maven

## Project Structure

```
src/main/java/com/rewards/api/
├── RewardsApiApplication.java      # Application entry point
├── controller/RewardController.java    # REST endpoints
├── service/RewardService.java          # Reward calculation orchestration
├── util/RewardCalculator.java          # Points calculation logic
├── repository/TransactionRepository.java  # Spring Data JPA repository
├── model/                              # Entities and DTOs
└── exception/                          # Custom exceptions and global handler
```

## Getting Started

### Prerequisites

- JDK 17+
- Maven (or use the bundled `mvnw`/`mvnw.cmd` wrapper)

### Run the application

```powershell
./mvnw.cmd spring-boot:run
```

The API starts on `http://localhost:8080`.

### Run the tests

```powershell
./mvnw.cmd test
```

## Database

The app uses an in-memory H2 database, seeded on startup from [data.sql](src/main/resources/data.sql).

- H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:rewardsdb`
- Username: `sa` / Password: _(empty)_

## API Reference

### Get rewards for a customer

```
GET /api/rewards/{customerId}
```

**Example request:**

```
GET http://localhost:8080/api/rewards/101
```

**Example response:**

```json
{
  "customerId": 101,
  "customerName": "John",
  "monthlyRewards": {
    "January": 90,
    "February": 25,
    "March": 150
  },
  "totalRewards": 265
}
```

Returns a `404`-style error (via [GlobalExceptionHandler](src/main/java/com/rewards/api/exception/GlobalExceptionHandler.java)) if the customer has no transactions on record.

## Configuration

Key settings in [application.properties](src/main/resources/application.properties):

| Property                        | Value         | Description                         |
| ------------------------------- | ------------- | ----------------------------------- |
| `server.port`                   | `8080`        | Application port                    |
| `spring.jpa.hibernate.ddl-auto` | `create-drop` | Schema is recreated on each startup |
| `spring.h2.console.enabled`     | `true`        | Enables the H2 web console          |
