# 🚀 TPM Backend System (Trade Promotion Management)

## 📌 Overview

The Trade Promotion Management (TPM) Backend System is a Spring Boot–based microservices-driven application designed to manage promotional campaigns, product mappings, and trade calculations.

It enables businesses to create, manage, and evaluate promotions with structured workflows and scalable backend architecture.

---

## 🧩 Core Modules

### 🔹 Product Service
- Manages product master data
- Handles SKU, pricing, and validity

### 🔹 TPM Service
- Manages promotions and tactics
- Handles promotion-product mapping
- Applies business rules and validations

---

## 🎯 Key Features

- Promotion lifecycle management (**DRAFT → APPROVED → ACTIVE**)  
- Support for multiple promotion tactics:
  - Flat Discount  
  - Buy One Get One (BOGO)  
- Product-to-promotion mapping  
- Dynamic filtering (status, date range)  
- Pagination and sorting  
- Validation and exception handling  
- Microservice-based architecture  

---

## 🏗️ Architecture
            ┌──────────────────────┐
            │   Client / Postman   │
            └─────────┬────────────┘
                      │
                      ▼
            ┌──────────────────────┐
            │   TPM Service        │
            │----------------------│
            │ Promotion            │
            │ PromotionProduct     │
            │ Business Logic       │
            └─────────┬────────────┘
                      │ (REST API Call)
                      ▼
            ┌──────────────────────┐
            │   Product Service    │
            │----------------------│
            │ Product              │
            │ Pricing              │
            └──────────────────────┘
	
---

## ⚙️ Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- MySQL  
- REST APIs  
- Maven  
- Postman  

---

## 🔗 API Endpoints

### 🔹 Promotion APIs

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | `/promotions` | Create promotion |
| GET | `/promotions` | Get all promotions |
| PUT | `/promotions/{id}/approve` | Approve promotion |
| PUT | `/promotions/{id}/activate` | Activate promotion |

---

### 🔹 Query APIs

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | `/promotions/status/{status}` | Get by status |
| GET | `/promotions/active` | Get active promotions |
| GET | `/promotions/date-range` | Filter by date |
| GET | `/promotions/filter` | Combined filters |

---

### 🔹 Pagination APIs

| Method | Endpoint | Description |
|--------|---------|------------|
| GET | `/promotions/paginated` | Paginated data |
| GET | `/promotions/filter-paginated` | Filter + pagination |

---

### 🔹 Promotion-Product APIs

| Method | Endpoint | Description |
|--------|---------|------------|
| POST | `/promotion-products` | Add product to promotion |
| GET | `/promotion-products/{promotionId}` | Get products for promotion |

---

## 🔄 Service-to-Service Communication

- TPM Service interacts with Product Service via REST APIs  
- Product data is fetched using SKU  
- No direct database relationship (microservice separation)  

---

## ⚡ Business Logic Highlights

- Validates promotion dates and discount constraints  
- Ensures controlled state transitions  
- Supports product-level discount overrides  
- Prevents invalid promotion configurations  

---

## 🛠️ Setup & Run

### 🔹 1. Clone the Repository
```bash
git clone https://github.com/your-username/tpm-backend.git
cd tpm-backend

##👨‍💻 Author

-Kirti Bardhan Tung
-Backend Developer | Java | Spring Boot
