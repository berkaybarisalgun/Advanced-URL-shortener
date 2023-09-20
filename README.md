# URL Shortener App

Welcome to the URL Shortener App! This application, built with Spring Boot and backed by an H2 database, empowers users to shorten long URLs and seamlessly redirect them to their original, longer counterparts. The app offers a range of API endpoints for creating, retrieving, updating, and deleting URLs, enhancing the web experience. Please note that while the project is in progress, not all features may be fully functional at this time.

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
2. Configure the H2 database settings within the `application.properties` file.
3. Start the application by executing the command `mvn spring-boot:run`.

## API Endpoints

- **GET /:** Retrieve a list of all URLs.
- **GET /get/{ref}:** Retrieve a specific URL using its reference.
- **GET /{ref}:** Redirect a URL to its original, longer URL.
- **POST /create/:** Craft a brand-new URL.
- **POST /delete/{id}:** Delete an existing URL.
- **PUT /update/{ref}:** Modify an existing URL.


## QR Code Generation
In addition to the core features, the URL Shortener App offers QR code generation functionality. This feature allows users to generate QR codes for the shortened URLs, simplifying sharing and quick access for those who prefer scanning QR codes. QR codes can be generated for any created URL and can be downloaded or shared directly.

## How to Generate a QR Code

After creating a URL using the /create endpoint, you can access the QR code generation feature.
To generate a QR code for a specific URL, send a GET request to the following endpoint:
POST /create/

The app will respond with the generated QR code path in your local driver.

Users can download the QR code image and use it for sharing or printing.

This QR code generation feature adds a new layer of convenience to the URL Shortener App, making it easier for users to share and access the shortened URLs using QR codes.

## Usage Example

1. Creating a new URL is as simple as sending a POST request:

   ```http
   POST /create/
   {
     "url": "https://www.example.com"
   }
   ```

   It will response you with qrCode!


2. Retrieve a generated URL using:
```http
GET /get/{ref}
```


3. Opening the following URL in your browser:
```http
http://localhost:8080/{ref}
```

