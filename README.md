# 🚀 Trade Promotion Management (TPM) Backend System

## 📌 Overview
The Trade Promotion Management (TPM) Backend System is a microservices-based application built using Spring Boot to simulate real-world retail/FMCG promotion workflows.

It enables businesses to:
- Manage products and SKU-level pricing  
- Define baseline sales (FORECAST vs ACTUAL)  
- Create and manage promotions  
- Apply promotional tactics (discounts, lump sum, etc.)  
- Calculate price impact, uplift volume, and revenue  

---

## 🧠 System Architecture
- Eureka Server → Service discovery  
- Product Service → Manages products & baseline data  
- TPM Service → Handles promotions, tactics, and calculations  

---

## 🔄 End-to-End Workflow

### 1. Product & Baseline Setup

Create Product:
POST /product  
{
  "name": "Dairy Milk",
  "sku": 96385,
  "price": 10.00,
  "validFrom": "2021-01-01"
}

Create Baseline:
POST /baseline  
{
  "sku": "96385",
  "startDate": "2026-01-11",
  "endDate": "2026-01-17",
  "volume": 50,
  "baselineType": "FORECAST"
}

---

### 2. Promotion Creation

POST /promotions  
{
  "name": "MEMBER OF YEAR SALES",
  "promotionType": "LONG_TERM",
  "startDate": "2026-01-01",
  "endDate": "2027-01-02",
  "status": "DRAFT"
}

---

### 3. Map Product to Promotion

GET /promotion-product/add?sku=96385&promotionId=1  

---

### 4. Apply Tactics

POST /promotion/tactic  
{
  "promotion_id": 1,
  "tacticType": "FLAT_DISCOUNT_PERCENTAGE",
  "discount": 10,
  "perProduct": 0,
  "lumpsum": 10000.00
}

---

### 5. Run Calculation Engine

GET /calc/tactic/{tacticId}

Sample Output:
{
  "sku": 96385,
  "finalPrice": 9.0,
  "upliftVolume": 96,
  "revenue": 864.0
}

---

## ⚙️ Calculation Engine Design
- PriceService → Applies discount  
- UpliftService → Adjusts baseline volume  
- RevenueService → Calculates final revenue  

---

## 🧩 Key Features
- Microservices-based architecture  
- SKU-level promotion mapping  
- Baseline modeling (FORECAST vs ACTUAL)  
- Tactic-driven promotion engine  
- Modular calculation services  
- REST APIs with Swagger  
- Clean layered architecture  

---

## 🛠️ Tech Stack
- Java, Spring Boot  
- Spring Data JPA, Hibernate  
- MySQL  
- REST APIs, Swagger  
- Maven  

---

## 📁 Project Structure
eureka/ → Service discovery  
product/ → Product & baseline service  
tpm-backend/ → Promotion, tactic & calculation service  

---

## 🚀 Future Enhancements
- Apache Kafka for event-driven processing  
- Redis for caching  
- API Gateway  
- Docker & Cloud deployment  

---

## 🧠 Key Learnings
- Designing real-world domain models  
- Handling time-based baseline data  
- Structuring business logic into services  
- Building a calculation engine  

---

## 🔗 Author
Kirti Bardhan Tung  
Backend Developer | Java | Spring Boot  

---

## ⭐ Give a star if you like this project!
