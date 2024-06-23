# Anagram Application

This Java 17 Spring Boot application designed to check if two strings are anagrams of each other. It supports both a REST API and a CLI for checking anagrams and retrieving previously checked anagrams.

## Requirements

You need to have Java JDK 17 installed on your machine and available on your path.

## Features
- Check if two strings are anagrams.
- Retrieve all anagrams of a given string that were previously checked.
- In-memory storage of checked anagrams.
- Spring Boot REST API.
- Command Line Interface (CLI) application.

## Cloning the Git Repository

To get started with this project, you'll need to clone this Git repository to your local machine.
Follow these steps:

1. Open your terminal.
2. Navigate to the directory where you want to store the project.
3. Clone the project from the following git repository

  ```bash
  git clone https://github.com/elenakjaeva/anagram-application.git
  ```

## Building the Application

After cloning the application to your local machine navigate to the application directory

  ```bash
  cd anagram-application
  ```
Build the application
  ```bash
  ./gradlew clean build
  ```

## Running the Application

### Running the CLI Application and the Spring Boot REST API

Execute the command:
  ```bash
  java -Dspring.profiles.active=cli -jar build/libs/anagram-1.0.0.jar
  ```

### Running only the Spring Boot REST API

Start the application by executing the command:
  ```bash
  ./gradlew bootRun
  ```

Wait for the application to start.

The REST API will be available on http://localhost:8080

## Usage


### CLI Application
Run the CLI application and follow the prompts to check anagrams and retrieve previously checked anagrams.
```bash
Choose an option:
1. Check if two strings are anagrams
2. Find all anagrams of a string
3. Exit
  ```

****
### REST API
#### Check Anagrams
```bash
GET /api/v1/anagrams?input1={input1}&input2={input2} 

Response: {"response": "Yes"}
  ```

#### Find Anagrams: GET /api/anagrams/find?word=listen
```bash
GET /api/v1/anagrams/{input} 

Response: ["string1", "string2"]
  ```
#### Swagger API Documentation

The application integrates Swagger for API documentation.
Once the application is running locally, you can access the Swagger UI at

http://localhost:8080/swagger-ui/index.html

This UI provides a user-friendly interface to explore and test the available API endpoints.

#### Postman Collections

For API testing and interaction, a Postman collection is provided in the repository.
You can import this collection into Postman to access pre-configured requests for testing the API endpoints.

To import the Postman collection:

* Open Postman.
* Click on the "Import" button.
* Choose the Postman collection JSON file from the repository (named 'Anagrams.postman_collection.json').
  The collection will be imported, and you can start using the pre-configured requests for testing.

### Testing

Run the tests using Gradle:
  ```bash
  ./gradlew test
  ```

## Project Structure
The project follows a modular structure based on the Hexagonal architecture. 
The Hexagonal architecture, also known as the Ports and Adapters, is used in this project for several reasons

* `Modularity`: The architecture promotes modular design by separating concerns into distinct layers such as domain, application, and infrastructure, making the codebase more maintainable and scalable.
* `Testability`: The use of ports and adapters allows for easier testing as dependencies can be mocked or replaced with test implementations, facilitating unit and integration testing.
* `Flexibility`: Ports and adapters enable the application to be easily adapted to different environments or technologies without significant changes to the core business logic, promoting flexibility and future-proofing.
* `Isolation of Concerns`: The architecture isolates business logic from external dependencies, such as databases or external services, improving code readability and making it easier to reason about system behavior.
* `Separation of Domain and Infrastructure`: By separating domain logic from infrastructure concerns, such as persistence or communication protocols, the codebase becomes more maintainable and adheres to principles like Single Responsibility and Dependency Inversion.

### Package Structure
Base package is `com.beyonnex.anagram`.

Under the base package, the following package structure can be found: 
- `domain`, contains all domain-related classes
- `infrastructure`, contains implementations for inbound and outbound adapters according to the Hexagonal Architecture (Ports and Adapters).

Under the base package the following package structure can be found:
  - `domain.config`: Contains configuration file for initializing the beans/usecases.
  - `domain.exception`: Contains custom exception classes.
  - `domain.model`: Includes domain models. 
  - `domain.service`: Contains services and business logic for anagram management.
    - `domain.service.port`: Defines ports for interacting with the domain.
      - `domain.service.port.in`: Defines input ports for the domain.
      - `domain.service.port.out`: Defines output ports for the domain.
  - `infrastructure.adapter.out.persistence.inmemory`: Contains adapters for InMemory persistence-related operations.
    - `infrastructure.adapter.out.persistence.inmemory.config`: Configuration classes for persistence adapters. 
    - `infrastructure.adapter.out.persistence.inmemory.adapter`: Entity classes for the anagram domain. 
  - `infrastructure.adapter.in`: Contains IN adapters for entrying the application through REST API and CLI application.
    - `infrastructure.adapter.in.restapi.controller`: Controller classes for handling REST API requests. 
    - `infrastructure.adapter.in.restapi.exception.handler`: Exception Handler that will generalize the error responses for the REST API.
    - `infrastructure.adapter.in.cli`: CLI application for using the app from command line.
    - `infrastructure.adapter.in.dto`: Data transfer objects (DTOs) for REST API and CLI communication. 
    - `infrastructure.adapter.in.config`: Configuration class for initializing the infrastructure IN adapters.