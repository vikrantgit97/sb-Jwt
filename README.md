# Spring Boot 3.1.1 Security with JWT Implementation
This project demonstrates the implementation of jwt using Spring Boot 3.1.1
<img src="https://www.svgrepo.com/show/354380/spring-icon.svg" style="height: 40px">
## [Springboot_Document](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## Spring-Boot-Security-Json-Web-Token(JWT)-Application (Project_Structure)
````
└───src
├───main
│   ├───java
│   │   └───com
│   │       └───spring
│   │           └───security
│   │               └───jwt
│   │                   ├───advice
│   │                   ├───config
│   │                   ├───controllers
│   │                   ├───dto
│   │                   ├───exception
│   │                   ├───models
│   │                   ├───payload
│   │                   │   ├───request
│   │                   │   └───response
│   │                   ├───repository
│   │                   ├───runner
│   │                   ├───security
│   │                   │   ├───jwt
│   │                   │   └───security
│   │                   ├───service
│   │                   └───serviceimpl
│   └───resources
└───test
└───java
└───com
└───spring
└───security
└───jwt
````

* [Install Ubuntu or any LINUX OS for Development ](https://releases.ubuntu.com/jammy/)<img src="https://assets.ubuntu.com/v1/a7e3c509-Canonical%20Ubuntu.svg" style="height: 50px">

## Requirements
Make sure to have the followings installed:
<img src="https://www.freepnglogos.com/uploads/logo-mysql-png/logo-mysql-mysql-logo-png-images-are-download-crazypng-21.png" style="height: 40px">
<img src="https://www.svgrepo.com/show/331370/docker.svg" style="height: 40px">

* To run locally
    - MySQL Server

* To run with Docker
    - Docker (Ubuntu 22.04 [Installation guide](https://docs.docker.com/engine/install/ubuntu/))
    - Docker-compose (Ubuntu 22.04 [Installation guide](https://docs.docker.com/compose/install/)))
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 17+
* Maven 3+

## Build and Run the project, follow these steps
* Locally
    - Clone the repository: git clone [SpringBoot-Jwt](https://github.com/vikrantgit97/SpringBoot-Jwt.git)
    - Navigate to the project directory
    - Create a database in MySql `spring_jwt`
    - Build the project: `mvn clean install`
    - Run `mvn clean package spring-boot:run` to build the artifact and run the application

* Docker
    - Run `docker-compose up --build -d` to run the docker services

## Run Spring Boot application directly
```
mvn spring-boot:run
```

## Documentation (Swagger) to visualize the exposed API endpoints.
Visit it [SwaggerDocumentation_SpringBoot_Application](http://localhost:8080/swagger-ui.html)

