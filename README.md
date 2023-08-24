# URL Shortener App

Welcome to the URL Shortener App—an innovative project currently in development. Developed using Spring Boot and powered by an H2 database, this application empowers users to shorten lengthy URLs and seamlessly redirect users from concise URLs to their original, longer counterparts. Through an array of API endpoints, this app facilitates the creation, retrieval, update, and deletion of short URLs, delivering an enhanced web experience. Please note that while the project is making strides, not all features may be fully functional at this time.

## Technologies

- **Spring Boot** 
- **H2 Database**
- **Maven** 
- **Spring Boot Data JPA**
- **Spring Boot Validation**
- **Lombok** 
- **Spring Boot Test** 
## Installation

1. Begin by cloning this repository to your local machine.
2. Fine-tune the H2 database settings within the `application.properties` file.
3. Kickstart the application by executing the command `mvn spring-boot:run`.

## API Endpoints

- **GET /:** Retrieve a list of all short URLs.
- **GET /get/{ref}:** Retrieve a specific short URL using its reference.
- **GET /{ref}:** Redirect a short URL to its original, longer URL.
- **POST /create/:** Craft a brand-new short URL.
- **POST /delete/{id}:** Delete an existing short URL.
- **PUT /update/{ref}:** Modify an existing short URL.

## Usage Example

1. Creating a new short URL is as simple as sending a POST request:

   ```http
   POST /create/
   {
     "url": "https://www.example.com"
   }
   ```

2. Retrieve a generated short URL using:
```http
GET /get/{ref}
```


3. Opening the following URL in your browser:
```http
http://localhost:8080/{ref}
```

