# Course Registration API

REST API where a user registers for a course, deployed on AWS Lambda via Poja.

Generated and deployed with [Poja](https://poja.io): a Spring Boot application packaged for AWS Lambda, with Flyway migrations and GitHub Actions workflows that build it and deploy it to AWS.

## Endpoints

| Route | Description |
|---|---|
| `POST /courses/{courseId}/subscribe` | Register a user for a course |
| `GET /ping`, `/health/*` | Health checks (database, bucket, email) |

## Stack

Java 21, Spring Boot 3.2, PostgreSQL, Flyway, AWS Lambda (via Poja).

## Build

```bash
./gradlew build
```
