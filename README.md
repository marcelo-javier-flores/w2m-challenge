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

##### Iniciar aplicación

    docker-compose up

---

Una vez levantada la aplicación se puede consultar la siguiente URL del swagger: http://localhost:8080/w2m-challenge/swagger-ui/index.html#/

##### En el desarrollo de la aplicación se utiliza:
* Java 21 JDK
* Spring Boot 3.3.0
* Junit 5.10.2
* Base de datos Postgres 12
* Docker
