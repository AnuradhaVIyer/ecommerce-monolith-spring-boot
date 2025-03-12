# 🛒 Spring Boot eCommerce Monolithic Application

This repository contains a **Spring Boot** eCommerce application built using a monolithic architecture. The application covers core features like managing users, products, categories, orders, order details, and payments. It uses an **in-memory H2 database** for ease of development and testing.

## 📌 Features

- **User Management**: Register, authenticate, and manage user profiles
- **Product Catalog**: Add, update, view, and delete products
- **Categories**: Organize products into categories for better navigation
- **Orders**: Place and manage customer orders
- **Order Details**: Store information about purchased items
- **Payment**: Simulate payment processing
- **In-Memory H2 Database**: Easy setup for development and testing

## 🛠️ Tech Stack

- **Java**: Core programming language
- **Spring Boot**: Application framework
- **H2 Database**: In-memory database for development
- **Maven**: Build and dependency management

## 📂 Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │    └── com.example.ecommerce
│   │   │         ├── exception  // Exceptions and errors
│   │   │         ├── model      // Entities (User, Product, Order, etc.)
│   │   │         ├── dto        // Data Transfer Objects (UserDTO, ProductDTO, etc.)
│   │   │         ├── repository // Data access layer
│   │   │         ├── service    // Business logic
│   │   │         └── controller // REST APIs
│   │   └── resources
│   │        ├── static 			// For static assets (CSS, JS, images, etc.)
│   │        ├── templates 		// For dynamic HTML templates (Thymeleaf, FreeMarker, etc.)
│   │        ├── application.properties
│   │        └── data.sql        // Initial seed data
└── pom.xml
```

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+

### Clone the Repository

```bash
git clone https://github.com/anuradhaviyer/ecommerce-monolith-spring-boot.git
cd ecommerce-monolith-spring-boot
```

### Run the Application

1. Ensure Maven is installed and Java is set up correctly.

2. Start the application:

```bash
mvn spring-boot:run
```

3. Access the application at:

```
http://localhost:8080
```

### Sample Endpoints
- `GET api/users` - Retrieve all users
- `POST api/users/register` - Register a new user
- `GET api/products` - Retrieve all products
- `GET api/products/categories` - Retrieve all categories
- `POST api/products` - Add a product 
- `GET api/orders/{orderId}` - Retrieve order
- `GET api/orders/user/{userId}` - Retrieve orders by user
- `POST api/orders/create` - Place a new order
- `GET api/payments` - Retrieve all payments
- `POST api/payments` - Make payment

### API Documentation

- **JSON format**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)
- **YAML format**: [http://localhost:8080/v3/api-docs.yaml](http://localhost:8080/v3/api-docs.yaml)
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)


## 📊 H2 Database Console

You can access the H2 console at:

```
http://localhost:8080/h2-console
```

Use these credentials (as defined in `application.properties`):

- **JDBC URL**: `jdbc:h2:mem:ecommerce`
- **Username**: `sa`
- **Password**: *(empty)*

## 🤝 Contributing

Feel free to fork the repository and submit pull requests. Contributions are welcome!

## 📜 License

This project is licensed under the MIT License.

---

🌟 **Happy Coding!**

