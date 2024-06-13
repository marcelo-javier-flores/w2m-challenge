# W2M Challenge 

Prueba técnica de Spring Boot, se solicita una API que permita hacer un mantenimiento CRUD de naves espaciales de series y películas.

La aplicación se encuentra dockerizada.


## Overview de Funcionalidad 📄

Permite realizar las siguientes operaciones:
* Listar todas las naves espaciales paginadas.
  * Se puede elegir página y límite por página
  * Se puede filtrar por el contenido del nombre
* Recuperar una nave espacial por ID
* Crear una nave espacial.
* Actualizar una nave espacial.
* Eliminar una nave espacial por ID

---
##### Compilar .jar:

    ./mvnw clean package -DskipTests

---
##### Iniciar aplicación:

    docker-compose up

---
##### URL Swagger: 
http://localhost:8080/w2m-challenge/swagger-ui/index.html#/

##### Credenciales para autenticación:
- USER: w2m-user
- PASS: w2m-user-password

---
##### JACOCO: Comando para obtener reporte de cobertura. 
El reporte quedará ubicado en:
target -> site -> jacoco -> index.html

    mvn clean test

---
El reporte quedará ubicado en:
target -> site -> jacoco -> index.html

##### En el desarrollo de la aplicación se utiliza:
* Java 21 JDK
* Spring Boot 3.3.0
* Junit 5.10.2
* Base de datos Postgres 12
* Docker
