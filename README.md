# NOTE-TAKING APP
Welcome to the Note-Taking App!. The note-taking is a web based application that allows a user to Create,
read, edit and delete notes using REST APIs.

## Table of Contents
* Getting Started
    * Prerequisites
    * Running the application
* API Endpoints
* Running Test
* Swagger Documentation
* Assumptions



## Getting Started

### Prerequisite
- Java Development Kit [(JDK)](https://www.oracle.com/java/technologies/downloads/) 17 or higher
- Apache Maven 3.6.0 or higher
- [IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=mac) with the Spring Boot plugin or any other suitable IDE that can run spring boot
- PostgresSQL: Install and configure PostgresSQL as the app's database.


### Running the application
Follow this steps to run the application:

- Clone the [repository](https://github.com/nnamdi16/note-taking-app.git)

```bash 
git clone https://github.com/nnamdi16/note-taking-app.git
cd note-taking-app
 ```
- Set up your PostgresSQL database and configure the database connection details in **src/main/resources/application.properties**.
- Build the application using maven.

```bash
mvn clean install
```

- Run the Spring Boot application:
```bash
mvn spring-boot:run
```
The app should now be running locally at http://localhost:8080 


## API Endpoints
The following API endpoints are available:

* GET /api/v1/notes: Retrieve all notes.
* GET /api/v1/notes/{id}: Retrieve a note by ID.
* POST /api/v1/notes: Create a new note.
* PUT /api/v1/notes/{id}: Update an existing note.
* DELETE /api/v1/notes/{id}: Delete a note by ID.


## Running Test
#### Spring Boot Application
To run the test for the spring boot application, run the command below:

```bash
mvn clean test
```


## Documentation
The REST endpoints for the note-taking app is documented using swagger.
The swagger documentation UI is seen below:
- [Note-taking App - http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Assumptions
- Creating a user account is not required.
- Creating, Reading, Updating and Deleting a note doesn't require authentication or authorisation

