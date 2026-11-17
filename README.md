# Course Registration API

REST API where a user registers for a course, deployed on AWS Lambda.

Generated from the Poja starter: Spring Boot packaged for AWS Lambda, with Flyway migrations and GitHub Actions workflows for build and deployment.

## Endpoints

| Route | Description |
|---|---|
| `POST /courses/{courseId}/subscribe` | Register a user for a course |
| `GET /ping`, `/health/*` | Health checks (database, bucket, email) |

## Stack

Java 21, Spring Boot 3.2, PostgreSQL, Flyway, AWS Lambda.

## Build

```bash
./gradlew build
```
