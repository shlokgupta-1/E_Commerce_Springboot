# 🛒 E-Commerce Backend Application (Spring Boot + MongoDB)

This project is a **complete backend for an e-commerce application** built using **Spring Boot** and **MongoDB**. It supports product management, cart operations, order placement, and a mock payment workflow with webhook handling.

This README is written to be **exam/viva-friendly**, **college-project ready**, and **easy to set up from scratch**.

---

## 🚀 Features

* Product APIs (create & list products)
* Cart management (add, view, clear)
* Order placement from cart
* Order retrieval
* Mock payment gateway
* Payment webhook simulation
* MongoDB persistence

---

## 🧱 Tech Stack

* **Java 17**
* **Spring Boot 4.x**
* **Spring Web**
* **Spring Data MongoDB**
* **MongoDB (local)**
* **Maven**
* **Postman (for testing)**

---

## 📁 Project Structure

```
com.example_e_commerce
│
├── controller
│   ├── ProductController.java
│   ├── CartController.java
│   ├── OrderController.java
│   └── PaymentController.java
│
├── service
│   ├── ProductService.java
│   ├── CartService.java
│   ├── OrderService.java
│   └── PaymentService.java
│
├── repository
│   ├── ProductRepository.java
│   ├── CartRepository.java
│   ├── OrderRepository.java
│   └── PaymentRepository.java
│
├── model
│   ├── User.java
│   ├── Product.java
│   ├── CartItem.java
│   ├── Order.java
│   ├── OrderItem.java
│   └── Payment.java
│
├── dto
│   ├── AddToCartRequest.java
│   ├── CreateOrderRequest.java
│   ├── PaymentRequest.java
│   └── PaymentWebhookRequest.java
│
├── webhook
│   └── PaymentWebhookController.java
│
└── ECommerceApplication.java
```

---

## ⚙️ Application Setup (Step-by-Step)

### 1️⃣ Prerequisites

Make sure the following are installed:

* **Java 17**
  Check using:

  ```bash
  java -version
  ```

* **Maven** (or use the Maven Wrapper provided)

* **MongoDB Community Server**

---

### 2️⃣ Start MongoDB

Start MongoDB locally on default port `27017`.

Check:

```bash
mongod
```

---

### 3️⃣ application.yaml Configuration

📁 `src/main/resources/application.yaml`

```yaml
server:
  port: 8080

spring:
  data:
    mongodb:
      host: localhost
      port: 27017
      database: ecommerce_db
```

---

### 4️⃣ Build & Run the Application

From the project root:

```bash
./mvnw spring-boot:run
```

Expected log:

```
Tomcat started on port 8080
Started ECommerceApplication
```

---

## 🧪 API Testing (Postman)

### 🔹 Product APIs

#### Create Product

```
POST /api/products
```

```json
{
  "name": "iPhone 15",
  "description": "Apple smartphone",
  "price": 80000,
  "stock": 10
}
```

#### Get All Products

```
GET /api/products
```

---

### 🔹 Cart APIs

#### Add to Cart

```
POST /api/cart/add
```

```json
{
  "userId": "user123",
  "productId": "PRODUCT_ID",
  "quantity": 2
}
```

#### View Cart

```
GET /api/cart/user123
```

#### Clear Cart

```
DELETE /api/cart/user123/clear
```

---

### 🔹 Order APIs

#### Place Order (from cart)

```
POST /api/orders
```

```json
{
  "userId": "user123"
}
```

#### Get Order by ID

```
GET /api/orders/{orderId}
```

---

### 🔹 Payment APIs (Mock Gateway)

#### Create Payment

```
POST /api/payments/create
```

```json
{
  "orderId": "ORDER_ID"
}
```

#### Simulate Payment Webhook

```
POST /api/webhooks/payment
```

```json
{
  "paymentId": "MOCK_xxx",
  "status": "SUCCESS"
}
```

✅ Order status updates to **PAID**

---

## 🔄 Complete Flow

```
Product → Cart → Order → Payment → Webhook → Order PAID
```

---

## 🧠 Viva / Exam Explanation (Short)

* **Controller**: Handles HTTP requests
* **Service**: Business logic
* **Repository**: MongoDB access
* **DTOs**: Request/response data transfer
* **OrderItem**: Embedded document
* **Payment**: Mock external gateway simulation
* **Webhook**: Async payment confirmation

---

## 📌 Notes

* No authentication (kept simple for learning)
* MongoDB uses document-based modeling
* Payment gateway is mocked for safety and simplicity

---

## ✅ Project Status

✔ Fully working backend
✔ Testable using Postman
✔ College + viva ready
✔ Easily extensible (JWT, Razorpay, Swagger)

---

## 🙌 Author

**Shlok Gupta**
Spring Boot E-Commerce Backend Project

---

🎉 **You now have a complete, documented backend application.**
