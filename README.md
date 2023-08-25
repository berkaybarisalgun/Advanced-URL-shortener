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


## QR Code Generation
In addition to the core features mentioned earlier, the URL Shortener App offers a convenient QR code generation functionality. This feature allows users to generate QR codes for the shortened URLs, enabling easy sharing and quick access for users who prefer scanning QR codes. The QR codes can be generated for any created short URL and can be downloaded or shared directly.

## How to Generate a QR Code

After creating a short URL using the /create endpoint, you can access the QR code generation feature.
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


2. Retrieve a generated short URL using:
```http
GET /get/{ref}
```


3. Opening the following URL in your browser:
```http
http://localhost:8080/{ref}
```

