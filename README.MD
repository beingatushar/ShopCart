# 🛒 ShopCart Backend API

A RESTful backend service for a shopping platform, built using **Spring Boot**, **JPA**, and **OpenAPI 3.1**. This project provides APIs for managing products, users, brands, categories, addresses, and product images.

---

## 🚀 Getting Started

### 📦 Requirements

- Java 17+
- Maven 3.6+
- Spring Boot 3.x

### ▶️ Running the App

```bash
git clone https://github.com/beingatushar/shopcart.git
cd shopcart
./mvnw spring-boot:run
```

---

## 📘 API Documentation

Once the server is running, access the OpenAPI docs via Swagger UI:

- **Swagger UI**: [http://localhost:8081/api/swagger-ui/index.html](http://localhost:8081/api/swagger-ui/index.html)
- **Raw OpenAPI Spec**: [http://localhost:8081/api/v3/api-docs](http://localhost:8081/api/v3/api-docs)

OpenAPI version: **3.1**  
Base URL: `http://localhost:8081/api`

---

## 📚 Available Endpoints

### 🔹 Address Management
```
GET     /addresses
GET     /addresses/{addressId}
POST    /addresses
PUT     /addresses/{addressId}
DELETE  /addresses/{addressId}
```

### 🔹 Category Management
```
GET     /categories
GET     /categories/{categoryName}
POST    /categories
PUT     /categories/{categoryId}
DELETE  /categories/{categoryId}
```

### 🔹 Product Management
```
GET     /products
GET     /products/{productId}
POST    /products
PUT     /products/{productId}
DELETE  /products/{productId}
```

### 🔹 Product Image Management
```
GET     /product-images
GET     /product-images/{productImageId}
GET     /product-images/product/{productId}
POST    /product-images
PUT     /product-images/{productImageId}
DELETE  /product-images/{productImageId}
```

### 🔹 User Management
```
GET     /users
GET     /users/{userId}
POST    /users
PUT     /users/{userId}
DELETE  /users/{userId}
```

### 🔹 Brand Management
```
GET     /brands
GET     /brands/{brandName}
POST    /brands
PUT     /brands/{brandId}
DELETE  /brands/{brandId}
```

---

## 📦 DTOs / Schemas

Each resource supports the following DTO structures (available in OpenAPI docs):

- `CreateXDTO`, `UpdateXDTO` for input
- `XDTO` for response
- Example:
    - `CreateProductDTO`, `UpdateProductDTO`, `ProductDTO`
    - `CreateAddressDTO`, `UpdateAddressDTO`, `AddressDTO`

---

## 🛠️ Tech Stack

- Spring Boot
- Spring Data JPA
- Hibernate
- ModelMapper
- OpenAPI 3.1 / Swagger UI
- Lombok
- H2 / MySQL (configurable)

---

## 👨‍💻 Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss.

---

## 📝 License

[MIT](LICENSE)

---

## 📩 Contact

For queries, email [aggarwaltushar36@gmail.com](mailto:aggarwaltushar36@gmail.com)