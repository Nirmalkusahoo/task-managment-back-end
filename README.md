# Task Management Back-End

This is a back-end application for a task management system. It provides RESTful APIs for managing tasks, including operations like creating, updating, retrieving, and deleting tasks. It also provides user authentication features.

## Tech Stack

- **Language**: Java
- **Framework**: Spring Boot
- **Build Tool**: Maven
- **Security**: Spring Security with JWT for authentication
- **Database**: Uses JPA for database operations . To keep things simple, an in-memory H2 database is used.

## How to Start

1. **Clone the repository**: Clone this repository to your local machine using `git clone <repository-url>`.

2. **Build the project**: Navigate to the project directory and run `mvn clean install` to build the project.

3. **Run the application**: You can run the application using the command `mvn spring-boot:run`.

Please ensure you have Maven and Java installed on your machine before running these commands.

## Server Details

The application is configured to run on a local server with the following details:

- **Host**: localhost
- **Port**: 8091

You can change these details in the `application.properties` file.

## API Endpoints

The application exposes the following main endpoints:

- `/api/auth/signup`: Register a new user.
- `/api/auth/login`: Login for existing users.
- `/api/task`: Create a new task.
- `/api/task`: Update an existing task.
- `/api/task/{name}`: Retrieve a task by its name.
- `/api/task/all`: Retrieve all tasks.
- `/api/task/{name}`: Delete a task by its name.

