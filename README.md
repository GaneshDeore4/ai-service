# 🧠 AI Agent Service (Java – Spring Boot)

A **Spring Boot–based AI Agent Service** built in **Java**, designed for **learning and production readiness**, demonstrating how to integrate **Large Language Models (LLMs)** into backend systems using **clean architecture**, **prompt control**, **structured JSON output**, and **conversation memory**.

This project intentionally avoids heavy AI frameworks to help Java developers understand **how AI agents actually work under the hood**.

---

## 📋 Table of Contents
- Prerequisites
- Installation
- Configuration
- Running the Application
- API Endpoints
- Testing with Postman
- Troubleshooting

---

## 🔧 Prerequisites

- Java 17+  
  ```bash
  java -version
  ```

- Maven  
  ```bash
  mvn -version
  ```

- Ollama (Local LLM Runtime)  
  https://ollama.com  
  ```bash
  ollama --version
  ```

- Postman (optional)

---

## 📥 Installation

```bash
git clone https://github.com/GaneshDeore4/ai-service.git
cd ai-service
mvn clean install
```

---

## ⚙️ Configuration

### Ollama Model Setup (One-time)

```bash
ollama pull llama3
# OR
ollama pull mistral
```

Verify:
```bash
ollama list
```

---

### application.yml

```yaml
server:
  port: 8080

ollama:
  base-url: http://localhost:11434
  model: llama3
```

---

## 🚀 Running the Application

```bash
mvn spring-boot:run
```

Expected:
```
Tomcat started on port 8080
```

---

## 📡 API Endpoints

### Health Check
```
GET /health
```

Response:
```json
{
  "status": "UP"
}
```

---

### AI Support Agent
```
POST /ai/support
```

Headers:
```
Content-Type: application/json
X-Session-Id: user-1
```

Request:
```json
{
  "question": "What is your refund policy?"
}
```

Response:
```json
{
  "answer": "Refunds available within 7 days of purchase",
  "source": "FAQ",
  "confidence": "HIGH"
}
```

---

## 🧪 Testing with Postman

1. Start application  
2. Open Postman  
3. Call:
   ```
   POST http://localhost:8080/ai/support
   ```
4. Use same `X-Session-Id` to test conversation memory

---

---

## 🔍 Troubleshooting

- Ollama 404 error  
  ```bash
  curl http://localhost:11434
  ```

- Slow response  
  - Local LLM inference is CPU intensive
  - First request is always slower

---

## 👤 Author

Ganesh Deore  
Senior Java Developer

GitHub: https://github.com/GaneshDeore4

---

## 📄 License

This project is intended for learning and experimentation.
