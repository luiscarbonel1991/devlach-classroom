
<h1 align="center">
  Classrooms
  <br>
</h1>
<h4 align="center">This is a microservice intended to handle classroom's reservations.</h4>
<p align="center">
  <a href="#key-features">Key Features</a> •
  <a href="#dependencies">Dependencies</a> •
  <a href="#how-to-use">How To Use</a> •
  <a href="#documentation">Documentation</a> •
  <a href="#faq">FAQ</a>
</p>

## Key Features
- Schedule Regular and Weekly Availability for a Teacher.

## Dependencies
- [PostgresSQL](https://www.postgresql.org/)
- [Java Corretto 21](https://sdkman.io/jdks)
- [Docker](https://docs.docker.com/install/)
- [Gradle](https://gradle.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)

## How To Use
- Make sure you have the dependencies installed and running.

### Install Java with [SDKMAN](https://sdkman.io/jdks).

- Install Java Version
```shell
sdk install java <version>
```
- Use Java Version
```shell
sdk use java <version>
```

### Local Development

### Starting the database

- Run the following command to start the database.
```shell
docker-compose -f ./docker/docker-compose-local.yml up classroomdb
```

- Clean the database.
```shell
docker-compose -f ./docker/docker-compose-local.yml down -v
```

### Starting Aws Localstack

- Run the following command to start the aws localstack.
```shell
docker-compose -f ./docker/docker-compose-local.yml up localstack
```

- Clean the aws localstack.
```shell
docker-compose -f ./docker/docker-compose-local.yml down -v
```


### Running the project

- Make sure you have the database running.
```shell
./gradlew bootRun
```

## Documentation
[![Postman Collection](https://run.pstmn.io/button.svg)](CLASSROOMS.postman_collection.json)

## FAQ

