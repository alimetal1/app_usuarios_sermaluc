API Usuarios Este proyecto es una API RESTful diseñada para manejar el registro y la gestión de usuarios. Fue desarrollada como parte de un proceso de reclutamiento de Sermaluc con fecha de hoy 08/08/2025, implementando buenas prácticas de desarrollo y tecnologías modernas.

Tecnologías Utilizadas Java 17: Lenguaje de programación principal. Spring Boot 3.0.0: Framework para construir aplicaciones RESTful. H2 Database: Base de datos en memoria utilizada para pruebas rápidas. Maven: Herramienta de gestión de dependencias y construcción. Lombok: Librería para reducir el código repetitivo en Java. Swagger OpenAPI: Documentación de la API. JUnit 5: Framework para pruebas unitarias. Requisitos Previos Tener instalado Java 17 o superior. Tener instalado Maven. Un cliente HTTP como Postman o cURL. (Opcional) Git para clonar el repositorio. Configuración del Proyecto

Clonar el Repositorio bash Copiar código git clone https://github.com/alimetal1/app_usuarios_sermaluc.git cd api-usuarios
Construir el Proyecto Usando Maven, puedes construir el proyecto con el siguiente comando:
bash mvn clean install 3. Ejecutar el Proyecto Inicia la aplicación:

bash mvn spring-boot:run La API estará disponible en: http://localhost:8080.

Base de Datos Esta API utiliza H2 Database (base de datos en memoria) para pruebas. No es necesario configurar manualmente la base de datos; al iniciar la aplicación, se crea automáticamente. Puedes acceder a la consola de H2 en:

URL: http://localhost:8080/h2-console Driver Class: org.h2.Driver JDBC URL: jdbc:h2:mem:testdb User: sa Password: (dejar vacío) Endpoints Principales

Registrar Usuario URL: POST /api/usuarios Descripción: Registra un nuevo usuario en el sistema. Headers: Content-Type: application/json Body (JSON): json { "name": "Alinson Morales", "email": "ali@moraless.com", "password": "AlinsonM", "phones": [ { "number":"999384455", "citycode": "4190000", "contrycode": "56" } ] } Ejemplo de cURL: bash curl --location 'http://localhost:8080/api/usuarios'
--header 'Content-Type: application/json'
--data-raw '{ "name": "Alinson Morales", "email": "ali@moraless.com", "password": "AlinsonM", "phones": [ { "number":"999384455", "citycode": "4190000", "contrycode": "56" } ] }'
Respuesta Exitosa json { "id": "595d5ee6-f11c-4e49-b425-f988621353a7", "name": "Alinson Morales", "email": "ali@moraless.com", "created": "2024-11-20", "modified": "2024-11-20", "lastLogin": "2024-11-20", "token": "ed2f98d2-55f8-465a-965f-74c953fab822", "phones": [ { "number": "999384455", "citycode": "4190000", "contrycode": "56" } ], "active": true } Pruebas Unitarias El proyecto incluye pruebas unitarias que validan las funcionalidades clave del servicio. Para ejecutarlas:
bash mvn test Documentación de la API La API está documentada utilizando Swagger OpenAPI. Puedes acceder a la documentación interactiva en:

http://localhost:8080/swagger-ui.html

Contacto Alinson Morales Correo: ali.morales.guitar@gmail.com LinkedIn: https://www.linkedin.com/in/alison-morales-san-mart%C3%ADn-b42049223/
