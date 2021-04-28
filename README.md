# P9_NOTES

Micro-service note manage the notes for a patient.

## Prerequisite to run

- Java 1.8
- Spring Boot 2.2.6
- Docker
- MongoDB

## Installation
### Host file

- 127.0.0.1 note
- 127.0.0.1 assessment
- 127.0.0.1 webapp
- 127.0.0.1 patient
- 127.0.0.1 mongodb

### Docker image construction in project directory
~~~
docker build --build -t note .
~~~
### Docker execution
if docker-compose is not use
~~~
docker run -p 8082:8082 --name P9-note note
~~~~
if docker-compose is user (directory patient)
~~~
docker-compose up -d
~~~~

## Documentation
 http://localhost:8082/swagger-ui.html
   
## Global architecture
![alt text](schema.jpg)