# Spring Security with JWT Implementation
This project demonstrates the implementation of jwt using Spring Boot
<img src="https://www.svgrepo.com/show/354380/spring-icon.svg" style="height: 40px">
## [Springboot_Document](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## Spring-Boot-Security-Json-Web-Token(JWT)-Application (Project_Structure)
````
spring-boot-refresh-token-jwt
.
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── request.http
└── src
    ├── main
    │ ├── java
    │ │ └──in
    │ │     └──vit
    │ │         └── security
    │ │             └── jwt
    │ │                 ├── JwtApplication.java
    │ │                 ├── advice
    │ │                 │ ├── ErrorMessage.java
    │ │                 │ └── ExceptionHandler.java
    │ │                 ├── config
    │ │                 │ └── OpenApiConfig.java
    │ │                 ├── controllers
    │ │                 │ ├── AuthController.java
    │ │                 │ ├── ProductController.java
    │ │                 │ └── TestController.java
    │ │                 ├── dto
    │ │                 │ ├── ErrorDetails.java
    │ │                 │ ├── ProductDto.java
    │ │                 │ ├── Status.java
    │ │                 │ ├── StatusConverter.java
    │ │                 │ └── SuccessDetails.java
    │ │                 ├── exception
    │ │                 │ ├── ProductNotFoundException.java
    │ │                 │ ├── TokenRefreshException.java
    │ │                 │ └── UserNotFoundException.java
    │ │                 ├── models
    │ │                 │ ├── Product.java
    │ │                 │ ├── RefreshToken.java
    │ │                 │ ├── Role.java
    │ │                 │ └── User.java
    │ │                 ├── payload
    │ │                 │ ├── request
    │ │                 │ │ ├── LoginRequest.java
    │ │                 │ │ ├── SignupRequest.java
    │ │                 │ │ └── TokenRefreshRequest.java
    │ │                 │ └── response
    │ │                 │     ├── JwtResponse.java
    │ │                 │     ├── MessageResponse.java
    │ │                 │     └── TokenRefreshResponse.java
    │ │                 ├── repository
    │ │                 │ ├── ProductRepository.java
    │ │                 │ ├── RefreshTokenRepository.java
    │ │                 │ ├── RoleRepository.java
    │ │                 │ └── UserRepository.java
    │ │                 ├── runner
    │ │                 │ ├── ProductRunner.java
    │ │                 │ └── RoleRunner.java
    │ │                 ├── security
    │ │                 │ ├── WebSecurityConfig.java
    │ │                 │ ├── jwt
    │ │                 │ │ ├── AuthEntryPointJwt.java
    │ │                 │ │ ├── AuthTokenFilter.java
    │ │                 │ │ └── JwtUtils.java
    │ │                 │ └── security
    │ │                 │     ├── UserDetailServiceImpl.java
    │ │                 │     └── RefreshTokenService.java
    │ │                 ├── service
    │ │                 │ ├── ProductService.java
    │ │                 │ └── UserService.java
    │ │                 └── serviceimpl
    │ │                     ├── ProductServiceImpl.java
    │ │                     └── UserServiceImpl.java
    │ └── resources
    │     ├── application.properties
    │     └── queries.sql
    └── test
        └── java
            └── in
                └── vit
                    └── security
                        └── jwt
                            └── JwtApplication.java
````

* [Install any LINUX OS or Ubuntu for Development (Recommended) ](https://releases.ubuntu.com/noble/)<img src="https://assets.ubuntu.com/v1/a7e3c509-Canonical%20Ubuntu.svg" style="height: 50px">

## Requirements
Make sure to have the followings installed:
<img src="https://www.freepnglogos.com/uploads/logo-mysql-png/logo-mysql-mysql-logo-png-images-are-download-crazypng-21.png" style="height: 40px">
<img src="https://www.svgrepo.com/show/331370/docker.svg" style="height: 40px">

* To run locally
    - MySQL Server

* To run with Docker
    - Docker (Ubuntu 24.04 [Installation guide](https://docs.docker.com/engine/install/ubuntu/))
    - Docker-compose (Ubuntu 24.04 [Installation guide](https://docs.docker.com/compose/install/)))
## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 21+
* Maven 3+

## Build and Run the project, follow these steps
* Locally
    - Clone the repository: git clone [SpringBoot-Jwt](https://github.com/vikrantgit97/sb-Jwt.git)
    - Navigate to the project directory
    - Create a database in MySql `jwt`
    - Build the project: `mvn clean install`
    - Run `mvn clean package spring-boot:run` to build the artifact and run the application

* Docker
    - Run `docker-compose up --build -d` to run the docker services

## Run Spring Boot application directly
```
mvn spring-boot:run
```

## Documentation (Swagger) to visualize the exposed API endpoints.
Visit it [SwaggerDocumentation_Jwt_Application](http://localhost:8080/swagger-ui.html)